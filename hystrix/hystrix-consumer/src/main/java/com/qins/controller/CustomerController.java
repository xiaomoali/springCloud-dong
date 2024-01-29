package com.qins.controller;


import com.qins.feign.CustomerRentFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CustomerController {


    @Autowired   //报错 容器中有多个对象
//    @Resource  //
//    @Qualifier("customerRentFeign")  //指定某一个对象
    private CustomerRentFeign customerRentFeign;


    @GetMapping("/customer")

    public String Customer(){

        System.out.println("customer");
        String rent = customerRentFeign.rent();

        return rent;
    }
}
