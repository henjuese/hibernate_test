package cn.one2one.domain;

import javax.persistence.*;

@Entity
@Table(name="tb_person")
public class Person implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	//@Transient当配置了单项一对一时，这个 字段可以不要
	@OneToOne(mappedBy="person",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=Card.class)
	private Card card;
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
	public Card getCard() {
		return card;
	}
	public void setCard(Card idCard) {
		this.card = idCard;
	}
	
}
