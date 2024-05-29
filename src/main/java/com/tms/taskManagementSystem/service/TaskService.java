package com.tms.taskManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.taskManagementSystem.dao.TaskResopisitory;
import com.tms.taskManagementSystem.entity.Task;

@Service
public class TaskService {
    TaskResopisitory taskResopisitory;

    // constructor injection
    @Autowired
    public TaskService(TaskResopisitory taskResopisitory) {
        this.taskResopisitory = taskResopisitory;
    }

    public TaskService() {

    }

    public List<Task> getTasks() {
        List<Task> tasks = taskResopisitory.findAll();
        return tasks;
    }

    public List<Task> getTasksByAssigneeId(String userId) {
        List<Task> tasks = taskResopisitory.findByAssigneeId(userId);
        return tasks;
    }

    public List<Task> getTasksByAssignorId(String userId) {
        List<Task> tasks = taskResopisitory.findByAssignorId(userId);
        return tasks;
    }

    public void saveTask(Task task) {
        taskResopisitory.save(task);
    }
    
    // public void updateTask(Task task)
    // {
    //     taskResopisitory.
    // }

    public Task getTaskById(int taskId)
    {
        Task task = taskResopisitory.findById(taskId).get();    
        return task;
    }
}
