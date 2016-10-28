package com.jxlx.jayden.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * User : jianjun.xu
 * Date : 2016/10/24
 * Time : 11:49
 * Desc :
 *
 * 实现原理：
 * Linked list node class
 * private final ReentrantLock takeLock = new ReentrantLock();
 * private final Condition notEmpty = takeLock.newCondition();
 * private final ReentrantLock putLock = new ReentrantLock();
 * private final Condition notFull = putLock.newCondition();
 *
 * LinkedBlockingQueue:
 * 基于单向链表的、范围任意的（其实是有界的）、FIFO 阻塞队列
 * 访问与移除操作是在队头进行，添加操作是在队尾进行
 * 队列是否为空、是否已满仍然是通过元素数量的计数器（count）进行判断的，由于可以同时在队头、队尾并发地进行访问、添加操作，所以这个计数器必须是线程安全的，
 * 		这里使用了一个原子类 AtomicInteger, 这就决定了它的容量范围是： 1 – Integer.MAX_VALUE
 * http://ifeve.com/juc-linkedblockingqueue/
 */
public class LinkedBlockingQueueTest {
	public static void main(String[] args){
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	}
}
