package com.lhdb.game.mongo.entity;

import java.util.Date;

/**
 * title: 用户反水日志
 * des: 
 * @author Horst
 * @date 2017年6月21日 下午7:50:53
 * @version V1.0
 */
public class UserRebateLog {
	
	private String id;
	private Long userId;
	private int addScore;//押注总分数
	private long score;//身上分数
	private Date time;
	private int state;//0未通知1已通知
	public UserRebateLog() {}
	public UserRebateLog(Long userId, int addScore, long score, Date time, int state) {
		super();
		this.userId = userId;
		this.addScore = addScore;
		this.score = score;
		this.time = time;
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public int getAddScore() {
		return addScore;
	}
	public void setAddScore(int addScore) {
		this.addScore = addScore;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
