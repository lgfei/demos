package com.lgfei.demo.ssh.daos;

import java.util.List;

import com.lgfei.demo.ssh.pojo.Student;

public interface IStudentDao {
	
	public List<Student> findAll();
	
	public List<Student> findPart(Student stu);
	
	public Student findOne(String stuNo);
	
	public int insert(Student stu);
	
	public void update(Student stu);
	
	public void delete(List<Student> stuList);
	
	public List<Student> findPageList(Student stu,int currPage,int pageSize);
}
