package com.example.mybatisplus.task;

import com.example.mybatisplus.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @author lilinsong
 * @version V2.1 * Update Logs: * Name: * Date: * Description: 初始化
 * @ClassName: FileFechingTask
 * @Description: 南京银行oss文件定时拉取
 * @date 2019年7月4日 下午3:58:46
 */
@Configuration        // 相当于配置beans, 
@EnableScheduling    // <task:*>, 让spring进行任务调度
@Slf4j
public class TestTask {

    @Scheduled(cron = "0/5 * * * * ?")    // 5秒执行一次
    public void scheduler() {
        log.info(DateUtil.DAYTIME.format(new Date()));
    }

}