package com.zetta.security.service;

import java.util.List;

import com.zetta.security.model.Security;



public interface SecurityService {	
	
	 public Security getUser(String login);  
	 
	 
	  public List getGroupRoleList(String bi_dept_id); 
	  
	  public Security save(Security save); 
	  
	  public int batchInsert(List<Security> list);
	  
	  public int deleteAll();
	  
	  public int setInitQlikviewUser();
	  

	    
}
