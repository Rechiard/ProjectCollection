package com.example.service;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    //告诉Spring这是一个异步的方法,@Async注解会帮助我们开启线程池，如果需要开启异步功能需要在主程序中开启异步注解功能@EnableAsync
    //异步任务就是类似于邮件发送
    @Async
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据正在处理...");
    }

}
