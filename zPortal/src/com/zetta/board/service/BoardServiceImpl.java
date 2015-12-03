package com.zetta.board.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.board.dao.BoardDAO;
import com.zetta.board.model.Board;
import com.zetta.common.DateTimeUtil;
import com.zetta.notice.model.Notice;


@Service
@Transactional
public class BoardServiceImpl implements BoardService  {
	
	public Logger logger = Logger.getLogger(getClass());

	
	@Autowired
	private BoardDAO boardDao;	

	
	@Override
	public Board getById(int bi_bbs_sn){
		
		
		return boardDao.getById(bi_bbs_sn);
		
	}

	@Override
	public Board save(Board board) {
		
		board.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		board.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));

		Board result = boardDao.insert(board);

		if (logger.isDebugEnabled()) {
			logger.debug("added new board!!. New boardCode ID : "+ result.getBi_bbs_sn());
		}

	
		//파일 갯수 위한 메소드 
		
		return result;
		
	}
	
	

	@Override
	public Board update(Board board) {
		
		
		Board getBoard = this.getById(board.getBi_bbs_sn());
		board.setBi_unity_cust_id(getBoard.getBi_unity_cust_id());
		board.setBi_reg_dt(getBoard.getBi_reg_dt());
		board.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
	
		
		Board result = boardDao.update(board);
		if (logger.isDebugEnabled()) {
			logger.debug("updated board!!. Update Board ID : "+ result.getBi_bbs_sn());
		}
		
		
		return result;
		
	}
	
	@Override
	public int remove(int bi_bbs_sn) {
		
	
		return boardDao.delete(bi_bbs_sn);	
		
	}
	
	

	@Override
	public Board findList(int bi_bbs_sn) {
	
		boardDao.updateHitCount(bi_bbs_sn);
		Board board = boardDao.findList(bi_bbs_sn);
		
		return board;
		
	}

	@Override
	public Board findWithView(int bi_bbs_sn) {
	
		return findList(bi_bbs_sn);
	}

	

	@Override
	public List<Board> findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent) {
		
		return boardDao.findAllList(currentPage, countPerPage, searchTitle, searchContent);
	}
	
	@Override
	public List<Board> findMypageList(int currentPage, int countPerPage, int searchTitle, String searchContent, String bi_unity_cust_id) {
		
		return boardDao.findMypageList(currentPage, countPerPage, searchTitle, searchContent, bi_unity_cust_id);
	}
	


	@Override
	public int getTotalCount(int searchTitle, String searchContent) {
		return boardDao.getTotalNo(searchTitle, searchContent);
	}
	
	@Override
	public int getMyPageTotalNo(int searchTitle, String searchContent, String bi_unity_cust_id){
		
		return boardDao.getMyPageTotalNo(searchTitle, searchContent, bi_unity_cust_id);
	}
	
	@Override
	public Board  getPreView(int bi_bbs_sn) {
		
		return boardDao.getPreView(bi_bbs_sn);
	}
	@Override
	public Board getNextView(int bi_bbs_sn) {
	
		return boardDao.getNextView(bi_bbs_sn);
	
	}

	@Override
	public int getCntCheck(int bi_bbs_sn, String bi_unity_cust_id){
		
		return boardDao.getCntCheck(bi_bbs_sn,bi_unity_cust_id);
		
	}
	@Override
	public Board findListDesc(){
		
		return boardDao.findListDesc();
	}
	
}
