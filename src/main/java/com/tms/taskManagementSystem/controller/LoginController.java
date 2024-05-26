package com.tms.taskManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tms.taskManagementSystem.dao.UserRepository;
import com.tms.taskManagementSystem.entity.User;


@Controller
public class LoginController {


    //method to show html form
    @GetMapping("/")
    public String showLoginForm(Model theModel)
    {
        theModel.addAttribute("loginForm", new User());
        return "login";
    }

    //method to show html results
    
    @PostMapping("/home")
    public String showHome(@ModelAttribute("loginForm") User user)
    {
        String userID = user.getUserId();
        String password = user.getPassword();

        // User dbUser = 
        //check against the db
        //if valid return the home page
        //if not valid return back the login page
        //return "login";
        System.out.println(user.getClass());
        return "home";
    }
    
}
