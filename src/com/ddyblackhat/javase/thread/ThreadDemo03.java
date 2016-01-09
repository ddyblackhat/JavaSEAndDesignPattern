package com.ddyblackhat.javase.thread;

class Phone
{
	public  synchronized void getIOS() throws InterruptedException
	{
		Thread.sleep(5000);
		System.out.println("------getIOS");
	}
	public synchronized void getAndroid() throws InterruptedException
	{
		System.out.println("------getAndroid");
	}	
	
	public void helloPhone() throws InterruptedException
	{
		System.out.println("------helloPhone");		
	}
}

public class ThreadDemo03 {
	public static void main(String[] args) {
		final Phone phone = new Phone();
		final Phone phone2 = new Phone();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					phone.getIOS();
				} catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		},"AA").start();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					//phone.getAndroid();
					//phone.helloPhone();
					phone.getAndroid();
				} catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		},"BB").start();	
	}
}

//1	首次执行，苹果还是android？
//2 新增Thread.sleep试试,苹果还是android？
//3 新增普通方法试试,苹果还是hello?
//4 新增第2部手机试试，苹果还是android？
//5 换成静态同步方法，同一部手机，苹果还是android？
//6 换成静态同步方法，2部手机，苹果还是android？
//7 一个静态同步方法，另一个非静态同步方法，同一部手机，苹果还是android？
//8 一个静态同步方法，另一个非静态同步方法，2部手机，苹果还是android？