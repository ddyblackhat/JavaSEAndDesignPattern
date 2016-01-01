package com.ddyblackhat.javase;

/**
 * ��ͨ��ģ��ʵ�� enum
 * 
 * @author dudy
 *
 */
public abstract class Weekend {
	private Weekend() {
	}; // ������˽�л�

	/**
	 * �����ڲ��࣬Weekend���󷽷�������
	 */
	public final static Weekend SUN = new Weekend() {
		@Override
		public Weekend getNextDay() {

			return MON;
		}
		@Override
		public String toString() {

			return "SUN";
		}

	};

	public final static Weekend MON = new Weekend() {

		@Override
		public Weekend getNextDay() {
			return SUN;
		}
		@Override
		public String toString() {

			return "MON";
		}
	};

	/**
	 * ���ó��󷽷���������if..else ת����һ�������ࡣ
	 * @return
	 */
	public abstract Weekend getNextDay();

	public abstract String toString();

	/*
	 * public Weekend getNextDay() {
	 * 
	 * if (this == SUN) { return MON; } else { return SUN; }
	 * 
	 * }
	 */

}
