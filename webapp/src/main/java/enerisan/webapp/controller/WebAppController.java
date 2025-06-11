package enerisan.webapp.controller;



import enerisan.webapp.dto.SignUpForm;
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
            model.addAttribute("incidents", incidentService.getAllIncidentsByUserId(user.getId()));
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
