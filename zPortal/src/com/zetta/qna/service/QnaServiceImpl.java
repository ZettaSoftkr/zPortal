package com.zetta.qna.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.common.DateTimeUtil;
import com.zetta.qna.dao.QnaDAO;
import com.zetta.qna.model.Qna;


@Service
@Transactional
public class QnaServiceImpl implements QnaService  {
	
	public Logger logger = Logger.getLogger(getClass());

	
	@Autowired
	private QnaDAO qnaDao;	

	
	@Override
	public Qna getById(int qnaId){
		
		return qnaDao.getById(qnaId);
		
	}

	@Override
	public Qna save(Qna qna) {
		
		qna.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		qna.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		qna.setBi_answer_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		qna.setBi_answer_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		
		Qna result = qnaDao.insert(qna);

		if (logger.isDebugEnabled()) {
			logger.debug("added new qna!!. New qnaCode ID : "+ result.getBi_reg_dt());
		}
		//파일 갯수 위한 메소드 
		
		return result;
		
	}

	@Override
	public Qna update(Qna qna) {
		
		
		Qna getQna = this.getById(qna.getBi_qna_sn());
		qna.setBi_answer_yn(getQna.getBi_answer_yn());
		qna.setBi_answer_cn(getQna.getBi_answer_cn());
		qna.setBi_reg_dt(getQna.getBi_reg_dt());
		qna.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		qna.setBi_answer_reg_dt(getQna.getBi_answer_reg_dt());
		qna.setBi_answer_updt_dt(getQna.getBi_answer_updt_dt());
		qna.setBi_inqire_num(qna.getBi_inqire_num());
		
		Qna result = qnaDao.update(qna);
		if (logger.isDebugEnabled()) {
			logger.debug("updated qna!!. Update Qna ID : "+ result.getBi_qna_sn());
		}
		
		
		return result;
		
	}
	
	@Override
	public Qna answerUpdate(Qna qna) {
		
		
		Qna getQna = this.getById(qna.getBi_qna_sn());
		
		qna.setBi_unity_cust_id(getQna.getBi_unity_cust_id());
		qna.setBi_titl(getQna.getBi_titl());
		qna.setBi_cn(getQna.getBi_cn());
		
		qna.setBi_reg_dt(getQna.getBi_reg_dt());
		qna.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		qna.setBi_answer_reg_dt(getQna.getBi_answer_reg_dt());
		qna.setBi_answer_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		
		
		Qna result = qnaDao.update(qna);
		
		return result;
		
	}
	
	@Override
	public int remove(int bi_qna_sn) {
		
	
		return qnaDao.delete(bi_qna_sn);	
		
	}

	@Override
	public Qna findList(int bi_qna_sn) {
		
		
		qnaDao.updateHitCount(bi_qna_sn);		
		Qna qna = qnaDao.findList(bi_qna_sn);
		
		return qna;
		
	}

	@Override
	public Qna findWithView(int bi_qna_sn) {
		return findList(bi_qna_sn);
	}

	@Override
	public List<Qna> findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent) {
		
		return qnaDao.findAllList(currentPage, countPerPage, searchTitle, searchContent);
	}

	@Override
	  public List<Qna> findMypageList(int currentPage, int countPerPage,int searchTitle, String searchContent, String bi_unity_cust_id){
		  
		  return qnaDao.findMypageList(currentPage, countPerPage, searchTitle, searchContent, bi_unity_cust_id);
	  }
	
	@Override
	public int getTotalCount(int searchTitle, String searchContent) {
		return qnaDao.getTotalNo(searchTitle, searchContent);
	}
	
	@Override
	public int getMypageTotalNo(int searchTitle, String searchContent, String bi_unity_cust_id) {
		return qnaDao.getMypageTotalNo(searchTitle, searchContent, bi_unity_cust_id);
	}
	
	public int getCntCheck(int bi_qna_sn, String bi_unity_cust_id){
		
		return qnaDao.getCntCheck(bi_qna_sn,bi_unity_cust_id);
		
	}
}
