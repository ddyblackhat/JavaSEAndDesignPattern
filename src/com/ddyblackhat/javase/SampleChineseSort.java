package com.ddyblackhat.javase;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * Arrays������� comparator �ӿ�ʵ������
 * comparable �� comparator ����
 * ��comparable�ӿ���Ҫ���б��еĶ�����ʵ�ֽӿ��еķ���comparaTo(E).����������ʾ��ǰ����ȴ�������
 * ���Ǻ����,ʵ�ֽӿں���������̶���,Ҳ�������򷽷��̶��ˡ�
 * �ڿ���ʹ��comparator�ӿ�����չ,�������ڱ�����Ķ��󵥶�����,����Ҫ�������ʱ��,�Բ�������ʽ���ݡ�
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
		List<String> list = Arrays.asList("����", "����", "����");
		Collections.sort(list, CHINA_COMPARE);
		for (String str : list) {

			System.out.println(str);
		}
	}

	private static void sortArray() {
		String[] arr = { "����", "����", "����" };
		Arrays.sort(arr, CHINA_COMPARE);
		for (String str : arr) {

			System.out.println(str);
		}
	}
}
