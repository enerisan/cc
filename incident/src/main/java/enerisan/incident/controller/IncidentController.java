package enerisan.incident.controller;

import enerisan.incident.dto.CategoryDto;
import enerisan.incident.dto.IncidentWithCategoriesDto;
import enerisan.incident.model.*;
import enerisan.incident.repository.*;
import enerisan.incident.service.IncidentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class IncidentController {

    @Autowired
    private IncidentRepository incidentRepository;
    @Autowired
    private IncidentCategoryRepository incidentCategoryRepository;
    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IncidentService incidentService;

    @GetMapping("/incidents")
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    @GetMapping("/incident/{id}")
    public IncidentWithCategoriesDto getIncidentWithCategoriesById (@PathVariable Integer id) {

      return incidentService.findIncidentWithCategoriesById(id);
    }

    @GetMapping("/incidents/{userId}")
    public List<IncidentWithCategoriesDto> getIncidentsWithCategoriesByUserId(@PathVariable Integer userId) {
        List<Incident> incidents = incidentRepository.findByUserId(userId);

        return incidents.stream().map(incident -> {
            List<CategoryDto> categories = incidentCategoryRepository.findByIncidentId(incident.getId())
                    .stream()
                    .map(incidentCategory -> new CategoryDto(incidentCategory.getCategory()))
                    .collect(Collectors.toList());

            List<Integer> categoryIds = categories.stream()
                    .map(CategoryDto::getId)
                    .collect(Collectors.toList());


            IncidentWithCategoriesDto dto = new IncidentWithCategoriesDto();
            dto.setId(incident.getId());
            dto.setCityId(incident.getCity().getId());
            dto.setCityName(incident.getCity().getName());
            dto.setUserId(incident.getUser().getId());
            dto.setStatusId(incident.getStatus().getId());
            dto.setTitle(incident.getTitle());
            dto.setAddress(incident.getAddress());
            dto.setNeighborhood(incident.getNeighborhood());
            dto.setPostalCode(incident.getPostalCode());
            dto.setImageUrl(incident.getImage());
            dto.setDescription(incident.getDescription());
            dto.setCreatedAt(incident.getCreatedAt());
            dto.setClosedAt(incident.getClosedAt());
            dto.setLatitude(incident.getLatitude());
            dto.setLongitude(incident.getLongitude());
            dto.setCategories(categories);
            dto.setCategoryIds(categoryIds);

            return dto;
        }).collect(Collectors.toList());
    }


    @PostMapping("/incident")
    public ResponseEntity<?> addIncident(@RequestBody Incident incident) {
        try {
            City city = cityRepository.findById(incident.getCity().getId())
                    .orElseThrow(() -> new IllegalArgumentException("City not found"));

            User user = userRepository.findById(incident.getUser().getId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            Status status = statusRepository.findById(incident.getStatus().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Status not found"));

            incident.setCity(city);
            incident.setUser(user);
            incident.setStatus(status);

            if (incident.getCreatedAt() == null) {
                incident.setCreatedAt(LocalDateTime.now());
            }

            Incident saved = incidentRepository.save(incident);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving the incident: " + e.getMessage());
        }
    }



    @PutMapping("/incidents/{id}")
    public Incident updateIncident(@RequestBody Incident incident, @PathVariable Integer id) {
        return incidentRepository.findById(id)
                .map(existingIncident -> {

                    existingIncident.setCity(incident.getCity());
                    existingIncident.setTitle(incident.getTitle());
                    existingIncident.setAddress(incident.getAddress());
                    existingIncident.setNeighborhood(incident.getNeighborhood());
                    existingIncident.setDescription(incident.getDescription());
                    existingIncident.setPostalCode(incident.getPostalCode());
                    existingIncident.setAddress(incident.getAddress());
                    existingIncident.setImage(incident.getImage());
                    if (incident.getClosedAt() != null) {
                        existingIncident.setClosedAt(incident.getClosedAt());
                    }

                    existingIncident.setLatitude(incident.getLatitude());
                    existingIncident.setLongitude(incident.getLongitude());
                    return incidentRepository.save(existingIncident);
                })
                .orElseThrow(() -> new RuntimeException("Incident not found with id " + id));
    }

    @DeleteMapping("/incidents/{id}")
    public void deleteIncidentById(@PathVariable Integer id) {
        incidentRepository.deleteById(id);
    }
}
