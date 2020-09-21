package com.dmholland.demo.services;

import com.dmholland.demo.models.Post;
import com.dmholland.demo.repository.PostRepository;

import java.util.List;

public interface PostService{
    List<Post> findAll();
    List<Post> findLatest5();
    Post findById(Long id);
    Post create(Post post);
    Post edit(Post post);
    void deleteById(Long id);
}
