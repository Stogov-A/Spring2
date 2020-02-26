package ru.javamentor.Client.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.Client.service.RestTemplateService;
import ru.javamentor.Client.model.User;

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
        return restTemplateService.findUserById(id);
    }

    @GetMapping(value = "/getAllRoles")
    public String getAllRoles() {
        return restTemplateService.findAllRoles();
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public String delete(@PathVariable long id) throws JsonProcessingException {
        restTemplateService.deleteUserById(id);
        return objectMapper.writeValueAsString("User delete");
    }

    @PutMapping(value = "/addUser")
    public int addUser(@RequestBody User user) throws JsonProcessingException {
        return restTemplateService.addUser(user);
    }

    @PostMapping(value = "/sendEditForm")
    public int str(@RequestBody User user) throws JsonProcessingException {
        return restTemplateService.editUser(user);
    }
}
