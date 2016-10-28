package com.jxlx.jayden.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jianjun.xu
 * @description
 * @email jianjun.xu@vipshop.com
 * @date 16/8/23 下午10:51
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args){
        final BlockingQueue queue = new ArrayBlockingQueue(2);
        ExecutorService es = new ThreadPoolExecutor(1,2,10, TimeUnit.SECONDS, queue,
                new ThreadFactoryBuilder().setNameFormat("MY-THREAD-%d")
                        .setDaemon(true)
                        .build(),
                new RejectedExecutionHandler(){

                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("Task " + r.toString() +
                                " rejected from " +
                                executor.toString());
                    }
                });
        for (int i=0;i<100;i++){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("---run---"+Thread.currentThread()+"----queue size:"+queue.size());
                }
            });
        }
    }
}
