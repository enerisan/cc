package enerisan.incident.controller;

import enerisan.incident.service.IncidentProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentProcedureController {

    @Autowired
    private IncidentProcedureService incidentProcedureService;

    @GetMapping("/monthly-chart")
    public List<Object[]> getMonthlyChart(
            @RequestParam int year,
            @RequestParam int cityId) {

        return incidentProcedureService.getMonthlyIncidentsByCategory(year, cityId);
    }
}
