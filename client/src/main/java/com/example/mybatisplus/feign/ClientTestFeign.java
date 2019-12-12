package com.example.mybatisplus.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;

/**
*@ClassName ClientTestFeign
*@Description TODO
*@Author lilinsong
*@Date 2019/12/10 11:20
*@Version 1.0
*/
@FeignClient(name = "mptest")
@RequestMapping("/output/user")
public interface ClientTestFeign {

    /**
     * @MethodName getUser
     * @Author lilinsong
     * @Description  TODO
     * @Param []
     * @return java.lang.String
     * @Date 2019/12/12 16:21
     **/
    @RequestMapping(value = "/getUsers",method = RequestMethod.POST)
    public String getUser();
}
