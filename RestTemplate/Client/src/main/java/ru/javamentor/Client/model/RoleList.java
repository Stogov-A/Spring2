package ru.javamentor.Client.model;

import java.util.List;

public class RoleList {
    private List<Role>roleList;

    public RoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
