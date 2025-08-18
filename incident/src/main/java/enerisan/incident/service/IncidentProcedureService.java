package enerisan.incident.service;

import enerisan.incident.repository.IncidentProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IncidentProcedureService {

    @Autowired
    private IncidentProcedureRepository incidentProcedureRepository;


    public List<Object[]> getMonthlyIncidentsByCategory(int year, int cityId) {
        return incidentProcedureRepository.getMonthlyIncidentCountsByCategory(year, cityId);
    }
}
