package com.qins.aspect;


import com.qins.model.Fish;
import com.qins.model.FishStatus;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
@Aspect
public class FishAspect {

//    public static final String POINT_CUT="execution (* com.qins.controller.FishController.doRpc(..))";


    public static Map<String, Fish> fishMap=new HashMap<>();
    static {
        fishMap.put("order-service",new Fish());
    }
    Random random=new Random();


    /**
     * 类比拦截器
     * 判断当前断路器的状态 决定发起是都调用
     * @param joinPoint
     * @return
     */
//    @SneakyThrows
    @Around(value = "@annotation(com.qins.anno.MyFish)")
    public Object fishAround(ProceedingJoinPoint joinPoint){
        Object result =null;

        Fish fish = fishMap.get("order-service");
        FishStatus status = fish.getStatus();
        switch (status){
            case CLOSE:
                //正常调用
                try {
                    Object proceed = joinPoint.proceed();
                    return result;
                } catch (Throwable e) {
//                    throw new RuntimeException(e);  //调用失败 记录次数
                    fish.addFailCount();
                    return "bei tai ";
                }
            case OPEN:
                //不能调用
                return "bei tai";
            case HALF_OPEN:
                //可以用少许流量调用
                int i = random.nextInt(5);
                System.out.println(i);
                if(i==1){
                    try {
                        result=joinPoint.proceed();
                        fish.setStatus(FishStatus.CLOSE);
                        synchronized (fish.getLock()){
                            fish.getLock().notifyAll();
                        }
                        return result;

                    } catch (Throwable e) {
                        return "bei tai";
                    }
                }
            default:
                return "bei tai";

        }
//        return "bei tai";

    }
}
