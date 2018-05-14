package com.lhdb.game.mongo.dao;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.lhdb.game.mongo.entity.UserBank;
import com.mongodb.WriteResult;

public class UserBankOperation {
	
	private static Logger logger = Logger.getLogger(UserBankOperation.class);
	
	MongoTemplate mongoTemplate;
	private final String COLLECTION = "bank";
	 
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
    
    public void insertBank(UserBank userBank){
    	if (userBank == null || userBank.getUserId() == null)
			return;
        mongoTemplate.insert(userBank, COLLECTION);
    }
    
    public int updateByUserId(UserBank userBank) {
    	int num = -1;
		if (userBank.getUserId() == null)
			return num;
		Criteria criteria = Criteria.where("userId").is(userBank.getUserId()).and("version").is(userBank.getVersion());
		Query query = new Query(criteria);
		Update update = new Update();
		update.set("score", userBank.getScore());
		update.set("version", userBank.getVersion()+1);
		try{
			WriteResult writeResult = mongoTemplate.updateFirst(query, update, UserBank.class, COLLECTION);
			num = writeResult.getN();
		}catch(Exception e){
			logger.info("mongo update error userid="+userBank.getUserId()+e);
			return 0;
		}
		return num;
	}
    
    public void updateBatchScore(long score, Set<Long> userIds){
    	int num = -1;
    	if(userIds==null||userIds.size()==0)
            return;
        Criteria criteria = Criteria.where("userId").in(userIds);
        Query query = new Query(criteria);
        Update update = Update.update("score", score);
        WriteResult writeResult = mongoTemplate.updateMulti(query, update, COLLECTION);
        num = writeResult.getN();
        logger.info(num);
    }

	public UserBank findByUserId(long uid) {
		Query query = new Query(Criteria.where("userId").is(uid));
		return mongoTemplate.findOne(query, UserBank.class, COLLECTION);
	}
	
	public UserBank findObjectId(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return mongoTemplate.findOne(query, UserBank.class, COLLECTION);
	}
}
