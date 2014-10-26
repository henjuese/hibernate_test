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
	
		//testMoreToOne();//���һ����
		//testStudent();
		getDepter();//һ�Զ����
		//deleteDepter();
		//testCatch();
		//testCatchTwo();
	}
	//һ�������������ڣ������������
	public static void testCatchTwo(){
		Session session=null;
		Transaction tran=null;
		try{
			session=HibernateUtil.openSession();
			System.out.println("*****��һ��*********");
     		Department depart=(Department)session.get(Department.class, 22);
			System.out.println("depart="+depart.getName());								
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();//���ر�ʱ��һ�����棨session�������ͽ����ˣ�����ջ��档
			}
		}
		
		//�ر�session���ٴβ�ѯ22�����ݣ�����û�г���sql��䣬˵����ȥ���������������ˡ�
		try{
			session=HibernateUtil.openSession();
			//tran=session.beginTransaction();				
			System.out.println("*****�ڶ���*********");
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
	
	
	//�������
	public static void testCatch(){
		Session session=HibernateUtil.getCurrentSession();
		Transaction tran=null;
		try{
			tran=session.beginTransaction();
			
			
			Department d=new Department();
			d.setName("�з���444");		
			session.save(d);
			System.out.println("**************");
			//���ﲢ����ȥ��ѯ���ݿ⣬��ִ����ʾ���������ﲢû�в���sql��ѯ��䣻
			//����˵��save�Ȼᱣ�浽session�У�Ϊһ�����棩���ʱ��d����ҲΪ�־�״̬����get��ѯʱ�ȴ�session��ȥ�ң�����Ҳ����Ż�ȥ���������ҡ�
			Department depart=(Department)session.get(Department.class, d.getId());
			System.out.println("depart="+depart.getName());
						
			tran.commit();
			
			
		}catch(Exception e){
			if(tran!=null){
				//���������ع���ȥ����ȡ������Ĳ���
				tran.rollback();
			}
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
	}
	
	
	
	
	//�������ÿ���������depart�����ã�Ҳ������student�����ã�depart�����ã������departʱ���Զ����student����֮һ��
	//һ����˵����Ҫ���������depart�����н�������
	//����ɾ��
	public static void deleteDepter(){
		Session session=HibernateUtil.getCurrentSession();
		Transaction tran=null;
		try{
			tran=session.beginTransaction();
			Department depart=(Department)session.get(Department.class, 27);
			//�����ļ�������Department.hbm.xml��set��ǩ������cascade="delete"��ʾ��ɾ��Departʱ��student��Ӧ��Ҳɾ������������ɾ����
			//ɾ�������У��ȸ���student����depart������������Ϊnull��Ȼ������ɾ��student����ɾ��depart
			session.delete(depart);
			tran.commit();
			
			
		}catch(Exception e){
			if(tran!=null){
				//���������ع���ȥ����ȡ������Ĳ���
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
		System.out.println(s.getName()+",���ţ�"+s.getDepert().getName());
	}
	
	public static Student getStudent(){
		Session session=HibernateUtil.getCurrentSession();
		Transaction tran=null;
		Student s=null;
		try{
			tran=session.beginTransaction();
			s=(Student)session.get(Student.class, 1);
			
			//��***********�������ؽ������
			//1.Ϊ����department�ڹر�session����Ȼ���ã�������Ҫ�õ�hibernate����������������
			//Hibernate.initialize(s.getDepert());
			//2.�����ڲ���Ҫ�����ص������ļ������class���������lazy=false�����������department�����ļ��������
			
			
			tran.commit();
			
			
		}catch(Exception e){
			if(tran!=null){
				//���������ع���ȥ����ȡ������Ĳ���
				tran.rollback();
			}
		}finally{
			if(session!=null&&session.isOpen()){
				//�ر�close�Ͳ���ʹ���������ˣ���close�ر�֮ǰ�ǿ���ʹ�������ص�
				session.close();
			}
		}
		return s;
	}
	
	
	//���һ
	public static void testMoreToOne(){
		Session session=HibernateUtil.getCurrentSession();
		Transaction tran=null;
		try{
			tran=session.beginTransaction();
			
			Student s=new Student();
			s.setName("����2");
			Department d=new Department();
			d.setName("�з���2");
			s.setDepert(d);
			//��Ϊstudent���õ�deptart�������ȱ���depart��Ȼ���ڱ���student������ü������������ֱ�����depart����
			//�����student�����ļ�����������������ֻ��Ҫ����student����,cascade=all������ok��
			session.save(d);
			session.save(s);			
			tran.commit();
			
			
		}catch(Exception e){
			if(tran!=null){
				//���������ع���ȥ����ȡ������Ĳ���
				tran.rollback();
			}
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
	}
	
	//һ�Զౣ���ϵ
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
				s.setName("С��");
				Student s2=new Student();
				s2.setName("С��1");
				Student s3=new Student();
				s3.setName("С��2");
				
				Set set=new HashSet<Student>();
				set.add(s);
				set.add(s2);
				set.add(s3);
				
				Department d=new Department();
				d.setName("��Ʒ��");
				
				//set�������µ�ѧ�������ʱ�򱣴沿�ŵ�ʱ���������ѧ��ѧ�������ʱ��Ӧ��ʹ�ü�����ϵ����������ӽ�ȥ
				//���´�����ѧ����ò�����ϵҲ��ӽ�ȥ
				//(��������)
				//��������Ҫ��set����������cascade="save-update"������ϵ ��
				d.setStu(set);
				
				
				session.save(d);
				
				tran.commit();
				
				
			}catch(Exception e){
				e.printStackTrace();
				if(tran!=null){
					//���������ع���ȥ����ȡ������Ĳ���
					tran.rollback();
				}
			}finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}
			}
		}
	

}
