package com.lhdb.service;

import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;
import com.lhdb.game.util.pojo.ParamUtilVO;

public interface UserRebateLogService {
	
	Response search(ParamUtilVO paramUtilVO, Page pager);

}
