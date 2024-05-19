package com.tms.taskManagementSystem.dao;

import java.util.List;

import com.tms.taskManagementSystem.entity.Task;

public interface AssigneeDAO {
    List<Task> displayTasks(String assigneeId);
    Task displayTask(String assigneeId, int taskId);
    Task updateStatus(String assigneeId, int taskId);

    String authentication(String assigneeId, String password);
}
