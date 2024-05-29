package com.tms.taskManagementSystem.dao;

import com.tms.taskManagementSystem.entity.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskResopisitory extends JpaRepository<Task, Integer> {

    List<Task> findByAssigneeId(String assigneeId);
    List<Task> findByAssignorId(String assignorId);

}
