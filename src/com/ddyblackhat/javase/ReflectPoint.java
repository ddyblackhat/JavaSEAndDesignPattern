package com.ddyblackhat.javase;

/**
 * ���������
 * @author ddyb
 *
 */
public class ReflectPoint {
	private int x;
	
	public int y;
 
	public String a = "ball";
	public String b = "baseketball";
	public String c = "ddy";
	
	public ReflectPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return a + ":" + b + ":" + c;
	}
}
