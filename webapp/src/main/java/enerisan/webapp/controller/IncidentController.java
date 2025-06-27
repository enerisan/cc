package enerisan.webapp.controller;

import enerisan.webapp.dto.IncidentForm;
import enerisan.webapp.dto.IncidentWithCategoriesDto;
import enerisan.webapp.model.Incident;
import enerisan.webapp.model.User;
import enerisan.webapp.service.IncidentService;
import enerisan.webapp.service.SessionService;
import enerisan.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public ResponseEntity<Void> createIncident(@RequestBody IncidentWithCategoriesDto dto) {
        incidentService.createIncidentWithCategories(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
