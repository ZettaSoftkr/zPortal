package com.zetta.faq.dao;

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
import com.zetta.notice.model.Notice;

@Repository
@Transactional
public class FaqDAOImpl implements FaqDAO {
	

	private static Logger logger = Logger.getLogger(FaqDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@SuppressWarnings("unchecked")
	public Faq getById(int getNo)  throws DataAccessException 
	{
		return (Faq) sessionFactory.getCurrentSession().get(Faq.class, getNo);
	}

	
	@SuppressWarnings("unchecked")
	public Faq insert(Faq faq) throws DataAccessException {
		sessionFactory.getCurrentSession().save(faq);
		return faq;
	}
	
	

	@SuppressWarnings("unchecked")
	public Faq update(Faq faq) throws DataAccessException {
		
		sessionFactory.getCurrentSession().merge(faq);

		return faq;
	}
	@SuppressWarnings("unchecked")
	public int delete(int bi_faq_sn) throws DataAccessException {
		
		sessionFactory.getCurrentSession().delete(getById(bi_faq_sn));

		return 1;
	}
	
	
	@SuppressWarnings("unchecked")
	public Faq findList(int bi_faq_sn) throws DataAccessException {
		
		Faq faq = (Faq)sessionFactory.getCurrentSession().get(Faq.class,bi_faq_sn);
		
		if (faq == null) {
			
			throw new ObjectRetrievalFailureException(Faq.class, bi_faq_sn);
		
		}
		
		return faq;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Faq> getFaqList(int searchTitle, String searchContent)  throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;
		
		if(searchTitle == 1){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Faq faq where faq.bi_titl like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			
			
		}else if(searchTitle == 2){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Faq faq where faq.bi_cn like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			
		}else if(searchTitle == 3){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Faq faq where faq.bi_titl like ? or faq.bi_cn like ?");
			query.setParameter(0, "%"+searchContent+"%");
			query.setParameter(1, "%"+searchContent+"%");
			
			
		}else{
			
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Faq faq order by faq.bi_faq_sn desc");
		
			
		}
		
		
	
		
		return query.list();
		
	}


	@SuppressWarnings("unchecked")
	public List<Faq> findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent)  throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;
		
		if(searchTitle == 1){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Faq faq where faq.bi_titl like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			
			
		}else if(searchTitle == 2){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Faq faq where faq.bi_cn like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			
		}else if(searchTitle == 3){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Faq faq where faq.bi_titl like ?  or faq.bi_cn like ?");
			query.setParameter(0, "%"+searchContent+"%");
			query.setParameter(1, "%"+searchContent+"%");
			
		}else{
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Faq faq order by faq.bi_faq_sn desc");
		
			
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
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Faq faq where faq.bi_titl like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			
			
		}else if(searchTitle == 2){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Faq faq where faq.bi_cn like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			
		}else if(searchTitle == 3){
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Faq faq where faq.bi_titl like ? or faq.bi_cn like ?");
			query.setParameter(0, "%"+searchContent+"%");
			query.setParameter(1, "%"+searchContent+"%");
			
		}else{
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Faq faq order by faq.bi_faq_sn desc");
			
		}		
		
		return query.list().size();
		
	}

	

	public Faq getPriView(int bi_faq_sn) throws DataAccessException {
		
		
		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery("from Faq faq where faq.bi_faq_sn < ?  order by faq.bi_faq_sn asc");
		query.setParameter(0, bi_faq_sn);
		

		
		if(query.list().size() > 0 ){
		
			List<Faq> resultList = query.list();
			
			return resultList.get(0);
		
		}else{
			
			return null;
			
		}
		
		
	}
	public Faq getNextView(int bi_faq_sn) throws DataAccessException {
		
		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery("from Faq faq where faq.bi_faq_sn > ?  order by faq.bi_faq_sn asc");
		query.setParameter(0, bi_faq_sn);
		
		if(query.list().size() > 0 ){
		
			List<Faq> resultList = query.list();
			return resultList.get(0);
		
		}else{
			
			return null;
			
		}
		
	}
	
	public int updateHitCount(int bi_faq_sn) throws DataAccessException {
		Faq faq = (Faq) sessionFactory.getCurrentSession().get(Faq.class, new Integer(bi_faq_sn));
		if (faq != null) {
			faq.setBi_inqire_num(faq.getBi_inqire_num() + 1);
		}

		sessionFactory.getCurrentSession().update(faq);

		return 1;
	}
	
	
}
