package com.lgfei.demo.ssh.services;

import java.util.List;

import com.lgfei.demo.ssh.pojo.Student;

public interface IStudentInfoService {

	public List<Student> findAll();
	
	public Student findOne(String no);
	
	public void add(Student stu);
	
	public List<Student> findPageList(Student stu,int currPage);
	
	public int getLastPage();
	
	public void update(Student stu);
	
	public void delete(String stuIds);
}
