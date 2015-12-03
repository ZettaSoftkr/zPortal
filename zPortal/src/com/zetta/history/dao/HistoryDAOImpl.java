package com.zetta.history.dao;

// Generated 2010. 5. 24 ���� 7:01:23 by Hibernate Tools 3.2.4.GA

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.history.model.Advenced;
import com.zetta.history.model.Backup;
import com.zetta.history.model.History;


@Repository
@Transactional
public class HistoryDAOImpl implements HistoryDAO {
	

	private static Logger logger = Logger.getLogger(HistoryDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@SuppressWarnings("unchecked")
	public History getById(int getNo)  throws DataAccessException 
	{
		return (History) sessionFactory.getCurrentSession().get(History.class, getNo);
	}


	@SuppressWarnings("unchecked")
	public List<History> getDataList(int currentPage, int countPerPage, String bi_portal_menu_id)  throws DataAccessException {
		logger.debug("getDataList ");
		
		Query query = null;
		query  = sessionFactory.getCurrentSession().createQuery("From History h WHERE h.bi_portal_menu_id = ?");
		query.setParameter(0, bi_portal_menu_id);
		
		//int start = (currentPage) * countPerPage;
		query.setFirstResult(currentPage);
		query.setMaxResults(countPerPage);
		
		logger.info("query.list" + query.list().toString());
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public int getDataCnt(String bi_portal_menu_id)  throws DataAccessException {
		
		logger.info("bi_portal_menu_id::" + bi_portal_menu_id);
		
		Query query = null;
			
		query  = sessionFactory.getCurrentSession().createQuery(" FROM History h WHERE h.bi_portal_menu_id = ?");
		logger.info("historyList::" + query.toString());
		
		
		
		query.setParameter(0, bi_portal_menu_id);	
			
		
		return query.list().size();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Advenced> getAdvencedDataList(int currentPage, int countPerPage, String bi_portal_menu_id)  throws DataAccessException {
		logger.debug("getDataList ");
		
		Query query = null;
		query  = sessionFactory.getCurrentSession().createQuery("From Advenced a WHERE a.bi_portal_menu_id = ?");
		query.setParameter(0, bi_portal_menu_id);
		
		//int start = (currentPage) * countPerPage;
		query.setFirstResult(currentPage);
		query.setMaxResults(countPerPage);
		
		logger.info("query.list" + query.list().toString());
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public int getAdvencedDataCnt(String bi_portal_menu_id)  throws DataAccessException {
		
		logger.info("bi_portal_menu_id::" + bi_portal_menu_id);
		
		Query query = null;
			
		query  = sessionFactory.getCurrentSession().createQuery(" FROM Advenced a WHERE a.bi_portal_menu_id = ?");
		logger.info("historyList::" + query.toString());
		
		
		
		query.setParameter(0, bi_portal_menu_id);	
			
		
		return query.list().size();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Backup> getBackupDataList(int currentPage, int countPerPage, String bi_portal_menu_id)  throws DataAccessException {
		logger.debug("getDataList ");
		
		Query query = null;
		query  = sessionFactory.getCurrentSession().createQuery("From Backup b WHERE b.bi_portal_menu_id = ?");
		query.setParameter(0, bi_portal_menu_id);
		
		//int start = (currentPage) * countPerPage;
		query.setFirstResult(currentPage);
		query.setMaxResults(countPerPage);
		
		logger.info("query.list" + query.list().toString());
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public int getBackupDataCnt(String bi_portal_menu_id)  throws DataAccessException {
		
		logger.info("bi_portal_menu_id::" + bi_portal_menu_id);
		
		Query query = null;
			
		query  = sessionFactory.getCurrentSession().createQuery(" FROM Backup b WHERE b.bi_portal_menu_id = ?");
		logger.info("historyList::" + query.toString());
		
		
		
		query.setParameter(0, bi_portal_menu_id);	
			
		
		return query.list().size();
	}



	
}
