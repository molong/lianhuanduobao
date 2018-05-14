package com.lhdb.game.util.pojo;

public class RecordOperation {
	private long addRmbNum;//赠送RMB数目
	private long addNum;//赠送金币数目
	private String reason;//赠送原因
	public long getAddRmbNum() {
		return addRmbNum;
	}
	public void setAddRmbNum(long addRmbNum) {
		this.addRmbNum = addRmbNum;
	}
	public long getAddNum() {
		return addNum;
	}
	public void setAddNum(long addNum) {
		this.addNum = addNum;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
