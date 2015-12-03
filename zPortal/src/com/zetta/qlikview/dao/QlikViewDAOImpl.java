package com.zetta.qlikview.dao;

// Generated 2010. 5. 24 ���� 7:01:23 by Hibernate Tools 3.2.4.GA

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.qlikview.model.QlikView;
import com.zetta.qlikview.model.QlikViewLoginInfo;



@Repository
@Transactional
public class QlikViewDAOImpl implements QlikViewDAO {
	

	private static Logger logger = Logger.getLogger(QlikViewDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@SuppressWarnings("unchecked")
	public QlikView getById(int bi_sn)  throws DataAccessException 
	{
		return (QlikView) sessionFactory.getCurrentSession().get(QlikView.class, bi_sn);
	}

	
	@SuppressWarnings("unchecked")
	public QlikView insert(QlikView qlikview) throws DataAccessException {
		sessionFactory.getCurrentSession().save(qlikview);
		return qlikview;
	}
	
	

	@SuppressWarnings("unchecked")
	public QlikView update(QlikView qlikview) throws DataAccessException {
		
		sessionFactory.getCurrentSession().merge(qlikview);

		return qlikview;
	}
	@SuppressWarnings("unchecked")
	public int delete(int bi_sn) throws DataAccessException {
		
		sessionFactory.getCurrentSession().delete(getById(bi_sn));

		return 1;
	}
	
	
	@SuppressWarnings("unchecked")
	public QlikView findList(int bi_sn) throws DataAccessException {
		
		QlikView qlikview = (QlikView)sessionFactory.getCurrentSession().get(QlikView.class,bi_sn);
		
		if (qlikview == null) {
			
			throw new ObjectRetrievalFailureException(QlikView.class, bi_sn);
		
		}
		
		return qlikview;
		
	}
	
	public int updateHitCount(int bi_sn) throws DataAccessException {
		QlikView qlikview = (QlikView) sessionFactory.getCurrentSession().get(QlikView.class, new Integer(bi_sn));		
		sessionFactory.getCurrentSession().update(qlikview);

		return 1;
	}
	

	@SuppressWarnings("unchecked")
	public List<QlikView> findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent)  throws DataAccessException {
		logger.debug("findQlikViewList ");
		Query query = null;
		
		if(searchTitle == 1){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From QlikView qlikview where qlikview.bi_sj like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			
			
		}else if(searchTitle == 2){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From QlikView qlikview where qlikview.bi_cn like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			
		}else if(searchTitle == 3){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From QlikView qlikview where qlikview.bi_sj like ? or qlikview.bi_cn like ?");
			query.setParameter(0, "%"+searchContent+"%");
			query.setParameter(1, "%"+searchContent+"%");
			
		}else{
			
			query  = sessionFactory.getCurrentSession().createQuery(" From QlikView qlikview order by qlikview.bi_sn desc");
		
			
		}
		
		
		//int start = (currentPage) * countPerPage;
		query.setFirstResult(currentPage);
		query.setMaxResults(countPerPage);
		
		logger.info("query.list" + query.list().toString());
		
		return query.list();
		
	}

	@SuppressWarnings("unchecked")
	public int getTotalNo(int searchTitle, String searchContent)  throws DataAccessException {
		
		Query query = null;
		
		if(searchTitle == 1){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From QlikView qlikview where qlikview.bi_titl like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			
			
		}else if(searchTitle == 2){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From QlikView qlikview where qlikview.bi_cn like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			
		}else if(searchTitle == 3){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From QlikView qlikview where qlikview.bi_titl like ? or qlikview.bi_cn like ?");
			query.setParameter(0, "%"+searchContent+"%");
			query.setParameter(1, "%"+searchContent+"%");
			
		}else{
			
			query  = sessionFactory.getCurrentSession().createQuery(" From QlikView qlikview order by qlikview.bi_sn desc");
			
		}		
		
		return query.list().size();
		
	}

	
	@SuppressWarnings("unchecked")
	public QlikView qlikViewUser(String bi_unity_cust_id)  throws DataAccessException {
		logger.debug("findQlikViewList ");
		Query query = null;
			
		query  = sessionFactory.getCurrentSession().createQuery(" From QlikView qlikview where qlikview.bi_unity_cust_id = ? ");		
	     query.setParameter(0, bi_unity_cust_id);	     
	     QlikView qv = (QlikView) query.list().get(0);		
		return qv;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<QlikView> qlikViewMaxUser()  throws DataAccessException {
		logger.debug("findQlikViewList ");
		Query query = null;
			
		query  = sessionFactory.getCurrentSession().createQuery(" From QlikView qlikview where qlikview.bi_unity_cust_id is null order by qlikview.bi_sn asc ");
		List<QlikView> qv = query.list();		
		return qv;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public QlikView qvSessionLogin(String bi_unity_cust_id)  throws DataAccessException {
		logger.info("qvSessionLogin"+ bi_unity_cust_id);
		
		Query query = null;
		QlikView qv =  null;	
		 query  = sessionFactory.getCurrentSession().createQuery(" From QlikView qv where qv.bi_unity_cust_id = ? ");		
	     query.setParameter(0, bi_unity_cust_id);
	     if(query.list().size() > 0){
	     
	    	 qv = (QlikView) query.list().get(0);	
	    	 
	     }else{
	    	 
	    	 qv = null;
	    	 
	     }
	     
	     logger.info("query.list()" + query.list().toString());
	     
		return qv;
		
	}
	
	@SuppressWarnings("unchecked")
	public QlikViewLoginInfo qvLoginInfo(String bi_session_id)  throws DataAccessException {
		logger.debug("findQlikViewList ");
		Query query = null;
			
		 query  = sessionFactory.getCurrentSession().createQuery(" From QlikViewLoginInfo qv where qv.bi_session_id = ? ");		
	     query.setParameter(0, bi_session_id);	 
	     

	     QlikViewLoginInfo qv = (QlikViewLoginInfo) query.list().get(0);	
	     
		return qv;
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<QlikView> qlikViewLoginCheck(String userId)  throws DataAccessException {
		logger.debug("findQlikViewList ");
		   Query query = null;
			
			query  = sessionFactory.getCurrentSession().createQuery(" From QlikView qvUser where qvUser.bi_unity_cust_id = ?");
			query.setParameter(0, userId);
		
		logger.info("query.list" + query.list().toString());
		
		return query.list();
		
	}
	
	@Value("#{sqlquery['qlikview.qlikViewLoginDelete']}") private String query1;
	@SuppressWarnings("unchecked")
	public int  qlikViewLoginDelete(String bi_qlikview_user_id)  throws DataAccessException {
		logger.debug("findQlikViewList ");
		   Query query = null;
			
			query  = sessionFactory.getCurrentSession().createSQLQuery(query1);
			query.setParameter(0, bi_qlikview_user_id);
		    query.executeUpdate();
		
		return 1;
		
	}
	@Value("#{sqlquery['qlikview.qlikviewMyLoginInfoDelete']}") private String query2;
	@SuppressWarnings("unchecked")
	 public int  qlikviewMyLoginInfoDelete(String bi_unity_cust_id, String bi_qlikview_user_id) throws DataAccessException {
			logger.debug("findQlikViewList ");
			   Query query = null;
				
				query  = sessionFactory.getCurrentSession().createSQLQuery(query2);
				query.setParameter(0, bi_unity_cust_id);
				query.setParameter(1, bi_qlikview_user_id);
			    query.executeUpdate();
			
			return 1;
			
		}
	//클릭뷰 로그인 현황 정보 
	public QlikViewLoginInfo  qlikViewLoginSave(QlikViewLoginInfo qlikViewLoginInfo){
		
		
		sessionFactory.getCurrentSession().save(qlikViewLoginInfo);
		return qlikViewLoginInfo;
	
	}
	
	public QlikViewLoginInfo  qlikViewLoginEdit(QlikViewLoginInfo qlikViewLoginInfo){
		
		sessionFactory.getCurrentSession().merge(qlikViewLoginInfo);
		return qlikViewLoginInfo;
		
	}
	
	
	//클릭뷰 로그인 현황 정보 
	public QlikViewLoginInfo  qlikViewLoginMerge(QlikViewLoginInfo qlikViewLoginInfo){
		
		
		sessionFactory.getCurrentSession().merge(qlikViewLoginInfo);
		return qlikViewLoginInfo;
	
	}
	
	 public QlikView qlikViewAddUser(QlikView qlikview)  throws DataAccessException{
		 
		    sessionFactory.getCurrentSession().merge(qlikview);

		return qlikview;
		 
	 }

	
}
