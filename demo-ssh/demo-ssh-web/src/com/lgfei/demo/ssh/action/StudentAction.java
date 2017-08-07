package com.lgfei.demo.ssh.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.lgfei.demo.ssh.pojo.Student;
import com.lgfei.demo.ssh.services.IStudentInfoService;
import com.opensymphony.xwork2.ActionSupport;

public class StudentAction extends ActionSupport {

	private static final long serialVersionUID = 3018528219830091759L;
	
	//service 对象
	private IStudentInfoService stuService;
	
	public void setStuService(IStudentInfoService stuService) {
		this.stuService = stuService;
	}
	
	//VO对象
	private Student stu;
	public Student getStu() {
		return stu;
	}
	public void setStu(Student stu) {
		this.stu = stu;
	}
	
	private int currPage;//当前页
	private int lastPage;//总页数
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	
	private String actFlag;//编辑动作
	public String getActFlag() {
		return actFlag;
	}
	public void setActFlag(String actFlag) {
		this.actFlag = actFlag;
	}
	
	private String stuNos;//删除的no
	public String getStuNos() {
		return stuNos;
	}
	public void setStuNos(String stuNos) {
		this.stuNos = stuNos;
	}

	public String edit(){
		if(null != stu){
			if("add".equals(this.actFlag)){
				stuService.add(stu);
			}else if("update".equals(this.actFlag)){
				stuService.update(stu);
			}else{
				//不是新增也不是修改，那是什么？
				System.out.println("actFlag="+actFlag);
			}
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String findOne(){
		if(null != stu){
			Student vo = stuService.findOne(stu.getNo());
			Gson gson = new Gson();
			String jsonStr = gson.toJson(vo);
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			try {
				ServletActionContext.getResponse().getWriter().print(jsonStr);
			} catch (IOException e) {
				Log4JLogger log = new Log4JLogger();
				log.error("数据返回异常");
				e.printStackTrace();
			}finally{
				try {
					ServletActionContext.getResponse().getWriter().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String findPageList(){
		if(null != stuService){
			List<Student> stuList = null;
			this.lastPage = stuService.getLastPage();
			//session.put("lastPage", lastPage);
			stuList = stuService.findPageList(stu, currPage);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", stuList);
			map.put("lastPage", lastPage);
			Gson gson = new Gson();
			String jsonStr = gson.toJson(map);
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			try {
				ServletActionContext.getResponse().getWriter().print(jsonStr);
			} catch (IOException e) {
				Log4JLogger log = new Log4JLogger();
				log.error("数据返回异常");
				e.printStackTrace();
			}finally{
				try {
					ServletActionContext.getResponse().getWriter().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String del(){
		if(null != stuNos){
			stuService.delete(stuNos);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", "success");
			Gson gson = new Gson();
			String jsonStr = gson.toJson(map);
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			try {
				ServletActionContext.getResponse().getWriter().print(jsonStr);
			} catch (IOException e) {
				Log4JLogger log = new Log4JLogger();
				log.error("数据返回异常");
				e.printStackTrace();
			}finally{
				try {
					ServletActionContext.getResponse().getWriter().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return SUCCESS;
		}
		return ERROR;
	}
}
