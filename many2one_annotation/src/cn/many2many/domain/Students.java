package cn.many2many.domain;

import java.util.HashSet;
import java.util.Set;

public class Students implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int sid;
	private String sname;
	private Set<StuCourse> studcourses = new HashSet<StuCourse>(0);
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Set<StuCourse> getStudcourses() {
		return studcourses;
	}
	public void setStudcourses(Set<StuCourse> studcourses) {
		this.studcourses = studcourses;
	}
	

}
