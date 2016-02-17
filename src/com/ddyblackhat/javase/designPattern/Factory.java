package com.ddyblackhat.javase.designPattern;

/**
 * @author ����ģʽ ���ǳ����˴����Ĳ�Ʒ��Ҫ���������Ҿ��й�ͬ�Ľӿ�ʱ������ͨ����������ģʽ���д�����
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

// ------���󹤳�ģʽ------
/**
 * ������Ƴ�һ���ӿڣ���ƷҲ��Ƴ�һ���ӿڣ� �в�ͬ�Ĺ����Ͳ�Ʒ
 * �ô�: ������һ������: ���ͼ�ʱ��Ϣ����ֻ��Ҫ��һ��ʵ���࣬ʵ��sender�ӿ�
 * ͬʱ��һ�������࣬ʵ��Provider�ӿڣ���OK�ˣ�����ȥ�Ķ��ֳɵĴ��룬��չ�ԽϺá�
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
// ��̬����ģʽ
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
