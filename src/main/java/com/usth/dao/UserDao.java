package com.usth.dao;

import com.usth.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userDao")
public interface UserDao {
    List<User> selectAll();
    int insertOne(User user);
    User selectById(Integer userid);
    int updateOne(User user);
    int deleteOne(Integer userid);
    List<User> selectOne(@Param("k")String k,@Param("v")String value);
}
