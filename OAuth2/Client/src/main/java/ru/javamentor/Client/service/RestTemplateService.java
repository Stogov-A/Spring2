package ru.javamentor.Client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.javamentor.Client.model.User;

@Service
public class RestTemplateService implements UserDetailsService {

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

    public Integer addUser(User user) {
        HttpEntity<User> requestUpdate = new HttpEntity<>(user);
        ResponseEntity<Integer> responseEntity = restTemplate.exchange("http://localhost:8080/addUser",
                HttpMethod.PUT, requestUpdate, Integer.class);
        return responseEntity.getBody();
    }

    public int editUser(User user) {
        return restTemplate.postForObject("http://localhost:8080/sendEditForm", user, Integer.class);
    }

    public User findUserByName(String name) {
        return restTemplate.getForObject("http://localhost:8080/getUserByName/" + name, User.class);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User userDetails = restTemplate.getForObject("http://localhost:8080/getUserByName/" + s, User.class);
        return userDetails;
    }
}
