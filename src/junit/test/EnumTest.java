package junit.test;

import org.junit.Test;

import com.ddyblackhat.javase.Weekend;

/**
 * ����
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
		SUN, MON(), TUES(2), WED, Thur, FRI, SAT;// ֻҪһ���þͻᴴ����Щ����,���еķ������������ں�ߡ�
		private Weekends() {
			System.out.println("first");
		}; // ����������˽�С�

		private Weekends(int num) {
			System.out.println("seconed");
		};
	}

	public enum TrafficLamp {
		RED(60) {// ����������,���ø���Ĺ��췽��
			@Override
			public TrafficLamp nextLamp() {// ��д����ķ���
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
