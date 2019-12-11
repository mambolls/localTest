package com.example.mybatisplus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.example.mybatisplus.client.entity.User;
import com.example.mybatisplus.client.service.UserService;
import com.example.mybatisplus.feign.ClientTestFeign;
import com.kingxunlian.common.XLBaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName ClientTestController
 * @Description TODO
 * @Author lilinsong
 * @Date 2019/12/10 11:22
 * @Version 1.0
 */
@RequestMapping("/client")
@RestController
public class ClientTestController {

    @Autowired
    private ClientTestFeign clientTestFeign;

    @Autowired
    private UserService service;

    @RequestMapping("/getUser")
    public XLBaseResponse<String> getUSer(){
        return XLBaseResponse.newInstance(this.clientTestFeign.getUser());
    }

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.type}")
    private String type;
    @Value("${spring.datasource.url}")
    private String url;

    @GetMapping("/test")
    public String test() {
        StringBuilder builder = new StringBuilder();
        builder.append("driver-class-name:"+driverClassName+";");
        builder.append("password:"+password+";");
        builder.append("username:"+username+";");
        builder.append("type:"+type+";");
        builder.append("url:"+url);
        return builder.toString();
    }

    @RequestMapping("/selectUsers")
    public XLBaseResponse<List<User>> selectUsers(){
        Wrapper<User> wrapper = new EntityWrapper<>();
        List<User> users = this.service.selectList(wrapper);
        return XLBaseResponse.newInstance(users);
    }

}
