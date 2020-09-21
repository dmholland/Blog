package com.dmholland.demo.services;

import com.dmholland.demo.models.Post;
import com.dmholland.demo.models.User;

import java.util.List;
import java.util.Set;

public interface UserService {
 List<User> findAll();
 User createUser(User user);
 void deleteUser(User user);
 User findById(Long id);
 User findByName(String name);
 User editUser(User user);
 Set<Post> findAllPosts(User user);

}
