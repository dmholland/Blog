package com.dmholland.demo.controllers;


import com.dmholland.demo.models.Post;
import com.dmholland.demo.services.NotificationService;
import com.dmholland.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostsController {
    @Autowired
    NotificationService notifyService;

    @Autowired
    private PostService postService;

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
