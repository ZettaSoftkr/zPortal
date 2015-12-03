package com.zetta.board.dao;

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

import com.zetta.board.model.Board;

@Repository
@Transactional
public class BoardDAOImpl implements BoardDAO {

	private static Logger logger = Logger.getLogger(BoardDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public Board getById(int bi_bbs_sn) throws DataAccessException {
		return (Board) sessionFactory.getCurrentSession().get(Board.class, bi_bbs_sn);
	}

	@SuppressWarnings("unchecked")
	public Board insert(Board board) throws DataAccessException {
		sessionFactory.getCurrentSession().save(board);
		return board;
	}

	@SuppressWarnings("unchecked")
	public Board update(Board board) throws DataAccessException {

		sessionFactory.getCurrentSession().merge(board);

		return board;
	}

	@SuppressWarnings("unchecked")
	public int delete(int bi_bbs_sn) throws DataAccessException {

		sessionFactory.getCurrentSession().delete(getById(bi_bbs_sn));

		return 1;
	}

	@SuppressWarnings("unchecked")
	public Board findList(int bi_bbs_sn) throws DataAccessException {

		Board board = (Board) sessionFactory.getCurrentSession().get(Board.class, bi_bbs_sn);

		if (board == null) {

			throw new ObjectRetrievalFailureException(Board.class, bi_bbs_sn);

		}

		return board;

	}

	public int updateHitCount(int bi_bbs_cn) throws DataAccessException {
		Board board = (Board) sessionFactory.getCurrentSession().get(Board.class, new Integer(bi_bbs_cn));
		if (board != null) {
			board.setBi_inqire_num(board.getBi_inqire_num() + 1);
		}

		sessionFactory.getCurrentSession().update(board);

		return 1;
	}

	@SuppressWarnings("unchecked")
	public Board findListDesc() throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From Board board order by board.bi_bbs_sn desc");

		Board board = (Board) query.list().get(0);

		logger.info("query.list" + query.list().toString());

		return board;

	}

	@SuppressWarnings("unchecked")
	public List<Board> findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent) throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		if (searchTitle == 1) {

			query = sessionFactory.getCurrentSession().createQuery(" From Board board where board.bi_titl like ? ");
			query.setParameter(0, "%" + searchContent + "%");

		} else if (searchTitle == 2) {

			query = sessionFactory.getCurrentSession().createQuery(" From Board board where board.bi_cn like ? ");
			query.setParameter(0, "%" + searchContent + "%");

		} else if (searchTitle == 3) {

			query = sessionFactory.getCurrentSession().createQuery(" From Board board where board.bi_titl like ? or board.bi_cn like ?");
			query.setParameter(0, "%" + searchContent + "%");
			query.setParameter(1, "%" + searchContent + "%");

		} else {

			query = sessionFactory.getCurrentSession().createQuery(" From Board board order by board.bi_bbs_sn desc");

		}

		// int start = (currentPage) * countPerPage;
		query.setFirstResult(currentPage);
		query.setMaxResults(countPerPage);

		logger.info("query.list" + query.list().toString());

		return query.list();

	}

	@SuppressWarnings("unchecked")
	public List<Board> findMypageList(int currentPage, int countPerPage, int searchTitle, String searchContent, String bi_unity_cust_id) throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		if (searchTitle == 1) {

			query = sessionFactory.getCurrentSession().createQuery(" From Board board where board.bi_unity_cust_id = ? and board.bi_titl like ? ");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%" + searchContent + "%");

		} else if (searchTitle == 2) {

			query = sessionFactory.getCurrentSession().createQuery(" From Board board where board.bi_unity_cust_id = ? and board.bi_cn like ? ");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%" + searchContent + "%");

		} else if (searchTitle == 3) {

			query = sessionFactory.getCurrentSession().createQuery(" From Board board where board.bi_unity_cust_id = ? and board.bi_titl like ? or board.bi_cn like ?");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%" + searchContent + "%");
			query.setParameter(2, "%" + searchContent + "%");

		} else {

			query = sessionFactory.getCurrentSession().createQuery(" From Board board where board.bi_unity_cust_id = ? order by board.bi_bbs_sn desc");
			query.setParameter(0, bi_unity_cust_id);

		}

		// int start = (currentPage) * countPerPage;
		query.setFirstResult(currentPage);
		query.setMaxResults(countPerPage);

		logger.info("query.list" + query.list().toString());

		return query.list();

	}

	@SuppressWarnings("unchecked")
	public int getMyPageTotalNo(int searchTitle, String searchContent, String bi_unity_cust_id) throws DataAccessException {

		Query query = null;

		if (searchTitle == 1) {

			query = sessionFactory.getCurrentSession().createQuery(" From Board board where board.bi_unity_cust_id = ? and board.bi_titl like ? ");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%" + searchContent + "%");

		} else if (searchTitle == 2) {

			query = sessionFactory.getCurrentSession().createQuery(" From Board board where board.bi_unity_cust_id = ? and board.bi_cn like ? ");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%" + searchContent + "%");

		} else if (searchTitle == 3) {

			query = sessionFactory.getCurrentSession().createQuery(" From Board board where board.bi_unity_cust_id = ? and board.bi_titl like ? or board.bi_cn like ?");
			query.setParameter(0, bi_unity_cust_id);
			query.setParameter(1, "%" + searchContent + "%");
			query.setParameter(2, "%" + searchContent + "%");

		} else {

			query = sessionFactory.getCurrentSession().createQuery(" From Board board where board.bi_unity_cust_id = ?  order by board.bi_bbs_sn desc");
			query.setParameter(0, bi_unity_cust_id);

		}

		return query.list().size();

	}

	@SuppressWarnings("unchecked")
	public int getTotalNo(int searchTitle, String searchContent) throws DataAccessException {

		Query query = null;

		if (searchTitle == 1) {

			query = sessionFactory.getCurrentSession().createQuery(" From Board board where board.bi_titl like ? ");
			query.setParameter(0, "%" + searchContent + "%");

		} else if (searchTitle == 2) {

			query = sessionFactory.getCurrentSession().createQuery(" From Board board where board.bi_cn like ? ");
			query.setParameter(0, "%" + searchContent + "%");

		} else if (searchTitle == 3) {

			query = sessionFactory.getCurrentSession().createQuery(" From Board board where board.bi_titl like ? or board.bi_cn like ?");
			query.setParameter(0, "%" + searchContent + "%");
			query.setParameter(1, "%" + searchContent + "%");

		} else {

			query = sessionFactory.getCurrentSession().createQuery(" From Board board order by board.bi_bbs_sn desc");

		}

		return query.list().size();

	}

	public Board getPreView(int bi_bbs_sn) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery("from Board board where board.bi_bbs_sn < ?  order by board.bi_bbs_sn asc");
		query.setParameter(0, bi_bbs_sn);

		if (query.list().size() > 0) {

			List<Board> resultList = query.list();

			return resultList.get(0);

		} else {

			return null;

		}

	}

	public Board getNextView(int bi_bbs_sn) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery("from Board board where board.bi_bbs_sn > ?  order by board.bi_bbs_sn asc");
		query.setParameter(0, bi_bbs_sn);

		if (query.list().size() > 0) {

			List<Board> resultList = query.list();
			return resultList.get(0);

		} else {

			return null;

		}

	}

	@SuppressWarnings("unchecked")
	public int getCntCheck(int bi_bbs_sn, String bi_unity_cust_id) throws DataAccessException {

		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From Board board where board.bi_bbs_sn = ? and board.bi_unity_cust_id = ?");
		query.setParameter(0, bi_bbs_sn);
		query.setParameter(1, bi_unity_cust_id);

		return query.list().size();

	}

}
