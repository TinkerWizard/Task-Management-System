import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

class Auth {
    static HashMap<String, String> userAuth = new HashMap<>();// to store userId and password of assignor's and assignee's
    ArrayList<String> assigneeList = new ArrayList<>();//stores only assignee's userid

    Auth() {
        addUser();
        addAssignee();
    }

    public void addUser() {
        userAuth.put("NOR_001", "dummy1");
        userAuth.put("NOR_002", "dummy2");
        userAuth.put("NEE_001", "dummy3");
        userAuth.put("NEE_002", "dummy4");
    }

    public void addNewAssignee() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // assignee list size
        int alSize = assigneeList.size();
        String getSuffix[] = assigneeList.get(alSize - 1).split("_");
        int digits = Integer.parseInt(getSuffix[1]);
        int newDigit = digits + 001;
        String newDigitStr = String.format("%03d", newDigit);
        String newAssigneeUserId = "NEE_" + newDigitStr;

        System.out.println("New user id for the assignee created!");
        System.out.println("The new assignee's user id is: " + newAssigneeUserId);
        // get the password from the assignor
        try {
            System.out.println("Enter the password: ");
            String password = reader.readLine();
            userAuth.put(newAssigneeUserId, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //it triggers the addAssignee method after adding a new user is added to the userAuth map
        addAssignee();
    }
    public boolean checkCredentials(String userId, String password) {
        if (!userAuth.containsKey(userId)) {
            return false;
        }
        if (userAuth.get(userId) == password) {
            return true;
        }
        return true;
    }

    public String getType(String userId) {
        String userType[] = userId.split("_");
        return userType[0];
    }

    public void addAssignee() {
        for (String userId : userAuth.keySet()) {
            if (userId.split("_")[0].equals("NEE")) {
                if (!assigneeList.contains(userId)) {
                    assigneeList.add(userId);
                }
            }
        }
    }

    public void getAllAssignees() {
        int index = 1;
        System.out.println();
        System.out.println("All the assignee's are listed below");
        for (String assignee : assigneeList) {
            System.out.println(index + ": " + assignee);
            index++;
        }
        System.out.println();
    }
}