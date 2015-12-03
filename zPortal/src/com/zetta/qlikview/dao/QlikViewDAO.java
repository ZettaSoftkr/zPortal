package com.zetta.qlikview.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.qlikview.model.QlikView;
import com.zetta.qlikview.model.QlikViewLoginInfo;


public interface QlikViewDAO {

	
      	public QlikView getById(int bi_bbs_sn) throws DataAccessException;
      	
	 	public QlikView insert(QlikView qlikview) throws DataAccessException;

	    public QlikView update(QlikView qlikview) throws DataAccessException;

	    public int delete(int bi_bbs_sn) throws DataAccessException;

	    public QlikView findList(int bi_bbs_sn) throws DataAccessException;
	    
	    public List findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent)  throws DataAccessException;
	    
	    public int getTotalNo(int searchTitle, String searchContent) throws DataAccessException;		   
	    
	    public int updateHitCount(int bi_bbs_sn) throws DataAccessException;
	    
	    public QlikView qlikViewUser(String userId) throws DataAccessException;
	    
	    public List<QlikView> qlikViewLoginCheck(String userId) throws DataAccessException;
	    
	    public int  qlikViewLoginDelete(String userId) throws DataAccessException;
	    
	    public int  qlikviewMyLoginInfoDelete(String bi_unity_cust_id, String bi_qlikview_user_id) throws DataAccessException;
	    
	    public QlikViewLoginInfo  qlikViewLoginSave(QlikViewLoginInfo qlikViewLoginInfo) throws DataAccessException;
	    
	    public QlikViewLoginInfo  qlikViewLoginEdit(QlikViewLoginInfo qlikViewLoginInfo) throws DataAccessException;
	    
	    public List<QlikView> qlikViewMaxUser()  throws DataAccessException;
	    
	    public QlikView qlikViewAddUser(QlikView qlikview)  throws DataAccessException;
	    
	    public QlikViewLoginInfo qvLoginInfo(String bi_session_id)  throws DataAccessException;
	    
	    public QlikView qvSessionLogin(String bi_unity_cust_id)  throws DataAccessException;
	    
}