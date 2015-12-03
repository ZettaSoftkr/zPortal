package com.zetta.dept.dao;

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

import com.zetta.dept.model.Dept;
import com.zetta.userInfo.model.UserInfo;


@Repository
@Transactional
public class DeptDAOImpl implements DeptDAO {

	private static Logger logger = Logger.getLogger(DeptDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public Dept getById(String bi_dept_id) throws DataAccessException {
		return (Dept) sessionFactory.getCurrentSession().get(Dept.class,bi_dept_id);
	}

	@SuppressWarnings("unchecked")
	public Dept insert(Dept dept) throws DataAccessException {
		sessionFactory.getCurrentSession().save(dept);
		return dept;
	}

	@SuppressWarnings("unchecked")
	public Dept update(Dept dept) throws DataAccessException {

		sessionFactory.getCurrentSession().merge(dept);

		return dept;
	}

	@SuppressWarnings("unchecked")
	public int delete(String bi_dept_id) throws DataAccessException {
		
		Dept dept = (Dept) sessionFactory.getCurrentSession().get(Dept.class,bi_dept_id);
		sessionFactory.getCurrentSession().delete(dept);

		return 1;
	}

	@SuppressWarnings("unchecked")
	public Dept findList(String bi_dept_id) throws DataAccessException {

		Dept dept = (Dept) sessionFactory.getCurrentSession().get(Dept.class,bi_dept_id);

		if (dept == null) {

			throw new ObjectRetrievalFailureException(Dept.class, bi_dept_id);

		}

		return dept;

	}
	
	@SuppressWarnings("unchecked")
	public Dept getDeptCode(String bi_deptnm) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery(" From Dept dept where dept.bi_deptnm = ? ");
		query.setParameter(0, bi_deptnm);
		Dept dept = (Dept) query.list().get(0);
		return dept;

	}

	@SuppressWarnings("unchecked")
	public List<Dept> findAllList(int startRow, int endRow, int searchTitle,
			String searchContent) throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		if (searchTitle == 1) {

			query = sessionFactory.getCurrentSession().createQuery(" From Dept dept where dept.bi_dept_id like ? ");
			query.setParameter(0, "%"+searchContent+"%");

		} else if (searchTitle == 2) {

			query = sessionFactory.getCurrentSession().createQuery(" From Dept dept where dept.bi_deptnm like ? ");
			query.setParameter(0, "%"+searchContent+"%");

		} else if (searchTitle == 3) {

			query = sessionFactory.getCurrentSession().createQuery(" From Dept dept where dept.bi_dept_id like ? or dept.bi_deptnm like ?");
			query.setParameter(0, "%"+searchContent+"%");
			query.setParameter(1, "%"+searchContent+"%");

		} else {

			query = sessionFactory.getCurrentSession().createQuery(" From Dept dept order by dept.bi_dept_id desc");

		}

		// int start = (currentPage) * countPerPage;
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);

		logger.info("query.list" + query.list().toString());

		return query.list();

	}

	@SuppressWarnings("unchecked")
	public int getTotalNo(int searchTitle, String searchContent)
			throws DataAccessException {

		Query query = null;

		if (searchTitle == 1) {

			query = sessionFactory.getCurrentSession().createQuery(	" From Dept dept where dept.bi_dept_id like ? ");
			query.setParameter(0, "%"+searchContent+"%");

		} else if (searchTitle == 2) {

			query = sessionFactory.getCurrentSession().createQuery(	" From Dept dept where dept.bi_deptnm like ? ");
			query.setParameter(0, "%"+searchContent+"%");

		} else if (searchTitle == 3) {

			query = sessionFactory.getCurrentSession().createQuery(" From Dept dept where dept.bi_dept_id like ? or dept.bi_deptnm like ?");
			query.setParameter(0, "%"+searchContent+"%");
			query.setParameter(1, "%"+searchContent+"%");

		} else {

			query = sessionFactory.getCurrentSession().createQuery(	" From Dept dept order by dept.bi_sort_sn asc");

		}

		return query.list().size();

	}

	@SuppressWarnings("unchecked")
	public List<Dept> getDeptList() throws DataAccessException {
		logger.debug("findBoardList ");

		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From Dept dept order by dept.bi_dept_id asc");

		return query.list();

	}
	
	@Value("#{sqlquery['dept.deleteAll']}") private String query1;
	
	@SuppressWarnings("unchecked")
	public int deleteAll() throws DataAccessException {
		
		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query1);
		query.executeUpdate();
		

		return 1;

	} 
	
	@Value("#{sqlquery['dept.deleteDept']}") private String query2;
	@SuppressWarnings("unchecked")
	public int deleteDept(String bi_perm_yn) throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query2);
		query.setParameter(0, bi_perm_yn);
		query.executeUpdate();
		

		return 1;

	} 
	
	@SuppressWarnings("unchecked")
	public int batchInsert(List<Dept> list) throws DataAccessException { //HibernateException

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

	
	
}
