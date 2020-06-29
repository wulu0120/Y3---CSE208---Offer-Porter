package com.cse208.mapper;

import com.cse208.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//Mapper ==》表明这个为mybatis的mapper
@Mapper
@Repository
public interface UserMapper {

    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByName(String name);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int id);
    boolean setOneUser(User user);

    int updateAns(User user);
    String getAns(User user);
    String getQues(User user);

}
