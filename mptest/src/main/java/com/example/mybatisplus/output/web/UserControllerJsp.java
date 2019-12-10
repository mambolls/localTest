package com.example.mybatisplus.output.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.example.mybatisplus.output.entity.User;
import com.example.mybatisplus.output.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @ClassName UserControllerJsp
 * @Description 测试
 * @Author lilinsong
 * @Date 2019/12/9 9:15
 * @Version 1.0
 */
@Controller
public class UserControllerJsp {

    @Autowired
    private UserService service;

    @GetMapping(value = "/userList")
    public String userList() {
        Wrapper<User> wrapper = new EntityWrapper<>();
        List<User> users = this.service.selectList(wrapper);
        return "index";
    }
}
