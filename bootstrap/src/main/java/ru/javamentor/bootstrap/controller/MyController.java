package ru.javamentor.bootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "hello";
    }
}
