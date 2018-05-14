package cn.lhdb.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhdb.game.entity.MGiveScoreLog;
import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;
import com.lhdb.service.TakeScoreLogService;

@Controller
@RequestMapping("/takeScoreLog")
public class TakeScoreLogController {
	
	private static Logger logger = Logger.getLogger(TakeScoreLogController.class);
	
	@Autowired
	private TakeScoreLogService takeScoreLogService;
	
	/**
	 * @Title: 转账/充值记录
	 * @Description:
	 * @param log
	 * @param pager
	 * @return
	 * @author Horst
	 * @date 2017年1月11日 下午2:58:18
	 * @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/search")
	private Response search(MGiveScoreLog log, Page pager){
		Response response = new Response();
		try{
			response = takeScoreLogService.search(log, pager); 
		}catch(Exception e){
			logger.error("error", e);
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}

}
