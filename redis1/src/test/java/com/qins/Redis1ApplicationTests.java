package com.qins;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis1ApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();  //redis的连接对象
        redisTemplate.opsForValue().set("mykey","qins");
        redisTemplate.opsForValue().get("mykey");

    }

}
