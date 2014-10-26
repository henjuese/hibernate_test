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
	//����һ���м��
	@JoinTable(name="pes_add",
				joinColumns=@JoinColumn(name="person_id"),//�����������м�����ɵĶ�Ӧ�ֶ���
				inverseJoinColumns=@JoinColumn(name="address_id")//�Է����������м�����ɵĶ�Ӧ�ֶ���
			)
	private Set<Address> addresses= new HashSet<Address>();

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