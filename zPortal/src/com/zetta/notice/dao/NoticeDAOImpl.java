package com.zetta.notice.dao;

// Generated 2010. 5. 24 ���� 7:01:23 by Hibernate Tools 3.2.4.GA

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.notice.model.Notice;

/**
 * Home object for domain model class Board.
 * 
 * @see com.board.model.Board
 * @author Hibernate Tools
 */

@Repository
@Transactional
public class NoticeDAOImpl implements NoticeDAO {

	private static Logger logger = Logger.getLogger(NoticeDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public Notice getById(int bi_nct_sn) throws DataAccessException {
		return (Notice) sessionFactory.getCurrentSession().get(Notice.class, bi_nct_sn);
	}

	@SuppressWarnings("unchecked")
	public Notice insert(Notice notice) throws DataAccessException {
		sessionFactory.getCurrentSession().save(notice);
		return notice;
	}

	@SuppressWarnings("unchecked")
	public Notice update(Notice notice) throws DataAccessException {

		sessionFactory.getCurrentSession().merge(notice);

		return notice;
	}

	@SuppressWarnings("unchecked")
	public int delete(int bi_nct_sn) throws DataAccessException {

		sessionFactory.getCurrentSession().delete(getById(bi_nct_sn));

		return 1;
	}

	@SuppressWarnings("unchecked")
	public Notice findList(int bi_nct_sn) throws DataAccessException {

		Notice notice = (Notice) sessionFactory.getCurrentSession().get(Notice.class, bi_nct_sn);

		if (notice == null) {

			throw new ObjectRetrievalFailureException(Notice.class, bi_nct_sn);

		}

		return notice;

	}

	@SuppressWarnings("unchecked")
	public List<Notice> findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent) throws DataAccessException {
		logger.debug("findBoardList ");

		List<Notice> list = new ArrayList<Notice>();
		Query query = null;

		if (searchTitle == 1) {

			query = sessionFactory.getCurrentSession().createQuery(" From Notice notice where notice.bi_titl like ? ");
			query.setParameter(0, "%" + searchContent + "%");

		} else if (searchTitle == 2) {

			query = sessionFactory.getCurrentSession().createQuery(" From Notice notice where notice.bi_cn like ? ");
			query.setParameter(0, "%" + searchContent + "%");

		} else if (searchTitle == 3) {

			query = sessionFactory.getCurrentSession().createQuery(" From Notice notice where notice.bi_titl like ? or notice.bi_cn like ?");
			query.setParameter(0, "%" + searchContent + "%");
			query.setParameter(1, "%" + searchContent + "%");

		} else {

			// query =
			// sessionFactory.getCurrentSession().createQuery(" From Notice notice  order by notice.no desc");
			query = sessionFactory.getCurrentSession().createQuery(" From Notice notice order by notice.bi_nct_sn desc");

			// query = sessionFactory
			// .getCurrentSession()
			// .createSQLQuery(
			// " Select  "
			// + " notice.no as no "
			// + ", notice.userId as userId "
			// + ", member.userNm as userNm "
			// + ", notice.title as title "
			// + ", notice.modifyDate as modifyDate "
			// + ", notice.hitcount as hitCount "
			// + " FROM NOTICE notice "
			// + " left outer join MEMBER member "
			// + " on notice.userId = member.userId ");

		}

		// int start = (currentPage) * countPerPage;
		query.setFirstResult(currentPage);
		query.setMaxResults(countPerPage);
		list = query.list();
		// logger.info("query.list" + query.list().toString());

		return list;

	}

	@SuppressWarnings("unchecked")
	public int getTotalNo(int searchTitle, String searchContent) throws DataAccessException {

		Query query = null;

		if (searchTitle == 1) {

			query = sessionFactory.getCurrentSession().createQuery(" From Notice notice where notice.bi_titl like ? ");
			query.setParameter(0, "%" + searchContent + "%");

		} else if (searchTitle == 2) {

			query = sessionFactory.getCurrentSession().createQuery(" From Notice notice where notice.bi_cn like ? ");
			query.setParameter(0, "%" + searchContent + "%");

		} else if (searchTitle == 3) {

			query = sessionFactory.getCurrentSession().createQuery(" From Notice notice where notice.bi_titl like ? or notice.bi_cn like ?");
			query.setParameter(0, "%" + searchContent + "%");
			query.setParameter(1, "%" + searchContent + "%");

		} else {

			query = sessionFactory.getCurrentSession().createQuery(" From Notice notice order by notice.bi_nct_sn desc");

		}

		return query.list().size();

	}

	public int updateHitCount(int bi_nct_sn) throws DataAccessException {
		Notice notice = (Notice) sessionFactory.getCurrentSession().get(Notice.class, new Integer(bi_nct_sn));
		if (notice != null) {
			notice.setBi_inqire_num(notice.getBi_inqire_num() + 1);
		}

		sessionFactory.getCurrentSession().update(notice);

		return 1;
	}

	public Notice getPriView(int bi_nct_sn) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery("from Notice notice where notice.bi_nct_sn < ?  order by notice.bi_nct_sn asc");
		query.setParameter(0, bi_nct_sn);

		if (query.list().size() > 0) {

			List<Notice> resultList = query.list();

			return resultList.get(0);

		} else {

			return null;

		}

	}

	public Notice getNextView(int bi_nct_sn) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery("from Notice notice where notice.bi_nct_sn > ?  order by notice.bi_nct_sn asc");
		query.setParameter(0, bi_nct_sn);

		if (query.list().size() > 0) {

			List<Notice> resultList = query.list();
			return resultList.get(0);

		} else {

			return null;

		}

	}

	public Notice getMainPopUpView(String bi_popup_yn) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery("from Notice notice where notice.bi_popup_yn = ?  order by notice.bi_nct_sn desc");
		query.setParameter(0, bi_popup_yn);

		if (query.list().size() > 0) {

			List<Notice> resultList = query.list();
			return resultList.get(0);

		} else {

			return null;

		}

	}

}
