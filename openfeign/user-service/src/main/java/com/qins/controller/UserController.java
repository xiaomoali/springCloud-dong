package com.qins.controller;


import com.qins.domain.Order;
import com.qins.feign.UserOrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class UserController {


    @Autowired
    private UserOrderFeign userOrderFeign;


    /***
     * 浏览器 user-service(/userDoOrder)-----rpc(feign) ---order-service(/doOrder)
     * @return
     */
    @GetMapping("/userDoOrder")
    public String userDoOrder(){
        System.out.println("The user comes in");

        //需要发起远程调用
        String s=userOrderFeign.doOrder();
        //接口被创建出代理对象
        //动态代理 jdk(java interface 接口 proxy) cglib(subclass 子类)
        //jdk 都会走invoke方法
        return s;
    }

//    public static void main(String[] args) {
//        UserOrderFeign o =(UserOrderFeign) Proxy.newProxyInstance(UserController.class.getClassLoader(), new Class[]{UserController.class}, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println("come in");
//                return "haha";
//            }
//        });
//        String s = o.doOrder();
//        System.out.println(s);
//
//    }
    @GetMapping("testParam")
    public String testParam(){

        String cxs = userOrderFeign.testUrl("cxs", 19);
        System.out.println(cxs);
        String qins = userOrderFeign.oneParam("qins");
        System.out.println(qins);

        String qin = userOrderFeign.twoParam("qin", 11);
        System.out.println(qin);
        Order li = Order.builder()
                .price(22d)
                .time(new Date())
                .name("li")
                .id(1)
                .build();

        String s = userOrderFeign.oneObj(li);
        System.out.println(s);

        String wang = userOrderFeign.oneObjOneParam(li, "wang");
        System.out.println(wang);



        return "ok,testParam";


    }
    @GetMapping("/time")
    public String testTime(){

        Date date=new Date();
        System.out.println(date);
        ;
        userOrderFeign.testTime(date);
        LocalDate now=LocalDate.now();
        LocalDateTime now1=LocalDateTime.now();

        return "ok, time";
    }

}
