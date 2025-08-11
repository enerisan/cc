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
import java.util.Optional;

@Service
public class IncidentService {
    @Autowired
    IncidentFeignClient incidentFeignClient;



    @Autowired
    private ImageServiceFeignClient imageServiceFeignClient;

    public List<IncidentWithCategoriesDto> getAllIncidentsWithCategoriesByUserId(Integer userId) {
        return incidentFeignClient.getAllIncidentsWithCategoriesByUserId((userId));
    }


    public List<IncidentWithCategoriesDto> getAllIncidentsWithCategories() {
          return incidentFeignClient.getAllIncidentsWithCategories();
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


        // Assign incidentId to uploaded image
        String imageId = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        imageServiceFeignClient.assignIncidentIdToImage(imageId, newIncident.getId().toString());

        // I add the categories to the created incident
        for (Integer categoryId : dto.getCategoryIds()) {
            IncidentCategoryDto incidentCategoryDto = new IncidentCategoryDto(newIncident.getId(), categoryId);
            incidentFeignClient.addIncidentCategory(incidentCategoryDto);
        }
    }

    //To show incident detail

    public IncidentWithCategoriesDto getIncidentWithCategoriesById(Integer id) {
       return incidentFeignClient.getIncidentById(id);
    }

    public List<City> getAllCities() {
        return incidentFeignClient.getAllCities();
    }

    public void updateIncidentWithCategories(IncidentForm dto) {
        Incident incident = dto.toIncident();
        incident.setId(dto.getId());

        // Update incident
        incidentFeignClient.updateIncident(dto.getId(), incident);


        // Update categories
        incidentFeignClient.deleteIncidentCategoriesByIncidentId(dto.getId());
        for (Integer categoryId : dto.getCategoryIds()) {
            IncidentCategoryDto incidentCategoryDto = new IncidentCategoryDto(dto.getId(), categoryId);
            incidentFeignClient.addIncidentCategory(incidentCategoryDto);
        }
    }

    public void deletePatient(Integer id) {
        incidentFeignClient.deleteIncidentCategoriesByIncidentId(id);
        incidentFeignClient.deleteIncidentByIncidentId(id);
    }
}


