package com.zetta.userInfo.dao;

// Generated 2010. 5. 24 ���� 7:01:23 by Hibernate Tools 3.2.4.GA

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import com.zetta.userInfo.model.UserInfo;

/**
 * Home object for domain model class Board.
 * 
 * @see com.board.model.Board
 * @author Hibernate Tools
 */

@Repository
@Transactional
public class UserInfoDAOImpl implements UserInfoDAO {

	private static Logger logger = Logger.getLogger(UserInfoDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	

	@SuppressWarnings("unchecked")
	public UserInfo getById(String bi_unity_cust_id) throws DataAccessException {
		return (UserInfo) sessionFactory.getCurrentSession().get(UserInfo.class, bi_unity_cust_id);
	}

	@SuppressWarnings("unchecked")
	public UserInfo insert(UserInfo userInfo) throws DataAccessException {
		sessionFactory.getCurrentSession().save(userInfo);

		return userInfo;
	}

	@SuppressWarnings("unchecked")
	public int batchInsert(List<UserInfo> list) throws DataAccessException { //HibernateException

		 Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		logger.info("전체개수:" + list.size());
		

			for (int i = 0; i < list.size(); i++) {
				
				logger.info("list.get(i):"+ list.get(i).getBi_user_nm());

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

	@SuppressWarnings("unchecked")
	public UserInfo update(UserInfo userInfo) throws DataAccessException {

		sessionFactory.getCurrentSession().merge(userInfo);

		return userInfo;
	}

	@SuppressWarnings("unchecked")
	public int delete(String bi_unity_cust_id) throws DataAccessException {
		
		
		UserInfo userInfo = (UserInfo) sessionFactory.getCurrentSession().get(UserInfo.class, bi_unity_cust_id);
		sessionFactory.getCurrentSession().delete(userInfo);
		

		return 1;
	}

	@SuppressWarnings("unchecked")
	public UserInfo findList(String bi_unity_cust_id) throws DataAccessException {

		UserInfo userInfo = (UserInfo) sessionFactory.getCurrentSession().get(UserInfo.class, bi_unity_cust_id);

		if (userInfo == null) {

			throw new ObjectRetrievalFailureException(UserInfo.class, bi_unity_cust_id);

		}

		return userInfo;

	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> findAllList(int startRow, int endRow, int searchTitle, String searchContent) throws DataAccessException {
		logger.debug("findBoardList ");
		logger.debug("searchTitle " + searchTitle);
		logger.debug("searchContent " + searchContent);
		Query query = null;

		if (searchTitle == 1) {

			query = sessionFactory.getCurrentSession().createQuery(" From UserInfo userInfo where userInfo.bi_unity_cust_id like ? ");
			query.setParameter(0, "%"+searchContent+"%");

		} else if (searchTitle == 2) {

			query = sessionFactory.getCurrentSession().createQuery(" From UserInfo userInfo where userInfo.bi_user_nm like ? ");
			query.setParameter(0, "%"+searchContent+"%");

		} else if (searchTitle == 3) {

			query = sessionFactory.getCurrentSession().createQuery(" From UserInfo userInfo where userInfo.bi_unity_cust_id like ? or userInfo.bi_user_nm like ?");
			query.setParameter(0, "%"+searchContent+"%");
			query.setParameter(1, "%"+searchContent+"%");

		} else {

			query = sessionFactory.getCurrentSession().createQuery(" From UserInfo userInfo order by userInfo.bi_dept_id asc, userInfo.bi_unity_cust_id desc");

		}

		// int start = (currentPage) * countPerPage;
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);

		logger.info("query.list" + query.list().toString());

		return query.list();

	}

	@SuppressWarnings("unchecked")
	public int getTotalNo(int searchTitle, String searchContent) throws DataAccessException {

		Query query = null;

		if (searchTitle == 1) {

			query = sessionFactory.getCurrentSession().createQuery(" From UserInfo userInfo where userInfo.bi_unity_cust_id like ? ");
			query.setParameter(0, "%"+searchContent+"%");

		} else if (searchTitle == 2) {

			query = sessionFactory.getCurrentSession().createQuery(" From UserInfo userInfo where userInfo.bi_user_nm like ? ");
			query.setParameter(0, "%"+searchContent+"%");

		} else if (searchTitle == 3) {

			query = sessionFactory.getCurrentSession().createQuery(" From UserInfo userInfo where userInfo.bi_unity_cust_id like ? or userInfo.bi_user_nm like ?");
			query.setParameter(0, "%"+searchContent+"%");
			query.setParameter(1, "%"+searchContent+"%");

		} else {

			query = sessionFactory.getCurrentSession().createQuery(" From UserInfo userInfo");

		}
		logger.info("query.list():" + query.list().toString());
		return query.list().size();

	}

	

	@SuppressWarnings("unchecked")
	public List<UserInfo> findDeptUserList(String bi_dept_id) throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From UserInfo userInfo where userInfo.bi_dept_id = ? ");
		query.setParameter(0, bi_dept_id);
		logger.info("query.list" + query.list().toString());

		return query.list();

	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<UserInfo> findUserList(String bi_user_nm) throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From UserInfo userInfo where userInfo.bi_user_nm like ? ");
		query.setParameter(0, "%"+ bi_user_nm+"%");
		logger.info("query.list" + query.list().toString());

		return query.list();

	}
	@Value("#{sqlquery['userInfo.deleteAll']}") private String query1; 
	@SuppressWarnings("unchecked")
	public int deleteAll() throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query1);
		query.executeUpdate();
		

		return 1;

	} 
	
	
	@Value("#{sqlquery['userInfo.deleteUser']}") private String query2; 
	@SuppressWarnings("unchecked")
	public int deleteUser(String bi_perm_yn) throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query2);
		query.setParameter(0, bi_perm_yn);
		query.executeUpdate();
		

		return 1;

	} 
	
	
	
	@Value("#{sqlquery['userInfo.deleteDeptUser']}") private String query3; 
	@SuppressWarnings("unchecked")
	public int deleteDeptUser(String deptId) throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query3);
		query.setParameter(0, deptId);
		query.executeUpdate();
		

		return 1;

	} 
	
/*
 * 관리실 > 사용자 목록 > 사용자관리  : 전체 조직 -사용자 보기
 * @param :
 * @return : list(부서ID,부서명, 사용자ID, 사용자명 )
 * @return : json data 
 * 조직 -사용자 트리 표현 
 */
	@Value("#{sqlquery['userInfo.getDeptUserList']}") private String query4; 
	@SuppressWarnings("unchecked")
	public List getDeptUserList() throws DataAccessException {
		logger.debug("getDeptUserList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query4);
		
		return query.list();

	}
}
