package com.tms.taskManagementSystem.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tms.taskManagementSystem.dao.UserDAO;
import com.tms.taskManagementSystem.entity.User;

@RestController
@RequestMapping("/api/assignor")
public class AssignorRestController {
    private UserDAO userDAO;
    //constructor injection
    @Autowired
    public AssignorRestController(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    @GetMapping("/auth")
    public String authentication()
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter user id:");
            String userId = reader.readLine();
            System.out.println("Enter password:");
            String password = reader.readLine();
            String result = userDAO.authentication(userId, password);
            return "Auth result: " + result;
        } catch (Exception e) {
            return e.toString();
        }
    }
    @GetMapping("/users")
    public List<User> getAllUsers() {
        System.out.println("Inside get all users");
        return userDAO.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable String userId) 
    {
        User user = userDAO.getUserById(userId);
        return user;
    }

    @GetMapping("/users/assignee")
    public List<User> getAllAssignee() {
        System.out.println("Inside get all assignee");
        System.out.println("Size of the list: " + userDAO.getAssignees().size());
        return userDAO.getAssignees();
    }

    //add a new assignee
    @PostMapping("users/assignee")
    public User addAssignee()
    {
        User user = userDAO.addAssignee();
        return user;
    }

    @PutMapping("users/{assigneeId}")
    public User updateAssignee(@PathVariable String assigneeId)
    {
        // User user = userDAO.getUserById(assigneeId);
        User user =  userDAO.updateAssignee(assigneeId);
        return user;
    }

    @DeleteMapping("users/{assigneeId}")
    public String deleteAssignee(@PathVariable String assigneeId)
    {
        User user = userDAO.getUserById(assigneeId);
        userDAO.deleteAssignee(assigneeId);
        return "Deleted User: " + user.toString(); 
    }
}
 