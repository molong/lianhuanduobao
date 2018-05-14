package com.lhdb.game.util;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.lhdb.game.entity.User;

public class Response {
	
	
	//操作成功还是失败
	private boolean success = true;
	
	//错误信息
	private String message = "";
	
	//返回的业务数据
	private Object data;
	
	//查询条件
	private Object params;
	
	
	public Response(){
		super();
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

	public static void main(String[] args) {
		Response resp = new Response();
		User user = new User();
		user.setUpdateDate(new Date());
		resp.setData(user);
		resp.setSuccess(true);
		resp.setMessage("");
		ObjectMapper  objectMapper= new ObjectMapper();
		try {
			StringBuilder sb = new StringBuilder("callback(");
			String respStr = objectMapper.writeValueAsString(resp);  
			sb.append(respStr);
			sb.append(")");
			System.out.println(sb.toString());
		
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
