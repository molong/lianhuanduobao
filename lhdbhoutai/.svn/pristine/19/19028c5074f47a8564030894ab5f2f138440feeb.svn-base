package cn.lhdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhdb.game.entity.OrderInfo;
import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;
import com.lhdb.service.OrderInfoService;

@Controller
@RequestMapping("/order")
public class OrderInfoController {
	
	@Autowired
	private OrderInfoService orderInfoService;
	
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
	private Response search(OrderInfo orderInfo, Page pager) {
		Response response = new Response();
		try{
			response = orderInfoService.search(orderInfo, pager); 
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	/**
	 * @Title: 代付
	 * @Description:
	 * @param orderInfo
	 * @param pager
	 * @return
	 * @author Horst
	 * @date 2017年7月11日 下午8:24:47
	 * @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/searchDf")
	private Response searchDf(OrderInfo orderInfo, Page pager) {
		Response response = new Response();
		try{
			response = orderInfoService.searchDf(orderInfo, pager); 
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}

}
