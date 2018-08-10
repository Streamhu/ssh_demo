package com.hh.test.service.impl;

import com.hh.test.dao.UserDao;
import com.hh.test.entity.User;
import com.hh.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/10 11:16
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> select() {
        return userDao.selectAll();
    }
}
