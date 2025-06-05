package enerisan.webapp.service.client;

import enerisan.webapp.dto.UserDto;
import enerisan.webapp.model.Incident;
import enerisan.webapp.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("incident")
public interface IncidentFeignClient {
    @PostMapping(value= "api/user", consumes = "application/json")
    public User createUser(@RequestBody UserDto user);





    @GetMapping(value = "/api/findUserByUsername", consumes = "application/json")
    public User findUserByUsername(@RequestParam("email") String email);


    @PostMapping(value= "api/incident", consumes = "application/json")
    Incident createIncident(Incident incident);
}
