package com.lhdb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhdb.game.mongo.dao.UserWinLoseLogOperation;
import com.lhdb.game.mongo.entity.UserJewelDownLog;
import com.lhdb.game.mongo.entity.UserJewelDownLog.DownInfoVO;
import com.lhdb.game.mongo.entity.UserNotice;
import com.lhdb.game.util.Page;
import com.lhdb.game.util.Response;
import com.lhdb.game.util.pojo.ParamUtilVO;
import com.lhdb.service.JewelDownLogService;


@Service
public class JewelDownLogServiceImpl implements JewelDownLogService{
	
	@Autowired
	private UserWinLoseLogOperation userWinLoseLogOperation;
	
	public Response search(ParamUtilVO paramUtilVO, Page pager){
		Response response = new Response();
		org.springframework.data.domain.Page<UserJewelDownLog> list = userWinLoseLogOperation.findJewelDownLogPage(paramUtilVO, pager);
		List<UserJewelDownLog> list1 = list.getContent();
		for(UserJewelDownLog log:list1) {
			int fetchScore = log.getFetchScore();
			List<DownInfoVO> downLists = log.getScoreList();
			if(fetchScore == 0 && downLists.size() == 0) {
				List<DownInfoVO> scoreList = new ArrayList<>();
				Integer[][] cell = log.getJewelList();
				int xSize = 4;
				switch (log.getVisitor()) {
				case 1:
					xSize = 4;
					break;
				case 2:
					xSize = 5;
					break;
				case 3:
					xSize = 6;
					break;
				}
				Integer[][] cellList = new Integer[xSize][xSize];
				for(int i=0;i<xSize;i++) {
					for(int j=0;j<xSize;j++) {
						cellList[i][j] = cell[i][j];
					}
				}
				DownInfoVO downInfoVO = new DownInfoVO();
				downInfoVO.setCellList(cellList);
				scoreList.add(downInfoVO);
				log.setScoreList(scoreList);
			}
		}
		pager.setTotalCount(pager.getTotalCount());
		response.setData(list.getContent());
		UserNotice notice = new UserNotice();
		notice.setPager(pager);
		response.setParams(notice);
		return response;
	}
}
