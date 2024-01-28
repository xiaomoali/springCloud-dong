package com.qins.controller;

import lombok.SneakyThrows;
import org.omg.CORBA.TIMEOUT;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
public class OrderController {


    @SneakyThrows
    @GetMapping("/doOrder")
    public String doOrder(){
        TimeUnit.SECONDS.sleep(2);

        return "doOrder ;";

    }
}
