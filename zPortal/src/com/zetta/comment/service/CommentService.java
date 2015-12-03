package com.zetta.comment.service;

import java.util.List;

import com.zetta.comment.model.Comment;



public interface CommentService {	
	
	
	   public Comment getById(int bi_bbs_ansr_sn) ;

	   public Comment save(Comment comment) ;
	    
	   public Comment update(Comment comment);

	   public int remove(int bi_bbs_ansr_sn);

	   public Comment findList(int bi_bbs_ansr_sn);
	    
	   public Comment findWithView(int bi_bbs_ansr_sn);
	    
	   public List findAllList(int bi_bbs_sn, int searchTitle, String searchContent);

	   public int getTotalCount(int bi_bbs_sn, int searchTitle, String searchContent);
	   
	   
	    
	}
