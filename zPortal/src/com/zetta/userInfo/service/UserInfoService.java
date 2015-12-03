package com.zetta.userInfo.service;

import java.io.IOException;
import java.util.List;

import com.zetta.userInfo.model.UserInfo;



public interface UserInfoService {	
	
	
	   public UserInfo getById(String bi_unity_cust_id) ;

	   public UserInfo save(UserInfo userInfo) ;
	    
	   public UserInfo update(UserInfo userInfo);

	   public int remove(String bi_unity_cust_id);

	   public UserInfo findList(String bi_unity_cust_id);
	    
	   public UserInfo findWithView(String bi_unity_cust_id);
	    
	   public List findAllList(int currentPage, int countPerPage,int searchTitle, String searchContent);

	   public int getTotalCount(int searchTitle, String searchContent);
	    
	   public List<UserInfo> findDeptUserList(String bi_dept_id);
	   
	   public int batchInsert(List<UserInfo> list);  
	   
	   public int deleteAll();
	   
	   public List<UserInfo> findUserList(String bi_user_nm);
	   
	   public String addUserList(String qvdName) throws IOException;
	   
	   public int deleteUser(String bi_perm_yn);
	   
	   public int deleteDeptUser(String deptId);
	   
		/*
		 * 관리실 > 사용자 목록 > 사용자관리  : 전체 조직 -사용자 보기
		 * @param :
		 * @return : list(부서ID,부서명, 사용자ID, 사용자명 )
		 * @return : json data 
		 * 조직 -사용자 트리 표현 
		 */

	   public List getDeptUserList();

}
