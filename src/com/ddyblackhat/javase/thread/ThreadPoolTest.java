package com.ddyblackhat.javase.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池（执行器）
 * 
 * @author dudy
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
		 ExecutorService threadPool = Executors.newFixedThreadPool(3);
		 //ExecutorService threadPool = Executors.newCachedThreadPool();
		//ExecutorService threadPool = Executors.newSingleThreadExecutor();// 始终一个线程,当线程死掉后会又新启动一个。
		
		for (int i = 0; i < 10; i++) {

			final int task = i;
			threadPool.execute(new Runnable() {

				@Override
				public void run() {

					System.out.println(Thread.currentThread().getName() + " for task of " + task);
				}
			});
		}

		System.out.println("all of the 10 task commit");

		/**
		 * 定时执行器: 
		 * initDelay: 第一次多长时间执行，
		 * period:    之后每隔多长时间执行一次
		 */
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("boming...");
			}
		}, 10,3,TimeUnit.SECONDS);
	}

}
