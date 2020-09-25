package com.dmholland.demo.controllers;

import com.dmholland.demo.services.NotificationService;
import com.dmholland.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private NotificationService notifyService;

    @Autowired
    private UserService userService;



    //Due to using the spring security, login form is not required in method
    @RequestMapping("/users/login")
    public String loginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth instanceof AnonymousAuthenticationToken)) {
            return "users/login";
        } else {
            notifyService.addErrorMessage("Credentials are not correct");
            return "redirect:/";
        }
    }

    }


