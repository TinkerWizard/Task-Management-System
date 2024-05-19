package com.tms.taskManagementSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    // define fields
    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    // define constructors
    public User()
    {
        
    }
    public User(String name, String userId, String password) {
        this.name = name;
        this.userId = userId;
        this.password = password;
    }

    // define getters and stters

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    //define toString() method
    @Override
    public String toString() {
        return "User [name=" + name + ", userId=" + userId + ", password=" + password + "]";
    }
    
}
