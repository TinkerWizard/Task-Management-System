package com.tms.taskManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tms.taskManagementSystem.entity.User;
import com.tms.taskManagementSystem.service.AuthorityService;
import com.tms.taskManagementSystem.service.TaskService;
import com.tms.taskManagementSystem.service.UserService;
import com.tms.taskManagementSystem.util.Utils;

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
        return "/users/admin";
    }

    @GetMapping("/admin/add")
    public String addUser(Model model) {
        User newUser = new User();
        newUser.setEnabled(1);
        model.addAttribute("user", newUser);
        return "add-user";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user, @ModelAttribute("authority") String authority) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        authorityService.addUserWithAuthority(user, authority);
        return "redirect:/admin";
    }

    @GetMapping("/admin/update")
    public String upoateUser(Model model, @RequestParam("username") String username) {
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "add-user";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(@RequestParam("username") String username) {
        // System.out.println(username);
        User user = userService.getUserByUsername(username);
        userService.deleteUser(user);
        System.out.println("User deleted. \n" + user.toString());
        System.out.println(user.toString());
        return "redirect:/admin";
    }
}
