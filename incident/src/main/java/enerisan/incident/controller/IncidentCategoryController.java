package enerisan.incident.controller;

import enerisan.incident.dto.IncidentCategoryDto;
import enerisan.incident.model.IncidentCategory;
import enerisan.incident.service.IncidentCategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incident-categories")
public class IncidentCategoryController {

    private final IncidentCategoryService incidentCategoryService;


    public IncidentCategoryController(IncidentCategoryService incidentCategoryService) {
        this.incidentCategoryService = incidentCategoryService;

    }

    @GetMapping("/{incidentId}/{categoryId}")
    public ResponseEntity<IncidentCategoryDto> getById(
            @PathVariable Integer incidentId,
            @PathVariable Integer categoryId) {
        try {
            IncidentCategoryDto dto = incidentCategoryService.getById(incidentId, categoryId);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<IncidentCategory> addIncidentCategory(@RequestBody IncidentCategoryDto incidentCategoryDto) {
        IncidentCategory incidentCategory = incidentCategoryService.createIncidentCategory(incidentCategoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(incidentCategory);
    }

    // Typically, these relationships aren’t updated via PUT but deleted and recreated instead.
    // Therefore, PUT is not created for this entity.


    @DeleteMapping("/{incidentId}/{categoryId}")
    public ResponseEntity<String> deleteIncidentCategory(
            @PathVariable Integer incidentId, @PathVariable Integer categoryId) {
        incidentCategoryService.delete(incidentId, categoryId);
        return ResponseEntity.ok("Deleted successfully");
    }



}
