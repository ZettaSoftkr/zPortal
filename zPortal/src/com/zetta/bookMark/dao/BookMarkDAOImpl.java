package com.zetta.bookMark.dao;

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

import com.zetta.bookMark.model.BookMark;


/**
 * Home object for domain model class BookMark.
 * 
 * @see com.bookMark.model.BookMark
 * @author Hibernate Tools
 */

@Repository
@Transactional
public class BookMarkDAOImpl implements BookMarkDAO {
	

	private static Logger logger = Logger.getLogger(BookMarkDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@SuppressWarnings("unchecked")
	public BookMark getById(int bi_bkmk_sn)  throws DataAccessException 
	{
		return (BookMark) sessionFactory.getCurrentSession().get(BookMark.class, bi_bkmk_sn);
	}

	
	@SuppressWarnings("unchecked")
	public BookMark insert(BookMark bookMark) throws DataAccessException {
		sessionFactory.getCurrentSession().save(bookMark);
		return bookMark;
	}
	
	

	@SuppressWarnings("unchecked")
	public BookMark update(BookMark bookMark) throws DataAccessException {
		
		sessionFactory.getCurrentSession().merge(bookMark);

		return bookMark;
	}
	@SuppressWarnings("unchecked")
	public int delete(int bi_bkmk_sn) throws DataAccessException {
		
		BookMark bookMark = (BookMark)sessionFactory.getCurrentSession().get(BookMark.class,bi_bkmk_sn);
		sessionFactory.getCurrentSession().delete(bookMark);

		return 1;
	}
	
	
	@SuppressWarnings("unchecked")
	public BookMark findList(int bi_bkmk_sn) throws DataAccessException {
		
		BookMark bookMark = (BookMark)sessionFactory.getCurrentSession().get(BookMark.class,bi_bkmk_sn);
		
		if (bookMark == null) {
			
			throw new ObjectRetrievalFailureException(BookMark.class, bi_bkmk_sn);
		
		}
		
		return bookMark;
		
	}
	


	@SuppressWarnings("unchecked")
	public List<BookMark> findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent, String bi_unity_cust_id)  throws DataAccessException {
		logger.debug("findBookMarkList ");
		Query query = null;
		
		if(searchTitle == 1){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From BookMark bookMark where reportCycle.bi_unity_cust_id = ?  and bookMark.bi_titl like ? ");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%"+searchContent+"%");
			
			
			
		}else if(searchTitle == 2){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From BookMark bookMark where reportCycle.bi_unity_cust_id = ?  and bookMark.bi_cn like ? ");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%"+searchContent+"%");
			
			
		}else if(searchTitle == 3){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From BookMark bookMark where reportCycle.bi_unity_cust_id = ?  and bookMark.bi_titl like ? or bookMark.bi_cn like ?");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%"+searchContent+"%");
			query.setParameter(2, "%"+searchContent+"%");
			
		}else{
			
			query  = sessionFactory.getCurrentSession().createQuery(" From BookMark bookMark where bookMark.bi_unity_cust_id = ?  order by bookMark.bi_bkmk_sn desc");
			query.setParameter(0, bi_unity_cust_id);
		
			
		}
		
		
		//int start = (currentPage) * countPerPage;
		query.setFirstResult(currentPage);
		query.setMaxResults(countPerPage);
		
		logger.info("query.list" + query.list().toString());
		
		return query.list();
		
	}

	@SuppressWarnings("unchecked")
	public int getTotalNo(int searchTitle, String searchContent, String bi_unity_cust_id)  throws DataAccessException {
		
		Query query = null;
		
		if(searchTitle == 1){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From BookMark bookMark where bookMark.bi_unity_cust_id = ?  and bookMark.bi_titl like ? ");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%"+searchContent+"%");

			
			
		}else if(searchTitle == 2){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From BookMark bookMark where bookMark.bi_unity_cust_id = ?  and bookMark.bi_cn like ? ");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%"+searchContent+"%");
			
			
		}else if(searchTitle == 3){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From BookMark bookMark where bookMark.bi_unity_cust_id = ?  and bookMark.bi_titl like ? or bookMark.bi_cn like ?");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%"+searchContent+"%");
			query.setParameter(2, "%"+searchContent+"%");
			
		}else{
			
			query  = sessionFactory.getCurrentSession().createQuery(" From BookMark bookMark where bookMark.bi_unity_cust_id = ?  order by bookMark.bi_bkmk_sn desc");
			query.setParameter(0, bi_unity_cust_id);
			
		}		
		
		return query.list().size();
		
	}

	
	@SuppressWarnings("unchecked")
	public int getCntCheck(int bi_bkmk_sn, String bi_unity_cust_id)  throws DataAccessException {
		
		Query query = null;
			
			query  = sessionFactory.getCurrentSession().createQuery(" From BookMark bookMark where bookMark.bi_bkmk_sn = ? and bookMark.bi_unity_cust_id = ?");
			query.setParameter(0, bi_bkmk_sn);
			query.setParameter(1, bi_unity_cust_id);
		
		
		return query.list().size();
		
	}

	@Value("#{sqlquery['bookMark.deleteAll']}") private String deleteQuery;
	@SuppressWarnings("unchecked")
	public int deleteAll() throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(deleteQuery);
		query.executeUpdate();
		

		return 1;

	}
}
