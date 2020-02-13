package ru.javamentor.bootstrap.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.bootstrap.model.Role;
import ru.javamentor.bootstrap.model.User;
import ru.javamentor.bootstrap.service.RoleServiceImpl;
import ru.javamentor.bootstrap.service.UserDetailsServiceImpl;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/")
public class RESTController {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    RoleServiceImpl roleService;

    @GetMapping(value = "/table")
    public List<User> getUsers() {
        return userDetailsService.findAllUsers();
    }

    @GetMapping(value = "/getUser/{id}")
    public String getUser(@PathVariable long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(userDetailsService.findUserByID(id));
    }

    @GetMapping(value = "/getAllRoles")
    public Set<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public String delete(@PathVariable long id) throws JsonProcessingException {
        userDetailsService.deleteUserById(id);
        return objectMapper.writeValueAsString("User delete");
    }

    @PutMapping(value = "/addUser")
    public String addUser(@RequestBody User user) throws JsonProcessingException {
        if (user.getName().isEmpty() || user.getLastName().isEmpty() || user.getAge() < 0 || user.getEmail().isEmpty()
                || user.getPassword().isEmpty() || user.getRoles().size() == 0) {
            return objectMapper.writeValueAsString("User not added! Invalid Arguments");
        }
        user.setRoles(roleService.getSomeRolesByNames(user.getRoles()));
        userDetailsService.addUser(user);
        return objectMapper.writeValueAsString("User successfully added");
    }

    @PostMapping(value = "/sendEditForm")
    public String str(@RequestBody User user) throws JsonProcessingException {
        if (user.getName().isEmpty() || user.getLastName().isEmpty() || user.getAge() < 0 || user.getEmail().isEmpty()
                || user.getPassword().isEmpty() || user.getRoles().size() == 0) {
            return objectMapper.writeValueAsString("Invalid argument");
        }
        user.setRoles(roleService.getSomeRolesByNames(user.getRoles()));
        userDetailsService.editUser(user);
        return objectMapper.writeValueAsString("");
    }
}
