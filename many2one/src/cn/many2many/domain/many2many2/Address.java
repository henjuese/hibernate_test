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
	//��ʶ����
	private int addressid;
	//��ַ��ϸ��Ϣ
	private String addressdetail;
	//N��N������ϵ��ʹ��Set���������ʵ��
	private Set<Person2> persons2
		= new HashSet<Person2>();

	//�޲����Ĺ�����
	public Address()
	{
	}
	//��ʼ��addressdetail���ԵĹ�����
	public Address(String addressdetail)
	{
		this.addressdetail = addressdetail;
	}

	//addressid���Ե�setter��getter����
	public void setAddressid(int addressid)
	{
		this.addressid = addressid;
	}
	public int getAddressid()
	{
		return this.addressid;
	}

	//addressdetail���Ե�setter��getter����
	public void setAddressdetail(String addressdetail)
	{
		this.addressdetail = addressdetail;
	}
	public String getAddressdetail()
	{
		return this.addressdetail;
	}

	//persons���Ե�setter��getter����
	public void setPersons2(Set<Person2> persons2)
	{
		this.persons2 = persons2;
	}
	public Set<Person2> getPersons2()
	{
		return this.persons2;
	}
}