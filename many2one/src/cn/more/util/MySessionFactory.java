package cn.more.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//在使用hibernate开发项目是，一定要保证只有一个SessionFactory
//一个数据库对应一个sessionFactory对象
//这个是个单体类
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
