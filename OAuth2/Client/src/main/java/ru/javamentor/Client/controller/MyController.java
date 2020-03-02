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

    private OAuth2User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName()+"!!!!!!!!!!!!!!");
        System.out.println(auth.getPrincipal().toString()+"!!!!!!!!!!!!!");
        return ((OAuth2AuthenticationToken) auth).getPrincipal();
    }

    private User getUserFromPrincipal(OAuth2User principal) {
        System.out.println(principal.getName());
        System.out.println(principal.getAttributes());
        User user = restTemplateService.findUserByName((String) principal.getAttributes().get("given_name"));
        if (user == null){
            user = new User();
            user.setName((String) principal.getAttributes().get("given_name"));
            user.setLastName((String) principal.getAttributes().get("family_name"));
            user.setEmail((String) principal.getAttributes().get("email"));
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Set<Role> roles = new HashSet<>();
            for (GrantedAuthority authority : auth.getAuthorities()) {
                if (authority.getAuthority().contains("ROLE")) {
                    roles.add(new Role(authority.getAuthority()));
                }
            }
        }
        return user;
    }

    @GetMapping(value = "/hello")
    public String usersGet(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

//        System.out.println(auth.getName());
//        System.out.println(auth.getCredentials());
//        System.out.println(auth.getDetails());
//        System.out.println(auth.getPrincipal());
        User user = getUserFromPrincipal(getCurrentUser());
        model.addAttribute("user", user);
        return "hello";
    }

    @GetMapping(value = "/admin")
    public String adminGet(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        System.out.println(auth.getCredentials());
        System.out.println(auth.getDetails());
        System.out.println(auth.getPrincipal());
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
