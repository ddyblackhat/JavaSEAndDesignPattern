package com.ddyblackhat.javase.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用读写锁模拟实现缓存系统,使用同步也是可以的
 * 
 * @author dudy
 */
public class CacheDemo {

	//private Map<String, Object> cache = new ConcurrentHashMap<String, Object>();
	private Map<String, Object> cache = new HashMap<String, Object>();
	volatile boolean cacheValid = false;

	ReadWriteLock rw = new ReentrantReadWriteLock();

	public Object get(String key) { // 存在 并发问题,数据可见性
		rw.readLock().lock();
		Object data = null;
		try {
			data = cache.get(key);
			if (!cacheValid) {
				// 这里要写入数据,所以要先释放掉读锁，上写锁
				rw.readLock().unlock();
				rw.writeLock().lock(); // ①
				try {
					if (!cacheValid) { // 再一次判断,因为可能多个线程同时进入执行到①
						data = new Random().nextInt(1000);
						cache.put(key, data);
						cacheValid = true;
						System.out.println("data has write : " + data);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					rw.writeLock().unlock();
				}
				rw.readLock().lock();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rw.readLock().unlock();
		}
		return data;
	}

	public Object getBad(String key) { // 存在 并发问题,数据可见性,data 是线程不可见的
		rw.readLock().lock();
		Object data = null;
		try {
			data = cache.get(key);
			if (data == null) {
				// 这里要写入数据,所以要先释放掉读锁，上写锁
				rw.readLock().unlock();
				rw.writeLock().lock(); // ①
				try {
					if (data == null) { // 再一次判断,因为可能多个线程同时进入执行到①
						data = new Random().nextInt(1000);
						cache.put(key, data);
						System.out.println("data has write : " + data);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					rw.writeLock().unlock();
				}
				rw.readLock().lock();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rw.readLock().unlock();
		}
		return data;
	}

	public synchronized Object get1(String key) {
		Object data = cache.get(key);
		if (data == null) {
			data = "AAA";
			cache.put(key, data);
			System.out.println("data has write : " + data);
		}
		return cache.get(key);
	}

	public static void main(String[] args) {
		final CacheDemo cache = new CacheDemo();

		for (int i = 0; i < 10000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// cache.get1("k");
					cache.get("key");
				}
			}).start();
		}
	}
	ArrayBlockingQueue
}
