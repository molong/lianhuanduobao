package com.lhdb.game.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.lhdb.game.util.CustomDateSerializer;
import com.lhdb.game.util.DateUtil;

public class SpreadRateInfo extends BasePojo{
	private Integer userId;
	private String accounts;
	private String nickName;
	private Date registerDate;
	private Integer accountsType;
	private Integer rewardRate;
	private Integer parentId;
	private int spreaderId;
	private Integer parentRate;
	private Integer userCount;
	private Integer ip;//去除重复Ip
	private Integer mac;//去除重复Mac
	private Integer payCount;//充值次数
	private Integer payAccountCount;//充值账户个数
	private float payMoney;//充值金额
	private Date insertDate;//插入时间
	private String accountsNickName;
	private Date startDate;
	private Date endDate;
	
	private boolean onOffical;
	
	public int getSpreaderId() {
		return spreaderId;
	}
	public void setSpreaderId(int spreaderId) {
		this.spreaderId = spreaderId;
	}
	public String getAccountsNickName() {
		return accountsNickName;
	}
	public void setAccountsNickName(String accountsNickName) {
		this.accountsNickName = accountsNickName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getParentRate() {
		return parentRate;
	}
	public void setParentRate(Integer parentRate) {
		this.parentRate = parentRate;
	}
	public Integer getUserCount() {
		return userCount;
	}
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	public Integer getIp() {
		return ip;
	}
	public void setIp(Integer ip) {
		this.ip = ip;
	}
	public Integer getMac() {
		return mac;
	}
	public void setMac(Integer mac) {
		this.mac = mac;
	}
	public Integer getPayCount() {
		return payCount;
	}
	public void setPayCount(Integer payCount) {
		this.payCount = payCount;
	}
	public Integer getPayAccountCount() {
		return payAccountCount;
	}
	public void setPayAccountCount(Integer payAccountCount) {
		this.payAccountCount = payAccountCount;
	}
	public float getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(float payMoney) {
		this.payMoney = payMoney;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Integer getAccountsType() {
		return accountsType;
	}
	public void setAccountsType(Integer accountsType) {
		this.accountsType = accountsType;
	}
	public Integer getRewardRate() {
		return rewardRate;
	}
	public void setRewardRate(Integer rewardRate) {
		this.rewardRate = rewardRate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		if(endDate!=null)
			this.endDate = DateUtil.addDay(1, endDate);
		else
			this.endDate = endDate;
	}
	public boolean isOnOffical() {
		return onOffical;
	}
	public void setOnOffical(boolean onOffical) {
		this.onOffical = onOffical;
	}
}
