package com.dmholland.demo.controllers;

import com.dmholland.demo.forms.LoginForm;
import com.dmholland.demo.models.User;
import com.dmholland.demo.services.NotificationService;
import com.dmholland.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private NotificationService notifyService;

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
}

