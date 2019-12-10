package com.example.mybatisplus.controller;

import com.example.mybatisplus.feign.ClientTestFeign;
import com.kingxunlian.common.XLBaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/getUser")
    public XLBaseResponse<String> getUSer(){
        return XLBaseResponse.newInstance(this.clientTestFeign.getUser());
    }

    @Value("${hello}")
    private String hello;

    @GetMapping("/test")
    public String test() {
        return hello;
    }
}
