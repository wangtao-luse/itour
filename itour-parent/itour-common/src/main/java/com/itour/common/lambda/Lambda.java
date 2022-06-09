package com.itour.common.lambda;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alibaba.fastjson.JSONObject;
import com.itour.exception.BaseException;


//https://developer.ibm.com/zh/articles/j-lo-java8streamapi/
public class Lambda {
	/**
	 * .语法
		   (parameters) -> expression
		   或
		   (parameters) ->{ statements; }
	 * 
	 */
	/*
	 * 优点：1.简洁;
	 *   2.易并行计算。尤其适用于遍历结果，循环计算数值或者赋值的时候非常方便;
	 *   
	 * 缺点: 1.若不用并行计算，很多时候计算速度没有比传统的 for 循环快。
	 *   2.不容易使用debug模式调试。
	 *   3.在 lambda语句中直接强制类型转换不方便。
	 *   4.不可以在foreach中修改foreach外面的值。
	 */
	
	/**
	 * 使用流的步骤:
	 * 1.获取一个数据源（source）
	 * 2.数据转换
	 * 3.执行操作获取想要的结果（每次转换原有 Stream 对象不改变，返回一个新的 Stream 对象（可以有多次转换），这就允许对其操作可以像链条一样排列，变成一个管道）
	 */
	/**
	 * 生成 Stream Source 的方式:
	 * 1.Collection.stream();
	 * 2.Collection.parallelStream();
	 * 3.Arrays.stream(array)or Stream.of();
	 */
	
	/**
	 * shi
	 * @param args
	 */
	public static void main(String[] args) {
		//1.初始化数据
		List<Student> list = getData();
		
		//2. 构造流的几种常见方法
		    StreamOf();
		    
		//3.将String[]类型为List<String>
		   strArrToList();
		   
        //3.1将Stream转为String[]
		streamToStrArr();
		
		//3.2将Stream 转为List
		streamToList();
		//3.3 将Stream转为Map
		streamToMap();
		//List<String> list1 = new ArrayList<String>();
		//String.join(",", list1);
		
		//4.Stream 常见操作
		commonStream();
		
		//5.List<JavaBean>中的某属性---->List<基本类型|包装类>
		List<Integer> toList = ToList(list);
		
		//6.检查list集合中是否存在某个值
				contains(list);
				
		/*
		 * 跟多方式 https://www.it1352.com/999007.html
		 */	}
	private static void contains(List<Student> list) {
		boolean present = list.stream().filter(t->t.getName().equals("张三")).findAny().isPresent();
	}
	/**
	 * Stream转为Map
	 */
	private static void streamToMap() {
		// TODO Auto-generated method stub
		List<Student> stuList = getData();
		//1.将Student封装为Map key为Id
		Map<Integer, Student> collect = stuList.stream().collect(Collectors.toMap(Student::getId, p -> p, (k1,k2)->k1));
	    Map<String, Student> collect3 = stuList.stream().collect(Collectors.toMap(z -> String.valueOf(z.getId()), a -> a, (k1, k2) -> k1));
		System.out.println(JSONObject.toJSONString(collect));
		//2.待研究
		Map<Integer,List<Student>> map = new HashMap<Integer, List<Student>>();
		map.put(1, stuList);
		for (Integer key : map.keySet()) {
			List<String> collect2 = map.get(key).stream().map(Student::getId).map(String::valueOf ).collect(Collectors.toList());
			System.out.println(JSONObject.toJSONString(collect2));
		}
		
	}
	/**
	 * Stream 常见操作
	 */
	private static void commonStream() {
		// TODO Auto-generated method stub
		//1.map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
		 //1.1map 它的作用就是把 input Stream 的每一个元素，映射成 output Stream 的另外一个元素。
		   //转换大写
			List<String> wordList = new ArrayList<String>();
			wordList.add("milk");
			wordList.add("tomato");
			List<String> output = wordList .stream().map(String::toUpperCase).collect(Collectors.toList());
			System.out.println(JSONObject.toJSONString(output));
			//平方数
			List<Integer> nums = Arrays.asList(1, 2, 3, 4);
			List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());
			System.out.println(JSONObject.toJSONString(squareNums));
		/**
		 * map 生成的是个 1:1 映射，每个输入元素，都按照规则转换成为另外一个元素。还有一些场景，是一对多映射关系的，这时需要 flatMap
		 */
			//一对多
			Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1),	 Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
			Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
			System.out.println(JSONObject.toJSONString(inputStream));	
			System.out.println(outputStream);
		/**
		 * flatMap 把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起，最终 output 的新 Stream 里面已经没有
		 * List 了，都是直接的数字。
		 */
		//1.2 filter  对原始 Stream 进行某项测试，通过测试的元素被留下来生成一个新 Stream。
			//留下偶数
			Integer[] sixNums = {1, 2, 3, 4, 5, 6};
			Integer[] evens =	Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
			System.out.println(JSONObject.toJSONString(evens));	
			/**
			 * 经过条件“被 2 整除”的 filter，剩下的数字为 {2, 4, 6}。
			 */
		
		//2.forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
		//3.anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
			List<Student> stuList = getData();
			//检查stuList是否有名字叫tom且年龄大于18的学生
			boolean anyMatch = stuList.stream().anyMatch(stu -> stu.getName().equals("tom")&&stu.getAge()>18);
			System.out.println("anyMatch： "+anyMatch);
	}
	
	/**
	 * Stream转为List
	 */
	private static void streamToList() {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list.add("Amy");
		list.add("Tom");
		list.add("Mike");
		//Stream-->List
		List<String> list1 = list.stream().collect(Collectors.toList());
		//Stream-->ArrayList
		ArrayList<String> list2 = list.stream().collect(Collectors.toCollection(ArrayList::new));
		//Stream-->Set
		Set<String> set = list.stream().collect(Collectors.toSet());
		 //Stream-->Stack
		 Stack<String> stack = list.stream().collect(Collectors.toCollection(Stack::new));
		//Stream-->String
		 String string = list.stream().collect(Collectors.joining()).toString();
		 //使用Stirng类的join()
		 String [] str1 = new String[] {"a","b","c"};
		 String join = String.join(",", Arrays.stream(str1).collect(Collectors.toList()));
		 String join_ = String.join(",", list);
		 
		 //List<String> -->指定分隔符的String 
		 String collect3 = list.stream().collect(Collectors.joining(","));
		
		 //List<Student>中的ID 按照逗号分隔为字符串
		 List<Student> stuList = getData();
		 String ids = String.join(",", stuList.stream().map(t->String.valueOf(t.getId())).collect(Collectors.toList()));
		 
		//将一个按逗号隔开的字符串转换为List<String>
		 List<String> collect = Arrays.asList(ids.split(",")).stream().map(s -> s.trim()).collect(Collectors.toList());
		 //获取一个按逗号隔开的字符串转换为List<String>后的长度;
		 int size = Arrays.asList(ids.split(",")).stream().map(s -> s.trim()).collect(Collectors.toList()).size();
		 //将List<Student> 中id属性的值转换为一个List<String>
		 List<String> strList = stuList.stream().map(t->String.valueOf(t.getId())).collect(Collectors.toList());
		 
		 //将List<Student> 中Name属性的值转换为一个List<String>
		 List<String> strList1 = stuList.stream().map(Student::getName).collect(Collectors.toList());
		 //将List<Student>中的Name属性的值转换为按逗号分隔的字符串
		 String collect5 = stuList.stream().map(Student::getName).collect(Collectors.joining(","));
		 //计算某一列的和
		 Double collect2 = stuList.stream().collect(Collectors.summingDouble(Student::getMoney));
		 double sum = stuList.stream().mapToDouble(Student::getMoney).sum();
		 //计算某一列的平均值
		 double asDouble = stuList.stream().mapToDouble(Student::getMoney).average().getAsDouble();
		 
        //合并名字相同的学生(名字相同只保留1条)
		 ArrayList<Student> collect4 = stuList.stream().collect(
                 Collectors.collectingAndThen(
               		  Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getName))), ArrayList::new)
       	        );
		 
		 //将List<Student> 中的id属性转为List<String>  map(String::valueOf)强转
		 List<String> collect6 = stuList.stream().map(Student::getId).map(String::valueOf).collect(Collectors.toList());
		 List<Integer> collect7 = stuList.stream().map(Student::getId).collect(Collectors.toList());
		 List<String> collect8 = stuList.stream().map(t->String.valueOf(t.getId())).collect(Collectors.toList());
		 
		 //list中年龄大于12的学生
		 List<Student> collect9 = stuList.stream().filter(stu->stu.getAge()>12).collect(Collectors.toList());
		 //获取名字为mike的记录,调整记录添加到studentList中;
		 List<Student> studentList = new ArrayList<Student>();
		 stuList.stream().filter(stu -> stu.getName().equals("mike")).forEach(item -> {
				item.setAge(20);
				item.setMoney(10000d);
				
				studentList.add(item);
			});
		 //重复判断
		 Map<String, Long> collect10 = stuList.stream().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
		 for (Long value : collect10.values()) {
				if (value > 1) {
					
					System.out.println("有重复的姓名");
				}
			}
		 //分组
		 Map<String, List<Student>> map = stuList.stream().collect(Collectors.groupingBy(Student::getName));
		 
			
		System.out.println("3.1Stream转为List:streamToList()");
		System.out.println("  List<String> :"+JSONObject.toJSONString(list));
		System.out.println("  List1: "+JSONObject.toJSONString(list));
		System.out.println("  ArrayList: "+JSONObject.toJSONString(list2));
		System.out.println("  set: "+JSONObject.toJSONString(set));
		System.out.println("  Stack: "+JSONObject.toJSONString(stack));
		System.out.println("  String: "+JSONObject.toJSONString(string));
		System.out.println("  join :"+JSONObject.toJSONString(join) );
		System.out.println("collect2 :"+JSONObject.toJSONString(collect2)+"---"+sum);
		
		System.out.println("avg :"+ asDouble);
		System.out.println("str :"+ collect3);
		System.out.println("collect4 :"+JSONObject.toJSONString(collect4));
		System.out.println("collect5 :"+JSONObject.toJSONString(collect5));
		System.out.println("collect6 :"+JSONObject.toJSONString(collect6));
		
	}
	/**
     * 将String[]类型为List<String>
 */
	public static void strArrToList() {
		String [] strArray = new String[] {"a", "b", "c"};
		List<String> list = Arrays.asList(strArray);
		System.out.println("3.将String[]类型为List<String>:strArrToList()");
		System.out.println("  String[] :"+JSONObject.toJSONString(strArray));
		System.out.println("  List: "+JSONObject.toJSONString(list));
	}
	/**
	 * Stream流转为String[]
	 */
	public static void streamToStrArr() {
		//1.构建Array类型的Stream
		String [] strArray = new String[] {"a", "b", "c"};
		Stream  stream = Arrays.stream(strArray);
		//将流转为String[]
		String[] strArray1 = (String[]) stream.toArray(String[]::new);
		System.out.println("4.Stream流转为String[]:streamToStrArr()");
		System.out.println("  String[] :"+JSONObject.toJSONString(strArray));
		System.out.println("  List: "+JSONObject.toJSONString(strArray1));
	}
	/**
	 *  构建Stream的常用方式
	 * @param <T>
	 * @param value
	 * @return
	 */
	public static void StreamOf() {
		// 1. Individual values
		Stream<String> of = Stream.of("a", "b", "c");
		// 2. Arrays
		String [] strArray = new String[] {"a", "b", "c"};
		 Stream<String> of2 = Stream.of(strArray);
	    Stream<String> stream = Arrays.stream(strArray);
		// 3. Collections
		List<String> list = Arrays.asList(strArray);
		Stream stream2 = list.stream();
		Stream<String> parallelStream = list.parallelStream();
		System.out.println("2.构建Stream的常用方式:StreamOf()");
	}
	/**
	 * 初始化数据
	 * @return
	 */
	private static List<Student> getData() {
		List<Student> list = new ArrayList<Student>();
		Student stu1 = new Student(1, "mike", 18,100d);
		Student stu2 = new Student(2, "mike", 16,100.00);
		Student stu3 = new Student(3, "amy", 13,100d);
		Student stu4 = new Student(4, "tom", 12,50d);
		list.add(stu1);
		list.add(stu2);
		list.add(stu3);
		list.add(stu4);
		System.out.println("1.原始数据：getData()");
		for (Student student : list) {
			System.out.println(student);
		}
		return list;
	}	

   
	
	//List<JavaBean>---->List<基本类型|包装类>
	public static List<Integer> ToList(List<Student> list) {
		List<Integer> collect = list.stream().map(Student::getId).collect(Collectors.toList());
		return collect;
	}
	
}
