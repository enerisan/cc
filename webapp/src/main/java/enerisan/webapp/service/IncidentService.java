package enerisan.webapp.service;

import enerisan.webapp.dto.IncidentForm;
import enerisan.webapp.model.Incident;
import enerisan.webapp.model.User;
import enerisan.webapp.service.client.IncidentFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidentService {
    @Autowired
    IncidentFeignClient incidentFeignClient;

    @Autowired
    SessionService sessionService;

    public Incident addPatient(IncidentForm form) {
        User user = sessionService.sessionUser();
        Incident incident = new Incident();
        incident.setTitle(form.getTitle());
        incident.setDescription(form.getDescription());
        incident.setAddress(form.getAddress());
        incident.setCityId(1);
        incident.setImage(form.getImage());
        incident.setLatitude(form.getLatitude());
        incident.setLongitude(form.getLongitude());
        incident.setNeighborhood(form.getNeighborhood());
        incident.setPostalCode(form.getPostalCode());
        incident.setStatusId(1);
        incident.setUserId(user.getId());

        return incidentFeignClient.createIncident(incident);
    }
}
