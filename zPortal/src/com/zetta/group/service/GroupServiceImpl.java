package com.zetta.group.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.common.DateTimeUtil;
import com.zetta.group.dao.GroupDAO;
import com.zetta.group.model.DeptGroup;
import com.zetta.group.model.Group;
import com.zetta.group.model.UserGroup;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

	public Logger logger = Logger.getLogger(getClass());

	@Autowired
	private GroupDAO groupDao;

	@Override
	public Group getById(String bi_group_id) {

		return groupDao.getById(bi_group_id);

	}

	@Override
	public Group save(Group group) {

		group.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		group.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));

		Group result = groupDao.insert(group);
		// 파일 갯수 위한 메소드

		return result;

	}

	@Override
	public Group update(Group group) {

		Group getGroup = this.getById(group.getBi_group_id());
		group.setBi_reg_dt(getGroup.getBi_reg_dt());
		group.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		Group result = groupDao.update(group);

		return result;

	}

	@Override
	public int remove(String bi_group_id) {

		return groupDao.delete(bi_group_id);
	}

	@Override
	public Group findList(String bi_group_id) {

		Group group = groupDao.findList(bi_group_id);

		return group;
	}

	@Override
	public Group findWithView(String bi_group_id) {

		return findList(bi_group_id);

	}
	
	
	public Group getGroupNm(String bi_group_nm) {
		
		return groupDao.getGroupNm(bi_group_nm);
	}

	@Override
	public List findAllList(int startRow, int endRow, int searchTitle, String searchContent) {

		return groupDao.findAllList(startRow, endRow, searchTitle, searchContent);
	}

	@Override
	public int getTotalCount(int searchTitle, String searchContent) {
		return groupDao.getTotalNo(searchTitle, searchContent);
	}

	@Override
	public List<Group> getGroupList() {

		return groupDao.getGroupList();
		
	}

	@Override
	public int getMenuGroupCnt(String bi_portal_menu_id) {

		return groupDao.getMenuGroupCnt(bi_portal_menu_id);
	}

	@Override
	public List getMenuGroupList(String bi_portal_menu_id) {

		return groupDao.getMenuGroupList(bi_portal_menu_id);
	}


	@Override
	public int getMenuGroupDelete(String bi_portal_menu_id) {

		return groupDao.getMenuGroupDelete(bi_portal_menu_id);

	}

	@Override
	public List getMenuGroupMapngList(String bi_portal_menu_id) {

		return groupDao.getMenuGroupMapngList(bi_portal_menu_id);
	}

	@Override
	public List getMenuGroupRoleList(String bi_portal_menu_id) {

		return groupDao.getMenuGroupRoleList(bi_portal_menu_id);
	}



	public Group getGroupCode(String bi_group_nm) {

		return groupDao.getGroupCode(bi_group_nm);
	}

	/************************ 사용자 그룹 조직맵핑 ************************/
	@Override
	public DeptGroup groupDeptSave(DeptGroup deptGroup) {

		DeptGroup result = groupDao.groupDeptInsert(deptGroup);
		return result;
	}

	@Override
	public DeptGroup deptGroupSave(DeptGroup deptGroup) {

		DeptGroup result = groupDao.deptGroupInsert(deptGroup);
		// 파일 갯수 위한 메소드

		return result;
	}

 /*
	 * 관리실 > 사용자 관리 >사용자 그룹관리  (select count)
	 * @param : groupId (그룹ID) 
	 * @return : count(number)
	 * @return 
	 * @desc : 사용자 그룹- 조직 맵핑 갯수 
	 */
	
	@Override
	public int ugDeptMappCnt(String groupId) {

		return groupDao.ugDeptMappCnt(groupId);
	}

	@Override
	public List getDeptGroupList(String bi_gorup_id, String bi_deptNm) {

		return groupDao.getDeptGroupList(bi_gorup_id, bi_deptNm);
	}
	/*
	 * 관리실 > 사용자 관리 >사용자 그룹관리  (delete)
	 * @param : groupId (그룹ID) 
	 * @return : 
	 * @return : 
	 * @desc : 사용자 그룹- 조직 맵핑 삭제 
	 */
	@Override
	public int ugDeptMappDelete(String groupId) {

		return groupDao.ugDeptMappDelete(groupId);

	}

	@Override
	public List getDeptGroupMapngList(String bi_gorup_id) {

		return groupDao.getDeptGroupMapngList(bi_gorup_id);
	}

	/************************ 사용자 그룹 조직맵핑 ************************/

	/***************** 사용자그룹 사용자 맵핑 ************************/
	@Override
	 public List<UserGroup> getUserGroupList(String bi_dept_id,String bi_group_id){
		 
		 return groupDao.getUserGroupList(bi_dept_id, bi_group_id);
	 }
	
	 @Override
	public UserGroup userGroupSave(UserGroup userGroup) {

		return groupDao.userDeptInsert(userGroup);

	}
	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 사용자그룹 -사용자 맵핑 삭제
	 * @param : groupId[사용자그룹IDugUserMappDelete : 1-ok
	 * @return :  
	 * 사용자그룹 사용자 맵핑 삭제
	 */
		
	@Override
	public int ugUserMappDelete(String groupId) {

		return groupDao.ugUserMappDelete(groupId);

	}
	
	
	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 사용자그룹 -사용자 맵핑 현황
	 * @param : groupId[사용자그룹ID]
	 * @return : List
	 * @return :  
	 * 전체 사용자그룹 -사용자 맵핑 현황
	 */

	@Override
	public List getUgUserMapngData(String groupId) {

		return groupDao.getUgUserMapngData(groupId);
	}
	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 사용자그룹 -사용자 맵핑 갯수
	 * @param : groupId[사용자그룹ID]
	 * @return : count[전체갯수]
	 * @return :  
	 * 사용자그룹 사용자 맵핑 갯수 
	 */
	
	public int ugUserMappCnt(String groupId){
		
		return groupDao.ugUserMappCnt(groupId);
		
	}
	/***************** 사용자그룹 사용자 맵핑 ************************/

	
   /***************** 사용자 그룹 메뉴 그룹 사용자 맵핑 *******************/
   public List getUserGroupListData(String bi_menu_group_id){
	   
		return groupDao.getUserGroupListData(bi_menu_group_id); 
   }
   /**********************************************************/
   
   /***************** 사용자그룹 전체 삭제 *******************/		
	public int deleteAll(){
		
		return groupDao.deleteAll(); 
	}
/***************** 사용자그룹  조직 맵핑 삭제 전체 삭제 *******************/	
	
	public int deleteUgDeptMappAll(){
		
		return groupDao.deleteUgDeptMappAll(); 
	}	
/***************** 사용자그룹  사용자 맵핑 삭제 전체 삭제 *******************/	
	
	public int deleteUgUserMappAll(){
		
		return groupDao.deleteUgUserMappAll(); 
	} 
	
	
/*
 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 맵핑 현황
 * @param :
 * @return : list(자식 아이디, 부모ID, 명 )
 * @return : json data 
 * 사용자 그룹 -조직-사용자 전체 맵핑현황 보기 
 */
	@Override
	public List getMenuGroupMappAllStatus(){
		
		return groupDao.getMenuGroupMappAllStatus();
	}
    
}
