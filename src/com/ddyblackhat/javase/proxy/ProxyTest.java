package com.ddyblackhat.javase.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Subject {
	public void doSomething();
}

interface Say {
	public void saySomething();
}

class RealSubject implements Subject, Say {

	@Override
	public void doSomething() {
		System.out.println("call doSomething()");
	}

	@Override
	public void saySomething() {
		// TODO Auto-generated method stub
		System.out.println("say something");
	}

}

// class SubjectProxy implements Subject{// ��̬����
//
// Subject subimpl = new RealSubject();
// @Override
// public void doSomething() {
// subimpl.doSomething();
// }
// @Override
// public void saySomething() {
// // TODO Auto-generated method stub
//
// }
//
// }

class ProxyHandler { // ��̬����
	private Object tar; // Ҫ����Ķ���

	public ProxyHandler(Object tar) {
		this.tar = tar;
	}

	public Object getProxy() {
		if (tar == null)
			throw new RuntimeException("tar is null");

		// 1. ׼��Ŀ�������������
		// �ٻ�ȡĿ������Class�����
		Class<? extends Object> tarClass = this.tar.getClass();
		// �ڻ�ȡ�����������
		ClassLoader loader = tarClass.getClassLoader();

		// 2. ׼��Ŀ�������ʵ�ֵĽӿ����͵�����
		Class<?>[] interfaces = tarClass.getInterfaces();

		// 3. ׼��InvocationHandler����
		 InvocationHandler h = new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				String className = tar.getClass().getName();
				String methodName = method.getName();
				Object result = null;
				try {
					System.out.println("[��־][" + className + "]" + methodName + "��ʼ...");
					result = method.invoke(tar, args);
					System.out.println("[��־][" + className + "]" + methodName + "��������...");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("[��־][" + className + "]" + methodName + "�쳣����...");
					e.printStackTrace();
				} finally {
					System.out.println("[��־][" + className + "]" + methodName + "���ս���...");
				}

				return result;
			}
		};

		Object o = Proxy.newProxyInstance(loader, interfaces, h);
		return o;
	}

}

public class ProxyTest {

	public static void main(String[] args) {
		// Subject sub = new SubjectProxy();
		// sub.doSomething();

		ProxyHandler proxy = new ProxyHandler(new RealSubject());
		Subject rs = (Subject) proxy.getProxy();
		Say say = (Say) proxy.getProxy();

		rs.doSomething();
		say.saySomething();
	}
}
