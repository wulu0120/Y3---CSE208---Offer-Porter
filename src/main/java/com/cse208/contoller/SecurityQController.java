package com.cse208.contoller;

import com.cse208.Entity.SecurityQ;
import com.cse208.Entity.User;
import com.cse208.mapper.SecurityQMapper;
import com.cse208.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SecurityQController {
    @Autowired
    private SecurityQMapper securityQMapper;

    //获得全部用户
    @GetMapping("/getAllQuestion")
    public List<SecurityQ>  getAllQuestion(){
        List<SecurityQ> questions = securityQMapper.getAllQuestion();
        return questions;
    }

    //获取用户 by ID
    @GetMapping("/getQuestionById/{qusId}")
    public SecurityQ getQuestionById(@PathVariable("qusId") int qusId){
        SecurityQ securityQ=securityQMapper.getQuestionById(qusId);
        return securityQ;
    }

}
