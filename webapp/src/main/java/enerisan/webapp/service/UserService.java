package enerisan.webapp.service;


import enerisan.webapp.dto.SignUpForm;
import enerisan.webapp.dto.UserDto;
import enerisan.webapp.model.Role;
import enerisan.webapp.model.User;
import enerisan.webapp.service.client.IncidentFeignClient;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IncidentFeignClient incidentFeignClient;



    public User register(SignUpForm form){

        UserDto userDto = new UserDto();
        userDto.setFirstname(form.getFirstname());
        userDto.setLastname(form.getLastname());
        userDto.setPhone(form.getPhone());
        userDto.setEmail(form.getEmail());
        userDto.setRoleId(2); //By default "user" not "admin
        userDto.setPassword(passwordEncoder.encode(form.getPassword()));
        return incidentFeignClient.createUser(userDto);
    }




}
