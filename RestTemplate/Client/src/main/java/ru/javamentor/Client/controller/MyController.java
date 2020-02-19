package ru.javamentor.Client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javamentor.Client.model.Role;
import ru.javamentor.Client.model.User;
import ru.javamentor.Client.service.RestTemplateService;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class MyController {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    RestTemplateService restTemplateService;

    @GetMapping(value = "/hello")
    public String usersGet(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "hello";
    }

    @GetMapping(value = "/admin")
    public String adminGet(Model model) {
        UserDetails details = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAdmin = false;
        boolean isUser = false;
        for (GrantedAuthority authority : details.getAuthorities()) {
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
        //model.addAttribute("loginUser", user);
        return "admin";
    }


}
