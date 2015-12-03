package com.zetta.menuGroup.dao;

// Generated 2010. 5. 24 ���� 7:01:23 by Hibernate Tools 3.2.4.GA

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.menuGroup.model.MenuGroup;
import com.zetta.menuGroup.model.MenuGroupMapp;
import com.zetta.menuGroup.model.MenuUserGroupMapp;

@Repository
@Transactional
public class MenuGroupDAOImpl implements MenuGroupDAO {

	private static Logger logger = Logger.getLogger(MenuGroupDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public MenuGroup getById(String bi_menu_group_id) throws DataAccessException {
		return (MenuGroup) sessionFactory.getCurrentSession().get(MenuGroup.class, bi_menu_group_id);
	}

	@SuppressWarnings("unchecked")
	public MenuGroup insert(MenuGroup menuGroup) throws DataAccessException {
		sessionFactory.getCurrentSession().save(menuGroup);
		return menuGroup;
	}

	@SuppressWarnings("unchecked")
	public MenuGroup update(MenuGroup menuGroup) throws DataAccessException {

		sessionFactory.getCurrentSession().merge(menuGroup);

		return menuGroup;
	}

	@SuppressWarnings("unchecked")
	public int delete(String bi_menu_group_id) throws DataAccessException {

		MenuGroup menuGroup = (MenuGroup) sessionFactory.getCurrentSession().get(MenuGroup.class, bi_menu_group_id);

		sessionFactory.getCurrentSession().delete(menuGroup);
	

		return 1;
	}
	
	@Value("#{sqlquery['menuGroup.deleteAll']}") private String query1;	
	@SuppressWarnings("unchecked")
	public int deleteAll() throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query1);
		query.executeUpdate();
		

		return 1;

	}
	//메뉴그룹 맵핑
	@Value("#{sqlquery['menuGroup.deleteMenuGroupMappAll']}") private String query2;	
	@SuppressWarnings("unchecked")
	public int deleteMenuGroupMappAll() throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query2);
		query.executeUpdate();
		

		return 1;

	}
	
	//사용자그룹 메뉴 그룹 맵핑
	@Value("#{sqlquery['menuGroup.deleteMenuGroupUserGroupMappAll']}") private String query3;	
	@SuppressWarnings("unchecked")
	public int deleteMenuGroupUserGroupMappAll() throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query3);
		query.executeUpdate();
		

		return 1;

	}

	@SuppressWarnings("unchecked")
	public List<MenuGroup> findAllList(int startRow, int endRow, int searchTitle, String searchContent) throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		if (searchTitle == 1) {

			query = sessionFactory.getCurrentSession().createQuery(" From MenuGroup menuGroup where menuGroup.bi_menu_group_nm like ? ");
			query.setParameter(0, "%"+searchContent+"%");

		} else if (searchTitle == 2) {

			query = sessionFactory.getCurrentSession().createQuery(" From MenuGroup menuGroup where menuGroup.bi_menu_group_id like ? ");
			query.setParameter(0, "%"+searchContent+"%");

		} else if (searchTitle == 3) {

			query = sessionFactory.getCurrentSession().createQuery(" From MenuGroup menuGroup where menuGroup.bi_menu_group_nm like ? or menuGroup.bi_menu_group_id like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			query.setParameter(1, "%"+searchContent+"%");

		} else {

			query = sessionFactory.getCurrentSession().createQuery(" From MenuGroup menuGroup order by menuGroup.bi_menu_group_id desc");

		}

		// int start = (currentPage) * countPerPage;
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);

		logger.info("query.list" + query.list().toString());

		return query.list();

	}
	
	
	
	
	

	@SuppressWarnings("unchecked")
	public List<MenuGroup> getMenuGroupList() throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From MenuGroup menuGroup order by menuGroup.bi_menu_group_id desc ");

		logger.info("query.list" + query.list().toString());

		return query.list();

	}
	
	@SuppressWarnings("unchecked")
	public int getMenuGroupMappMenuCnt(String bi_portal_menu_id) throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From MenuGroupMapp menuGroupMapp where menuGroupMapp.bi_portal_menu_id = ? ");
		query.setParameter(0, bi_portal_menu_id);
		logger.info("query.list" + query.list().toString());

		return query.list().size();

	}
	
	@Value("#{sqlquery['menuGroup.getMenuGroupMappDelete1']}") private String query4;	
	@SuppressWarnings("unchecked")
	public int getMenuGroupMappMenuDelete(String bi_portal_menu_id) throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query4);
		query.setParameter(0, bi_portal_menu_id);
		query.executeUpdate();

		return 1;

	}



	@SuppressWarnings("unchecked")
	public int getTotalNo(int searchTitle, String searchContent) throws DataAccessException {

		Query query = null;

		if (searchTitle == 1) {

			query = sessionFactory.getCurrentSession().createQuery(" From MenuGroup menuGroup where menuGroup.bi_menu_group_nm like ? ");
			query.setParameter(0, "%"+searchContent+"%");

		} else if (searchTitle == 2) {

			query = sessionFactory.getCurrentSession().createQuery(" From MenuGroup menuGroup where menuGroup.bi_menu_group_id like ? ");
			query.setParameter(0, "%"+searchContent+"%");

		} else if (searchTitle == 3) {

			query = sessionFactory.getCurrentSession().createQuery(" From MenuGroup menuGroup where menuGroup.bi_menu_group_nm like ? or menuGroup.bi_menu_group_id like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			query.setParameter(1, "%"+searchContent+"%");

		} else {

			query = sessionFactory.getCurrentSession().createQuery(" From MenuGroup menuGroup order by menuGroup.bi_menu_group_id asc");

		}

		return query.list().size();

	}

	@SuppressWarnings("unchecked")
	public MenuGroupMapp menuGroupMappInsert(MenuGroupMapp menuGroupMapp) throws DataAccessException {
		sessionFactory.getCurrentSession().save(menuGroupMapp);
		return menuGroupMapp;
	}
	@Value("#{sqlquery['menuGroup.getMenuGroupMappDelete1']}") private String query5;	
	@SuppressWarnings("unchecked")
	public int getMenuGroupMappDelete(String bi_menu_group_id) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query5);
		query.setParameter(0, bi_menu_group_id);
		query.executeUpdate();

		// sessionFactory.getCurrentSession().delete(bi_group_id);

		return 1;
	}
	
	@Value("#{sqlquery['menuGroup.getMenuGroupMappDelete2']}") private String query6;
	@SuppressWarnings("unchecked")
	public int getMenuGroupMappDelete(String bi_menu_group_id, String bi_portal_menu_id) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query6);
		query.setParameter(0, bi_menu_group_id);
		query.setParameter(1, bi_portal_menu_id);
		query.executeUpdate();

		// sessionFactory.getCurrentSession().delete(bi_group_id);

		return 1;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public MenuGroup getMenuGroupNm(String bi_menu_group_nm) throws DataAccessException {

		Query query = null;
		
		logger.info("bi_menu_group_nm: " + bi_menu_group_nm);
		query = sessionFactory.getCurrentSession().createQuery("From MenuGroup menuGroup where menuGroup.bi_menu_group_nm = ?");
		query.setParameter(0, bi_menu_group_nm);
		logger.info("query.list(): " + query.list());
		logger.info("query.list().get(0): " + query.list().get(0));
		MenuGroup menuGroup = (MenuGroup) query.list().get(0);
		// sessionFactory.getCurrentSession().delete(bi_group_id);

		return menuGroup;
	}

	@SuppressWarnings("unchecked")
	public int getMenuGroupMappCnt(String bi_menu_group_id) throws DataAccessException {

		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From MenuGroupMapp menuGroupMapp where menuGroupMapp.bi_menu_group_id = ? ");
		query.setParameter(0, bi_menu_group_id);

		return query.list().size();

	}
	
	@SuppressWarnings("unchecked")
	public int getMenuGroupMappCnt(String bi_menu_group_id,String bi_portal_menu_id) throws DataAccessException {

		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From MenuGroupMapp menuGroupMapp where menuGroupMapp.bi_menu_group_id = ? and menuGroupMapp.bi_portal_menu_id = ?");
		query.setParameter(0, bi_menu_group_id);
		query.setParameter(1, bi_portal_menu_id);

		return query.list().size();

	}
	@Value("#{sqlquery['menuGroup.getMenuGroupMappList']}") private String query7;
	public List<MenuGroupMapp> getMenuGroupMappList(String bi_menu_group_id) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query7);
		query.setParameter(0, bi_menu_group_id);
		return query.list();
	}

	
	
	@SuppressWarnings("unchecked")
	public MenuUserGroupMapp menuUserGroupMappInsert(MenuUserGroupMapp menuUserGroupMapp) throws DataAccessException {
		sessionFactory.getCurrentSession().save(menuUserGroupMapp);
		return menuUserGroupMapp;
	}
	@Value("#{sqlquery['menuGroup.getMenuUserGroupMappDelete1']}") private String query8;
	@SuppressWarnings("unchecked")
	public int getMenuUserGroupMappDelete(String bi_menu_group_id) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query8);
		query.setParameter(0, bi_menu_group_id);
		query.executeUpdate();

		// sessionFactory.getCurrentSession().delete(bi_group_id);

		return 1;
	}
	
	@Value("#{sqlquery['menuGroup.getMenuUserGroupMappDelete2']}") private String query9;
	@SuppressWarnings("unchecked")
	public int getMenuUserGroupMappDelete(String bi_menu_group_id, String bi_group_id) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query9);
		query.setParameter(0, bi_menu_group_id);
		query.setParameter(1,  bi_group_id);
		query.executeUpdate();

		// sessionFactory.getCurrentSession().delete(bi_group_id);

		return 1;
	}
	

	

	
	@SuppressWarnings("unchecked")
	public int getMenuUserGroupMappCnt(String bi_menu_group_id) throws DataAccessException {

		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From MenuUserGroupMapp menuUserGroupMapp where menuUserGroupMapp.bi_menu_group_id = ? ");
		query.setParameter(0, bi_menu_group_id);

		return query.list().size();

	}
	
	
	@SuppressWarnings("unchecked")
	public int getMenuUserGroupMappCnt(String bi_menu_group_id, String bi_group_id) throws DataAccessException {

		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From MenuUserGroupMapp menuUserGroupMapp where menuUserGroupMapp.bi_menu_group_id = ? and menuUserGroupMapp.bi_group_id = ? ");
		query.setParameter(0, bi_menu_group_id);
		query.setParameter(1, bi_group_id);

		return query.list().size();

	}
	
	

	@Value("#{sqlquery['menuGroup.getMenuUserGroupMappList']}") private String query10;
	@SuppressWarnings("unchecked")
	public List<MenuUserGroupMapp> getMenuUserGroupMappList(String bi_menu_group_id) throws DataAccessException {
		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query10);
		query.setParameter(0, bi_menu_group_id);
		return query.list();
	}
	
	
	@Value("#{sqlquery['menuGroup.getMenuGroupMappStatus']}") private String query11;	
	@SuppressWarnings("unchecked")
	public List<MenuGroup> getMenuGroupMappStatus() throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query11);

		logger.info("query.list" + query.list().toString());

		return query.list();

	}
	
	@Value("#{sqlquery['menuGroup.getMenuGroupInitDelete']}") private String query12;	
	@SuppressWarnings("unchecked")
	public int getMenuGroupInitDelete(String initYn) throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query12);
		query.setParameter(0, initYn);
		query.executeUpdate();

		return 1;

	}
	
	
	
}