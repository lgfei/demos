package com.lgfei.demo.ssh.daos.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.lgfei.demo.ssh.daos.IStudentDao;
import com.lgfei.demo.ssh.pojo.Student;
import com.lgfei.demo.ssh.utils.CollectionUtil;
import com.lgfei.demo.ssh.utils.DBUtil;

public class StudentDao implements IStudentDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findAll() {
		// TODO findAll
		return (List<Student>)DBUtil.getHibernateTemplate().find("from Student");
	}

	@Override
	public List<Student> findPart(Student stu) {
		// TODO findPart
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findPageList(Student stu,int currPage,int pageSize) {
		// TODO findPageList
		int firstRow = pageSize*(currPage - 1);
		SessionFactory sf=DBUtil.getHibernateTemplate().getSessionFactory();
		Session session=sf.openSession();
		Query query = session.createQuery("from Student");
		query.setFirstResult(firstRow);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Student findOne(String stuNo) {
		// TODO findOne
		String sql = "SELECT t.no AS no, "
				+ "t.name AS name, "
				+ "IFNULL(t.stu_sex,'') AS sex, "
				+ "t.idcard AS idcard "
				+ "FROM t_student t "
				+ "WHERE t.no = "+stuNo+"";
		SessionFactory sf=DBUtil.getHibernateTemplate().getSessionFactory();
		Session session=sf.openSession();
		Query query = session.createSQLQuery(sql);
		List<Object[]> reLsit = query.list();
		if(!CollectionUtil.isNullOrEmpty(reLsit)){
			Student stu = new Student();
			Object[] objs = reLsit.get(0);
			stu.setNo(objs[0].toString());
			stu.setName(objs[1].toString());
			stu.setSex(Integer.getInteger(objs[2].toString()));
			stu.setIdcard(objs[3].toString());
			return stu;
		}
		return null;
	}

	@Override
	public int insert(Student stu) {
		// TODO insert
		return (int)DBUtil.getHibernateTemplate().save(stu);
	}

	@Override
	public void update(Student stu) {
		// TODO update
	    DBUtil.getHibernateTemplate().update(stu);
	}

	@Override
	public void delete(List<Student> stuList) {
		// TODO delete
		for (Student stu : stuList) {
		    DBUtil.getHibernateTemplate().delete(stu);
		}
	}

}
