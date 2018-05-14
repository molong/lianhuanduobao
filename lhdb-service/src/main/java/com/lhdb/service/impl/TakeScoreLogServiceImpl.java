package com.lhdb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhdb.game.dao.MGiveScoreLogMapper;
import com.lhdb.game.dao.UserMapper;
import com.lhdb.game.dao.UsersMapper;
import com.lhdb.game.entity.MGiveScoreLog;
import com.lhdb.game.entity.User;
import com.lhdb.game.entity.Users;
import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;
import com.lhdb.service.TakeScoreLogService;


@Service
public class TakeScoreLogServiceImpl implements TakeScoreLogService{
	
	@Autowired
	private MGiveScoreLogMapper mGiveScoreLogMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UsersMapper usersMapper;
	
	/**
	 * @Title: 转账/充值记录
	 * @Description: 
	 * @param log
	 * @param pager
	 * @return
	 * @author Horst
	 * @date 2017年1月11日 下午2:54:26
	 * @version V1.0
	 */
	public Response search(MGiveScoreLog log, Page pager){
		Response response = new Response();
		log.setPager(pager);
		List<MGiveScoreLog> list = new ArrayList<>();
		MGiveScoreLog countRecord = mGiveScoreLogMapper.count(log);
		int count = 0;
		long sumScore = 0;
		if(countRecord != null){
			count = countRecord.getId();
			if(count != 0){
				sumScore = countRecord.getScore();
			}
		}
		if(count > 0){
			list = mGiveScoreLogMapper.searchOnPage(log);
			for(MGiveScoreLog l:list){
				int type = l.getType();
				int masterId = l.getMasterId();
				User myUser = userMapper.selectByPrimaryKey(l.getUserId());
				if(myUser != null){
					l.setName(myUser.getUserName());
				}
				String transferName = "";
				if(type == 0){
					Users user = usersMapper.selectByPrimaryKey(masterId);
					if(user != null){
						transferName = user.getUsername();
					}
				}else{
					User user = userMapper.selectByPrimaryKey((long)masterId);
					if(user != null){
						transferName = user.getUserName();
					}
				}
				l.setTransferName(transferName);
			}
		}
		pager.setTotalCount(count);
		response.setData(list);
		log.setScore(sumScore);
		response.setParams(log);
		return response;
	}
}
