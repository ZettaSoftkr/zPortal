package com.zetta.bookMark.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.common.DateTimeUtil;
import com.zetta.bookMark.dao.BookMarkDAO;
import com.zetta.bookMark.model.BookMark;


@Service
@Transactional
public class BookMarkServiceImpl implements BookMarkService  {
	
	public Logger logger = Logger.getLogger(getClass());

	
	@Autowired
	private BookMarkDAO bookMarkDao;	

	
	@Override
	public BookMark getById(int bookMarkId){
		
		
		return bookMarkDao.getById(bookMarkId);
		
	}

	@Override
	public BookMark save(BookMark bookMark) {
		
		bookMark.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		bookMark.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));

		BookMark result = bookMarkDao.insert(bookMark);

		if (logger.isDebugEnabled()) {
			logger.debug("added new bookMark!!. New bookMarkCode ID : "+ result.getBi_bkmk_sn());
		}

	
		//파일 갯수 위한 메소드 
		
		return result;
		
	}
	
	

	@Override
	public BookMark update(BookMark bookMark) {
		
		
		BookMark getBookMark = this.getById(bookMark.getBi_bkmk_sn());
		
		bookMark.setBi_reg_dt(getBookMark.getBi_reg_dt());
		
		bookMark.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		
		
	
	
		
		BookMark result = bookMarkDao.update(bookMark);
		if (logger.isDebugEnabled()) {
			logger.debug("updated bookMark!!. Update BookMark ID : "+ result.getBi_bkmk_sn());
		}
		
		
		return result;
		
	}
	
	@Override
	public int remove(int bi_bkmk_sn) {
		
	
		return bookMarkDao.delete(bi_bkmk_sn);	
		
	}
	
	

	@Override
	public BookMark findList(int bi_bkmk_sn) {
		
		BookMark bookMark = bookMarkDao.findList(bi_bkmk_sn);
		
		return bookMark;
		
	}

	@Override
	public BookMark findWithView(int bi_bkmk_sn) {
		
	
		return findList(bi_bkmk_sn);
		
	}

	

	@Override
	public List findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent, String bi_unity_cust_id) {
		
		return bookMarkDao.findAllList(currentPage, countPerPage, searchTitle, searchContent, bi_unity_cust_id);
	}
	

	@Override
	public int getTotalCount(int searchTitle, String searchContent, String bi_unity_cust_id) {
		return bookMarkDao.getTotalNo(searchTitle, searchContent, bi_unity_cust_id);
	}
	
	@Override
	public int getCntCheck(int bi_bkmk_sn, String bi_unity_cust_id){
		
		return bookMarkDao.getCntCheck(bi_bkmk_sn,bi_unity_cust_id);
		
	}

	@Override
	public int deleteAll(){
		
		return bookMarkDao.deleteAll();
	}
	
}
