package com.zetta.qna.service;

import java.util.List;

import com.zetta.qna.model.Qna;



public interface QnaService {	
	
	
	   public Qna getById(int bi_qna_sn) ;

	   public Qna save(Qna qna) ;
	    
	   public Qna update(Qna qna);
	   
	   public Qna answerUpdate(Qna qna);
	   

	   public int remove(int bi_qna_sn);

	   public Qna findList(int bi_qna_sn);
	    
	   public Qna findWithView(int bi_qna_sn);
	    
	   public List<Qna> findAllList(int currentPage, int countPerPage,int searchTitle, String searchContent);
	   
	   public List<Qna> findMypageList(int currentPage, int countPerPage,int searchTitle, String searchContent, String bi_unity_cust_id);

	   public int getTotalCount(int searchTitle, String searchContent);
	   
	   public int getMypageTotalNo(int searchTitle, String searchContent, String bi_unity_cust_id);
	   
	   public int getCntCheck(int bi_qna_sn, String bi_unity_cust_id);
	    
	}
