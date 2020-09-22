package com.dmholland.demo.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

@RequestMapping("/error")
public ModelAndView handleError(HttpServletResponse response){
ModelAndView modelAndView = new ModelAndView();

if(response.getStatus() == HttpStatus.NOT_FOUND.value()){
    modelAndView.setViewName("Error/404");
}
    if(response.getStatus() == HttpStatus.FORBIDDEN.value()){
        modelAndView.setViewName("Error/403");
    }
    if(response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()){
        modelAndView.setViewName("Error/500");
    }
    else {
        modelAndView.setViewName("Error/other");
    }

    return  modelAndView;
}


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
