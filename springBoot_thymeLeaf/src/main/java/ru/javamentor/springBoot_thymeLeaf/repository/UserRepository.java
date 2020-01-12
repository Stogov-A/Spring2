package ru.javamentor.springBoot_thymeLeaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.javamentor.springBoot_thymeLeaf.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.name = ?1")
    User findUserByName(String name);
}
