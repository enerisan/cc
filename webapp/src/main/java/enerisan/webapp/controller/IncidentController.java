package enerisan.webapp.controller;

import enerisan.webapp.dto.CategoryDto;
import enerisan.webapp.dto.IncidentForm;
import enerisan.webapp.dto.IncidentWithCategoriesDto;
import enerisan.webapp.dto.UpdateIncidentForm;
import enerisan.webapp.mapper.IncidentMapper;
import enerisan.webapp.model.City;
import enerisan.webapp.model.User;
import enerisan.webapp.service.CategoryIconsService;
import enerisan.webapp.service.IncidentService;
import enerisan.webapp.service.SessionService;
import enerisan.webapp.service.UserService;
import enerisan.webapp.service.client.ImageServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    ImageServiceFeignClient imageServiceFeignClient;

    @Value("${google.api.key}")
    private String googleApiKey;


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
        mav.addObject("googleApiKey", googleApiKey);
        return mav;
    }

    //To create an incident :
    @PostMapping("/addIncident")
    public String addIncident(@ModelAttribute IncidentForm dto) {

        User user = sessionService.sessionUser();
        dto.setUserId(user.getId());
        dto.setStatusId(1);

        incidentService.createIncidentWithCategories(dto);

        return "redirect:/";
    }

    //To show incident detail
    @GetMapping("/incident/{id}")
    public ModelAndView showIncidentDetail(@PathVariable Integer id) {
        User user = sessionService.sessionUser();
        ModelAndView mav = new ModelAndView("incident_detail");
        IncidentWithCategoriesDto dto = incidentService.getIncidentWithCategoriesById(id);
        Map<String, String> categoryIcons = categoryIconsService.getCategoryIcons();
        IncidentForm incidentForm = IncidentMapper.toForm(dto);
        UpdateIncidentForm updateIncidentForm = new UpdateIncidentForm();
        updateIncidentForm.setIncidentWithCategories(dto);
        updateIncidentForm.setIncidentForm(incidentForm);
        List<City> Cities = incidentService.getAllCities();
        List<CategoryDto> categories = incidentService.getAllCategories();
        mav.addObject("incident", dto);
        mav.addObject("categoryIcons", categoryIcons);
        mav.addObject("incidentForm", incidentForm);
        mav.addObject("categories", categories);
        mav.addObject("cities", Cities);
        mav.addObject("updateIncidentForm", updateIncidentForm);
        return mav;
    }

    //To update incident :

    @PostMapping("/incident/update")
    public String updateIncident(@ModelAttribute UpdateIncidentForm form) {
        User user = sessionService.sessionUser();

        IncidentForm incidentForm = form.getIncidentForm();
        incidentForm.setUserId(user.getId());

        String incidentIdAsString = String.valueOf(incidentForm.getId());

        // If delete image is chosen
        if (incidentForm.isRemoveImage()) {
            imageServiceFeignClient.deleteImageByIncidentId(incidentIdAsString);
            incidentForm.setImageUrl(null);
        }

        // If a new image is to be uploaded
        if (incidentForm.getImage() != null && !incidentForm.getImage().isEmpty()) {
            // Upload image without incident id
            String newImageUrl = imageServiceFeignClient.uploadImage(incidentForm.getImage());
            incidentForm.setImageUrl(newImageUrl);

            // We obtain image id from url
            String imageId = newImageUrl.substring(newImageUrl.lastIndexOf("/") + 1);

            // We assign incidentId to image in Mongo DB
            imageServiceFeignClient.assignIncidentIdToImage(imageId, incidentIdAsString);
        }

        // To update incident and categories
        incidentService.updateIncidentWithCategories(incidentForm);

        return "redirect:/incident/" + incidentForm.getId();
    }


    @GetMapping("/incident/delete/{id}")
    public String deleteIncident(@PathVariable Integer id) {
        incidentService.deletePatient(id);
        return "redirect:/";
    }



}
