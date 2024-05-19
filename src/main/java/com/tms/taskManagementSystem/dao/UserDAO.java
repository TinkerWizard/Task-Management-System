package com.tms.taskManagementSystem.dao;

import java.util.List;


import com.tms.taskManagementSystem.entity.User;
public interface UserDAO {
    //post methods
    User addAssignee();

    //get methods
    List<User> getAllUsers();
    User getUserById(String userId);
    List<User> getAssignees();

    //put methods
    User updateAssignee(String userId);

    //delete methods
    User deleteAssignee(String assigneeId);//accept only assignee id and process the deletion

    //other methods
    boolean checkCredentials(String userId, String password);
    String getUserType(String userId);
    String authentication(String userId, String password);
}
