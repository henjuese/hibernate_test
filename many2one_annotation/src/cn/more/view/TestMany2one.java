package cn.more.view;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.more.domain.Department;
import cn.more.domain.Student;
import cn.more.util.HibernateUtil;

public class TestMany2one {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		//testMoreToOne();//多对一测试
		//testStudent();
		getDepter();//一对多测试
		//deleteDepter();
		//testCatch();
		//testCatchTwo();
	}
	//一级缓存生命周期，二级缓存测试
	public static void testCatchTwo(){
		Session session=null;
		Transaction tran=null;
		try{
			session=HibernateUtil.openSession();
			System.out.println("*****第一次*********");
     		Department depart=(Department)session.get(Department.class, 22);
			System.out.println("depart="+depart.getName());								
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();//当关闭时，一级缓存（session）生命就结束了，即清空缓存。
			}
		}
		
		//关闭session后再次查询22号数据，但并没有出现sql语句，说明是去二级缓存里面找了。
		try{
			session=HibernateUtil.openSession();
			//tran=session.beginTransaction();				
			System.out.println("*****第二次*********");
			Department depart=(Department)session.get(Department.class,22);
			System.out.println("depart="+depart.getName());

			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}	
		
	}
	
	
	//缓存测试
	public static void testCatch(){
		Session session=HibernateUtil.getCurrentSession();
		Transaction tran=null;
		try{
			tran=session.beginTransaction();
			
			
			Department d=new Department();
			d.setName("研发部444");		
			session.save(d);
			System.out.println("**************");
			//这里并不会去查询数据库，从执行显示来看，这里并没有产生sql查询语句；
			//所以说：save先会保存到session中（为一级缓存）这个时候d对象也为持久状态，当get查询时先从session中去找，如果找不到才会去数据里面找。
			Department depart=(Department)session.get(Department.class, d.getId());
			System.out.println("depart="+depart.getName());
						
			tran.commit();
			
			
		}catch(Exception e){
			if(tran!=null){
				//如果出错，则回滚回去，即取消上面的操作
				tran.rollback();
			}
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
	}
	
	
	
	
	//级联配置可以在主类depart中配置，也可以在student中配置，depart中配置，即添加depart时，自动添加student；反之一样
	//一般来说，主要还是添加在depart主类中进行配置
	//级联删除
	public static void deleteDepter(){
		Session session=HibernateUtil.getCurrentSession();
		Transaction tran=null;
		try{
			tran=session.beginTransaction();
			Department depart=(Department)session.get(Department.class, 27);
			//配置文件中设置Department.hbm.xml中set标签中设置cascade="delete"表示当删除Depart时将student相应的也删除掉，即级联删除；
			//删除过程中，先更新student的与depart相关联的外键至为null，然后在先删除student，在删除depart
			session.delete(depart);
			tran.commit();
			
			
		}catch(Exception e){
			if(tran!=null){
				//如果出错，则回滚回去，即取消上面的操作
				tran.rollback();
			}
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
		
	}
	
	
	
	
	public static void testStudent(){
		Student s=getStudent();
		System.out.println(s.getName()+",部门："+s.getDepert().getName());
	}
	
	public static Student getStudent(){
		Session session=HibernateUtil.getCurrentSession();
		Transaction tran=null;
		Student s=null;
		try{
			tran=session.beginTransaction();
			s=(Student)session.get(Student.class, 1);
			
			//《***********》懒加载解决方法
			//1.为了让department在关闭session后任然能用，这里需要用到hibernate代理来处理懒加载
			//Hibernate.initialize(s.getDepert());
			//2.可以在不需要懒加载的配置文件里面的class属性中添加lazy=false；这里可以在department配置文件里面添加
			
			
			tran.commit();
			
			
		}catch(Exception e){
			if(tran!=null){
				//如果出错，则回滚回去，即取消上面的操作
				tran.rollback();
			}
		}finally{
			if(session!=null&&session.isOpen()){
				//关闭close就不能使用懒加载了，在close关闭之前是可以使用懒加载的
				session.close();
			}
		}
		return s;
	}
	
	
	//多对一
	public static void testMoreToOne(){
		Session session=HibernateUtil.getCurrentSession();
		Transaction tran=null;
		try{
			tran=session.beginTransaction();
			
			Student s=new Student();
			s.setName("孔子2");
			Department d=new Department();
			d.setName("研发部2");
			s.setDepert(d);
			//因为student中用到deptart，所以先保存depart，然后在保存student；如果用级联操作则可以直接添加depart即可
			//如果在student配置文件中配置了联级，则只需要保存student即可,cascade=all这样就ok了
			session.save(d);
			session.save(s);			
			tran.commit();
			
			
		}catch(Exception e){
			if(tran!=null){
				//如果出错，则回滚回去，即取消上面的操作
				tran.rollback();
			}
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
	}
	
	//一对多保存关系
		public static void getDepter(){
			Session session=HibernateUtil.getCurrentSession();
			Transaction tran=null;
			try{
				tran=session.beginTransaction();
				/*
				 * Department d=(Department)session.get(Department.class, 22);
				Set<Student> stus=d.getStu();			
				for(Student s:stus){
					System.out.println(s.getName());
				}
				 */
				Student s=new Student();
				s.setName("小命");
				Student s2=new Student();
				s2.setName("小命1");
				Student s3=new Student();
				s3.setName("小命2");
				
				Set set=new HashSet<Student>();
				set.add(s);
				set.add(s2);
				set.add(s3);
				
				Department d=new Department();
				d.setName("产品部");
				
				//set里面有新的学生，这个时候保存部门的时候关联了这学新学生，这个时候应该使用级联关系来将他们添加进去
				//将新创建的学生与该部门联系也添加进去
				//(级联操作)
				//不过还需要在set属性中配置cascade="save-update"级联关系 ；
				d.setStu(set);
				
				
				session.save(d);
				
				tran.commit();
				
				
			}catch(Exception e){
				e.printStackTrace();
				if(tran!=null){
					//如果出错，则回滚回去，即取消上面的操作
					tran.rollback();
				}
			}finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}
			}
		}
	

}
