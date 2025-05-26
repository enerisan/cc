package enerisan.webapp.service;



import enerisan.webapp.model.User;
import enerisan.webapp.service.client.IncidentFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    IncidentFeignClient incidentFeignClient;



    public User sessionUser() {
        org.springframework.security.core.userdetails.User springUser =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return incidentFeignClient.findUserByEmail(springUser.getUsername());

    }
}
