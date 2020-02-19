package ru.javamentor.Server.service;

import ru.javamentor.Server.model.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUsers();

    public User findUserByID(long id);

    public void addUser(User user);

    public void deleteUser(User user);

    public void editUser(User user);

    public void deleteUserById(long id);
}
