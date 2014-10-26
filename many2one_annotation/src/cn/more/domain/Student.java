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
	//fetch=FetchType.EAGER：在查询用户时，需要s.getDepert().getName()直接就获取Depertment对象，那么就应该将懒加载去掉。就是读取student之后就应该查询deptment了
	//因为他们是有关联的多对一的关联哦！所以可以这样做。只要警用懒加载就，在查询部门的时候直接使用getDepert()就可以获取到部门对象了
	//optional=false时join 查询关系为inner join,为true时：关系为left join
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER,targetEntity=Department.class)
	//JoinColumn添加关联字段，添加外键，定义外键的名称，nullable是否允许为空；updatable使用ORM框架更新操作时，该字段是否出现在update语句中
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
