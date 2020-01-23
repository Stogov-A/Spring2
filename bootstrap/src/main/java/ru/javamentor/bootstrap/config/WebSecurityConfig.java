package ru.javamentor.bootstrap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.javamentor.bootstrap.config.handler.LoginSuccessHandler;
import ru.javamentor.bootstrap.dao.RoleDao;
import ru.javamentor.bootstrap.dao.UserDao;
import ru.javamentor.bootstrap.model.User;
import ru.javamentor.bootstrap.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    RoleDao roleDao;
    @Autowired
    UserDao userDao;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if (roleDao.getAllRoles().size() < 2) {
            roleDao.addAdminRole();
            roleDao.addUserRole();
        }
        if (userDao.findAllUsers().size() == 0) {
            userDao.saveUser(new User("a", "a", 10, "a", "a", roleDao.getAllRoles()));
        }
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .antMatchers("/hello").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(new LoginSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
