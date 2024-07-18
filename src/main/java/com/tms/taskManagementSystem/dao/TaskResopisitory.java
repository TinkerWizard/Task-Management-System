package com.tms.taskmanagementsystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.taskmanagementsystem.entity.Task;

public interface TaskResopisitory extends JpaRepository<Task, Integer> {

    List<Task> findByAssigneeId(String assigneeId);
    List<Task> findByAssignorId(String assignorId);

}
