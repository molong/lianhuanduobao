package com.lhdb.game.redis;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fanfeipeng on 2015/6/19 0019.
 * 自行封装的redis接口，读取各种类型的数据
 * 推荐直接使用redisTemplate
 */
@Deprecated
public class RedisOperationDao extends AbstractBaseRedisDao {

    /**
     * 通过key，获取一个set的member个数
     *
     * @param key
     * @return
     */
    public long getSetCountByKey(final String key) {
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] bkey = serializer.serialize(key);
                return connection.sCard(bkey);
            }
        });
        return result;
    }

    /**
     * 通过key，获取一个set的member列表
     *
     * @param key
     * @return
     */
    public Set<Integer> getSetIntegerMembersByKey(final String key) {
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
     * 通过key和member，获取sorted set中一个member的排名和分数
     *
     * @param key
     * @param member
     * @return
     */
    public Pair<Long, Double> getZRevRankAndScoreByMember(final String key, final String member) {
        final RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        Long result = redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bkey = serializer.serialize(key);
                return connection.zRevRank(bkey, serializer.serialize(member));
            }
        });
        if (null == result) {
            return null;
        }
        Double score = redisTemplate.execute(new RedisCallback<Double>() {
            public Double doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bkey = serializer.serialize(key);
                return connection.zScore(bkey, serializer.serialize(member));
            }
        });
        return new ImmutablePair<Long, Double>(result, score);
    }

    public void sadd(final String key,final String member,final long expiretime){
        final RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        redisTemplate.execute(new RedisCallback<Integer>() {
            @Override
            public Integer doInRedis(RedisConnection connection) throws DataAccessException {
                connection.sAdd(serializer.serialize(key),serializer.serialize(member));
                connection.pExpireAt(serializer.serialize(key),expiretime);
                return null;
            }
        });
    }

    //没有数据时返回null
    public String get(final String key){
        String rs = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] rs = connection.get(serializer.serialize(key));
                if (rs != null)
                    return serializer.deserialize(rs);
                return null;
            }
        });
        return rs;
    }

    public void hset(final String key,final String field,final String value,final long expiretime){
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.hSet(serializer.serialize(key), serializer.serialize(field), serializer.serialize(value));
                if (expiretime != -1) {
                    connection.pExpireAt(serializer.serialize(key), expiretime);
                }
                return null;
            }
        });
    }

    public String hget(final String key,final String field){
        String rs = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                return serializer.deserialize(redisConnection.hGet(serializer.serialize(key),serializer.serialize(field)));
            }
        });
        return rs;
    }



}
