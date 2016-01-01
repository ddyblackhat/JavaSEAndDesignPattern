package junit.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.junit.Test;

import com.ddyblackhat.javase.ReflectPoint;

/**
 * ����test��
 * 
 * @author dudy
 *
 */
public class ReflectTest {

	@Test
	public void fieldTest() throws Exception, SecurityException {
		ReflectPoint point = new ReflectPoint(3, 6);
		Field field = point.getClass().getField("y");// ��ȡpoint ��Ա����y
														// �ĳ�Ա������
		System.out.println(field.get(point));// ��ȡ point�����y���Ե�ֵ

		Field field2 = point.getClass().getDeclaredField("x"); // ��ȡ˽�еģ��ͼ̳и����
		field2.setAccessible(true); // ��������
		System.out.println(field2.get(point));

		
		changeB2A(point);
		System.out.println(point.toString());

	}

	/**
	 * �� ĳ�������ַ�����Ա�����е� b �滻�� a
	 * @param point
	 * @throws Exception
	 */
	private void changeB2A(Object point) throws Exception {
		Field[] fields = point.getClass().getDeclaredFields();
		for (Field field : fields) {
			if(field.getType() == String.class){ //���ǻ�ȡһ���ֽ��룬���ǻ�ȡString���ֽ���
				 String str = (String)field.get(point);
				 String replace = str.replace('b', 'a');
				 field.set(point, replace);
			}
		}
	}

	@Test
	public void constructTest() throws Exception {
		// ��ȡĳ��������еĹ��췽����
		// Constructor<?>[] constructors = String.class.getConstructors();
		// ��ȡĳ�����һ�����췽����
		Constructor<String> cs1 = String.class.getConstructor(StringBuffer.class);
		// �������õ�����StringBuffer��,ֻ��newInstance StringBuffer���󣬿��Զ�ε���
		String string = cs1.newInstance(new StringBuffer("abc"));
		System.out.println(string.charAt(2));
	}

}
