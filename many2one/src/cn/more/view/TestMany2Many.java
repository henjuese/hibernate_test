package cn.more.view;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.many2many.domain.Courses;
import cn.many2many.domain.StuCourse;
import cn.many2many.domain.Students;
import cn.more.domain.Student;
import cn.more.util.HibernateUtil;

public class TestMany2Many {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ÿ�����е����Ե�get��set������������޸ģ�����Ϊ����get��������������ʽ����ȻhibernateҲ�ᱨ������ʾ�Ҳ�����ĳĳ�С�
		//HibernateUtil.getCurrentSession();
		test();
	}
	
	public static void test(){
		Session session=HibernateUtil.getCurrentSession();
		Transaction tran=null;
		try{
			tran=session.beginTransaction();
			Students s=new Students();
			s.setSname("bao");
			
			Courses c=new Courses();
			c.setCname("����");
			
			StuCourse sc=new StuCourse();
			sc.setCourse(c);
			sc.setStudent(s);
			session.save(s);
			session.save(c);
			session.save(sc);
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
