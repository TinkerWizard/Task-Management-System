package com.tms.taskManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.taskManagementSystem.dao.AuthorityRepository;
import com.tms.taskManagementSystem.entity.Authority;
import com.tms.taskManagementSystem.entity.User;

@Service
public class AuthorityService {

    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository)
    {
        this.authorityRepository = authorityRepository;
    }


    public void addUserWithAuthority(User user, String authority) {
        Authority userAuthority = new Authority();
        userAuthority.setAuthority(authority);
        userAuthority.setUser(user);
        user.getAuthorities().add(userAuthority);

        authorityRepository.save(userAuthority);
    }
}
