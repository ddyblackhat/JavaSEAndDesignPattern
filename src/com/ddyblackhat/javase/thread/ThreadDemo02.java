package com.ddyblackhat.javase.thread;

/**
 * 线程实现：一个让count +1, 一个-1, 循环5次交替进行
 * 
 * @author dudy
 */

class ShareData {
	private int count = 0;

	public synchronized void increment() throws Exception {
		while (this.count != 0) {
			this.wait();
		}
		++count;
		System.out.println(Thread.currentThread().getName() + "--> " + count);
		this.notify();
	}

	public synchronized void decrement() throws Exception {
		while (this.count == 0) {
			this.wait();
		}
		--count;
		System.out.println(Thread.currentThread().getName() + "--> " + count);
		this.notify();
	}
}

public class ThreadDemo02 {

	public static void main(String[] args) {

		final ShareData data = new ShareData();

		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 5; i++) {

					try {
						data.increment();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}, "AA").start();
		
		
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {

					try {
						data.decrement();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}, "BB").start();
		
		
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {

					try {
						Thread.sleep(200);
						data.increment();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}, "CC").start();
		
		
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 5; i++) {

					try {
						Thread.sleep(500);
						data.decrement();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}, "DD").start();
	}

}
