package com.zetta.menu.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.common.DateTimeUtil;
import com.zetta.menu.dao.MenuDAO;
import com.zetta.menu.model.Menu;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	public Logger logger = Logger.getLogger(getClass());

	@Autowired
	private MenuDAO menuDao;

	@Override
	public Menu getById(String Id) {

		return menuDao.getById(Id);

	}

	@Override
	public Menu save(Menu menu) {

		menu.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		menu.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		if(menu.getBi_top_view_yn() != null){
			
			menu.setBi_top_view_yn(menu.getBi_top_view_yn());
		}else{
			menu.setBi_top_view_yn("N");
		}
		if(menu.getBi_menu_type_yn().equals("M")){
			
			menu.setBi_inqire_yn("");
			menu.setBi_inqire_param("");
//			menu.setBi_object_nm("");

			
		}
		Menu result = menuDao.insert(menu);
	
		// 파일 갯수 위한 메소드
		return result;

	}

	@Override
	public Menu update(Menu menu) {
		
		
		Menu getMenu = menuDao.getById(menu.getBi_portal_menu_id());

		menu.setBi_reg_dt(getMenu.getBi_reg_dt());
		menu.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		logger.info("menu.getBi_top_view_yn()"+ getMenu.getBi_top_view_yn());
		menu.setBi_top_view_yn(getMenu.getBi_top_view_yn());
		
		Menu result = menuDao.update(menu);
		if (logger.isDebugEnabled()) {
			logger.debug("updated board!!. Update Board ID : " + result.getBi_portal_menu_id());
		}
		return result;

	}

	@Override
	public int remove(String Id) {

		return menuDao.delete(Id);

	}
	
	public int deleteAll(){
		
		return menuDao.deleteAll();
	}

	@Override
	public int childRemove(String Id) {

		return menuDao.childDelete(Id);

	}

	@Override
	public Menu findList(String Id) {

		Menu menu = menuDao.findList(Id);

		return menu;

	}

	@Override
	public Menu findWithView(String Id) {

		return findList(Id);
	}

	@Override
	public List findAllList() {

		return menuDao.findAllList();
	}
	
	

	
	@Override
	public List<Menu> findSearchList(String bi_search_keyword) {

		return menuDao.findSearchList(bi_search_keyword);
	}

	public List<Menu> findChildList(String bi_parent_id){

	
		 List<Menu> result = menuDao.findChildList(bi_parent_id);

	
		return result;

	}

	@Override
	public int getChildCount(String bi_portal_menu_parent_id) {
		return menuDao.getChildCount(bi_portal_menu_parent_id);
	}
	@Override
	public Menu getChildMaxId(String bi_portal_menu_parent_id){
		
		return menuDao.getChildMaxId(bi_portal_menu_parent_id);
	}

	@Override
	public int getTotalCount() {
		return menuDao.getTotalNo();
	}
	
	@Override
	public int getMenuCnt(String bi_menu_nm, String bi_portal_menu_parent_id){
		
		return menuDao.getMenuCnt(bi_menu_nm, bi_portal_menu_parent_id);

	}

	@Override
	public List<Menu> getMenuList(String bi_portal_menu_parent_id) {

		return menuDao.getMenuList(bi_portal_menu_parent_id);

	}

	public List<Menu> leftMenuList(String bi_portal_menu_parent_id) {

		return menuDao.leftMenuList(bi_portal_menu_parent_id);

	}

	public List getRoleMenuList(String bi_unity_cust_id, String bi_portal_menu_parent_id) {

		return menuDao.getRoleMenuList(bi_unity_cust_id, bi_portal_menu_parent_id);
	}

	
	public List getRoleReportList(String bi_unity_cust_id, String bi_portal_menu_parent_id, String bi_menu_type_yn){
		
		return menuDao.getRoleReportList(bi_unity_cust_id, bi_portal_menu_parent_id, bi_menu_type_yn);
	}
	
	
	public int getRoleMenuCnt(String bi_unity_cust_id, String bi_portal_menu_parent_id, String roleType) {

		return menuDao.getRoleMenuCnt(bi_unity_cust_id, bi_portal_menu_parent_id, roleType);
	}

	public List<Menu> promptMenuList(String bi_inqire_yn) {

		return menuDao.promptMenuList(bi_inqire_yn);

	}


	
	
	@Value("#{qvconf['serviceMenuCd']}")
	private String serviceMenuCd;
	

	@Override
	public String getPromptMenuUrl(String bi_portal_menu_id, String qlikParam, int deptLev) {
		
		
		

		
		   Menu menu = menuDao.findList(bi_portal_menu_id);
		
		
		

			if (deptLev == 0) {

				qlikParam = qlikParam;

			} else {

				qlikParam = menu.getBi_menu_nm() +"/"+ qlikParam;

			}
			
			logger.info("qlikParam: :"+ qlikParam);
			
			deptLev = deptLev + 1;
			
			if (!menu.getBi_portal_menu_parent_id().equals(serviceMenuCd)) {

				return getPromptMenuUrl(menu.getBi_portal_menu_parent_id(), qlikParam, deptLev);
				

			}else{
				
				
				
				return qlikParam;
				
			}
			

		

		

	}
	
	
	@Override
	public String getMenuTree(String bi_portal_menu_id, String menuTree, int deptLev) {
		
		
		
		   Menu menu = menuDao.findList(bi_portal_menu_id);
		

		if (deptLev == 0) {
			
			//menuTree = "<span class=\"sel_txt\">"+ menuTree + "</span>";
			menuTree = "";

		} else if(deptLev == 1){ 
			
			
			menuTree = menu.getBi_menu_nm();
			
		}else{
				
			menuTree = menu.getBi_menu_nm() +" > "+ menuTree;
				
			
		}
		
		logger.info("qlikParam: :"+ menuTree);
		
		deptLev = deptLev + 1;
		
		if (!menu.getBi_portal_menu_parent_id().equals(serviceMenuCd) && !menu.getBi_portal_menu_parent_id().equals("#")) {

			return getMenuTree(menu.getBi_portal_menu_parent_id(), menuTree, deptLev);
			

		}else{
			
			
			
			return menuTree;
			
		}
		

		

		

	}
	
	public Menu getMenuNm(String bi_menu_nm){
		
		return menuDao.getMenuNm(bi_menu_nm);
		
	}
	
	public Menu getParentMenuNm(String bi_menu_nm, String bi_portal_menu_parent_id){
		
		return menuDao.getParentMenuNm(bi_menu_nm, bi_portal_menu_parent_id);
	}
	
	public Menu getParentMenuReturn(String[] bi_menu_nm, String bi_portal_menu_parent_id, int lastDept, int dpet){
		
		
		
		logger.info("파일이름::" + bi_menu_nm);
		logger.info("파일일명::" + bi_menu_nm[dpet]);
		logger.info("파일return::" + lastDept);
		logger.info("파일deot::" + dpet);
		
		Menu menu =  menuDao.getParentMenuNm(bi_menu_nm[dpet], bi_portal_menu_parent_id);
		logger.info("menu.getBi_portal_menu_id();" + menu.getBi_portal_menu_id());
		if(lastDept == dpet){
		
				
			return menu;
			
			
		}else{
			
			dpet = dpet + 1;
			
			return getParentMenuReturn(bi_menu_nm, menu.getBi_portal_menu_id(),lastDept, dpet);
			
		}
		
		
	}
	
	public int deleteMenuAll(String bi_menu_type_yn){
		
		return menuDao.deleteMenuAll(bi_menu_type_yn);
	}
	
	 public int getMenuTypeCnt(String bi_menu_type_yn){
		 
		 return menuDao.getMenuTypeCnt(bi_menu_type_yn);
	}
	 
	 public List getSearchReportList(String bi_unity_cust_id, String bi_menu_type_yn, String keyword){
		 
		 return menuDao.getSearchReportList(bi_unity_cust_id, bi_menu_type_yn, keyword);
	 }
	 
	 public int getSearchReportCount(String bi_unity_cust_id, String bi_menu_type_yn,  String keyword){
		 return menuDao.getSearchReportCount(bi_unity_cust_id, bi_menu_type_yn, keyword);
	 }
	 
	 public List<Menu> getBatchMenuList(String bi_menu_type_yn, String bi_menu_fm_yn){
		 
		 return menuDao.getBatchMenuList(bi_menu_type_yn, bi_menu_fm_yn);
	 }
	 


	 
	 public List getSiteMap(String bi_portal_menu_id){
		 
		 return menuDao.getSiteMap(bi_portal_menu_id);
	 }
	 
	 
	

	
	 public int getRoleQlikViewList(String bi_unity_cust_id, String bi_portal_menu_id){
		 return menuDao.getRoleQlikViewList( bi_unity_cust_id,  bi_portal_menu_id);
	 }
	 
	 public int deleteInit(String initYn){
		 
		 return menuDao.deleteInit(initYn);
	 }
	 
	
	 public String getExcelMenuId(List excelRows, int rowNum, String cateType){
		 
			HashMap menuHm = (HashMap) excelRows.get(rowNum);			
			String cateNm = null;
			if( menuHm.get(cateType) !=  null){				
				cateNm  = menuHm.get(cateType).toString();
			}
			
			if(cateNm == null){
				
				return	getExcelMenuId(excelRows,  rowNum-1, cateType);
				
			}else{
				
				return cateNm;
				
			}
			
			
		 
		 
	 }
	 
	 public Menu getLastMenuId(){
		 
		 return menuDao.getLastMenuId(); 
	 }
	 
	 public List getIndexSearch(String bi_unity_cust_id, String bi_menu_type_yn){
		 
		 return menuDao.getIndexSearch(bi_unity_cust_id, bi_menu_type_yn); 
	 }

}
