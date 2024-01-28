package com.qins.controller;


import com.qins.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/GetTest")
    public String get(String name){
        System.out.println(name);
        return "ok,GetTest";


    }


    @GetMapping("/PostTest")
    public String post(@RequestBody  User user){
        System.out.println(user);
        return "ok,PostTest";

    }

    @PostMapping("/PostTest2")
    public String post2(User user){
        System.out.println(user);
        return "ok,PostTest2";

    }
}
