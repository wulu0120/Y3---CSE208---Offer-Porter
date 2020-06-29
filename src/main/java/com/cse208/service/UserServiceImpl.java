package com.cse208.service;

import com.cse208.Entity.SecurityQ;
import com.cse208.Entity.User;
import com.cse208.mapper.SecurityQMapper;
import com.cse208.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Autowired
    SecurityQMapper securityQMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public int addUser(User user) { return userMapper.addUser(user);  }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public boolean setOneUser(User user) {return userMapper.setOneUser(user); }

    @Override
    public int updateAns(User user) {
        return userMapper.updateAns(user);
    }

    @Override
    public String getAns(User user) {
        return userMapper.getAns(user);
    }

    @Override
    public String getQues(User user){ return userMapper.getQues(user); }

    @Override
    public int addQuestion(String quest){ return securityQMapper.addQuestion(quest); }
}
