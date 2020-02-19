package ru.javamentor.Client.model;

import java.util.List;

public class UserList {
    private List<User> userList;

    public UserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
