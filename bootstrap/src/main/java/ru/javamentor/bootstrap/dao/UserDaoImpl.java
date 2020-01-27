package ru.javamentor.bootstrap.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.bootstrap.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<User> findAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public User findUserByID(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findUserByName(String name) {
        Query query = entityManager.createQuery("SELECT u FROM User u where u.name = :name");
        query.setParameter("name", name);
        List<User> users = query.getResultList();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}
