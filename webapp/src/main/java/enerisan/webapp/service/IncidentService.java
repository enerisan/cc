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
        incident.setAddress(form.get);
        return incident.createIncident(incident);
    }
}
