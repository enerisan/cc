package enerisan.incident.controller;

import enerisan.incident.dto.UserDto;
import enerisan.incident.model.Role;
import enerisan.incident.model.User;
import enerisan.incident.repository.RoleRepository;
import enerisan.incident.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/test")
    public String test() {
        return "OK";
    }
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user")
    public Optional<User> getUserById(@RequestParam Integer id){
        return userRepository.findById(id);
    }

    @GetMapping("/findUserByUsername")
    public Optional<User> findUserByEmail(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }

    @PostMapping("/user")
    public User addUser(@Valid @RequestBody UserDto userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        Role role = roleRepository.findById(userDto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + userDto.getRoleId()));
        user.setRole(role);
        return userRepository.save(user);
    }
    @PutMapping("/user")
    public Optional <User> updateUser(@RequestBody User user, @RequestParam Integer id){
        return userRepository.findById(id).map(oldUser ->{
            oldUser.setFirstname(user.getFirstname());
            oldUser.setFirstname(user.getLastname());
            oldUser.setPhone(user.getPhone());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            oldUser.setRole(user.getRole());
            return userRepository.save(oldUser);
        });
    }


    @DeleteMapping("/user")
    public void deleteUserById(@RequestParam Integer id){
        userRepository.deleteById(id);
    }
}
