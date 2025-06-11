package enerisan.incident.controller;


import enerisan.incident.dto.IncidentCategoryDto;
import enerisan.incident.model.Category;
import enerisan.incident.model.IncidentCategory;
import enerisan.incident.repository.IncidentCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private IncidentCategoryRepository incidentCategoryRepository;


    @GetMapping("/categorieByIncidentId/{incidentId}")
    public List <IncidentCategoryDto> getCategoryByIncidentId(@PathVariable Integer incidentId){
        return incidentCategoryRepository.findByIncidentId(incidentId)
                .stream()
                .map(IncidentCategoryDto::new)
                .collect(Collectors.toList());
    }

}
