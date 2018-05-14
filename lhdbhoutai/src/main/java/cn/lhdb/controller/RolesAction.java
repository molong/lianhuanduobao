package cn.lhdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhdb.game.dao.RolesMapper;
import com.lhdb.game.dao.UsersMapper;
import com.lhdb.game.entity.Roles;
import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RolesAction extends BaseAction {
	@Autowired
	private RolesMapper rolesMapper;
	
	@Autowired
	private UsersMapper usersMapper;
	
	public void setOnLineUserMapper(RolesMapper rolesMapper) {
		this.rolesMapper = rolesMapper;
	}
	
	//查询角色
	@RequestMapping("/search")
	@ResponseBody
	public Response search(Roles role,Page pager) {
		Response response = new Response();
		
		try {
			List<Roles> list = rolesMapper.selectByExample(null);
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
	
	//添加角色
	@RequestMapping("/addrole")
	@ResponseBody
	public Response add(Roles role) {
		Response response = new Response();
		try {
			role.setRoleid(null);
			int result = rolesMapper.insertSelective(role);
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
	
	//修改角色
	@RequestMapping("/modifyrole")
	@ResponseBody
	public Response modify(Roles role) {
		Response response = new Response();
		try {
			int result = rolesMapper.updateByPrimaryKey(role);
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
	
	//删除角色
	@RequestMapping("/delrole")
	@ResponseBody
	public Response delete(Roles role) {
		Response response = new Response();
		try {
//			if( rolesMapper.countByRoleID(role.getRoleid()) > 0 )
//			{
//				response.setSuccess(false);
//				// 错误消息描述
//				response.setMessage("此角色正在使用，禁止删除!");
//			}
			if(response.isSuccess())
			{
				int result = rolesMapper.deleteByPrimaryKey(role.getRoleid());
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
