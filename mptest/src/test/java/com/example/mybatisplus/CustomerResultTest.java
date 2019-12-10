package com.example.mybatisplus;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.example.mybatisplus.output.entity.User;
import com.example.mybatisplus.output.service.UserService;
import com.example.mybatisplus.utils.rst.CustomerResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName CustomerResultTest
 * @Description TODO
 * @Author lilinsong
 * @Date 2019/12/9 11:57
 * @Version 1.0
 */
@SpringBootTest
@RunWith(value = SpringRunner.class)
public class CustomerResultTest {

    @Autowired
    private UserService service;

    @Test
    public void customerResultTest() {
        Wrapper<User> wrapper = new EntityWrapper<>();
        List<User> users = this.service.selectList(wrapper);
        System.out.println(CustomerResult.newSuccess());
        System.out.println(CustomerResult.newFailure("-1", "成功！"));
        System.out.println(CustomerResult.newSuccess(users));
        System.out.println(CustomerResult.newFailure("错误"));
        System.out.println(getUsers());
        System.out.println(getUsers().isSuccess());
        System.out.println(getUsers().hasData());
        System.out.println(CustomerResult.newSuccess().hasData());
    }

    public CustomerResult<User> getUsers() {
        Wrapper<User> wrapper = new EntityWrapper<>();
        List<User> users = this.service.selectList(wrapper);
        return CustomerResult.newSuccess(users);
    }
}
