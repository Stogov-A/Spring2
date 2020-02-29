package ru.javamentor.Client.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.Client.service.RestTemplateService;
import ru.javamentor.Client.model.User;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping(value = "/")
public class RESTController {

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
    public void delete(@PathVariable long id) throws JsonProcessingException {
        restTemplateService.deleteUserById(id);
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
