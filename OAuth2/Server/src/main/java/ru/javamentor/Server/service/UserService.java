package ru.javamentor.Server.service;

import ru.javamentor.Server.model.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUsers();

    public User findUserByID(long id);

    public int addUser(User user);

    public void deleteUser(User user);

    public int editUser(User user);

    public void deleteUserById(long id);
}
