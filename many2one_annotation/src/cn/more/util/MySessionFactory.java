package cn.more.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//��ʹ��hibernate������Ŀ�ǣ�һ��Ҫ��ֻ֤��һ��SessionFactory
//һ�����ݿ��Ӧһ��sessionFactory����
//����Ǹ�������
final public class MySessionFactory {

	private static SessionFactory sessionFactory=null;
	
	private MySessionFactory(){
		
	}
	static{
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
}
