package com.zetta.userInfo.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.userInfo.model.UserInfo;


public interface UserInfoDAO {

	
      	public UserInfo getById(String bi_unity_cust_id) throws DataAccessException;
      	
	 	public UserInfo insert(UserInfo userInfo) throws DataAccessException;
	 	
		public int batchInsert(List<UserInfo> userInfo)  throws DataAccessException;//HibernateException;

	    public UserInfo update(UserInfo userInfo) throws DataAccessException;

	    public int delete(String bi_unity_cust_id) throws DataAccessException;

	    public UserInfo findList(String bi_unity_cust_id) throws DataAccessException;
	    
	    public List findAllList(int startRow, int endRow, int searchTitle, String searchContent)  throws DataAccessException;
	    
	    public int getTotalNo(int searchTitle, String searchContent) throws DataAccessException;
	    
	    public List<UserInfo> findDeptUserList(String bi_dept_id) throws DataAccessException;	    
	  
	    public int deleteAll() throws DataAccessException;
	    
	    public List<UserInfo> findUserList(String bi_user_nm)  throws DataAccessException;
	    
	    public int deleteUser(String bi_perm_yn) throws DataAccessException;	    
	    /* 관련부서  사용자 상제 */
	    public int deleteDeptUser(String deptId) throws DataAccessException;
	    
		/*
		 * 관리실 > 사용자 목록 > 사용자관리  : 전체 조직 -사용자 보기
		 * @param :
		 * @return : list(부서ID,부서명, 사용자ID, 사용자명 )
		 * @return : json data 
		 * 조직 -사용자 트리 표현 
		 */

	    public List getDeptUserList() throws DataAccessException;
}