package com.tms.taskManagementSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.taskManagementSystem.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}