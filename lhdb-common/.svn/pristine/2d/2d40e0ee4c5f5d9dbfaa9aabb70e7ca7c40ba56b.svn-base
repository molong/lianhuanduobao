package com.lhdb.game.util;

public class Page {
	
	
	private int limit = 30;
	
	private int totalCount;
	
	private int totalPages;
	
	private int currentPage=1;
	 
	
	public int getCurrentPage(){
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage){
		 this.currentPage = currentPage;
	}
	
	
	
	public int getTotalPages(){
		totalPages = totalCount/limit;
		if(totalCount%limit>0) return ++totalPages;
		return totalPages;
	}
	
	public int getStart() {
		return (currentPage - 1) * limit;
	}

	/*public void setStart(int start) {
		this.start = start;
	}*/

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	
	
}
