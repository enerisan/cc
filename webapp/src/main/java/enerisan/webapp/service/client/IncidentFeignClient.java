package enerisan.webapp.service.client;

import enerisan.webapp.dto.IncidentWithCategoriesDto;
import enerisan.webapp.dto.UserDto;
import enerisan.webapp.model.Incident;
import enerisan.webapp.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("incident")
public interface IncidentFeignClient {
    @PostMapping(value= "api/user", consumes = "application/json")
    public User createUser(@RequestBody UserDto user);

    @GetMapping(value = "/api/findUserByUsername", consumes = "application/json")
    public User findUserByUsername(@RequestParam("email") String email);

    @GetMapping(value = "/api/incidents/{userId}", consumes = "application/json")
    List<IncidentWithCategoriesDto> getAllIncidentsWithCategoriesByUserId(@PathVariable("userId") Integer userId);


    @PostMapping("/api/incident")
    Incident createIncident(@RequestBody Incident incident);




}
