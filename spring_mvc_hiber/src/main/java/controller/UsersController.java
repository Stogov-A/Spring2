package controller;

import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UsersController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String printUsers(ModelMap modelMap) {
        List<User> users = userService.getAllUsers();
        modelMap.addAttribute("users", users);
        return "listUsers";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String printEditUserGet(ModelMap modelMap, @RequestParam long id) {
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        return "editUser";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String printEditUserPost(ModelMap modelMap, @RequestParam long id, @RequestParam String name,
                                    @RequestParam String lastName, @RequestParam int age, @RequestParam String email,
                                    @RequestParam String password, @RequestParam(required = false) String[] userRoles) {
        if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || userRoles == null) {
            modelMap.addAttribute("user", userService.getUserById(id));
            modelMap.addAttribute("message", "Wrong argument");
            return "editUser";
        } else {
            Set<Role> roles = new HashSet<>();
            for (String s : userRoles) {
                roles.add(new Role(s));
            }
            userService.editUser(id, name, lastName, age, email, password, roles);
            List<User> users = userService.getAllUsers();
            modelMap.addAttribute("users", users);
            return "listUsers";
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String printDeleteUserGet(ModelMap modelMap, @RequestParam long id) {
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        return "deleteUser";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String printDeleteUserPost(ModelMap modelMap, @RequestParam long id, @RequestParam String password) {
        if (userService.checkPasswordById(password, id)) {
            userService.deleteUserById(id);
            List<User> users = userService.getAllUsers();
            modelMap.addAttribute("users", users);
            return "listUsers";
        } else {
            User user = userService.getUserById(id);
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("id", id);
            return "deleteUser";
        }
    }

    @RequestMapping(value = "addUser", method = RequestMethod.GET)
    public String printAddUserGet(ModelMap modelMap) {
        return "addUser";
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public String printAddUserPost(ModelMap modelMap,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) String lastName,
                                   @RequestParam int age,
                                   @RequestParam(required = false) String email,
                                   @RequestParam(required = false) String password,
                                   @RequestParam(required = false) String[] userRoles) {
        if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || userRoles == null) {
            modelMap.addAttribute("message", "Wrong argument");
            return "addUser";
        } else {
            Set<Role> roles = new HashSet<>();
            for (String s : userRoles) {
                roles.add(new Role(s));
            }
            userService.addUser(new User(name, lastName, age, email, password, roles));
            List<User> users = userService.getAllUsers();
            modelMap.addAttribute("users", users);
            return "listUsers";
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(ModelMap modelMap) {
        return "login";
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add(user.getName());
        messages.add(user.getLastName());
        messages.add(String.valueOf(user.getAge()));
        messages.add(user.getEmail());
        messages.add(user.getPassword());
        for (Role role : user.getRoles()) {
            messages.add(role.getName());
        }
        model.addAttribute("messages", messages);
        return "hello";
    }
}
