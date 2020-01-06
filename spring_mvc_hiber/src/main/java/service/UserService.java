package service;

import model.Role;
import model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAllUsers();

    void addUser(User user);

    User getUserById(long id);

    void editUser(long id, String name, String lastName, int age, String email, String password, Set<Role>roles);

    boolean checkPasswordById(String password, long id);

    void deleteUserById(long id);

    User getUserByName(String name);
}
