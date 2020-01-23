package ru.javamentor.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javamentor.bootstrap.dao.RoleDao;
import ru.javamentor.bootstrap.dao.UserDao;
import ru.javamentor.bootstrap.model.User;

@SpringBootApplication
public class BootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }

}
