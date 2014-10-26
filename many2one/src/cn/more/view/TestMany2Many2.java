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
		//每个类中的属性的get，set方法不能随便修改，必须为：“get属性名”这种形式，不然hibernate也会报创建表示找不报到某某列。
		//HibernateUtil.getCurrentSession();
		test2();
	}
	
	public static void test2(){
		Session session=HibernateUtil.getCurrentSession();
		Transaction tran=null;
		try{
			tran=session.beginTransaction();
			
			Address a = new Address("广州天河2hao");
			//再持久化Address对象(对应于插入从表记录)
			session.persist(a);
			Address a2 = new Address("广州天河3hao");
			//再持久化Address对象(对应于插入从表记录)
			session.persist(a2);
			
			//创建一个Person对象
			Person2 p = new Person2();
			//设置Person的Name为Yeeku字符串
			p.setName("bao2");
			p.setAge(29);
			//这里并不会将a与p的关系添加到关系库里面，
			//因为person2.hbm.xml中配置了inverse="true"不做关联，让address来做主导
			p.getAddresses().add(a);
			//持久化Person对象(对应于插入主表记录)
			session.save(p);
			//创建一个瞬态的Address对象
		
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
			//创建一个Person对象
			Person2 p = new Person2();
			//设置Person的Name为Yeeku字符串
			p.setName("Yeeku");
			p.setAge(29);
			//持久化Person对象(对应于插入主表记录)
			session.save(p);
			
			//创建一个瞬态的Address对象
			Address a = new Address("广州天河");
			//先设置Person和Address之间的关联关系
			a.getPersons2().add(p);
			//再持久化Address对象(对应于插入从表记录)
			session.persist(a);
			
			
			//创建一个瞬态的Address对象
			Address a2 = new Address("上海虹口");
			//先设置Person和Address之间的关联关系
			a2.getPersons2().add(p);
			//再持久化Address对象(对应于插入从表记录)
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
