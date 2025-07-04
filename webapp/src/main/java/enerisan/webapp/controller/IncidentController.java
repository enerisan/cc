package enerisan.webapp.controller;

import enerisan.webapp.dto.CategoryDto;
import enerisan.webapp.dto.IncidentForm;
import enerisan.webapp.dto.IncidentWithCategoriesDto;
import enerisan.webapp.model.City;
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

import java.util.List;

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
       IncidentWithCategoriesDto  incidentWithCategoriesDto = new IncidentWithCategoriesDto();
       List<City> Cities = incidentService.getAllCities();
        List<CategoryDto> categories = incidentService.getAllCategories();
        ModelAndView mav = new ModelAndView("add_incident");
        mav.addObject("incidentWithCategoriesDto", incidentWithCategoriesDto);
        mav.addObject("categories", categories);
        mav.addObject("cities", Cities);

        return mav;
    }

    @PostMapping("/addIncident")
    public String createIncident(@ModelAttribute("incidentWithCategoriesDto") IncidentWithCategoriesDto dto) {

        User user = sessionService.sessionUser();

        dto.setUserId(1);
        dto.setStatusId(1);

        incidentService.createIncidentWithCategories(dto);

        return "redirect:/";
    }


}
