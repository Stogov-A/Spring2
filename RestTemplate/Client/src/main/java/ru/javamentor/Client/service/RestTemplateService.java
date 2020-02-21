package ru.javamentor.Client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.javamentor.Client.model.Role;
import ru.javamentor.Client.model.RoleList;
import ru.javamentor.Client.model.User;
import ru.javamentor.Client.model.UserList;

import java.util.List;
import java.util.Set;

@Service
public class RestTemplateService {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;

    public String findAllUsers() {
        return restTemplate.getForObject("http://localhost:8080/admin", String.class);

    }

    public String findAllRoles() {
        return restTemplate.getForObject("http://localhost:8080/getAllRoles", String.class);
    }

    public String findUserById(long id) {
        return restTemplate.getForObject("http://localhost:8080/getUser/"+ id, String.class);
    }
}
