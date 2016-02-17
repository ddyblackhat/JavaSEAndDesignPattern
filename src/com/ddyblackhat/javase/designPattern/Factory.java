package com.ddyblackhat.javase.designPattern;

/**
 * @author 工厂模式 凡是出现了大量的产品需要创建，并且具有共同的接口时，可以通过工厂方法模式进行创建。
 *
 */

interface Sender {
	public void Send();
}

class MailSender implements Sender {

	@Override
	public void Send() {
		System.out.println("this is mail sender... ");
	}

}

class SMSSender implements Sender {

	@Override
	public void Send() {
		System.out.println("this is SMS sender... ");
	}

}

// ------抽象工厂模式------
/**
 * 工厂设计成一个接口，产品也设计成一个接口， 有不同的工厂和产品
 * 好处: 想增加一个功能: 发送及时信息，则只需要做一个实现类，实现sender接口
 * 同时做一个工厂类，实现Provider接口，就OK了，无需去改动现成的代码，拓展性较好。
 *
 */
interface Provider {
	public Sender produce();
}

class SendMailFactory implements Provider {

	@Override
	public Sender produce() {
		return new MailSender();
	}

}

class SendSMSFactory implements Provider {

	@Override
	public Sender produce() {
		return new SMSSender();
	}
	
}

class FactoryTest{
	public static void main(String[] args) {
		Provider provider = new SendSMSFactory();
		Sender sms = provider.produce();
		sms.Send();
	}
}
// 静态工厂模式
public class Factory {
	public static Sender produceMail() {
		return new MailSender();
	}

	public static Sender produceSms() {
		return new SMSSender();
	}

	public static void main(String[] args) {
		Sender mail = Factory.produceMail();
		mail.Send();
	}
}
