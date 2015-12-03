package com.zetta.comment.dao;

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

import com.zetta.comment.model.Comment;


/**
 * Home object for domain model class Comment.
 * 
 * @see com.comment.model.Comment
 * @author Hibernate Tools
 */

@Repository
@Transactional
public class CommentDAOImpl implements CommentDAO {
	

	private static Logger logger = Logger.getLogger(CommentDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@SuppressWarnings("unchecked")
	public Comment getById(int bi_bbs_ansr_sn)  throws DataAccessException 
	{
		return (Comment) sessionFactory.getCurrentSession().get(Comment.class, bi_bbs_ansr_sn);
	}

	
	@SuppressWarnings("unchecked")
	public Comment insert(Comment comment) throws DataAccessException {
		sessionFactory.getCurrentSession().save(comment);
		return comment;
	}
	
	

	@SuppressWarnings("unchecked")
	public Comment update(Comment comment) throws DataAccessException {
		
		sessionFactory.getCurrentSession().merge(comment);

		return comment;
	}
	@SuppressWarnings("unchecked")
	public int delete(int bi_bbs_ansr_sn) throws DataAccessException {
		
		sessionFactory.getCurrentSession().delete(getById(bi_bbs_ansr_sn));

		return 1;
	}
	
	
	@SuppressWarnings("unchecked")
	public Comment findList(int bi_bbs_ansr_sn) throws DataAccessException {
		
		Comment comment = (Comment)sessionFactory.getCurrentSession().get(Comment.class,bi_bbs_ansr_sn);
		
		if (comment == null) {
			
			throw new ObjectRetrievalFailureException(Comment.class, bi_bbs_ansr_sn);
		
		}
		
		return comment;
		
	}
	


	@SuppressWarnings("unchecked")
	public List<Comment> findAllList(int bi_bbs_sn, int searchTitle, String searchContent)  throws DataAccessException {
		logger.debug("findCommentList ");
		Query query = null;
			
			query  = sessionFactory.getCurrentSession().createQuery(" From Comment comment where comment.bi_bbs_sn = ?");
			query.setParameter(0, bi_bbs_sn);
			
		
		//int start = (currentPage) * countPerPage;
		
		logger.info("query.list" + query.list().toString());
		
		return query.list();
		
	}

	@SuppressWarnings("unchecked")
	public int getTotalNo(int bi_bbs_sn, int searchTitle, String searchContent)  throws DataAccessException {
		
		Query query = null;
			

			query  = sessionFactory.getCurrentSession().createQuery(" From Comment comment where comment.bi_bbs_sn = ?");
			query.setParameter(0, bi_bbs_sn);
			
			
			
		
		return query.list().size();
		
	}


	

	
}
