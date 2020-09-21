package com.dmholland.demo.services;

import com.dmholland.demo.models.Post;
import com.dmholland.demo.repository.PostRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class PostServiceRepository implements PostService {

    PostRepository repository;
    @Override
    public List<Post> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Post> findLatest5() {
            return findAll().stream()
                    .sorted((a,b) -> b.getDate().compareTo(a.getDate()))
                    .limit(5)
                    .collect(Collectors.toList());

    }

    @Override
    public Post findById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Post create(Post post) {
        return this.repository.save(post);
    }

    @Override
    public Post edit(Post post) {
        return this.repository.save(post);
    }

    @Override
    public void deleteById(Long id) {
     this.repository.deleteById(id);
    }
}
