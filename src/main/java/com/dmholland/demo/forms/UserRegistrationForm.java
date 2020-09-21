package com.dmholland.demo.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistrationForm {
    @Size(min = 2, max = 30, message = "Username must be at least 30 characters long")
    private String username;

    @NotNull
    @Size(min=1,max=50, message = "Make the password at least 50 characters long")
    private String password;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password=password;
    }


}
