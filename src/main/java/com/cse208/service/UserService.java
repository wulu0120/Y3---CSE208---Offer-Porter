package com.cse208.service;

import com.cse208.Entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByName(String name);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int id);
    boolean setOneUser(User user);

    //securityQ
    int updateAns(User user);
    String getAns(User user);
    String getQues(User user);
    int addQuestion(String quest);

    //uploadOffer

}
