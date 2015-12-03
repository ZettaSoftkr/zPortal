package com.zetta.comment.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.comment.model.Comment;


public interface CommentDAO {

	
      	public Comment getById(int bi_bbs_ansr_sn) throws DataAccessException;
      	
	 	public Comment insert(Comment comment) throws DataAccessException;

	    public Comment update(Comment comment) throws DataAccessException;

	    public int delete(int bi_bbs_ansr_sn) throws DataAccessException;

	    public Comment findList(int bi_bbs_ansr_sn) throws DataAccessException;
	    
	    public List findAllList(int bi_bbs_sn, int searchTitle, String searchContent)  throws DataAccessException;
	    
	    public int getTotalNo(int bi_bbs_sn, int searchTitle, String searchContent) throws DataAccessException;
	    
	 
	    
}