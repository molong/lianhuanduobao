package cn.lhdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;
import com.lhdb.game.util.pojo.ParamUtilVO;
import com.lhdb.service.JewelDownLogService;

@Controller
@RequestMapping("/jewel")
public class JewelDownLogController {
	
	@Autowired
	private JewelDownLogService jewelDownLogService;
	
	/**
	 * @Title: 查询订单详情
	 * @Description:
	 * @param orderInfo
	 * @param pager
	 * @return
	 * @author Horst
	 * @date 2017年7月11日 上午12:20:46
	 * @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/search")
	private Response search(ParamUtilVO paramUtilVO, Page pager) {
		Response response = new Response();
		try{
			response = jewelDownLogService.search(paramUtilVO, pager); 
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}

}
