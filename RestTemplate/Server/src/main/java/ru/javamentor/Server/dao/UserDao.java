package ru.javamentor.Server.dao;

import ru.javamentor.Server.model.User;

import java.util.List;

public interface UserDao {
    List<User>findAllUsers();
    User findUserByID(long id);
    void addUser(User user);
    void deleteUser(User user);
    void editUser(User user);
    User findUserByName(String name);
}
