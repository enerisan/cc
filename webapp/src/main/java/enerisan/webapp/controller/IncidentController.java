package enerisan.webapp.controller;

import enerisan.webapp.dto.CategoryDto;
import enerisan.webapp.dto.IncidentForm;
import enerisan.webapp.dto.IncidentWithCategoriesDto;
import enerisan.webapp.model.City;
import enerisan.webapp.model.User;
import enerisan.webapp.service.CategoryIconsService;
import enerisan.webapp.service.IncidentService;
import enerisan.webapp.service.SessionService;
import enerisan.webapp.service.UserService;
import enerisan.webapp.service.client.ImageServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IncidentController {

    @Autowired
    SessionService sessionService;

    @Autowired
    IncidentService incidentService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryIconsService categoryIconsService;




    //To show incidentAdd form :
    @GetMapping("/addIncident")
    public ModelAndView showAddIncidentForm() {
        IncidentForm  incidentForm = new IncidentForm();
        List<City> Cities = incidentService.getAllCities();
        List<CategoryDto> categories = incidentService.getAllCategories();
        ModelAndView mav = new ModelAndView("add_incident");
        mav.addObject("incidentForm", incidentForm);
        mav.addObject("categories", categories);
        mav.addObject("cities", Cities);

        return mav;
    }

    @PostMapping("/addIncident")
    public String addIncident(@ModelAttribute IncidentForm dto) {

        User user = sessionService.sessionUser();
        dto.setUserId(user.getId());
        dto.setStatusId(1);

        incidentService.createIncidentWithCategories(dto);

        return "redirect:/";
    }

    @GetMapping("/incident/{id}")
    public ModelAndView showIncidentDetail(@PathVariable Integer id) {
        User user = sessionService.sessionUser();
        ModelAndView mav = new ModelAndView("incident_detail");
        IncidentWithCategoriesDto dto = incidentService.getIncidentWithCategoriesById(id);
        Map<String, String> categoryIcons = categoryIconsService.getCategoryIcons();
        mav.addObject("incident", dto);
        mav.addObject("categoryIcons", categoryIcons);
        return mav;
    }

}
