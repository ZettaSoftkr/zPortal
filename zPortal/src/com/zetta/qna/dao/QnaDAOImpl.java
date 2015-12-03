package com.zetta.qna.dao;

// Generated 2010. 5. 24 ���� 7:01:23 by Hibernate Tools 3.2.4.GA

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.faq.model.Faq;
import com.zetta.qna.model.Qna;


/**
 * Home object for domain model class Qna.
 * 
 * @see com.qna.model.Qna
 * @author Hibernate Tools
 */

@Repository
@Transactional
public class QnaDAOImpl implements QnaDAO {
	

	private static Logger logger = Logger.getLogger(QnaDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@SuppressWarnings("unchecked")
	public Qna getById(int bi_qna_sn)  throws DataAccessException 
	{
		return (Qna) sessionFactory.getCurrentSession().get(Qna.class, bi_qna_sn);
	}

	
	@SuppressWarnings("unchecked")
	public Qna insert(Qna qna) throws DataAccessException {
		sessionFactory.getCurrentSession().save(qna);
		return qna;
	}
	
	

	@SuppressWarnings("unchecked")
	public Qna update(Qna qna) throws DataAccessException {
		
		sessionFactory.getCurrentSession().merge(qna);

		return qna;
	}
	@SuppressWarnings("unchecked")
	public int delete(int bi_qna_sn) throws DataAccessException {
		
		sessionFactory.getCurrentSession().delete(getById(bi_qna_sn));

		return 1;
	}
	
	
	@SuppressWarnings("unchecked")
	public Qna findList(int bi_qna_sn) throws DataAccessException {
		
		Qna qna = (Qna)sessionFactory.getCurrentSession().get(Qna.class,bi_qna_sn);
		
		if (qna == null) {
			
			throw new ObjectRetrievalFailureException(Qna.class, bi_qna_sn);
		
		}
		
		return qna;
		
	}
	


	@SuppressWarnings("unchecked")
	public List<Qna> findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent)  throws DataAccessException {
		logger.debug("findQnaList ");
		Query query = null;
		
		if(searchTitle == 1){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna where qna.bi_titl like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			
			
		}else if(searchTitle == 2){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna where qna.bi_cn like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			
		}else if(searchTitle == 3){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna where qna.bi_titl like ? or qna.bi_cn like ?");
			query.setParameter(0, "%"+searchContent+"%");
			query.setParameter(1, "%"+searchContent+"%");
			
		}else{
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna order by qna.bi_qna_sn desc");
		
			
		}
		
		
		//int start = (currentPage) * countPerPage;
		query.setFirstResult(currentPage);
		query.setMaxResults(countPerPage);
		
		logger.info("query.list" + query.list().toString());
		
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Qna> findMypageList(int currentPage, int countPerPage, int searchTitle, String searchContent, String bi_unity_cust_id)  throws DataAccessException {
		logger.debug("findQnaList ");
		Query query = null;
		
		if(searchTitle == 1){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna where qna.bi_unity_cust_id = ? and qna.bi_titl like ? ");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%"+searchContent+"%");
			
			
		}else if(searchTitle == 2){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna where qna.bi_unity_cust_id = ? and qna.bi_cn like ? ");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%"+searchContent+"%");
			
		}else if(searchTitle == 3){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna where qna.bi_unity_cust_id = ? and qna.bi_titl like ? or qna.bi_cn like ?");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%"+searchContent+"%");
			query.setParameter(2, "%"+searchContent+"%");
			
		}else{
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna where qna.bi_unity_cust_id = ?  order by qna.bi_qna_sn desc");
			query.setParameter(0, bi_unity_cust_id);
			
		}
		
		
		//int start = (currentPage) * countPerPage;
		query.setFirstResult(currentPage);
		query.setMaxResults(countPerPage);
		
		logger.info("query.list" + query.list().toString());
		
		return query.list();
		
	}

	@SuppressWarnings("unchecked")
	public int getMypageTotalNo(int searchTitle, String searchContent, String bi_unity_cust_id)  throws DataAccessException {
		
		Query query = null;
		
		if(searchTitle == 1){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna where qna.bi_unity_cust_id = ? and qna.bi_titl like ? ");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%"+searchContent+"%");
			
			
		}else if(searchTitle == 2){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna where qna.bi_unity_cust_id = ? and qna.bi_cn like ? ");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%"+searchContent+"%");
			
		}else if(searchTitle == 3){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna where qna.bi_unity_cust_id = ? and qna.bi_titl like ? or qna.bi_cn like ?");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%"+searchContent+"%");
			query.setParameter(2, "%"+searchContent+"%");
			
		}else{
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna where qna.bi_unity_cust_id = ? order by qna.bi_qna_sn desc");
			
		}		
		
		return query.list().size();
		
	}
	
	@SuppressWarnings("unchecked")
	public int getTotalNo(int searchTitle, String searchContent)  throws DataAccessException {
		
		Query query = null;
		
		if(searchTitle == 1){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna where qna.bi_titl like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			
			
		}else if(searchTitle == 2){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna where qna.bi_cn like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			
		}else if(searchTitle == 3){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna where qna.bi_titl like ? or qna.bi_cn like ?");
			query.setParameter(0, "%"+searchContent+"%");
			query.setParameter(1, "%"+searchContent+"%");
			
		}else{
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna order by qna.bi_qna_sn desc");
			
		}		
		
		return query.list().size();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public int getCntCheck(int bi_qna_sn, String bi_unity_cust_id)  throws DataAccessException {
		
		Query query = null;
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Qna qna where qna.bi_qna_sn = ? and qna.bi_unity_cust_id = ?");
			query.setParameter(0, bi_qna_sn);
			query.setParameter(1, bi_unity_cust_id);
		
		
		return query.list().size();
		
	}

	
	public int updateHitCount(int bi_qna_sn) throws DataAccessException {
		Qna qna = (Qna) sessionFactory.getCurrentSession().get(Qna.class, new Integer(bi_qna_sn));
		if (qna != null) {
			qna.setBi_inqire_num(qna.getBi_inqire_num() + 1);
		}

		sessionFactory.getCurrentSession().update(qna);

		return 1;
	}


	

	
}
