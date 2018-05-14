package cn.lhdb.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhdb.game.dao.ModuleMapper;
import com.lhdb.game.dao.ModulePermissionMapper;
import com.lhdb.game.dao.RolePermissionMapper;
import com.lhdb.game.entity.Module;
import com.lhdb.game.entity.ModulePermission;
import com.lhdb.game.entity.ModulePermissionExample;
import com.lhdb.game.entity.RolePermission;
import com.lhdb.game.entity.RolePermissionExample;
import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;

import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
@RequestMapping("/Modules")
public class ModulesAction extends BaseAction {
	
	private static Logger logger = Logger.getLogger(ModulesAction.class);
	
	@Autowired
	private ModuleMapper moduleMapper;
	@Autowired
	private ModulePermissionMapper modulePermissionMapper;
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	public void setOnLineUserMapper(ModuleMapper moduleMapper) {
		this.moduleMapper = moduleMapper;
	}
	
	@RequestMapping("/getmodulebyrole")
	@ResponseBody
	public Response getModulePermission(Integer roleid) {
		
		Response response = new Response();
		try {
			List<Module> list = moduleMapper.getModulePermission(roleid);
			response.setData(list);
		} catch (Exception e) {
			logger.error(e);
			// 设置响应状态为500，应用错误
			response.setSuccess(false);
			// 错误消息描述
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	/**
	 * 设置角色操作权限
	 * @param moduleid  模块id
	 * @param roleid    角色id
	 * @param permissioncode  设置权限值
	 * @return
	 */
	@RequestMapping("/setRolePermission")
	@ResponseBody
	public Response setRolePermission(HttpSession session, Integer moduleid,Integer roleid,Integer permissioncode)
	{
		Response response = new Response();
		try {
			Map<String, Integer> map =new HashMap<String, Integer>();
			map.put("moduleid", moduleid) ;
			map.put("roleid", roleid) ;
			map.put("permissioncode", permissioncode) ;
			RolePermissionExample example=new RolePermissionExample();
			example.createCriteria().andModuleidEqualTo(moduleid).andRoleidEqualTo(roleid);
			int count=rolePermissionMapper.countByExample(example);
			int result=-1;
			if(count==0){
				RolePermission rolePermission=new RolePermission();
				rolePermission.setRoleid(roleid);
				rolePermission.setModuleid(moduleid);
				rolePermission.setOperationpermission(permissioncode.longValue());
				rolePermission.setManagerpermission(0L);
				rolePermission.setStateflag(0);
				result=rolePermissionMapper.insert(rolePermission);
			}
			else
			{
				result = moduleMapper.setRolePermission(map);
			}
			
			
			Map<Integer,String> modulesPermission = (Map<Integer, String>) session.getAttribute("permission");
			if(modulesPermission != null)
			{
				modulesPermission.put(moduleid, Integer.toBinaryString(permissioncode));
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
	
	/**
	 * 保存/更新权限
	 * @param request
	 * @return
	 */
	@RequestMapping("/savePermission")
	@ResponseBody
	public Response saveOrUpdate(HttpServletRequest request){
		Response response = new Response();
		String p[] = request.getParameterValues("permission");
		int roleid = Integer.parseInt(request.getParameter("roleid"));
		int moduleid = 0;
		Long permission = 0l;
		int tempModuleid = 0;
		Long tempPermission = 0l;
		RolePermissionExample example = new RolePermissionExample();
		Map<Integer,Long> mp = new HashMap<Integer,Long> ();
		for (int i = 0; i < p.length; i++) {
			String[] info = p[i].split("_");
			moduleid = Integer.parseInt(info[0]);
			permission = Long.parseLong(info[1]);
			if(moduleid == tempModuleid){
				tempModuleid = moduleid;
				tempPermission += permission;
				mp.remove(moduleid);
				mp.put(moduleid, tempPermission);
			}else{
				tempModuleid = moduleid;
				tempPermission = permission;
				mp.put(moduleid, permission);
			}
		}
		Iterator<Integer> iter2 = (Iterator<Integer>)mp.keySet().iterator();
		while(iter2.hasNext()){
			moduleid = (Integer) iter2.next();
			permission = mp.get(moduleid);
			RolePermission record = new RolePermission();
			try{
				example.createCriteria().andRoleidEqualTo(roleid).andModuleidEqualTo(moduleid);
				record.setOperationpermission(permission);
				record.setManagerpermission(0L);
				record.setStateflag(0);
				int rs = rolePermissionMapper.updateByExampleSelective(record, example);
				if(rs == 0){
					record.setRoleid(roleid);
					record.setModuleid(moduleid);
					rolePermissionMapper.insertSelective(record);
				}
				example.clear();
				example.createCriteria().andOperationpermissionEqualTo(0l);
				rolePermissionMapper.deleteByExample(example);
				example.clear();
				response.setSuccess(true);
				response.setData("权限保存成功！");
			}catch(Exception e){
				e.printStackTrace();
				response.setSuccess(false);
				response.setData("权限保存失败！");
				response.setMessage(e.getMessage());
			}
		}
		return response;
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Response search(Module module,Page pager) {
		Response response = new Response();
		try {
			List<Module> list = moduleMapper.selectByExample(null);
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
	public Response add(Module module) {
		Response response = new Response();
		if(module.getLink() == null) module.setLink("");
		try {
			int result = moduleMapper.insertSelective(module);
			ModulePermission permission = new ModulePermission();
			permission.setModuleid(module.getModuleid());
			permission.setNullity((byte)0);
			permission.setParentid(module.getParentid() == null ? 0 : module.getParentid());
			permission.setPermissiontitle("查看");
			permission.setPermissionvalue(1L);
			permission.setStateflag(0);
			permission.setUri(module.getHref());
			Response res = this.addModulePersmission(permission);
			if(res.isSuccess()) 
				response.setData(result);
			else {
				response.setSuccess(false);
				response.setMessage(res.getMessage());
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
	@RequestMapping("/addModulePersmission")
	@ResponseBody
	public Response addModulePersmission(ModulePermission permission) {
		Response response = new Response();
		try {
			if(permission.getParentid() == null)
				permission.setParentid(moduleMapper.selectByPrimaryKey(permission.getModuleid()).getParentid());
			int result = modulePermissionMapper.insertSelective(permission);
			response.setData(result);
			response.setSuccess(true);
		} catch(Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping("/delModule")
	@ResponseBody
	public Response delModule(Module module) {
		Response response = new Response();
		try {
			int result = moduleMapper.deleteByPrimaryKey(module.getModuleid());
			ModulePermissionExample example = new ModulePermissionExample();
			example.createCriteria().andModuleidEqualTo(module.getModuleid());
			modulePermissionMapper.deleteByExample(example);
			response.setData(result);
			response.setSuccess(true);
		} catch(Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping("/updateModule")
	@ResponseBody
	public Response updateModule(Module module) {
		Response response = new Response();
		try {
			int result = moduleMapper.updateByPrimaryKeySelective(module);
			response.setData(result);
			response.setSuccess(true);
		} catch(Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping("/getModulePermissions")
	@ResponseBody
	public Response getModulePermission(int moduleid) {
		Response response = new Response();
		try {
			ModulePermissionExample example = new ModulePermissionExample();
			example.createCriteria().andModuleidEqualTo(moduleid);
			 List<ModulePermission> permissions = modulePermissionMapper.selectByExample(example);
			response.setData(permissions);
			response.setParams(moduleid);
			response.setSuccess(true);
		} catch(Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping("/updateModulePermission")
	@ResponseBody
	public Response updateModulePermission(ModulePermission permission) {
		Response response = new Response();
		try {
			
			int result = modulePermissionMapper.updateByPrimaryKeySelective(permission);
			response.setData(result);
			response.setParams(permission);
			response.setSuccess(true);
		} catch(Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping("/delModulePermission")
	@ResponseBody
	public Response delModulePermission(ModulePermission permission) {
		Response response = new Response();
		try {
			int result = modulePermissionMapper.deleteByPrimaryKey(permission);
			response.setData(result);
			response.setParams(permission);
			response.setSuccess(true);
		} catch(Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	
}
