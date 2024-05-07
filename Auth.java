import java.util.ArrayList;
import java.util.HashMap;

class Auth {
    static HashMap<String, String> userAuth = new HashMap<>();
    ArrayList<String> assigneeList = new ArrayList<>();

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
            if(userId.split("_")[0].equals("NEE"))
            {
                assigneeList.add(userId);
            }
        }

    }

    public void getAllAssignees() {
        int index = 1;
        for (String assignee : assigneeList) {
            System.out.println(index + ": " + assignee);
            index++;
        }
    }
}