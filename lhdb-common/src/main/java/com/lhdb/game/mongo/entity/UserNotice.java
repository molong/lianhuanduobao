package com.lhdb.game.mongo.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhdb.game.entity.BasePojo;

public class UserNotice extends BasePojo{
	
	private String id;
	private String title;
	private Integer status;//0正常1暂停
//	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
//	private Date start;
//	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
//	private Date end;
//	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date time;
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date updateTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
//	@JsonSerialize(using = CustomDateSerializeryyyyMMddHHmmss.class)
//	public Date getStart() {
//		return start;
//	}
//	public void setStart(Date start) {
//		this.start = start;
//	}
//	@JsonSerialize(using = CustomDateSerializeryyyyMMddHHmmss.class)
//	public Date getEnd() {
//		return end;
//	}
//	public void setEnd(Date end) {
//		this.end = end;
//	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}	
}
