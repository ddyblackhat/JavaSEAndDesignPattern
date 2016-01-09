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

//1	�״�ִ�У�ƻ������android��
//2 ����Thread.sleep����,ƻ������android��
//3 ������ͨ��������,ƻ������hello?
//4 ������2���ֻ����ԣ�ƻ������android��
//5 ���ɾ�̬ͬ��������ͬһ���ֻ���ƻ������android��
//6 ���ɾ�̬ͬ��������2���ֻ���ƻ������android��
//7 һ����̬ͬ����������һ���Ǿ�̬ͬ��������ͬһ���ֻ���ƻ������android��
//8 һ����̬ͬ����������һ���Ǿ�̬ͬ��������2���ֻ���ƻ������android��