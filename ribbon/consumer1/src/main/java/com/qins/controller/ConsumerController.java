package com.qins.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/ribbon")
    public  String  testRibbon(String serviceName){

        String forObject = restTemplate.getForObject("http://" + serviceName + "/hello",String.class);


//        RestTemplate template=new RestTemplate();
//        String object = template.getForObject("http://localhost:8888/aaa", String.class);

        //安全的轮询算法 加锁 效率低  CAS自旋锁 没有线程的等待和唤醒的开销   cmpxy  cmpxchg指令
        //CAS缺点会导致短暂时间内cpu飙升 还有aba问题
        ss();
        return forObject;
    }

    @GetMapping("/testRibbonRule")
    public String testRibbonRule(String serviceName){
        ServiceInstance choose = loadBalancerClient.choose(serviceName);
        return choose.toString();
    }
    void ss(){
        int i=0;
        String s= "s";
        int i1 = i == 0 ? 1 : 2;
        System.out.println("i1="+i1);

    }

    @GetMapping("/he")
    public  String he(){
        return  "ok";
    }
}












