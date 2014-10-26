package cn.more.domain;

import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="tb_department")
public class Department implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	
	//@Transient,设置该属性与配置无关
	//这里使用了懒加载，因为我查询department时，我不想查询他对应着多少student
	//mappedBy="depert",
	@OneToMany(cascade=CascadeType.ALL,mappedBy="depert",fetch=FetchType.LAZY,targetEntity=Student.class)
	private Set<Student> stu;//@JoinColumn(name="depart_id",nullable=false)//不配上这个会出现一张中间表
	
	public Set<Student> getStu() {
		return stu;
	}
	public void setStu(Set<Student> stu) {
		this.stu = stu;
	}
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
}
