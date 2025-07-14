package enerisan.webapp.service;

import enerisan.webapp.dto.CategoryDto;
import enerisan.webapp.dto.IncidentCategoryDto;
import enerisan.webapp.dto.IncidentForm;
import enerisan.webapp.dto.IncidentWithCategoriesDto;
import enerisan.webapp.model.City;
import enerisan.webapp.model.Incident;
//import enerisan.webapp.service.client.IncidentCategoryFeignClient;
import enerisan.webapp.service.client.ImageServiceFeignClient;
import enerisan.webapp.service.client.IncidentFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService {
    @Autowired
    IncidentFeignClient incidentFeignClient;

//    @Autowired
//    IncidentCategoryFeignClient incidentCategoryFeignClient;

    @Autowired
    SessionService sessionService;
    @Autowired
    private ImageServiceFeignClient imageServiceFeignClient;

    public List<IncidentWithCategoriesDto> getAllIncidentsWithCategoriesByUserId(Integer userId) {
        return incidentFeignClient.getAllIncidentsWithCategoriesByUserId((userId));
    }


    public List <CategoryDto> getAllCategories() {
        return incidentFeignClient.getAllCategories();
    }

    //To create and IncidentWithCategories
    public void createIncidentWithCategories(IncidentForm dto) {


        // I create the incident
        Incident incident = dto.toIncident();

        String imageUrl = imageServiceFeignClient.uploadImage(dto.getImage());

        incident.setImage(imageUrl);

        Incident newIncident = incidentFeignClient.createIncident(incident);

        // I add the categories to the created incident
        for (Integer categoryId : dto.getCategoryIds()) {
            IncidentCategoryDto incidentCategoryDto = new IncidentCategoryDto(newIncident.getId(), categoryId);
            incidentFeignClient.addIncidentCategory(incidentCategoryDto);
        }


    }

    public List<City> getAllCities() {
        return incidentFeignClient.getAllCities();
    }
}