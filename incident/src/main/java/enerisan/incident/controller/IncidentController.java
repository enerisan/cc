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
        return incidentService.findAllIncidentsWithCategoriesByUserId(userId);
    }

    @PostMapping("/incident")
    public ResponseEntity<?> addIncident(@RequestBody Incident incident) {
        return incidentService.createIncident(incident);

    }

    @PutMapping("/incidents/{id}")
    public Incident updateIncident(@RequestBody Incident incident, @PathVariable Integer id) {
        return incidentService.updateIncident(incident, id);
    }

    @DeleteMapping("/incidents/{id}")
    public void deleteIncidentById(@PathVariable Integer id) {
        incidentRepository.deleteById(id);
    }
}
