package com.test;

import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

public class TestArr {
@Test
public void arrInit() {
	 //1.数组动态初始化
	 String [] arr= new String[3];
	 arr[0]="008";
	 arr[1]="tom";
	 arr[2]="read";
	 //2.数组静态初始化
	 String [] arr1 = {"008","007"};
	 System.out.println(arr);
	 for (int i = 0; i < arr.length; i++) {
		 System.out.print(arr[i]+"  ");
		
	}
}
/**
 * 在指定位置插入元素
 * 实现思路
 * 1.创建一个新数组，新数组长度 = 旧数组长度 + 1，在把旧数组里面的元素复制到新元素里面。根据指定位置之后的数组元素往后移一位。再把指定的元素插进去。再把新数组赋给旧数组
 * https://blog.csdn.net/weixin_40916641/article/details/99772226
 */
@Test
public void arrInsert() {
	String [] arrOld = {"1","2","3","4","5"};
	//{"1","2","3","4","5",""}; i>2
	
	//{"1","2","3","4","","5"};i=5
	
	//{"1","2","3","","4","5"};i=4
	
	//{"1","2","","3","4","5"};i=3
	
	//{"1","2","","3","4","5"};i=2
	int index=2;
	String value ="QQ";
	
	String [] arrNew = new String[arrOld.length+1];
	for (int i = 0; i < arrOld.length; i++) {
		arrNew[i]=arrOld[i];
		
	}
	for (int i = arrNew.length-1; i > index; i--) {
		arrNew[i]=arrNew[i-1];
	}
	arrNew[index]=value;
	arrOld=arrNew;
	for (String string : arrOld) {
		System.out.print(string+" ");
	}
	
	
	
}
/**
 * 在指定位置插入元素
 * https://www.cnblogs.com/liyihua/p/11707136.html
 * 
 * 思路：
 *   1.第一步：把数组转化为集合
 *   2.向集合中添加元素;
 *   3.第三步：将集合转化为数组
 */
@Test
public void arrInsert1() {
	String [] arr = {"1","2","3","4","5"};
	//1.第一步：把数组转化为集合
	List<Object> asList = Arrays.asList(arr);
	//2.向集合中添加元素;
	asList.add(2, "QQ");
	//3.第三步：将集合转化为数组
	Object[] array = asList.toArray();
	/*	new string[0]的作用:
		如果指定的数组能容纳该 collection，则返回包含此 collection 元素的数组。
		否则，将根据指定数组的运行时类型和此 collection 的大小分配一个新数组。
		这里给的参数的数组长度是0，因此就会返回包含此 collection 中所有元素的数组，并且返回数组的类型与指定数组的运行时类型相同。
		原文链接：https://blog.csdn.net/qq_38521014/article/details/89931049
*/	
	String[] array2 = asList.toArray(new String[0]);

}
@Test
public void testInitArr2() {
	 String [][] arr = {
					{"id","编号"},
					{"name","姓名"},
					{"age","年龄"},
					{"hobby","爱好"},
			};
	 int x = 2;//每行总列数
	 int y=1;//插入行数下标
	 
	 String [] idCard= {"idCard","身份证"};
	 
	add2Arr(arr, x, y, idCard);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < x; j++) {
				System.out.println(arr[i][j]);
			}
		}

  }
/**
 * 向String [][] 中添加一行
 * @param String [][] arr 二维目标数组 
 * @param x   目标数组的列数
 * @param y   希望插入数组到目标数值的行下标
 * @param String [] array
 * @return String [][]
 */
	public String[][] add2Arr(String[][] arr, int x, int y, String[] array) {
		// 1.创建一个比当前二维行数大1的二维数组
		String[][] arrNew = new String[arr.length + 1][x];

		// 2.将目标二维数值复制到创建好的二维数组中;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < x; j++) {
				arrNew[i][j] = arr[i][j];
			}
		}

		// 3.将指定下标及下标后的数据后移
		for (int i = arrNew.length - 1; i > y; i--) {
			for (int k = 0; k < x; k++) {
				arrNew[i][k] = arrNew[i - 1][k];
			}
		}

		// 4.将指定的值放入二维数值中
		for (int m = 0; m < x; m++) {
			arrNew[y][m] = array[m];
		}
		
		arr = arrNew;
		return arr;
	}
@Test
/**
 * 1.明确基本数据类型和引用数据类型的区别;
 *   a.基本数据类型的值JVM会在栈内存中开辟一块空间保存;
 *   b.引用数据类型JVM会在栈内存开辟一块空间来保存对象的引用,引用指向堆内存中的保存的对象;
 * 总结：
 *   a.值类型不会改变值本身;
 *   b.引用类型会改变值本身;
 *   
 */
public void passByValueAndpassByReferen() {
	int v =200;
	foo(v);
	System.out.println(v);//v对象没有变;
	String str="IOS";
	foo(str);
	System.out.println(str);//str对象没有变;
	StringBuffer sb = new StringBuffer("windows");
	foo(sb);
	System.out.println(sb);
	foo1(sb);
	StringBuffer sb1 = new StringBuffer("windows");
	System.out.println(sb1);
}
public void foo(int value) {
	value=100;
	
}

public void foo(String value) {
	value="windows";
}

public void foo(StringBuffer sb) {
	sb.append("IOS");
}
public void foo1(StringBuffer sb) {
 sb = new StringBuffer();
 sb.append("ISO");
}

}