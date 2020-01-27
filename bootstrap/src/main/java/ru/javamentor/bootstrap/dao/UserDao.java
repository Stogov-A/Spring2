package ru.javamentor.bootstrap.dao;

import ru.javamentor.bootstrap.model.User;

import java.util.List;

public interface UserDao {
    List<User>findAllUsers();
    User findUserByID(long id);
    void addUser(User user);
    void deleteUser(User user);
    void editUser(User user);
    User findUserByName(String name);
}
