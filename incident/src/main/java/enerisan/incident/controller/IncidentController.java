package enerisan.incident.controller;

import enerisan.incident.dto.CategoryDto;
import enerisan.incident.dto.IncidentWithCategoriesDto;
import enerisan.incident.model.*;
import enerisan.incident.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CategoryRepository categoryRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/incidents")
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    @GetMapping("/incident/{id}")
    public Optional<Incident> getIncidentById(@PathVariable Integer id) {
        return incidentRepository.findById(id);
    }

    @GetMapping("/incidents/{userId}")
    public List<IncidentWithCategoriesDto> getIncidentsWithCategoriesByUserId(@PathVariable Integer userId) {
        List<Incident> incidents = incidentRepository.findByUserId(userId);

        return incidents.stream().map(incident -> {
            List<CategoryDto> categories = incidentCategoryRepository.findByIncidentId(incident.getId())
                    .stream()
                    .map(incidentCategory -> new CategoryDto(incidentCategory.getCategory()))
                    .collect(Collectors.toList());

            return new IncidentWithCategoriesDto(
                    incident.getId(),
                    incident.getCity().getId(),
                    incident.getUser().getId(),
                    incident.getStatus().getId(),
                    incident.getTitle(),
                    incident.getAddress(),
                    incident.getNeighborhood(),
                    incident.getPostalCode(),
                    incident.getImage(),
                    incident.getDescription(),
                    incident.getCreatedAt(),
                    incident.getClosedAt(),
                    incident.getLatitude(),
                    incident.getLongitude(),
                    categories
            );
        }).collect(Collectors.toList());
    }

    @PostMapping("/incident")
    public Incident addIncident(@RequestBody Incident incident) {
        return incidentRepository.save(incident);
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
