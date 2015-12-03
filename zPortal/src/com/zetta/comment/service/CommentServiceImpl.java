package com.zetta.comment.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.board.model.Board;
import com.zetta.common.DateTimeUtil;
import com.zetta.comment.dao.CommentDAO;
import com.zetta.comment.model.Comment;


@Service
@Transactional
public class CommentServiceImpl implements CommentService  {
	
	public Logger logger = Logger.getLogger(getClass());

	
	@Autowired
	private CommentDAO commentDao;	

	
	@Override
	public Comment getById(int bi_bbs_ansr_sn){
		
		
		return commentDao.getById(bi_bbs_ansr_sn);
		
	}

	@Override
	public Comment save(Comment comment) {
		
		comment.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		comment.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));

		Comment result = commentDao.insert(comment);

	
	
		//파일 갯수 위한 메소드 
		
		return result;
		
	}
	
	

	@Override
	public Comment update(Comment comment) {
		
		
		Comment getComment = this.getById(comment.getBi_bbs_ansr_sn());
		
		comment.setBi_reg_dt(getComment.getBi_reg_dt());
		
		comment.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		
		
	
		Comment result = commentDao.update(comment);
		
		
		
		return result;
		
	}
	
	@Override
	public int remove(int bi_ansr_sn) {
		
	
		return commentDao.delete(bi_ansr_sn);	
		
	}
	
	

	@Override
	public Comment findList(int bi_ansr_sn) {
		
		Comment comment = commentDao.findList(bi_ansr_sn);
		
		return comment;
		
	}

	@Override
	public Comment findWithView(int bi_ansr_sn) {
		
	
		return findList(bi_ansr_sn);
		
	}

	

	@Override
	public List findAllList(int bi_bbs_sn, int searchTitle, String searchContent) {
		
		return commentDao.findAllList(bi_bbs_sn, searchTitle, searchContent);
	}
	


	@Override
	public int getTotalCount(int bi_bbs_sn,  int searchTitle, String searchContent) {
		return commentDao.getTotalNo(bi_bbs_sn,  searchTitle, searchContent);
	}
	
	

	
}
