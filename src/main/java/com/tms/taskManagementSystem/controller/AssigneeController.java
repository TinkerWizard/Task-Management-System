package com.tms.taskManagementSystem.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tms.taskManagementSystem.entity.Task;
import com.tms.taskManagementSystem.entity.User;
import com.tms.taskManagementSystem.service.AuthorityService;
import com.tms.taskManagementSystem.service.TaskService;
import com.tms.taskManagementSystem.service.UserService;
import com.tms.taskManagementSystem.util.Utils;

@Controller
public class AssigneeController {

    UserService userService;
    TaskService taskService;
    AuthorityService authorityService;


    // @Autowired
    public AssigneeController(UserService userService, TaskService taskService, AuthorityService authorityService) {
        this.userService = userService;
        this.taskService = taskService;
        this.authorityService = authorityService;
    }

    @GetMapping("/assignee")
    public String assignee(@RequestParam("username") String username, Model model) {
        if (!userService.isAuthorized(username)) {
            return "/403"; // Return a 403 error page if not authorized
        }
        User assignee = userService.getUserByUsername(username);
        List<Task> tasks = taskService.getTasksByAssigneeId(username);

        model.addAttribute("assignee", assignee);
        model.addAttribute("tasks", tasks);
        return "users/assignee";
    }

    @GetMapping("/assignee/updateStatus")
    public String updateStatus(Model model, @RequestParam("taskId") int taskId) {
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        return "update-status";
    }

    @PostMapping("/assignee/updateStatus")
    public String updateTaskStatus(@ModelAttribute("task") Task task, Model model) {
        Task existingTask = taskService.getTaskById(task.getTaskId());
        System.out.println(existingTask.toString());
        existingTask.setTaskId(task.getTaskId());
        existingTask.setTaskTitle(task.getTaskTitle());
        existingTask.setTaskNote(task.getTaskNote());
        // we're not updating assignor id
        existingTask.setAssigneeId(task.getAssigneeId());
        existingTask.setAssignedDate(task.getAssignedDate());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setTaskStatus(task.getTaskStatus());

        taskService.saveTask(existingTask);
        List<Task> tasks = taskService.getTasksByAssigneeId(task.getAssigneeId());
        User assignee = userService.getUserByUsername(task.getAssigneeId());
        model.addAttribute("assignee", assignee);
        model.addAttribute("tasks", tasks);

        return "users/assignee";
    }
}
