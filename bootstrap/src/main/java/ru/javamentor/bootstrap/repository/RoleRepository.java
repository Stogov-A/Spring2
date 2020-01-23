package ru.javamentor.bootstrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.javamentor.bootstrap.model.Role;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select r from Role r")
    Set<Role>getAllRoles();

    @Query("select r from Role r where r.name = 'ROLE_USER'")
    Set<Role>getUserRoleSet();

    @Query("select r from Role r where r.name = 'ROLE_ADMIN'")
    Set<Role>getAdminRoleSet();
}
