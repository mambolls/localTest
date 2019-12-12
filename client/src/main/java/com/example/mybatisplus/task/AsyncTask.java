package com.example.mybatisplus.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 注意：该线程池被所有的异步任务共享，而不属于某一个异步任务
 * 描述：配置异步任务的线程池
 *
 * @author Yanni
 */
@EnableAsync
@Configuration
public class AsyncTask {
    
    /**
     * 线程池维护线程的最小数量. .
     */
    private final int corePoolSize = 10;
    /**
     * 线程池维护线程的最大数量. .
     */
    private final int maxPoolSize = 200;
    /**
     * 队列最大长度.
     */
    private final int queueCapacity = 500;
    /**
     * 保持时间.
     */
    private final int keepAliveSeconds = 120000;
    
    
    /**
     * @MethodName AsyncTestExecutor
     * @Author lilinsong
     * @Description  异步任务测试
     * @Param []
     * @return java.util.concurrent.Executor
     * @Date 2019/12/12 11:41
     **/
    @Bean(name = "AsyncTest")
    public Executor AsyncTestExecutor() {
        return this.buildExecutor("AsyncTestExecutor");
    }
    


    private ThreadPoolTaskExecutor buildExecutor(String logPrefix) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(this.maxPoolSize);
        executor.setCorePoolSize(this.corePoolSize);
        executor.setThreadNamePrefix(logPrefix + "-");
        executor.setQueueCapacity(this.queueCapacity);
        executor.setKeepAliveSeconds(this.keepAliveSeconds);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
    
    
}