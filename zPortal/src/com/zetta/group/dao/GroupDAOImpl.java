package com.zetta.group.dao;

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

import com.zetta.group.model.DeptGroup;
import com.zetta.group.model.Group;
import com.zetta.group.model.UserGroup;

@Repository
@Transactional
public class GroupDAOImpl implements GroupDAO {

	private static Logger logger = Logger.getLogger(GroupDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public Group getById(String bi_group_id) throws DataAccessException {
		return (Group) sessionFactory.getCurrentSession().get(Group.class, bi_group_id);
	}

	@SuppressWarnings("unchecked")
	public Group insert(Group group) throws DataAccessException {
		sessionFactory.getCurrentSession().save(group);
		return group;
	}

	@SuppressWarnings("unchecked")
	public Group update(Group group) throws DataAccessException {

		sessionFactory.getCurrentSession().merge(group);

		return group;
	}

	@SuppressWarnings("unchecked")
	public int delete(String bi_group_id) throws DataAccessException {

		Group group = (Group) sessionFactory.getCurrentSession().get(Group.class, bi_group_id);

		sessionFactory.getCurrentSession().delete(group);

		return 1;
	}

	@SuppressWarnings("unchecked")
	public Group findList(String bi_group_id) throws DataAccessException {

		Group group = (Group) sessionFactory.getCurrentSession().get(Group.class, bi_group_id);

		if (group == null) {

			throw new ObjectRetrievalFailureException(Group.class, bi_group_id);

		}

		return group;

	}

	@SuppressWarnings("unchecked")
	public List<Group> findAllList(int startRow, int endRow, int searchTitle, String searchContent) throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		if (searchTitle == 1) {

			query = sessionFactory.getCurrentSession().createQuery(" From Group Group where group.bi_group_nm like ? ");
			query.setParameter(0, "%"+searchContent+"%");

		} else if (searchTitle == 2) {

			query = sessionFactory.getCurrentSession().createQuery(" From Group group where group.bi_group_id like ? ");
			query.setParameter(0, "%"+searchContent+"%");

		} else if (searchTitle == 3) {

			query = sessionFactory.getCurrentSession().createQuery(" From Group group where group.bi_group_nm like ? or group.bi_group_id like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			query.setParameter(1, "%"+searchContent+"%");

		} else {

			query = sessionFactory.getCurrentSession().createQuery(" From Group group order by group.bi_group_id desc");

		}

		// int start = (currentPage) * countPerPage;
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);

		logger.info("query.list" + query.list().toString());

		return query.list();

	}
	
	
	@SuppressWarnings("unchecked")
	public Group getGroupNm(String bi_group_nm) throws DataAccessException {
		logger.debug("findBoardList ");
		Query query = null;

		

	
		 query = sessionFactory.getCurrentSession().createQuery(" From Group group where group.bi_group_nm = ?");
		 query.setParameter(0, bi_group_nm);
	
	    Group group = (Group) query.list().get(0);

		logger.info("query.list" + query.list().toString());

		return group;

	}

	@SuppressWarnings("unchecked")
	public int getTotalNo(int searchTitle, String searchContent) throws DataAccessException {

		Query query = null;

		if (searchTitle == 1) {

			query = sessionFactory.getCurrentSession().createQuery(" From Group group where Group.bi_group_nm like ? ");
			query.setParameter(0, "%"+searchContent+"%");

		} else if (searchTitle == 2) {

			query = sessionFactory.getCurrentSession().createQuery(" From Group group where group.bi_group_id like ? ");
			query.setParameter(0, "%"+searchContent+"%");

		} else if (searchTitle == 3) {

			query = sessionFactory.getCurrentSession().createQuery(" From Group group where group.bi_group_nm like ? or group.bi_group_id like ? ");
			query.setParameter(0, "%"+searchContent+"%");
			query.setParameter(1, "%"+searchContent+"%");

		} else {

			query = sessionFactory.getCurrentSession().createQuery(" From Group group order by group.bi_sort_sn asc");

		}

		return query.list().size();

	}

	@SuppressWarnings("unchecked")
	public List<Group> getGroupList() throws DataAccessException {
		logger.debug("findBoardList ");

		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From Group group order by group.bi_sort_sn asc");

		return query.list();

	}

	@SuppressWarnings("unchecked")
	public List<Group> getGroupDeptList(String bi_group_id) throws DataAccessException {
		logger.debug("findBoardList ");

		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From Group group where group.bi_group_id = ? ");
		query.setParameter(0, bi_group_id);

		return query.list();

	}

	@Value("#{sqlquery['group.getMenuGroupCnt']}") private String query1;
	
	@SuppressWarnings("unchecked")
	public int getMenuGroupCnt(String bi_portal_menu_id) throws DataAccessException {
		logger.debug("findBoardList ");

		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query1);

		query.setParameter(0, bi_portal_menu_id);
		return query.list().size();

	}
	@Value("#{sqlquery['group.getMenuGroupList']}") private String query2;
	@SuppressWarnings("unchecked")
	public List getMenuGroupList(String bi_portal_menu_id) throws DataAccessException {

		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query2);

		query.setParameter(0, bi_portal_menu_id);
		return query.list();

	}
	@Value("#{sqlquery['group.getMenuGroupDelete']}") private String query3;
	@SuppressWarnings("unchecked")
	public int getMenuGroupDelete(String bi_portal_menu_id) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query3);
		query.setParameter(0, bi_portal_menu_id);
		query.executeUpdate();

		// sessionFactory.getCurrentSession().delete(bi_group_id);

		return 1;
	}

	@Value("#{sqlquery['group.getMenuGroupMapngList']}") private String query4;
	@SuppressWarnings("unchecked")
	public List getMenuGroupMapngList(String bi_portal_menu_id) throws DataAccessException {
		logger.debug("findBoardList ");

		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query4);

		query.setParameter(0, bi_portal_menu_id);

		return query.list();

	}

	@Value("#{sqlquery['group.getMenuGroupRoleList']}") private String query5;
	@SuppressWarnings("unchecked")
	public List getMenuGroupRoleList(String bi_portal_menu_id) throws DataAccessException {
		logger.debug("findBoardList ");

		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query5);

		query.setParameter(0, bi_portal_menu_id);

		return query.list();

	}




	@SuppressWarnings("unchecked")
	public Group getGroupCode(String bi_group_nm) throws DataAccessException {
		logger.debug("findBoardList ");

		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery(" From Group group where bi_group_nm= ?");
		query.setParameter(0, bi_group_nm);
		Group group = (Group) query.list().get(0);
		return group;

	}

	 /*
	 * 관리실 > 사용자 관리 >사용자 그룹관리  (select count)
	 * @param : groupId (그룹ID) 
	 * @return : count(number)
	 * @return 
	 * @desc : 사용자 그룹- 조직 맵핑 갯수 
	 */
	@Value("#{sqlquery['group.ugDeptMappCnt']}") private String query6;
	@SuppressWarnings("unchecked")
	public int ugDeptMappCnt(String groupId) throws DataAccessException {
		logger.debug("findBoardList ");

		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query6);

		query.setParameter(0, groupId);

		return query.list().size();

	}
	@Value("#{sqlquery['group.getDeptGroupList1']}") private String query7;
	@Value("#{sqlquery['group.getDeptGroupList2']}") private String query8;
	@SuppressWarnings("unchecked")
	public List<DeptGroup> getDeptGroupList(String bi_group_id, String bi_deptNm) throws DataAccessException {

		Query query = null;
		if(!bi_deptNm.equals("") && bi_deptNm != null){			
			
			query = sessionFactory.getCurrentSession().createSQLQuery(query7);
			
		}else{
			
			query = sessionFactory.getCurrentSession().createSQLQuery(query8);
			
			

			
			
		}


		query.setParameter(0, bi_group_id);
		
		if(bi_deptNm != null && !bi_deptNm.equals("") ){
			
			query.setParameter(1, "%"+bi_deptNm+"%");
		}
		return query.list();

	}

	@SuppressWarnings("unchecked")
	public DeptGroup groupDeptInsert(DeptGroup deptGroup) throws DataAccessException {
		sessionFactory.getCurrentSession().save(deptGroup);
		return deptGroup;
	}

	/*
	 * 관리실 > 사용자 관리 >사용자 그룹관리  (delete)
	 * @param : groupId (그룹ID) 
	 * @return : 
	 * @return : 
	 * @desc : 사용자 그룹- 조직 맵핑 삭제 
	 */
	@Value("#{sqlquery['group.ugDeptMappDelete']}") private String query9;
	@SuppressWarnings("unchecked")
	public int ugDeptMappDelete(String groupId) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query9);
		query.setParameter(0, groupId);
		query.executeUpdate();

		// sessionFactory.getCurrentSession().delete(bi_group_id);

		return 1;
	}
	@Value("#{sqlquery['group.getDeptGroupMapngList']}") private String query10;
	@SuppressWarnings("unchecked")
	public List<DeptGroup> getDeptGroupMapngList(String bi_gorup_id) throws DataAccessException {
		logger.debug("findBoardList ");

		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query10);

		query.setParameter(0, bi_gorup_id);

		return query.list();

	}

	@SuppressWarnings("unchecked")
	public DeptGroup deptGroupInsert(DeptGroup deptGroup) throws DataAccessException {
		sessionFactory.getCurrentSession().save(deptGroup);
		return deptGroup;
	}

	/**************************** 사용자그룹 조직맵핑 ***************************************/

	/**************************** 사용자그룹 사용자맵핑 ***************************************/

	public UserGroup userDeptInsert(UserGroup userGroup) throws DataAccessException {

		sessionFactory.getCurrentSession().save(userGroup);
		return userGroup;
	}
	
	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 사용자그룹 -사용자 맵핑 삭제
	 * @param : groupId[사용자그룹ID]
	 * @return : 1-ok
	 * @return :  
	 * 사용자그룹 사용자 맵핑 삭제
	 */
	
	@Value("#{sqlquery['group.ugUserMappDelete']}") private String query11;
	@SuppressWarnings("unchecked")
	public int ugUserMappDelete(String groupId) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query11);
		query.setParameter(0, groupId);
		query.executeUpdate();

		// sessionFactory.getCurrentSession().delete(bi_group_id);

		return 1;
	}

	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 사용자그룹 -사용자 맵핑 갯수
	 * @param : groupId[사용자그룹ID]
	 * @return : count[전체갯수]
	 * @return :  
	 * 사용자그룹 사용자 맵핑 갯수 
	 */
	
	@Value("#{sqlquery['group.ugUserMappCnt']}") private String query12;
	@SuppressWarnings("unchecked")
	public int ugUserMappCnt(String groupId) throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query12);
		query.setParameter(0, groupId);

		// sessionFactory.getCurrentSession().delete(bi_group_id);

		return query.list().size();
	}

	@Value("#{sqlquery['group.getUserGroupList']}") private String query13;
	@SuppressWarnings("unchecked")
	public List<UserGroup> getUserGroupList(String bi_dept_id, String bi_group_id) throws DataAccessException {

		Query query = null;
		
		logger.info("bi_dept_id:" + bi_dept_id);
		logger.info("bi_group_id:" + bi_group_id);
		query = sessionFactory.getCurrentSession().createSQLQuery(query13);

		query.setParameter(0, bi_dept_id);
		query.setParameter(1, bi_group_id);
		return query.list();

	}
	
	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 사용자그룹 -사용자 맵핑 현황
	 * @param : groupId[사용자그룹ID]
	 * @return : List
	 * @return :  
	 * 전체 사용자그룹 -사용자 맵핑 현황
	 */

	
	@Value("#{sqlquery['group.getUgUserMapngData']}") private String query14;
	@SuppressWarnings("unchecked")
	public List<DeptGroup> getUgUserMapngData(String groupId) throws DataAccessException {
		logger.debug("findBoardList ");

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query14);
		query.setParameter(0, groupId);
		return query.list();

	}
	
	/***************** 사용자 그룹 메뉴 그룹 사용자 맵핑 *******************/
	@Value("#{sqlquery['group.getUserGroupListData']}") private String query15;
	@SuppressWarnings("unchecked")
	public List getUserGroupListData(String bi_menu_group_id)throws DataAccessException {
		
		logger.debug("findBoardList ");

		Query query = null;
		
		query = sessionFactory.getCurrentSession().createSQLQuery(query15);

		query.setParameter(0, bi_menu_group_id);
		
		return query.list();

	}
	/**********************************************************/

	
	/***************** 사용자그룹 전체 삭제 *******************/
	@Value("#{sqlquery['group.deleteAll']}") private String query16;
	@SuppressWarnings("unchecked")
	public int deleteAll() throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query16);
		
		query.executeUpdate();

		// sessionFactory.getCurrentSession().delete(bi_group_id);

		return 1;
	}
	
/***************** 사용자그룹  조직 맵핑 삭제 전체 삭제 *******************/
	@Value("#{sqlquery['group.deleteUgDeptMappAll']}") private String query17;
	@SuppressWarnings("unchecked")
	public int deleteUgDeptMappAll() throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query17);
		
		query.executeUpdate();

		// sessionFactory.getCurrentSession().delete(bi_group_id);

		return 1;
	}
	
	
  /***************** 사용자그룹  사용자 맵핑 삭제 전체 삭제 *******************/
	@Value("#{sqlquery['group.deleteUgUserMappAll']}") private String query18;
	@SuppressWarnings("unchecked")
	public int deleteUgUserMappAll() throws DataAccessException {

		Query query = null;
		query = sessionFactory.getCurrentSession().createSQLQuery(query18);
		
		query.executeUpdate();

		// sessionFactory.getCurrentSession().delete(bi_group_id);

		return 1;
	}

	
	
	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 맵핑 현황
	 * @param :
	 * @return : list(자식 아이디, 부모ID, 명 )
	 * @return : json data 
	 * 사용자 그룹 -조직-사용자 전체 맵핑현황 보기 
	 */
	@Value("#{sqlquery['menuGroup.getMenuGroupMappAllStatus']}") private String query19; 
	@SuppressWarnings("unchecked")
	public List getMenuGroupMappAllStatus() throws DataAccessException {
		logger.debug("getDeptUserList ");
		Query query = null;

		query = sessionFactory.getCurrentSession().createSQLQuery(query19);
		
		return query.list();

	}


}