package ru.javamentor.bootstrap.dao;

import ru.javamentor.bootstrap.model.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> getAllRoles();
    Set<Role>getUserRole();
    Set<Role>getAdminRole();
    void addUserRole();
    void addAdminRole();
    Role getRoleByName(String name);
    Set<Role> getSomeRolesBySet(Set<Role>roles);
}
