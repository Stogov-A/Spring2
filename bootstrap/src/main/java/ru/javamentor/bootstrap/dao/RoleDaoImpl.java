package ru.javamentor.bootstrap.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.bootstrap.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Repository
public class RoleDaoImpl implements RoleDao {
    @Autowired
    EntityManager entityManager;

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(entityManager.createQuery("SELECT r FROM Role r").getResultList());
    }

    @Override
    public Set<Role> getUserRole() {
        Role role = (Role) entityManager.createQuery("select r from Role r where r.name = 'ROLE_USER'")
                .getResultList().get(0);
        Set<Role> userRole = new HashSet<>();
        userRole.add(role);
        return userRole;
    }

    @Override
    public Set<Role> getAdminRole() {
        Role role = (Role) entityManager.createQuery("select r from Role r where r.name = 'ROLE_ADMIN'")
                .getResultList().get(0);
        Set<Role> adminRole = new HashSet<>();
        adminRole.add(role);
        return adminRole;
    }

    @Override
    public void addUserRole() {
        entityManager.persist(new Role("ROLE_USER"));
    }

    @Override
    public void addAdminRole() {
        entityManager.persist(new Role("ROLE_ADMIN"));
    }

    @Override
    public Role getRoleByName(String name) {
        Query query = entityManager.createQuery("select r from Role r where r.name = :name");
        query.setParameter("name", name);
        return (Role) query.getResultList().get(0);
    }

    @Override
    public Set<Role> getSomeRolesBySet(Set<Role> roles) {
        Object[] arrRoles =  roles.toArray();
        StringBuilder strQuery = new StringBuilder("select r from Role r where r.name =:");
        for (int i = 0; i < arrRoles.length; i++) {
            if (i > 0) {
                strQuery.append(" OR r.name =:").append("name").append(i);
            } else {
                strQuery.append("name").append(i);
            }
        }
        Query query = entityManager.createQuery(strQuery.toString());
        for (int i = 0; i < arrRoles.length; i++) {
            query.setParameter("name" + i, arrRoles[i].toString());
        }
        Set<Role> someRoles = new HashSet<>(query.getResultList());
        return someRoles;
    }
}
