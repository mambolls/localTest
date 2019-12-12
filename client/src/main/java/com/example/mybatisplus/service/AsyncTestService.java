package com.example.mybatisplus.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName AsyncTestService
 * @Description 异步方法测试
 * @Author lilinsong
 * @Date 2019/12/12 11:43
 * @Version 1.0
 */

@Component
public class AsyncTestService {

    /**
     * @MethodName forTest
     * @Author lilinsong
     * @Description  异步方法
     * @Param []
     * @return void
     * @Date 2019/12/12 11:48
     **/
    @Async("AsyncTest")
    public void forTest() {
        for (int i = 0; i <= 100 ; i++){
            try {
                new Thread().sleep(2000);
                System.out.println(new Date());
            }catch (Exception e){}
        }
    }
}
