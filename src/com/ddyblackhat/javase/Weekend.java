package com.ddyblackhat.javase;

/**
 * 普通类模拟实现 enum
 * 
 * @author dudy
 *
 */
public abstract class Weekend {
	private Weekend() {
	}; // 构造器私有化

	/**
	 * 匿名内部类，Weekend抽象方法的子类
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
	 * 采用抽象方法将大量的if..else 转换成一个个的类。
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
