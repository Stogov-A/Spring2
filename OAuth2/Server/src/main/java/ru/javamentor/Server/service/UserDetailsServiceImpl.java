package ru.javamentor.Server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.javamentor.Server.dao.RoleDao;
import ru.javamentor.Server.dao.UserDao;
import ru.javamentor.Server.model.User;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService,UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

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
    public int addUser(User user) {
        if (user.getName().isEmpty() || user.getLastName().isEmpty() || user.getAge() < 0 || user.getEmail().isEmpty()
                || user.getPassword().isEmpty() || user.getRoles().size() == 0) {
            return 400;
        }
        user.setRoles(roleDao.getSomeRolesBySet(user.getRoles()));
        userDao.addUser(user);
        return 200;
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public int editUser(User user) {
        if (user.getName().isEmpty() || user.getLastName().isEmpty() || user.getAge() < 0 || user.getEmail().isEmpty()
                || user.getPassword().isEmpty() || user.getRoles().size() == 0) {
            return 400;
        }
        user.setRoles(roleDao.getSomeRolesBySet(user.getRoles()));
        userDao.editUser(user);
        return 200;
    }

    @Override
    public void deleteUserById(long id) {
        userDao.deleteUser(findUserByID(id));
    }
}
