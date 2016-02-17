package com.ddyblackhat.javase.designPattern;

/**
 * 单例模式:饿汉式
 * 
 * @author dudy
 *
 */
public class Singleton {
	private static Singleton instance = null;

	private Singleton() {
		// private 的构造函数, 是保证这个类不能被其他类new出来的
	}

	private static Singleton getInstance01() {// 获得唯一一个实例化对象
		if (instance == null) {
			synchronized (instance) { // 运行报错: nullpointer
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}

		return instance;
	}

	// 在Java指令中创建对象和赋值操作是分开进行的，也就 是说instance = new Singleton();
	// 语句是分两步执行的。但是JVM并不保证这两个操作的先后顺序，也就是说有可能JVM会为新的Singleton实例分配空间，
	// 然后直接赋值给instance成员，然后再去初始化这个Singleton实例。
	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					// 其实还是可能会出问题
					instance = new Singleton();
				}
			}
		}
		return instance;

	}

	//---------使用内部类来维护单例的实现--------
	/**
	 * JVM 内部的机制能够保证党一个类被加载的时候，这个类的加载过程是线程互斥的。
	 * 这样当我们第一次调用getInstance的时候，JVM能够帮我们保证Instance只被创建一次
	 * 并且会保证把赋值给Instance的内存初始化完毕，这样我们就不用担心上面的问题。
	 * 同时该方法也只会在第一次调用的时候使用互斥机制，这样就解决了低性能问题。
	 */
	public static class SingletonFactory{
		private static Singleton instance = new Singleton();
	}
	
	public static Singleton getInstance02(){
		return SingletonFactory.instance;
	}
	//---------------
	public static void singletonInfo() {
		System.out.println("我就是 Singleton... ");
	}

	public static void main(String[] args) {
		Singleton ins = Singleton.getInstance();
		ins.singletonInfo();

		Singleton ins01 = Singleton.getInstance();
		ins01.singletonInfo();

		Singleton ins02 = Singleton.getInstance();
		ins02.singletonInfo();
	}

}
