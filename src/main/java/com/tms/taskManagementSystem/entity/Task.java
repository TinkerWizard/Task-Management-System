package com.tms.taskManagementSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// pre-defined task statuses
@Entity
@Table(name = "tasks")
public class Task {

    // define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int taskId;

    @Column(name = "task_title")
    private String taskTitle;

    @Column(name = "task_note")
    private String taskNote;

    @Column(name = "assignor_id")
    private String assignorId;

    @Column(name = "assignee_id")
    private String assigneeId;

    @Column(name = "assigned_date")
    private String assignedDate;

    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "task_status")
    private String taskStatus;

    // define constructors
    public Task() {

    }

    public Task(String taskTitle, String taskNote, String assignorId, String assigneeId, String assignedDate,
            String dueDate, String taskStatus) {
        this.taskTitle = taskTitle;
        this.taskNote = taskNote;
        this.assignorId = assignorId;
        this.assigneeId = assigneeId;
        this.assignedDate = assignedDate;
        this.dueDate = dueDate;
        this.taskStatus = taskStatus;
    }

    public int getTaskId() {
        return taskId;
    }

    // public void setTaskId(int taskId) {
    //     this.taskId = taskId;
    // }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskNote() {
        return taskNote;
    }

    public void setTaskNote(String taskNote) {
        this.taskNote = taskNote;
    }

    public String getAssignorId() {
        return assignorId;
    }

    public void setAssignorId(String assignorId) {
        this.assignorId = assignorId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
    

    // public void setStatus(String status) {
    //     // pre-defined task statuses
    //     //set drop down in the front end
    //     String[] taskStatus = {
    //             "Not Started",
    //             "In Progress",
    //             "On Hold",
    //             "Completed",
    //             "Cancelled",
    //             "Pending",
    //             "Under Review",
    //             "Delayed",
    //             "In Planning",
    //             "Blocked"
    //     };
    //     this.status = status;
    // }


    // define toString() method

}
