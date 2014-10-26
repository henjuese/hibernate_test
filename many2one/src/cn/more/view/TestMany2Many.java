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
		//每个类中的属性的get，set方法不能随便修改，必须为：“get属性名”这种形式，不然hibernate也会报创建表示找不报到某某列。
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
			c.setCname("语文");
			
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
