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
        List<User> assignors = userService.getAssignors();
        int assignorListSize = assignors.size();
        User lastAssignor = assignors.get(assignorListSize - 1);
        System.out.println("The last assignor: " + lastAssignor);
        // for (User user : assignors) {
        //     System.out.println(user);
        // }
        model.addAttribute("users", users);
        return "/users/admin";
    }

    @GetMapping("/admin/add")
    public String addUser(Model model) {
        User newUser = new User();
        model.addAttribute("user", newUser);
        Map<String, Integer> lastUserNumbers = new HashMap<>();
        lastUserNumbers.put("lastAdminUserNumber", userService.getAdminLastUserNumber());
        lastUserNumbers.put("lastAssignorUserNumber", userService.getAssignorLastUserNumber());
        lastUserNumbers.put("lastAssigneeUserNumber", userService.getAssigneeLastUserNumber());
        model.addAttribute("lastNumbers", lastUserNumbers);
        return "add-user";
    }
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user, @ModelAttribute("authority") String authority, @ModelAttribute("generatedUsername") String generatedUsername) {
        user.setUsername(generatedUsername);
        user.setEnabled(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        authorityService.addUserWithAuthority(user, authority);
        return "redirect:/admin";
    }

    @GetMapping("/admin/update")
    public String upoateUser(Model model, @RequestParam("username") String username) {
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        Map<String, Integer> lastUserNumbers = new HashMap<>();
        lastUserNumbers.put("lastAdminUserNumber", userService.getAdminLastUserNumber());
        lastUserNumbers.put("lastAssignorUserNumber", userService.getAssignorLastUserNumber());
        lastUserNumbers.put("lastAssigneeUserNumber", userService.getAssigneeLastUserNumber());
        model.addAttribute("lastNumbers", lastUserNumbers);
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
