package cn.lhdb.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhdb.game.entity.User;
import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;
import com.lhdb.game.util.pojo.RecordOperation;
import com.lhdb.service.UserSerice;

/**
 * title: 用户信息操作
 * des: 
 * @author Horst
 * @date 2016年12月2日 下午5:30:56
 * @version V1.0
 */
@Controller
@RequestMapping("/account")
public class UserController{
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserSerice userService;
	
	/**
	 * @Title: 分页查询
	 * @Description:
	 * @param user
	 * @param pager
	 * @return
	 * @author Horst
	 * @date 2016年12月2日 下午5:30:44
	 * @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/search")
	private Response search(User user, Page pager){
		Response response = new Response();
		try{
			response = userService.search(user, pager); 
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	//赠送金币
	@RequestMapping("/presentScoreForUserId")
	@ResponseBody
	public Response updateUserScoreForUserId(HttpServletRequest request, RecordOperation recordOperation,Long[] useridArray){
		Response response = new Response();
		try{
			response = userService.giveSocre(request, recordOperation, useridArray); 
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	//减金币
	@RequestMapping("/subScoreForUserId")
	@ResponseBody
	public Response subScoreForUserId(HttpServletRequest request, RecordOperation recordOperation,Long[] useridArray){
		Response response = new Response();
		try{
			response = userService.subSocre(request, recordOperation, useridArray); 
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/online")
	private Response online(User user, Page pager){
		Response response = new Response();
		try{
			response = userService.online(user, pager); 
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/feng")
	private Response feng(Long[] useridArray){
		Response response = new Response();
		try{
			response = userService.feng(useridArray); 
		}catch(Exception e){
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/uppwd")
	private Response updateUserPwd(User user){
		Response response = new Response();
		try{
			response = userService.updatePwd(user); 
		}catch(Exception e){
//			e.printStackTrace();
			logger.error(e);
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
}
