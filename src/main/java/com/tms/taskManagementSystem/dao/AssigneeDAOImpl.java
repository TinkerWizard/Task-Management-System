package com.tms.taskManagementSystem.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tms.taskManagementSystem.entity.Task;
import com.tms.taskManagementSystem.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AssigneeDAOImpl implements AssigneeDAO {
    private EntityManager entityManager;

    // constructor injection
    public AssigneeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public String authentication(String userId, String password) {
        if (userId == null || password == null) {
            return "Auth Invalid";
        }

        User user = entityManager.find(User.class, userId);

        if (user != null) {
            String dbPassword = user.getPassword();
            if (password.equals(dbPassword)) {
                System.out.println("Auth valid");
                return user.getUserId();
            }
        }
        return "Auth Invalid";
    }

    @Override
    public List<Task> displayTasks(String assigneeId) {
        TypedQuery<Task> theQuery = entityManager.createQuery("FROM Task WHERE assignee= :assigneeId", Task.class);
        theQuery.setParameter("assigneeId", assigneeId);
        return theQuery.getResultList();
    }

    @Override
    public Task displayTask(String assigneeId, int taskId) {
        TypedQuery<Task> theQuery = entityManager.createQuery("FROM Task WHERE assignee= :assigneeId AND taskId= :taskId", Task.class);
        theQuery.setParameter("assigneeId", assigneeId);
        theQuery.setParameter("taskId", taskId);
        return theQuery.getSingleResult();
    }

    @Override
    @Transactional
    public Task updateStatus(String assigneeId, int taskId) {
        Task task = entityManager.find(Task.class, taskId);
        task.setStatus("Completed");
        entityManager.merge(task);
        return task;
    }

}
