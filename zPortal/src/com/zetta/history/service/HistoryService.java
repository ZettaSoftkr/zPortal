package com.zetta.history.service;

import java.util.List;

import com.zetta.history.model.Advenced;
import com.zetta.history.model.Backup;
import com.zetta.history.model.History;



public interface HistoryService {

	  public History getById(int bi_faq_sn);
	  
	  
	  public int getDataCnt( String bi_portal_menu_id);		    
	  public List<History> getDataList(int currentPage, int countPerPage, String bi_portal_menu_id);
	  
	  public int getAdvencedDataCnt( String bi_portal_menu_id);		    
	  public List<Advenced> getAdvencedDataList(int currentPage, int countPerPage, String bi_portal_menu_id);
	  
	  public int getBackupDataCnt( String bi_portal_menu_id);		    
	  public List<Backup> getBackupDataList(int currentPage, int countPerPage, String bi_portal_menu_id);
}
