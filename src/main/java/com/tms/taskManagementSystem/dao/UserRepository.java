package com.tms.taskManagementSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.taskManagementSystem.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
