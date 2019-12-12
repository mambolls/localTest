package com.example.mybatisplus;

import com.kingxunlian.heaven.annotation.EnableConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.example.mybatisplus.client.mapper")
public class ClientTestApplication {
    private static final Logger logger = LoggerFactory.getLogger(ClientTestApplication.class);

    public static void main(String[] args) {
        logger.info("\n\n" +
                "----------------------------------------------------------------\n" +
                "  " + " - 开始启动 ...\n" +
                "----------------------------------------------------------------\n");
        SpringApplication.run(ClientTestApplication.class, args);
        logger.info("\n\n" +
                "----------------------------------------------------------------\n" +
                "  " + " - 启动成功! \n" +
                "----------------------------------------------------------------\n");
    }

}
