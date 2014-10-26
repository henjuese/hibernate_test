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
		//每个类中的属性的get，set方法不能随便修改，必须为：“get属性名”这种形式，不然hibernate也会报创建表示找不报到某某列。
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
			//双向关联与拦截在恍然大悟啊
			//这里做了双向关联，但是还是有主从关系。Address在注解里面被定义为从表。所以当执行test的时候并不会在中间表中添加数据
			//这里就有了，那为什么还要做双向关联呢？就下面这句告诉我答案了，查询的时候，通过地址，直接可以get出Person2的集合
			//因为在Address里面做了关联Person2的设置了。
			//那懒加载是什么问题你？
			//在Address里面配置懒加载后，hibernate会先查询出addressid=1的数据，当下面我调用了add.getPersons2时才会去库里面查询出
			//该地址下面有多少用户
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
			
			Address a = new Address("广州天河4");
			session.persist(a);
			
			Address a2 = new Address("广州天河5");
			//再持久化Address对象(对应于插入从表记录)
			session.persist(a2);
			
			Person2 p = new Person2();
			p.setName("bao");
			p.setAge(30);
			
			//单项关联时，我把配置放到了person2里面，这样person2去关联address，就是说person2是主导类
			//所以当更新人的时候，这一句会同时更新关联表
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
			
			Address a = new Address("广州天河");
			//虽然做了双向关联，这里更新时，并不会在中间表中加入他们之间的关系
			//因为主导权在Person2，只有Person2主动更新address时，中间表才有用。
			//address中配置了mapped=""...权限已经反转给了Person2了
			a.getPersons2().add(p);
			session.persist(a);

			Address a2 = new Address("上海虹口");
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
