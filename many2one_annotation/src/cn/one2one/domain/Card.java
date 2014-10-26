package cn.one2one.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tb_card")
public class Card implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private int id;
	@Column
	private Date date;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=Person.class)
	@JoinColumn(name="p_id",nullable=false,updatable=false)
	private Person person;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
}
