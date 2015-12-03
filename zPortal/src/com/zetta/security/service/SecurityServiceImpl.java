package com.zetta.security.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.security.dao.SecurityDAO;
import com.zetta.security.model.Security;


@Service
@Transactional
public class SecurityServiceImpl implements SecurityService  {
	
	public Logger logger = Logger.getLogger(getClass());

	
	@Autowired
	private SecurityDAO securityDao;	

	
	@Override
	public Security getUser(String login) {  
	       return securityDao.getUser(login);  
	}  
	
	
	
	public List getGroupRoleList(String bi_dept_id){
		
		 return securityDao.getGroupRoleList(bi_dept_id);
		
	}
	
	public Security save(Security save){
		
		 return securityDao.insert(save);
		
	}
	
	public int batchInsert(List<Security> list) {
		
		//userInfo.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		//userInfo.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		int num = securityDao.batchInsert(list);
		//파일 갯수 위한 메소드 
		
		return num;
		
	}
	
	
	public int deleteAll(){
		
		return securityDao.deleteAll();
	}
	
	public int setInitQlikviewUser(){
		
		return securityDao.setInitQlikviewUser();
	}

	

}
