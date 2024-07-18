package com.tms.taskmanagementsystem.service;

import org.springframework.stereotype.Service;

import com.tms.taskmanagementsystem.dao.AuthorityRepository;
import com.tms.taskmanagementsystem.entity.Authority;
import com.tms.taskmanagementsystem.entity.User;

@Service
public class AuthorityService {

    private AuthorityRepository authorityRepository;

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
