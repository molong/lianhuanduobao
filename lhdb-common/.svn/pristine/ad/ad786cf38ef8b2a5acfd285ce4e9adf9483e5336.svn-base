package com.lhdb.game.entity;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.lhdb.game.util.Page;
import com.lhdb.game.util.Sort;

public class BasePojo {
	private String sort;
	
	private Page pager;
	
	private Sort sortObj;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Page getPager() {
		return pager;
	}

	public void setPager(Page pager) {
		this.pager = pager;
	}

	public Sort getSortObj() {
		
		if(sort!=null && sort.trim().length()>0){
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
			Sort[] sorts = null;
			try {
				sorts = mapper.readValue(sort, Sort[].class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(sorts != null && sorts.length >0 ) 
				return sorts[0];
			else
				return null; 
		}else
			return null;
	}

	public void setSortObj(Sort sortObj) {
		this.sortObj = sortObj;
	}
	
	
}
