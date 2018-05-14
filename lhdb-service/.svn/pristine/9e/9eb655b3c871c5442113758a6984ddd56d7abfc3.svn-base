package com.lhdb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhdb.game.config.PropertyValue;
import com.lhdb.game.dao.MGiveScoreLogMapper;
import com.lhdb.game.dao.UserMapper;
import com.lhdb.game.entity.MGiveScoreLog;
import com.lhdb.game.entity.User;
import com.lhdb.game.entity.Users;
import com.lhdb.game.mongo.dao.OnlineUserOperation;
import com.lhdb.game.mongo.dao.UserBankOperation;
import com.lhdb.game.mongo.entity.OnlineUser;
import com.lhdb.game.mongo.entity.UserBank;
import com.lhdb.game.redis.RedisInitDao;
import com.lhdb.game.redis.RedisKey;
import com.lhdb.game.util.HttpRequest;
import com.lhdb.game.util.MD5Util;
import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;
import com.lhdb.game.util.ToolUtil;
import com.lhdb.game.util.business.Code;
import com.lhdb.game.util.business.MsgCode;
import com.lhdb.game.util.pojo.RecordOperation;
import com.lhdb.service.UserSerice;

@Service
public class UserServiceImpl implements UserSerice{
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserBankOperation userBankOperation;
	@Autowired
	private MGiveScoreLogMapper	mGiveScoreLogMapper;
	@Autowired
	private PropertyValue propertyValue;
	@Autowired
	private OnlineUserOperation onlineUserOperation;
	@Autowired
	private RedisInitDao redisInitDao;
	
	/**
	 * @Title: 分页查询
	 * @Description:
	 * @param user
	 * @param pager
	 * @return
	 * @author Horst
	 * @date 2016年12月2日 下午5:26:36
	 * @version V1.0
	 */
	@Override
	public Response search(User user, Page pager){
		Response response = new Response();
		user.setPager(pager);
		List<User> list = new ArrayList<User>();
		int count = userMapper.count(user);
		if(count > 0){
			list = userMapper.searchOnPage(user);
			for(User u:list){
				UserBank userBank = userBankOperation.findByUserId(u.getUserId());
				boolean isblack = redisInitDao.getSetIsMember(RedisKey.BLACKLIST_USER, u.getUserId());
				if(isblack){
					u.setState(1);
				}
				u.setUserBank(userBank);
			}
		}
		pager.setTotalCount(count);
		response.setData(list);
		response.setParams(user);
		return response;
	}
	
	/** 赠送金币**/
	@Override
	public Response giveSocre(HttpServletRequest request, RecordOperation recordOperation, Long[] useridArray){
		Response response = new Response();
		if(useridArray==null || useridArray.length == 0){
			response.setSuccess(false);
			response.setMessage(MsgCode.GIVE_SCORE_FAIL);
			return response;
		}
		//批量添加
		String data = "";
		for(Long uid:useridArray){
			UserBank userBank = userBankOperation.findByUserId(uid);
			long score = userBank.getScore();
			userBank.setScore(score+recordOperation.getAddNum());
			userBank.setPaySum(userBank.getPaySum()+recordOperation.getAddRmbNum());
			int num = userBankOperation.updateByUserId(userBank);
			if(num == 0){
				data += uid+",";
			}else{
				//日志添加
				MGiveScoreLog log = new MGiveScoreLog();
				String ip = ToolUtil.getIpAddr(request);
				log.setType(0);
				log.setClientIp(ip);
				log.setCreateTime(new Date());
				HttpSession session = request.getSession();
	  			Users userSession = (Users)session.getAttribute(Code.loginFlagName);
				log.setMasterId(userSession.getUserid());
				log.setReason(recordOperation.getReason());
				log.setScore(recordOperation.getAddNum());
				log.setUserId(uid);
				mGiveScoreLogMapper.insert(log);
				//通知前端
				StringBuilder sb = new StringBuilder("rechargeScore="+recordOperation.getAddNum());
				sb.append("&userId=").append(uid);
				HttpRequest.sendGet(propertyValue.getWeburl()+"take/giveScore", sb.toString());
			}
		}
		response.setData(data);
		return response;
	}
	
	/** 下金币**/
	public Response subSocre(HttpServletRequest request, RecordOperation recordOperation, Long[] useridArray){
		Response response = new Response();
		if(useridArray==null || useridArray.length == 0){
			response.setSuccess(false);
			response.setMessage(MsgCode.GIVE_SCORE_FAIL);
			return response;
		}
		//批量添加
		String data = "";
		for(Long uid:useridArray){
			UserBank userBank = userBankOperation.findByUserId(uid);
			long score = userBank.getScore();
			if(score < recordOperation.getAddNum()){
				score = 0;
			}else{
				score = score-recordOperation.getAddNum();
			}
			userBank.setScore(score);
			userBank.setPaySum(userBank.getPaySum()+recordOperation.getAddRmbNum());
			int num = userBankOperation.updateByUserId(userBank);
			if(num == 0){
				data += uid+",";
			}else{
				//日志添加
				MGiveScoreLog log = new MGiveScoreLog();
				String ip = ToolUtil.getIpAddr(request);
				log.setType(2);//0加金币1转账金币2减金币
				log.setClientIp(ip);
				log.setCreateTime(new Date());
				HttpSession session = request.getSession();
	  			Users userSession = (Users)session.getAttribute(Code.loginFlagName);
				log.setMasterId(userSession.getUserid());
				log.setReason(recordOperation.getReason());
				log.setScore(recordOperation.getAddNum());
				log.setUserId(uid);
				mGiveScoreLogMapper.insert(log);
				//通知前端
				StringBuilder sb = new StringBuilder("rechargeScore="+recordOperation.getAddNum());
				sb.append("&userId=").append(uid);
				HttpRequest.sendGet(propertyValue.getWeburl()+"take/subScore", sb.toString());
			}
		}
		response.setData(data);
		return response;
	}
	
	public Response online(User user, Page pager){
		Response response = new Response();
		user.setPager(pager);
		List<OnlineUser> list = onlineUserOperation.findAll();
		List<Long> ids = new ArrayList<Long>();
		for(OnlineUser ol:list){
			ids.add(Long.parseLong(ol.get_id()));
		}
		user.setIds(ids);
		int count = 0;
		List<User> userList = new ArrayList<User>();
		if(ids.size()>0){
			count = userMapper.onlineCount(user);
			if(count > 0){
				userList = userMapper.searchOnline(user);
				for(User u:userList){
					UserBank userBank = userBankOperation.findByUserId(u.getUserId());
					u.setUserBank(userBank);
				}
			}
		}
		pager.setTotalCount(count);
		response.setData(userList);
		response.setParams(user);
		return response;
	}
	
	public Response feng(Long[] useridArray){
		Response response = new Response();
		if(useridArray==null || useridArray.length == 0){
			response.setSuccess(false);
			response.setMessage(MsgCode.CATCH_ERROR);
			return response;
		}
		for(Long userId:useridArray){
			boolean isMember = redisInitDao.getSetIsMember(RedisKey.BLACKLIST_USER, userId);
			if(isMember){
				redisInitDao.delSetKey(RedisKey.BLACKLIST_USER, userId);
			}else{
				redisInitDao.addSetKey(RedisKey.BLACKLIST_USER, userId);
			}
		}
		return response;
	}
	
	/**
	 * @Title: 更新玩家密码
	 * @Description: 
	 * @param user
	 * @return
	 * @author Horst
	 * @date 2017年12月13日 下午9:49:31
	 * @version V1.0
	 */
	public Response updatePwd(User user){
		Response response = new Response();
		User record = userMapper.selectByPrimaryKey(user.getUserId());
		if(record == null) {
			response.setSuccess(false);
			return response;
		}
		record.setPassword(MD5Util.MD5(record.getPassword()));
		int state = userMapper.updateByPrimaryKeySelective(record);
		if(state != 1) {
			response.setSuccess(false);
			return response;
		}
		return response;
	}
	
}
