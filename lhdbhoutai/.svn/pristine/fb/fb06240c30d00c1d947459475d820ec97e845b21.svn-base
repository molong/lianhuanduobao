package cn.lhdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;
import com.lhdb.game.util.pojo.ParamUtilVO;
import com.lhdb.service.UserRebateLogService;

@Controller
@RequestMapping("/rebateLog")
public class UserRebateLogController {
	
	@Autowired
	private UserRebateLogService userRebateLogService;
	
	/**
	 * @Title: 查询反水日志
	 * @Description:
	 * @param paramUtilVO
	 * @param pager
	 * @return
	 * @author Horst
	 * @date 2017年12月27日 下午9:07:17
	 * @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/search")
	private Response search(ParamUtilVO paramUtilVO, Page pager) {
		Response response = new Response();
		try{
			response = userRebateLogService.search(paramUtilVO, pager); 
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}

}
