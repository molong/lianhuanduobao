 package cn.lhdb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lhdb.game.dao.UsersMapper;
import com.lhdb.game.entity.Module;
import com.lhdb.game.entity.ModulePermission;
import com.lhdb.game.entity.Users;
import com.lhdb.game.util.GoogleAuthenticator;
import com.lhdb.game.util.MD5Util;
import com.lhdb.game.util.Response;
import com.lhdb.game.util.business.Code;

@Controller
@RequestMapping("/login")  
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	private GoogleAuthenticator ga = new GoogleAuthenticator();
	
	@Autowired
	private UsersMapper usersMapper;

	public void setUsersMapper(UsersMapper usersMapper) {
		this.usersMapper = usersMapper;
	}

	// 这个方法是获取游戏列表的方法;
	@RequestMapping("/userLogin")
	@ResponseBody
	public Response getLogList(String username, String password, Integer code, HttpServletRequest request, HttpSession session) {
		Response response = new Response();
		try {
			password = MD5Util.MD5(password);
			Users users = new Users();
			users.setUsername(username);
			users.setPassword(password);
			List<Users> userList = usersMapper.selectWithSpreader(users);
			logger.info("logger="+username+"/password="+password);
			if (userList.size() > 0) {
				
				Users user = userList.get(0);				
				if(user.getIsband() == 1) {
					if(!user.getBandip().equals(getIpAddr(request))) {
						response.setSuccess(false);
						response.setMessage("该用户已绑定IP！");
						return response;
					}
				}
				
				if(user.getNullity() == 1) {
					response.setSuccess(false);
					response.setMessage("该用户已被冻结！");
					return response;
				}
				response.setSuccess(true);
				response.setMessage("恭喜您登录成功！");
				
				user.setPreloginip(user.getLastloginip());
				user.setPrelogintime(user.getLastlogintime());
				user.setLastloginip(getIpAddr(request));
				user.setLastlogintime(new java.util.Date());
				user.setLogintimes(user.getLogintimes() == null ? 1 : user.getLogintimes() + 1);
				//保存当前登陆的用户对象
				session.setAttribute(Code.loginFlagName,user);
				
				//获得用户至少拥有读权限的模块，作为菜单显示
				List<Module> modules = usersMapper.getUserMenu(user.getUserid());
				session.setAttribute(Code.userMenu, modules);
				
				//获得用户每个模块拥有什么权限
				List<ModulePermission> permissions = usersMapper.getUserPermission(user.getUserid());
				Map<Integer,Integer> permissionMap = new HashMap<Integer,Integer>();
				for(ModulePermission up:permissions){
					permissionMap.put(up.getModuleid(), up.getPermissionvalue().intValue());
				}
				session.setAttribute(Code.userPermission, permissionMap);
				
			} else {
				response.setSuccess(false);
				response.setMessage("用户名或密码有误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	
	
	private boolean checkGoogleCode(String secret,Integer googleCode){
		
	     long t = System.currentTimeMillis();
	     boolean isok = false;
	     t = t - 60*1000;
	     ga.setWindowSize(5); //should give 5 * 30 seconds of grace...
	     
	     for(int i=0;i<3;i++){
	    	 
	    	 isok = ga.check_code(secret, googleCode, t);
	    	 
	    	 if(isok) break;
	    	 
	    	 t += 30*1000;
	     }
	     return isok;
	}
	
	
	@RequestMapping("/userExit")
	public ModelAndView userExit(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:../login.html");
	}

	// 这个方法是获取游戏列表的方法;
	@RequestMapping("/getUserPermission")
	@ResponseBody
	public Response getUserPermission(HttpSession session) {
		Response response = new Response();
		try {
			Users user = (Users)session.getAttribute(Code.loginFlagName);
			List<Module> modules = usersMapper.getUserMenu(user.getUserid());
			
			Map<Integer,String> modulesPermission = (HashMap<Integer, String>) session.getAttribute("permission");
			if(modulesPermission == null)
			{
				modulesPermission= new HashMap<Integer, String>();
				for(int i=0;i<modules.size();i++)
				{
					List<Module> children =modules.get(i).children;
					if(children!=null && children.size()>0)
					{
						for(int j=0;j<children.size();j++)
						{
							Module module = children.get(j);
							if(module!=null && module.getManagerpopedom()!=null)modulesPermission.put(module.getModuleid(),Integer.toBinaryString(module.getManagerpopedom()));
						}
					}
				}
				session.setAttribute("permission", modulesPermission);//权限写入session
			}
			
			response.setData(modules);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	private String getIpAddr(HttpServletRequest request) {
		 String ip = request.getHeader("x-forwarded-for");
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		  ip = request.getHeader("Proxy-Client-IP");
		 }
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		  ip = request.getHeader("WL-Proxy-Client-IP");
		 }
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		  ip = request.getRemoteAddr();
		 }
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		  ip = request.getHeader("http_client_ip");
		 }
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		  ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		 }
		 // 如果是多级代理，那么取第一个ip为客户ip
		 if (ip != null && ip.indexOf(",") != -1) {
		  ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		 }
		 return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
		}	
	
}