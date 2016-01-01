package junit.test;

import org.junit.Test;

import com.ddyblackhat.javase.Weekend;

/**
 * 测试
 * 
 * @author dudy
 */
public class EnumTest {

	@Test
	public void enumTest() {
		Weekend M = Weekend.MON;
		Weekend S = M.getNextDay();
		System.out.println(S);
	}

	@Test
	public void enumTest1() {
		Weekends w = Weekends.SUN;
		System.out.println(w.name());
		System.out.println(w.ordinal());
		System.out.println(w.valueOf("WED").toString());
		System.out.println(Weekends.values().length);
	}

	public enum Weekends {
		SUN, MON(), TUES(2), WED, Thur, FRI, SAT;// 只要一调用就会创建这些对象,所有的方法必须必须放在后边。
		private Weekends() {
			System.out.println("first");
		}; // 构造器必须私有。

		private Weekends(int num) {
			System.out.println("seconed");
		};
	}

	public enum TrafficLamp {
		RED(60) {// 匿名的子类,调用父类的构造方法
			@Override
			public TrafficLamp nextLamp() {// 重写父类的方法
				return GREEN;
			}

		},
		GREEN(60) {

			@Override
			public TrafficLamp nextLamp() {
				return YELLOW;
			}

		},
		YELLOW(5) {

			@Override
			public TrafficLamp nextLamp() {
				return RED;
			}
		};

		public abstract TrafficLamp nextLamp();

		private int time;

		private TrafficLamp(int time) {
			this.time = time;
		}
	}

}
