package com.qins.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 断路器的模型
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fish {

    private FishStatus status=FishStatus.CLOSE;  //当前断路器状态

    public static final Integer WINDOW_TIME=20;  //窗口时间

    public static final Integer MAX_FAIL_COUNT=3;  //最大失败次数

    private AtomicInteger currentFailCount=new AtomicInteger(0);//当前断路器失败次数  可以保证线程的安全

    /**
     * 线程池
     */
    private ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(
            4,
            8,
            20,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(2000),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );
    private  Object lock=new Object();

    {poolExecutor.execute(()->{
        while (true){
            try {
                TimeUnit.SECONDS.sleep(WINDOW_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (this.status.equals(FishStatus.CLOSE)){
                this.currentFailCount.set(0);
            }else {
                synchronized (lock){
                    try {
                        lock.wait();
                        System.out.println("awake");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            //断路器是开的 不会调用
        }

    });

    }

    /**
     * 记录失败次数
     */
    public void addFailCount(){
        int i = currentFailCount.incrementAndGet(); //++i
//        currentFailCount.getAndIncrement();   //i++
        if(i>=MAX_FAIL_COUNT){
            //修改当前状态
            this.setStatus(FishStatus.OPEN);

            poolExecutor.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(WINDOW_TIME);
                } catch(InterruptedException e){
                    throw new RuntimeException(e);
                }
                this.setStatus(FishStatus.HALF_OPEN);
                this.currentFailCount.set(0);
            });

        }
    }

}
