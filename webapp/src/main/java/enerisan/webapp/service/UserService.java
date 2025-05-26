package enerisan.webapp.service;


import enerisan.webapp.dto.SignUpForm;
import enerisan.webapp.model.User;
import enerisan.webapp.service.client.IncidentFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
   IncidentFeignClient incidentFeignClient;

    public User register(SignUpForm form){

        User user = new User();
        user.setFirstname(form.getFirstname());
        user.setLastname(form.getLastname());
        user.setPhone(form.getPhone());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        return incidentFeignClient.createUser(user);
    }




}
