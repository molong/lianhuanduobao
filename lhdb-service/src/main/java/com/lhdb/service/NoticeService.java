package com.lhdb.service;

import com.lhdb.game.mongo.entity.UserNotice;
import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;

public interface NoticeService {
	
	public Response search(Page pager);
	
	public Response save(UserNotice notice);
	
	public Response findByUserId(UserNotice notice);
	
	public Response update(UserNotice notice);
	
	public Response delete(UserNotice notice);

}
