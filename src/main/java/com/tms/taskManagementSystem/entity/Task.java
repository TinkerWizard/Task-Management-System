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

    @Column(name = "title")
    private String title;

    @Column(name = "note")
    private String note;

    @Column(name = "assignor")
    private String assignor;

    @Column(name = "assignee")
    private String assignee;

    @Column(name = "assigned_on")
    private String assignedOn;

    @Column(name = "deadline")
    private String deadline;

    @Column(name = "status")
    private String status;

    // define constructors
    public Task() {

    }

    public Task(String title, String note, String assignor, String assignee, String assignedOn,
            String deadline, String status) {
        this.title = title;
        this.note = note;
        this.assignor = assignor;
        this.assignee = assignee;
        this.assignedOn = assignedOn;
        this.deadline = deadline;
        this.status = status;
    }
    // define getters and setters

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }

    public String getAssignor() {
        return assignor;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getAssignedOn() {
        return assignedOn;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setAssignor(String assignor) {
        this.assignor = assignor;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setAssignedOn(String assignedOn) {
        this.assignedOn = assignedOn;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setStatus(String status) {
        // pre-defined task statuses
        //set drop down in the front end
        String[] taskStatus = {
                "Not Started",
                "In Progress",
                "On Hold",
                "Completed",
                "Cancelled",
                "Pending",
                "Under Review",
                "Delayed",
                "In Planning",
                "Blocked"
        };
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task [taskId=" + taskId + ", title=" + title + ", note=" + note + ", assignor=" + assignor
                + ", assignee=" + assignee + ", assignedOn=" + assignedOn + ", deadline=" + deadline + ", status="
                + status + "]";
    }

    // define toString() method

}
