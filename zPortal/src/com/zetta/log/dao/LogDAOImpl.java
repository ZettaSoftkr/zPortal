package com.zetta.log.dao;

// Generated 2010. 5. 24 ���� 7:01:23 by Hibernate Tools 3.2.4.GA

import java.sql.Timestamp;
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

import com.zetta.log.model.LogFail;
import com.zetta.log.model.LogInfo;
import com.zetta.log.model.LoginInfo;

@Repository
@Transactional
public class LogDAOImpl implements LogDAO {

	private static Logger logger = Logger.getLogger(LogDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public LoginInfo getById(int bi_sn) throws DataAccessException {
		return (LoginInfo) sessionFactory.getCurrentSession().get(LoginInfo.class, bi_sn);
	}

	@SuppressWarnings("unchecked")
	public LoginInfo insert(LoginInfo log) throws DataAccessException {
		sessionFactory.getCurrentSession().save(log);
		return log;
	}
	
	
	public LogFail logFailSave(LogFail log)throws DataAccessException {
		
		sessionFactory.getCurrentSession().save(log);
		return log;
		
	}

	@SuppressWarnings("unchecked")
	public int pageInsert(LogInfo logInfo) throws DataAccessException {
		sessionFactory.getCurrentSession().save(logInfo);
		return 1;

	}
	@Value("#{sqlquery['log.logOutUpdate']}") private String query1;
	@SuppressWarnings("unchecked")
	public int logOutUpdate(String sessionId, Timestamp logoutTime) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query1);
		query.setParameter(0, logoutTime);
		query.setParameter(1, sessionId);

		query.executeUpdate();

		logger.info(query.toString());

		return 1;
	}

	@SuppressWarnings("unchecked")
	public LoginInfo update(LoginInfo log) throws DataAccessException {

		sessionFactory.getCurrentSession().merge(log);

		return log;
	}

	@SuppressWarnings("unchecked")
	public int delete(int bi_sn) throws DataAccessException {

		sessionFactory.getCurrentSession().delete(getById(bi_sn));

		return 1;
	}

	@SuppressWarnings("unchecked")
	public LoginInfo findList(int bi_sn) throws DataAccessException {

		LoginInfo log = (LoginInfo) sessionFactory.getCurrentSession().get(LoginInfo.class, bi_sn);

		if (log == null) {

			throw new ObjectRetrievalFailureException(LoginInfo.class, bi_sn);

		}

		return log;

	}


	
	@Value("#{sqlquery['log.getHistoryView']}") private String query2;
	public List getHistoryView(String bi_unity_cust_id, String bi_menu_type_yn) throws DataAccessException {
		
		Query query = null;
		

		
		query = sessionFactory.getCurrentSession().createSQLQuery(query2);
		
		query.setParameter(0, bi_unity_cust_id);
		query.setParameter(1, bi_menu_type_yn);	
		return query.list();
		
	}


}
