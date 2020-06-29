package com.cse208.mapper;

import com.cse208.Entity.SecurityQ;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SecurityQMapper {

    List<SecurityQ> getAllQuestion();
    SecurityQ getQuestionById(int qusId);
    int addQuestion(String question);
}
