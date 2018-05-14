package com.lhdb.game.redis;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;

public class RedisInitDao extends AbstractBaseRedisDao<String, Object> {
	
	Logger logger = Logger.getLogger(RedisInitDao.class);
	
	//添加手机验证码
	public boolean addTimeEx(final String keyId, final long chatime, final long code) {
		try{
			Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
					byte[] key = serializer.serialize(keyId);
					byte[] member = serializer.serialize(String.valueOf(code));
					// 设置超时时间
					connection.setEx(key, chatime, member);
					return true;
				}
			});
			return result;
		}catch (Exception e) {
			logger.error("用户发送验证码失败", e);
		}
		return false;
	}

	public void addValueToRedis(final String keyId, final int userid, final long count) {
		int result = redisTemplate.execute(new RedisCallback<Integer>() {
			public Integer doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] key = serializer.serialize(keyId);
				byte[] member = serializer.serialize(String.valueOf(userid));
				connection.zIncrBy(key, (double) count, member);
				return 0;
			}
		});
	}
	
	public double addScoreStock(final String key, final double score) {
		try {
			Double result = redisTemplate.execute(new RedisCallback<Double>() {
				public Double doInRedis(RedisConnection connection) throws DataAccessException {

					RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] values = connection.get(keys);
					double sco = 0;
					if (values != null) {
						String sc = serializer.deserialize(values);
						sco = Double.parseDouble(sc);
					}
					sco = sco + score;
					byte[] val = serializer.serialize(sco + "");
					connection.set(keys, val);
					return sco;
				}
			});
			return result;
		} catch (Exception e) {
			logger.error("添加或者减少金币库存挂了", e);
		}
		return 0l;
	}
	
	public double updateScoreStock(final String key, final double score) {
		try {
			Double result = redisTemplate.execute(new RedisCallback<Double>() {
				public Double doInRedis(RedisConnection connection) throws DataAccessException {
					RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] val = serializer.serialize(score + "");
					connection.set(keys, val);
					return score;
				}
			});
			return result;
		} catch (Exception e) {
			logger.error("添加或者减少金币库存挂了", e);
		}
		return 0l;
	}
	
	//查询key
	public double getRedisKey(final String key) {
		try {
			double result = redisTemplate.execute(new RedisCallback<Double>() {
				public Double doInRedis(RedisConnection connection) throws DataAccessException {
					RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] values = connection.get(keys);
					if (values != null) {
						String sc = serializer.deserialize(values);
						return Double.parseDouble(sc);
					}
					return 0D;
				}
			});
			return result;
		} catch (Exception e) {
			logger.error("按"+key+" 查询出错了", e);
		}
		return 0;
	}
	
	/**
	 * @Title: 加用户id到set中
	 * @Description:
	 * @param key
	 * @param userId
	 * @return
	 * @author Horst
	 * @date 2017年1月16日 下午4:55:45
	 * @version V1.0
	 */
	public long addSetKey(final String key, final long userId) {
		long result = redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] myKey = serializer.serialize(key);
				connection.sAdd(myKey, serializer.serialize(String.valueOf(userId)));
				return userId;
			}
		});
		return result;
	}
	
	/**
	 * @Title: 删除set中id
	 * @Description:
	 * @param key
	 * @param userId
	 * @return
	 * @author Horst
	 * @date 2017年1月17日 下午10:48:46
	 * @version V1.0
	 */
	public long delSetKey(final String key, final long userId) {
		long result = redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] myKey = serializer.serialize(key);
				long state = connection.sRem(myKey, serializer.serialize(String.valueOf(userId)));
				return state;
			}
		});
		return result;
	}
	
	/**
	 * @Title: 通过key，获取一个set的member列表
	 * @Description:
	 * @param key
	 * @return
	 * @author Horst
	 * @date 2017年1月16日 下午4:52:55
	 * @version V1.0
	 */
	public Set<Integer> getBlackList(final String key) {
		 Set<Integer> integers = redisTemplate.execute(new RedisCallback<Set<Integer>>() {
	            public Set<Integer> doInRedis(RedisConnection connection) throws DataAccessException {
	                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
	                byte[] bkey = serializer.serialize(key);
	                Set<byte[]> sets = connection.sMembers(bkey);
	                Set<Integer> integers = new HashSet<Integer>(sets.size());
	                for (byte[] set : sets) {
	                    integers.add(Integer.parseInt(serializer.deserialize(set)));
	                }
	                return integers;
	            }
	        });
	        return integers;
	}
	
	/**
	 * @Title: set中是否有用户id
	 * @Description:
	 * @param key
	 * @param userId
	 * @return
	 * @author Horst
	 * @date 2017年1月16日 下午4:53:50
	 * @version V1.0
	 */
	public boolean getSetIsMember(final String key, final long userId) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
            	RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] bkey = serializer.serialize(key);
                boolean isMember = connection.sIsMember(bkey,  serializer.serialize(String.valueOf(userId)));
                return isMember;
            }
        });
        return result;
    }

}
