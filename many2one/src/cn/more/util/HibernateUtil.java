/**
 *  该类主要演示了，openSession和getCurrentSession他们之间的联系；
 *  从创建getCurrentSession可以看出他其实就是一个openSession;正因为getCurrentSession和线程关联所以他才能无论创建多少个session，其实都是一个session
 */
package cn.more.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

final public class HibernateUtil {

	private static SessionFactory sessionFactory=null;
	//使用线程局部模式；ThreadLocal本地线程
	private static ThreadLocal<Session> thread =new ThreadLocal<Session>();
	private HibernateUtil(){
		
	}
	
	static{
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	//获取一个全新的session
	public static Session openSession(){
		return sessionFactory.openSession();
	}
	//获取和线程关联的session
	public static Session getCurrentSession(){
		Session session=thread.get();
		if(session==null){
			session=sessionFactory.openSession();
			//将session和线程绑定
			thread.set(session);
		}
		return session;
	}
	
}
