package com.zetta.log.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.log.model.LogFail;
import com.zetta.log.model.LogInfo;
import com.zetta.log.model.LoginInfo;


public interface LogDAO {

	
      	public LoginInfo getById(int bi_sn) throws DataAccessException;
      	
	 	public LoginInfo insert(LoginInfo log) throws DataAccessException;
	 	
	 	public LoginInfo update(LoginInfo log) throws DataAccessException;
	 	
	 	public int delete(int bi_sn) throws DataAccessException;
	 	
	 	public int pageInsert(LogInfo logInfo) throws DataAccessException;
	 	
	 	public int logOutUpdate(String sessionId ,Timestamp  logoutTime) throws DataAccessException;

	    public LoginInfo findList(int bi_sn) throws DataAccessException;
	    
	    public List getHistoryView(String bi_unity_cust_id, String bi_menu_type_yn) throws DataAccessException;
	    
	    
	    public LogFail logFailSave(LogFail log) throws DataAccessException;
	    
}