package com.qins;

import redis.clients.jedis.Jedis;

public class TestPing {

    public static void main(String[] args) {
        //new jedis对象即可
        Jedis jedis=new Jedis("127.0.0.1",6379);
        String money = jedis.get("money");
        System.out.println(money);
        System.out.println(jedis.ping());

    }
}
