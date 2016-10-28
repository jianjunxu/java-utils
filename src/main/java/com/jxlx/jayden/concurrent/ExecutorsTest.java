package com.jxlx.jayden.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User : jianjun.xu
 * Date : 2016/10/21
 * Time : 15:24
 * Desc :
 */
public class ExecutorsTest {
	public static void main(String[] args){
		ExecutorService service1 = Executors.newFixedThreadPool(1);
		ExecutorService service2 = Executors.newCachedThreadPool();
		ExecutorService service3 = Executors.newScheduledThreadPool(1);
		ExecutorService service4 = Executors.newSingleThreadExecutor();
		ExecutorService service5 = Executors.newSingleThreadScheduledExecutor();
	}
}
