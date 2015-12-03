package com.zetta.security.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;

import com.zetta.group.model.Group;
import com.zetta.security.model.Security;


public interface SecurityDAO {
		
	   
	
      	public Security getUser(String login) throws DataAccessException;
      	
      	public Security insert(Security save) throws DataAccessException;
      	
        public List getGroupRoleList(String bi_dept_id)throws DataAccessException; 
        
    	public Group getById(String bi_group_id) throws DataAccessException;
    	
    	public int batchInsert(List<Security> userInfo)  throws HibernateException;
    	
    	public int deleteAll() throws DataAccessException;
    	
    	public int setInitQlikviewUser() throws DataAccessException;
}