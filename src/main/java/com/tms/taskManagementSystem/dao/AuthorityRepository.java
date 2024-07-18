package com.tms.taskmanagementsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.taskmanagementsystem.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}