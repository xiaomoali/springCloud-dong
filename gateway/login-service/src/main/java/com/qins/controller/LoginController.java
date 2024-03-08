package com.qins.controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

public class LoginController {


    @GetMapping("/doLogin")
    public String doLogin(String name,String pwd){

        System.out.println(name);
        System.out.println(pwd);

        String token = UUID.randomUUID().toString();
        return token+"d";
    }
}
