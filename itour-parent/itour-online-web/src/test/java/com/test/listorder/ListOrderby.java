package com.test.listorder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.itour.common.resp.ResponseMessage;
import com.itour.util.FastJsonUtil;

public class ListOrderby {
public static void main(String[] args) {
	
	List<Person> initList = ListOrderby.initList();
	//simpleListSort(initList);
	//2.经过转换得到的List
	ResponseMessage resp = ResponseMessage.getSucess();
	resp.setReturnResult(initList);
	List<Person> mapToList = FastJsonUtil.mapToList(resp.getReturnResult(), Person.class);
	List<Person> sortList = ListOrderby.sortList(mapToList);
	System.out.println("---------------------------------------------------------------------");
	resp.setReturnResult(sortList);
	for (Person person : sortList) {
		System.out.println(person);
	}
	System.out.println("---------------------------------------------------------------------");
	List<Person> list = FastJsonUtil.mapToList(resp.getReturnResult(), Person.class);
	for (Person person : list) {
		System.out.println(person);
	}
	
}
private static void simpleListSort(List<Person> initList) {
	//1.简单的List
	List<Person> pList = ListOrderby.sortList(initList);
	for (Person person : pList) {
		System.out.println(person);
	}
}
public static List sortList(List pList) {
	Collections.sort(pList, new Comparator<Person>() {
		@Override
		public int compare(Person o1, Person o2) {
			 //按照CreatTime字段进行降序排列
	        if (o1.getAge() < o2.getAge()) {
	            return 1;
	        }else if (o1.getAge() == o2.getAge()) {
	            return 0;
	        }
	        return -1;
		}
	});
	return pList;
}



public static List<Person> initList(){
	List<Person> list = new ArrayList<Person>();
	list.add(new Person(1L, "zhangsan", 18));
	list.add(new Person(2L, "amy", 16));
	list.add(new Person(3L, "12306", 28));
	list.add(new Person(4L, "jack", 26));
	return list;
}

}
