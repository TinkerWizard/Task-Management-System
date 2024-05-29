package com.tms.taskManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.taskManagementSystem.dao.UserRepository;
import com.tms.taskManagementSystem.entity.User;

@Service
public class UserService {
    UserRepository userRepository;
    //constructor injection
    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    public UserService()
    {

    }
    public List<User> getAllUsers()
    {
        List<User> users = userRepository.findAll();
        return users;
    }
    public User getUserById(String userId) {
        User user = userRepository.findById(userId).get();
        return user;
    }

    public void saveUser(User user)
    {
        userRepository.save(user);
    }
    public void deleteUser(User user)
    {
        userRepository.delete(user);
    }
}
