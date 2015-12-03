package com.zetta.faq.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.faq.model.Faq;
import com.zetta.notice.model.Notice;


public interface FaqDAO {

	
      	public Faq getById(int bi_faq_sn) throws DataAccessException;
      	
	 	public Faq insert(Faq faq) throws DataAccessException;

	    public Faq update(Faq faq) throws DataAccessException;

	    public int delete(int bi_faq_sn) throws DataAccessException;

	    public Faq findList(int bi_faq_sn) throws DataAccessException;
	    
	    public List<Faq> findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent)  throws DataAccessException;
	    
	    
	    public List<Faq> getFaqList(int searchTitle, String searchContent)  throws DataAccessException;
	    
	    
	    public int getTotalNo(int searchTitle, String searchContent) throws DataAccessException;
	    
	 
	    public Faq getPriView(int bi_faq_sn);
	    
	    public Faq getNextView(int bi_faq_sn);
	    
	    public int updateHitCount(int bi_faq_sn) throws DataAccessException;
}