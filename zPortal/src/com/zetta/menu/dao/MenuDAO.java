package com.zetta.menu.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.menu.model.Menu;


public interface MenuDAO {

	
      	public Menu getById(String bi_portal_menu_id) throws DataAccessException;
      	
	 	public Menu insert(Menu menu) throws DataAccessException;

	    public Menu update(Menu menu) throws DataAccessException;

	    public int delete(String bi_portal_menu_id) throws DataAccessException;	
	    
	    public int deleteAll() throws DataAccessException;
	    
	    public int childDelete(String bi_portal_menu_id) throws DataAccessException;

	    public Menu findList(String bi_portal_menu_id) throws DataAccessException;
	    
	    public List findAllList()  throws DataAccessException;
	    
	    public List<Menu> findSearchList(String bi_search_keyword)  throws DataAccessException;
	    
	    public List findChildList(String bi_portal_menu_parent_id)  throws DataAccessException;
	    
	    public int getChildCount(String getChildCount) throws DataAccessException;
	    
	    public Menu getChildMaxId(String bi_portal_menu_parent_id) throws DataAccessException;
	    
	    public int getTotalNo() throws DataAccessException;
	    
	    public int getMenuCnt(String bi_menu_nm, String bi_portal_menu_parent_id)  throws DataAccessException;
	    
	    public List<Menu> getMenuList(String bi_portal_menu_parent_id) throws DataAccessException;
	    
	    public List<Menu> leftMenuList(String bi_portal_menu_parent_id) throws DataAccessException;
	    
	    public List getRoleMenuList(String bi_user_id, String bi_parent_id)  throws DataAccessException;	    
	   
		public List getRoleReportList(String bi_unity_cust_id, String bi_portal_menu_parent_id, String bi_menu_type_yn)  throws DataAccessException;
	    
	    public int getRoleMenuCnt(String bi_user_id, String bi_parent_id, String roleType)  throws DataAccessException;
	   
	    public List<Menu> promptMenuList(String bi_inqire_yn) throws DataAccessException;
	    
	    public Menu getParentMenuNm(String bi_menu_nm, String bi_portal_menu_parent_id)  throws DataAccessException;
	    
	    public Menu getMenuNm(String bi_menu_nm) throws DataAccessException;
	    
	    public int getMenuTypeCnt(String bi_menu_type_yn)throws DataAccessException;
	    
	    public int deleteMenuAll(String bi_menu_type_yn) throws DataAccessException;
	    
	    public List getSearchReportList(String bi_unity_cust_id, String bi_menu_type_yn, String keyword) throws DataAccessException;
	    
	    public int getSearchReportCount(String bi_unity_cust_id, String bi_menu_type_yn, String keyword) throws DataAccessException;
	    
	    public List<Menu> getBatchMenuList(String bi_menu_type_yn, String bi_menu_fm_yn) throws DataAccessException;
	    
	    public List getSiteMap(String bi_portal_menu_id) throws DataAccessException;
	    
	    public int getRoleQlikViewList(String bi_unity_cust_id, String bi_portal_menu_id) throws DataAccessException;
	    
	    public int deleteInit(String initYn) throws DataAccessException;
	    
	    public Menu getLastMenuId() throws DataAccessException;
	    
	    public List getIndexSearch(String bi_unity_cust_id, String bi_menu_type_yn) throws DataAccessException;
}