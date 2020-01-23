package ru.javamentor.bootstrap.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javamentor.bootstrap.model.Role;
import ru.javamentor.bootstrap.repository.RoleRepository;

import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    RoleRepository roleRepository;
    @Override
    public Set<Role> getAllRoles() {
       return roleRepository.getAllRoles();
    }

    @Override
    public Set<Role> getUserRole() {
        return roleRepository.getUserRoleSet();
    }

    @Override
    public Set<Role> getAdminRole() {
        return roleRepository.getAdminRoleSet();
    }

    @Override
    public void addUserRole() {
        roleRepository.save(new Role("ROLE_USER"));
    }

    @Override
    public void addAdminRole() {
        roleRepository.save(new Role("ROLE_ADMIN"));
    }
}
