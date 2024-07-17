package com.tms.taskManagementSystem.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
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
import com.tms.taskManagementSystem.util.IsAuthorized;

@Controller
public class AssignorController {

    UserService userService;
    TaskService taskService;
    AuthorityService authorityService;

    IsAuthorized isAuthorized;

    // @Autowired
    public AssignorController(UserService userService, TaskService taskService, AuthorityService authorityService) {
        this.userService = userService;
        this.taskService = taskService;
        this.authorityService = authorityService;
    }


    @GetMapping("/assignor")
    public String assignorPage(Model model, @RequestParam("username") String username) {
        if (!isAuthorized.isAuthorized(username)) {
            return "/403"; // Return a 403 error page if not authorized
        }
        System.out.println("Assignor");
        List<Task> tasks = taskService.getTasksByAssignorId(username);
        User assignor = userService.getUserByUsername(username);
        model.addAttribute("assignor", assignor);
        model.addAttribute("tasks", tasks);
        return "users/assignor";
    }

    @GetMapping("/assignor/add")
    public String addTask(Model model) {
        String assignorId = SecurityContextHolder.getContext().getAuthentication().getName();
        // System.out.println("Username: " + username);
        Task task = new Task();
        List<User> assigneeList = userService.getAssignees();
        // System.out.println(assigneeList);
        task.setAssignorId(assignorId);
        model.addAttribute("task", task);
        model.addAttribute("assignees", assigneeList);
        return "add-task";
    }

    @PostMapping("/assignor/save")
    public String saveTask(@ModelAttribute("task") Task task, Model model) {
        taskService.saveTask(task);
        System.out.println("Saved task:" + task.toString());
        List<Task> tasks = taskService.getTasksByAssignorId(task.getAssignorId());
        User assignor = userService.getUserByUsername(task.getAssignorId());
        model.addAttribute("tasks", tasks);
        model.addAttribute("assignor", assignor);
        return "users/assignor";
    }

    @GetMapping("/assignor/update")
    public String updateTask(Model model, @RequestParam("taskId") int taskId) {
        Task task = taskService.getTaskById(taskId);
        List<User> assigneeList = userService.getAssignees();
        model.addAttribute("assignees", assigneeList);
        model.addAttribute("task", task);
        return "update-task";
    }

    @PostMapping("/assignor/update")
    public String updateTask(@ModelAttribute("task") Task task, Model model) {
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
        List<Task> tasks = taskService.getTasksByAssignorId(task.getAssignorId());
        User assignor = userService.getUserByUsername(task.getAssignorId());
        model.addAttribute("assignor", assignor);
        model.addAttribute("tasks", tasks);

        return "users/assignor";
    }

}
