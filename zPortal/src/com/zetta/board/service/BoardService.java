package com.zetta.board.service;

import java.util.List;

import com.zetta.board.model.Board;



public interface BoardService {	
	
	
	   public Board getById(int bi_bbs_sn) ;

	   public Board save(Board board) ;
	    
	   public Board update(Board board);

	   public int remove(int bi_bbs_sn);

	   public Board findList(int bi_bbs_sn);
	    
	   public Board findWithView(int bi_bbs_sn);
	    
	   public List<Board> findAllList(int currentPage, int countPerPage,int searchTitle, String searchContent);
	   
	   public List<Board> findMypageList(int currentPage, int countPerPage,int searchTitle, String searchContent, String bi_unity_cust_id);

	   public int getTotalCount(int searchTitle, String searchContent);
	   
	   public int getMyPageTotalNo(int searchTitle, String searchContent, String bi_unity_cust_id);
	   
	   public Board getPreView(int bi_bbs_sn);
	    
	   public Board getNextView(int bi_bbs_sn);
	    
	   public int getCntCheck(int bi_bbs_sn, String bi_unity_cust_id);
	   
	   public Board findListDesc();
	    
	}
