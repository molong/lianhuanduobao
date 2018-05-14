package com.lhdb.game.mongo.dao;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.lhdb.game.mongo.entity.OnlineUser;

public class OnlineUserOperation {

	MongoTemplate mongoTemplate;
	private final String COLLECTION = "online";
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
    
    public void insertBank(OnlineUser onlineUser){
    	if (onlineUser == null || onlineUser.get_id() == null)
			return;
        mongoTemplate.insert(onlineUser, COLLECTION);
    }
    
    public OnlineUser findById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return mongoTemplate.findOne(query, OnlineUser.class, COLLECTION);
	}
    
    public void remove(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		mongoTemplate.remove(query, OnlineUser.class, COLLECTION);
	}
    
    public List<OnlineUser> findAll(){
    	return mongoTemplate.findAll(OnlineUser.class, COLLECTION);
    }
	
}
