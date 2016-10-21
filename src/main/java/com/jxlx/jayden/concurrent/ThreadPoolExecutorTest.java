package com.jxlx.jayden.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * User : jianjun.xu
 * Date : 2016/10/21
 * Time : 14:56
 * Desc :
 * 提交一个新任务到线程池时，线程池处理流程如下：
 * 1）线程池判断核心线程池里的线程是否都在执行任务。如果不是，则创建一个新的工作线程来执行任务。如果核心线程池里的线程都在执行任务，则进入下个流程。
 * 2）线程池判断任务队列是否已满。如果工作队列没满，则将新提交的任务存储在这个工作队列中。如果满了，则进入下个流程。
 * 3）线程池判断线程池的线程池的线程是否都处于工作状态。如果没有，则创建一个新的工作线程来执行任务。如果已经满了，则交给饱和策略来处理这个任务
 * <p>
 * ** 核心线程池满了以后，将新任务放在任务队列而不是创建新的线程处理是为了避免获取全局锁。
 */
public class ThreadPoolExecutorTest {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		/**
		 * 核心线程数
		 * 即使有空闲线程也会重新创建，知道任务数大于核心线程数
		 */
		int corePoolSize = 1;
		/**
		 * 最大线程数
		 * 使用无界的任务队列，这个参数就没用了
		 */
		int maximumPoolSize = 2;
		/**
		 * 线程池中超过corePoolSize数目的空闲线程最大存活时间
		 * 如果任务多，且每个任务执行时间比较短，可以调大时间，提高线程利用率
		 */
		long keepAliveTime = 1;
		/** 存活时间单位 */
		TimeUnit unit = TimeUnit.MINUTES;
		/**
		 * 阻塞任务队列
		 * ArrayBlockingQueue 基于数组结构，FIFO有界队列
		 * LinkedBlockingQueue 基于链表 FIFO无界队列
		 * SynchronousQueue 不存储元素，每个插入操作必须等到另一个线程调用移除操作，否则一直阻塞
		 * PriorityQueue 有优先级的无界阻塞队列
		 */
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
		/**
		 * 新建线程工厂
		 * 使用guava提供的ThreadFactoryBuilder可以给线程池里的线程设置有意义的名字
		 */
		ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("MY-task-%d").build();
		/**
		 * 拒绝策略 当提交任务数超过maximumPoolSize+workQueue之和时，任务会交给RejectedExecutionHandler来处理
		 * AbortPolicy:直接抛出异常
		 * CallerRunsPolicy:只用调用者所在的线程来运行任务
		 * DiscardOldestPolicy:丢弃队列里最近的一个任务，并执行当前任务
		 * DiscardPolicy:不处理，丢弃
		 */
		RejectedExecutionHandler handler = new MyHandler();

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
		for (int i = 0; i < 10; i++) {
			threadPoolExecutor.submit(new MyRunnable());
		}
	}

	public static class MyHandler implements RejectedExecutionHandler {

		public MyHandler() {
		}

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			System.out.println("rejectedExecution");
		}
	}

	public static class MyRunnable implements Runnable {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "------MyRunnable------");
		}
	}
}
