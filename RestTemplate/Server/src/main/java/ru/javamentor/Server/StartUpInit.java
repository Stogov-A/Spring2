package ru.javamentor.Server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javamentor.Server.model.User;
import ru.javamentor.Server.service.RoleService;
import ru.javamentor.Server.service.UserDetailsServiceImpl;

import javax.annotation.PostConstruct;

@Component
public class StartUpInit {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    RoleService roleService;

    @PostConstruct
    public void init() {
        if (roleService.getAllRoles().size() < 2) {
            roleService.addAdminRole();
            roleService.addUserRole();
        }
        if (userDetailsService.findAllUsers().size() == 0) {
            userDetailsService.addUser(new User("a", "a", 100, "a", "a",
                    roleService.getAllRoles()));
        }
    }
}
