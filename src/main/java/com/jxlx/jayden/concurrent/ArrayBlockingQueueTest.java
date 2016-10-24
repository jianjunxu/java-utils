package com.jxlx.jayden.concurrent;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * User : jianjun.xu
 * Date : 2016/10/21
 * Time : 19:34
 * Desc :
 * 阻塞队列有四种处理方式：
 * 1 抛出异常 add(e)-内部调用offer remove()-内部调用poll
 * 2 返回特殊值 offer(e) poll()
 * 3 一直阻塞 put(e) take()
 * 4 超时退出 offer(e,time,unit) poll(time,unit)
 *
 * 实现原理：
 * final Object[] items;
 * final ReentrantLock lock;
 * private final Condition notEmpty;
 * private final Condition notFull;
 *
 * ArrayBlockingQueue:
 * 一个线程安全的、基于数组、有界的、阻塞的、FIFO 队列
 * http://ifeve.com/juc-arrayblockingqueue/
 */
public class ArrayBlockingQueueTest {
	public static void main(String[] args) {
		int capacity = 1000;
		List<String> eles = Lists.newArrayList("ArrayBlockingQueue");
		/**
		 * Constructors
		 * 1 默认情况不保证线程公平的访问队列
		 * 2 公平是指阻塞的队列按照阻塞的先后顺序访问队列/非公平指阻塞的线程都有争夺访问队列的资格
		 * 3 保证公平性会降低吞吐量
		 * 3 公平性是使用可重入锁实现 ReentrantLock
		 */
		BlockingQueue<String> queueUnFair = new ArrayBlockingQueue<String>(capacity);
		BlockingQueue<String> queueFair = new ArrayBlockingQueue<String>(capacity, true);
		BlockingQueue<String> queueWithElements = new ArrayBlockingQueue<String>(capacity, false, eles);
	}
}
