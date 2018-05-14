package com.lhdb.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhdb.game.mongo.dao.UserNoticeOperation;
import com.lhdb.game.mongo.entity.UserNotice;
import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;
import com.lhdb.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private UserNoticeOperation userNoticeOperation;
	
	public Response search(Page pager){
		Response response = new Response();
		org.springframework.data.domain.Page<UserNotice> list = userNoticeOperation.find(pager.getCurrentPage());
		pager.setTotalCount(list.getNumber());
		response.setData(list.getContent());
		UserNotice notice = new UserNotice();
		notice.setPager(pager);
		response.setParams(notice);
		return response;
	}
	
	public Response save(UserNotice notice){
		Response response = new Response();
		String title = notice.getTitle()!=null?notice.getTitle():"";
//		Date start = notice.getStart();
//		Date end = notice.getEnd();
		if(title.equals("")){
			response.setSuccess(false);
			return response;
		}
		notice.setId(new ObjectId().toString());
		userNoticeOperation.insert(notice);
		return response;
	}
	
	public Response findByUserId(UserNotice notice){
		Response response = new Response();
		UserNotice record = userNoticeOperation.findByUserId(notice.getId());
		response.setData(record);
		return response;
	}
	
	public Response update(UserNotice notice){
		Response response = new Response();
		String title = notice.getTitle()!=null?notice.getTitle():"";
//		Date start = notice.getStart();
//		Date end = notice.getEnd();
		if(title.equals("")){
			response.setSuccess(false);
			return response;
		}
		userNoticeOperation.update(notice);
		return response;
	}
	
	public Response delete(UserNotice notice){
		Response response = new Response();
		userNoticeOperation.delete(notice.getId());
		return response;
	}

}
