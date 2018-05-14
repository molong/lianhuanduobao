package com.lhdb.service;

import javax.servlet.http.HttpServletRequest;

import com.lhdb.game.entity.User;
import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;
import com.lhdb.game.util.pojo.RecordOperation;

public interface UserSerice {
	
	/** 分页查询**/
	public Response search(User user, Page pager);
	
	/** 赠送金币**/
	public Response giveSocre(HttpServletRequest request, RecordOperation recordOperation, Long[] useridArray);
	
	/** 下金币**/
	public Response subSocre(HttpServletRequest request, RecordOperation recordOperation, Long[] useridArray);

	public Response online(User user, Page pager);
	
	public Response feng(Long[] useridArray);
	
	Response updatePwd(User user);
}
