package com.zetta.menuGroup.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

















import com.zetta.menuGroup.model.MenuGroup;
import com.zetta.menuGroup.model.MenuGroupMapp;
import com.zetta.menuGroup.model.MenuUserGroupMapp;




public interface MenuGroupDAO {

	
      	public MenuGroup getById(String bi_menu_group_id) throws DataAccessException;
      	
	 	public MenuGroup insert(MenuGroup menuGroup) throws DataAccessException;

	    public MenuGroup update(MenuGroup menuGroup) throws DataAccessException;

	    public int delete(String bi_menuGroup_id) throws DataAccessException;
	    
	    public List<MenuGroup> getMenuGroupList() throws DataAccessException;
	    
	    public List findAllList(int startRow, int endRow, int searchTitle, String searchContent)  throws DataAccessException;
	    
	    public int getTotalNo(int searchTitle, String searchContent) throws DataAccessException;
	    
	    public MenuGroupMapp menuGroupMappInsert(MenuGroupMapp menuGroupMapp) throws DataAccessException;
	    
	    public int getMenuGroupMappDelete(String bi_menu_group_id) throws DataAccessException;
	    
	    public int getMenuGroupMappDelete(String bi_menu_group_id, String bi_portal_menu_id) throws DataAccessException;
	    
	    public int getMenuGroupMappCnt(String bi_menu_group_id) throws DataAccessException;
	    
	    public int getMenuGroupMappCnt(String bi_menu_group_id,String bi_portal_menu_id) throws DataAccessException;	    
	    
	    public MenuGroup getMenuGroupNm(String bi_menu_group_nm) throws DataAccessException;
	    
	    
	    
	    
	    public List<MenuGroupMapp> getMenuGroupMappList(String bi_menu_group_id) throws DataAccessException;	    
	    
	    public MenuUserGroupMapp menuUserGroupMappInsert(MenuUserGroupMapp menuUserGroupMapp) throws DataAccessException;
	    
	    public int getMenuUserGroupMappDelete(String bi_menu_group_id) throws DataAccessException;
	    
	    public int getMenuUserGroupMappDelete(String bi_menu_group_id, String bi_group_id) throws DataAccessException;
	    
	    public int getMenuUserGroupMappCnt(String bi_menu_group_id) throws DataAccessException;
	    
	    public int getMenuUserGroupMappCnt(String bi_menu_group_id , String bi_group_id) throws DataAccessException;
	    
	    public List<MenuUserGroupMapp> getMenuUserGroupMappList(String bi_menu_group_id) throws DataAccessException;
	    
	    
	    public int deleteAll() throws DataAccessException;
	    
	    public int deleteMenuGroupMappAll() throws DataAccessException;
	    
	    public int deleteMenuGroupUserGroupMappAll() throws DataAccessException;
	    
	    
	    public int getMenuGroupMappMenuCnt(String bi_portal_menu_id) throws DataAccessException;
	    
	    public int getMenuGroupMappMenuDelete(String bi_portal_menu_id) throws DataAccessException; 
	    
	    public List<MenuGroup> getMenuGroupMappStatus() throws DataAccessException;
	    
	    public int getMenuGroupInitDelete(String initYn) throws DataAccessException;
	    
}