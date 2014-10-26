/**
 *  ������Ҫ��ʾ�ˣ�openSession��getCurrentSession����֮�����ϵ��
 *  �Ӵ���getCurrentSession���Կ�������ʵ����һ��openSession;����ΪgetCurrentSession���̹߳����������������۴������ٸ�session����ʵ����һ��session
 */
package cn.more.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

final public class HibernateUtil {

	private static SessionFactory sessionFactory=null;
	//ʹ���ֲ߳̾�ģʽ��ThreadLocal�����߳�
	private static ThreadLocal<Session> thread =new ThreadLocal<Session>();
	private HibernateUtil(){
		
	}
	
	static{                     
		Configuration cfg = new AnnotationConfiguration();
		//ʹ��ע��Ļ�ȡsessionFactory�ķ�������xml�����ļ��ķ�����һ��Ŷ��
		sessionFactory=cfg.configure().buildSessionFactory();
	}
	//��ȡһ��ȫ�µ�session
	public static Session openSession(){
		return sessionFactory.openSession();
	}
	//��ȡ���̹߳�����session
	public static Session getCurrentSession(){
		Session session=thread.get();
		if(session==null){
			session=sessionFactory.openSession();
			//��session���̰߳�
			thread.set(session);
		}
		return session;
	}
	
}
