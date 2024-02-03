package com.qins.feign.hystrix;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qins.feign.CustomerRentFeign;
import org.springframework.stereotype.Component;

@Component   //ioc容器
public class CustomerRentFeignHystrix implements CustomerRentFeign {

    /**
     * 备选方案
     * @return
     */
    @Override
//    @HystrixCommand
    public String rent() {
        return "bei xuan";
    }
}
