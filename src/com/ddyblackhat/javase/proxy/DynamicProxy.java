package com.ddyblackhat.javase.proxy;

import java.lang.reflect.*;

interface AbstractSubject {
	public void say();
}

class RealSubjectA implements AbstractSubject {

	@Override
	public void say() {
		// TODO Auto-generated method stub
		System.out.println("....");
	}

}

public class DynamicProxy implements InvocationHandler {
	public static void main(String[] args) throws Exception {
		Class cls = Proxy.getProxyClass(AbstractSubject.class.getClassLoader(), AbstractSubject.class);

		Constructor csr = cls.getConstructor(InvocationHandler.class);

		AbstractSubject as = (AbstractSubject) csr.newInstance(new DynamicProxy(new RealSubjectA()));

		as.say();

	}

	private AbstractSubject as;

	public DynamicProxy(AbstractSubject as) {
		this.as = as;
	}

	public void preRequest() {
		System.out.println("调用之前");
	}

	public void postRequest() {
		System.out.println("调用之后");
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		preRequest();
		method.invoke(as, args);
		postRequest();
		return null;
	}
}
