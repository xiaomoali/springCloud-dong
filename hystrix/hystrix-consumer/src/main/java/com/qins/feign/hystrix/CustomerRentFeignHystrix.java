package com.qins.feign.hystrix;


import com.qins.feign.CustomerRentFeign;
import org.springframework.stereotype.Component;

@Component   //ioc容器
public class CustomerRentFeignHystrix implements CustomerRentFeign {

    /**
     * 备选方案
     * @return
     */
    @Override
    public String rent() {
        return "bei xuan";
    }
}
