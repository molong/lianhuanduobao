package com.lhdb.game.mongo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.lhdb.game.mongo.entity.UserBank;
import com.lhdb.game.mongo.entity.UserNotice;
import com.lhdb.game.util.SpringDataPageable;
import com.mongodb.WriteResult;

public class UserNoticeOperation {
	
	private static Logger logger = Logger.getLogger(UserNoticeOperation.class);
	
	MongoTemplate mongoTemplate;
	private final String COLLECTION = "notice";
	 
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
    
    public void insert(UserNotice log){
    	if (log == null)
			return;
        mongoTemplate.insert(log, COLLECTION);
    }
    
    public UserNotice findByUserId(String id){
    	Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
    	return mongoTemplate.findOne(query, UserNotice.class, COLLECTION);
    }
    
    public void delete(String id){
    	Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
    	mongoTemplate.remove(query, UserNotice.class, COLLECTION);
    }

	public Page<UserNotice> find(Integer pageNum) {
		SpringDataPageable pageable = new SpringDataPageable();
		Query query = new Query();
	    List<Order> orders = new ArrayList<Order>();  //排序
	    orders.add(new Order(Direction.DESC, "time"));
	    Sort sort = new Sort(orders);

	    // 开始页
	    pageable.setPagenumber(pageNum);
	    // 每页条数
	    pageable.setPagesize(10);
	    // 排序
	    pageable.setSort(sort);
	    // 查询出一共的条数
	    Long count = mongoTemplate.count(query, UserNotice.class);
	    List<UserNotice> list = mongoTemplate.find(query.with(pageable), UserNotice.class, COLLECTION);
	    // 将集合与分页结果封装
	    Page<UserNotice> pagelist = new PageImpl<UserNotice>(list, pageable, count);
		return pagelist;
	}
	
	
	
	public int update(UserNotice userNotice) {
    	int num = -1;
		if (userNotice == null)
			return num;
		Criteria criteria = Criteria.where("_id").is(userNotice.getId());
		Query query = new Query(criteria);
		Update update = new Update();
		String title = userNotice.getTitle()!=null?userNotice.getTitle():"";
		Integer status = userNotice.getStatus();
//		Date start = userNotice.getStart();
//		Date end = userNotice.getEnd();
		if(!title.equals("")){
			update.set("title", title);
		}
		if(status != null){
			update.set("status", status);
		}
//		if(start != null){
//			update.set("start", start);
//		}
//		if(end != null){
//			update.set("end", end);
//		}
		try{
			WriteResult writeResult = mongoTemplate.updateFirst(query, update, UserBank.class, COLLECTION);
			num = writeResult.getN();
		}catch(Exception e){
			logger.info("mongo update error objectid="+userNotice.getId()+e);
			return 0;
		}
		return num;
	}
}
