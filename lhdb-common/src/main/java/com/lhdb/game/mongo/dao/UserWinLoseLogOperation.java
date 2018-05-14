package com.lhdb.game.mongo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.lhdb.game.mongo.entity.UserJewelDownLog;
import com.lhdb.game.mongo.entity.UserWinLoseLog;
import com.lhdb.game.util.SpringDataPageable;
import com.lhdb.game.util.pojo.ParamUtilVO;
import com.mongodb.WriteResult;

public class UserWinLoseLogOperation {
	
	private static Logger logger = Logger.getLogger(UserWinLoseLogOperation.class);
	
	MongoTemplate mongoTemplate;
	private final String COLLECTION = "winloselog";
	/** 掉落珠子详情**/
	private final String COLLECTION_DOWN = "jeweldownlog";
	 
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
    
    public String insert(UserWinLoseLog log){
    	if (log == null || log.getUserId() == null)
			return null;
        mongoTemplate.insert(log, COLLECTION);
        return log.getId();
    }
	
	public UserWinLoseLog findByUserIdAndWinScore(String id) {
		Query query = new Query(Criteria.where("_id").is(id).and("winScore").gt(20000).and("status").is(0));
		return mongoTemplate.findOne(query, UserWinLoseLog.class, COLLECTION);
	}
	
	public int updateStatus(UserWinLoseLog log) {
    	int num = -1;
		if (log == null)
			return num;
		Criteria criteria = Criteria.where("_id").is(log.getId());
		Query query = new Query(criteria);
		Update update = new Update();
		update.set("status", 1);
		try{
			WriteResult writeResult = mongoTemplate.updateFirst(query, update, UserWinLoseLog.class, COLLECTION);
			num = writeResult.getN();
		}catch(Exception e){
			logger.info("mongo update error id="+log.getId()+e);
			return 0;
		}
		return num;
	}
	
	/**
	 * @Title: 掉落珠子详情分页
	 * @Description:
	 * @param pageNum
	 * @return
	 * @author Horst
	 * @date 2017年6月26日 下午8:46:08
	 * @version V1.0
	 */
	public Page<UserJewelDownLog> findJewelDownLogPage(ParamUtilVO paramUtilVO, com.lhdb.game.util.Page pager){
		SpringDataPageable pageable = new SpringDataPageable();
		Query query = new Query();
		if(paramUtilVO.getUserId() != null) {
			query.addCriteria(Criteria.where("userId").is(paramUtilVO.getUserId()));
		}
//		Date start = paramUtilVO.getStart();
//		if(start != null) {
//			query.addCriteria(Criteria.where("time").gte(start));
//		}
//		Date end = paramUtilVO.getEnd();
//		if(end != null) {
//			query.addCriteria(Criteria.where("time").lte(end));
//		}
//		Integer min = paramUtilVO.getMin();
//		if(min != null) {
//			query.addCriteria(Criteria.where("fetchScore").gte(min));
//		}
//		Integer max = paramUtilVO.getMax();
//		if(max != null) {
//			query.addCriteria(Criteria.where("fetchScore").lte(max));
//		}
//		List<Order> orders = new ArrayList<Order>();  //排序
//	    orders.add(new Order(Direction.DESC, "time"));
//	    Sort sort = new Sort(orders);
		// 开始页
	    pageable.setPagenumber(pager.getCurrentPage());
    	// 每页条数
    	pageable.setPagesize(30);
	    // 排序
//	    pageable.setSort(sort);
	    // 查询出一共的条数
	    Long count = mongoTemplate.count(query, COLLECTION_DOWN);
	    List<UserJewelDownLog> list =  mongoTemplate.find(query.with(pageable), UserJewelDownLog.class, COLLECTION_DOWN);
	    pager.setTotalCount(count.intValue());
	    // 将集合与分页结果封装
	    Page<UserJewelDownLog> pagelist = new PageImpl<UserJewelDownLog>(list, pageable, count);
		return pagelist;
	}
}
