package enerisan.incident.service;

import enerisan.incident.dto.CategoryDto;
import enerisan.incident.dto.IncidentWithCategoriesDto;
import enerisan.incident.mapper.IncidentMapper;
import enerisan.incident.model.City;
import enerisan.incident.model.Incident;
import enerisan.incident.model.Status;
import enerisan.incident.model.User;
import enerisan.incident.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncidentService {
    @Autowired
    IncidentRepository incidentRepository;
    @Autowired
    IncidentCategoryRepository incidentCategoryRepository;

    @Autowired
    IncidentMapper incidentMapper;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatusRepository statusRepository;


    public IncidentWithCategoriesDto findIncidentWithCategoriesById(Integer id) {
        return incidentRepository.findById(id)
                .map(incident -> incidentMapper.incidentToIncidentWithCategoriesDto(incident))
                .orElseThrow(() -> new EntityNotFoundException("Incident not found"));
    }

    public List<IncidentWithCategoriesDto> findAllIncidentsWithCategoriesByUserId(Integer userId) {
        return incidentRepository.findByUserId(userId)
                .stream()
                .map(incident -> incidentMapper.incidentToIncidentWithCategoriesDto(incident))                          // 3
                .collect(Collectors.toList());
    }

    public List<IncidentWithCategoriesDto> findAllIncidentsWithCategories() {
        return incidentRepository.findAll()
                .stream()
                .map(incident -> incidentMapper.incidentToIncidentWithCategoriesDto(incident))                          // 3
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> createIncident(Incident incident) {
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

    public Incident updateIncident(Incident incident, Integer id) {

        return incidentRepository.findById(id)
                .map(existingIncident -> {

                    existingIncident.setCity(incident.getCity());
                    existingIncident.setTitle(incident.getTitle());
                    existingIncident.setAddress(incident.getAddress());
                    existingIncident.setNeighborhood(incident.getNeighborhood());
                    existingIncident.setDescription(incident.getDescription());
                    existingIncident.setPostalCode(incident.getPostalCode());
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
}