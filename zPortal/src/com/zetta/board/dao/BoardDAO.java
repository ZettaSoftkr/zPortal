package com.zetta.board.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.board.model.Board;
import com.zetta.notice.model.Notice;


public interface BoardDAO {

	
      	public Board getById(int bi_bbs_sn) throws DataAccessException;
      	
	 	public Board insert(Board board) throws DataAccessException;

	    public Board update(Board board) throws DataAccessException;

	    public int delete(int bi_bbs_sn) throws DataAccessException;

	    public Board findList(int bi_bbs_sn) throws DataAccessException;
	    
	    public List<Board> findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent)  throws DataAccessException;
	    
	    public List<Board> findMypageList(int currentPage, int countPerPage, int searchTitle, String searchContent, String bi_unity_cust_id)  throws DataAccessException;
	    
	    public int getTotalNo(int searchTitle, String searchContent) throws DataAccessException;
	    
	    public int getMyPageTotalNo(int searchTitle, String searchContent, String bi_unity_cust_id)  throws DataAccessException; 
	    
	    public Board getPreView(int bi_bbs_sn);
	    
	    public Board getNextView(int bi_bbs_sn);
	    
	    public int updateHitCount(int bi_bbs_sn) throws DataAccessException;
	    
	    public int getCntCheck(int bi_bbs_sn, String bi_unity_cust_id) throws DataAccessException;
	    
	    public Board findListDesc() throws DataAccessException;
	    
	    
}