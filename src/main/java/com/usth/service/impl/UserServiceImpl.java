package com.usth.service.impl;

import com.usth.dao.UserDao;
import com.usth.entity.User;
import com.usth.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        List<User> list = userDao.selectAll();
        return list;
    }

    @Override
    public int addOne(User user) {
        int num = userDao.insertOne(user);
        return num;
    }

    @Override
    public User findById(Integer userid) {
        User user = userDao.selectById(userid);
        return user;
    }

    @Override
    public int updateOne(User user) {
        int num = userDao.updateOne(user);
        return num;
    }

    @Override
    public int deleteOne(Integer userid) {
        int num = userDao.deleteOne(userid);
        return num;
    }

    @Override
    public List<User> findOne(String k, String value) {
        List<User> list = userDao.selectOne(k,value);
        return list;
    }
}
