package com.tms.taskManagementSystem.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

@Controller
public class UserController {

    UserService userService;
    TaskService taskService;
    AuthorityService authorityService;

    // @Autowired
    public UserController(UserService userService, TaskService taskService, AuthorityService authorityService) {
        this.userService = userService;
        this.taskService = taskService;
        this.authorityService = authorityService;
    }

    @PostMapping("/role-detection")
    public String detectRole(@ModelAttribute("loginForm") User user, Model model) {
        String role = "";
        String username = user.getUsername();
        String password = user.getPassword();
        if (username == null || password == null) {
            return "login";
        }
        User dbUser = userService.getUserByUsername(username);
        if (dbUser.getUsername().equals(username)) {
            if (dbUser.getPassword().equals(password)) {
                System.out.println(user.getClass());
                if (username.startsWith("ADMIN")) {
                    System.out.println("Admin");
                    List<User> users = userService.getAllUsers();
                    // for (User user_var : users) {
                    // System.out.println(user_var);
                    // }
                    model.addAttribute("users", users);
                    role = "admin";
                }
                if (username.startsWith("NOR")) {
                    System.out.println("Assignor");
                    List<Task> tasks = taskService.getTasksByAssignorId(username);
                    User assignor = userService.getUserByUsername(username);
                    model.addAttribute("assignor", assignor);
                    model.addAttribute("tasks", tasks);
                    role = "assignor";
                }
                if (username.startsWith("NEE")) {
                    System.out.println("Assignee");
                    List<Task> tasks = taskService.getTasksByAssigneeId(username);
                    User assignee = userService.getUserByUsername(username);
                    model.addAttribute("assignee", assignee);
                    model.addAttribute("tasks", tasks);
                    role = "assignee";
                }
            }
        }
        // redirectAttributes.addAttribute("username", username);
        return "/users/" + role;
    }

    // ADMIN methods

    @GetMapping("/admin")
    public String adminPage(Model model) {
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
        newUser.setEnabled(1);
        model.addAttribute("user", newUser);
        return "add-user";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user, @ModelAttribute("authority") String authority) {
        userService.saveUser(user);
        authorityService.addUserWithAuthority(user, authority);
        System.out.println(user);
        System.out.println(authority);
        System.out.println(user.toString());
        return "redirect:/admin";
    }

    @GetMapping("/admin/update")
    public String upoateUser(Model model, @RequestParam("username") String username) {
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "add-user";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(@RequestParam("username") String username) {
        // System.out.println(username);
        User user = userService.getUserByUsername(username);
        userService.deleteUser(user);
        System.out.println("User deleted. \n" + user.toString());
        System.out.println(user.toString());
        return "redirect:/admin";
    }

    // ASSIGNOR methods

    @GetMapping("/assignor")
    public String assignorPage(Model model, @RequestParam("username") String username) {
        if (!isAuthorized(username)) {
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

    // ASSIGNEE Methods
    @GetMapping("/assignee")
    public String assignee(@RequestParam("username") String username, Model model) {
        if (!isAuthorized(username)) {
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

    // Other methods

    private String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        }
        return null;
    }

    private boolean isAuthorized(String username) {
        String authenticatedUsername = getAuthenticatedUsername();
        return authenticatedUsername != null && authenticatedUsername.equals(username);
    }
}
