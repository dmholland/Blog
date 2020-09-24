package com.dmholland.demo.controllers;

import com.dmholland.demo.forms.LoginForm;
import com.dmholland.demo.models.User;
import com.dmholland.demo.services.NotificationService;
import com.dmholland.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private NotificationService notifyService;

    @Autowired
    private UserService userService;

    @RequestMapping("/users/login")
    public String login(LoginForm loginForm) {
        return "users/login";
    }

    //Due to using the spring security, login form is not required in method
    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public String loginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth instanceof AnonymousAuthenticationToken)) {
            return "users/backoffice";
        } else {
            notifyService.addErrorMessage("Credentials are not correct");
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/users/backoffice")
    public String backOfficePage(Model model, @PageableDefault(sort = {"username"}, value = 6) Pageable pageable){

        Page<User> users = this.userService.findAll(pageable);
        return "users/backoffice";
        }
    }


