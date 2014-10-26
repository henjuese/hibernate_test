package cn.many2many.domain.many2many2;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="tb_person2")
public class Person2
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int personid;

	private String name;

	private int age;
	
	@ManyToMany(cascade=CascadeType.ALL,targetEntity=Address.class,fetch=FetchType.EAGER)
	//生成一张中间表
	@JoinTable(name="pes_add",
				joinColumns=@JoinColumn(name="person_id"),//本类主键在中间表生成的对应字段名
				inverseJoinColumns=@JoinColumn(name="address_id")//对方类主键在中间表生成的对应字段名
			)
	private Set<Address> addresses= new HashSet<Address>();

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