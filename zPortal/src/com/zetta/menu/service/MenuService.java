package com.zetta.menu.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestParam;

import com.zetta.menu.model.Menu;

public interface MenuService {

	public Menu getById(String bi_portal_menu_id);

	public Menu save(Menu menu);

	public Menu update(Menu menu);

	public int remove(String bi_portal_menu_id);
	
	public int deleteAll();

	public int childRemove(String bi_portal_menu_id);

	public Menu findList(String bi_portal_menu_id);

	public Menu findWithView(String bi_portal_menu_id);

	public List findAllList();
	
	public List<Menu> findSearchList(String bi_search_keyword);

	public List<Menu> findChildList(String bi_parent_id);

	public int getChildCount(String bi_parent_id);
	
	public Menu getChildMaxId(String bi_portal_menu_parent_id);

	public int getTotalCount();
	
	public int getMenuCnt(String bi_menu_nm, String bi_portal_menu_parent_id);

	public List<Menu> getMenuList(String bi_portal_menu_parent_id);

	public List<Menu> leftMenuList(String bi_portal_menu_parent_id);

	public List getRoleMenuList(String bi_user_id, String bi_portal_menu_parent_id);
	
	public List getRoleReportList(String bi_unity_cust_id, String bi_portal_menu_parent_id, String bi_menu_type_yn);

	public int getRoleMenuCnt(String bi_user_id, String bi_portal_menu_parent_id,	String roleType);

	public List<Menu> promptMenuList(String bi_inqire_yn);
	
	public String getPromptMenuUrl(String bi_portal_menu_id, String bi_menu_nm, int deptLev);
	
	public Menu getMenuNm(String bi_menu_nm);
	
	public Menu getParentMenuNm(String bi_menu_nm, String bi_portal_menu_parent_id);
	
	public Menu getParentMenuReturn(String[] bi_menu_nm, String bi_portal_menu_parent_id, int lastDept, int dpet);
	
	public int deleteMenuAll(String bi_menu_type_yn);
	
	public int getMenuTypeCnt(String bi_menu_type_yn);
	
	public List getSearchReportList(String bi_unity_cust_id, String bi_menu_type_yn, String keyword);
	
	public int getSearchReportCount(String bi_unity_cust_id, String bi_menu_type_yn, String keyword);
	
	public List<Menu> getBatchMenuList(String bi_menu_type_yn, String bi_menu_fm_yn);
	
	public List getSiteMap(String bi_portal_menu_id);
	
	public String getMenuTree(String bi_portal_menu_id, String menuTree, int deptLev); 

	
	public int getRoleQlikViewList(String bi_unity_cust_id, String bi_portal_menu_id);
	
	public int deleteInit(String initYn);
	
	public String getExcelMenuId(List excelRows, int rowNum, String cateType);
	 
	
	public Menu getLastMenuId();
	
	public List getIndexSearch(String bi_unity_cust_id, String bi_menu_type_yn);
	
}
