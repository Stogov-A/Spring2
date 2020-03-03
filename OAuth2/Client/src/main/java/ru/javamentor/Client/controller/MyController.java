package ru.javamentor.Client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javamentor.Client.model.Role;
import ru.javamentor.Client.model.User;
import ru.javamentor.Client.service.RestTemplateService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/")
public class MyController {
    @Autowired
    RestTemplateService restTemplateService;

    private User getUserFromPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user;
        //смотрим откуда получили юзера, из гугла или бд
        if (auth.getCredentials() == null) {
            user = restTemplateService.findUserByName(auth.getName());
        }else {
            OAuth2User principal = ((OAuth2AuthenticationToken) auth).getPrincipal();
            user = restTemplateService.findUserByName((String) principal.getAttributes().get("given_name"));
            if (user == null) {
                user = new User();
                user.setName((String) principal.getAttributes().get("given_name"));
                user.setLastName((String) principal.getAttributes().get("family_name"));
                user.setEmail((String) principal.getAttributes().get("email"));
                Set<Role> roles = new HashSet<>();
                for (GrantedAuthority authority : auth.getAuthorities()) {
                    if (authority.getAuthority().contains("ROLE")) {
                        roles.add(new Role(authority.getAuthority()));
                    }
                }
                user.setRoles(roles);
                restTemplateService.addUser(user);
            }
        }
        return user;
    }

    @GetMapping(value = "/hello")
    public String usersGet(Model model) {
        User user = getUserFromPrincipal();
        model.addAttribute("user", user);
        return "hello";
    }

    @GetMapping(value = "/admin")
    public String adminGet(Model model) {
        UserDetails user = getUserFromPrincipal();
        boolean isAdmin = false;
        boolean isUser = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
            }
            if (authority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
            }
        }
        String users = restTemplateService.findAllUsers();
        String roles = restTemplateService.findAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isUser", isUser);
        model.addAttribute("users", users);
        return "admin";
    }
}
