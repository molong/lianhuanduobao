package cn.lhdb.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class BaseAction {

	public String getBaseUrl(HttpServletRequest request){
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		basePath = basePath.replaceAll("localhost",getHostIp());
		return basePath;
	}

	public String getProjectUrl(HttpServletRequest request){
		String path = request.getServletContext().getRealPath("/")+ "page";
		return path;
	}

	public static String getHostIp(){
		InetAddress ia= null;
		try {
			ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String localip=ia.getHostAddress();
		return localip;
	}

	/**
	 * js回调parent的方法
	 * @param content 回调信息
	 * @param funcName function名称
     * @return
     */
	public String getParentCallBack(String content,String funcName){
		StringBuilder builder = new StringBuilder();
		builder.append("<script>");
		builder.append("parent.");
		builder.append(funcName);
		builder.append("(");
		builder.append(content);
		builder.append(")");
		builder.append("</script>");
		return builder.toString();
	}

	/**
	 * 用uuid的方式重新生成文件名
	 * @param oldName
	 * @return
     */
	public String fileReName(String oldName){
		String format = oldName.substring(oldName.lastIndexOf("."));
		return UUID.randomUUID().toString().replaceAll("-","")+format;
	}




}
