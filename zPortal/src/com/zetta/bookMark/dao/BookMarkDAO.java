package com.zetta.bookMark.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.bookMark.model.BookMark;


public interface BookMarkDAO {

	
      	public BookMark getById(int bi_bkmk_sn) throws DataAccessException;
      	
	 	public BookMark insert(BookMark bookMark) throws DataAccessException;

	    public BookMark update(BookMark bookMark) throws DataAccessException;

	    public int delete(int bi_bkmk_sn) throws DataAccessException;

	    public BookMark findList(int bi_bkmk_sn) throws DataAccessException;
	    
	    public List findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent, String bi_unity_cust_id)  throws DataAccessException;
	    
	    public int getTotalNo(int searchTitle, String searchContent, String bi_unity_cust_id) throws DataAccessException;
	    
	    public int getCntCheck(int bi_bkmk_sn, String bi_unity_cust_id) throws DataAccessException;
	    	    
	    public int deleteAll() throws DataAccessException;
}