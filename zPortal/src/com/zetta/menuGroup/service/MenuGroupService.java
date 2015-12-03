package com.zetta.menuGroup.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.menuGroup.model.MenuGroup;
import com.zetta.menuGroup.model.MenuGroupMapp;
import com.zetta.menuGroup.model.MenuUserGroupMapp;

public interface MenuGroupService {	
	
	
	   public MenuGroup getById(String bi_menu_group_id);

	   public MenuGroup save(MenuGroup menuGroup) ;
	    
	   public MenuGroup update(MenuGroup menuGroup);

	   public int remove(String bi_menuGroup_id);

	   public MenuGroup findList(String bi_menu_group_id);
	   
	   public List<MenuGroup> getMenuGroupList();
	    
	   public List findAllList(int currentPage, int countPerPage,int searchTitle, String searchContent);

	   public int getTotalCount(int searchTitle, String searchContent);
	    
	   public MenuGroupMapp menuGroupMappSave(MenuGroupMapp menuGroupMapp);

	   public int getMenuGroupMappDelete(String bi_group_menu_id);
	   
	   public int getMenuGroupMappDelete(String bi_menu_group_id, String bi_portal_menu_id);
	    
	   public int getMenuGroupMappCnt(String bi_menu_group_id);
	   
	   public int getMenuGroupMappCnt(String bi_menu_group_id,String bi_portal_menu_id);
	   
	   public MenuGroup getMenuGroupNm(String bi_menu_group_nm);
	   
	   public List<MenuGroupMapp> getMenuGroupMappList(String bi_menu_group_id);	   
	   
	   public MenuUserGroupMapp menuUserGroupMappSave(MenuUserGroupMapp menuUserGroupMapp);

	   public int getMenuUserGroupMappDelete(String bi_group_menu_id);
	   
	   public int getMenuUserGroupMappDelete(String bi_group_menu_id, String bi_group_id);
	    
	   public int getMenuUserGroupMappCnt(String bi_menu_group_id);
	   
	   public int getMenuUserGroupMappCnt(String bi_menu_group_id, String bi_group_id);
	   
	   public List<MenuUserGroupMapp> getMenuUserGroupMappList(String bi_menu_group_id);	   
	   
	   public int deleteAll();
	   
	   public int deleteMenuGroupMappAll();
	   
	   public int deleteMenuGroupUserGroupMappAll();
	   
	   
	   public int getMenuGroupMappMenuCnt(String bi_portal_menu_id);   
	   
	   
	   public int getMenuGroupMappMenuDelete(String bi_portal_menu_id);
	   
	   public List<MenuGroup> getMenuGroupMappStatus();
	   
	   public int getMenuGroupInitDelete(String initYn);
	}
