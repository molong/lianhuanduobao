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

import com.lhdb.game.mongo.entity.UserRebateLog;
import com.lhdb.game.util.DateUtil;
import com.lhdb.game.util.SpringDataPageable;
import com.lhdb.game.util.pojo.ParamUtilVO;

public class UserRebateLogOperation {
	
	private static Logger logger = Logger.getLogger(UserWinLoseLogOperation.class);
	
	MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	/** 是否领取反水**/
	private final String COLLECTION_REBATE = "rebatelog";
	
	public Page<UserRebateLog> findUserRebateLogPage(ParamUtilVO paramUtilVO, com.lhdb.game.util.Page pager){
		SpringDataPageable pageable = new SpringDataPageable();
		Query query = new Query();
		if(paramUtilVO.getUserId() != null) {
			query.addCriteria(Criteria.where("userId").is(paramUtilVO.getUserId()));
		}
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
	    Long count = mongoTemplate.count(query, COLLECTION_REBATE);
	    List<UserRebateLog> list =  mongoTemplate.find(query.with(pageable), UserRebateLog.class, COLLECTION_REBATE);
	    pager.setTotalCount(count.intValue());
	    // 将集合与分页结果封装
	    Page<UserRebateLog> pagelist = new PageImpl<UserRebateLog>(list, pageable, count);
		return pagelist;
	}

}
