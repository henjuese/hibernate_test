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
			 ������������ʽ
			 Person p=new Person();
			p.setId(3);
			p.setName("����");
			
			Card card=new Card();
			card.setDate(new Date());
			card.setPerson(p);
			 * */			
			
			//����card���������ʽ
			Person p=new Person();
			p.setId(3);
			p.setName("����");
			
			Card card=new Card();
			card.setId(1);
			card.setDate(new Date());
			card.setPerson(p);			
						
			//�������������card��
	         //ID STARTDATE           PERSON
	         //----------- ----------- -----------
	        //  1 2012/9/12 2           3�����3��Ӧperson�����������ֵ��
			
			//����������ʽ������card��
			 //ID STARTDATE
			 //----------- -----------
			 //1 2012/9/11 2
			//�����1����card�������ֵ��Ҳ������person���Ӧ�����ֵ��
			//ΪʲôҪ��card��������������أ�
			//��ΪPerson�������������һ�������ı�card����person�����ı�card��������person����ÿ���˵�һЩ��Ϣ��������Ҫ��card�����������
			
			
			
			
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
