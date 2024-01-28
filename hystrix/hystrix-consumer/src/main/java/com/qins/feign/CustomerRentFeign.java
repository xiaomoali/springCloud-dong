package com.qins.feign;


import com.qins.feign.hystrix.CustomerRentFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * 这里需要指定熔断类  fallback备选方案
 */
@FeignClient(value = "rent-car-service",fallback = CustomerRentFeignHystrix.class)
public interface CustomerRentFeign {



    @GetMapping("/rent")
    public String rent();

}
