package com.wen.token.config;


import com.wen.token.repository.RedisHandle;
import com.wen.token.util.BeanUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ApplicationEventListener implements ApplicationListener<ApplicationReadyEvent> {
    @Value("user.name")
    private String username;
    @Value("user.pass")
    private String password;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        RedisHandle redis= BeanUtil.getBean("redisHandle");
        redis.set(username,password);
        System.out.println("LoginBean");
    }
}

