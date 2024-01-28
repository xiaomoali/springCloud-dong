package com.qins;

import com.qins.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class RestTemplateApplicationTests {

    @Test
    void contextLoads() {
        RestTemplate restTemplate=new RestTemplate();

        String url="https://www.baidu.com";
        String forObject = restTemplate.getForObject(url, String.class);

        System.out.println(forObject);
    }

    @Test
    void testGet(){

        RestTemplate restTemplate=new RestTemplate();
        String url="http://localhost:8088/GetTest?name=cxs";
        String forObject = restTemplate.getForObject(url, String.class);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        //HTTP协议规范
        //请求头 请求参数

        System.out.println(responseEntity);
    }

    @Test
    void testPost1(){
        RestTemplate restTemplate=new RestTemplate();
        String url ="http://localhost:8088/PostTest";
        User user=new User("qins",11, 100.0);

        String postForObject = restTemplate.postForObject(url, user, String.class);
        System.out.println(postForObject);


    }

    @Test
    void testPost2(){
        RestTemplate restTemplate=new RestTemplate();

        String url ="http://localhost:8088/PostTest2";
        LinkedMultiValueMap<Object, Object> map = new LinkedMultiValueMap<>();

        map.add("name","qins");
        map.add("age",29);
        map.add("price",12.0);

        String s = restTemplate.postForObject(url, map, String.class);
        System.out.println(s);

    }

}
