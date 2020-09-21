package com.dmholland.demo.controllers;


import com.dmholland.demo.models.User;
import com.dmholland.demo.services.NotificationService;
import com.dmholland.demo.services.UserServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


public class RegistrationController {
    @Autowired
    private UserServiceRepository repository;


    @GetMapping("/users/registration")
    public String registrationPage(WebRequest request, Model model){
   User user=repository.createUser(new User());
        model.addAttribute("user",user);
        return "users/registration";
    }

    @RequestMapping(value="users/registration",method = RequestMethod.POST)
    public ModelAndView register(@Valid User user, BindingResult validate){
        ModelAndView mv=new ModelAndView();
        if(repository.userCheck(user)){
            validate.rejectValue("userName", "error.user", "User exists");
            mv.setViewName("users/registration");
            return mv;
        }
        this.repository.createUser(user);
        mv.addObject("user",user);
        mv.addObject("successMessage", "User has been created");
        mv.addObject("user", new User());
        mv.setViewName("users/login");
        return mv;

    }

}
