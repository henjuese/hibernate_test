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
	//��ʶ����
	private int personid;
	//Person��name����
	private String name;
	//����Person��age����
	private int age;
	//N��N������ϵ��ʹ��Set���������ʵ��
	private Set<Address> addresses
		= new HashSet<Address>();

	//�޲����Ĺ�����
	public Person2()
	{
	}
	//��ʼ��ȫ�����ԵĹ�����
	public Person2(int personid , String name , int age)
	{
		this.personid = personid;
		this.name = name;
		this.age = age;
	}

	//personid���Ե�setter��getter����
	public void setPersonid(int personid)
	{
		this.personid = personid;
	}
	public int getPersonid()
	{
		return this.personid;
	}

	//name���Ե�setter��getter����
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}

	//age���Ե�setter��getter����
	public void setAge(int age)
	{
		this.age = age;
	}
	public int getAge()
	{
		return this.age;
	}

	//addresses���Ե�setter��getter����
	public void setAddresses(Set<Address> addresses)
	{
		this.addresses = addresses;
	}
	public Set<Address> getAddresses()
	{
		return this.addresses;
	}
}