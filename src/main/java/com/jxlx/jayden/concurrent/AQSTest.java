package com.jxlx.jayden.concurrent;

/**
 * User : jianjun.xu
 * Date : 2016/10/24
 * Time : 15:43
 * Desc :
 *  AQS 是 java.util.concurrent.locks.AbstractQueuedSynchronizer 类的简称
 *  为实现依赖于先进先出 (FIFO) 等待队列的阻塞锁和相关同步器（信号量、事件，等等）提供一个框架，这些类同步器都依赖单个原子 int 值来表示状态
 *  AQS 实现了控制同步的框架，并定义抽象方法留给子类定义哪种状态意味着被获取或被释放，是个典型的模板方法实现。
 *
 *
 * AQS实现了模板方法，使用时具体实现的子类只需要根据需要实现下面的方法：
 * tryAcquire(int)  // 试图在独占模式下获取对象状态。此方法应该查询是否允许它在独占模式下获取对象状态，如果允许，则获取它。
 * tryRelease(int)  // 试图设置状态来反映独占模式下的一个释放。
 * tryAcquireShared(int)// 试图在共享模式下获取对象状态。此方法应该查询是否允许它在共享模式下获取对象状态，如果允许，则获取它。
 * tryReleaseShared(int)// 试图设置状态来反映共享模式下的一个释放。
 * isHeldExclusively()  // 如果对于当前（正调用的）线程，同步是以独占方式进行的，则返回 true。此方法是在每次调用非等待 AbstractQueuedSynchronizer.ConditionObject 方法时调用的。（等待方法则调用 release(int)。）
 *
 */
public class AQSTest {
	public static void main(String[] args) {
	}
}
