package ru.javamentor.bootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javamentor.bootstrap.dao.RoleDao;
import ru.javamentor.bootstrap.dao.UserDao;
import ru.javamentor.bootstrap.model.Role;
import ru.javamentor.bootstrap.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/")
public class MyController {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

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
        for (Role role : user.getRoles()) {
            if (role.getName().equals("ROLE_ADMIN")) {
                isAdmin = true;
            }
        }
        List<User> users = userDao.findAllUsers();
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("users", users);
        model.addAttribute("loginUser", user);
        return "admin";
    }

    @PostMapping(value = "/edit")
    public String editPost(Model model, @ModelAttribute("id") int id, @ModelAttribute("name") String name,
                           @ModelAttribute("lastName") String lastName, @ModelAttribute("age") int age,
                           @ModelAttribute("email") String email, @ModelAttribute("password") String password,
                           @ModelAttribute("isUser") String isUser, @ModelAttribute("isAdmin") String isAdmin) {
        if (name.isEmpty() || lastName.isEmpty() || age < 0 || email.isEmpty() || password.isEmpty()
                || (isUser.isEmpty() && isAdmin.isEmpty())) {
            return "redirect:/admin";
        } else {
            User user = userDao.findUserByID(id);
            Set<Role> roles = new HashSet<>();
            if (!isUser.isEmpty()) {
                roles = roleDao.getUserRole();
            }
            if (!isAdmin.isEmpty()) {
                roles = roleDao.getAllRoles();
            }
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            user.setEmail(email);
            user.setPassword(password);
            user.setRoles(roles);
            userDao.editUser(user);
        }

        return "redirect:/admin";
    }

    @PostMapping(value = "/add")
    public String addPost(Model model, @ModelAttribute("name") String name,
                          @ModelAttribute("lastName") String lastName, @ModelAttribute("age") int age,
                          @ModelAttribute("email") String email, @ModelAttribute("password") String password,
                          @ModelAttribute("isUser") String isUser, @ModelAttribute("isAdmin") String isAdmin) {
        if (name.isEmpty() || lastName.isEmpty() || age < 0 || email.isEmpty() || password.isEmpty()
                || (isUser.isEmpty() && isAdmin.isEmpty())) {
            return "redirect:/admin";
        } else {
            Set<Role> roles = new HashSet<>();
            if (!isUser.isEmpty()) {
                roles = roleDao.getUserRole();
            }
            if (!isAdmin.isEmpty()) {
                roles = roleDao.getAllRoles();
            }
           userDao.addUser(new User(name, lastName, age, email, password, roles));
        }
        return "redirect:/admin";
    }

    @PostMapping(value = "/delete")
    public String deletePost(Model model, @ModelAttribute("id") int id) {
        userDao.deleteUser(userDao.findUserByID(id));
        return "redirect:/admin";
    }
}
