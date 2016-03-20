package com.ddyblackhat.javase.thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁测试
 * 读与读不互斥， 读与写互斥，写与写互斥
 * @author dudy
 *
 */
public class ReadWriteLockTest {

	public static void main(String[] args) {
		final Queque3 q = new Queque3();

		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					q.get();
				}
			}).start();

			new Thread(new Runnable() {
				@Override
				public void run() {
					q.set(new Random().nextInt(100));
				}
			}).start();
		}

//		Thread-0 be ready to read data!
//		Thread-0 have get the data :0
//		Thread-1 be ready to set data
//		Thread-1 have set the data :46
//		Thread-3 be ready to set data
//		Thread-3 have set the data :92
//		Thread-5 be ready to set data
//		Thread-5 have set the data :60
//		Thread-4 be ready to read data!
//		Thread-2 be ready to read data!
//		Thread-2 have get the data :60
//		Thread-4 have get the data :60

	}

}

// 操作的资源对象类
class Queque3 {

	private int data;
    // 获取读写锁对象
	ReadWriteLock rw = new ReentrantReadWriteLock();
	public int get() {
		try {
			// 读锁
			rw.readLock().lock();
			System.out.println(Thread.currentThread().getName() + " be ready to read data!");
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " have get the data :" + data);
			return this.data;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rw.readLock().unlock();
		}
		return 0;
	}

	public void set(int data) {
		try {
			// 写锁
			rw.writeLock().lock();
			System.out.println(Thread.currentThread().getName() + " be ready to set data");
			Thread.sleep(1000);
			this.data = data;
			System.out.println(Thread.currentThread().getName() + " have set the data :" + data);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rw.writeLock().unlock();
		}
	}

}
