package com.zetta.notice.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.common.DateTimeUtil;
import com.zetta.notice.dao.NoticeDAO;
import com.zetta.notice.model.Notice;

import egovframework.com.utl.fcc.service.EgovStringUtil;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

	public Logger logger = Logger.getLogger(getClass());

	@Autowired
	private NoticeDAO noticeDao;

	@Override
	public Notice getById(int bi_nct_sn) {

		return noticeDao.getById(bi_nct_sn);

	}

	@Override
	public Notice save(Notice notice) {

		notice.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		notice.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		
		notice.setBi_cn(EgovStringUtil.getHtmlStrCnvr(notice.getBi_cn())); //html 방지
		
		logger.info("날짜1::" + notice.getBi_reg_dt());
		logger.info("날짜2::" + notice.getBi_updt_dt());

		Notice result = noticeDao.insert(notice);
		// 파일 갯수 위한 메소드

		return result;

	}

	@Override
	public Notice update(Notice notice) {

		Notice getNotice = this.getById(notice.getBi_nct_sn());
		notice.setBi_reg_dt(getNotice.getBi_reg_dt());
		notice.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));

		Notice result = noticeDao.update(notice);
		if (logger.isDebugEnabled()) {
			logger.debug("updated board!!. Update Board ID : " + result.getBi_nct_sn());
		}

		return result;

	}

	@Override
	public int remove(int bi_nct_sn) {

		return noticeDao.delete(bi_nct_sn);

	}

	@Override
	public Notice findList(int bi_nct_sn) {

		noticeDao.updateHitCount(bi_nct_sn);

		Notice notice = noticeDao.findList(bi_nct_sn);

		return notice;

	}

	@Override
	public List findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent) {

		return noticeDao.findAllList(currentPage, countPerPage, searchTitle, searchContent);
	}

	@Override
	public int getTotalCount(int searchTitle, String searchContent) {
		return noticeDao.getTotalNo(searchTitle, searchContent);
	}

	@Override
	public Notice getPriView(int bi_nct_sn) {

		return noticeDao.getPriView(bi_nct_sn);
	}

	@Override
	public Notice getNextView(int bi_nct_sn) {

		return noticeDao.getNextView(bi_nct_sn);

	}

	public Notice getMainPopUpView(String bi_popup_yn) {

		return noticeDao.getMainPopUpView(bi_popup_yn);
	}

}
