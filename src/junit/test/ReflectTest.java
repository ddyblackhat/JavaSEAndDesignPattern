package junit.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.junit.Test;

import com.ddyblackhat.javase.ReflectPoint;

/**
 * 反射test类
 * 
 * @author dudy
 *
 */
public class ReflectTest {

	@Test
	public void fieldTest() throws Exception, SecurityException {
		ReflectPoint point = new ReflectPoint(3, 6);
		Field field = point.getClass().getField("y");// 获取point 成员变量y
														// 的成员变量类
		System.out.println(field.get(point));// 获取 point对象的y属性的值

		Field field2 = point.getClass().getDeclaredField("x"); // 获取私有的，和继承父类的
		field2.setAccessible(true); // 暴力反射
		System.out.println(field2.get(point));

		
		changeB2A(point);
		System.out.println(point.toString());

	}

	/**
	 * 将 某个类中字符串成员变量中的 b 替换成 a
	 * @param point
	 * @throws Exception
	 */
	private void changeB2A(Object point) throws Exception {
		Field[] fields = point.getClass().getDeclaredFields();
		for (Field field : fields) {
			if(field.getType() == String.class){ //都是获取一份字节码，都是获取String的字节码
				 String str = (String)field.get(point);
				 String replace = str.replace('b', 'a');
				 field.set(point, replace);
			}
		}
	}

	@Test
	public void constructTest() throws Exception {
		// 获取某个类的所有的构造方法。
		// Constructor<?>[] constructors = String.class.getConstructors();
		// 获取某个类的一个构造方法。
		Constructor<String> cs1 = String.class.getConstructor(StringBuffer.class);
		// 构造器拿到的是StringBuffer的,只能newInstance StringBuffer对象，可以多次调用
		String string = cs1.newInstance(new StringBuffer("abc"));
		System.out.println(string.charAt(2));
	}

}
