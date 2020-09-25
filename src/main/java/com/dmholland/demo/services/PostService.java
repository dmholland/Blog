package com.dmholland.demo.services;

import com.dmholland.demo.models.Post;
import com.dmholland.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;



/**
 * Implement the PostService and UserService to Use the DB
 * 	Just add new implementations for the UserService and PostService,
 * 	annotated with @Primary.
 * 	This will tell the Spring Framework to use these implementations instead of the old stubs.
 *
 */
@Service
public class PostService implements PostServiceInterface {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    @Override
    public List<Post> findLatest5() {
        // Create our own query
       // return this.postRepository.findLatest5Posts( PageRequest.of(0,5) );
        //Using Streams also worked Descending order
        return this.postRepository.findAll( PageRequest.of(0, 2) ).stream().sorted( (a,b) -> a.getDate().compareTo(b.getDate()) ).collect(Collectors.toList());
    }
    @Override
    public Post findById(Long id) {
        return this.postRepository.findById(id).orElse(null);
        // return this.postRepository.getOne(id);
    }

    @Override
    public Post create(Post post) {
        return this.postRepository.save(post);
    }
    @Override
    public Post edit(Post post) {
        return this.postRepository.save(post);
    }
    @Override
    public void deleteById(Long id) {
        this.postRepository.deleteById(id);
    }

    @Override
    public boolean isValid(Post post){
        if(null == post.getTitle() ||null == post.getBody() || null == post.getAuthor()){return false;}
        return true;
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return this.postRepository.findAll(pageable);
    }
}
