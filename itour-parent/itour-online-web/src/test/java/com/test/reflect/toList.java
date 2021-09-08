package com.test.reflect;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

public class toList {
/**
 * 1.什么是反射？
 *       反射就是把Java类中的各个成分映射成一个个的Java对象。
 *       即在运行状态中，对于任意一个类，都能够知道这个类的所以属性和方法；对于任意一个对象，都能调用它的任意一个方法和属性。
 *       这种动态获取信息及动态调用对象方法的功能叫Java的反射机制。
 * 2.实现反射机制的类
 *  Class:代表一个类;
 *  Field:代表成员变量(类的属性)
 *  Method:代表类的方法;
 *  Constructor:代表类的构造方法
 *  Array：提供了动态创建数组，以及访问数组的元素的静态方法
 * 3.反射的使用
 *   1.获取Class的三种方式
 *     a.Object getClass();
 *       Date date = new Date();
	    Class clazz = date.getClass();
	   b.通过class属性
	     Class clazz = Date.class;
	   c.通过Class类的静态方法：forName(String  className)
	    Class clazz = Class.forName("java.util.Date");
 *   
 *   2.获取构造函数
 *    a.获取所有的公有的构造函数
 *      clazz.getConstructors(); *    
 *    b.获取指定的公有的构造函数getConstructor(parameterTypes);
 *      1. 获取公共的无惨构造 
 *         Constructor constructor = clazz.getConstructor(null);
 *      2. 调用公共构造函数
 *          Object newInstance = constructor.newInstance();
 *    
 *    I.获取所有的构造方法(包括：私有、受保护、默认、公有)
 *      clazz.getDeclaredConstructors();
 *    II.获取指定的构造函数（包括：私有、受保护、默认、公有)getDeclaredConstructor(parameterTypes)
 *       a.获取构造函数（私有或公有的都可以获取到）
 *         Constructor constructor = clazz.getDeclaredConstructor(null);
 *       b.调用私有的构造函数（如果类中的无惨构造是私有的必须设置setAccessible(true)）
 *         constructor.setAccessible(true);
		   Object newInstance = constructor.newInstance();
		   
 *   3.获取属性    
 *     a.获取所有的公有字段
 *       Field[] fields = clazz.getFields();       
 *     b.获取所有的公有字段(私有、受保护、默认、公有)
 *       Field[] fields = clazz.getDeclaredFields();
 *     c.单个获取公共字段
	     Field field = clazz.getField(name);	    
	   d.单个获取的公有字段(私有、受保护、默认、公有)
	     Field field = clazz.getDeclaredField("id");
	   e.单个获取公共字段并调用
	     Field field = clazz.getField("hobby");
         Object newInstance = clazz.getConstructor().newInstance();
         field.set(newInstance, "看电影");
         Person p= (Person)newInstance;
         System.out.println(p.hobby);
       f.单个获取私有字段并调用
         Class clazz = Person.class;
		 Field field = clazz.getDeclaredField("id");
	     Object newInstance = clazz.getDeclaredConstructor().newInstance();
	     field.setAccessible(true);
	     field.set(newInstance, 1L);
	     Person p= (Person)newInstance;
	     System.out.println(p.getId());
	 
	 4.获取方法
	   I.获取所有"公有方法"；（包含了父类的方法也包含Object类)
	     Method[] methods = clazz.getMethods();
	   II.获取所有的成员方法，包括私有的(不包括继承的)
	     Method[] methods = clazz.getDeclaredMethods();
	   III.单个获取：name:方法名;parameterTypes:形参的Class类型对象
	       getMethod(String name,Class<?>... parameterTypes)
	        Method method = clazz.getMethod("getTime");
	       
	   IV.调用方法 invoke(Object obj,Object... args)
	      obj 要调用方法的对象;args:调用方式时所传递的实参
	      Object invoke = method.invoke(newInstance);
	   v.实例	      
	       Class clazz = Date.class;
	       Method method = clazz.getMethod("getTime");
	       Object newInstance = clazz.getConstructor().newInstance();
	       Object invoke = method.invoke(newInstance);
	       System.out.println(invoke);
	   
 *      
 * @param args
 * @throws ClassNotFoundException 
 */
public static void main(String[] args) throws Exception {
	Class clazz = Person.class;
	Field[] fields = clazz.getDeclaredFields();
	for (Field field2 : fields) {
		String name = field2.getType().getName();
		String t = field2.getType().getSimpleName();
		System.out.println(name+"---"+t);
	}
	
	
	
	
}
public List<?> toList(Class clazz,List<?>data){
	for (Object object : data) {
		
	}
	return null;
}
}
