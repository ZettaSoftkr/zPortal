package com.zetta.dept.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.dept.model.Dept;
import com.zetta.userInfo.model.UserInfo;




public interface DeptDAO {

	
      	public Dept getById(String bi_dept_id) throws DataAccessException;
      	
	 	public Dept insert(Dept dept) throws DataAccessException;

	    public Dept update(Dept dept) throws DataAccessException;

	    public int delete(String bi_dept_id) throws DataAccessException;

	    public Dept findList(String bi_dept_id) throws DataAccessException;
	    
	    public List findAllList(int startRow, int endRow, int searchTitle, String searchContent)  throws DataAccessException;
	    
	    public int getTotalNo(int searchTitle, String searchContent) throws DataAccessException;
	    
	    public List<Dept> getDeptList()  throws DataAccessException;
	    
	    public Dept getDeptCode(String bi_deptnm) throws DataAccessException;

	    public int deleteAll() throws DataAccessException;
	    
	    public int deleteDept(String bi_perm_yn) throws DataAccessException;
	    
	    public int batchInsert(List<Dept> dept)  throws DataAccessException;//HibernateException;
}