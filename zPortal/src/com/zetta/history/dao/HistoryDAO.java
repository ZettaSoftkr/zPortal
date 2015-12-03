package com.zetta.history.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.history.model.Advenced;
import com.zetta.history.model.Backup;
import com.zetta.history.model.History;



public interface HistoryDAO {
	
      	 public History getById(int bi_h_mng_sn) throws DataAccessException;
      	
	     public List<History> getDataList(int currentPage, int countPerPage, String bi_portal_menu_id)  throws DataAccessException;
	    
	     public int getDataCnt(String bi_portal_menu_id) throws DataAccessException;
	      	
	     public List<Advenced> getAdvencedDataList(int currentPage, int countPerPage, String bi_portal_menu_id)  throws DataAccessException;

	     public int getAdvencedDataCnt(String bi_portal_menu_id) throws DataAccessException;
	      	
	     public List<Backup> getBackupDataList(int currentPage, int countPerPage, String bi_portal_menu_id)  throws DataAccessException;
	     
	     public int getBackupDataCnt(String bi_portal_menu_id) throws DataAccessException;
}