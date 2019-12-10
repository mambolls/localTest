package com.example.mybatisplus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigServerApplication {
    private static final Logger logger = LoggerFactory.getLogger(ConfigServerApplication.class);

    public static void main(String[] args) {
        logger.info("\n\n" +
                "----------------------------------------------------------------\n" +
                "  " + " - 开始启动 ...\n" +
                "----------------------------------------------------------------\n");
        SpringApplication.run(ConfigServerApplication.class, args);
        logger.info("\n\n" +
                "----------------------------------------------------------------\n" +
                "  " + " - 启动成功! \n" +
                "----------------------------------------------------------------\n");
    }

}
