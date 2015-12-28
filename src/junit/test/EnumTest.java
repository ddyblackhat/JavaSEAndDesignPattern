package junit.test;

import org.junit.Test;

import com.ddyblackhat.javase.Weekend;

/**
 * ≤‚ ‘
 * @author dudy
 */
public class EnumTest {
	
	@Test
	public void enumTest(){
		Weekend M = Weekend.MON;
		Weekend S = M.getNextDay();
		System.out.println(S);
	}
	
	@Test
	public void enumTest1(){
		Weekends w = Weekends.SUN;
		System.out.println(w.name());
		System.out.println(w.ordinal());
		System.out.println(Weekends.values().length);
	}
	
	
	public enum Weekends{
		SUN,MON,TUES,WED,Thur,FRI,SAT;
		
	}

}
