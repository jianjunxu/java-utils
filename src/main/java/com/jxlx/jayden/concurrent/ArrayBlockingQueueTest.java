package com.jxlx.jayden.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * User : jianjun.xu
 * Date : 2016/10/21
 * Time : 19:34
 * Desc :
 * 阻塞队列有四种处理方式：
 * 1 抛出异常 add(e) remove()
 * 2 返回特殊值 offer(e) poll()
 * 3 一直阻塞 put(e) take()
 * 4 超时退出 offer(e,time,unit) poll(time,unit)
 *
 * 实现原理：TODO
 */
public class ArrayBlockingQueueTest {
	public static void main(String[] args) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
	}
}
