package com.zetta.menuGroup.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.board.model.Board;
import com.zetta.common.DateTimeUtil;
import com.zetta.menuGroup.dao.MenuGroupDAO;
import com.zetta.menuGroup.model.MenuGroup;
import com.zetta.menuGroup.model.MenuGroupMapp;
import com.zetta.menuGroup.model.MenuUserGroupMapp;

@Service
@Transactional
public class MenuGroupServiceImpl implements MenuGroupService {

	public Logger logger = Logger.getLogger(getClass());

	@Autowired
	private MenuGroupDAO menuGroupDao;

	@Override
	public MenuGroup getById(String bi_menu_group_id) {

		return menuGroupDao.getById(bi_menu_group_id);

	}

	@Override
	public MenuGroup save(MenuGroup menuGroup) {

		menuGroup.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		menuGroup.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));

		MenuGroup result = menuGroupDao.insert(menuGroup);
		// 파일 갯수 위한 메소드

		return result;

	}

	@Override
	public MenuGroup update(MenuGroup menuGroup) {

		MenuGroup getMenuGroup = this.getById(menuGroup.getBi_menu_group_id());
		menuGroup.setBi_reg_dt(getMenuGroup.getBi_reg_dt());
		menuGroup.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		MenuGroup result = menuGroupDao.update(menuGroup);

		return result;

	}

	@Override
	public int remove(String bi_menu_group_id) {

		return menuGroupDao.delete(bi_menu_group_id);
	}

	@Override
	public MenuGroup findList(String bi_menu_group_id) {

		MenuGroup menuGroup = menuGroupDao.getById(bi_menu_group_id);

		return menuGroup;

	}

	public List<MenuGroup> getMenuGroupList() {

		return menuGroupDao.getMenuGroupList();

	}

	@Override
	public List findAllList(int startRow, int endRow, int searchTitle, String searchContent) {

		return menuGroupDao.findAllList(startRow, endRow, searchTitle, searchContent);
	}

	@Override
	public int getTotalCount(int searchTitle, String searchContent) {
		return menuGroupDao.getTotalNo(searchTitle, searchContent);
	}

	public MenuGroupMapp menuGroupMappSave(MenuGroupMapp menuGroupMapp) {

		menuGroupMapp.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		menuGroupMapp.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));

		MenuGroupMapp result = menuGroupDao.menuGroupMappInsert(menuGroupMapp);
		// 파일 갯수 위한 메소드

		return result;

	}

	public int getMenuGroupMappDelete(String bi_menu_group_id) {

		int result = menuGroupDao.getMenuGroupMappDelete(bi_menu_group_id);
		// 파일 갯수 위한 메소드

		return result;
	}
	
	
	 public int getMenuGroupMappDelete(String bi_menu_group_id, String bi_portal_menu_id) {
		 
		 int result = menuGroupDao.getMenuGroupMappDelete(bi_menu_group_id,bi_portal_menu_id);
			// 파일 갯수 위한 메소드
		 return result;
		 
	 }

	public int getMenuGroupMappCnt(String bi_menu_group_id) {

		int result = menuGroupDao.getMenuGroupMappCnt(bi_menu_group_id);
		return result;

	}

	public int getMenuGroupMappCnt(String bi_menu_group_id, String bi_portal_menu_id) {

		int result = menuGroupDao.getMenuGroupMappCnt(bi_menu_group_id, bi_portal_menu_id);
		return result;
	}

	public MenuGroup getMenuGroupNm(String bi_menu_group_nm) {

		return menuGroupDao.getMenuGroupNm(bi_menu_group_nm);

	}

	public List<MenuGroupMapp> getMenuGroupMappList(String bi_menu_group_id) {

		List<MenuGroupMapp> result = menuGroupDao.getMenuGroupMappList(bi_menu_group_id);
		return result;
	}

	public MenuUserGroupMapp menuUserGroupMappSave(MenuUserGroupMapp menuUserGroupMapp) {

		menuUserGroupMapp.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		menuUserGroupMapp.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));

		MenuUserGroupMapp result = menuGroupDao.menuUserGroupMappInsert(menuUserGroupMapp);
		// 파일 갯수 위한 메소드

		return result;

	}

	public int getMenuUserGroupMappDelete(String bi_menu_group_id) {

		int result = menuGroupDao.getMenuUserGroupMappDelete(bi_menu_group_id);
		// 파일 갯수 위한 메소드

		return result;
	}

	public int getMenuUserGroupMappDelete(String bi_menu_group_id, String bi_group_id) {

		int result = menuGroupDao.getMenuUserGroupMappDelete(bi_menu_group_id, bi_group_id);
		// 파일 갯수 위한 메소드

		return result;
	}

	public int getMenuUserGroupMappCnt(String bi_menu_group_id) {

		int result = menuGroupDao.getMenuUserGroupMappCnt(bi_menu_group_id);
		return result;

	}

	public int getMenuUserGroupMappCnt(String bi_menu_group_id, String bi_group_id) {

		int result = menuGroupDao.getMenuUserGroupMappCnt(bi_menu_group_id, bi_group_id);
		return result;

	}

	public List<MenuUserGroupMapp> getMenuUserGroupMappList(String bi_menu_group_id) {

		List<MenuUserGroupMapp> result = menuGroupDao.getMenuUserGroupMappList(bi_menu_group_id);
		return result;
	}

	public int deleteAll() {

		return menuGroupDao.deleteAll();
	}

	public int deleteMenuGroupMappAll() {

		return menuGroupDao.deleteMenuGroupMappAll();
	}
	
	public int deleteMenuGroupUserGroupMappAll(){
		
		return menuGroupDao.deleteMenuGroupUserGroupMappAll();
	}
	
	public int getMenuGroupMappMenuCnt(String bi_portal_menu_id){
		
		return menuGroupDao.getMenuGroupMappMenuCnt(bi_portal_menu_id);
	}
	
	public int getMenuGroupMappMenuDelete(String bi_portal_menu_id){
		
		return menuGroupDao.getMenuGroupMappMenuDelete(bi_portal_menu_id);
	}
	
	 public List<MenuGroup> getMenuGroupMappStatus(){
		 
		 return menuGroupDao.getMenuGroupMappStatus();
	 }
	 
	 public int getMenuGroupInitDelete(String initYn){
		 
		 return menuGroupDao.getMenuGroupInitDelete(initYn);
	 }
}
