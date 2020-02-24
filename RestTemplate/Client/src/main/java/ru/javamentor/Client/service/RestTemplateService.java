package ru.javamentor.Client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.javamentor.Client.model.User;

@Service
public class RestTemplateService {

    @Autowired
    RestTemplate restTemplate;

    public String findAllUsers() {
        return restTemplate.getForObject("http://localhost:8080/admin", String.class);

    }

    public String findAllRoles() {
        return restTemplate.getForObject("http://localhost:8080/getAllRoles", String.class);
    }

    public String findUserById(long id) {
        return restTemplate.getForObject("http://localhost:8080/getUser/" + id, String.class);
    }

    public void deleteUserById(long id) {
        restTemplate.delete("http://localhost:8080/deleteUser/" + id);
    }

    public void addUser(User user) {
        restTemplate.put("http://localhost:8080/addUser", user, String.class);
    }

    public void editUser(User user) {
        restTemplate.postForEntity("http://localhost:8080/sendEditForm", user, String.class);
    }
}
