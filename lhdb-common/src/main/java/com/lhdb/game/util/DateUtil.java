package com.lhdb.game.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
	
	private static final SimpleDateFormat dd = new SimpleDateFormat("dd");
	
	public static Date addDay(int day,Date date){
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(c.DAY_OF_MONTH, day);
		return c.getTime();
	}

	public static Date getDate(String dateStr,String format) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.parse(dateStr);
	}
	
	 public static String getThisDay()
    {
        return dd.format(new Date());
    }
	
	public static Date getDayYMD(){
		GregorianCalendar c = new GregorianCalendar();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	
	public static int getHourToday(){
		return  new GregorianCalendar().get(Calendar.HOUR_OF_DAY);
	}
	
	public static Date getDayYMDH(){
		GregorianCalendar c = new GregorianCalendar();
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static Date getDayYMDHLastHour(){
		GregorianCalendar c = new GregorianCalendar();
		c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY)-1);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static String formatDate(Date date){
		return formatDate(date,null);
	}

	public static String formatDate(Date date,String format){
		 if(date == null) return "";
		 if(format == null) format = "yyyy-MM-dd HH:mm:ss";
		 SimpleDateFormat formatter = new SimpleDateFormat(format);
		 String dateString = formatter.format(date);
		 return dateString;
	}
	
	public static String formatDate(int num,Date date){
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 Date nowDate = DateUtil.addDay(num, date);
		 String dateString = formatter.format(nowDate);
		 return dateString;
	}
	
	public static String formatDateYMDHm(Date date){
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 String dateString = formatter.format(date);
		 return dateString;
	}
	
	public static String formatDateD(Date date){
		 SimpleDateFormat formatter = new SimpleDateFormat("dd");
		 String dateString = formatter.format(date);
		 return dateString;
	}
	
	
	public static Date getMonday(Date date){
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
//		System.out.println(formatDate(c.getTime()));
		//获得周几，并以周一为1
		int day = c.get(c.DAY_OF_WEEK);
		if(day == 1) day = 7; else day--;
		//计算周一的日期
		c.add(c.DAY_OF_MONTH, -(day-1));
		return c.getTime();
	}
	
	public static Date getNextMonday(Date date){
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
//		System.out.println(formatDate(c.getTime()));
		//获得周几，并以周一为1
		int day = c.get(c.DAY_OF_WEEK);
		if(day == 1) day = 7; else day--;
		//计算周一的日期
		c.add(c.DAY_OF_MONTH, ((7-day)+1));
		return c.getTime();
	}
	
	
	public static Date getLastMonday(Date date){
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
//		System.out.println(formatDate(c.getTime()));
		//获得周几，并以周一为1
		int day = c.get(c.DAY_OF_WEEK);
		if(day == 1) day = 7; else day--;
		//计算周一的日期
		c.add(c.DAY_OF_MONTH, -(7+(day-1)));
		return c.getTime();
	}
	
	public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
        return Integer.parseInt(String.valueOf(between_days));           
    } 
	
	public static Date getTodayZeroTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		long htime = 60 * 60  * 1000;
		cal.setTimeInMillis(cal.getTimeInMillis() / htime * htime);
		return cal.getTime();
	}
	
	public static Date getZeroTime(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		long htime = 60 * 60  * 1000;
		cal.setTimeInMillis(cal.getTimeInMillis() / htime * htime);
		return cal.getTime();
	}
	
	public static void main(String[] args) {
		long dtime = 24 * 60 * 60  * 1000;
		Date today = new Date(System.currentTimeMillis() / dtime * dtime);
		Date yestoday = new Date(today.getTime() - dtime);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getTodayZeroTime()));
		System.out.println("123,321,4565,222".matches("(\\d+[,]?)+"));
		//System.out.println(formatDate(new Date()));
//		System.out.println(formatDate(0,new Date()));
		
//		System.out.println(formatDate(getDayYMD()));
		
//		System.out.println(formatDate(getDayYMDHLastHour()));
	
		
//		System.out.println(formatDate(getDayYMDHLastHour()));
//		System.out.println(formatDate(getDayYMDH()));
	
	}


	/**
	 * 获取2个时间之间的时间 包含开始时间 不包含结束时间
	 * @param b 开始时间
	 * @param e 结束时间
	 * @return
	 */
	public static Set<Date> getDates(Date b, Date e){
		Set<Date> set = new HashSet<>();
		Calendar calendar  = Calendar.getInstance();
		calendar.setTime(b);
		while(calendar.getTime().before(e)){
			set.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH,1);
		}
		return set;
	}

	/**
	 * 获取2个时间之间的时间 包含开始时间 不包含结束时间
	 * @param b 开始时间
	 * @param e 结束时间
	 * @return
	 */
	public static List<String> getDates(Date b, Date e,String format){
		if(format==null)format="yyyyMMdd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		List<String> set = new LinkedList<>();
		Calendar calendar  = Calendar.getInstance();
		calendar.setTime(b);
		while(calendar.getTime().before(e)){
			set.add(dateFormat.format(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_MONTH,1);
		}
		return set;
	}

	//获取周第一天
	public static String getStartDayOfWeek() {
		Calendar cal =Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
		return df.format(cal.getTime());
	}

	//获取上周第一天
	public static String getStartDayOfsWeek() {
		Calendar cal =Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DAY_OF_WEEK,-7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
		return df.format(cal.getTime());
	}





}
