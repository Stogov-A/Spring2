package ru.javamentor.Server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javamentor.Server.dao.RoleDao;
import ru.javamentor.Server.model.Role;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Override
    public Set<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public Set<Role> getUserRole() {
        return roleDao.getUserRole();
    }

    @Override
    public Set<Role> getAdminRole() {
        return roleDao.getAdminRole();
    }

    @Override
    public void addUserRole() {
        roleDao.addUserRole();
    }

    @Override
    public void addAdminRole() {
        roleDao.addAdminRole();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    public Set<Role> getSomeRolesByNames(Set<Role> roles) {
        return roleDao.getSomeRolesBySet(roles);
    }
}
