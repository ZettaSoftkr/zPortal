package com.zetta.group.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.group.model.DeptGroup;
import com.zetta.group.model.Group;
import com.zetta.group.model.UserGroup;



public interface GroupService {	
	
	
	   public Group getById(String bi_group_id);

	   public Group save(Group group) ;
	    
	   public Group update(Group group);

	   public int remove(String bi_group_id);

	   public Group findList(String bi_group_id);
	    
	   public Group findWithView(String bi_group_id);
	    
	   public List findAllList(int currentPage, int countPerPage,int searchTitle, String searchContent);

	   public int getTotalCount(int searchTitle, String searchContent);
	   
	   public List<Group> getGroupList();
	   
	   public int getMenuGroupCnt(String bi_portal_menu_id);
	   
	   public List getMenuGroupList(String bi_portal_menu_id);
	   
	 
	   
	   public int getMenuGroupDelete(String bi_portal_menu_id); 
	
	   public List getMenuGroupMapngList(String bi_portal_menu_id); 
	   
	   public List getMenuGroupRoleList(String bi_portal_menu_id); 
	   
	   public Group getGroupNm(String bi_group_nm);
	   
	 
	   
	   public Group getGroupCode(String bi_group_nm);
	   
	   /*******************사용자 그룹 조직맵핑**********************/
	   public DeptGroup groupDeptSave(DeptGroup deptGroup);
	   
	   public DeptGroup deptGroupSave(DeptGroup deptGroup);
	   
	   /*
		 * 관리실 > 사용자 관리 >사용자 그룹관리  (select count)
		 * @param : groupId (그룹ID) 
		 * @return : count(number)
		 * @return 
		 * @desc : 사용자 그룹- 조직 맵핑 갯수 
		 */
	   public int ugDeptMappCnt(String groupId);
	   
	   public List getDeptGroupList(String bi_gorup_id, String bi_deptNm);
	   
	   /*
		 * 관리실 > 사용자 관리 >사용자 그룹관리  (delete)
		 * @param : groupId (그룹ID) 
		 * @return : 
		 * @return : 
		 * @desc : 사용자 그룹- 조직 맵핑 삭제 
		 */
	   public int ugDeptMappDelete(String groupId); 
	
	   public List getDeptGroupMapngList(String groupId); 
	   
	   /***************** 사용자그룹 사용자 맵핑 ************************/
	   public List<UserGroup> getUserGroupList(String bi_dept_id, String bi_group_id);
	   
	   public UserGroup userGroupSave(UserGroup userGroup);	   
	   
	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 사용자그룹 -사용자 맵핑 삭제
	 * @param : groupId[사용자그룹IDugUserMappDelete : 1-ok
	 * @return :  
	 * 사용자그룹 사용자 맵핑 삭제
	 */
	   public int ugUserMappDelete(String groupId);	
	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 사용자그룹 -사용자 맵핑 현황
	 * @param : groupId[사용자그룹ID]
	 * @return : List
	 * @return :  
	 * 전체 사용자그룹 -사용자 맵핑 현황
	 */
	   public List getUgUserMapngData(String groupId); 
	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 사용자그룹 -사용자 맵핑 갯수
	 * @param : groupId[사용자그룹ID]
	 * @return : count[전체갯수]
	 * @return :  
	 * 사용자그룹 사용자 맵핑 갯수 
	 */
	   public int ugUserMappCnt(String groupId);
	   

	   public List getUserGroupListData(String bi_menu_group_id);
	   

	   public int deleteAll(); 	

		
	   public int deleteUgDeptMappAll();	

		
	   public int deleteUgUserMappAll(); 
		
	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 맵핑 현황
	 * @param :
	 * @return : list(자식 아이디, 부모ID, 명 )
	 * @return : json data 
	 * 사용자 그룹 -조직-사용자 전체 맵핑현황 보기 
	 */
	   public List getMenuGroupMappAllStatus();
	    
	}
