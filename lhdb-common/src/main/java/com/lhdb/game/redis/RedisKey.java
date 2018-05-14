package com.lhdb.game.redis;

public class RedisKey {
	
	/** 官方收益池**/
	public static String REDIS_INCOME_POOL = "POOL:INCOME";
	/** 官方收益池率**/
	public static String REDIS_INCOME_RATE_POOL = "POOL:INCOME:RATE";
	/** 小奖池**/
	public static String REDIS_SMALL_POOL = "POOL:SMALL";
	/** 小奖池率**/
	public static String REDIS_SMALL_RATE_POOL = "POOL:SMALL:RATE";
	/** 大奖池**/
	public static String REDIS_BIG_POOL = "POOL:BIG";
	/** 大奖池率**/
	public static String REDIS_BIG_RATE_POOL = "POOL:BIG:RATE";
	/** 在线玩家**/
	public static String ONLINE_USER = "ONLINE:USER";
	/** 黑名单**/
	public static String BLACKLIST_USER = "BLACKLIST:USER";

}
