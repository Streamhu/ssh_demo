package com.hh.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/10 10:24
 */
@Controller
public class TestController {

    @RequestMapping(value="/test")
    public void test(){
        System.out.println("springMVC配置成功");
    }
}
