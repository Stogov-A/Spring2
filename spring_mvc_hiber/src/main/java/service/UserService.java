package service;

import model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void addUser(User user);

    User getUserById(long id);

    void editUser(long id, String name, String lastName, int age, String email, String password);

    boolean checkPasswordById(String password, long id);

    void deleteUserById(long id);
}
