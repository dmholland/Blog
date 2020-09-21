package com.dmholland.demo.controllers;


import com.dmholland.demo.models.Post;
import com.dmholland.demo.services.PostServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private PostServiceInterface postService;

    @RequestMapping(value= { "/", "/home" } )
    public String index(Model model) {
        List<Post> latest5Posts = postService.findLatest5();
        model.addAttribute("latest5Posts",latest5Posts);

        List<Post> latest3Posts = latest5Posts.stream()
                .limit(3)
                .collect(Collectors.toList());
        model.addAttribute("latest3Posts",latest3Posts);
        return "index";
    }
}