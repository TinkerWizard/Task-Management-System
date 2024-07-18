package com.tms.taskmanagementsystem.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tms.taskmanagementsystem.entity.User;
import com.tms.taskmanagementsystem.service.UserService;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService)
    {
        this.userService = userService;
    }


    @GetMapping("/showMyLoginPage")
    public String showLoginPage(Model theModel)
    {
        theModel.addAttribute("loginForm", new User());
        return "login";
    }

    @GetMapping("/home")
    public String showHome(Model model, Principal principal)
    {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        System.out.println(user);
        return "home";
    }
}
