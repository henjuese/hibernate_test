package cn.many2many.domain;

import java.util.HashSet;
import java.util.Set;

public class Courses implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int cid;
	private String cname;
	private Set<StuCourse> studcourses = new HashSet<StuCourse>(0);
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<StuCourse> getStudcourses() {
		return studcourses;
	}
	public void setStudcourses(Set<StuCourse> studcourses) {
		this.studcourses = studcourses;
	}
	
	

}
