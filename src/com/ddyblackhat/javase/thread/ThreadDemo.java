package com.ddyblackhat.javase.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author dudy
 */
class MyThread2 implements Runnable{

	@Override
	public void run() {
		System.out.println("implements Runable...");
	}
	
}

class MyThread extends Thread{
	
	@Override
	public void run() {
		System.out.println("extends Thread...");
	}
}

class MyThread3 implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		System.out.println("implements callable...");
		return 200;
	}
	
}


public class ThreadDemo {

	
	public static void main(String[] args) {
		new MyThread().start();
		new Thread(new MyThread2()).start();
		FutureTask task = new FutureTask<>(new MyThread3());
		new Thread(task).start();
	}
}
