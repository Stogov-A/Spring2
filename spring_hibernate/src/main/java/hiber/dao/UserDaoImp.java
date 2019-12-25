package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCarNameAndCarSeries(String name, int series) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User as user" +
                " where user.car.name = :name and user.car.series = :series");
        query.setParameter("name", name);
        query.setParameter("series", series);
        List<User> list = query.getResultList();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
