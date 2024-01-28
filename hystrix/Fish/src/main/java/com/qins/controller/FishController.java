package com.qins.controller;


import com.qins.anno.MyFish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FishController {


    @Autowired
    RestTemplate restTemplate;

    @MyFish
    @GetMapping("/doRpc")
    public String doRpc(){ //远程过程调用 rpc
        String forObject = restTemplate.getForObject("https://localhost:8095/abc", String.class);
        return forObject;
    }


}
