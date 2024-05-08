import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String userId;
        String password;
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Task taskObj = new Task();
        Auth authObj = new Auth();
        boolean loginLoop = true;
        while (loginLoop == true) {
            System.out.println("Enter your choice: \n1. Login \n2. Exit");
            int loginChoice = scanner.nextInt();
            switch (loginChoice) {
                case 1:
                    try {
                        System.out.println("Enter your userId:");
                        userId = reader.readLine();
                        // userId = "NOR_001";
                        System.out.println("Enter your password: ");
                        password = reader.readLine();
                        // password = "dummy1";
                        boolean isValid = authObj.checkCredentials(userId, password);
                        if (isValid) {
                            System.out.println("User credentials valid! \n You're logged in");
                            boolean taskLoop = true;
                            while (taskLoop == true) {
                                String userType = authObj.getType(userId);

                                // for the assignor

                                if (userType.equals("NOR")) {
                                    // assign task, display tasks assigned by the assignor, display task using the
                                    // task id, delete a task, update a task, exit
                                    System.out.println("Enter your choice:");
                                    System.out.println(
                                            "1. Assign Task \n2. Display all the tasks assigned by you \n3. Display a task \n4. Update Task \n5. Delete a task \n6. Add an assignee \n7.Display all assignee's \n8. Exit");
                                    System.out.println();
                                    try {
                                        int assignorChoice = scanner.nextInt();
                                        switch (assignorChoice) {
                                            case 1:
                                                authObj.getAllAssignees();
                                                System.out.println("Choose an assignee from the above");
                                                int assigneeChoice = scanner.nextInt();
                                                String chosenAssignee = authObj.assigneeList.get(assigneeChoice - 1);
                                                taskObj.assignTask(userId, chosenAssignee);
                                                break;
                                            case 2:
                                                taskObj.displayAllTasks(userId);
                                                break;
                                            case 3:
                                                System.out.println("Enter the task id to be displayed: ");
                                                int displayTaskId = scanner.nextInt();
                                                taskObj.displayTask(displayTaskId);
                                                break;
                                            case 4:
                                                taskObj.updateTask(userId);
                                                break;
                                            case 5:
                                                taskObj.deleteTask(userId);
                                                break;
                                            case 6:
                                                authObj.addNewAssignee();
                                                break;
                                            case 7:
                                                authObj.getAllAssignees();
                                                break;
                                            case 8:
                                                taskLoop = false;
                                                break;
                                            default:
                                                System.out.println("Enter a valid choice from the above");
                                                break;
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                // for the assignee

                                if (userType.equals("NEE")) {
                                    // display all tasks assigned to the assignee, display task using the task id,
                                    // update status, exit
                                    System.out.println("Enter your choice:");
                                    System.out.println(
                                            "1. Display all the tasks assigned to you \n2. Display a task  \n3. Update Status \n4. Exit");
                                    System.out.println();
                                    try {
                                        int assigneeChoice = scanner.nextInt();
                                        switch (assigneeChoice) {
                                            case 1:
                                                taskObj.displayAllTasks(userId);
                                                break;
                                            case 2:
                                                System.out.println("Enter the task id to be displayed: ");
                                                int displayTaskId = scanner.nextInt();
                                                taskObj.displayTask(displayTaskId);
                                                break;
                                            case 3:
                                                taskObj.updateTask(userId);
                                                break;
                                            case 4:
                                                taskLoop = false;
                                                break;
                                            default:
                                                System.out.println("Enter a valid choice from the above");
                                                break;
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            System.out.println("User credentials invalid, try again");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    loginLoop = false;
                    System.out.println("-----Logging out-----");
                    break;
                default:
                    System.out.println("Enter a valid choice from the above.");
                    break;
            }

        }
        scanner.close();
    }
}
