package cn.lhdb.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhdb.game.dao.UsersMapper;
import com.lhdb.game.entity.Users;
import com.lhdb.game.util.GoogleAuthenticator;
import com.lhdb.game.util.MD5Util;
import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;

@Controller
@RequestMapping("/adminUser")
public class AdminUserAction extends BaseAction{
	
	@Autowired
	private UsersMapper usersMapper;
	
	@RequestMapping("/search")
	@ResponseBody
	public Response search(Users user,Page pager) {
		Response response = new Response();
		
		try {
			List<Users> list = usersMapper.selectAll();
			response.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
			// 设置响应状态为500，应用错误
			response.setSuccess(false);
			// 错误消息描述
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	//联合角色表查询
	@RequestMapping("/searchs")
	@ResponseBody
	public Response searchs(Users user,Page pager) {
		Response response = new Response();
		
		try {
			List<Users> list = usersMapper.selectByExample(null);
			response.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
			// 设置响应状态为500，应用错误
			response.setSuccess(false);
			// 错误消息描述
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Response add(Users user, HttpServletRequest request) {
		Response response = new Response();
		try {
			if(user.getRoleid() == null || user.getRoleid() == 0 )
			{
				response.setSuccess(false);
				response.setMessage("角色ID不能为空!");
			}
			
			if(user.getUsername() == null || user.getUsername().isEmpty() )
			{
				response.setSuccess(false);
				// 错误消息描述
				response.setMessage("管理员账号不能为空!");
			}
			
			String password = user.getPassword();
			if(password == null || password.isEmpty() )
			{
				response.setSuccess(false);
				// 错误消息描述
				response.setMessage("管理员密码不能为空!");
			}
			
			if(response.isSuccess())
			{
				
				
				user.setPassword( MD5Util.MD5(password));
				user.setUserid(null);
				if(user.getIsband() == null)
					user.setIsband(0);
				if(user.getNullity() == null)
					user.setNullity((byte)0);
				
				user.setPrelogintime(new Date(0));
				user.setPreloginip("000.000.000.000");
				user.setLastlogintime(new Date(0));
				user.setLastloginip("000.000.000.000");
				user.setLogintimes(0);
				user.setBandip("000.000.000.000");//?
				
				
				String secret = GoogleAuthenticator.generateSecretKey();
				user.setSecret(secret);
				int result = usersMapper.insertSelective(user);
				usersMapper.insertUserRole(user);
				response.setData(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 设置响应状态为500，应用错误
			response.setSuccess(false);
			// 错误消息描述
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	//删除管理员
	@RequestMapping("/del")
	@ResponseBody
	public Response del(int[] idArray){
		Response response = new Response();
		try {
			int result = usersMapper.deleteByIDList(idArray);
			response.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			// 设置响应状态为500，应用错误
			response.setSuccess(false);
			// 错误消息描述
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	//修改管理员
	@RequestMapping("/modify")
	@ResponseBody
	public Response modify(Users record, @RequestParam(value="disabled", required = false)String disabled){
		if(disabled != null && !"".equals(disabled))
			record.setNullity(Byte.valueOf(disabled));
		Response response = new Response();
		try {
			int result = usersMapper.updateUserInfo(record);
			if(record.getRoleid()!=null)
			{
				usersMapper.updateUserRole(record);
			} 
			response.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			// 设置响应状态为500，应用错误
			response.setSuccess(false);
			// 错误消息描述
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	//修改管理员
	@RequestMapping("/modifypass")
	@ResponseBody
	public Response modifyPass(Users record){
		Response response = new Response();
		try {
			/*if(record.getRoleid() == null || record.getRoleid() == 0 )
			{
				response.setSuccess(false);
				response.setMessage("角色ID不能为空!");
			}
			*/
			String password = record.getPassword();
			if(password == null || password.isEmpty() )
			{
				response.setSuccess(false);
				// 错误消息描述
				response.setMessage("管理员密码不能为空!");
			}
			
			if(response.isSuccess())
			{
				record.setPassword( MD5Util.MD5(password));
				int result = usersMapper.updateByPrimaryKeySelective(record);
				response.setData(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 设置响应状态为500，应用错误
			response.setSuccess(false);
			// 错误消息描述
			response.setMessage(e.getMessage());
		}
		return response;
	}

}
