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

    public String findAllUsers(){
        try {
            return objectMapper.writeValueAsString(restTemplate.getForObject("http://localhost:8080/admin", String.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String findAllRoles(){
        try {

            String s = restTemplate.getForObject("http://localhost:8080/getAllRoles", String.class);
            System.out.println(s);
            ObjectMapper mapper = new ObjectMapper();
            Set <Role> roles = mapper.reader()
                    .forType(new TypeReference<Set<Role>>() {})
                    .readValue(s);
            System.out.println(roles+"ASADSSADASD");

            return s;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
