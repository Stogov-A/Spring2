package dao;

import model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        TypedQuery<User> typedQuery = sessionFactory.getCurrentSession().createQuery("FROM User");
        return typedQuery.getResultList();
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public void editUser(long id, String name, String lastName, int age, String email, String password) {

    }
}
