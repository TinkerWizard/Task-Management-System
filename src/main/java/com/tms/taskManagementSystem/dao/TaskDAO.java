package com.tms.taskManagementSystem.dao;

import java.util.List;

import com.tms.taskManagementSystem.entity.Task;
public interface TaskDAO {
    Task assignTask();
    List<Task> displayAllTasks(String assignorId);//display tasks assigned by an assignor
    Task displayTask(int taskId);
    Task updateTask(int taskId);
    Task deleteTask(int taskId);
}
