import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Task {
    Auth authObj = new Auth();
    static int id = 1;
    int taskId;
    String title = "";
    String note = "";
    String assignor;
    String assignee;
    String assignedOn;
    String deadline;
    String status = "";
    ArrayList<Task> taskList = new ArrayList<>();
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

    Task(String title, String note, String assignor, String assignee, String assingedOn, String deadline,
            String status) {
        this.taskId = id++;
        this.title = title;
        this.note = note;
        this.assignor = assignor;
        this.assignee = assignee;
        this.assignedOn = assingedOn;
        this.deadline = deadline;
        this.status = status;
    }

    Task(int taskId, String title, String note, String assignor, String assignee, String assingedOn,
            String deadline, String status) {
        this.taskId = taskId;
        this.title = title;
        this.note = note;
        this.assignor = assignor;
        this.assignee = assignee;
        this.assignedOn = assingedOn;
        this.deadline = deadline;
        this.status = status;
    }

    Task() {
        addTasks();
    }

    public void addTasks() {
        taskList.add(new Task("Java", "", "NOR_001", "NEE_001", "", "", taskStatus[0]));
        taskList.add(new Task("C Lang", "", "NOR_001", "NEE_001", "", "", taskStatus[0]));
        taskList.add(new Task("Leetcode", "", "NOR_001", "NEE_002", "", "", taskStatus[0]));
        taskList.add(new Task("Task management system", "", "NOR_001", "NEE_002", "", "", taskStatus[0]));
        taskList.add(new Task("LMS", "Library management system", "NOR_001", "NEE_001", "", "", taskStatus[0]));
    }

    public void assignTask(String assignor, String assignee) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Enter title of the task: ");
            String title = reader.readLine();
            System.out.println("Enter note of the task(if any): ");
            String note = reader.readLine();
            note = note.length() > 1 ? note : "No Notes were added";
            System.out.println("Enter the assigned date and time: ");
            String assignedOn = setDateTime();
            System.out.println("Enter the deadline date and time: ");
            String deadline = setDateTime();
            String status = taskStatus[0];
            taskList.add(new Task(title, note, assignor, assignee, assignedOn, deadline, status));
            System.out.println("Task assigned successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayAllTasks(String userId) {
        if (taskList.size() == 0) {
            System.out.println("TaskList empty");
            return;
        }
        int assignedTask = 0;
        for (Task task : taskList) {
            if (task.assignor.equals(userId) || task.assignee.equals(userId)) {
                displayTask(task.taskId);
                assignedTask++;
            }
        }
        System.out.println(assignedTask + " task assigned");

    }

    public void displayTask(int id) {
        for (Task task : taskList) {
            if (task.taskId == id) {
                System.out.println("----------------------------------------");
                System.out.println();
                System.out.println("Task id: " + task.taskId);
                System.out.println("Task title: " + task.title);
                System.out.println("Task note: " + task.note);
                System.out.println("Task Assignor: " + task.assignor);
                System.out.println("Task Assignee: " + task.assignee);
                System.out.println("Task assigned on: " + task.assignedOn);
                System.out.println("Task deadline: " + task.deadline);
                System.out.println("Task status: " + task.status);
                System.out.println();
                System.out.println("----------------------------------------");
            }
        }
    }

    public void updateTask(String userId) {
        if (taskList.size() == 0) {
            System.out.println("Task list empty");
            return;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        displayAllTasks(userId);
        System.out.println("Enter the task id to be updated: ");
        int updateId = scanner.nextInt();
        boolean isPresent = false;
        System.out.println("Entered Id: " + updateId);
        int index = 0;
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("Inside for loop: " + i);
            int taskId = taskList.get(i).taskId;
            if (taskId == updateId) {
                isPresent = true;
                System.out.println("Task with the corresponding is found.");
                index = i;
                break;
            }
        }
        if (isPresent == false) {
            System.out.println("Task with the corresponding id not found.");
            scanner.close();
            return;
        }
        if (authObj.getType(userId).equals("NOR")) {
            boolean loop = true;
            while (loop == true) {
                System.out.println(
                        "What would you like to update? \n1. Task title \n2. Task note \n3.Update assignee \n4. Assigned date \n5. Deadline \n6. Status \n7. Display this task detail \n8. Exit ");
                try {
                    int choice = scanner.nextInt();

                    // intialize variables
                    int taskId;
                    String title;
                    String note;
                    String assignor;
                    String assignee;
                    String assignedOn;
                    String deadline;
                    String status;
                    switch (choice) {
                        case 1:
                            taskId = taskList.get(index).taskId;
                            System.out.println("Enter the new title to be updated: ");
                            String updatedTitle = reader.readLine();
                            note = taskList.get(index).note;
                            assignor = taskList.get(index).assignor;
                            assignee = taskList.get(index).assignee;
                            assignedOn = taskList.get(index).assignedOn;
                            deadline = taskList.get(index).deadline;
                            status = taskList.get(index).status;
                            taskList.set(index,
                                    new Task(taskId, updatedTitle, note, assignor, assignee, assignedOn, deadline,
                                            status));
                            break;
                        case 2:
                            taskId = taskList.get(index).taskId;
                            title = taskList.get(index).title;
                            System.out.println("Enter the note title to be updated: ");
                            String updatedNote = reader.readLine();
                            assignor = taskList.get(index).assignor;
                            assignee = taskList.get(index).assignee;
                            assignedOn = taskList.get(index).assignedOn;
                            deadline = taskList.get(index).deadline;
                            status = taskList.get(index).status;
                            taskList.set(index,
                                    new Task(taskId, title, updatedNote, assignor, assignee, assignedOn, deadline,
                                            status));
                            break;
                        case 3:
                            taskId = taskList.get(index).taskId;
                            title = taskList.get(index).title;
                            note = taskList.get(index).note;
                            assignor = taskList.get(index).assignor;
                            authObj.getAllAssignees();
                            System.out.println("Choose an assignee from the above");
                            int assigneeChoice = scanner.nextInt();
                            String updatedAssignee = authObj.assigneeList.get(assigneeChoice - 1);
                            assignedOn = taskList.get(index).assignedOn;
                            deadline = taskList.get(index).deadline;
                            status = taskList.get(index).status;
                            taskList.set(index,
                                    new Task(taskId, title, note, assignor, updatedAssignee, assignedOn, deadline,
                                            status));
                            break;
                        case 4:
                            taskId = taskList.get(index).taskId;
                            title = taskList.get(index).title;
                            note = taskList.get(index).note;
                            assignor = taskList.get(index).assignor;
                            assignee = taskList.get(index).assignee;
                            String updatedAssignedOn = null;
                            deadline = taskList.get(index).deadline;
                            status = taskList.get(index).status;
                            taskList.set(index,
                                    new Task(taskId, title, note, assignor, assignee, updatedAssignedOn, deadline,
                                            status));
                            break;
                        case 5:
                            taskId = taskList.get(index).taskId;
                            title = taskList.get(index).title;
                            note = taskList.get(index).note;
                            assignor = taskList.get(index).assignor;
                            assignee = taskList.get(index).assignee;
                            assignedOn = taskList.get(index).assignedOn;
                            String updatedDeadline = null;
                            status = taskList.get(index).status;
                            taskList.set(index,
                                    new Task(taskId, title, note, assignor, assignee, assignedOn, updatedDeadline,
                                            status));
                            break;
                        case 6:
                            taskId = taskList.get(index).taskId;
                            title = taskList.get(index).title;
                            note = taskList.get(index).note;
                            assignor = taskList.get(index).assignor;
                            assignee = taskList.get(index).assignee;
                            assignedOn = taskList.get(index).assignedOn;
                            deadline = taskList.get(index).deadline;
                            System.out.println("Choose a new status to be updated: ");
                            for (int i = 0; i < taskStatus.length; i++) {
                                System.out.println(i + 1 + ": " + taskStatus[i]);
                            }
                            int statusChoice = scanner.nextInt();
                            String updatedStatus = taskStatus[statusChoice - 1];
                            taskList.set(index,
                                    new Task(taskId, title, note, assignor, assignee, assignedOn, deadline,
                                            updatedStatus));
                            break;
                        case 7:
                            displayTask(index + 1);
                            break;
                        case 8:
                            System.out.println("-----Exiting Update Funcationality-----");
                            loop = false;
                            break;
                        default:
                            System.out.println("Enter a valid choice from above");
                            break;
                    }
                } catch (IOException e) {
                    System.out.println("Error found");
                    e.printStackTrace();
                }
            }
        }
        if (authObj.getType(userId).equals("NEE")) {
            taskId = taskList.get(index).taskId;

            title = taskList.get(index).title;
            note = taskList.get(index).note;
            assignor = taskList.get(index).assignor;
            assignee = taskList.get(index).assignee;
            assignedOn = taskList.get(index).assignedOn;
            deadline = taskList.get(index).deadline;
            System.out.println("Choose a new status to be updated: ");
            for (int i = 0; i < taskStatus.length; i++) {
                System.out.println(i + 1 + ": " + taskStatus[i]);
            }
            int statusChoice = scanner.nextInt();
            String updatedStatus = taskStatus[statusChoice - 1];
            taskList.set(index,
                    new Task(taskId, title, note, assignor, assignee, assignedOn, deadline, updatedStatus));
        }
        // scanner.close();
    }

    public void isTaskPresent() {

    }

    public void deleteTask(String userId) {
        Scanner scanner = new Scanner(System.in);
        displayAllTasks(userId);
        System.out.println("Enter the task id to be deleted:");
        int deleteId = scanner.nextInt();
        boolean isPresent = false;
        System.out.println("Entered Id: " + deleteId);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("Inside for loop: " + i);
            int taskId = taskList.get(i).taskId;
            if (taskId == deleteId) {
                isPresent = true;
                System.out.println("Task with the corresponding is found.");
                break;
            }
        }
        if (isPresent == false) {
            System.out.println("Task with the corresponding id not found.");
            return;
        } else {
            taskList.remove(deleteId - 1);
            System.out.println("Task deleted");
            id--;
        }
    }

    public String setDateTime() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String date = "";
        String time = "";
        try {
            System.out.print("Date: ");
            date = reader.readLine();
            System.out.print("Time: ");
            time = reader.readLine();
            return date + " at " + time;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return date + " at " + time;

    }
}