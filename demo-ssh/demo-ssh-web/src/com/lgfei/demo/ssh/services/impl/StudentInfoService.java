package com.lgfei.demo.ssh.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.lgfei.demo.ssh.daos.IStudentDao;
import com.lgfei.demo.ssh.pojo.Student;
import com.lgfei.demo.ssh.services.IStudentInfoService;

public class StudentInfoService implements IStudentInfoService {
	private static final int PAGE_SIZE = 10;
	
	private IStudentDao stuDao;

	public void setStuDao(IStudentDao stuDao) {
		this.stuDao = stuDao;
	}

	@Override
	public List<Student> findAll() {
		// TODO findAll
		return stuDao.findAll();
	}

	@Override
	public Student findOne(String stuNo) {
		// TODO findOne
		return stuDao.findOne(stuNo);
	}
	
	@Override
	public void add(Student stu) {
		// TODO add
		stuDao.insert(stu);
	}

	@Override
	public List<Student> findPageList(Student stu, int currPage) {
		// TODO findPageList
		return stuDao.findPageList(stu, currPage,PAGE_SIZE);
	}

	@Override
	public int getLastPage() {
		int amoutCount = this.findAll().size();
		int lastPage = 1;
		int m = amoutCount % PAGE_SIZE;
		if(m == 0){
			lastPage = amoutCount / PAGE_SIZE;
		}else{
			lastPage = amoutCount / PAGE_SIZE + 1;
		}
		return lastPage;
	}

	@Override
	public void update(Student stu) {
		// TODO update
		stuDao.update(stu);
	}

	@Override
	public void delete(String stuNos) {
		// TODO delete
		if(null == stuNos || "".equals(stuNos)){
			return;
		}
		String[] stuIdArr = stuNos.split(",");
		List<Student> stuList = new ArrayList<Student>();
		for (String stuNo : stuIdArr) {
		    Student stu = new Student();
			stu.setNo(stuNo);
			stuList.add(stu);
		}
		stuDao.delete(stuList);
	}

}
