package com.init.setData.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.bookMark.service.BookMarkService;
import com.zetta.common.DateTimeUtil;
import com.zetta.common.ExcelUtil;
import com.zetta.dept.model.Dept;
import com.zetta.dept.service.DeptService;
import com.zetta.group.model.DeptGroup;
import com.zetta.group.model.Group;
import com.zetta.group.model.UserGroup;
import com.zetta.group.service.GroupService;
import com.zetta.menu.model.Menu;
import com.zetta.menu.service.MenuService;
import com.zetta.menuGroup.model.MenuGroup;
import com.zetta.menuGroup.model.MenuGroupMapp;
import com.zetta.menuGroup.model.MenuUserGroupMapp;
import com.zetta.menuGroup.service.MenuGroupService;

import com.zetta.security.model.Security;
import com.zetta.security.service.SecurityService;
import com.zetta.userInfo.model.UserInfo;
import com.zetta.userInfo.service.UserInfoService;

@Controller
public class SetDataController {

	public Logger logger = Logger.getLogger(getClass());

	// private static final String DEFAULT_CURRENT_PAGE = "1";
	// private static final String DEFAULT_COUNT_PER_PAGE = "10";
	@Autowired
	DeptService deptService;

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	SecurityService securityService;

	@Autowired
	GroupService groupService;

	@Autowired
	MenuService menuService;

	@Autowired
	MenuGroupService menuGroupService;



	@Autowired
	BookMarkService bookMarkService;


	


	@RequestMapping(value = "/setData/list.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(@RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("setData/list");
		return mav;

	}
	
	@RequestMapping(value = "/setData/initPage.do", method = RequestMethod.GET)
	public ModelAndView gotoDefaultPage(@RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("setData/initPage");
		return mav;

	}
	

	@Value("#{qvconf['menuGroupAdminCd']}") private String menuGroupCd; //메뉴 그룹 관리자 코드	
	@Value("#{qvconf['userGroupAdminCd']}") private String userGroupCd; //메뉴 그룹 관리자 코드	
	// 1.포탈 초기 메뉴 설정 시작 
	@RequestMapping(value = "/setData/portalInit.do", method = RequestMethod.POST)
	public ModelAndView getPortalInit(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();
	
		String Excelfile = request.getRealPath("/file/portalSet/portalInit.xlsx");
		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);
		//부서 등록
		logger.info("workBook" + workBook);
		List<String> metaName = Arrays.asList("deptId", "deptNm", "sort", "dc", "connectYn"); // 부서코드,부서명 , 정렬순서,설명, 사용여부 				// 0
		List excelData = excelUtil.readExcel(workBook, 0, metaName);//  조직 순서 
		
		logger.info("excelData: " + excelData);
		logger.info("excelData: " + excelData.size());
		
		// 포탈 부서삭제 

		Dept dept = new Dept();

		for (int i = 1; i < excelData.size(); i++) {

			logger.info(excelData.get(i));
			HashMap hm = (HashMap) excelData.get(i);
			
			if(i == 1){
				
				
				userInfoService.deleteDeptUser(hm.get("deptId").toString()); //관련 부서 사용자 삭제
				deptService.deleteAll(); // 포탈 부서삭제 
				
			}
			
			dept.setBi_dept_id(hm.get("deptId").toString());
			dept.setBi_deptnm(hm.get("deptNm").toString());
			dept.setBi_sort_sn(hm.get("sort").toString());
		
			dept.setBi_perm_yn(hm.get("connectYn").toString());

			deptService.save(dept);

		}
		
		//부서 등록 
		
		//사용자 등록
		
		
		
		logger.info("workBook" + workBook);
		List<String> userMeta= Arrays.asList("userId", "userNm", "deptId", "cellphone", "email","yn"); // 부서코드,부서명 , 정렬순서,설명, 사용여부 				// 0
		List userData = excelUtil.readExcel(workBook, 1, userMeta);//  조직 순서 
		
		logger.info("excelData: " + userData);
		logger.info("excelData: " + userData.size());
		
		// 포탈 부서삭제 

		UserInfo userInfo = new UserInfo();

		for (int i = 1; i < userData.size(); i++) {

			logger.info(userData.get(i));
			HashMap userHm = (HashMap) userData.get(i);
			
			if(i == 1){
				
				userInfoService.deleteDeptUser(userHm.get("deptId").toString()); //관련 부서 사용자 삭제
				//deptService.deleteAll(); // 포탈 부서삭제 
				
				
			}
			
			userInfo.setBi_unity_cust_id(userHm.get("userId").toString());
			userInfo.setBi_user_nm(userHm.get("userNm").toString());
			userInfo.setBi_dept_id(userHm.get("deptId").toString());
			userInfo.setBi_mpno(userHm.get("cellphone").toString());
			userInfo.setBi_email_addr(userHm.get("email").toString());
			userInfo.setBi_perm_yn(userHm.get("yn").toString());

			userInfoService.save(userInfo);

		}
		
		
		//사용자 등록 
		
		//사용자 로그인 등록 정보 
		
		logger.info("workBook" + workBook);
		List<String> securityMeta= Arrays.asList("userId", "password"); //사용자 ID , 사용자 비밀번호
		List securityData = excelUtil.readExcel(workBook, 2, securityMeta);//  조직 순서 	
		
		
		securityService.deleteAll(); //사용자 전체 삭제 
		
		
		for (int i = 1; i < securityData.size(); i++) {

			Security security = new Security();
			HashMap securityHm = (HashMap) securityData.get(i);
			security.setBi_unity_cust_id(securityHm.get("userId").toString());
			security.setBi_portal_user_esntl_id(securityHm.get("password").toString());

			securityService.save(security);

		}
		
		
		
        // 메뉴 그룹 등록 	
		
		// 메뉴 그룹 삭제  
		groupService.deleteAll(); // 사용자그룹 전체 삭제
		groupService.deleteUgUserMappAll(); //사용자그룹-사용자  맵핑 삭제	
		groupService.deleteUgDeptMappAll(); // 사용자그룹-부서 맵핑 삭제
		menuGroupService.deleteMenuGroupMappAll(); //메뉴그룹 매핑 삭제 
		menuGroupService.deleteAll(); // 메뉴 그룹 삭제 
		
		List<String> menuGroupMeta = Arrays.asList("menuGroupId", "menuGroupNm", "sort", "dc"); // 시트
		List menuGroupData = excelUtil.readExcel(workBook, 10, menuGroupMeta);// 엑셀 파일
		// hashMap
		logger.info("excelData: " + menuGroupData);
		logger.info("excelData: " + menuGroupData.size());
		
		MenuGroup menugroup = new MenuGroup();
		
		for (int i = 1; i < menuGroupData.size(); i++) {
		
			logger.info(menuGroupData.get(i));
			HashMap hm = (HashMap) menuGroupData.get(i);			
			
			menugroup.setBi_menu_group_id(hm.get("menuGroupId").toString());
			menugroup.setBi_menu_group_nm(hm.get("menuGroupNm").toString());
			menugroup.setBi_sort_sn(hm.get("sort").toString());
			menugroup.setBi_dc(hm.get("dc").toString());
			
			menuGroupService.save(menugroup);
		
		}
		// 메뉴그룹 등록  끝 
		
		
		
		menuService.deleteInit("Y"); // 초기 메뉴 먼저 제거 
		List<String> menuMeta = Arrays.asList("menuNm", "menuType", "url", "sort", "linkType", "topMenuView", "initYn"); // 포탈메뉴

		// 맨 상위 데이터 넣기
		List menuData = excelUtil.readExcel(workBook, 3, menuMeta);// 엑셀

		for (int rowNum = 1; rowNum < menuData.size(); rowNum++) { // 최상위
																		// 메뉴
			HashMap menuHm = (HashMap) menuData.get(rowNum); // 설정

		
			String url = null;

			url = menuHm.get("url").toString();
			if (url.equals("not")) {

				url = "";

			} else {

				url = url + "("+menuHm.get("sort").toString() + "');";

			}

			logger.info("url:" + url);

			Menu menu = new Menu();
			menu.setBi_menu_type_yn(menuHm.get("menuType").toString()); // 포탈 코드 M
			menu.setBi_portal_menu_parent_id("#"); // 최상위 경우 "#"
			menu.setBi_portal_menu_id(menuHm.get("sort").toString()); // 메뉴ID
			menu.setBi_menu_nm(menuHm.get("menuNm").toString()); // 메뉴명
			menu.setBi_menu_sort_sn(Integer.valueOf(menuHm.get("sort").toString())); // 메뉴순서
			menu.setBi_menu_fm_yn(menuHm.get("linkType").toString()); // 메뉴 type
			menu.setBi_menu_url_addr(url);
			menu.setBi_top_view_yn(menuHm.get("topMenuView").toString());
			menu.setBi_init_yn(menuHm.get("initYn").toString());
			menuService.save(menu);
			
			// 관리자 메뉴 그룹에 바로 등록 
			MenuGroupMapp mgMapp = new MenuGroupMapp();
			mgMapp.setBi_menu_group_id(menuGroupCd);
			mgMapp.setBi_portal_menu_id(menuHm.get("sort").toString());
			mgMapp.setBi_sort_sn(menuHm.get("sort").toString());
			menuGroupService.menuGroupMappSave(mgMapp); 

		}
		
		// 메뉴 등록
		 // 메뉴그룹 메뉴 맵핑 삭제
		
		   
		// 메뉴 초기화 삭제 
		
		List<String> menuMeta1 = Arrays.asList("menuNm", "menuType", "url", "sort", "linkType","initYn"); // 포탈메뉴
		
		for (int sheetNum = 4; sheetNum < 8; sheetNum++) { // 최상위

			String sheetName = workBook.getSheetName(sheetNum);
			
			logger.info("sheetName::"  + sheetName);
			
			
			Menu parentMenu = menuService.getParentMenuNm(sheetName, "#"); //상위 메뉴
			
			logger.info("sheetName :: " + sheetName);

			List menuData1 = excelUtil.readExcel(workBook, sheetNum, menuMeta1);

			for (int rowNum = 1; rowNum < menuData1.size(); rowNum++) {

				Menu curMenu = new Menu();

				HashMap menuHm1 = (HashMap) menuData1.get(rowNum);
		
				String parentId = null;
				String menuId = null;
				
				String menuNm = menuHm1.get("menuNm").toString(); // 메뉴이름
				
				String[] menuNmSp = menuNm.split(">"); //폴더 dept 
				logger.info("메뉴 구조 갯수 :: " + menuNmSp.length);
				
				
				
				
				
				int menuIdx = menuNmSp.length; //메뉴 dept
				
				String url = menuHm1.get("url").toString();
				if (url.equals("NOT")) {

					url = "";

				}
				
				
				if (menuIdx == 1) {
					
					parentId = parentMenu.getBi_portal_menu_id(); //부모 메뉴 아이디 
					menuId =  parentMenu.getBi_portal_menu_id()+"_"+menuHm1.get("sort").toString(); //자신 메뉴아이디 
					
					
					logger.info("메뉴명 :: " + menuNmSp[menuIdx-1]);
					logger.info("parentId:: " +parentId);
					logger.info("menuId :: " + menuId);
					
					curMenu.setBi_portal_menu_parent_id(parentId); 
					curMenu.setBi_portal_menu_id(menuId);
					curMenu.setBi_menu_nm(menuNmSp[menuIdx-1]);
					curMenu.setBi_menu_type_yn(menuHm1.get("menuType").toString());
					curMenu.setBi_menu_sort_sn(Integer.valueOf(menuHm1.get("sort").toString()));
					curMenu.setBi_menu_fm_yn(menuHm1.get("linkType").toString());
					curMenu.setBi_init_yn(menuHm1.get("initYn").toString());
					curMenu.setBi_menu_url_addr(url);
					menuService.save(curMenu);
					
					
					// 관리자 메뉴 그룹에 바로 등록 
					MenuGroupMapp mgMapp = new MenuGroupMapp();
					mgMapp.setBi_menu_group_id(menuGroupCd);
					mgMapp.setBi_portal_menu_id(menuId);
					mgMapp.setBi_sort_sn(menuHm1.get("sort").toString());
					menuGroupService.menuGroupMappSave(mgMapp); 
					 
					
				}else if(menuIdx == 2) {
					
					
					
					
					
					Menu pMenu = menuService.getParentMenuNm(menuNmSp[0], parentMenu.getBi_portal_menu_id());
					parentId = pMenu.getBi_portal_menu_id(); //부모 메뉴 아이디 
					menuId =  pMenu.getBi_portal_menu_id()+"_"+menuHm1.get("sort").toString(); //자신 메뉴아이디 
					
					
					logger.info("메뉴명 :: " + menuNmSp[menuIdx-1]);
					logger.info("parentId:: " +parentId);
					logger.info("menuId :: " + menuId);
					
					curMenu.setBi_portal_menu_parent_id(parentId); 
					curMenu.setBi_portal_menu_id(menuId);
					curMenu.setBi_menu_nm(menuNmSp[menuIdx-1]);
					curMenu.setBi_menu_type_yn(menuHm1.get("menuType").toString());
					curMenu.setBi_menu_sort_sn(Integer.valueOf(menuHm1.get("sort").toString()));
					curMenu.setBi_menu_fm_yn(menuHm1.get("linkType").toString());
					curMenu.setBi_init_yn(menuHm1.get("initYn").toString());
					curMenu.setBi_menu_url_addr(url);
					menuService.save(curMenu);
					
					
					// 관리자 메뉴 그룹에 바로 등록 
					MenuGroupMapp mgMapp = new MenuGroupMapp();
					mgMapp.setBi_menu_group_id(menuGroupCd);
					mgMapp.setBi_portal_menu_id(menuId);
					mgMapp.setBi_sort_sn(menuHm1.get("sort").toString());
					menuGroupService.menuGroupMappSave(mgMapp); 
				
					
				}else if(menuIdx == 3) {
					
				
					Menu pMenu = menuService.getParentMenuNm(menuNmSp[0], parentMenu.getBi_portal_menu_id());
					Menu sMenu = menuService.getParentMenuNm(menuNmSp[1], pMenu.getBi_portal_menu_id());
					
					parentId = sMenu.getBi_portal_menu_id(); //부모 메뉴 아이디 
					menuId =  sMenu.getBi_portal_menu_id()+"_"+menuHm1.get("sort").toString(); //자신 메뉴아이디 
					
					
					logger.info("메뉴명 :: " + menuNmSp[menuIdx-1]);
					logger.info("parentId:: " +parentId);
					logger.info("menuId :: " + menuId);
					
					curMenu.setBi_portal_menu_parent_id(parentId); 
					curMenu.setBi_portal_menu_id(menuId);
					curMenu.setBi_menu_nm(menuNmSp[menuIdx-1]);
					curMenu.setBi_menu_type_yn(menuHm1.get("menuType").toString());
					curMenu.setBi_menu_sort_sn(Integer.valueOf(menuHm1.get("sort").toString()));
					curMenu.setBi_menu_fm_yn(menuHm1.get("linkType").toString());
					curMenu.setBi_init_yn(menuHm1.get("initYn").toString());
					curMenu.setBi_menu_url_addr(url);
					menuService.save(curMenu);		
					
					
					// 관리자 메뉴 그룹에 바로 등록 
					MenuGroupMapp mgMapp = new MenuGroupMapp();
					mgMapp.setBi_menu_group_id(menuGroupCd);
					mgMapp.setBi_portal_menu_id(menuId);
					mgMapp.setBi_sort_sn(menuHm1.get("sort").toString());
					menuGroupService.menuGroupMappSave(mgMapp); 
					
				}	
			 
			}

		}
		// 메뉴 정보 
		
		// 사용자 그룹  넣기 
		// 사용 자 그룹 삭제 
		
		groupService.deleteAll();
		List<String> userGroupMeta = Arrays.asList("groupId", "groupNm", "sord", "dc", "authId"); // 시트
		List userGroupData = excelUtil.readExcel(workBook, 8, userGroupMeta);// 엑셀 파일  hashMap
		
		logger.info("userGroupData: " + userGroupData);
		logger.info("userGroupData: " + userGroupData.size());
		
		Group group = new Group();
		for (int i = 1; i < userGroupData.size(); i++) {
		
			HashMap ugHm = (HashMap) userGroupData.get(i);
			logger.info("hm.get(groupId).toString()" + ugHm.get("groupId").toString());
			logger.info("hm.get(sord).toString()" + ugHm.get("sord").toString());
			logger.info("hm.get(groupNm).toString()" + ugHm.get("groupNm").toString());
			logger.info("hm.get(dc).toString()" + ugHm.get("dc").toString());
			logger.info("hm.get(authId).toString()" + ugHm.get("authId").toString());
			
			group.setBi_group_id(ugHm.get("groupId").toString());
			group.setBi_group_nm(ugHm.get("groupNm").toString());
			group.setBi_sort_sn(ugHm.get("sord").toString());		
			group.setBi_dc(ugHm.get("dc").toString());
			group.setBi_group_author_id(ugHm.get("authId").toString());
			
			groupService.save(group);

		}
				
		// 사용자 그룹 넣기 
		
		
		// 관리사용자 그룹 - 관리자 맵핑 시작  
		List<String> userGroupMappMeta = Arrays.asList("userGroupCd", "adminId"); // 시트
		List ugMappData = excelUtil.readExcel(workBook, 9, userGroupMappMeta);// 엑셀 파일
		// hashMap
		logger.info("excelData: " + ugMappData);
		logger.info("excelData: " + ugMappData.size());
	
	
		UserGroup  userGroupMapp = new UserGroup();
		for (int i = 1; i < ugMappData.size(); i++) {
		
			HashMap ugMapphm = (HashMap) ugMappData.get(i);			
			logger.info("hm.get(userGroupCd).toString()" + ugMapphm.get("userGroupCd").toString()); 
			logger.info("hm.get(adminId).toString()" + ugMapphm.get("adminId").toString()); 			
			
			userGroupMapp.setBi_group_id(ugMapphm.get("userGroupCd").toString());
			userGroupMapp.setBi_unity_cust_id(ugMapphm.get("adminId").toString());
			
			groupService.userGroupSave(userGroupMapp);
		 
		}
		// 관리사용자 그룹 - 관리자 맵핑 끝 
		
		// 관리사용자 그룹 - 관리메뉴그룹 맵핑
		List<String> ugMgMappMeta = Arrays.asList("menuGroupCd", "userGroupCd"); // 시트
		List ugMgMappData = excelUtil.readExcel(workBook, 11, ugMgMappMeta);// 엑셀 파일
		// hashMap0
		logger.info("excelData: " + ugMgMappData);
		logger.info("excelData: " + ugMgMappData.size());
		
		
		for (int i = 1; i < ugMgMappData.size(); i++) {
		
			HashMap ugMgMapphm = (HashMap) ugMgMappData.get(i);	
			
			menuGroupService.getMenuUserGroupMappDelete(ugMgMapphm.get("menuGroupCd").toString());	 // 관련 사용자 그룹별 삭제 		
			MenuUserGroupMapp  ugMgMapp = new MenuUserGroupMapp();
			logger.info("hm.get(userGroupCd).toString()" + ugMgMapphm.get("menuGroupCd").toString()); 
			logger.info("hm.get(userGroupCd).toString()" + ugMgMapphm.get("userGroupCd").toString()); 
			ugMgMapp.setBi_group_id(ugMgMapphm.get("userGroupCd").toString());
			ugMgMapp.setBi_menu_group_id(ugMgMapphm.get("menuGroupCd").toString());
			
			menuGroupService.menuUserGroupMappSave(ugMgMapp);
		 
		}
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "등록되었습니다.");
		mav.setViewName("jsonView");

		return mav;

	}
	
	// 1.사용자정보 등록

	@RequestMapping(value = "/setData/setUserExcel.do", method = RequestMethod.POST)
	public ModelAndView getUserExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/userInfo.xlsx");

		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);
		logger.info("workBook" + workBook);
		List<String> metaName = Arrays.asList("bi_user_id", "bi_user_nm", "bi_dept_id", "bi_cttpc_no", "bi_email", "bi_conect_perm_yn"); // 시트
																																			// 0
		List excelData = excelUtil.readExcel(workBook, 0, metaName);// 엑셀 파일
																	// hashMap

		// Transaction tx = null;

		List<UserInfo> list = new ArrayList<UserInfo>();

		for (int i = 1; i < excelData.size(); i++) {

			UserInfo userInfo = new UserInfo();
			HashMap hm = (HashMap) excelData.get(i);

			userInfo.setBi_unity_cust_id(hm.get("bi_user_id").toString());
			String bi_user_id = userInfo.getBi_unity_cust_id();
			String spUserid[] = bi_user_id.split("\\.");

			userInfo.setBi_dept_id(hm.get("bi_dept_id").toString());
			String bi_dept_id = userInfo.getBi_dept_id();
			String spDeptid[] = bi_dept_id.split("\\.");

			userInfo.setBi_unity_cust_id(spUserid[0]);
			userInfo.setBi_user_nm(hm.get("bi_user_nm").toString());
			userInfo.setBi_dept_id(spDeptid[0]);
			userInfo.setBi_mpno(hm.get("bi_cttpc_no").toString());
			userInfo.setBi_email_addr(hm.get("bi_email").toString());
			userInfo.setBi_perm_yn(hm.get("bi_conect_perm_yn").toString());

			userInfo.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
			userInfo.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));

			list.add(userInfo);

		}

		userInfoService.batchInsert(list);

		logger.info("excelData: " + excelData.size());

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "등록되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	// 2. 로그인 사용자 등록
	@RequestMapping(value = "/setData/setLoginExcel.do", method = RequestMethod.POST)
	public ModelAndView getLoginExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/userInfo.xlsx");
		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);
		logger.info("workBook" + workBook);
		List<String> metaName = Arrays.asList("bi_user_id", "bi_user_login_sn"); // 시트
																					// 0
		List excelData = excelUtil.readExcel(workBook, 1, metaName);// 엑셀 파일
																	// hashMap
		logger.info("excelData: " + excelData);
		logger.info("excelData: " + excelData.size());

		List<Security> list = new ArrayList<Security>();

		for (int i = 1; i < excelData.size(); i++) {

			logger.info(excelData.get(i));
			HashMap hm = (HashMap) excelData.get(i);
			Security secuity = new Security();

			logger.info(hm.get("bi_user_id"));
			logger.info(hm.get("bi_user_login_sn").toString());

			secuity.setBi_unity_cust_id(hm.get("bi_user_id").toString());
			secuity.setBi_portal_user_esntl_id(hm.get("bi_user_login_sn").toString());

			String bi_user_id = secuity.getBi_unity_cust_id();
			String spUserid[] = bi_user_id.split("\\.");

			String bi_security_login_sn = secuity.getBi_portal_user_esntl_id();
			String spLoginSn[] = bi_security_login_sn.split("\\.");

			secuity.setBi_unity_cust_id(spUserid[0]);
			secuity.setBi_portal_user_esntl_id(spLoginSn[0]);

			list.add(secuity);

		}

		securityService.batchInsert(list);

		logger.info("excelData: " + excelData);
		logger.info("excelData: " + excelData.size());

		ModelAndView mav = new ModelAndView();

		mav.addObject("msg", "등록되었습니다.");
		mav.setViewName("jsonView");

		return mav;

	}

	// 3. 부서 등록

	@RequestMapping(value = "/setData/setDeptExcel.do", method = RequestMethod.POST)
	public ModelAndView getDeptExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/userInfo.xlsx");

		logger.info("Excelfile: " + Excelfile);

		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);
		logger.info("workBook" + workBook);
		List<String> metaName = Arrays.asList("bi_dept_id", "bi_dept_nm", "bi_sort", "bi_dc", "bi_dept_conect_perm_yn"); // 시트
																															// 0
		List excelData = excelUtil.readExcel(workBook, 2, metaName);// 엑셀 파일
																	// hashMap
		logger.info("excelData: " + excelData);
		logger.info("excelData: " + excelData.size());

		Dept dept = new Dept();

		for (int i = 0; i < excelData.size(); i++) {

			logger.info(excelData.get(i));
			HashMap hm = (HashMap) excelData.get(i);

			dept.setBi_dept_id(hm.get("bi_dept_id").toString());
			String bi_dept_id = dept.getBi_dept_id();
			String spDeptid[] = bi_dept_id.split("\\.");

			dept.setBi_sort_sn(hm.get("bi_sort").toString());
			String bi_sort = dept.getBi_sort_sn();
			String spSort[] = bi_sort.split("\\.");

			dept.setBi_dept_id(spDeptid[0]);
			dept.setBi_deptnm(hm.get("bi_dept_nm").toString());
			dept.setBi_sort_sn(spSort[0]);
			
			dept.setBi_perm_yn(hm.get("bi_dept_conect_perm_yn").toString());

			deptService.save(dept);

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "등록되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	// 1.관리용...사용자 등록 ( 관리자 추가)

	@RequestMapping(value = "/setData/setAdminUserExcel.do", method = RequestMethod.POST)
	public ModelAndView getAdminUserExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/adminUserInfo.xlsx");

		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);
		logger.info("workBook" + workBook);
		List<String> metaName = Arrays.asList("userId", "userNm", "deptId", "cttpcNo", "email", "conectYN"); // 시트
																																			// 0
		List excelData = excelUtil.readExcel(workBook, 0, metaName);// 엑셀 파일
																	// hashMap
		logger.info("excelData: " + excelData);
		logger.info("excelData: " + excelData.size());

		UserInfo userInfo = new UserInfo();

		for (int i = 1; i < excelData.size(); i++) {

			logger.info(excelData.get(i));

			HashMap hm = (HashMap) excelData.get(i);
			userInfo.setBi_unity_cust_id(hm.get("userId").toString());
			String bi_user_id = userInfo.getBi_unity_cust_id();
			String spUserid[] = bi_user_id.split("\\.");

			userInfo.setBi_dept_id(hm.get("deptId").toString());
			String bi_dept_id = userInfo.getBi_dept_id();
			String spDeptid[] = bi_dept_id.split("\\.");

			userInfo.setBi_unity_cust_id(spUserid[0]);
			userInfo.setBi_user_nm(hm.get("userNm").toString());
			userInfo.setBi_dept_id(spDeptid[0]);
			userInfo.setBi_mpno(hm.get("cttpcNo").toString());
			userInfo.setBi_email_addr(hm.get("email").toString());
			userInfo.setBi_perm_yn(hm.get("conectYN").toString());

			userInfoService.save(userInfo);

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "등록되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	// 2.포탈관리로그인 사용자 등록

	@RequestMapping(value = "/setData/setAdminLoginExcel.do", method = RequestMethod.POST)
	public ModelAndView getAdminLoginExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/adminUserInfo.xlsx");

		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);
		logger.info("workBook" + workBook);
		List<String> metaName = Arrays.asList("bi_user_id", "bi_user_login_sn"); // 시트
																					// 0
		List excelData = excelUtil.readExcel(workBook, 1, metaName);// 엑셀 파일
																	// hashMap
		logger.info("excelData: " + excelData);
		logger.info("excelData: " + excelData.size());

		Security secuity = new Security();

		for (int i = 1; i < excelData.size(); i++) {

			logger.info(excelData.get(i));
			HashMap hm = (HashMap) excelData.get(i);

			logger.info(hm.get("bi_user_id"));
			logger.info(hm.get("bi_user_login_sn").toString());

			secuity.setBi_unity_cust_id(hm.get("bi_user_id").toString());
			secuity.setBi_portal_user_esntl_id(hm.get("bi_user_login_sn").toString());

			String bi_user_id = secuity.getBi_unity_cust_id();
			String spUserid[] = bi_user_id.split("\\.");

			String bi_security_login_sn = secuity.getBi_portal_user_esntl_id();
			String spLoginSn[] = bi_security_login_sn.split("\\.");

			logger.info("사용자ID: " + spUserid[0]);
			logger.info("사용자일련번호: " + spLoginSn[0]);

			secuity.setBi_unity_cust_id(spUserid[0]);
			secuity.setBi_portal_user_esntl_id(spLoginSn[0]);
			securityService.save(secuity);

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "등록되었습니다.");
		mav.setViewName("jsonView");

		return mav;

	}

	// 3.포탈포탈관리 부서 등록

	@RequestMapping(value = "/setData/setAdminDeptExcel.do", method = RequestMethod.POST)
	public ModelAndView getAdminDeptExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/adminUserInfo.xlsx");

		logger.info("Excelfile: " + Excelfile);

		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);
		logger.info("workBook" + workBook);
		List<String> metaName = Arrays.asList("bi_dept_id", "bi_dept_nm", "bi_sort", "bi_dc", "bi_dept_conect_perm_yn"); // 시트
																															// 0
		List excelData = excelUtil.readExcel(workBook, 2, metaName);// 엑셀 파일
																	// hashMap
		logger.info("excelData: " + excelData);
		logger.info("excelData: " + excelData.size());

		Dept dept = new Dept();

		for (int i = 1; i < excelData.size(); i++) {

			logger.info(excelData.get(i));
			HashMap hm = (HashMap) excelData.get(i);

			dept.setBi_dept_id(hm.get("bi_dept_id").toString());
			String bi_dept_id = dept.getBi_dept_id();
			String spDeptid[] = bi_dept_id.split("\\.");

			dept.setBi_sort_sn(hm.get("bi_sort").toString());
			String bi_sort = dept.getBi_sort_sn();
			String spSort[] = bi_sort.split("\\.");

			logger.info("dppt_id::" + spDeptid[0]);
			logger.info("dppt_nm::" + spSort[0]);

			dept.setBi_dept_id(spDeptid[0]);
			dept.setBi_deptnm(hm.get("bi_dept_nm").toString());
			dept.setBi_sort_sn(spSort[0]);
			dept.setBi_perm_yn(hm.get("bi_dept_conect_perm_yn").toString());

			deptService.save(dept);

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "등록되었습니다.");
		mav.setViewName("jsonView");

		return mav;

	}

	// 1.사용자 그룹 -관리 그룹 만들기
	@RequestMapping(value = "/setData/getAdminGroupExcel.do", method = RequestMethod.POST)
	public ModelAndView getAdminGroupExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/portalAdminSet.xlsx");

		logger.info("Excelfile: " + Excelfile);

		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);
		logger.info("workBook" + workBook);
		List<String> metaName = Arrays.asList("bi_group_id", "bi_group_nm", "bi_sort", "bi_dc", "bi_group_author_id"); // 시트
																														// 0
		List excelData = excelUtil.readExcel(workBook, 0, metaName);// 엑셀 파일
																	// hashMap
		logger.info("excelData: " + excelData);
		logger.info("excelData: " + excelData.size());

		Group group = new Group();

		for (int i = 1; i < excelData.size(); i++) {

			logger.info(excelData.get(i));
			HashMap hm = (HashMap) excelData.get(i);

			logger.info("hm.get(bi_group_id).toString()" + hm.get("bi_group_id").toString());
			logger.info("hm.get(bi_sort).toString()" + hm.get("bi_sort").toString());

			logger.info("hm.get(bi_group_nm).toString()" + hm.get("bi_group_nm").toString());
			logger.info("hm.get(bi_sort).toString()" + hm.get("bi_sort").toString());
			logger.info("hm.get(bi_group_author_id).toString()" + hm.get("bi_group_author_id").toString());

			group.setBi_group_id(hm.get("bi_group_id").toString());
			group.setBi_sort_sn(hm.get("bi_sort").toString());
			String bi_sort = group.getBi_sort_sn();
			String spSort[] = bi_sort.split("\\.");
			logger.info("dppt_nm::" + spSort[0]);
			group.setBi_group_nm(hm.get("bi_group_nm").toString());
			group.setBi_sort_sn(spSort[0]);
			group.setBi_dc(hm.get("bi_dc").toString());
			group.setBi_group_author_id(hm.get("bi_group_author_id").toString());

			groupService.save(group);

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "등록되었습니다.");
		mav.setViewName("jsonView");

		return mav;

	}

	// 2 .관리자 사용자 그룹 맵핑 추가

	@RequestMapping(value = "/setData/getAdminUserGroupMappExcel.do", method = RequestMethod.POST)
	public ModelAndView getAdminGroupMappExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/portalAdminSet.xlsx");

		logger.info("Excelfile: " + Excelfile);

		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);
		logger.info("workBook" + workBook);
		List<String> metaName = Arrays.asList("bi_group_id", "rtUid"); // 시트
																					// 0
		List excelData1 = excelUtil.readExcel(workBook, 1, metaName);// 엑셀 파일
																	// hashMap
		logger.info("excelData: " + excelData1);
		logger.info("excelData: " + excelData1.size());

		UserGroup ug = new UserGroup();

		for (int i = 1; i < excelData1.size(); i++) {

			logger.info(excelData1.get(i));
			HashMap hm1= (HashMap) excelData1.get(i);

			logger.info("hm.get(bi_group_id).toString()" + hm1.get("bi_group_id").toString());
			logger.info("hm.get(rtUid).toString()" + hm1.get("rtUid").toString());

			ug.setBi_group_id(hm1.get("bi_group_id").toString());
			ug.setBi_unity_cust_id(hm1.get("rtUid").toString());
			groupService.userGroupSave(ug);

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "등록되었습니다.");
		mav.setViewName("jsonView");

		return mav;

	}

	// 3.메뉴그룹 만들기 ( 관리자 그룹 추가)
	@RequestMapping(value = "/setData/getAdminMenuGroupExcel.do", method = RequestMethod.POST)
	public ModelAndView getAdminMenuGroupExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/portalAdminSet.xlsx");

		logger.info("Excelfile: " + Excelfile);

		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);
		logger.info("workBook" + workBook);
		List<String> metaName = Arrays.asList("bi_menu_group_id", "bi_menu_group_nm", "bi_sort", "bi_dc"); // 시트
																											// 0
		List excelData = excelUtil.readExcel(workBook, 2, metaName);// 엑셀 파일
																	// hashMap
		logger.info("excelData: " + excelData);
		logger.info("excelData: " + excelData.size());

		MenuGroup menugroup = new MenuGroup();

		for (int i = 1; i < excelData.size(); i++) {

			logger.info(excelData.get(i));
			HashMap hm = (HashMap) excelData.get(i);

			logger.info("hm.get(bi_menu_group_id).toString()" + hm.get("bi_menu_group_id").toString());
			logger.info("hm.get(bi_menu_group_nm).toString()" + hm.get("bi_menu_group_nm").toString());

			logger.info("hm.get(bi_sort).toString()" + hm.get("bi_sort").toString());
			logger.info("hm.get(bi_dc).toString()" + hm.get("bi_dc").toString());

			menugroup.setBi_menu_group_id(hm.get("bi_menu_group_id").toString());
			menugroup.setBi_sort_sn(hm.get("bi_sort").toString());
			String bi_sort = menugroup.getBi_sort_sn();
			String spSort[] = bi_sort.split("\\.");
			logger.info("dppt_nm::" + spSort[0]);
			menugroup.setBi_menu_group_nm(hm.get("bi_menu_group_nm").toString());
			menugroup.setBi_sort_sn(spSort[0]);
			menugroup.setBi_dc(hm.get("bi_dc").toString());

			menuGroupService.save(menugroup);

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "등록되었습니다.");
		mav.setViewName("jsonView");

		return mav;

	}

	// 4,메뉴그룹 메뉴 맵핑 (메뉴 추가);
	@RequestMapping(value = "/setData/getAdminMenuGroupMappExcel.do", method = RequestMethod.POST)
	public ModelAndView getAdminMenuGroupMappExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/portalAdminSet.xlsx");

		logger.info("Excelfile: " + Excelfile);

		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);
		logger.info("workBook" + workBook);
		List<String> metaName = Arrays.asList("menuNm", "menGroupNM"); // 시트

		List excelData = excelUtil.readExcel(workBook, 4, metaName);// 엑셀 파일
																	// hashMap
		logger.info("excelData: " + excelData);
		logger.info("excelData: " + excelData.size());

		MenuGroup menugroup = new MenuGroup();
		MenuGroupMapp menuGroupMapp = new MenuGroupMapp();

		HashMap codeHm = (HashMap) excelData.get(0);

		String menGroupNM = codeHm.get("menGroupNM").toString(); // 메뉴그룹ID

		MenuGroup menuGroup = menuGroupService.getMenuGroupNm(menGroupNM);
		logger.info("메뉴그룹ID" + menuGroup.getBi_menu_group_id());

		int num = menuGroupService.getMenuGroupMappCnt(menuGroup.getBi_menu_group_id());

		if (num > 0) {

			menuGroupService.getMenuGroupMappDelete(menuGroup.getBi_menu_group_id()); // 일단
																						// 메뉴그룹을

		}

		for (int i = 1; i < excelData.size(); i++) {

			HashMap hm = (HashMap) excelData.get(i);
			logger.info("hm.get(menGroupNM).toString()" + hm.get("menGroupNM").toString()); // O,X
			logger.info("hm.get(menuNm).toString()" + hm.get("menuNm").toString()); // 메뉴
																					// ID

			String menuNm = hm.get("menuNm").toString();
			String menuGroupType = hm.get("menGroupNM").toString();

			if (menuGroupType.equals("O")) { // o이면 바로 실행

				String[] spMenuNm = menuNm.split("/");

				for (int j = 0; j < spMenuNm.length; j++) {

					if (j == 0) {

						Menu pMenu = menuService.getParentMenuNm(spMenuNm[0], "#"); // 서비스I

						int menuGroupCnt = menuGroupService.getMenuGroupMappCnt(menuGroup.getBi_menu_group_id(), pMenu.getBi_portal_menu_id());

						if (menuGroupCnt == 0) {

							logger.info("0레벨 그룹ID" + menuGroup.getBi_menu_group_id());
							logger.info("0레벨  메뉴ID" + pMenu.getBi_portal_menu_id());

							menuGroupMapp.setBi_menu_group_id(menuGroup.getBi_menu_group_id());
							menuGroupMapp.setBi_portal_menu_id(pMenu.getBi_portal_menu_id());
							menuGroupService.menuGroupMappSave(menuGroupMapp);

						}

					} else if (j == 1) {

						Menu pMenu = menuService.getParentMenuNm(spMenuNm[0], "#"); // 서비스I
						Menu sMenu = menuService.getParentMenuNm(spMenuNm[1], pMenu.getBi_portal_menu_id()); // 두번째

						int menuGroupCnt = menuGroupService.getMenuGroupMappCnt(menuGroup.getBi_menu_group_id(), sMenu.getBi_portal_menu_id());

						if (menuGroupCnt == 0) {

							logger.info("1레벨 그룹ID" + menuGroup.getBi_menu_group_id());
							logger.info("1레벨  메뉴ID" + sMenu.getBi_portal_menu_id());

							menuGroupMapp.setBi_menu_group_id(menuGroup.getBi_menu_group_id());
							menuGroupMapp.setBi_portal_menu_id(sMenu.getBi_portal_menu_id());
							menuGroupService.menuGroupMappSave(menuGroupMapp);

						}

					} else if (j == 2) {

						Menu pMenu = menuService.getParentMenuNm(spMenuNm[0], "#"); // 서비스I
						Menu sMenu = menuService.getParentMenuNm(spMenuNm[1], pMenu.getBi_portal_menu_id()); // 두번째
						Menu tMenu = menuService.getParentMenuNm(spMenuNm[2], sMenu.getBi_portal_menu_id()); // 두번째
																												// //
																												// 서비스ID

						int menuGroupCnt = menuGroupService.getMenuGroupMappCnt(menuGroup.getBi_menu_group_id(), tMenu.getBi_portal_menu_id());

						if (menuGroupCnt == 0) {

							logger.info("2레벨 그룹ID" + menuGroup.getBi_menu_group_id());
							logger.info("2레벨  메뉴ID" + tMenu.getBi_portal_menu_id());

							menuGroupMapp.setBi_menu_group_id(menuGroup.getBi_menu_group_id());
							menuGroupMapp.setBi_portal_menu_id(tMenu.getBi_portal_menu_id());
							menuGroupService.menuGroupMappSave(menuGroupMapp);

						}

					} else if (j == 3) {

						Menu pMenu = menuService.getParentMenuNm(spMenuNm[0], "#"); // 서비스I
						Menu sMenu = menuService.getParentMenuNm(spMenuNm[1], pMenu.getBi_portal_menu_id()); // 1
						Menu tMenu = menuService.getParentMenuNm(spMenuNm[2], sMenu.getBi_portal_menu_id()); // 2
						Menu fMenu = menuService.getParentMenuNm(spMenuNm[3], tMenu.getBi_portal_menu_id()); // 3

						logger.info("pMenu: " + pMenu.getBi_menu_nm());
						logger.info("sMenu: " + sMenu.getBi_menu_nm());
						logger.info("tMenu: " + tMenu.getBi_menu_nm());
						logger.info("fMenu: " + fMenu.getBi_menu_nm());

						int menuGroupCnt = menuGroupService.getMenuGroupMappCnt(menuGroup.getBi_menu_group_id(), fMenu.getBi_portal_menu_id());

						if (menuGroupCnt == 0) {

							logger.info("3레벨 그룹ID" + menuGroup.getBi_menu_group_id());
							logger.info("3레벨  메뉴ID" + fMenu.getBi_portal_menu_id());

							menuGroupMapp.setBi_menu_group_id(menuGroup.getBi_menu_group_id());
							menuGroupMapp.setBi_portal_menu_id(fMenu.getBi_portal_menu_id());
							menuGroupService.menuGroupMappSave(menuGroupMapp);

						}

					}

				}

			}

			// menuGroupService.save(menugroup);

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "등록되었습니다.");
		mav.setViewName("jsonView");

		return mav;

	}

	// 5.메뉴그룹 - 사용자그룹 맵핑 ( 관리자용)
	@RequestMapping(value = "/setData/getAdminMenuUserGroupMappExcel.do", method = RequestMethod.POST)
	public ModelAndView getAdminMenuUserGroupMappExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/portalAdminSet.xlsx");

		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);

		List<String> metaName = Arrays.asList("menuGroup", "userGroup"); // 시트
																			// //
																			// 0
		List excelData = excelUtil.readExcel(workBook, 5, metaName);// 엑셀 파일
																	// hashMap
		logger.info("excelData: " + excelData);
		logger.info("excelData: " + excelData.size());

		MenuGroup menuGroup = new MenuGroup(); // 메뉴 그룹
		Group group = new Group(); // 사용자 그룹
		MenuUserGroupMapp menuUserGroupMapp = new MenuUserGroupMapp(); // 사용자 메뉴
																		// 그룹

		HashMap codeHm = (HashMap) excelData.get(0);
		String userGroupNm = codeHm.get("userGroup").toString(); // 메뉴그룹ID

		group = groupService.getGroupNm(userGroupNm);
		logger.info("그룹명" + codeHm.get("userGroup").toString());
		logger.info("그룹ID::" + group.getBi_group_id());

		for (int i = 1; i < excelData.size(); i++) {

			HashMap hm = (HashMap) excelData.get(i);

			logger.info("hm.get(menuGroup).toString()" + hm.get("menuGroup").toString()); // O,X
			logger.info("hm.get(userGroup).toString()" + hm.get("userGroup").toString()); // O,X

			String menuNm = hm.get("menuGroup").toString();
			String userGroupType = hm.get("userGroup").toString();

			if (userGroupType.equals("O")) {

				menuGroup = menuGroupService.getMenuGroupNm(menuNm);
				logger.info("메뉴그룹명: " + menuGroup.getBi_menu_group_id()); // O,X

				int num = menuGroupService.getMenuUserGroupMappCnt(group.getBi_group_id(), menuGroup.getBi_menu_group_id());

				if (num > 0) {

					menuGroupService.getMenuUserGroupMappDelete(group.getBi_group_id(), menuGroup.getBi_menu_group_id());

				}

				menuUserGroupMapp.setBi_group_id(group.getBi_group_id());
				menuUserGroupMapp.setBi_menu_group_id(menuGroup.getBi_menu_group_id());

				menuGroupService.menuUserGroupMappSave(menuUserGroupMapp);

			}
			// menuGroupService.save(menugroup);

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "등록되었습니다.");
		mav.setViewName("jsonView");

		return mav;

	}

	// 1.사용자 그룹 추가
	@RequestMapping(value = "/setData/setUserGroupExcel.do", method = RequestMethod.POST)
	public ModelAndView setUserGroupExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/portalSet.xlsx");
		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);

		List<String> metaName = Arrays.asList("bi_group_nm", "bi_group_author_id", "bi_sort_sn", "bi_dc"); // 시트
																											// 0
		List excelData = excelUtil.readExcel(workBook, 0, metaName);// 엑셀 파일

		Group group = new Group();

		for (int i = 1; i < excelData.size(); i++) {

			HashMap hm = (HashMap) excelData.get(i);

			int rowNum = groupService.getTotalCount(0, "");

			String keyCode = "UG";
			int keyNum = 100000;
			String groupId = keyCode + (keyNum + rowNum);

			group.setBi_group_id(groupId);
			group.setBi_group_nm(hm.get("bi_group_nm").toString());
			group.setBi_group_author_id(hm.get("bi_group_author_id").toString());
			group.setBi_dc(hm.get("bi_dc").toString());

			if (hm.get("bi_sort_sn") != null) {
				String sort = hm.get("bi_sort_sn").toString();
				String[] spSort = sort.split("\\.");
				group.setBi_sort_sn(spSort[0]);

			} else {

				group.setBi_sort_sn("");

			}

			logger.info("groupId: " + groupId);
			logger.info("bi_group_nm: " + hm.get("bi_group_nm").toString());
			logger.info("bi_group_author_id: " + hm.get("bi_group_author_id").toString());
			logger.info("bi_dc: " + hm.get("bi_dc").toString());
			logger.info("bi_sort_sn: " + hm.get("bi_sort_sn").toString());

			groupService.save(group);

		}

		ModelAndView mav = new ModelAndView();

		mav.addObject("msg", "등록되었습니다.");

		mav.setViewName("jsonView");
		return mav;

	}

	// 2.조직 사용자 그룹 맵핑 추가

	@RequestMapping(value = "/setData/setUserGroupDeptMappExcel.do", method = RequestMethod.POST)
	public ModelAndView setUserGroupDeptMappExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Group group = new Group();
		Dept dept = new Dept();

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/portalSet.xlsx");
		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);

		XSSFSheet sheet = workBook.getSheetAt(1); // 1번쉬트
		for (int i = 1; i < sheet.getLastRowNum(); i++) {

			XSSFRow rows = sheet.getRow(i);
			for (int j = 1; j < rows.getLastCellNum(); j++) {

				XSSFCell cells = rows.getCell(j);
				String mappType = excelUtil.getCellTypeVal(cells);

				if (mappType.equals("O")) {
					DeptGroup deptGroup = new DeptGroup();
					// 메뉴 코드 가지고 온다 //
					XSSFRow KeyRows = sheet.getRow(0);
					String userGroupNm = excelUtil.getCellTypeVal(KeyRows.getCell(j));
					group = groupService.getGroupNm(userGroupNm);
					// 그롭 코드 가지고 온다 //

					// 조직코드 가지고 온다

					XSSFCell keyCells = rows.getCell(0);

					String deptNm = excelUtil.getCellTypeVal(keyCells);
					dept = deptService.getDeptCode(deptNm);

					logger.info("부서ID:" + dept.getBi_dept_id() + " 부서명:" + deptNm + " 맵핑여부:" + mappType);
					logger.info("그룹ID:" + group.getBi_group_id() + " 그룹명:" + userGroupNm + " 맵핑여부:" + mappType);

					deptGroup.setBi_dept_id(dept.getBi_dept_id());
					deptGroup.setBi_group_id(group.getBi_group_id());
					groupService.deptGroupSave(deptGroup);

					// 조직코드 가지고 온다

				}

			}

		}

		ModelAndView mav = new ModelAndView();

		mav.addObject("msg", "등록되었습니다.");

		mav.setViewName("jsonView");
		return mav;

	}

	// 2 -1.사용자그룹 사용자 맵핑 추가

	@RequestMapping(value = "/setData/setUserGroupUserMappExcel.do", method = RequestMethod.POST)
	public ModelAndView setUserGroupUserMappExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/portalSet.xlsx");

		logger.info("Excelfile: " + Excelfile);

		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);
		logger.info("workBook" + workBook);
		List<String> metaName = Arrays.asList("bi_group_nm", "rtUid"); // 시트
																					// 0
		List excelData = excelUtil.readExcel(workBook, 2, metaName);// 엑셀 파일
																	// hashMap
		logger.info("excelData: " + excelData);
		logger.info("excelData: " + excelData.size());

		UserGroup ug = new UserGroup();
		Group group = new Group();

		for (int i = 1; i < excelData.size(); i++) {

			logger.info(excelData.get(i));
			HashMap hm = (HashMap) excelData.get(i);

			logger.info("hm.get(bi_group_id).toString()" + hm.get("bi_group_nm").toString());
			logger.info("hm.get(rtUid).toString()" + hm.get("rtUid").toString());
			group = groupService.getGroupNm(hm.get("bi_group_nm").toString());
			String userId = hm.get("rtUid").toString();

			String[] userIdSp = userId.split("//.");

			group = groupService.getGroupNm(hm.get("bi_group_nm").toString());

			logger.info("그룹ID:: " + group.getBi_group_id());
			logger.info("사용자ID:: " + userIdSp[0]);

			ug.setBi_group_id(group.getBi_group_id());
			ug.setBi_unity_cust_id(userIdSp[0]);
			groupService.userGroupSave(ug);

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "등록되었습니다.");
		mav.setViewName("jsonView");

		return mav;

	}

	// 3.메뉴 그룹 만들기

	@RequestMapping(value = "/setData/setMenuGroupExcel.do", method = RequestMethod.POST)
	public ModelAndView setMenuGroupExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/menuGroupMapp.xlsx");
		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);

		List<String> metaName = Arrays.asList("bi_group_nm", "bi_sort_sn", "bi_dc"); // 시트
																						// 0
		List excelData = excelUtil.readExcel(workBook, 0, metaName);// 엑셀 파일

		for (int i = 1; i < excelData.size(); i++) {

			MenuGroup menuGroup = new MenuGroup();
			HashMap hm = (HashMap) excelData.get(i);

			int rowNum = menuGroupService.getTotalCount(0, "");

			String keyCode = "MG";
			int keyNum = 100000;
			String groupId = keyCode + (keyNum + rowNum);

			menuGroup.setBi_menu_group_id(groupId);
			menuGroup.setBi_menu_group_nm(hm.get("bi_group_nm").toString());
			menuGroup.setBi_dc(hm.get("bi_dc").toString());

			if (hm.get("bi_sort_sn") != null) {
				String sort = hm.get("bi_sort_sn").toString();
				String[] spSort = sort.split("\\.");
				menuGroup.setBi_sort_sn(spSort[0]);

			} else {

				menuGroup.setBi_sort_sn("");

			}

			logger.info("groupId: " + groupId);
			logger.info("bi_group_nm: " + hm.get("bi_group_nm").toString());
			logger.info("bi_sort_sn: " + hm.get("bi_sort_sn").toString());
			logger.info("bi_dc: " + hm.get("bi_dc").toString());

			menuGroupService.save(menuGroup);

		}

		ModelAndView mav = new ModelAndView();

		mav.addObject("msg", "등록되었습니다.");

		mav.setViewName("jsonView");
		return mav;

	}

	// 4.메뉴 & 메뉴 그룹 맵핑 하기

	@RequestMapping(value = "/setData/setMenuUserGroupDeptMappExcel.do", method = RequestMethod.POST)
	public ModelAndView setMenuUserGroupDeptMappExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		MenuGroupMapp menuGroupMapp = new MenuGroupMapp();

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/menuGroupMapp.xlsx");
		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);

	
		for (int sheetNum = 1; sheetNum < (workBook.getNumberOfSheets() - 1); sheetNum++) { // 최상위

			MenuGroup menuGroup = new MenuGroup();
			XSSFSheet sheet = workBook.getSheetAt(sheetNum);
		    menuGroup   =    menuGroupService.getMenuGroupNm(sheet.getSheetName());
		    
		    String bi_menu_group_id =  menuGroup.getBi_menu_group_id(); //메뉴 그룹 ID
		    int menuGroupMappCnt = menuGroupService.getMenuGroupMappCnt(bi_menu_group_id);
		    
		    if(menuGroupMappCnt > 0){
		    	
		    	menuGroupService.getMenuGroupMappDelete(bi_menu_group_id);
		    	
		    }

		   for (int rowNum = 1; rowNum < sheet.getLastRowNum(); rowNum++) {

			XSSFRow rows = sheet.getRow(rowNum);
			

			for (int cellNum = 1; cellNum < rows.getLastCellNum(); cellNum++) {

				XSSFCell cells = rows.getCell(cellNum); // O,X
				String mappType = excelUtil.getCellTypeVal(cells); // O,X

				if (mappType.equals("O")) {
					
					String menuNm = excelUtil.getCellTypeVal(rows.getCell(0)); // O,X
					
					logger.info("menuNm::" + menuNm);

					String[] spMenuNm = menuNm.split("/");

					for (int k = 0; k < spMenuNm.length; k++) {

						//if (k == 0) {
							
							
							Menu pMenu = menuService.getParentMenuReturn(spMenuNm, "#", k, 0);

							//Menu pMenu = menuService.getParentMenuNm(spMenuNm[0], "#"); // 서비스I
							int menuGroupCnt = menuGroupService.getMenuGroupMappCnt(menuGroup.getBi_menu_group_id(), pMenu.getBi_portal_menu_id());

						if (menuGroupCnt == 0) {

								logger.info("0레벨 그룹ID" + menuGroup.getBi_menu_group_id());
								logger.info("0레벨  메뉴ID" + pMenu.getBi_portal_menu_id());
								logger.info("0레벨  a메뉴명" + pMenu.getBi_menu_nm());

								menuGroupMapp.setBi_menu_group_id(menuGroup.getBi_menu_group_id());
								menuGroupMapp.setBi_portal_menu_id(pMenu.getBi_portal_menu_id());
								menuGroupService.menuGroupMappSave(menuGroupMapp);

							}
							
						/*
						} else if (k == 1) {

							//Menu pMenu = menuService.getParentMenuNm(spMenuNm[0], "#"); // 서비스I
							///Menu sMenu = menuService.getParentMenuNm(spMenuNm[1], pMenu.getBi_portal_menu_id()); // 두번째
							
							Menu pMenu = menuService.getParentMenuReturn(spMenuNm, "#", k, 0);


							int menuGroupCnt = menuGroupService.getMenuGroupMappCnt(menuGroup.getBi_menu_group_id(), pMenu.getBi_portal_menu_id());

							if (menuGroupCnt == 0) {

								logger.info("1레벨 그룹ID" + menuGroup.getBi_menu_group_id());
								logger.info("1레벨  메뉴ID" + pMenu.getBi_portal_menu_id());
								logger.info("1레벨  a메뉴명" + pMenu.getBi_menu_nm());

								menuGroupMapp.setBi_menu_group_id(menuGroup.getBi_menu_group_id());
								menuGroupMapp.setBi_portal_menu_id(pMenu.getBi_portal_menu_id());
								menuGroupService.menuGroupMappSave(menuGroupMapp);

							}

						} else if (k == 2) {

//							Menu pMenu = menuService.getParentMenuNm(spMenuNm[0], "#"); // 서비스I
//							Menu sMenu = menuService.getParentMenuNm(spMenuNm[1], pMenu.getBi_portal_menu_id()); // 두번째
//							Menu tMenu = menuService.getParentMenuNm(spMenuNm[2], sMenu.getBi_portal_menu_id()); // 두번째
																													// //
							Menu pMenu = menuService.getParentMenuReturn(spMenuNm, "#", k, 0);																			// 서비스ID

							int menuGroupCnt = menuGroupService.getMenuGroupMappCnt(menuGroup.getBi_menu_group_id(), pMenu.getBi_portal_menu_id());

							if (menuGroupCnt == 0) {
								
								

								logger.info("2레벨 그룹ID" + menuGroup.getBi_menu_group_id());
								logger.info("2레벨  메뉴ID" + pMenu.getBi_portal_menu_id());
								logger.info("2레벨  a메뉴명" + pMenu.getBi_menu_nm());

								menuGroupMapp.setBi_menu_group_id(menuGroup.getBi_menu_group_id());
								menuGroupMapp.setBi_portal_menu_id(pMenu.getBi_portal_menu_id());
								menuGroupService.menuGroupMappSave(menuGroupMapp);
							}

						} else if (k == 3) {

							Menu pMenu = menuService.getParentMenuReturn(spMenuNm, "#", k, 0);		
																											

							int menuGroupCnt = menuGroupService.getMenuGroupMappCnt(menuGroup.getBi_menu_group_id(), pMenu.getBi_portal_menu_id());

							if (menuGroupCnt == 0) {

								logger.info("3레벨 그룹ID" + menuGroup.getBi_menu_group_id());
								logger.info("3레벨  메뉴ID" + pMenu.getBi_portal_menu_id());
								logger.info("3레벨  a메뉴명" + pMenu.getBi_menu_nm());

								menuGroupMapp.setBi_menu_group_id(menuGroup.getBi_menu_group_id());
								menuGroupMapp.setBi_portal_menu_id(pMenu.getBi_portal_menu_id());
								menuGroupService.menuGroupMappSave(menuGroupMapp);

							}

						}
 */
					}
					
				

				}

			}
			
		}
		  
		}
		ModelAndView mav = new ModelAndView();

		mav.addObject("msg", "등록되었습니다.");

		mav.setViewName("jsonView");
		return mav;

	}

	// 5.메뉴그룹 사용자 그룹 맵핑
	@RequestMapping(value = "/setData/getMenuUserGroupMappExcel.do", method = RequestMethod.POST)
	public ModelAndView getMenuUserGroupMappExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Group group = new Group();
		MenuGroup menuGroup = new MenuGroup();
		MenuUserGroupMapp menuUserGroupMapp = new MenuUserGroupMapp();

		ExcelUtil excelUtil = new ExcelUtil();

		String Excelfile = request.getRealPath("/file/portalSet/portalSet.xlsx");
		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);

		XSSFSheet sheet = workBook.getSheetAt(3); // 5번쉬트
		for (int rowNum = 1; rowNum < sheet.getLastRowNum(); rowNum++) {

			XSSFRow rows = sheet.getRow(rowNum);

			// 메뉴그룹
			String menuGroupNm = excelUtil.getCellTypeVal(rows.getCell(0)); // 메뉴그룹
			menuGroup = menuGroupService.getMenuGroupNm(menuGroupNm);

			for (int cellNum = 1; cellNum < rows.getLastCellNum(); cellNum++) {

				XSSFCell cells = rows.getCell(cellNum); // O,X
				String mappType = excelUtil.getCellTypeVal(cells); // O,X

				if (mappType.equals("O")) {

					XSSFRow userGroupRow = sheet.getRow(0); // 사용자 그룹명
					String userGroupNm = excelUtil.getCellTypeVal(userGroupRow.getCell(cellNum)); // 그룹명
					group = groupService.getGroupNm(userGroupNm); // 그룹명

					logger.info("사용자그룹ID: " + menuGroup.getBi_menu_group_id());
					logger.info("사용자그룹명: " + menuGroup.getBi_menu_group_nm());
					logger.info("메뉴그룹ID: " + group.getBi_group_id());
					logger.info("메뉴그룹명: " + group.getBi_group_nm());

					int num = menuGroupService.getMenuUserGroupMappCnt(menuGroup.getBi_menu_group_id(), group.getBi_group_id());

					if (num > 0) {

						menuGroupService.getMenuUserGroupMappDelete(menuGroup.getBi_menu_group_id(), group.getBi_group_id()); // 일단
																																// //
																																// 메뉴그룹을

					}

					menuUserGroupMapp.setBi_group_id(group.getBi_group_id());
					menuUserGroupMapp.setBi_menu_group_id(menuGroup.getBi_menu_group_id());

					menuGroupService.menuUserGroupMappSave(menuUserGroupMapp);

				}

			}

		}

		ModelAndView mav = new ModelAndView();

		mav.addObject("msg", "등록되었습니다.");

		mav.setViewName("jsonView");

		return mav;

	}



	// 1. 전체 메뉴 등록
	@RequestMapping(value = "/setData/getAllMenuMakeExcel.do", method = RequestMethod.POST)
	public ModelAndView getMenuAllMakeExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();

		int delCnt = menuService.getMenuTypeCnt("M");

		if (delCnt > 0) {

			menuService.deleteMenuAll("M"); // 포탈 기본메뉴 삭제
		}

		List<String> metaName = Arrays.asList("menuNm", "menuType", "url", "sort", "linkType", "topMenuView"); // 포탈메뉴

		String Excelfile = request.getRealPath("/file/portalSet/portalMenuList.xlsx");
		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);

		// 맨 상위 데이터 넣기
		List pExcelData = excelUtil.readExcel(workBook, 0, metaName);// 엑셀

		for (int rowNum = 1; rowNum < pExcelData.size(); rowNum++) { // 최상위
																		// 메뉴
			HashMap hm = (HashMap) pExcelData.get(rowNum); // 설정

			int menuCnt = menuService.getTotalCount();

			String currentId = String.valueOf(menuCnt + 1);

			String sort = hm.get("sort").toString(); // 메뉴이름
			String[] sortSp = sort.split("\\.");

			String linkType = hm.get("linkType").toString(); // 메뉴이름
			String[] linkTypeSp = linkType.split("\\.");

			String url = null;

			url = hm.get("url").toString();
			if (url.equals("not")) {

				url = "";

			} else {

				url = url + "('" + currentId + "');";

			}

			logger.info("url:" + url);

			Menu menu = new Menu();
			menu.setBi_menu_type_yn(hm.get("menuType").toString()); // 포탈 코드 M
			menu.setBi_portal_menu_parent_id("#"); // 최상위 경우 "#"
			menu.setBi_portal_menu_id(currentId); // 메뉴ID
			menu.setBi_menu_nm(hm.get("menuNm").toString()); // 메뉴명
			menu.setBi_menu_sort_sn(Integer.valueOf(sortSp[0])); // 메뉴순서
			menu.setBi_menu_fm_yn(linkTypeSp[0]); // 메뉴 type
			menu.setBi_menu_url_addr(url);
			menu.setBi_top_view_yn(hm.get("topMenuView").toString());

			menuService.save(menu);

		}
		// 맨 상위 데이터 넣기
		// 대메뉴 등록
		// 하위메뉴 등록

		List<String> metaName1 = Arrays.asList("menuNm", "menuType", "url", "sort", "linkType"); // 포탈메뉴
		
		for (int sheetNum = 1; sheetNum < (workBook.getNumberOfSheets() - 1); sheetNum++) { // 최상위

			String sheetName = workBook.getSheetName(sheetNum);
			Menu parentMenu = menuService.getParentMenuNm(sheetName, "#");

			List excelData = excelUtil.readExcel(workBook, sheetNum, metaName1);// 엑셀
																				// 파일

			for (int rowNum = 1; rowNum < excelData.size(); rowNum++) {

				Menu curMenu = new Menu();

				HashMap hm = (HashMap) excelData.get(rowNum);

				String linkType = null;
				String url = null;
				String sort = null;
				String menuType = null;

				String menuNm = hm.get("menuNm").toString(); // 메뉴이름
				String[] menuNmSp = menuNm.split(">"); //폴더 dept 

				sort = hm.get("sort").toString(); // 메뉴이름
				String[] sortSp = sort.split("\\.");

				linkType = hm.get("linkType").toString(); // 메뉴이름
				String[] linkTypeSp = linkType.split("\\.");

				logger.info("갯수:: " + menuNmSp.length);

				if (menuNmSp.length > 1) {

					for (int num = 0; num < menuNmSp.length; num++) {

						int menuCnt = menuService.getTotalCount();
						String currentId = String.valueOf(menuCnt + 1);
						menuType = hm.get("menuType").toString(); // 메뉴type

						if (num == 0) { // 첫번째 일때

							int menuNum = menuService.getMenuCnt(menuNmSp[num], parentMenu.getBi_portal_menu_id());
							if (menuNum == 0) {

								curMenu.setBi_portal_menu_parent_id(parentMenu.getBi_portal_menu_id()); // 부모아이디
								curMenu.setBi_portal_menu_id(currentId);
								curMenu.setBi_menu_nm(menuNmSp[num]);
								curMenu.setBi_menu_type_yn(hm.get("menuType").toString());
								curMenu.setBi_menu_sort_sn(Integer.valueOf(sortSp[0]));
								curMenu.setBi_menu_fm_yn(linkTypeSp[0]);
								menuService.save(curMenu);

								logger.info("+++++++++++++++++++++++++++++" + sheetName + "+++++++++++++++++++++++++++++++++++++");
								logger.info("메뉴타입: " + curMenu.getBi_menu_type_yn());
								logger.info("부모ID: " + curMenu.getBi_portal_menu_parent_id()); // 부모아이디
								logger.info("ID: " + curMenu.getBi_portal_menu_id());
								logger.info("메뉴명: " + curMenu.getBi_menu_nm());
								logger.info("정렬: " + curMenu.getBi_menu_sort_sn());
								logger.info("메뉴구성TYPE: " + curMenu.getBi_menu_fm_yn());
								logger.info("URL: " + curMenu.getBi_menu_url_addr());
								logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
							}

						} else if (num == 1) { // 마지막일때.

							Menu pMenu = menuService.getParentMenuNm(menuNmSp[0], parentMenu.getBi_portal_menu_id()); // 1단계
							// Menu sMenu =
							// menuService.getParentMenuNm(menuNmSp[1],
							// pMenu.getBi_portal_menu_id()); //2 단계

							int menuNum = menuService.getMenuCnt(menuNmSp[num], pMenu.getBi_portal_menu_id());

							if (menuNum == 0) {

								curMenu.setBi_portal_menu_parent_id(pMenu.getBi_portal_menu_id()); // 부모아이디
								curMenu.setBi_portal_menu_id(currentId);
								curMenu.setBi_menu_nm(menuNmSp[num]);

								if (menuNmSp.length == 2) {

									curMenu.setBi_menu_url_addr(hm.get("url").toString());
									curMenu.setBi_menu_type_yn(hm.get("menuType").toString());
									curMenu.setBi_menu_sort_sn(Integer.valueOf(sortSp[0]));
									curMenu.setBi_menu_fm_yn(linkTypeSp[0]);
									
								} else {

									curMenu.setBi_menu_url_addr("");
									curMenu.setBi_menu_type_yn(hm.get("menuType").toString());
									curMenu.setBi_menu_sort_sn(Integer.valueOf(sortSp[0]));
									curMenu.setBi_menu_fm_yn("2");
								}

								menuService.save(curMenu);
							}

							logger.info("+++++++++++++++++++++++++++" + sheetName + "++++++++++++++++++++++++++++++++");
							logger.info("메뉴타입: " + curMenu.getBi_menu_type_yn());
							logger.info("부모ID: " + curMenu.getBi_portal_menu_parent_id()); // 부모아이디
							logger.info("ID: " + curMenu.getBi_portal_menu_id());
							logger.info("메뉴명: " + curMenu.getBi_menu_nm());
							logger.info("정렬: " + curMenu.getBi_menu_sort_sn());
							logger.info("메뉴구성TYPE: " + curMenu.getBi_menu_fm_yn());
							logger.info("URL: " + curMenu.getBi_menu_url_addr());
							logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

						} else if (num == 2) { // 마지막일때.

							Menu pMenu = menuService.getParentMenuNm(menuNmSp[0], parentMenu.getBi_portal_menu_id());
							Menu sMenu = menuService.getParentMenuNm(menuNmSp[1], pMenu.getBi_portal_menu_id());
							// Menu tMenu =
							// menuService.getParentMenuNm(menuNmSp[2],
							// sMenu.getBi_portal_menu_id());

							int menuNum = menuService.getMenuCnt(menuNmSp[num], sMenu.getBi_portal_menu_id());

							if (menuNum == 0) {

								curMenu.setBi_portal_menu_parent_id(sMenu.getBi_portal_menu_id()); // 부모아이디
								curMenu.setBi_portal_menu_id(currentId);
								curMenu.setBi_menu_nm(menuNmSp[num]);
								curMenu.setBi_menu_url_addr(hm.get("url").toString());
								curMenu.setBi_menu_type_yn(hm.get("menuType").toString());
								curMenu.setBi_menu_sort_sn(Integer.valueOf(sortSp[0]));
								curMenu.setBi_menu_fm_yn(linkTypeSp[0]);
								menuService.save(curMenu);

								logger.info("+++++++++++++++++++++++++++" + sheetName + "++++++++++++++++++++++++++++++++");
								logger.info("메뉴타입: " + curMenu.getBi_menu_type_yn());
								logger.info("부모ID: " + curMenu.getBi_portal_menu_parent_id()); // 부모아이디
								logger.info("ID: " + curMenu.getBi_portal_menu_id());
								logger.info("메뉴명: " + curMenu.getBi_menu_nm());
								logger.info("정렬: " + curMenu.getBi_menu_sort_sn());
								logger.info("메뉴구성TYPE: " + curMenu.getBi_menu_fm_yn());
								logger.info("URL: " + curMenu.getBi_menu_url_addr());
								logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

							}

						}

					}

				} else {

					int menuCnt = menuService.getTotalCount();
					String currentId = String.valueOf(menuCnt + 1);
					curMenu.setBi_portal_menu_parent_id(parentMenu.getBi_portal_menu_id()); // 부모아이디
					curMenu.setBi_portal_menu_id(currentId);
					curMenu.setBi_menu_nm(menuNm);
					curMenu.setBi_menu_url_addr(hm.get("url").toString());
					curMenu.setBi_menu_type_yn(hm.get("menuType").toString());
					curMenu.setBi_menu_sort_sn(Integer.valueOf(sortSp[0]));
					curMenu.setBi_menu_fm_yn(linkTypeSp[0]);

					logger.info("++++++++++++++++++++++++" + sheetName + "++++++++++++++++++++++++++++++");
					logger.info("메뉴타입: " + curMenu.getBi_menu_type_yn());
					logger.info("부모ID: " + curMenu.getBi_portal_menu_parent_id()); // 부모아이디
					logger.info("ID: " + curMenu.getBi_portal_menu_id());
					logger.info("메뉴명: " + curMenu.getBi_menu_nm());
					logger.info("정렬: " + curMenu.getBi_menu_sort_sn());
					logger.info("메뉴구성TYPE: " + curMenu.getBi_menu_fm_yn());
					logger.info("URL: " + curMenu.getBi_menu_url_addr());
					logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

					menuService.save(curMenu);

				}

			}
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "등록되었습니다.");
		mav.setViewName("jsonView");

		return mav;

	}

	// 2. 보고서메뉴 전체 등록
	@RequestMapping(value = "/setData/getAllReportMakeExcel.do", method = RequestMethod.POST)
	public ModelAndView getAllReportMakeExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelUtil excelUtil = new ExcelUtil();

		int delCnt = menuService.getMenuTypeCnt("R");

		if (delCnt > 0) {

			menuService.deleteMenuAll("R"); // 포탈 기본메뉴 삭제
		}

		String Excelfile = request.getRealPath("/file/portalSet/portalMenuList.xlsx");

		XSSFWorkbook workBook = excelUtil.getWorkBook(Excelfile);
		List<String> metaName = Arrays.asList("category1", "category2", "reportNm", "prompt", "cashing", "basic", "type1", "type2", "reporturl", "menuNm", "keyword", "desc"); // 포탈메뉴

		List excelData = excelUtil.readExcel(workBook, 7, metaName);// 엑셀 파일

		Menu menu = menuService.getParentMenuNm("서비스메뉴", "#");

		for (int rowNum = 1; rowNum < excelData.size(); rowNum++) {

			Menu curMenu = new Menu();
			HashMap hm = (HashMap) excelData.get(rowNum);
			String menuNm = hm.get("category1").toString()+"/"+hm.get("category2").toString()+"/"+hm.get("reportNm").toString(); // 메뉴이름
			String[] menuNmSp = menuNm.split("/");

			logger.info("갯수:: " + menuNmSp.length);

			if (menuNmSp.length > 1) {

				for (int num = 0; num < menuNmSp.length; num++) {

					int menuCnt = menuService.getTotalCount();
					String currentId = String.valueOf(menuCnt + 1);

					// Menu tMenu = menuService.getParentMenuNm(menuNmSp[2],
					// sMenu.getBi_portal_menu_id()); //3번째 서비스ID

					if (num == 0) { // 첫번째 일때

						int menuNum = menuService.getMenuCnt(menuNmSp[num], menu.getBi_portal_menu_id());
						if (menuNum == 0) {

							curMenu.setBi_menu_type_yn("R"); // 형태
							curMenu.setBi_portal_menu_parent_id(menu.getBi_portal_menu_id()); // 부모아이디
							curMenu.setBi_portal_menu_id(currentId); // 현재 ID
							curMenu.setBi_menu_nm(menuNmSp[num]); // 메뉴명
							curMenu.setBi_menu_url_addr(""); // 포탈 위치 url
							curMenu.setBi_menu_fm_yn("2"); // 메뉴타입
							curMenu.setBi_menu_sort_sn(Integer.valueOf(rowNum)); // 순서
							curMenu.setBi_inqire_yn("N"); // 프로픔트 유무
							menuService.save(curMenu);

							logger.info("+++++++++++++++++++++++++++++  1 레벨  +++++++++++++++++++++++++++++++++++++");
							logger.info("메뉴타입: " + curMenu.getBi_menu_type_yn());
							logger.info("부모ID: " + curMenu.getBi_portal_menu_parent_id()); // 부모아이디
							logger.info("ID: " + curMenu.getBi_portal_menu_id());
							logger.info("메뉴명: " + curMenu.getBi_menu_nm());
							logger.info("URL: " + curMenu.getBi_menu_url_addr());
							logger.info("정렬: " + curMenu.getBi_menu_fm_yn());
							logger.info("정렬: " + curMenu.getBi_menu_sort_sn());
							logger.info("메뉴구성TYPE: " + curMenu.getBi_inqire_yn());
							logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						}

					} else if (num == 1) { // 마지막일때.

						Menu pMenu = menuService.getParentMenuNm(menuNmSp[0], menu.getBi_portal_menu_id()); // 서비스ID
						int menuNum = menuService.getMenuCnt(menuNmSp[num], pMenu.getBi_portal_menu_id());
						if (menuNum == 0) {

							curMenu.setBi_menu_type_yn("R"); // 형태
							curMenu.setBi_portal_menu_parent_id(pMenu.getBi_portal_menu_id()); // 부모아이디
							curMenu.setBi_portal_menu_id(currentId); // 현재 ID
							curMenu.setBi_menu_nm(menuNmSp[num]); // 메뉴명
							curMenu.setBi_menu_url_addr("/ts/report/gotoPage.do?pageNm=subList"); // 포탈
							curMenu.setBi_menu_fm_yn("5"); // 메뉴타입
							curMenu.setBi_menu_sort_sn(Integer.valueOf(rowNum)); // 순서
							curMenu.setBi_inqire_yn("N"); // 프로픔트 유무
							menuService.save(curMenu);

							logger.info("+++++++++++++++++++++++++++++  2 레벨  +++++++++++++++++++++++++++++++++++++");
							logger.info("메뉴타입: " + curMenu.getBi_menu_type_yn());
							logger.info("부모ID: " + curMenu.getBi_portal_menu_parent_id()); // 부모아이디
							logger.info("ID: " + curMenu.getBi_portal_menu_id());
							logger.info("메뉴명: " + curMenu.getBi_menu_nm());
							logger.info("URL: " + curMenu.getBi_menu_url_addr());
							logger.info("정렬: " + curMenu.getBi_menu_fm_yn());
							logger.info("정렬: " + curMenu.getBi_menu_sort_sn());
							logger.info("메뉴구성TYPE: " + curMenu.getBi_inqire_yn());
							logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

						}

					} else if (num == 2) { // 마지막일때.

						Menu pMenu = menuService.getParentMenuNm(menuNmSp[0], menu.getBi_portal_menu_id()); // 서비스I
						Menu sMenu = menuService.getParentMenuNm(menuNmSp[1], pMenu.getBi_portal_menu_id()); // 두번째
																												// 서비스ID

						int menuNum = menuService.getMenuCnt(menuNmSp[num], sMenu.getBi_portal_menu_id());

						if (menuNum == 0) {

							curMenu.setBi_menu_type_yn("R"); // 형태
							curMenu.setBi_portal_menu_parent_id(sMenu.getBi_portal_menu_id()); // 부모아이디
							curMenu.setBi_portal_menu_id(currentId); // 현재 ID
							curMenu.setBi_menu_nm(menuNmSp[num]); // 메뉴명
							curMenu.setBi_menu_url_addr("/ts/report/gotoPage.do?pageNm=list"); // 포탈
																								// 위치
																								// url
							curMenu.setBi_menu_fm_yn("3"); // 메뉴타입
							curMenu.setBi_menu_sort_sn(Integer.valueOf(rowNum)); // 순서
							curMenu.setBi_inqire_yn("Y"); // 프로픔트 유무
							curMenu.setBi_object_nm(hm.get("type1").toString() + "=" + hm.get("type2").toString()); // 프로픔트
																													// 유무
							String param = hm.get("prompt").toString();
							curMenu.setBi_inqire_param(param.replaceAll(":", ","));
							curMenu.setBi_search_keyword(hm.get("keyword").toString());
							curMenu.setBi_dc(hm.get("desc").toString());
							menuService.save(curMenu);

							logger.info("+++++++++++++++++++++++++++++  3 레벨  +++++++++++++++++++++++++++++++++++++");
							logger.info("메뉴타입: " + curMenu.getBi_menu_type_yn());
							logger.info("부모ID: " + curMenu.getBi_portal_menu_parent_id()); // 부모아이디
							logger.info("ID: " + curMenu.getBi_portal_menu_id());
							logger.info("메뉴명: " + curMenu.getBi_menu_nm());
							logger.info("URL: " + curMenu.getBi_menu_url_addr());
							logger.info("정렬: " + curMenu.getBi_menu_fm_yn());
							logger.info("정렬: " + curMenu.getBi_menu_sort_sn());
							logger.info("프롬프트: " + curMenu.getBi_inqire_yn());
							logger.info("object: " + curMenu.getBi_object_nm());
							logger.info("param: " + curMenu.getBi_inqire_param());
							logger.info("검색 " + curMenu.getBi_search_keyword());
							logger.info("설명 " + curMenu.getBi_dc());

							logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						}

					}

				}

			}

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "등록되었습니다.");
		mav.setViewName("jsonView");

		return mav;

	}

	// 1.메뉴 초기화 전체 삭제
	@RequestMapping(value = "/setData/getMenuDeleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteMenuAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("삭제 시작.");

		ModelAndView mav = new ModelAndView();

		int delNum = menuService.deleteAll();

		mav.addObject("data", "삭제되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	// 2.메뉴그룹 삭제
	@RequestMapping(value = "/setData/getMenuGroupDeleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteMenuGroupAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("삭제 시작.");

		ModelAndView mav = new ModelAndView();

		int delNum = menuGroupService.deleteAll();

		mav.addObject("data", "삭제되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	// 3.메뉴그룹 메뉴 맵핑삭제
	@RequestMapping(value = "/setData/getMenuGroupMappDeleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteMenuGroupMappAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("삭제 시작.");

		ModelAndView mav = new ModelAndView();

		int delNum = menuGroupService.deleteMenuGroupMappAll();

		mav.addObject("data", "삭제되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	// 4.메뉴그룹사용자그룹 맵핑 삭제
	@RequestMapping(value = "/setData/getMenuGroupUserGroupMappDeleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteMenuGroupUserGroupMappDeleteAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("삭제 시작.");

		ModelAndView mav = new ModelAndView();

		int delNum = menuGroupService.deleteMenuGroupUserGroupMappAll();

		mav.addObject("data", "삭제되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	// 1.사용자그룹 삭제
	@RequestMapping(value = "/setData/getUserGroupDeleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteUserGroupDeleteAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("삭제 시작.");

		ModelAndView mav = new ModelAndView();

		int delNum = groupService.deleteAll();

		mav.addObject("data", "삭제되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	// 2.사용자그룹 삭제
	@RequestMapping(value = "/setData/getUserGroupDeptMappDeleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteUserGroupDeptMappDeleteAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("삭제 시작.");

		ModelAndView mav = new ModelAndView();

		int delNum = groupService.deleteUgDeptMappAll();

		mav.addObject("data", "삭제되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	// 3.사용자그룹 사용자 맵핑 삭제
	@RequestMapping(value = "/setData/getUserGroupUserMappDeleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteUserGroupUserMappDeleteAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("삭제 시작.");

		ModelAndView mav = new ModelAndView();

		int delNum = groupService.deleteUgUserMappAll();

		mav.addObject("data", "삭제되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	// 1.사용자 전체 삭제
	@RequestMapping(value = "/setData/getdeleteUserAll.do", method = RequestMethod.POST)
	public ModelAndView deleteUserAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("삭제 시작.");

		ModelAndView mav = new ModelAndView();

		int delNum = userInfoService.deleteAll();

		mav.addObject("data", "삭제되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	// 2.로그인 사용자사용자 전체 삭제
	@RequestMapping(value = "/setData/getdeleteLoginInfoAll.do", method = RequestMethod.POST)
	public ModelAndView deleteLoginINfoAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("삭제 시작.");

		ModelAndView mav = new ModelAndView();

		int delNum = securityService.deleteAll();

		mav.addObject("data", "삭제되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	// 3. 조직 전체 삭제
	@RequestMapping(value = "/setData/getdeleteDeptAll.do", method = RequestMethod.POST)
	public ModelAndView deleteDeptAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("삭제 시작.");

		ModelAndView mav = new ModelAndView();

		int delNum = deptService.deleteAll();

		mav.addObject("data", "삭제되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}


	// 5. 메인화면 설정 전체 삭제
	@RequestMapping(value = "/setData/getDeleteBookMark.do", method = RequestMethod.POST)
	public ModelAndView deleteBookMarkAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("삭제 시작.");

		ModelAndView mav = new ModelAndView();

		int delNum = bookMarkService.deleteAll();

		mav.addObject("data", "삭제되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	
	public String splitDat(String word){
		
	
		String spWord[] = word.split("\\.");
		return spWord[0];
	}

}
