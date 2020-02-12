package ru.javamentor.bootstrap.service;

import ru.javamentor.bootstrap.model.Role;

import java.util.Set;

public interface RoleService {
    public Set<Role> getAllRoles();

    public Set<Role> getUserRole();

    public Set<Role> getAdminRole();

    public void addUserRole();

    public void addAdminRole();

    public Role getRoleByName(String name);

    public Set<Role> getSomeRolesByNames(Set<Role>roles);
}
