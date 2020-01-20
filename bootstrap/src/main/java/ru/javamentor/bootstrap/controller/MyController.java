package ru.javamentor.bootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javamentor.bootstrap.model.Role;
import ru.javamentor.bootstrap.model.User;
import ru.javamentor.bootstrap.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class MyController {
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/hello")
    public String usersGet(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "hello";
    }

    @GetMapping(value = "/admin")
    public String adminGet(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAdmin = false;
        for (Role role : user.getRoles()) {
            if (role.getName().equals("ROLE_ADMIN")){
                isAdmin = true;
            }
        }
        List<User> users = userRepository.findAll();
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("users", users);
        model.addAttribute("loginUser", user);
        return "admin";
    }
}
