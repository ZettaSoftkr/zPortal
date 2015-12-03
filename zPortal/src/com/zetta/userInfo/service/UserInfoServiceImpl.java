package com.zetta.userInfo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.common.DateTimeUtil;
import com.zetta.qlikview.utils.QVXtoCSVconversion;
import com.zetta.userInfo.dao.UserInfoDAO;
import com.zetta.userInfo.model.UserInfo;


@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService  {
	
	public Logger logger = Logger.getLogger(getClass());

	
	@Autowired
	private UserInfoDAO userInfoDao;	

	
	@Override
	public UserInfo getById(String bi_unity_cust_id){
		
		return userInfoDao.getById(bi_unity_cust_id);
		
	}

	@Override
	public UserInfo save(UserInfo userInfo) {
		
		userInfo.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		userInfo.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		UserInfo result = userInfoDao.insert(userInfo);
		//파일 갯수 위한 메소드 
		
		return result;
		
	}
	
	
	public int batchInsert(List<UserInfo> list) {		
		//userInfo.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		//userInfo.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		int num = userInfoDao.batchInsert(list);
		//파일 갯수 위한 메소드 
		
		return num;
		
	}
	
	

	@Override
	public UserInfo update(UserInfo userInfo) {
		
		
		logger.info("userInfo.getBi_unity_cust_id():" + userInfo.getBi_unity_cust_id());
		UserInfo getUserInfo = this.getById(userInfo.getBi_unity_cust_id());	
		logger.info("userInfo.getBi_unity_cust_id():" + getUserInfo.getBi_reg_dt());
		
		userInfo.setBi_reg_dt(getUserInfo.getBi_reg_dt());		
		userInfo.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		
		logger.info("userInfo.getBi_unity_cust_id():" + userInfo.getBi_unity_cust_id());
		
		UserInfo result = userInfoDao.update(userInfo);
	
		
		
		return result;
		
	}
	
	@Override
	public int remove(String bi_unity_cust_id) {
		
	
		return userInfoDao.delete(bi_unity_cust_id);		
	}
	
	

	@Override
	public UserInfo findList(String bi_unity_cust_id) {
		
		UserInfo userInfo = userInfoDao.findList(bi_unity_cust_id);
		
		return userInfo;
		
	}

	@Override
	public UserInfo findWithView(String bi_unity_cust_id) {
		
	
		return findList(bi_unity_cust_id);
		
	}

	

	@Override
	public List findAllList(int startRow, int endRow, int searchTitle, String searchContent) {
		
		return userInfoDao.findAllList(startRow, endRow, searchTitle, searchContent);
	}
	


	@Override
	public int getTotalCount(int searchTitle, String searchContent) {
		return userInfoDao.getTotalNo(searchTitle, searchContent);
	}
	
	
	
		 
	public List<UserInfo> findDeptUserList(String bi_dept_id){
		 return userInfoDao.findDeptUserList(bi_dept_id);
		
	} 
	
	
	public int deleteAll(){
		
		 return userInfoDao.deleteAll();
	}
	
	
	public List<UserInfo> findUserList(String bi_user_nm){
		
		return userInfoDao.findUserList(bi_user_nm);
		
	}
	
	
	
	@Value("#{qvconf['qlikview.QVDOUT']}")
	private String outputPath;
	
	public String addUserList(String qvdName) throws IOException{
		
		
		int delNum = userInfoDao.deleteUser("Y");
		if(delNum == 1){
			int key1 = 0;
			int key2 = 0;
			int key3 = 0;
			int key4 = 0;
			int key5 = 0;
			List<UserInfo> list = new ArrayList<UserInfo>();
			List optionValue = new QVXtoCSVconversion().getValues(qvdName, outputPath); 
			int optionSize = optionValue.size() / 5;
			for (int i = 0; i < optionSize; i++) {
	
				key1 = i * 5;
				key2 = (i * 5) + 1;
				key3 = (i * 5) + 2;
				key4 = (i * 5) + 3;
				key5 = (i * 5) + 4;		
				logger.info("optionValue.get(1):: " + optionValue.get(key1));
				logger.info("optionValue.get(2):: " + optionValue.get(key2));
				logger.info("optionValue.get(3):: " + optionValue.get(key3));
				logger.info("optionValue.get(4):: " + optionValue.get(key4));
				logger.info("optionValue.get(5):: " + optionValue.get(key5));
							
				UserInfo userInfo = new UserInfo();		
				
				
				userInfo.setBi_unity_cust_id(String.valueOf(optionValue.get(key1)));
				userInfo.setBi_user_nm(String.valueOf(optionValue.get(key2)));
				userInfo.setBi_dept_id(String.valueOf(optionValue.get(key3)));
				userInfo.setBi_email_addr(String.valueOf(optionValue.get(key4)));
				userInfo.setBi_mpno(String.valueOf(optionValue.get(key5)));
				userInfo.setBi_perm_yn("Y");
				userInfo.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
				userInfo.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
	
				list.add(userInfo);
	
				
			}
			
		   int saveNum = 	userInfoDao.batchInsert(list);
		   
		   if(saveNum == 1){
			   
			   return "sucess";
			   
		   }else{
			   
			   return "fail";
			  
		   }
		   
		}else{
			
			return "fail";
		}
		
		
	}
	
	public int deleteUser(String bi_perm_yn){
		
		return userInfoDao.deleteUser(bi_perm_yn);
	}
	
	public int deleteDeptUser(String deptCd){	
		
		return userInfoDao.deleteDeptUser(deptCd);
		
	}
	
	/*
	 * 관리실 > 사용자 목록 > 사용자관리  : 전체 조직 -사용자 보기
	 * @param :
	 * @return : list(부서ID,부서명, 사용자ID, 사용자명 )
	 * @return : json data 
	 * 조직 -사용자 트리 표현 
	 */

	
	public List getDeptUserList(){
		
		return userInfoDao.getDeptUserList();
	}
	
}
