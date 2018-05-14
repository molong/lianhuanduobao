package com.lhdb.game.mongo.entity;

import java.util.Date;

public class UserWinLoseLog {
	
	private String id;
	private long logid;
	private int type;//0普通掉落珠子1龙珠夺宝
	private int status;//0未广播1已广播
	private Long userId;
	private int multiple;//押注线数
	private int stakeScore;//押注分数
	private int stakeSumScore;//押注总分数
	private long score;//身上分数
	private int winScore;
	private Date time;
	public UserWinLoseLog(String id, long logid, int type, int status,
			Long userId, int multiple, int stakeScore, int stakeSumScore,
			long score, int winScore, Date time) {
		super();
		this.id = id;
		this.logid = logid;
		this.type = type;
		this.status = status;
		this.userId = userId;
		this.multiple = multiple;
		this.stakeScore = stakeScore;
		this.stakeSumScore = stakeSumScore;
		this.score = score;
		this.winScore = winScore;
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getLogid() {
		return logid;
	}
	public void setLogid(long logid) {
		this.logid = logid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public int getMultiple() {
		return multiple;
	}
	public void setMultiple(int multiple) {
		this.multiple = multiple;
	}
	public int getStakeScore() {
		return stakeScore;
	}
	public void setStakeScore(int stakeScore) {
		this.stakeScore = stakeScore;
	}
	public int getStakeSumScore() {
		return stakeSumScore;
	}
	public void setStakeSumScore(int stakeSumScore) {
		this.stakeSumScore = stakeSumScore;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public int getWinScore() {
		return winScore;
	}
	public void setWinScore(int winScore) {
		this.winScore = winScore;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
}
