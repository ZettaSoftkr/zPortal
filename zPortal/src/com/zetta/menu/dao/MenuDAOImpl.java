package com.zetta.menu.dao;

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

import com.zetta.menu.model.Menu;

/**
 * Home object for domain model class Board.
 * 
 * @see com.board.model.Board
 * @author Hibernate Tools
 */

@Repository
@Transactional
public class MenuDAOImpl implements MenuDAO {

	private static Logger logger = Logger.getLogger(MenuDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public Menu getById(String bi_portal_menu_id) throws DataAccessException {
		return (Menu) sessionFactory.getCurrentSession().get(Menu.class, bi_portal_menu_id);
	}

	@SuppressWarnings("unchecked")
	public Menu insert(Menu menu) throws DataAccessException {
		sessionFactory.getCurrentSession().save(menu);
		return menu;
	}

	@SuppressWarnings("unchecked")
	public Menu update(Menu menu) throws DataAccessException {

		sessionFactory.getCurrentSession().merge(menu);

		return menu;
	}

	@SuppressWarnings("unchecked")
	public int delete(String bi_portal_menu_id) throws DataAccessException {

		sessionFactory.getCurrentSession().delete(getById(bi_portal_menu_id));

		return 1;
	}

	@Value("#{sqlquery['menu.childDelete']}") private String query1;
	@SuppressWarnings("unchecked")
	public int childDelete(String bi_portal_menu_id) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query1);
		query.setEntity(0, bi_portal_menu_id);
		query.executeUpdate();

		return 1;
	}
	
	
	@SuppressWarnings("unchecked")
	public int getMenuTypeCnt(String bi_menu_type_yn) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery(" From Menu menu where menu.bi_menu_type_yn  =  ?");
		query.setParameter(0, bi_menu_type_yn);
		
		logger.debug("getMenuTypeCnt: " +  query.list());
		return query.list().size();
	}

	@Value("#{sqlquery['menu.deleteMenuAll']}") private String query2;
	@SuppressWarnings("unchecked")
	public int deleteMenuAll(String bi_menu_type_yn) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query2);
		query.setParameter(0, bi_menu_type_yn);
		query.executeUpdate();

		return 1;
	}
	
	@Value("#{sqlquery['menu.deleteAll']}") private String query3;
	@SuppressWarnings("unchecked")
	public int deleteAll() throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query3);	
		query.executeUpdate();

		return 1;
	}

	@SuppressWarnings("unchecked")
	public Menu findList(String bi_portal_menu_id) throws DataAccessException {

		Menu menu = (Menu) sessionFactory.getCurrentSession().get(Menu.class, bi_portal_menu_id);

		if (menu == null) {

			throw new ObjectRetrievalFailureException(Menu.class, bi_portal_menu_id);

		}

		return menu;

	}

	@SuppressWarnings("unchecked")
	public List<Menu> findAllList() throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From Menu menu order by menu.bi_menu_sort_sn asc");

		logger.info("query.list" + query.list().toString());

		return query.list();

	}
	

	
	
	@SuppressWarnings("unchecked")
	public List<Menu> findSearchList(String bi_search_keyword) throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From Menu menu where menu.bi_menu_type_yn = ? and menu.bi_menu_nm like ? or menu.bi_search_keyword like ? order by menu.bi_menu_sort_sn asc");
		query.setParameter(0, "R");
		query.setParameter(1, "%"+bi_search_keyword+"%");
		query.setParameter(2, "%"+bi_search_keyword+"%");
		logger.info("query.list" + query.list().toString());

		return query.list();

	}


	@SuppressWarnings("unchecked")
	public int getTotalNo() throws DataAccessException {

		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From Menu menu ");

		return query.list().size();

	}
	
	@SuppressWarnings("unchecked")
	public int getMenuCnt(String bi_menu_nm, String bi_portal_menu_parent_id) throws DataAccessException {

		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From Menu menu where menu.bi_menu_nm = ? and menu.bi_portal_menu_parent_id = ?");
		query.setParameter(0, bi_menu_nm);
		query.setParameter(1, bi_portal_menu_parent_id);

		return query.list().size();

	}

	@SuppressWarnings("unchecked")
	public List<Menu> findChildList(String bi_portal_menu_parent_id) throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From Menu menu Where menu.bi_portal_menu_parent_id = ? order by menu.bi_menu_sort_sn asc");

		// query.setEntity(0, parent);
		query.setParameter(0, bi_portal_menu_parent_id);

		return query.list();

	}

	@SuppressWarnings("unchecked")
	public int getChildCount(String bi_portal_menu_parent_id) throws DataAccessException {

		Query query = null;
		

		query = sessionFactory.getCurrentSession().createQuery(" From Menu menu Where menu.bi_portal_menu_parent_id = ? ");

		query.setParameter(0, bi_portal_menu_parent_id);
		
		return query.list().size();

	}
	
	@SuppressWarnings("unchecked")
	public Menu getChildMaxId(String bi_portal_menu_parent_id) throws DataAccessException {

		Query query = null;
		Menu menu = null;
		query = sessionFactory.getCurrentSession().createQuery(" From Menu menu Where menu.bi_portal_menu_parent_id = ? order by menu.bi_reg_dt desc");

		query.setParameter(0, bi_portal_menu_parent_id);
		if(query.list().size() > 0){
			
			menu = (Menu)query.list().get(0);
			
		}
		return menu;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public Menu getMenuNm(String bi_menu_nm) throws DataAccessException {
		logger.debug("findBoardList ");

		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery(" From Menu menu where menu.bi_menu_nm = ? ");
		query.setParameter(0, bi_menu_nm);
		Menu menu = (Menu)query.list().get(0);
		return menu;

	}

	
	public Menu getParentMenuNm(String bi_menu_nm, String bi_portal_menu_parent_id) throws DataAccessException {
		logger.debug("findBoardList ");

		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery(" From Menu menu where menu.bi_menu_nm = ? and menu.bi_portal_menu_parent_id = ?");
		query.setParameter(0, bi_menu_nm);
		query.setParameter(1, bi_portal_menu_parent_id);
		Menu menu = (Menu)query.list().get(0);
		
		
		return menu;

	}

	@SuppressWarnings("unchecked")
	public List<Menu> getMenuList(String bi_portal_menu_parent_id) throws DataAccessException {
		logger.debug("findBoardList ");

		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery(" From Menu menu where menu.bi_portal_menu_parent_id = ? order by menu.bi_menu_sort_sn asc");
		query.setParameter(0, bi_portal_menu_parent_id);

		return query.list();

	}

	@SuppressWarnings("unchecked")
	public List<Menu> leftMenuList(String bi_portal_menu_parent_id) throws DataAccessException {
		logger.debug("findBoardList ");

		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery(" From Menu menu where menu.bi_portal_menu_parent_id = ? order by menu.bi_menu_sort_sn asc");
		query.setParameter(0, bi_portal_menu_parent_id);

		return query.list();

	}

	@Value("#{sqlquery['menu.getRoleMenuList']}") private String query4;
	@SuppressWarnings("unchecked")
	public List getRoleMenuList(String bi_unity_cust_id, String bi_portal_menu_parent_id) throws DataAccessException {

		logger.debug("findBoardList ");

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query4);


		query.setParameter(0, bi_unity_cust_id);
		query.setParameter(1, bi_unity_cust_id);
		query.setParameter(2, bi_portal_menu_parent_id);

		logger.info("menu: " + query.list());
		logger.info("menu: " + query.list().toString());

		return query.list();

	}
	
	@Value("#{sqlquery['menu.getRoleQlikViewList']}") private String query5;
	@SuppressWarnings("unchecked")
	public int getRoleQlikViewList(String bi_unity_cust_id, String bi_portal_menu_id) throws DataAccessException {

		logger.debug("findBoardList ");

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query5);

//		Query query = null;
//		query = sessionFactory.getCurrentSession().createSQLQuery(
//				"SELECT" + "  menu.bi_portal_menu_id" + ", menu.bi_portal_menu_parent_id" + ", menu.bi_menu_nm" + ", menu.bi_menu_url_addr" + ", menu.bi_menu_sort_sn" + ", menu.bi_menu_fm_yn"
//						+ " FROM  TSPORTAL.BIS_PTL_TB_USER_MNG_D UR " + " LEFT OUTER JOIN TSPORTAL.BIS_PTL_TB_GROUP_DEPT_MAPNG_R GDM " + " ON GDM.BI_DEPT_ID = UR.BI_DEPT_ID "
//						+ " AND UR.BI_UNITY_CUST_ID = ? " + " LEFT OUTER JOIN TSPORTAL.BIS_PTL_TB_MENU_GROUP_MAPNG_R  MGM " + " ON GDM.BI_GROUP_ID = MGM.BI_GROUP_ID " + " AND MGM.BI_AUTHOR_ID =  ? "
//						+ " LEFT OUTER JOIN TSPORTAL.BIS_PTL_TB_MENU_MNG_M menu " + " ON MGM.BI_PORTAL_MENU_ID = menu.BI_PORTAL_MENU_ID " + " WHERE menu.BI_PORTAL_MENU_PARENT_ID = ?  "
//						+ " ORDER BY menu.BI_MENU_SORT_SN ASC  ");

		query.setParameter(0, bi_unity_cust_id);
		query.setParameter(1, bi_unity_cust_id);
		query.setParameter(2, bi_portal_menu_id);

		logger.info("menu: " + query.list());
		logger.info("menu: " + query.list().toString());

		return query.list().size();

	}
	@Value("#{sqlquery['menu.getRoleReportList']}") private String query6;
	@SuppressWarnings("unchecked")
	public List getRoleReportList(String bi_unity_cust_id, String bi_portal_menu_parent_id, String bi_menu_type_yn) throws DataAccessException {

		logger.debug("findBoardList ");

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query6);

//		Query query = null;
//		query = sessionFactory.getCurrentSession().createSQLQuery(
//				"SELECT" + "  menu.bi_portal_menu_id" + ", menu.bi_portal_menu_parent_id" + ", menu.bi_menu_nm" + ", menu.bi_menu_url_addr" + ", menu.bi_menu_sort_sn" + ", menu.bi_menu_fm_yn"
//						+ " FROM  TSPORTAL.BIS_PTL_TB_USER_MNG_D UR " + " LEFT OUTER JOIN TSPORTAL.BIS_PTL_TB_GROUP_DEPT_MAPNG_R GDM " + " ON GDM.BI_DEPT_ID = UR.BI_DEPT_ID "
//						+ " AND UR.BI_UNITY_CUST_ID = ? " + " LEFT OUTER JOIN TSPORTAL.BIS_PTL_TB_MENU_GROUP_MAPNG_R  MGM " + " ON GDM.BI_GROUP_ID = MGM.BI_GROUP_ID " + " AND MGM.BI_AUTHOR_ID =  ? "
//						+ " LEFT OUTER JOIN TSPORTAL.BIS_PTL_TB_MENU_MNG_M menu " + " ON MGM.BI_PORTAL_MENU_ID = menu.BI_PORTAL_MENU_ID " + " WHERE menu.BI_PORTAL_MENU_PARENT_ID = ?  "
//						+ " ORDER BY menu.BI_MENU_SORT_SN ASC  ");

		query.setParameter(0, bi_unity_cust_id);
		query.setParameter(1, bi_unity_cust_id);
		query.setParameter(2, bi_portal_menu_parent_id);
		query.setParameter(3, bi_menu_type_yn);
		

		logger.info("menu: " + query.list());
		logger.info("menu: " + query.list().toString());

		return query.list();

	}
	
	
	@Value("#{sqlquery['menu.getSearchReportList']}") private String query7;
	@SuppressWarnings("unchecked")
	public List getSearchReportList(String bi_unity_cust_id, String bi_menu_type_yn, String keyword) throws DataAccessException {

		logger.debug("findBoardList ");

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query7);

		query.setParameter(0, bi_unity_cust_id);
		query.setParameter(1, bi_unity_cust_id);
		query.setParameter(2, bi_menu_type_yn);
		query.setParameter(3, "%"+keyword+"%");
		query.setParameter(4, "%"+keyword+"%");
		query.setParameter(5, "%"+keyword+"%");
		
		logger.info("menu: " + query.list());
		logger.info("menu: " + query.list().toString());

		return query.list();

	}

	@Value("#{sqlquery['menu.getSearchReportCount']}") private String query8;
	@SuppressWarnings("unchecked")
	public int getSearchReportCount(String bi_unity_cust_id, String bi_menu_type_yn, String keyword) throws DataAccessException {

		logger.debug("findBoardList ");

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query8);

		query.setParameter(0, bi_unity_cust_id);
		query.setParameter(1, bi_unity_cust_id);
		query.setParameter(2, bi_menu_type_yn);
		query.setParameter(3, "%"+keyword+"%");
		query.setParameter(4, "%"+keyword+"%");
		query.setParameter(5, "%"+keyword+"%");
		
		logger.info("menu: " + query.list());
		logger.info("menu: " + query.list().toString());

		return query.list().size();

	}
	@Value("#{sqlquery['menu.getRoleMenuCnt']}") private String query9;
	@SuppressWarnings("unchecked")
	public int getRoleMenuCnt(String bi_user_id, String bi_portal_menu_parent_id, String roleType) throws DataAccessException {

		logger.debug("findBoardList ");

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query9);

		query.setParameter(0, bi_user_id);
		query.setParameter(1, roleType);
		query.setParameter(2, bi_portal_menu_parent_id);

		return query.list().size();

	}

	@SuppressWarnings("unchecked")
	public List<Menu> promptMenuList(String bi_inqire_yn) throws DataAccessException {
		logger.debug("findBoardList ");

		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery(" From Menu menu where menu.bi_inqire_yn = ? ");
		query.setParameter(0, bi_inqire_yn);

		return query.list();

	}
	
	
	@SuppressWarnings("unchecked")
	public List<Menu> getBatchMenuList(String bi_menu_type_yn, String bi_menu_fm_yn) throws DataAccessException {
		logger.debug("findBoardList ");

		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery(" From Menu menu where menu.bi_menu_type_yn = ? and menu.bi_menu_fm_yn = ? order by menu.bi_portal_menu_parent_id asc,  menu.bi_menu_sort_sn asc, menu.bi_portal_menu_id asc");
		query.setParameter(0, bi_menu_type_yn);
		query.setParameter(1, bi_menu_fm_yn);

		return query.list();

	}
	
	@Value("#{sqlquery['menu.getSiteMap']}") private String query10;
	@SuppressWarnings("unchecked")
	public List getSiteMap(String bi_portal_menu_id) throws DataAccessException {

		logger.debug("findBoardList ");
		//oracle

		Query query = null;
		
		/*
		query = sessionFactory.getCurrentSession().createSQLQuery(
		    "   SELECT "
		   +"  t1.bi_portal_menu_id"
           +" ,t1.bi_portal_menu_parent_id" 
           +" ,t1.bi_menu_nm"
           +" ,t1.bi_menu_url_addr"
           +" ,t1.bi_menu_sort_sn"
           +" FROM TSPORTAL.BIS_PTL_TB_MENU_MNG_M t1"
           +" LEFT JOIN TSPORTAL.BIS_PTL_TB_MENU_MNG_M t2 ON t2.bi_portal_menu_id = t1.bi_portal_menu_parent_id"
           +" START WITH t1.bi_portal_menu_id = ?"
           +" CONNECT BY PRIOR t1.bi_portal_menu_id  = t1.bi_portal_menu_parent_id ");
		*/
		
		//db2
		
		query = sessionFactory.getCurrentSession().createSQLQuery(query10);
	        
		
		
		query.setParameter(0, bi_portal_menu_id);
		return query.list();

	}
	
	@Value("#{sqlquery['menu.deleteInit']}") private String query11;
	@SuppressWarnings("unchecked")
	public int deleteInit(String initYn) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query11);	
		query.setParameter(0, initYn);
		query.executeUpdate();
		
	
		return 1;
	}

	
	@SuppressWarnings("unchecked")
	public Menu getLastMenuId() throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From Menu menu order by cast(menu.bi_portal_menu_id AS int) desc");
		Menu menu = (Menu) query.list().get(0);
		logger.info("query.list" + query.list().toString());

		return menu;

	}
	
	
	
	@Value("#{sqlquery['menu.getIndexSearchList']}") private String query12;
	@SuppressWarnings("unchecked")
	public List getIndexSearch(String bi_unity_cust_id, String bi_menu_type_yn) throws DataAccessException {

		logger.debug("findBoardList ");

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query12);

		query.setParameter(0, bi_unity_cust_id);
		query.setParameter(1, bi_unity_cust_id);
		query.setParameter(2, bi_menu_type_yn);
		
		logger.info("menu: " + query.list());
		logger.info("menu: " + query.list().toString());

		return query.list();

	}

	
}


