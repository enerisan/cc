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

        if (user.getRole().getId() == 2) {
            model.addAttribute("user", user);
            List<IncidentWithCategoriesDto> incidentsWithCategories = incidentService.getAllIncidentsWithCategoriesByUserId(user.getId());
            model.addAttribute("incidents", incidentService.getAllIncidentsWithCategoriesByUserId((user.getId())));
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

            model.addAttribute("categoryIcons", categoryIcons);
            return new ModelAndView("user_dashboard");
        } else if (user.getRole().getId() == 1) {
            model.addAttribute("user", user);
            return new ModelAndView("admin_dashboard");
        }
        return new ModelAndView("access_denied");
    }

    @GetMapping("/signup")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("signup", "signUpForm", new SignUpForm());
    }


    @PostMapping("/signup")
    public ModelAndView processRequest(@ModelAttribute("signUpForm") SignUpForm form) {
        userService.register(form);
        return new ModelAndView("signin");
    }
}
