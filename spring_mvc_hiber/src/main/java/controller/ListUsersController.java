package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class ListUsersController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String printUsers(ModelMap modelMap) {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user.getId() + user.getName());
        }
        modelMap.addAttribute("users", users);
        return "listUsers";
    }
}
