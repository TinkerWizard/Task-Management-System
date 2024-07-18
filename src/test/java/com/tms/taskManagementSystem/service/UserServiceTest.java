package com.tms.taskmanagementsystem.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tms.taskmanagementsystem.entity.User;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void getAssignors()
    {
        List<User> assignors = userService.getAssignors();
        for (User user : assignors) {
            System.out.println(user);
        }
    }
}
