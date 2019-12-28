package service;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void editUser(long id, String name, String lastName, int age, String email, String password) {
        userDao.editUser(id, name, lastName, age, email, lastName);
    }

    @Override
    public boolean checkPasswordById(String password, long id) {
        return userDao.checkPasswordById(password, id);
    }

    @Override
    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }


}
