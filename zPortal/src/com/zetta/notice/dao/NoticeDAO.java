package com.zetta.notice.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.notice.model.Notice;


public interface NoticeDAO {

	
      	public Notice getById(int bi_nct_sn) throws DataAccessException;
      	
	 	public Notice insert(Notice notice) throws DataAccessException;

	    public Notice update(Notice notice) throws DataAccessException;

	    public int delete(int bi_nct_sn) throws DataAccessException;

	    public Notice findList(int bi_nct_sn) throws DataAccessException;
	    
	    public List findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent)  throws DataAccessException;
	    
	    public int getTotalNo(int searchTitle, String searchContent) throws DataAccessException;
	    
	    public int updateHitCount(int bi_nct_sn) throws DataAccessException;
	    
	    public Notice getPriView(int bi_nct_sn) throws DataAccessException;
	    
	    public Notice getNextView(int bi_nct_sn) throws DataAccessException;
	 
	    public Notice getMainPopUpView(String bi_popup_yn) throws DataAccessException;
	 
	    
}