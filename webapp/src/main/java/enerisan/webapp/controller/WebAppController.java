package enerisan.webapp.controller;


import enerisan.webapp.dto.IncidentWithCategoriesDto;
import enerisan.webapp.dto.SignUpForm;
import enerisan.webapp.model.Incident;
import enerisan.webapp.model.User;
import enerisan.webapp.service.CategoryIconsService;
import enerisan.webapp.service.IncidentService;
import enerisan.webapp.service.SessionService;
import enerisan.webapp.service.UserService;
import enerisan.webapp.service.client.IncidentFeignClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class WebAppController {

    @Autowired
    SessionService sessionService;


    @Autowired
    UserService userService;

    @Autowired
    IncidentService incidentService;

    @Autowired
    private CategoryIconsService categoryIconsService;

    @Autowired
    IncidentFeignClient incidentFeignClient;


    @GetMapping("/")
    public ModelAndView welcome(Model model) {
        User user = sessionService.sessionUser();

        // Check if user is a regular user (role id 2)
        if (user.getRole().getId() == 2) {
            model.addAttribute("user", user);

            // Fetch incidents with categories for this user
            List<IncidentWithCategoriesDto> incidentsWithCategories = incidentService.getAllIncidentsWithCategoriesByUserId(user.getId());

            // Add incidents to the model
            model.addAttribute("incidents", incidentsWithCategories);

            // Add category icons map to the model
            Map<String, String> categoryIcons = categoryIconsService.getCategoryIcons();
            model.addAttribute("categoryIcons", categoryIcons);

            // Return user dashboard view
            return new ModelAndView("user_dashboard");

        } else if (user.getRole().getId() == 1) {
            // Admin user
            model.addAttribute("user", user);

            List<IncidentWithCategoriesDto> incidentsWithCategories = incidentService.getAllIncidentsWithCategories();

            // Add incidents to the model
            model.addAttribute("incidents", incidentsWithCategories);

            // Add category icons map to the model
            Map<String, String> categoryIcons = categoryIconsService.getCategoryIcons();
            model.addAttribute("categoryIcons", categoryIcons);


            //add prepared  data for graphic
            int year = java.time.Year.now().getValue();
            int cityId = 1; // configurable
            List<List<Object>> monthlyIncidents = incidentFeignClient.getMonthlyIncidents(year, cityId);

            model.addAttribute("monthlyIncidents", monthlyIncidents);


            // Return user dashboard view
            return new ModelAndView("admin_dashboard");
        }

        // Access denied for other roles or unauthenticated users
        return new ModelAndView("access_denied");
    }


    @GetMapping("/signup")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("signup", "signUpForm", new SignUpForm());
    }


    @PostMapping("/signup")
    public ModelAndView processRequest(
            @ModelAttribute("signUpForm") @Valid SignUpForm form,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("signup");
        }

        userService.register(form);
        return new ModelAndView("signin");
    }
}
