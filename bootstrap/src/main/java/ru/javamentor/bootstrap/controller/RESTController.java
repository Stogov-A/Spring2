package ru.javamentor.bootstrap.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.bootstrap.model.Role;
import ru.javamentor.bootstrap.model.User;
import ru.javamentor.bootstrap.service.RoleServiceImpl;
import ru.javamentor.bootstrap.service.UserDetailsServiceImpl;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/")
public class RESTController {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    RoleServiceImpl roleService;
    @GetMapping(value = "/table")
    public @ResponseBody
    List<User> getUsers() {
        return userDetailsService.findAllUsers();
    }

    @GetMapping(value = "/getUser/{id}")
    public @ResponseBody
    String getUser(@PathVariable long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(userDetailsService.findUserByID(id));
    }

    @GetMapping(value = "/getAllRoles")
    public @ResponseBody
    Set<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    @ResponseBody
    public String delete(@PathVariable long id) throws JsonProcessingException {
        userDetailsService.deleteUserById(id);
        return objectMapper.writeValueAsString("User delete");
    }

    @PutMapping(value = "/addUser")
    @ResponseBody
    public String addUser(@RequestBody User user) throws JsonProcessingException {
        if (user.getName().isEmpty() || user.getLastName().isEmpty() || user.getAge() < 0 || user.getEmail().isEmpty()
                || user.getPassword().isEmpty() || user.getRoles().size() == 0){
            return objectMapper.writeValueAsString("User not added! Invalid Arguments");
        }
        user.setRoles(roleService.getSomeRolesByNames(user.getRoles()));
        userDetailsService.addUser(user);
        return objectMapper.writeValueAsString("User successfully added");
    }

    @PostMapping(value = "/sendEditForm")
    public @ResponseBody
    String str(@RequestBody User user) throws JsonProcessingException {
        if (user.getName().isEmpty() || user.getLastName().isEmpty() || user.getAge() < 0 || user.getEmail().isEmpty()
                || user.getPassword().isEmpty() || user.getRoles().size() == 0){
            return objectMapper.writeValueAsString("Invalid argument");
        }
        user.setRoles(roleService.getSomeRolesByNames(user.getRoles()));
        userDetailsService.editUser(user);
        return objectMapper.writeValueAsString("");
    }
}
