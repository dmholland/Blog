package com.dmholland.demo.controllers;


import com.dmholland.demo.models.User;
import com.dmholland.demo.services.NotificationService;
import com.dmholland.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;


    @RequestMapping("/users/registration")
    public ModelAndView registration(){
        ModelAndView mv= new ModelAndView();
        User user=new User();
  // User user=repository.createUser(new User());
        //I had get mapping and used the repository
        mv.addObject(user);
        mv.setViewName("users/registration");
        return mv;
    }

    @RequestMapping(value="users/registration",method = RequestMethod.POST)
    public ModelAndView registration(@Valid User user, BindingResult validate){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("users/registration");
        if(this.userService.userCheck(user)){//user exists
            validate.rejectValue("username", "error.user", "User exists");
            return mv;
        }
     if(!validate.hasErrors()) {
         this.userService.createUser(user);
         mv.addObject("successMessage", "User has been created");
         mv.addObject("user", new User());
     }
        return mv;

    }

}
