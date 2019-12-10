package com.example.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.example.mybatisplus.output.mapper")
@ServletComponentScan
@EnableEurekaClient
public class MybatisplusApplication {
    private static final Logger logger = LoggerFactory.getLogger(MybatisplusApplication.class);

    public static void main(String[] args) {
        logger.info("\n\n" +
                "----------------------------------------------------------------\n" +
                "  " + " - 开始启动 ...\n" +
                "----------------------------------------------------------------\n");
        SpringApplication.run(MybatisplusApplication.class, args);
        logger.info("\n\n" +
                "----------------------------------------------------------------\n" +
                "  " + " - 启动成功! \n" +
                "----------------------------------------------------------------\n");
    }

}
