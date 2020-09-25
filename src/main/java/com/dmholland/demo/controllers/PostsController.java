package com.dmholland.demo.controllers;


import com.dmholland.demo.models.Post;
import com.dmholland.demo.models.User;
import com.dmholland.demo.services.NotificationService;
import com.dmholland.demo.services.PostService;
import com.dmholland.demo.services.PostServiceInterface;
import com.dmholland.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PostsController {
    @Autowired
    NotificationService notifyService;

    @Autowired
    private PostServiceInterface postService;

    @Autowired
    private UserService userService;

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

@RequestMapping("/posts/create")

public ModelAndView createPosts() {
        ModelAndView mv=new ModelAndView();
        Post post = new Post();
        mv.addObject(post);
        mv.setViewName("posts/create");
        return mv;
}

@RequestMapping(value = "posts/create", method = RequestMethod.POST)
public ModelAndView createPosts(@Valid Post post, BindingResult bindingResult){
        ModelAndView mv=new ModelAndView();
    Authentication verify = SecurityContextHolder.getContext().getAuthentication();

        //So the errors will reject and show reasoning
    if( post.getTitle().isEmpty() ){
        bindingResult.rejectValue("title", "error.post", "Title cannot be empty");
    }
    if( post.getBody().isEmpty() ){
        bindingResult.rejectValue("body", "error.post", "Content cannot be empty");
    }
    User user =this.userService.findByUserName(verify.getName());//gets name of current user
    if(null == user){
        bindingResult.rejectValue("author","error.author","Can not create post, your User Name is not Verified");
    }
    if( !bindingResult.hasErrors() ){
        post.setAuthor(user);
        this.postService.create(post);
        mv.addObject("successMessage","Post has been created");
        mv.addObject("post",new Post());
    }
        return mv;
}



@RequestMapping("/posts/delete/{id}")
public String delete(@PathVariable("id")Long id){
    Post post = this.postService.findById(id);
    if(null==post){
        notifyService.addErrorMessage("Can not find post # "+id);
    }else{
        this.postService.deleteById(id);
    }
    return "redirect:/posts/";
}

@RequestMapping("/posts/edit/{id}")
public String edit(@PathVariable("id") Long id, Model model){
    Post post = this.postService.findById(id);
    if( null==post ){
        notifyService.addErrorMessage("Cannot find post #" + id);
        return "redirect:/posts/";
    }
    model.addAttribute("post", post);
    return "posts/edit";
}


@RequestMapping(value = "/posts/edit", method = RequestMethod.POST)
public ModelAndView edit(@Valid Post post, BindingResult bindingResult){
    ModelAndView mv = new ModelAndView();
    Authentication verify = SecurityContextHolder.getContext().getAuthentication();
    mv.setViewName("posts/edit");
    //Since this was just like the creating of a post I copied and altered from above
    if( post.getTitle().isEmpty() ){
        bindingResult.rejectValue("title", "error.post", "Title cannot be empty");
    }
    if( post.getBody().isEmpty() ){
        bindingResult.rejectValue("body", "error.post", "Content cannot be empty");
    }
    User user =this.userService.findByUserName(verify.getName());//gets name of current user
    if(null == user){
        bindingResult.rejectValue("author","error.author","Can not edit post, your User Name is not Verified");
    }
    if( !bindingResult.hasErrors() ){
        post.setAuthor(user);
        this.postService.create(post);
        mv.addObject("successMessage","Post has been edited");
        mv.addObject("post",new Post());
    }
    return mv;
}

@RequestMapping("/posts")
public String index(Model model, @PageableDefault(sort = {"id"},value= 5) Pageable pageable){
        //not exactly sure how above line worked need to see if 6 posts will print

    Page<Post> posts = this.postService.findAll(pageable);

    model.addAttribute("posts",posts);
    return "posts/index";
    }
}