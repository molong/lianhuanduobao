package com.lhdb.game.util;

import com.lhdb.game.entity.Users;

public class LoginUserUtil {
	
	private static final ThreadLocal threadLocal = new ThreadLocal();
	
	public static void put(Users value){
		threadLocal.set(value);
	}
	
	public static Users get( ){
		return (Users)threadLocal.get();
	}

}
