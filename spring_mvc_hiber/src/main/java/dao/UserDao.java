package dao;

import model.Role;
import model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    List<User> getAllUsers();

    void addUser(User user);

    void deleteUserById(long id);

    User getUserById(long id);

    void editUser(long id, String name, String lastName, int age, String email, String password, Set<Role>roles);

    boolean checkPasswordById(String password, long id);

    User getUserByName(String username);
}
