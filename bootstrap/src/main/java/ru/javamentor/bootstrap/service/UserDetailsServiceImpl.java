package ru.javamentor.bootstrap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.javamentor.bootstrap.dao.UserDao;
import ru.javamentor.bootstrap.model.User;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService,UserService {
    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = userDao.findUserByName(s);
        if (userDetails == null){
            throw new UsernameNotFoundException("Fuck");
        }
        return userDetails;
    }


    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public User findUserByID(long id) {
        return userDao.findUserByID(id);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public void editUser(User user) {
        userDao.editUser(user);
    }

    @Override
    public void deleteUserById(long id) {
        userDao.deleteUser(findUserByID(id));
    }
}
