package junit.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class StringTest implements Serializable {

	// public void methodA(List<Object> mylist)
	public void methodA(List<? extends Object> mylist)
	// public void methodA(List<?> mylist)
	{
	}

	@Test
	public void test1() {

		StringTest test = new StringTest();
		test.methodA(new ArrayList<Object>());
		test.methodA(new LinkedList<Integer>());
		test.methodA(new ArrayList<String>());

	}

	@Test
	public void test() {
		/**
		 * 1 几个对象? 2 分别在哪？ 3 true/false情况如何?
		 */
		String s1 = new String("abc");
		String s2 = "abc";
		String s3 = new String("abc");
		System.out.println(s1 == s2); // false
		System.out.println(s2 == s3); // false
		System.out.println(s1 == s3); // false
		System.out.println("---------------");
		System.out.println(s1 == s1.intern());// false
		System.out.println(s1 == s2.intern());// false
		System.out.println(s1.intern() == s2.intern());// true
		System.out.println(s1.intern() == s2);
		System.out.println("---------------");

		String s4 = "java";
		String s5 = "ja";
		String s6 = "va";
		System.out.println(s4 == "ja" + "va");
		System.out.println(s4 == (s5 + s6));
		System.out.println(s4 == "ja" + s6);

		// 1 true/false/false;
	}

}
