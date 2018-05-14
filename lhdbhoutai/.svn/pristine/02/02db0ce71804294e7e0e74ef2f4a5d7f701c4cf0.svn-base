package cn.lhdb.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhdb.game.util.Response;
import com.lhdb.game.util.pojo.StockModel;
import com.lhdb.service.StockService;

@Controller
@RequestMapping("/stock")
public class StockController {
	
	private static Logger logger = Logger.getLogger(StockController.class);
	
	@Autowired
	private StockService stockService;
	
	@ResponseBody
	@RequestMapping("/search")
	private Response search(){
		Response response = new Response();
		try{
			response = stockService.search(); 
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	private Response update(StockModel model){
		Response response = new Response();
		try{
			response = stockService.update(model); 
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}

}
