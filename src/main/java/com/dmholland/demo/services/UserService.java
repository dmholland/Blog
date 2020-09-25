package com.dmholland.demo.services;

import com.dmholland.demo.models.Post;
import com.dmholland.demo.models.User;
import com.dmholland.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Primary
public class UserService implements UserServiceInterface{

    @Autowired
   private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        user.setHashPassword(bCryptPasswordEncoder.encode(user.getHashPassword()));
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

    @Override
    public User findByUserName(String username) {
        return this.repository.findByusername(username);}

    public boolean userCheck(User user){
        return (null != findByName(user.getUsername()));
    }
}
