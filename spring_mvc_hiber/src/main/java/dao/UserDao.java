package dao;

import model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void addUser(User user);

    void deleteUserById(long id);

    User getUserById(long id);

    void editUser(long id, String name, String lastName, int age, String email, String password);

    boolean checkPasswordById(String password, long id);
}
