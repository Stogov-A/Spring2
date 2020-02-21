package ru.javamentor.Client.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.Client.service.RestTemplateService;
import ru.javamentor.Client.model.Role;
import ru.javamentor.Client.model.User;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/")
public class RESTController {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestTemplateService restTemplateService;

    @GetMapping(value = "/table")
    public String getUsers() {
        return restTemplateService.findAllUsers();
    }

    @GetMapping(value = "/getUser/{id}")
    public String getUser(@PathVariable long id) throws JsonProcessingException {
       String s =  restTemplateService.findUserById(id);
        System.out.println(s);
        return s;
    }

    @GetMapping(value = "/getAllRoles")
    public String getAllRoles() {
        return restTemplateService.findAllRoles();
    }
//
//    @DeleteMapping(value = "/deleteUser/{id}")
//    public String delete(@PathVariable long id) throws JsonProcessingException {
//        userDetailsService.deleteUserById(id);
//        return objectMapper.writeValueAsString("User delete");
//    }
//
//    @PutMapping(value = "/addUser")
//    public String addUser(@RequestBody User user) throws JsonProcessingException {
//        if (user.getName().isEmpty() || user.getLastName().isEmpty() || user.getAge() < 0 || user.getEmail().isEmpty()
//                || user.getPassword().isEmpty() || user.getRoles().size() == 0) {
//            return objectMapper.writeValueAsString("User not added! Invalid Arguments");
//        }
//        user.setRoles(roleService.getSomeRolesByNames(user.getRoles()));
//        userDetailsService.addUser(user);
//        return objectMapper.writeValueAsString("User successfully added");
//    }
//
//    @PostMapping(value = "/sendEditForm")
//    public String str(@RequestBody User user) throws JsonProcessingException {
//        if (user.getName().isEmpty() || user.getLastName().isEmpty() || user.getAge() < 0 || user.getEmail().isEmpty()
//                || user.getPassword().isEmpty() || user.getRoles().size() == 0) {
//            return objectMapper.writeValueAsString("Invalid argument");
//        }
//        user.setRoles(roleService.getSomeRolesByNames(user.getRoles()));
//        userDetailsService.editUser(user);
//        return objectMapper.writeValueAsString("");
//    }
}
