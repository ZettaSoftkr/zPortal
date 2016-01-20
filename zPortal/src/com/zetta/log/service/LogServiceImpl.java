package com.zetta.log.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.common.DateTimeUtil;
import com.zetta.log.dao.LogDAO;
import com.zetta.log.model.LogFail;
import com.zetta.log.model.LogInfo;
import com.zetta.log.model.LoginInfo;


@Service
@Transactional
public class LogServiceImpl implements LogService  {
	
	public Logger logger = Logger.getLogger(getClass());

	
	@Autowired
	private LogDAO logDao;	

	
	@Override
	public LoginInfo getById(int bi_sn){
		
		
		return logDao.getById(bi_sn);
		
	}

	@Override
	public LoginInfo save(LoginInfo log) {
		
		log.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		log.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));

		LoginInfo result = logDao.insert(log);

	

	
		//파일 갯수 위한 메소드 
		
		return result;
		
	}
	
	@Override
	public int logOutUpdate(String sessionId ,Timestamp  logoutTime) {
		
		

		int num = logDao.logOutUpdate(sessionId, logoutTime);

	
		//파일 갯수 위한 메소드 
		
		return num;
		
	}
	
	
	 
	

	@Override
	public int pageSave(LogInfo logInfo) {
		
		logInfo.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		logInfo.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));

		int num = logDao.pageInsert(logInfo);

		if (logger.isDebugEnabled()) {
			logger.debug("added new log!!. New logCode ID : "+ num);
		}

	
		//파일 갯수 위한 메소드 
		
		return num;
		
	}
	
	

	@Override
	public LoginInfo update(LoginInfo log) {
		
		
		LoginInfo getLog = this.getById(log.getBi_sn());
		log.setBi_unity_cust_id(getLog.getBi_unity_cust_id());
		log.setBi_reg_dt(getLog.getBi_reg_dt());
		log.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
	
		
		LoginInfo result = logDao.update(log);
		if (logger.isDebugEnabled()) {
			logger.debug("updated log!!. Update Log ID : "+ result.getBi_sn());
		}
		
		return result;
		
	}
	
	@Override
	public int remove(int bi_sn) {
		
	
		return logDao.delete(bi_sn);	
		
	}
	
	

	@Override
	public LoginInfo findList(int bi_sn) {
	
		
		LoginInfo log = logDao.findList(bi_sn);
		
		return log;
		
	}



	public List getHistoryView(String bi_unity_cust_id, String bi_menu_type_yn){
		
		return logDao.getHistoryView(bi_unity_cust_id, bi_menu_type_yn);
	}
	
	
	 public LogFail logFailSave(LogFail log){
		 
		 
		log.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss")); 
		log.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		
		 return logDao.logFailSave(log);
	 }
}
