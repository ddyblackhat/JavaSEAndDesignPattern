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

// class SubjectProxy implements Subject{// 静态代理
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

class ProxyHandler { // 动态代理
	private Object tar; // 要代理的对象

	public ProxyHandler(Object tar) {
		this.tar = tar;
	}

	public Object getProxy() {
		if (tar == null)
			throw new RuntimeException("tar is null");

		// 1. 准备目标对象的类加载器
		// ①获取目标对象的Class类对象
		Class<? extends Object> tarClass = this.tar.getClass();
		// ②获取类加载器对象
		ClassLoader loader = tarClass.getClassLoader();

		// 2. 准备目标对象所实现的接口类型的数组
		Class<?>[] interfaces = tarClass.getInterfaces();

		// 3. 准备InvocationHandler对象
		 InvocationHandler h = new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				String className = tar.getClass().getName();
				String methodName = method.getName();
				Object result = null;
				try {
					System.out.println("[日志][" + className + "]" + methodName + "开始...");
					result = method.invoke(tar, args);
					System.out.println("[日志][" + className + "]" + methodName + "正常结束...");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("[日志][" + className + "]" + methodName + "异常结束...");
					e.printStackTrace();
				} finally {
					System.out.println("[日志][" + className + "]" + methodName + "最终结束...");
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
