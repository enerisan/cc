package enerisan.incident.controller;

import enerisan.incident.model.Incident;
import enerisan.incident.model.User;
import enerisan.incident.repository.IncidentRepository;
import enerisan.incident.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class IncidentController {

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/incidents")
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    @GetMapping("/incident/{id}")
    public Optional<Incident> getIncidentById(@PathVariable Integer id){
        return incidentRepository.findById(id);
    }

    @GetMapping("/incidents/{userId}")
    public Optional<Incident> getIncidenstByUserId(@PathVariable Integer userId){
        return incidentRepository.findByUserId(userId);
    }

    @PostMapping("/incident")
    public Incident addIncident(@RequestBody Incident incident, @AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        incident.setUser(user);  // Asignar el usuario autenticado
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
    public void deleteIncidentById(@PathVariable Integer id){
        incidentRepository.deleteById(id);
    }
}
