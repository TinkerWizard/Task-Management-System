package com.tms.taskmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.taskmanagementsystem.dao.TaskResopisitory;
import com.tms.taskmanagementsystem.entity.Task;

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
        return taskResopisitory.findAll();
    }

    public List<Task> getTasksByAssigneeId(String userId) {
        return taskResopisitory.findByAssigneeId(userId);
    }

    public List<Task> getTasksByAssignorId(String userId) {
        return taskResopisitory.findByAssignorId(userId);
    }

    public void saveTask(Task task) {
        taskResopisitory.save(task);
    }
    


    public Task getTaskById(int taskId)
    {
        return taskResopisitory.findById(taskId).get();    
    }
}
