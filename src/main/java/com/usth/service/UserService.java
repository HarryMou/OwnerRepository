package com.usth.service;

import com.usth.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    List<User> findAll();
    int addOne(User user);
    User findById(Integer userid);
    int updateOne(User user);
    int deleteOne(Integer userid);
    List<User> findOne(String k,String value);
}
