package cn.more.view;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.many2many.domain.Courses;
import cn.many2many.domain.StuCourse;
import cn.many2many.domain.Students;
import cn.many2many.domain.many2many2.Address;
import cn.many2many.domain.many2many2.Person2;
import cn.more.domain.Student;
import cn.more.util.HibernateUtil;

public class TestMany2Many2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ÿ�����е����Ե�get��set������������޸ģ�����Ϊ����get��������������ʽ����ȻhibernateҲ�ᱨ������ʾ�Ҳ�����ĳĳ�С�
		//HibernateUtil.getCurrentSession();
		test2();
	}
	
	public static void test2(){
		Session session=HibernateUtil.getCurrentSession();
		Transaction tran=null;
		try{
			tran=session.beginTransaction();
			
			Address a = new Address("�������2hao");
			//�ٳ־û�Address����(��Ӧ�ڲ���ӱ��¼)
			session.persist(a);
			Address a2 = new Address("�������3hao");
			//�ٳ־û�Address����(��Ӧ�ڲ���ӱ��¼)
			session.persist(a2);
			
			//����һ��Person����
			Person2 p = new Person2();
			//����Person��NameΪYeeku�ַ���
			p.setName("bao2");
			p.setAge(29);
			//���ﲢ���Ὣa��p�Ĺ�ϵ��ӵ���ϵ�����棬
			//��Ϊperson2.hbm.xml��������inverse="true"������������address��������
			p.getAddresses().add(a);
			//�־û�Person����(��Ӧ�ڲ��������¼)
			session.save(p);
			//����һ��˲̬��Address����
		
			tran.commit();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tran.rollback();
		}finally{
			if(session!=null&&session.isOpen())
			{
				session.close();
			}
		}
	}
	
	public static void test(){
		Session session=HibernateUtil.getCurrentSession();
		Transaction tran=null;
		try{
			tran=session.beginTransaction();
			//����һ��Person����
			Person2 p = new Person2();
			//����Person��NameΪYeeku�ַ���
			p.setName("Yeeku");
			p.setAge(29);
			//�־û�Person����(��Ӧ�ڲ��������¼)
			session.save(p);
			
			//����һ��˲̬��Address����
			Address a = new Address("�������");
			//������Person��Address֮��Ĺ�����ϵ
			a.getPersons2().add(p);
			//�ٳ־û�Address����(��Ӧ�ڲ���ӱ��¼)
			session.persist(a);
			
			
			//����һ��˲̬��Address����
			Address a2 = new Address("�Ϻ����");
			//������Person��Address֮��Ĺ�����ϵ
			a2.getPersons2().add(p);
			//�ٳ־û�Address����(��Ӧ�ڲ���ӱ��¼)
			session.persist(a2);
			
			tran.commit();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tran.rollback();
		}finally{
			if(session!=null&&session.isOpen())
			{
				session.close();
			}
		}
	}

}
