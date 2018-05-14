package com.lhdb.service;

import com.lhdb.game.entity.MGiveScoreLog;
import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;

public interface TakeScoreLogService {
	
	public Response search(MGiveScoreLog log, Page pager);

}
