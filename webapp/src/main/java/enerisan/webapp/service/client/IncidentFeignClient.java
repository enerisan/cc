package enerisan.webapp.service.client;

import enerisan.webapp.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("incident")
public interface IncidentFeignClient {
    @PostMapping(value= "api/user", consumes = "application/json")
    public User createUser(User user);

    @GetMapping(value = "/api/findUserByEmail", consumes = "application/json")
    public User findUserByEmail(@RequestParam String email);
}
