package enerisan.webapp.service;

import enerisan.webapp.model.User;
import enerisan.webapp.service.client.IncidentFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IncidentFeignClient incidentFeignClient;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = incidentFeignClient.findUserByUsername(email);

        if(user == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + email);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
//                .roles(user.getRole())
                .build();

    }
}
