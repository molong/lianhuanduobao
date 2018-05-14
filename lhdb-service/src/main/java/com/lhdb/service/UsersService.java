package com.lhdb.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lhdb.game.dao.UsersMapper;
import com.lhdb.game.entity.Users;

@Service
@Transactional
public class UsersService {
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insert(){
		Users user = new Users();
    	user.setBandip("");
    	user.setIsband(0);
    	user.setLastloginip("");
    	user.setLastlogintime(new Date());
    	user.setLogintimes(0);
    	user.setNullity((byte)0);
    	user.setPassword("1");
    	user.setPreloginip("0");
    	user.setUsername("user");
    	user.setPrelogintime(new Date());
    	user.setRoleid(1);
    	usersMapper.insert(user);
    	Users record1 = new Users();
    	record1.setBandip("");
    	record1.setIsband(0);
    	record1.setLastloginip("");
    	record1.setLastlogintime(new Date());
    	record1.setLogintimes(0);
    	record1.setNullity((byte)0);
    	record1.setPassword("1");
    	record1.setPreloginip("0");
    	record1.setUsername("record1");
		usersMapper.insert(record1);
	}
}
