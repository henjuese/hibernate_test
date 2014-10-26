package cn.many2many.domain.many2many2;

import java.util.*;
/**
 * @author  yeeku.H.lee kongyeeku@163.com
 * @version  1.0
 * <br>Copyright (C), 2005-2008, yeeku.H.Lee
 * <br>This program is protected by copyright laws.
 * <br>Program Name:
 * <br>Date: 
 */
public class Person2
{
	//标识属性
	private int personid;
	//Person的name属性
	private String name;
	//保留Person的age属性
	private int age;
	//N－N关联关系，使用Set来保存关联实体
	private Set<Address> addresses
		= new HashSet<Address>();

	//无参数的构造器
	public Person2()
	{
	}
	//初始化全部属性的构造器
	public Person2(int personid , String name , int age)
	{
		this.personid = personid;
		this.name = name;
		this.age = age;
	}

	//personid属性的setter和getter方法
	public void setPersonid(int personid)
	{
		this.personid = personid;
	}
	public int getPersonid()
	{
		return this.personid;
	}

	//name属性的setter和getter方法
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}

	//age属性的setter和getter方法
	public void setAge(int age)
	{
		this.age = age;
	}
	public int getAge()
	{
		return this.age;
	}

	//addresses属性的setter和getter方法
	public void setAddresses(Set<Address> addresses)
	{
		this.addresses = addresses;
	}
	public Set<Address> getAddresses()
	{
		return this.addresses;
	}
}