package com.tms.taskManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tms.taskManagementSystem.entity.User;

@Controller
public class LoginController {

    @GetMapping("/")
    public String showLoginPage(Model theModel)
    {
        theModel.addAttribute(new User());
        return "login";
    }
}
