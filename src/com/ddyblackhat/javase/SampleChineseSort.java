package com.ddyblackhat.javase;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * Arrays工具类和 comparator 接口实现排序。
 * comparable 和 comparator 区别：
 * ①comparable接口需要让列表中的对象来实现接口中的方法comparaTo(E).返回正数表示当前对象比传入对象大。
 * 不是很灵活,实现接口后这个方法固定了,也就是排序方法固定了。
 * ②可以使用comparator接口来扩展,他独立于被排序的对象单独存在,当需要的排序的时候,以参数的形式传递。
 * 
 * 
 * 
 * @author dudy
 *
 */
public class SampleChineseSort {

	@SuppressWarnings("rawtypes")
	private final static Comparator CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);

	public static void main(String[] args) {
		sortArray();
		sortList();
	}
	
	private static void sortList() {
		List<String> list = Arrays.asList("张三", "李四", "王五");
		Collections.sort(list, CHINA_COMPARE);
		for (String str : list) {

			System.out.println(str);
		}
	}

	private static void sortArray() {
		String[] arr = { "张三", "李四", "王五" };
		Arrays.sort(arr, CHINA_COMPARE);
		for (String str : arr) {

			System.out.println(str);
		}
	}
}
