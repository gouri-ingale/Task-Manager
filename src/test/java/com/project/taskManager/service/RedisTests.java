package com.project.taskManager.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Disabled
    @Test
    void tesesamail(){
        redisTemplate.opsForValue().set("email", "demofalana00@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
      // Object salary = redisTemplate.opsForValue().get("salary");
        //System.out.println(salary);
       int a=1;
    }
}
