package com.lhdb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhdb.game.dao.OrderDfMapper;
import com.lhdb.game.dao.OrderInfoMapper;
import com.lhdb.game.entity.OrderDf;
import com.lhdb.game.entity.OrderInfo;
import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;
import com.lhdb.service.OrderInfoService;

@Service
public class OrderInfoServiceImpl implements OrderInfoService{
	
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	@Autowired
	private OrderDfMapper orderDfMapper;
	
	/**
	 * @Title: 查询订单详情列表
	 * @Description: 
	 * @param orderInfo
	 * @param pager
	 * @return
	 * @author Horst
	 * @date 2017年7月11日 上午12:18:19
	 * @version V1.0
	 */
	@Override
	public Response search(OrderInfo orderInfo, Page pager){
		Response response = new Response();
		orderInfo.setPager(pager);
		List<OrderInfo> list = orderInfoMapper.searchOnPage(orderInfo);
		int count = orderInfoMapper.count(orderInfo);
		pager.setTotalCount(count);
		response.setData(list);
		response.setParams(orderInfo);
		return response;
	}
	
	@Override
	public Response searchDf(OrderInfo orderInfo, Page pager){
		Response response = new Response();
		orderInfo.setPager(pager);
		List<OrderDf> list = orderDfMapper.searchOnPage(orderInfo);
		int count = orderDfMapper.count(orderInfo);
		pager.setTotalCount(count);
		response.setData(list);
		response.setParams(orderInfo);
		return response;
	}

}
