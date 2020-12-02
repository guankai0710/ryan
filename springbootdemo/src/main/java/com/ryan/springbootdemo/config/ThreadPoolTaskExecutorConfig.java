package com.ryan.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author: guan.kai
 * @date: 2019/11/25 9:40
 **/
@Configuration
@EnableAsync
public class ThreadPoolTaskExecutorConfig {

    /**
     * 线程名称前缀
     */
    private static final String THREAD_NAME_PREFIX = "TEST-DEMO-";

    /**
     * 核心线程数为 5
     */
    private static final int CORE_POOL_SIZE = 5;

    /**
     * 最大线程数 10
     */
    private static final int MAX_POOL_SIZE = 10;

    /**
     * 任务队列容量为 100
     */
    private static final int QUEUE_CAPACITY = 100;

    /**
     * 等待时间为 300 秒
     */
    private static final int KEEP_ALIVE_TIME = 300;

    /**
     * 注入连接池实例
     * @return
     */
    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setThreadNamePrefix(THREAD_NAME_PREFIX);
        pool.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        pool.setCorePoolSize(CORE_POOL_SIZE);
        pool.setMaxPoolSize(MAX_POOL_SIZE);
        pool.setQueueCapacity(QUEUE_CAPACITY);
        //队列满，线程被拒绝执行策略
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //初始化
        pool.initialize();
        return pool;
    }
}
