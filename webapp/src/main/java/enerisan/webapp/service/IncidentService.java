package enerisan.webapp.service;

import enerisan.webapp.dto.CategoryDto;
import enerisan.webapp.dto.IncidentCategoryDto;
import enerisan.webapp.dto.IncidentForm;
import enerisan.webapp.dto.IncidentWithCategoriesDto;
import enerisan.webapp.model.City;
import enerisan.webapp.model.Incident;
import enerisan.webapp.model.Status;
import enerisan.webapp.model.User;
//import enerisan.webapp.service.client.IncidentCategoryFeignClient;
import enerisan.webapp.service.client.IncidentFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class IncidentService {
    @Autowired
    IncidentFeignClient incidentFeignClient;

//    @Autowired
//    IncidentCategoryFeignClient incidentCategoryFeignClient;

    @Autowired
    SessionService sessionService;

    public List<IncidentWithCategoriesDto> getAllIncidentsWithCategoriesByUserId(Integer userId) {
        return incidentFeignClient.getAllIncidentsWithCategoriesByUserId((userId));
    }


    public List <CategoryDto> getAllCategories() {
        return incidentFeignClient.getAllCategories();
    }

    //To create and IncidentWithCategories
    public void createIncidentWithCategories(IncidentWithCategoriesDto dto) {
        // I create the incident
        Incident incident = incidentFeignClient.createIncident(dto.toIncident());

        // I add the categories to the created incident
        for (Integer categoryId : dto.getCategoryIds()) {
            IncidentCategoryDto incidentCategoryDto = new IncidentCategoryDto(incident.getId(), categoryId);
            incidentFeignClient.addIncidentCategory(incidentCategoryDto);
        }


    }

    public List<City> getAllCities() {
        return incidentFeignClient.getAllCities();
    }
}