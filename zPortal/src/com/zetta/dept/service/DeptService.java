package com.zetta.dept.service;

import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.dept.model.Dept;




public interface DeptService {	
	
	
	   public Dept getById(String bi_dept_id) ;

	   public Dept save(Dept dept) ;
	    
	   public Dept update(Dept dept);

	   public int remove(String bi_dept_id);

	   public Dept findList(String bi_dept_id);
	    
	   public Dept findWithView(String bi_dept_id);
	    
	   public List findAllList(int currentPage, int countPerPage,int searchTitle, String searchContent);

	   public int getTotalCount(int searchTitle, String searchContent);
	   
	   public List<Dept> getDeptList();
	   
	   public Dept getDeptCode(String bi_deptnm);
	   
	   public int deleteAll();
	   
	   public String addDeptList(String qvdName) throws IOException;
	   
	   public int deleteDept(String bi_perm_yn);
	   
	   
		 
	}
