package com.tms.taskManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    

    public User getUserByUsername(String username) {
        User user = userRepository.findById(username).get();
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
    public List<User> getAssignees() {
        return userRepository.findUsersByAuthority("ASSIGNEE");
    }



    // ------------- Other methods-----------
    public String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        }
        return null;
    }

    public boolean isAuthorized(String username) {
        String authenticatedUsername = getAuthenticatedUsername();
        return authenticatedUsername != null && authenticatedUsername.equals(username);
    }
}
