package com.lhdb.game.mongo.entity;

import java.util.Date;

public class OnlineUser {
	
	private String _id;
	
	private Date time;	

	public OnlineUser(String _id, Date time) {
		super();
		this._id = _id;
		this.time = time;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
