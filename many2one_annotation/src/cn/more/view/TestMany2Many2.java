package cn.more.view;

import java.util.Set;

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
		//test();
		getPerson2();
		//getAddress();
	}
	
	
	public static void getAddress(){
		Session session=HibernateUtil.getCurrentSession();
		Transaction tran=null;
		try{
			tran=session.beginTransaction();
			Address add=(Address)session.get(Address.class, 1);
			System.out.println("add="+add.getAddressdetail());
			//˫������������ڻ�Ȼ����
			//��������˫����������ǻ��������ӹ�ϵ��Address��ע�����汻����Ϊ�ӱ����Ե�ִ��test��ʱ�򲢲������м�����������
			//��������ˣ���Ϊʲô��Ҫ��˫������أ��������������Ҵ��ˣ���ѯ��ʱ��ͨ����ַ��ֱ�ӿ���get��Person2�ļ���
			//��Ϊ��Address�������˹���Person2�������ˡ�
			//����������ʲô�����㣿
			//��Address�������������غ�hibernate���Ȳ�ѯ��addressid=1�����ݣ��������ҵ�����add.getPersons2ʱ�Ż�ȥ�������ѯ��
			//�õ�ַ�����ж����û�
			Set<Person2> ps=add.getPersons2();
			for(Person2 p:ps){
				System.out.println("person="+p.getName());
			}
			tran.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}finally{
			if(session!=null&&session.isOpen())
			{
				session.close();
			}
		}
	}
	
	public static void getPerson2(){
		Session session=HibernateUtil.getCurrentSession();
		Transaction tran=null;
		try{
			tran=session.beginTransaction();
			Person2 p=(Person2)session.get(Person2.class, 1);
			Set<Address> adds=p.getAddresses();
			System.out.println("p="+p.getName());
			for(Address add:adds){
				System.out.println("add="+add.getAddressdetail());
			}
			tran.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}finally{
			if(session!=null&&session.isOpen())
			{
				session.close();
			}
		}
	}
	
	public static void test2(){
		Session session=HibernateUtil.getCurrentSession();
		Transaction tran=null;
		try{
			tran=session.beginTransaction();
			
			Address a = new Address("�������4");
			session.persist(a);
			
			Address a2 = new Address("�������5");
			//�ٳ־û�Address����(��Ӧ�ڲ���ӱ��¼)
			session.persist(a2);
			
			Person2 p = new Person2();
			p.setName("bao");
			p.setAge(30);
			
			//�������ʱ���Ұ����÷ŵ���person2���棬����person2ȥ����address������˵person2��������
			//���Ե������˵�ʱ����һ���ͬʱ���¹�����
			p.getAddresses().add(a);
			
			session.save(p);		
			tran.commit();
			
		}catch (Exception e) {
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
			
			Person2 p = new Person2();
			p.setName("Yeeku");
			p.setAge(29);
			session.save(p);
			
			Address a = new Address("�������");
			//��Ȼ����˫��������������ʱ�����������м���м�������֮��Ĺ�ϵ
			//��Ϊ����Ȩ��Person2��ֻ��Person2��������addressʱ���м������á�
			//address��������mapped=""...Ȩ���Ѿ���ת����Person2��
			a.getPersons2().add(p);
			session.persist(a);

			Address a2 = new Address("�Ϻ����");
			a2.getPersons2().add(p);
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
