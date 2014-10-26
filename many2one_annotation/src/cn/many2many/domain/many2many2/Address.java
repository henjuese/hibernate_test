package cn.many2many.domain.many2many2;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="tb_address")
public class Address
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int addressid;
	
	private String addressdetail;
	
	//@Transient//在单项多对多时，已经在Person2配置了，这里就不需要这个字段，双向关联时才需要
	//下面我要做个双向关联，但是person2之前的配置不动
	//这里不要加上懒加载哦，加上了，以后查人员就不能直接查询出address了
	//双向关联时：两个实体间互相关联的属性必须标记为@ManyToMany，并相互指定targetEntity属性。
	//有且只有一个实体的@ManyToMany注解需要指定mappedBy属性，指向targetEntity的集合属性名称。
	//这里的mappedBy="addresses"中的address是Person2中的集合属性名称
	//什么时候使用懒加载呢？这里对person2配置了懒加载，那么当查询Address时，就不会把address相关的Person也给查询出来了。
	//那么当查询person2时，就需要将懒加载去掉了，因为我查询人员的时候需要获取该人员的地址。如果使用上懒加载反而会拖垮性能。这个时候需要的是一次性查询出来
	//懒加载是需要时才去查询，而此时person2是最需要Address的所以他不需要懒加载。address来说他不需要person2那就可以使用懒加载了
	@ManyToMany(mappedBy="addresses",cascade=CascadeType.ALL,targetEntity=Person2.class,fetch=FetchType.LAZY)
	private Set<Person2> persons2= new HashSet<Person2>();

	
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