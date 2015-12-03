package com.zetta.group.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.common.BoardUtil;
import com.zetta.common.ExcelUtil;
import com.zetta.dept.model.Dept;
import com.zetta.dept.service.DeptService;
import com.zetta.group.model.DeptGroup;
import com.zetta.group.model.Group;
import com.zetta.group.model.UserGroup;
import com.zetta.group.service.GroupService;




@Controller
public class GroupController {
	
	public Logger logger = Logger.getLogger(getClass());
	
	//private static final String DEFAULT_CURRENT_PAGE = "1";
	//private static final String DEFAULT_COUNT_PER_PAGE = "10";

	@Autowired
	GroupService groupService;
	
	@Autowired
	DeptService deptService;

	

	@RequestMapping(value = "/admin/group/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			@RequestParam(value="pageNm", required = false, defaultValue = "") String pageNm
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		if(pageNm.equals("") || pageNm == null){
			
			pageNm = "redirect:pageError.do";
			
		}else{
			
			pageNm = "admin/group/"+pageNm;
			
		}
		
//	
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName(pageNm);			
		//mav.addObject("topMenuList", menuMngService.topMenuList()); //상단메뉴 실행 
		return mav;
		
	}
	
	

	@RequestMapping(value="/admin/group/getListData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView list(
			 @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			,@RequestParam(value="countPerPage", required = false, defaultValue = "10") int countPerPage //페이지당 뿌릴 데이터 
			,@RequestParam(value="bi_group_id", required = false, defaultValue = "0") String bi_group_id
			,@RequestParam(value="sord", required = false, defaultValue = "") String sord
			,@RequestParam(value="searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value="searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		

		logger.info("menuMngList.do Start");
						
		//String strToday = BoardUtil.getDate(0);
		
		int totalNo = groupService.getTotalCount(searchTitle, searchContent);
		
		int endPage = 0;
		int startRow =	0;	
		int endRow = 0;
		if(totalNo > 0){
		
		 endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo);  // 마지막 페이지 
		
		 startRow = BoardUtil.startRow(currentPage, countPerPage);  // 시작 row
		
		 endRow = BoardUtil.endRow(totalNo, countPerPage, endPage);  //끝 row
		
		} 
	

		logger.info("startRow" + startRow);
		logger.info("endRow" + endRow);
		logger.info("endPage" + endPage);
		
		
		ModelAndView mav = new ModelAndView();	
		
		//mav.addObject("pageDivideForm", pageDivideForm);
		 mav.addObject("currentPage", currentPage);
		 mav.addObject("totalPage", endPage);
		 mav.addObject("totalNo", totalNo);	
		
		mav.addObject("rows", groupService.findAllList(startRow, endRow, searchTitle, searchContent));
		logger.info("menuMngList:: ");
		logger.info("menuMngList.do end");
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}


	@RequestMapping(value = "/admin/group/getViewData.do", method = RequestMethod.POST)
	public ModelAndView view(
			@RequestParam(value="bi_group_id", required = false, defaultValue = "") String bi_group_id
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//	
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
	
		ModelAndView mav = new ModelAndView();	
		
		mav.addObject("rows",  groupService.findList(bi_group_id));
		mav.addObject("prevTitle", "");
		mav.addObject("nextTitle", "");
		mav.setViewName("jsonView");	
	
					
		return mav;	
	}
	
	
	/**
	 * 자료실  (등록하기)
	 * @param request
	 * @param response
	 * @return view
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/group/save.do", method = RequestMethod.POST)
	public ModelAndView save(
			  @ModelAttribute("group") Group group 
			, BindingResult result			
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		

		String ugCode = "UG_"+UUID.randomUUID().toString();
		
		// 해당 코드가 있으면 삭제 함 
		int ugdmCnt = groupService.ugDeptMappCnt(ugCode);
		if(ugdmCnt > 0){
			
			groupService.ugDeptMappDelete(ugCode); // 맵핑된 조직 삭제됩니다.
			
		}
		
		// 해당 코드가 있으면 삭제 함 
		int ugUmCnt = groupService.ugDeptMappCnt(ugCode);
		if(ugUmCnt > 0){

			groupService.ugUserMappDelete(ugCode); // 맵핑된 조직 삭제됩니다.
			
		}
		
		
		
		logger.info("사용자 그룹 코드 " + ugCode);
		group.setBi_group_id(ugCode);
		if(group.getBi_init_yn() == null || !group.getBi_init_yn().equals("Y")){
			group.setBi_init_yn("N"); // Y - 초기 설정 그룹
		}
		groupService.save(group);		
		ModelAndView mav = new ModelAndView();		
		mav.addObject("pageNm","list");
		mav.setViewName("redirect:/admin/group/gotoPage.do");			
		return mav;
		
	}
	
	

	@RequestMapping(value = "/group/modify.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView modify(
			 @ModelAttribute("group") Group group
			, BindingResult result		
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
			
	
		groupService.update(group);
		ModelAndView mav = new ModelAndView();		
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/admin/group/gotoPage.do");			
		return mav;
	}

	
	@RequestMapping(value = "/admin/group/editForm.do", method = RequestMethod.POST)
	public ModelAndView editForm(
			@RequestParam(value="bi_group_id", required = false, defaultValue = "") String bi_group_id
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
		ModelAndView mav = new ModelAndView();	
		
		mav.addObject("group", groupService.getById(bi_group_id));
		mav.setViewName("redirect:menuMngList.do");	
		
		return mav;
	}

	@RequestMapping(value = "/admin/group/modify.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView adminModify(
			@ModelAttribute("group") Group group
			, BindingResult result	
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
	   
	
		groupService.update(group);
		ModelAndView mav = new ModelAndView();		
		mav.addObject("msg","수정완료");
		mav.setViewName("jsonView");		
		
		return mav;
	}
	
	@RequestMapping(value = "/admin/group/deleteForm.do", method = RequestMethod.POST)
	public ModelAndView deleteForm(
			@RequestParam(value="bi_group_id", required = false, defaultValue = "") String bi_group_id
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
//		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
		ModelAndView mav = new ModelAndView();			
		
		mav.addObject("menuCode", groupService.getById(bi_group_id));
		mav.setViewName("/admin/group/delete");	
		
		return mav;

	}

	@RequestMapping(value = "/admin/group/delete.do", method = RequestMethod.POST)
	public ModelAndView delete(
			@RequestParam(value="bi_group_id", required = false, defaultValue = "") String bi_group_id
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("entering 'remove' method...");
		}

//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
		if (logger.isDebugEnabled()) {
			logger.debug("boardNo : " + bi_group_id);
		}

	
		//if (password.equals(board.getPassword())) {
		groupService.remove(bi_group_id);
		
		ModelAndView mav = new ModelAndView();			
		
		mav.addObject("pageNm","list");
		mav.setViewName("redirect:/admin/group/gotoPage.do");			
		
		return mav;
			
		
	}
	

	
	@RequestMapping(value="/admin/group/getGroupListData.do" , method = RequestMethod.GET)  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView getGrouplist(
			HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		

		logger.info("menuMngList.do Start");
						
		//String strToday = BoardUtil.getDate(0);
	
	
		ModelAndView mav = new ModelAndView();	
		
		//mav.addObject("pageDivideForm", pageDivideForm);
		List<Group> getData = groupService.getGroupList();
		
		logger.info("getData"+ getData);
		
		mav.addObject("rows", getData);
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}
	
	

	@RequestMapping(value = "/admin/group/getMenuGroupData.do", method = RequestMethod.POST)
	public ModelAndView getMenuGroupData(
			 @RequestParam(value="bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id			 
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ModelAndView mav = new ModelAndView();	
		int cnt =  groupService.getMenuGroupCnt(bi_portal_menu_id);
		logger.info("bi_group_id::" + bi_portal_menu_id);
		logger.info("cnt::" + cnt);
		mav.addObject("rows",  groupService.getMenuGroupList(bi_portal_menu_id));
		mav.setViewName("jsonView");	
					
		return mav;	
	}
                             
	
	
	
	
	                              
	@RequestMapping(value="/admin/group/getMenuGroupMapngData.do" , method = RequestMethod.POST)  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView getMenuGroupMapngData(
			@RequestParam(value="bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		

		logger.info("menuMngList.do Start");
	
		ModelAndView mav = new ModelAndView();	
		
		mav.addObject("rows", groupService.getMenuGroupMapngList(bi_portal_menu_id));
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}
	
	
	@RequestMapping(value="/admin/group/getMenuGroupRoleData.do" , method = RequestMethod.POST)  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView getMenuGroupRoleData(
			@RequestParam(value="bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		

		logger.info("menuMngList.do Start");
	
		ModelAndView mav = new ModelAndView();	
		
		mav.addObject("rows", groupService.getMenuGroupRoleList(bi_portal_menu_id));
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}
	
	

	@RequestMapping(value = "/admin/group/deleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteAll(
			 @RequestParam(value="checkId", required = false, defaultValue = "") String[] checkId
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		int ugdmCnt = 0;
		int ugUmCnt = 0;
       
		for(int i=0; i<checkId.length; i++){
			logger.info("선택된 값" + checkId[i]);
			groupService.remove(checkId[i]);
			//사용자그룹 삭제시   맵핑된 조직 삭제
			ugdmCnt = groupService.ugDeptMappCnt(checkId[i]);
			if(ugdmCnt > 0){
				
				groupService.ugDeptMappDelete(checkId[i]); // 맵핑된 조직 삭제됩니다.
				
			}
			
			ugUmCnt = groupService.ugDeptMappCnt(checkId[i]);
			if(ugUmCnt > 0){
	
				groupService.ugUserMappDelete(checkId[i]); // 맵핑된 조직 삭제됩니다.
				
			}
			
			//사용자 그룹 삭제시 맵핑된 사용자 삭제 
			
			
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows",  "삭제 되었습니다.");
		mav.setViewName("jsonView");	
		return mav;
	}

	
	@Value("#{qvconf['qlikview.excelDataPath']}")
	private String excelDataPath;

	@RequestMapping(value = "/admin/group/makeGroupDeptExcel.do")
	public ModelAndView makeGroupDeptExcel(
			HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		XSSFWorkbook wb = new XSSFWorkbook();       
		XSSFSheet sheet = wb.createSheet(URLEncoder.encode("그룹조직맵핑","UTF-8"));
		
		//sheet.createRow("")
		Row r = sheet.createRow(0);
		List<Group> groupList = groupService.getGroupList();
		
		for(int i=0;i<groupList.size();i++){
			
			  Cell c = r.createCell(i+1);
			  c.setCellValue( groupList.get(i).getBi_group_nm());
		
			  logger.info("groupList:"+ groupList.get(i).getBi_group_nm());
			
		}
		
		
		List<Dept> deptList = deptService.getDeptList();
		
		for(int i=0;i<deptList.size();i++){
			
			  Row deptRow = sheet.createRow(i+1);
			  Cell c = deptRow.createCell(0);
			  c.setCellValue(deptList.get(i).getBi_deptnm());
			  logger.info("deptList:"+ deptList.get(i).getBi_deptnm());
			
		}
		
		logger.info("wb:" + wb);
		
		
		String filePath = excelDataPath+"\\portal\\groupDept.xlsx";
		

 		try {
 			
 			
 			
 			FileOutputStream stream =  new FileOutputStream(filePath);
 			logger.info("wb:" + stream);
 			wb.write(stream);
 			stream.close();

 		} catch (FileNotFoundException e) {

 			e.printStackTrace();

 		}catch (IOException e) {

 			e.printStackTrace();

 		}
    	
		
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows",  "삭제 되었습니다.");
		mav.setViewName("jsonView");	
		return mav;
	}
	
	


	@RequestMapping(value = "/admin/group/addGroupDeptExcel.do")
	public ModelAndView addGroupDeptExcel(
			HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		ExcelUtil excelUtil = new ExcelUtil();
		String filePath = excelDataPath+"\\portal\\groupDept.xlsx";
		
		XSSFWorkbook workBook = excelUtil.getWorkBook(filePath);      
		XSSFSheet sheet = workBook.getSheetAt(0);
		
		for(int i=1;i<sheet.getLastRowNum();i++){
			
			Row row = sheet.getRow(i);	
			Row keyRow = sheet.getRow(0);
			for(int j=1;j<row.getLastCellNum();j++){
				
				logger.info("그룹명: "+keyRow.getCell(j).getStringCellValue());
				Group group = groupService.getGroupCode(String.valueOf(keyRow.getCell(j)));
				logger.info("그룹ID: "+group.getBi_group_id());
			
				Cell keyCell = row.getCell(0);
				logger.info("부서명: "+keyCell.getStringCellValue());
				Dept dept = deptService.getDeptCode(String.valueOf(keyCell));
				logger.info("그룹ID: "+dept.getBi_dept_id());
				Cell cell = row.getCell(j);
				
				
				
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					logger.info("값: "+cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:

					logger.info("값: "+cell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_STRING:

					logger.info("값: "+cell.getStringCellValue());
					break;
				}
				
				logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				
				
			}
			
			 
			
		}
		
		//sheet.createRow("")
		
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows",  "삭제 되었습니다.");
		mav.setViewName("jsonView");	
		return mav;
	}
	
	//*사용자 그룹 조직 맵핑 ***************8*/
	
	@RequestMapping(value = "/admin/group/getDeptGroupData.do", method = RequestMethod.POST)
	public ModelAndView detpGroupDataList(
			@RequestParam(value = "bi_group_id", required = false, defaultValue = "") String bi_group_id
			,@RequestParam(value = "bi_deptNm", required = false, defaultValue = "") String bi_deptNm
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {

		ModelAndView mav = new ModelAndView();
		int cnt = groupService.ugDeptMappCnt(bi_group_id);		
		mav.addObject("rows", groupService.getDeptGroupList(bi_group_id,bi_deptNm));
		mav.setViewName("jsonView");

		return mav;
	}
	
	
	@RequestMapping(value = "/admin/group/groupDeptsave.do", method = RequestMethod.POST)
	public ModelAndView groupDeptSave(
			@ModelAttribute("groupDept") DeptGroup deptGroup,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.info("값: " + deptGroup.getBi_dept_id());
		logger.info("값: " + deptGroup.getBi_group_id());

		groupService.ugDeptMappDelete(deptGroup.getBi_group_id());

		if (deptGroup.getBi_dept_id() != null) {

			String deptIdSplit[] = deptGroup.getBi_dept_id().split(",");
			for (int i = 0; i < deptIdSplit.length; i++) {

				deptGroup.setBi_dept_id(deptIdSplit[i]);
				groupService.groupDeptSave(deptGroup);

			}

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/admin/group/gotoPage.do");	
		return mav;

	}

	@RequestMapping(value = "/admin/group/getDeptGroupMapngData.do", method = RequestMethod.POST)
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView getDeptGroupMapnglist(
			@RequestParam(value = "bi_group_id", required = false, defaultValue = "") String bi_group_id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.info("menuMngList.do Start");

		ModelAndView mav = new ModelAndView();

		mav.addObject("rows", groupService.getDeptGroupMapngList(bi_group_id));
		mav.setViewName("jsonView");

		return mav;

	}
	
	//*사용자 그룹 조직 맵핑 ***************8*/
	
	@RequestMapping(value = "/admin/group/userGroupsave.do", method = RequestMethod.POST)
	public ModelAndView groupUserSave(
			@ModelAttribute("userGroup") UserGroup userGroup,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		logger.info("값: " + userGroup.getBi_group_id());
		int num = 0;
		num = groupService.ugUserMappCnt(userGroup.getBi_group_id());
		logger.info("값: " + num);
        if(num > 0){
        	groupService.ugUserMappDelete(userGroup.getBi_group_id());
        }
        
		if (userGroup.getBi_group_id() != null) {

			String userIdSplit[] = userGroup.getBi_unity_cust_id().split(",");
			for (int i = 0; i < userIdSplit.length; i++) {

				userGroup.setBi_unity_cust_id(userIdSplit[i]);
				groupService.userGroupSave(userGroup);

			}

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("confirm", "ok");
		mav.setViewName("jsonView");
		return mav;

	}

	
	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 사용자그룹 -사용자 맵핑 현황
	 * @param : groupId[사용자그룹ID]
	 * @return : List
	 * @return :  
	 * 전체 사용자그룹 -사용자 맵핑 현황
	 */
	@RequestMapping(value = "/admin/group/getUgUserMapngData.do", method = RequestMethod.POST)	
	@ResponseBody
	public ModelAndView getUgUserMapngData(			
			@RequestParam(value = "bi_group_id", required = false, defaultValue = "") String bi_group_id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.info("menuMngList.do Start");

		ModelAndView mav = new ModelAndView();

		mav.addObject("rows", groupService.getUgUserMapngData(bi_group_id));
		mav.setViewName("jsonView");

		return mav;

	}

	
	@RequestMapping(value = "/admin/group/getUserGroupList", method = RequestMethod.POST)
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView getUserGroupList(			
			@RequestParam(value = "bi_group_id", required = false, defaultValue = "") String bi_group_id,
			@RequestParam(value = "bi_dept_id", required = false, defaultValue = "") String bi_dept_id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.info("menuMngList.do Start");

		ModelAndView mav = new ModelAndView();

		mav.addObject("rows", groupService.getUserGroupList(bi_dept_id, bi_group_id));
		mav.setViewName("jsonView");

		return mav;

	}
	
	
	
	@RequestMapping(value="/admin/group/getUserGroupListData.do" , method = RequestMethod.POST)  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView getUserGroupListData(
			  @RequestParam(value = "bi_menu_group_id", required = false, defaultValue = "") String bi_menu_group_id
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		

		logger.info("getUserGroupListData.do Start");
						
		//String strToday = BoardUtil.getDate(0);
	
	
		ModelAndView mav = new ModelAndView();	
		
		//mav.addObject("pageDivideForm", pageDivideForm);
		
		
		mav.addObject("rows", groupService.getUserGroupListData(bi_menu_group_id));
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}
	
	
	/*
	 * 관리실 > 사용자 관리 > 사용자그룹목록  : 전체 맵핑 현황
	 * @param :
	 * @return : list(자식 아이디, 부모ID, 명 )
	 * @return : json data 
	 * 사용자 그룹 -조직-사용자 전체 맵핑현황 보기 
	 */
	
	@RequestMapping(value = "/admin/group/getMenuGroupMappAllStatus.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getMenuGroupMappAllStatus(
			  HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
	
		List list = groupService.getMenuGroupMappAllStatus();
		
		logger.info("list"+ list.size());
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", list);		
		mav.setViewName("jsonView");		
		return mav;
		
		
		
		
	}
}
