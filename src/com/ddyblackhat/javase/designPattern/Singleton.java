package com.ddyblackhat.javase.designPattern;

/**
 * ����ģʽ:����ʽ
 * 
 * @author dudy
 *
 */
public class Singleton {
	private static Singleton instance = null;

	private Singleton() {
		// private �Ĺ��캯��, �Ǳ�֤����಻�ܱ�������new������
	}

	private static Singleton getInstance01() {// ���Ψһһ��ʵ��������
		if (instance == null) {
			synchronized (instance) { // ���б���: nullpointer
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}

		return instance;
	}

	// ��Javaָ���д�������͸�ֵ�����Ƿֿ����еģ�Ҳ�� ��˵instance = new Singleton();
	// ����Ƿ�����ִ�еġ�����JVM������֤�������������Ⱥ�˳��Ҳ����˵�п���JVM��Ϊ�µ�Singletonʵ������ռ䣬
	// Ȼ��ֱ�Ӹ�ֵ��instance��Ա��Ȼ����ȥ��ʼ�����Singletonʵ����
	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					// ��ʵ���ǿ��ܻ������
					instance = new Singleton();
				}
			}
		}
		return instance;

	}

	//---------ʹ���ڲ�����ά��������ʵ��--------
	/**
	 * JVM �ڲ��Ļ����ܹ���֤��һ���౻���ص�ʱ�������ļ��ع������̻߳���ġ�
	 * ���������ǵ�һ�ε���getInstance��ʱ��JVM�ܹ������Ǳ�֤Instanceֻ������һ��
	 * ���һᱣ֤�Ѹ�ֵ��Instance���ڴ��ʼ����ϣ��������ǾͲ��õ�����������⡣
	 * ͬʱ�÷���Ҳֻ���ڵ�һ�ε��õ�ʱ��ʹ�û�����ƣ������ͽ���˵��������⡣
	 */
	public static class SingletonFactory{
		private static Singleton instance = new Singleton();
	}
	
	public static Singleton getInstance02(){
		return SingletonFactory.instance;
	}
	//---------------
	public static void singletonInfo() {
		System.out.println("�Ҿ��� Singleton... ");
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
