package com.tms.taskManagementSystem.dao;

import com.tms.taskManagementSystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskResopisitory extends JpaRepository<Task, Integer> {

}
