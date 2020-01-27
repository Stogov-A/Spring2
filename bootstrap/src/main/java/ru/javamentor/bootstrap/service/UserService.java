package ru.javamentor.bootstrap.service;

import ru.javamentor.bootstrap.model.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUsers();

    public User findUserByID(long id);

    public void addUser(User user);

    public void deleteUser(User user);

    public void editUser(User user);
}
