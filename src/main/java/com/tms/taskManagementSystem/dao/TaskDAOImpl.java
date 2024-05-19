package com.tms.taskManagementSystem.dao;

import com.tms.taskManagementSystem.entity.Task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class TaskDAOImpl implements TaskDAO{
    private EntityManager entityManager;

    //constructor injection
    @Autowired
    public TaskDAOImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }


    @Override
    public List<Task> displayAllTasks(String assignorId) {
        TypedQuery<Task> thQuery = entityManager.createQuery("FROM Task WHERE assignor = :assignorId", Task.class);
        thQuery.setParameter("assignorId", assignorId);
        return thQuery.getResultList(); 
    }

    @Override
    public Task displayTask(int taskId) {
        Task task = entityManager.find(Task.class, taskId);
        return task;
    }

    @Override
    @Transactional
    public Task updateTask(int taskId) {
        Task task = entityManager.find(Task.class, taskId);
        //do the necessary updates using setter methods

        entityManager.merge(task);
        return task;
    }

    @Override
    @Transactional
    public Task deleteTask(int taskId) {
        Task task = entityManager.find(Task.class, taskId);
        entityManager.remove(task);
        return task;
    }

    @Override
    @Transactional
    public Task assignTask() {
        //get the details in front end
        //for now, get the details through command line
        Task newTask = new Task("Java Performance Tuning", "Tune Java application performance", "NOR_2", "NEE_2", "2024-06-05", "2024-06-08", "Blocked");
        entityManager.merge(newTask);
        return newTask;
    }

}
