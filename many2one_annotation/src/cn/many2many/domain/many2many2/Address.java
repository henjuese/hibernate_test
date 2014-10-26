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
	
	//@Transient//�ڵ����Զ�ʱ���Ѿ���Person2�����ˣ�����Ͳ���Ҫ����ֶΣ�˫�����ʱ����Ҫ
	//������Ҫ����˫�����������person2֮ǰ�����ò���
	//���ﲻҪ����������Ŷ�������ˣ��Ժ����Ա�Ͳ���ֱ�Ӳ�ѯ��address��
	//˫�����ʱ������ʵ��以����������Ա�����Ϊ@ManyToMany�����໥ָ��targetEntity���ԡ�
	//����ֻ��һ��ʵ���@ManyToManyע����Ҫָ��mappedBy���ԣ�ָ��targetEntity�ļ����������ơ�
	//�����mappedBy="addresses"�е�address��Person2�еļ�����������
	//ʲôʱ��ʹ���������أ������person2�����������أ���ô����ѯAddressʱ���Ͳ����address��ص�PersonҲ����ѯ�����ˡ�
	//��ô����ѯperson2ʱ������Ҫ��������ȥ���ˣ���Ϊ�Ҳ�ѯ��Ա��ʱ����Ҫ��ȡ����Ա�ĵ�ַ�����ʹ���������ط������Ͽ����ܡ����ʱ����Ҫ����һ���Բ�ѯ����
	//����������Ҫʱ��ȥ��ѯ������ʱperson2������ҪAddress������������Ҫ�����ء�address��˵������Ҫperson2�ǾͿ���ʹ����������
	@ManyToMany(mappedBy="addresses",cascade=CascadeType.ALL,targetEntity=Person2.class,fetch=FetchType.LAZY)
	private Set<Person2> persons2= new HashSet<Person2>();

	
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