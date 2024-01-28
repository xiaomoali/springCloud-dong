package com.qins.controller;


import com.qins.domain.Order;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * url /doOrder/**** /add/
 * get
 * post
 */
@RestController
public class ParamController {

    @GetMapping("/testUrl/{name}/{age}")
    public String testUrl(@PathVariable("name") String name,@PathVariable("age") Integer age){

        System.out.println(name+"--"+age);
        return "ok,/testUrl/{name}/{age}";
    }

    @GetMapping("/oneParam")
    public String oneParam(@RequestParam(required = false) String name){

        System.out.println(name);
        return "ok,/oneParam";
    }

    @GetMapping("/twoParam")
    public String twoParam(@RequestParam(required = false) String name,@RequestParam(required = false) Integer age){

        System.out.println(name+"--"+age);
        return "ok,/twoParam";
    }

    @PostMapping("/oneObj")
    public String oneObj(@RequestBody Order order){

        System.out.println(order);
        return "ok,/oneObj";
    }

    @PostMapping("/oneObjOneParam")
    public String oneObjOneParam(@RequestBody Order order,@RequestParam("name") String name){

        System.out.println(order);
        System.out.println("name"+name);
        return "ok,/oneObj";
    }

    @GetMapping("/time")
    public String testTime(@RequestParam Date date){
        System.out.println(date);
        return "ok, /time";
    }

}
