package com.qins.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.qins.domain.Order;

import java.util.Date;

@FeignClient(value = "order-service")  //提供者的应用名称
public interface UserOrderFeign {


    @GetMapping("/doOrder") //方法签名 包含方法的所有属性
    public String doOrder();



    @GetMapping("/testUrl/{name}/{age}")
    public String testUrl(@PathVariable("name") String name, @PathVariable("age") Integer age);

    @GetMapping("/oneParam")
    public String oneParam(@RequestParam(required = false) String name);

    @GetMapping("/twoParam")
    public String twoParam(@RequestParam(required = false) String name,@RequestParam(required = false) Integer age);

    @PostMapping("/oneObj")
    public String oneObj(@RequestBody Order order);

    @PostMapping("/oneObjOneParam")
    public String oneObjOneParam(@RequestBody Order order,@RequestParam("name") String name);

    @GetMapping("/time")
    public String testTime(@RequestParam Date date);



}
