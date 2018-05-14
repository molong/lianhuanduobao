package com.lhdb.game.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class ToolUtil {
	
	/** 获取访问ip**/
	public static String getIpAddr(HttpServletRequest request) {
		 String ip = request.getHeader("x-forwarded-for");
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		  ip = request.getHeader("Proxy-Client-IP");
		 }
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		  ip = request.getHeader("WL-Proxy-Client-IP");
		 }
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		  ip = request.getRemoteAddr();
		 }
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		  ip = request.getHeader("http_client_ip");
		 }
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		  ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		 }
		 // 如果是多级代理，那么取第一个ip为客户ip
		 if (ip != null && ip.indexOf(",") != -1) {
		  ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		 }
		 return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
	
	/** 验证字符串是否有特殊字符**/
	public static boolean verifyString(String str){
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

		Pattern p = Pattern.compile(regEx);

		Matcher m = p.matcher(str);

		return m.find();
	}
	
	/**
	 * @Title: 手机号验证
	 * @Description:移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
					联通：130、131、132、152、155、156、185、186
					电信：133、153、180、189、（1349卫通）
	 * @param mobiles
	 * @return
	 * @author Horst
	 * @date 2016年11月17日 下午2:18:31
	 * @version V1.0
	 */
	public static boolean isMobileNO(String mobiles){  
		  
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
	  
		Matcher m = p.matcher(mobiles); 
	  
		return m.matches();
	  
	}
	
}
