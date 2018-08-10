package com.hh.test.controller;

import com.hh.test.entity.User;
import com.hh.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/10 11:06
 */
@Controller
public class MybatisTestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/test/mybatis/select")
    public void select(){
        List<User> userList = userService.select();
        for(User user : userList){
            System.out.println(user.toString());
        }
    }
}
