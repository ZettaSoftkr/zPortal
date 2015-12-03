package com.zetta.qlikview.service;

import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.qlikview.model.QlikView;
import com.zetta.qlikview.model.QlikViewLoginInfo;

/**
 * @시스템 통합정보구축
 * @파일명 QlikViewFileService
 * @설명 QlikView가 저장하고 있는 QVW파일의 정보를 읽거나 저장하는 역할 수행 interface
 * @작성자 김창영 
 * @작성일 2014/02/11
 * @기타사항 없음.
 */

public interface QlikViewService {
	
	
	public QlikView getById(int bi_bbs_sn);
  	
 	public QlikView save(QlikView qlikview);

    public QlikView modify(QlikView qlikview);

    public int remove(int bi_sn);

    public QlikView findList(int bi_bbs_sn);
    
    public List<QlikView> findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent);
    
    public int getTotalCount(int searchTitle, String searchContent);    
    
    public int updateHitCount(int bi_bbs_sn) throws DataAccessException;       
	
	public List getFileList(String userID);

	public String getRequestUrl(String object, String objectType);

	public String getRequestHTML(String requestID);		
	 
	public String getValues(String qvxFileName)  throws IOException;
	
	public QlikView qlikViewUser(String bi_unity_cust_id);
	 
	public List<QlikView> qlikViewLoginCheck(String userId);
	
	public int  qlikViewLoginDelete(String bi_qlikview_user_id);
	
	public int  qlikviewMyLoginInfoDelete(String bi_unity_cust_id, String bi_qlikview_user_id);
	
	public QlikViewLoginInfo  qlikViewLoginSave(QlikViewLoginInfo qlikViewLoginInfo);
	
	public QlikViewLoginInfo  qlikViewLoginEdit(QlikViewLoginInfo qlikViewLoginInfo);
	
	public List<QlikView> qlikViewMaxUser();
	
	public QlikView qlikViewAddUser(QlikView qlikview);
	
	public QlikViewLoginInfo qvLoginInfo(String bi_session_id);
	
	public QlikView qvSessionLogin(String bi_unity_cust_id);

}
