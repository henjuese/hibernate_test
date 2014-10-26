package cn.more.domain;

import javax.persistence.*;


@Entity
@Table(name="tb_student")
public class Student implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	//
	//fetch=FetchType.EAGER���ڲ�ѯ�û�ʱ����Ҫs.getDepert().getName()ֱ�Ӿͻ�ȡDepertment������ô��Ӧ�ý�������ȥ�������Ƕ�ȡstudent֮���Ӧ�ò�ѯdeptment��
	//��Ϊ�������й����Ķ��һ�Ĺ���Ŷ�����Կ�����������ֻҪ���������ؾͣ��ڲ�ѯ���ŵ�ʱ��ֱ��ʹ��getDepert()�Ϳ��Ի�ȡ�����Ŷ�����
	//optional=falseʱjoin ��ѯ��ϵΪinner join,Ϊtrueʱ����ϵΪleft join
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER,targetEntity=Department.class)
	//JoinColumn��ӹ����ֶΣ���������������������ƣ�nullable�Ƿ�����Ϊ�գ�updatableʹ��ORM��ܸ��²���ʱ�����ֶ��Ƿ������update�����
	@JoinColumn(name="depart_id",updatable=true,nullable=true)//,,updatable=true
	private Department depert;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Department getDepert() {
		return depert;
	}
	public void setDepert(Department depert) {
		this.depert = depert;
	}
	
}
