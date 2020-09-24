package com.dmholland.demo.services;

import com.dmholland.demo.models.Post;
import com.dmholland.demo.models.User;
import com.dmholland.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    UserRepository repository;

    @Override
    public List<User> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }
    @Override
    public User createUser(User user) {
    return this.repository.save(user);
    }

    @Override
    public void deleteUser(Long Id) {
         this.repository.delete(findById(Id));
    }

    @Override
    public User findById(Long id) {
        return this.repository.getOne(id);
    }

    @Override
    public User findByName(String name) {
        return findAll().stream()
                .filter(x->x.getFullName().equals(name))
                .findFirst().orElse(null);
    }

    @Override
    public User editUser(User user) {
        return this.repository.save(user);
    }

    @Override
    public Set<Post> findAllPosts(User user) {
     return user.getPosts();
    }

    public boolean userCheck(User user){
        return (null != findByName(user.getUsername()));
    }
}
