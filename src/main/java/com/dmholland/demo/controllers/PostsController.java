package com.dmholland.demo.controllers;


import com.dmholland.demo.models.Post;
import com.dmholland.demo.services.NotificationService;
import com.dmholland.demo.services.PostService;
import com.dmholland.demo.services.PostServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostsController {
    @Autowired
    NotificationService notifyService;

    @Autowired
    private PostServiceInterface postService;

    @RequestMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id, Model model){
        Post post = this.postService.findById(id);
        if( post == null ){
            notifyService.addErrorMessage("Cannot find post #" + id);
            return "redirect:/";
        }
        model.addAttribute("post", post);
        // To have something like src/main/resources/templates/<CONTROLLER-NAME>/<Mapping-Name-view>
        return "posts/view";
    }
}

@RequestMapping("/posts/create")

@RequestMapping(value = "/posts/create", method = RequestMethod.POST)

@RequestMapping("/posts/delete/{id}",method = RequestMethod.DELETE)

@RequestMapping("/posts/edit/{id}")

@RequestMapping(value = "/posts/edit", method = RequestMethod.POST)

@RequestMapping("/posts")