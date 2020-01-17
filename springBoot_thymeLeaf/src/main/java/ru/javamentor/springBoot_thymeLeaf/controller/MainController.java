package ru.javamentor.springBoot_thymeLeaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springBoot_thymeLeaf.model.Role;
import ru.javamentor.springBoot_thymeLeaf.model.User;
import ru.javamentor.springBoot_thymeLeaf.repository.UserRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/")
public class MainController {
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/users")
    public String usersGet(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/deleteUser")
    public String deleteUserGet(Model model, @RequestParam long id) {
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        return "deleteUser";
    }

    @PostMapping(value = "/deleteUser")
    public String deleteUserPost(Model model, @ModelAttribute ("enterPass") String enterPass,
                                 @ModelAttribute("user")User user){
        if (!user.getPassword().equals(enterPass)){
            model.addAttribute("user", user);
            return "deleteUser";
        }else {
            userRepository.delete(user);
            return "redirect:/users";
        }
    }

    @GetMapping(value = "/editUser")
    public String editUserGet(Model model, @RequestParam long id) {
        User user = userRepository.findById(id).get();
        boolean isUser = false;
        boolean isAdmin = false;
        for (Role role : user.getRoles()) {
            if (role.getName().equals("ROLE_USER")) {
                isUser = true;
            } else if (role.getName().equals("ROLE_ADMIN")) {
                isAdmin = true;
            }
        }
        model.addAttribute("oldPass", user.getPassword());
        model.addAttribute("user", user);
        model.addAttribute("isUser", isUser);
        model.addAttribute("isAdmin", isAdmin);
        return "editUser";
    }

    @PostMapping(value = "/editUser")
    public String editUserPost(Model model, @ModelAttribute("user") User user, @ModelAttribute("isUser") String isUser,
                               @ModelAttribute("isAdmin") String isAdmin, @ModelAttribute("checkOldPass") String checkOldPass,
                               @ModelAttribute("oldPass") String oldPass, @ModelAttribute("id") long id) {
        if (user.getName().isEmpty() || user.getLastName().isEmpty() || user.getAge() < 0 || user.getEmail().isEmpty()
                || user.getPassword().isEmpty() || (isUser.isEmpty() && isAdmin.isEmpty())
                || !(oldPass.equals(checkOldPass))) {
            user.setId(id);
            model.addAttribute("user", user);
            model.addAttribute("isUser", !isUser.isEmpty());
            model.addAttribute("isAdmin", !isAdmin.isEmpty());
            return "editUser";
        } else {
            user.setId(id);
            Set<Role> roles = new HashSet<>();
            if (!isUser.isEmpty()) {
                roles.add(new Role("ROLE_USER"));
            }
            if (!isAdmin.isEmpty()) {
                roles.add(new Role("ROLE_ADMIN"));
            }
            user.setRoles(roles);
            userRepository.save(user);
            return "redirect:/users";
        }
    }

    @GetMapping(value = "/addUser")
    public String addUserGet(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("isUser", false);
        model.addAttribute("isAdmin", false);
        return "addUser";
    }

    @PostMapping(value = "/addUser")
    public String addUserPost(Model model, @ModelAttribute("user") User user, @ModelAttribute("isUser") String isUser,
                              @ModelAttribute("isAdmin") String isAdmin) {
        if (user.getName().isEmpty() || user.getLastName().isEmpty() || user.getAge() < 0 || user.getEmail().isEmpty()
                || user.getPassword().isEmpty() || (isUser.isEmpty() && isAdmin.isEmpty())) {
            model.addAttribute("user", user);
            model.addAttribute("isUser", !isUser.isEmpty());
            model.addAttribute("isAdmin", !isAdmin.isEmpty());
            return "addUser";
        } else {
            Set<Role> roles = new HashSet<>();
            if (!isUser.isEmpty()) {
                roles.add(new Role("ROLE_USER"));
            }
            if (!isAdmin.isEmpty()) {
                roles.add(new Role("ROLE_ADMIN"));
            }
            user.setRoles(roles);
            userRepository.save(user);
        }
        return "redirect:/users";
    }

    @GetMapping(value = "/hello")
    public String helloGet(Model model){
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        model.addAttribute("user", user);
        return "hello";
    }
}
