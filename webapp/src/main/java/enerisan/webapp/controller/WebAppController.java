package enerisan.webapp.controller;



import enerisan.webapp.dto.IncidentWithCategoriesDto;
import enerisan.webapp.dto.SignUpForm;
import enerisan.webapp.model.Incident;
import enerisan.webapp.model.User;
import enerisan.webapp.service.IncidentService;
import enerisan.webapp.service.SessionService;
import enerisan.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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

            // Map of category names to their corresponding icon classes
            Map<String, String> categoryIcons = new HashMap<>();
            categoryIcons.put("Voirie", "fa-solid fa-triangle-exclamation");
            categoryIcons.put("Éclairage public", "fa-solid fa-lightbulb");
            categoryIcons.put("Trottoirs", "fa-solid fa-shoe-prints");
            categoryIcons.put("Conteneurs à déchets", "fa-solid fa-trash");
            categoryIcons.put("Aire de jeux", "fa-solid fa-child");
            categoryIcons.put("Fontaines", "fa-solid fa-faucet");
            categoryIcons.put("Propreté", "fa-solid fa-broom");
            categoryIcons.put("Gestion des déchets", "fa-solid fa-recycle");
            categoryIcons.put("Espaces verts", "fa-solid fa-tree");
            categoryIcons.put("Installations sportives", "fa-solid fa-dumbbell");
            categoryIcons.put("Bâtiments publics", "fa-solid fa-building");
            categoryIcons.put("Nuisibles", "fa-solid fa-bug");
            categoryIcons.put("Égouts", "fa-solid fa-water");
            categoryIcons.put("Circulation", "fa-solid fa-car-side");
            categoryIcons.put("Autres", "fa-solid fa-ellipsis-h");
            categoryIcons.put("Accessibilité", "fa-solid fa-wheelchair");

            // Add category icons map to the model
            model.addAttribute("categoryIcons", categoryIcons);

            // Return user dashboard view
            return new ModelAndView("user_dashboard");

        } else if (user.getRole().getId() == 1) {
            // Admin user
            model.addAttribute("user", user);
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
    public ModelAndView processRequest(@ModelAttribute("incidentWithCategoriesDto") SignUpForm form) {
        userService.register(form);
        return new ModelAndView("signin");
    }
}
