package com.hh.test.dao;

import com.hh.test.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/10 11:10
 */
public interface UserDao {

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAll();
}
