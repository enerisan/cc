package enerisan.webapp.service.client;

import enerisan.webapp.dto.CategoryDto;
import enerisan.webapp.dto.IncidentCategoryDto;
import enerisan.webapp.dto.IncidentWithCategoriesDto;
import enerisan.webapp.dto.UserDto;
import enerisan.webapp.model.City;
import enerisan.webapp.model.Incident;
import enerisan.webapp.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient("incident")
public interface IncidentFeignClient {
    @PostMapping(value = "api/user", consumes = "application/json")
    public User createUser(@RequestBody UserDto user);

    @GetMapping(value = "/api/findUserByUsername", consumes = "application/json")
    public User findUserByUsername(@RequestParam("email") String email);

    @GetMapping(value = "/api/incidents/{userId}", consumes = "application/json")
    List<IncidentWithCategoriesDto> getAllIncidentsWithCategoriesByUserId(@PathVariable("userId") Integer userId);

    @GetMapping(value = "api/incidents")
    List<IncidentWithCategoriesDto> getAllIncidentsWithCategories();

    @GetMapping("/api/incident/{id}")
    IncidentWithCategoriesDto getIncidentById(@PathVariable Integer id);

    @PostMapping("/api/incident")
    Incident createIncident(@RequestBody Incident incident);

    @PutMapping("/api/incidents/{id}")
    Incident updateIncident(@PathVariable("id") Integer id, @RequestBody Incident incident);

    @GetMapping("/api/categories")
    List<CategoryDto> getAllCategories();


    @PostMapping("/incident-categories")
    void addIncidentCategory(@RequestBody IncidentCategoryDto dto);


    @GetMapping("/api/cities")
    List<City> getAllCities();

    @DeleteMapping("/incident-categories/{incidentId}")
    void deleteIncidentCategoriesByIncidentId(@PathVariable("incidentId") Integer incidentId);


    @DeleteMapping("/api/incidents/{id}")
    void deleteIncidentByIncidentId(@PathVariable("id") Integer id);


}
