package com.zetta.faq.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.common.DateTimeUtil;
import com.zetta.faq.dao.FaqDAO;
import com.zetta.faq.model.Faq;
import com.zetta.notice.model.Notice;


@Service
@Transactional
public class FaqServiceImpl implements FaqService  {
	
	public Logger logger = Logger.getLogger(getClass());

	
	@Autowired
	private FaqDAO faqDao;	

	
	@Override
	public Faq getById(int faqId){
		
		
		return faqDao.getById(faqId);
		
	}

	@Override
	public Faq save(Faq faq) {
		
		faq.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		faq.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));

		Faq result = faqDao.insert(faq);

		if (logger.isDebugEnabled()) {
			logger.debug("added new board!!. New faqCode ID : "+ result.getBi_faq_sn());
		}

	
		//파일 갯수 위한 메소드 
		
		return result;
		
	}
	
	

	@Override
	public Faq update(Faq faq) {
		
		 Faq getFaq = this.getById(faq.getBi_faq_sn());
		 logger.info("getFaq:: "+ getFaq.getBi_reg_dt());
		 faq.setBi_reg_dt(getFaq.getBi_reg_dt());
		faq.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
	
		
		Faq result = faqDao.update(faq);
		if (logger.isDebugEnabled()) {
			logger.debug("updated board!!. Update Board ID : "+ result.getBi_faq_sn());
		}
		
		
		return result;
		
	}
	
	@Override
	public int remove(int bi_faq_sn) {
		
	
		return faqDao.delete(bi_faq_sn);	
		
	}
	
	

	@Override
	public Faq findList(int bi_faq_sn) {
		
		faqDao.updateHitCount(bi_faq_sn);
		
		Faq faq = faqDao.findList(bi_faq_sn);
		
		return faq;
		
	}

	@Override
	public Faq findWithView(int bi_faq_sn) {
		
	
		return findList(bi_faq_sn);
		
	}

	

	
	@Override
	public List<Faq> getFaqList(int searchTitle, String searchContent) {
		
		return faqDao.getFaqList(searchTitle, searchContent);
	}
	
	
	
	@Override
	public List<Faq> findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent) {
		
		return faqDao.findAllList(currentPage, countPerPage, searchTitle, searchContent);
	}
	


	@Override
	public int getTotalCount(int searchTitle, String searchContent) {
		return faqDao.getTotalNo(searchTitle, searchContent);
	}
	
	@Override
	public Faq  getPriView(int bi_faq_sn) {
		
		return faqDao.getPriView(bi_faq_sn);
	}
	@Override
	public Faq getNextView(int bi_faq_sn) {
	
		return faqDao.getNextView(bi_faq_sn);
	
	}
	

	
}
