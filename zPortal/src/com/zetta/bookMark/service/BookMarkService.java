package com.zetta.bookMark.service;

import java.util.List;

import com.zetta.bookMark.model.BookMark;



public interface BookMarkService {	
	
	
	   public BookMark getById(int bi_bkmk_sn) ;

	   public BookMark save(BookMark bookMark) ;
	    
	   public BookMark update(BookMark bookMark);

	   public int remove(int bi_bkmk_sn);

	   public BookMark findList(int bi_bkmk_sn);
	    
	   public BookMark findWithView(int bi_bkmk_sn);
	    
	   public List findAllList(int currentPage, int countPerPage,int searchTitle, String searchContent, String bi_unity_cust_id);

	   public int getTotalCount(int searchTitle, String searchContent, String bi_unity_cust_id);
	   
	   public int getCntCheck(int bi_bkmk_sn, String bi_unity_cust_id);
	    
	   public int deleteAll();
	}
