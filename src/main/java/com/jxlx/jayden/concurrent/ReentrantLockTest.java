package com.jxlx.jayden.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * User : jianjun.xu
 * Date : 2016/10/27
 * Time : 14:47
 * Desc :
 * ** 公平锁和非公平锁内存语义：
 * * 公平锁和非公平锁释放锁时，最后都要写一个volatile变量state
 * * 公平锁获取锁时，首先会去读volatile变量
 * * 非公平锁获取时，首先会用CAS更新volatile变量，这个操作同时具有volatile读和写的内存语义
 */
public class ReentrantLockTest {
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		try {

		} finally {
			lock.unlock();
		}
	}
}
