package com.zetta.qna.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.qna.model.Qna;


public interface QnaDAO {

	
      	public Qna getById(int bi_qna_sn) throws DataAccessException;
      	
	 	public Qna insert(Qna qna) throws DataAccessException;

	    public Qna update(Qna qna) throws DataAccessException;

	    public int delete(int bi_qna_sn) throws DataAccessException;

	    public Qna findList(int bi_qna_sn) throws DataAccessException;
	    
	    public List<Qna> findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent)  throws DataAccessException;
	    
	    public List<Qna> findMypageList(int currentPage, int countPerPage, int searchTitle, String searchContent, String bi_unity_cust_id)  throws DataAccessException;
	    
	    public int getTotalNo(int searchTitle, String searchContent) throws DataAccessException;
	    
	    public int getMypageTotalNo(int searchTitle, String searchContent, String bi_unity_cust_id) throws DataAccessException;
	    
	    
	    public int getCntCheck(int bi_qna_sn, String bi_unity_cust_id) throws DataAccessException;
	 
		public int updateHitCount(int bi_qna_sn) throws DataAccessException;
}