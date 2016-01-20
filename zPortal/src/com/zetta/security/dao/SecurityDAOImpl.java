package com.zetta.security.dao;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.security.model.Security;

/**
 * Home object for domain model class Security.
 * 
 * @see com.security.model.Security
 * @author Hibernate Tools
 */

@Repository
@Transactional
public class SecurityDAOImpl implements SecurityDAO {

	private static Logger logger = Logger.getLogger(SecurityDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;



	public Security getUser(String bi_unity_cust_id) {

		List<Security> userList = new ArrayList<Security>();
		Query query = sessionFactory.getCurrentSession().createQuery("from Security u where u.bi_unity_cust_id = ?");
		query.setParameter(0, bi_unity_cust_id);
		userList = query.list();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}
	@Value("#{sqlquery['security.getGroupRoleList']}") private String query1;
	public List getGroupRoleList(String userId) throws DataAccessException {

		List list = new ArrayList();
		Query query = null;
		query  = sessionFactory.getCurrentSession().createSQLQuery(query1); 

		//query = sessionFactory.getCurrentSession().createQuery("From DeptGroup dg where dg.bi_dept_id = ? ");
		query.setParameter(0, userId);
		query.setParameter(1, userId);
		
		
		list = query.list();
		return list;

	}
	

	
	@SuppressWarnings("unchecked")
	public Security insert(Security security) throws DataAccessException {
		sessionFactory.getCurrentSession().save(security);
		return security;
	}
	

	
	public int batchInsert(List<Security> list) throws HibernateException {
		
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		logger.info("전체개수:" + list.size());
		

			for (int i = 0; i < list.size(); i++) {
				
				

				session.save(list.get(i));
				
				if (i % 50 == 0) {

					session.flush();
					session.clear();

				}

			}
		tx.commit();
		session.close();
		

		return 1;
	}
	
	@Value("#{sqlquery['security.deleteAll']}") private String query2;
	@SuppressWarnings("unchecked")
	public int deleteAll() throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query2);
		query.executeUpdate();
		

		return 1;

	} 
	
	@Value("#{sqlquery['security.qlikviewUserInit']}") private String query3;
	@SuppressWarnings("unchecked")
	public int setInitQlikviewUser() throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query3);
		query.executeUpdate();
		

		return 1;

	} 

}
