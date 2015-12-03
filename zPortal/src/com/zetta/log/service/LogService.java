package com.zetta.log.service;

import java.sql.Timestamp;
import java.util.List;

import com.zetta.log.model.LogFail;
import com.zetta.log.model.LoginInfo;
import com.zetta.log.model.LogInfo;



public interface LogService {	
	
	
	   public LoginInfo getById(int bi_sn) ;

	   public LoginInfo save(LoginInfo log);
	   
	   public int logOutUpdate(String sessionId ,Timestamp  logoutTime);
	   
	   public int pageSave(LogInfo logInfo) ;
	    
	   public LoginInfo update(LoginInfo log);

	   public int remove(int bi_sn);

	   public LoginInfo findList(int bi_sn);
	    
	   public List getHistoryView(String bi_unity_cust_id, String bi_menu_type_yn);
	    
	   public LogFail logFailSave(LogFail log);
	    
	}
