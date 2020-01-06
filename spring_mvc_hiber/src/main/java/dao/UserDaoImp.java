package dao;

import model.Role;
import model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

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
    public void deleteUserById(long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE FROM User WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public User getUserById(long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE id = :id" );
        query.setParameter("id", id);
        List<User>list = query.getResultList();
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void editUser(long id, String name, String lastName, int age, String email, String password, Set<Role>roles) {
        Query query = sessionFactory.getCurrentSession().createQuery("UPDATE User SET name = :name, " +
                "lastName = :lastName, age = :age, email = :email, password = :password, roles = :roles WHERE id = :id");
        query.setParameter("name", name);
        query.setParameter("lastName", lastName);
        query.setParameter("age", age);
        query.setParameter("email", email);
        query.setParameter("password", password);
        query.setParameter("roles", roles);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public boolean checkPasswordById(String password, long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE id = :id " +
                "AND password = :password");
        query.setParameter("password", password);
        query.setParameter("id", id);
        List<User>users = query.getResultList();
        if (users.size() > 0){
            return true;
        }
        return false;
    }

    @Override
    public User getUserByName(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE name = :name");
        query.setParameter("name", username);
        List<User>users = query.getResultList();
        if (users.size() > 0){
            return users.get(0);
        }
        return null;
    }
}
