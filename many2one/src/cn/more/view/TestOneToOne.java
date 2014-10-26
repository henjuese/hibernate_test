package cn.more.view;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.more.util.HibernateUtil;
import cn.one2one.domain.Card;
import cn.one2one.domain.Person;

public class TestOneToOne {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Session session=HibernateUtil.getCurrentSession();
		test();
	}
	
	public static void test(){
		Session session=HibernateUtil.getCurrentSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			/*
			 基于主键的形式
			 Person p=new Person();
			p.setId(3);
			p.setName("哈啊");
			
			Card card=new Card();
			card.setDate(new Date());
			card.setPerson(p);
			 * */			
			
			//基于card表外键的形式
			Person p=new Person();
			p.setId(3);
			p.setName("哈啊");
			
			Card card=new Card();
			card.setId(1);
			card.setDate(new Date());
			card.setPerson(p);			
						
			//基于外键创建的card表；
	         //ID STARTDATE           PERSON
	         //----------- ----------- -----------
	        //  1 2012/9/12 2           3（这个3对应person表里面的主键值）
			
			//基于主键形式创建的card表
			 //ID STARTDATE
			 //----------- -----------
			 //1 2012/9/11 2
			//（这个1既是card表的主键值，也是他与person表对应的外键值）
			//为什么要在card表里面设置外键呢？
			//因为Person在设计是他就是一个独立的表，card是与person关联的表，card里面存放了person里面每个人的一些信息；所以需要在card里面设置外键
			
			
			
			
			session.save(p);
			session.save(card);
			
			tx.commit();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
		
	}

}
