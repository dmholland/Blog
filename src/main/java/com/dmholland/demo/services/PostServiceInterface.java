package com.dmholland.demo.services;

import com.dmholland.demo.models.Post;
import com.dmholland.demo.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostServiceInterface {
    List<Post> findAll();
    List<Post> findLatest5();
    Post findById(Long id);
    Post create(Post post);
    Post edit(Post post);
    void deleteById(Long id);
    boolean isValid(Post post);
    Page<Post> findAll(Pageable pageable);
}
