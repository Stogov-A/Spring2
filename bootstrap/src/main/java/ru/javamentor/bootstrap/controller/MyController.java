package ru.javamentor.bootstrap.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.bootstrap.model.Role;
import ru.javamentor.bootstrap.model.User;
import ru.javamentor.bootstrap.service.RoleServiceImpl;
import ru.javamentor.bootstrap.service.UserDetailsServiceImpl;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/")
public class MyController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    RoleServiceImpl roleService;

    @GetMapping(value = "/hello")
    public String usersGet(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "hello";
    }

    @GetMapping(value = "/admin")
    public String adminGet(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAdmin = false;
        boolean isUser = false;
        for (Role role : user.getRoles()) {
            if (role.getName().equals("ROLE_ADMIN")) {
                isAdmin = true;
            }
            if (role.getName().equals("ROLE_USER")) {
                isUser = true;
            }
        }
        List<User> users = userDetailsService.findAllUsers();
        Set<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isUser", isUser);
        model.addAttribute("users", users);
        model.addAttribute("loginUser", user);
        return "admin";
    }


}
