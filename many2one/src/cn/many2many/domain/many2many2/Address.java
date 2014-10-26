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
public class Address
{
	//标识属性
	private int addressid;
	//地址详细信息
	private String addressdetail;
	//N－N关联关系，使用Set来保存关联实体
	private Set<Person2> persons2
		= new HashSet<Person2>();

	//无参数的构造器
	public Address()
	{
	}
	//初始化addressdetail属性的构造器
	public Address(String addressdetail)
	{
		this.addressdetail = addressdetail;
	}

	//addressid属性的setter和getter方法
	public void setAddressid(int addressid)
	{
		this.addressid = addressid;
	}
	public int getAddressid()
	{
		return this.addressid;
	}

	//addressdetail属性的setter和getter方法
	public void setAddressdetail(String addressdetail)
	{
		this.addressdetail = addressdetail;
	}
	public String getAddressdetail()
	{
		return this.addressdetail;
	}

	//persons属性的setter和getter方法
	public void setPersons2(Set<Person2> persons2)
	{
		this.persons2 = persons2;
	}
	public Set<Person2> getPersons2()
	{
		return this.persons2;
	}
}