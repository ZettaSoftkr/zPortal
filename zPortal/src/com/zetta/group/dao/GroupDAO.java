package com.zetta.group.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;

import com.zetta.group.model.DeptGroup;
import com.zetta.group.model.Group;
import com.zetta.group.model.UserGroup;

public interface GroupDAO {

	public Group getById(String bi_group_id) throws DataAccessException;

	public Group insert(Group group) throws DataAccessException;

	public Group update(Group group) throws DataAccessException;

	public int delete(String bi_group_id) throws DataAccessException;

	public Group findList(String bi_group_id) throws DataAccessException;

	public List findAllList(int startRow, int endRow, int searchTitle, String searchContent) throws DataAccessException;
	
	
	
	
	public Group getGroupNm(String bi_group_nm);
	
	
	public int getTotalNo(int searchTitle, String searchContent) throws DataAccessException;

	public List<Group> getGroupList() throws DataAccessException;

	public int getMenuGroupCnt(String bi_portal_menu_id) throws DataAccessException;

	public List getMenuGroupList(String bi_portal_menu_id) throws DataAccessException;

	public int getMenuGroupDelete(String bi_portal_menu_id) throws DataAccessException;

	public List getMenuGroupMapngList(String bi_portal_menu_id) throws DataAccessException;

	public List getMenuGroupRoleList(String bi_portal_menu_id) throws DataAccessException;



	public Group getGroupCode(String bi_group_nm) throws DataAccessException;

	 /*
	 * 관리실 > 사용자 관리 >사용자 그룹관리  (select count)
	 * @param : groupId (그룹ID) 
	 * @return : count(number)
	 * @return 
	 * @desc : 사용자 그룹- 조직 맵핑 갯수 
	 */
	public int ugDeptMappCnt(String groupId) throws DataAccessException;

	public List getDeptGroupList(String bi_gorup_id, String bi_deptNm) throws DataAccessException;

	/*
	 * 관리실 > 사용자 관리 >사용자 그룹관리  (delete)
	 * @param : groupId (그룹ID) 
	 * @return : 
	 * @return : 
	 * @desc : 사용자 그룹- 조직 맵핑 삭제 
	 */
	public int ugDeptMappDelete(String groupId) throws DataAccessException;

	public List getDeptGroupMapngList(String bi_gorup_id) throws DataAccessException;

	public DeptGroup groupDeptInsert(DeptGroup deptGroup) throws DataAccessException;

	public DeptGroup deptGroupInsert(DeptGroup deptGroup) throws DataAccessException;

	// ***** 사용자 그룹 조직 맵핑 *******************/

	public List<UserGroup> getUserGroupList(String bi_dept_id, String groupId) throws DataAccessException;

	public UserGroup userDeptInsert(UserGroup userGroup) throws DataAccessException;
	
	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 사용자그룹 -사용자 맵핑 삭제
	 * @param : groupId[사용자그룹ID]
	 * @return : 1-ok
	 * @return :  
	 * 사용자그룹 사용자 맵핑 삭제
	 */
	public int ugUserMappDelete(String groupId) throws DataAccessException;
	
	
	
	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 사용자그룹 -사용자 맵핑 현황
	 * @param : groupId[사용자그룹ID]
	 * @return : List
	 * @return :  
	 * 전체 사용자그룹 -사용자 맵핑 현황
	 */

	public List getUgUserMapngData(String groupId) throws DataAccessException;
	
	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 사용자그룹 -사용자 맵핑 갯수
	 * @param : groupId[사용자그룹ID]
	 * @return : count[전체갯수]
	 * @return :  
	 * 사용자그룹 사용자 맵핑 갯수 
	 */
	
	public int ugUserMappCnt(String groupId) throws DataAccessException;

	// ***** 사용자그룹 사용자 맵핑 *******************/

	/***************** 사용자 그룹 메뉴 그룹 사용자 맵핑 *******************/
	public List getUserGroupListData(String bi_menu_group_id);
	/**********************************************************/
	
	
	
/***************** 사용자그룹 전체 삭제 *******************/		
	public int deleteAll() throws DataAccessException; 	
/***************** 사용자그룹  조직 맵핑 삭제 전체 삭제 *******************/	
	
	public int deleteUgDeptMappAll() throws DataAccessException;	
/***************** 사용자그룹  사용자 맵핑 삭제 전체 삭제 *******************/	
	
	public int deleteUgUserMappAll() throws DataAccessException; 
	
		
	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 맵핑 현황
	 * @param :
	 * @return : list(자식 아이디, 부모ID, 명 )
	 * @return : json data 
	 * 사용자 그룹 -조직-사용자 전체 맵핑현황 보기 
	 */
	public List getMenuGroupMappAllStatus() throws DataAccessException;



}