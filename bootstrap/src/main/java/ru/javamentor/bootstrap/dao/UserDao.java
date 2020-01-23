package ru.javamentor.bootstrap.dao;

import ru.javamentor.bootstrap.model.User;

import java.util.List;

public interface UserDao {
    List<User>findAllUsers();
    User findUserByID(long id);
    void saveUser(User user);
    void deleteUser(User user);
}
