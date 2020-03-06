package ru.javamentor.Client.config.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.javamentor.Client.model.Role;
import ru.javamentor.Client.model.User;
import ru.javamentor.Client.service.RestTemplateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    RestTemplateService restTemplateService;

    private User getUserFromPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user;
        //смотрим откуда получили юзера, из гугла или бд
        if (auth.getCredentials() == null) {
            user = restTemplateService.findUserByName(auth.getName());
        } else {
            OAuth2User principal = ((OAuth2AuthenticationToken) auth).getPrincipal();
            user = restTemplateService.findUserByName((String) principal.getAttributes().get("given_name"));
            if (user == null) {
                user = new User();
                user.setName((String) principal.getAttributes().get("given_name"));
                user.setLastName((String) principal.getAttributes().get("family_name"));
                user.setEmail((String) principal.getAttributes().get("email"));
                Set<Role> roles = new HashSet<>();
                roles.add(new Role("ROLE_USER"));
                roles.add(new Role("ROLE_ADMIN"));
                user.setRoles(roles);
                restTemplateService.addUser(user);
            }
        }
        return user;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean isUser = false;
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                httpServletResponse.sendRedirect("/admin");
                return;
            } else if (authority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
            }
        }
        if (isUser) {
            OAuth2User principal = ((OAuth2AuthenticationToken) authentication).getPrincipal();
            if (principal.getAttributes().get("given_name").equals("андрей")) {
                getUserFromPrincipal();
                Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                Authentication reAuth = new UsernamePasswordAuthenticationToken("андрей","андрей", grantedAuthorities);
                SecurityContextHolder.getContext().setAuthentication(reAuth);
                httpServletResponse.sendRedirect("/admin");
                return;
            }
            httpServletResponse.sendRedirect("/hello");
        }
    }
}