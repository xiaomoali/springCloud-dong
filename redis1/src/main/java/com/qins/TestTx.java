package com.qins;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.util.GuavaSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTx {

    public static void main(String[] args) {
        Jedis jedis=new Jedis("127.0.0.1",6379);


        Transaction multi = jedis.multi();
//        jedis.watch("name");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","world");
        jsonObject.put("qins","s");
        String s = jsonObject.toJSONString();

        jedis.watch(s);
        try {
            multi.set("user1",s);
            multi.set("user2",s);

//            int i=1/0;
            multi.exec();

        }catch (Exception e){
            multi.discard();

        }finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();
        }








    }
}
