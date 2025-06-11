package enerisan.webapp.service;

import enerisan.webapp.dto.IncidentForm;
import enerisan.webapp.model.City;
import enerisan.webapp.model.Incident;
import enerisan.webapp.model.Status;
import enerisan.webapp.model.User;
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

    @Autowired
    SessionService sessionService;

    public List<Incident> getAllIncidentsByUserId(Integer userId) {
        return incidentFeignClient.getAllIncidentsByUserId(userId);
    }


    @PostMapping("/incident")
    public Incident addIncident(IncidentForm form) {
        User user = sessionService.sessionUser();
        Incident incident = new Incident();
        incident.setTitle(form.getTitle());
        incident.setDescription(form.getDescription());
        incident.setAddress(form.getAddress());
        City city = new City();
        city.setId(1);
        city.setName("Aix-en-Provence");
        incident.setImage(form.getImage());
        incident.setLatitude(form.getLatitude());
        incident.setLongitude(form.getLongitude());
        incident.setNeighborhood(form.getNeighborhood());
        incident.setPostalCode(form.getPostalCode());
        Status status = new Status();
        status.setId(1);
        status.setType("signalé");
        incident.setStatus(status);
        incident.setUser(user);

        return incidentFeignClient.createIncident(incident);
    }



}
