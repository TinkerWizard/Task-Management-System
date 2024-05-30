package com.tms.taskManagementSystem.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tms.taskManagementSystem.entity.Task;
import com.tms.taskManagementSystem.entity.User;
import com.tms.taskManagementSystem.service.TaskService;
import com.tms.taskManagementSystem.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    UserService userService;
    TaskService taskService;

    // @Autowired
    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    // method to show html form
    @GetMapping("/login")
    public String showLoginForm(Model theModel) {
        theModel.addAttribute("loginForm", new User());
        return "login";
    }

    @PostMapping("/role-detection")
    public String detectRole(@ModelAttribute("loginForm") User user, Model model) {
        String role = "";
        String userId = user.getUserId();
        String password = user.getPassword();
        if (userId == null || password == null) {
            return "login";
        }
        User dbUser = userService.getUserById(userId);
        if (dbUser.getUserId().equals(userId)) {
            if (dbUser.getPassword().equals(password)) {
                System.out.println(user.getClass());
                if (userId.startsWith("ADMIN")) {
                    System.out.println("Admin");
                    List<User> users = userService.getAllUsers();
                    // for (User user_var : users) {
                    // System.out.println(user_var);
                    // }
                    model.addAttribute("users", users);
                    role = "admin";
                }
                if (userId.startsWith("NOR")) {
                    System.out.println("Assignor");
                    List<Task> tasks = taskService.getTasksByAssignorId(userId);
                    User assignor = userService.getUserById(userId);
                    model.addAttribute("assignor", assignor);
                    model.addAttribute("tasks", tasks);
                    role = "assignor";
                }
                if (userId.startsWith("NEE")) {
                    System.out.println("Assignee");
                    List<Task> tasks = taskService.getTasksByAssigneeId(userId);
                    model.addAttribute("tasks", tasks);
                    role = "assignee";
                }
            }
        } 
        // redirectAttributes.addAttribute("userId", userId);
        return "/users/" + role;
    }

    // ADMIN methods

    @GetMapping("/admin")
    public String adminPAge(Model model) {
        List<User> users = userService.getAllUsers();
        // for (User user_var : users) {
        // System.out.println(user_var);
        // }
        model.addAttribute("users", users);
        return "/users/admin";
    }

    @GetMapping("/admin/add")
    public String addUser(Model model) {
        User newUser = new User();
        model.addAttribute("user", newUser);
        return "add-user";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        System.out.println(user.toString());
        return "redirect:/users/admin";
    }

    @GetMapping("/admin/update")
    public String upoateUser(Model model, @RequestParam("userId") String userId) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "add-user";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(@RequestParam("userId") String userId) {
        // System.out.println(userId);
        User user = userService.getUserById(userId);
        userService.deleteUser(user);
        System.out.println("User deleted. \n" + user.toString());
        System.out.println(user.toString());
        return "redirect:/users/admin";
    }

    // ASSIGNOR methods

    @GetMapping("/assignor")
    public String assignorPage(Model model) {
        return "users/assignor";
    }

    @GetMapping("/assignor/add")
    public String addTask(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "add-task";
    }

    @PostMapping("/assignor/save")
    public String saveTask(@ModelAttribute("task") Task task, Model model) {
        taskService.saveTask(task);
        System.out.println("Saved task:" + task.toString());
        List<Task> tasks = taskService.getTasksByAssignorId(task.getAssignorId());
        User assignor = userService.getUserById(task.getAssignorId());
        model.addAttribute("tasks", tasks);
        model.addAttribute("assignor", assignor);
        return "users/assignor";
    }

    @GetMapping("/assignor/update")
    public String updateTask(Model model, @RequestParam("taskId") int taskId)
    {
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        return "update-task";
    }

    @PostMapping("/assignor/update")
    public String updateTask(@ModelAttribute("task") Task task, Model model)
    {
        Task existingTask = taskService.getTaskById(task.getTaskId());
        System.out.println(existingTask.toString());
        existingTask.setTaskId(task.getTaskId());
        existingTask.setTaskTitle(task.getTaskTitle());
        existingTask.setTaskNote(task.getTaskNote());
        //we're not updating assignor id
        existingTask.setAssigneeId(task.getAssigneeId());
        existingTask.setAssignedDate(task.getAssignedDate());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setTaskStatus(task.getTaskStatus());

        taskService.saveTask(existingTask);
        List<Task> tasks = taskService.getTasksByAssignorId(task.getAssignorId());
        User assignor = userService.getUserById(task.getAssignorId());
        model.addAttribute("assignor", assignor);
        model.addAttribute("tasks", tasks);

        return "users/assignor";
    }       
}
