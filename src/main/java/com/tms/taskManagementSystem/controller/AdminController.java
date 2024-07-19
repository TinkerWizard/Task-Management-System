package com.tms.taskmanagementsystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tms.taskmanagementsystem.entity.User;
import com.tms.taskmanagementsystem.service.AuthorityService;
import com.tms.taskmanagementsystem.service.TaskService;
import com.tms.taskmanagementsystem.service.UserService;

@Controller
public class AdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    UserService userService;
    TaskService taskService;
    AuthorityService authorityService;


    // @Autowired
    public AdminController(UserService userService, TaskService taskService, AuthorityService authorityService) {
        this.userService = userService;
        this.taskService = taskService;
        this.authorityService = authorityService;
    }

    @GetMapping("/admin")
    public String adminPage(@RequestParam("username") String username, Model model) {
        if (!userService.isAuthorized(username)) {
            return "/403"; // Return a 403 error page if not authorized
        }
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("adminUsername", username);
        return "/users/admin";
    }

    @GetMapping("/admin/add")
    public String addUser(Model model, @ModelAttribute("adminUsername") String adminUsername) {
        User newUser = new User();
        model.addAttribute("user", newUser);
        Map<String, Integer> lastUserNumbers = new HashMap<>();
        lastUserNumbers.put("lastAdminUserNumber", userService.getAdminLastUserNumber());
        lastUserNumbers.put("lastAssignorUserNumber", userService.getAssignorLastUserNumber());
        lastUserNumbers.put("lastAssigneeUserNumber", userService.getAssigneeLastUserNumber());
        model.addAttribute("lastNumbers", lastUserNumbers);
        model.addAttribute("adminUsername", adminUsername);
        return "add-user";
    }
    @PostMapping("/save")
    public String saveUser(Model model, @ModelAttribute("user") User user, @ModelAttribute("authority") String authority, @ModelAttribute("generatedUsername") String generatedUsername, @ModelAttribute("adminUsername") String adminUsername) {
        String message = "added";
        user.setUsername(generatedUsername);
        user.setEnabled(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        authorityService.addUserWithAuthority(user, authority);
        model.addAttribute("message", message);
        model.addAttribute("adminUsername", adminUsername);
        return "user-added-updated-deleted-success";
    }

    @PostMapping("/saveUpdate")
    public String saveUpdatedUser(Model model, @ModelAttribute("user") User updatedUser, @ModelAttribute("adminUsername") String adminUsername)
    {
        String message = "updated";
        System.out.println("Updated user: " + updatedUser);
        updatedUser.setEnabled(1);
        updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        userService.saveUser(updatedUser);
        System.out.println("--------------ADMIN USERNAME - SAVE UPDATE----------- \n " + adminUsername);
        model.addAttribute("message", message);
        model.addAttribute("adminUsername", adminUsername);
        return "user-added-updated-deleted-success";
    }
    @GetMapping("/admin/update")
    public String updateUser(Model model, @RequestParam("username") String username, @ModelAttribute("adminUsername") String adminUsername) {
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("adminUsername", adminUsername);
        System.out.println("--------------ADMIN USERNAME - UPDATE USER----------- \n " + adminUsername);
        return "update-user";
    }
    
    @GetMapping("/admin/delete")
    public String deleteUser(Model model, @RequestParam("username") String username, @ModelAttribute("adminUsername") String adminUsername) {
        String message = "deleted";
        User user = userService.getUserByUsername(username);
        userService.deleteUser(user);
        model.addAttribute("adminUsername", adminUsername);
        model.addAttribute("message", message);
        System.out.println("User deleted. \n" + user.toString());
        System.out.println(user.toString());
        return "user-added-updated-deleted-success";
    }

}
