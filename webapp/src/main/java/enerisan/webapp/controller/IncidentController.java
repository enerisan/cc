package enerisan.webapp.controller;

import enerisan.webapp.dto.IncidentForm;
import enerisan.webapp.model.Incident;
import enerisan.webapp.model.User;
import enerisan.webapp.service.IncidentService;
import enerisan.webapp.service.SessionService;
import enerisan.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IncidentController {

    @Autowired
    SessionService sessionService;


    @Autowired
    UserService userService;

    @Autowired
    IncidentService incidentService;


    //To show incidentAdd form :
    @GetMapping("/addIncident")
    public ModelAndView showAddIncidentForm() {
        IncidentForm incidentForm= new IncidentForm();

        return new ModelAndView("add_incident", "incidentForm", incidentForm);
    }

    @PostMapping("/addIncident")
    public String processIncidentByUserId(Model model, @ModelAttribute("incident") IncidentForm incidentForm) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        incidentService.addIncident(incidentForm);
        return "redirect:/" + user.getId();
    }

}
