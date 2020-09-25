package com.dmholland.demo.models;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Identity because it auto increments
    private Long id;

    @Column(length = 60)
    @Length(min = 5, message = "Your password must have at least 5 characters")
    @NotEmpty(message = "Please provide your password")
    private String hashPassword;


    @Column(nullable = false,length = 60, unique = true)
    @NotEmpty(message = "Please provide a username")
    private String username;

    @Column(length = 60)
    @Length(min = 5, message = "Your name must have at least 5 characters")
    @NotEmpty(message = "You must enter your name")
    private String fullName;

    @OneToMany(mappedBy = "author")      //Must be a container
    private Set<Post> posts = new HashSet<>();

    public User() {
    }



    public User(Long id, String username, String fullName) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" + "id= " + id + ", username='" + username + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                ", fullName='" + fullName + '\'' + '}';
    }
}
