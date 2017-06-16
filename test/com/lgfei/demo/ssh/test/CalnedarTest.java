package com.lgfei.demo.ssh.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalnedarTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ExceptiveCalendarVO ecVO1 = new ExceptiveCalendarVO();
		ecVO1.setCalendarId(1L);
		ecVO1.setExceptiveDate(new Date("2014/09/01"));
		ecVO1.setExceptiveType("0");
		ExceptiveCalendarVO ecVO2 = new ExceptiveCalendarVO();
		ecVO2.setCalendarId(1L);
		ecVO2.setExceptiveDate(new Date("2014/09/09"));
		ecVO2.setExceptiveType("1");
		ExceptiveCalendarVO ecVO3 = new ExceptiveCalendarVO();
		ecVO3.setCalendarId(1L);
		ecVO3.setExceptiveDate(new Date("2014/09/11"));
		ecVO3.setExceptiveType("0");
		ExceptiveCalendarVO ecVO4 = new ExceptiveCalendarVO();
		ecVO4.setCalendarId(1L);
		ecVO4.setExceptiveDate(new Date("2014/09/13"));
		ecVO4.setExceptiveType("1");
		List<ExceptiveCalendarVO> exceptiveList = new ArrayList<ExceptiveCalendarVO>();
		exceptiveList.add(ecVO1);
		exceptiveList.add(ecVO2);
		exceptiveList.add(ecVO3);
		exceptiveList.add(ecVO4);
		WorkingCalendarVO wcVO = new WorkingCalendarVO();
		wcVO.setCalendarId(1L);
		wcVO.setWorkingDay("2,3,4,5,6");
		wcVO.setExceptiveList(exceptiveList);
		
		CalnedarTest test = new CalnedarTest();
		
		Map<String, List<Date>> resultMap = new HashMap<String, List<Date>>();
		resultMap = test.getWorkingAndExceptive(wcVO, new Date("2014/09/01"), new Date("2014/10/30"));
		for (int i = 0; i < resultMap.get("except").size(); i++) {
			System.out.println(resultMap.get("except").get(i).toLocaleString());
		}
	}
	
	public Map<String, List<Date>> getWorkingAndExceptive(
			WorkingCalendarVO wcVO,
			Date startDate,
			Date endDate)
	{
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		
		Map<String, List<Date>> resultMap = new HashMap<String, List<Date>>();
		
		List<Date> exceptDateList = new ArrayList<Date>();
		List<Date> workDateList = new ArrayList<Date>();
		
		while(startCal.before(endCal) || startCal.equals(endCal)){
			String dayOfWeek = startCal.get(Calendar.DAY_OF_WEEK)+"";
			if(!wcVO.getWorkingDay().contains(dayOfWeek)){
				exceptDateList.add(startCal.getTime());
			}else{
				workDateList.add(startCal.getTime());
			}
			startCal.add(startCal.DATE, 1);
		}
		
		List<ExceptiveCalendarVO> exceptVOList = new ArrayList<ExceptiveCalendarVO>();
		exceptVOList = wcVO.getExceptiveList();
		List<ExceptiveCalendarVO> tempList = new ArrayList<ExceptiveCalendarVO>();
		for (ExceptiveCalendarVO exceptiveCalendarVO : exceptVOList) {
			if(exceptiveCalendarVO.getExceptiveType().equals("1")){
				tempList.add(exceptiveCalendarVO);
			}
		}
		
		exceptVOList.removeAll(tempList);
		
		for (ExceptiveCalendarVO exceptiveCalendarVO : exceptVOList) {
			exceptDateList.add(exceptiveCalendarVO.getExceptiveDate());
		}
		
		resultMap.put("work", workDateList);
		resultMap.put("except", exceptDateList);
		
		return resultMap;
	}

}

class WorkingCalendarVO{
	private Long calendarId;
	private String workingDay;
	private String firstDay;
	private List<ExceptiveCalendarVO> exceptiveList;
	public String getWorkingDay() {
		return workingDay;
	}
	public void setWorkingDay(String workingDay) {
		this.workingDay = workingDay;
	}
	public String getFirstDay() {
		return firstDay;
	}
	public void setFirstDay(String firstDay) {
		this.firstDay = firstDay;
	}
	public Long getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(Long calendarId) {
		this.calendarId = calendarId;
	}
	public List<ExceptiveCalendarVO> getExceptiveList() {
		return exceptiveList;
	}
	public void setExceptiveList(List<ExceptiveCalendarVO> exceptiveList) {
		this.exceptiveList = exceptiveList;
	}
	
}

class ExceptiveCalendarVO{
	private Long calendarId;
	private String exceptiveType;
	private Date exceptiveDate;
	public String getExceptiveType() {
		return exceptiveType;
	}
	public void setExceptiveType(String exceptiveType) {
		this.exceptiveType = exceptiveType;
	}
	public Date getExceptiveDate() {
		return exceptiveDate;
	}
	public void setExceptiveDate(Date exceptiveDate) {
		this.exceptiveDate = exceptiveDate;
	}
	public Long getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(Long calendarId) {
		this.calendarId = calendarId;
	}
	
}
