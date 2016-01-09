package junit.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

import com.ddyblackhat.javase.ReflectPoint;

/**
 * ����test��
 * @author dudy
 */
public class ReflectTest {

	/**
	 * �����ķ���
	 * 
	 * @throws Exception
	 * @throws NoSuchMethodException
	 */
	@Test
	public void methodTest() throws Exception {
		String str = "123";
		/* ��ȡ str �� charAt() ���� */
		Method method = str.getClass().getMethod("charAt", int.class);
		System.out.println(method.invoke(str, 2));
		System.out.println(method.invoke(str, new Object[] { 2 }));

		// ����ĳ����� main ������ �Խ�����������ķ������з���
		Method methodMain = ArgumentsTest.class.getMethod("main", String[].class);
		/**
		 *  null:  ִ�������������Ҫʵ������-> ��̬������
		 *  (Object)new String[]{"123","abc"}
		 *  ֻ��Ҫ����һ�����������
		 *  Ϊ�����¼��ݣ�1.4 ������е������ڲ��ĵ���һ�����Ĳ�����
		 *  ���԰�������һ��Object����
		 *  
		 */
		methodMain.invoke(null,(Object)new String[]{"123","abc"});
		methodMain.invoke(null,new Object[]{new String[]{"123","abc"}});
	}

	static class ArgumentsTest {
		public static void main(String[] args) {

			for (String string : args) {
				System.out.println(string);
			}
		}
	}

	// -------------------------------------------
	/**
	 * ��Ա�����ķ���
	 * 
	 * @throws Exception
	 * @throws SecurityException
	 */
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
	 * 
	 * @param point
	 * @throws Exception
	 */
	private void changeB2A(Object point) throws Exception {
		Field[] fields = point.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getType() == String.class) { // ���ǻ�ȡһ���ֽ��룬���ǻ�ȡString���ֽ���
				String str = (String) field.get(point);
				String replace = str.replace('b', 'a');
				field.set(point, replace);
			}
		}
	}

	// ---------------------------------------------
	/**
	 * �������ķ���
	 * 
	 * @throws Exception
	 */
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
