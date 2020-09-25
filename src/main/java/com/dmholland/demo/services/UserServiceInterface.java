package com.dmholland.demo.services;

import com.dmholland.demo.models.Post;
import com.dmholland.demo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface UserServiceInterface {
 List<User> findAll();
 Page<User> findAll(Pageable pageable);
 User createUser(User user);
 void deleteUser(Long id);
 User findById(Long id);
 User findByName(String name);
 User editUser(User user);
 Set<Post> findAllPosts(User user);
User findByUserName(String username);
}
