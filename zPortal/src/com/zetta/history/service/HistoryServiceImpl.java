package com.zetta.history.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.history.dao.HistoryDAO;
import com.zetta.history.model.Advenced;
import com.zetta.history.model.Backup;
import com.zetta.history.model.History;


@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

	public Logger logger = Logger.getLogger(getClass());

	
	@Autowired
	private HistoryDAO historyDao;	

	
	@Override
	public History getById(int historyId){
		return historyDao.getById(historyId);
	}
	
	@Override
	public List<History> getDataList(int currentPage, int countPerPage, String bi_portal_menu_id) {
		return historyDao.getDataList(currentPage, countPerPage, bi_portal_menu_id);
	}
	
	@Override
	public int getDataCnt(String bi_portal_menu_id) {
		return historyDao.getDataCnt(bi_portal_menu_id);
	}
	
	
	@Override
	public List<Advenced> getAdvencedDataList(int currentPage, int countPerPage, String bi_portal_menu_id) {
		return historyDao.getAdvencedDataList(currentPage, countPerPage, bi_portal_menu_id);
	}
	
	@Override
	public int getAdvencedDataCnt(String bi_portal_menu_id) {
		return historyDao.getAdvencedDataCnt(bi_portal_menu_id);
	}
	
	
	@Override
	public List<Backup> getBackupDataList(int currentPage, int countPerPage, String bi_portal_menu_id) {
		return historyDao.getBackupDataList(currentPage, countPerPage, bi_portal_menu_id);
	}
	
	@Override
	public int getBackupDataCnt(String bi_portal_menu_id) {
		return historyDao.getBackupDataCnt(bi_portal_menu_id);
	}
}
