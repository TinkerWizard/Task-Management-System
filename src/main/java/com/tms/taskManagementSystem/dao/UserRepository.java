package com.tms.taskmanagementsystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tms.taskmanagementsystem.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u JOIN Authority r ON u = r.user WHERE r.authority = :authority")
    List<User> findUsersByAuthority(@Param("authority") String authority);
}
