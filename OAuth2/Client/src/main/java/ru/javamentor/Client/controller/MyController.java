package ru.javamentor.Client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javamentor.Client.model.User;
import ru.javamentor.Client.service.RestTemplateService;

import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class MyController {
    @Autowired
    RestTemplateService restTemplateService;

    public OAuth2User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ((OAuth2AuthenticationToken)auth).getPrincipal();
    }

    @GetMapping(value = "/hello")
    public String usersGet(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OAuth2User principal = ((OAuth2AuthenticationToken)auth).getPrincipal();
        //106236926171317099731 sub its id
        //106236926171317099731
        //9223372036854775807
//        for (Map.Entry<String, Object> stringObjectEntry : user.getAttributes().entrySet()) {
//            System.out.println(stringObjectEntry.getKey()+"   -   "+stringObjectEntry.getValue());
//        }
        System.out.println(Long.MAX_VALUE);
        System.out.println(restTemplateService.findUserById(Long.parseLong(principal.getAttributes().get("sub").toString())));
        model.addAttribute("user", principal);
        return "hello";
    }

    @GetMapping(value = "/admin")
    public String adminGet(Model model) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAdmin = false;
        boolean isUser = false;
        for (GrantedAuthority authority : details.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
            }
            if (authority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
            }
        }
        String users = restTemplateService.findAllUsers();
        String roles = restTemplateService.findAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isUser", isUser);
        model.addAttribute("users", users);
        return "admin";
    }
}
