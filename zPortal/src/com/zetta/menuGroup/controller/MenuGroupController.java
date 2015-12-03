package com.zetta.menuGroup.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.common.BoardUtil;
import com.zetta.menuGroup.model.MenuGroup;
import com.zetta.menuGroup.model.MenuGroupMapp;
import com.zetta.menuGroup.model.MenuUserGroupMapp;
import com.zetta.menuGroup.service.MenuGroupService;





@Controller
public class MenuGroupController {
	
	public Logger logger = Logger.getLogger(getClass());
	
	//private static final String DEFAULT_CURRENT_PAGE = "1";
	//private static final String DEFAULT_COUNT_PER_PAGE = "10";

	@Autowired
	MenuGroupService menuGroupService;

	@RequestMapping(value = "/admin/menuGroup/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			@RequestParam(value="pageNm", required = false, defaultValue = "") String pageNm
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		if(pageNm.equals("") || pageNm == null){
			
			pageNm = "redirect:pageError.do";
			
		}else{
			
			pageNm = "admin/menuGroup/"+pageNm;
			
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
	
	

	@RequestMapping(value="/admin/menuGroup/getListData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView list(
			 @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			,@RequestParam(value="countPerPage", required = false, defaultValue = "10") int countPerPage //페이지당 뿌릴 데이터 
			,@RequestParam(value="bi_menu_group_id", required = false, defaultValue = "0") String bi_menu_group_id
			,@RequestParam(value="sord", required = false, defaultValue = "") String sord
			,@RequestParam(value="searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value="searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		logger.info("menuMngList.do Start");
						
		//String strToday = BoardUtil.getDate(0);
		
		int totalNo = menuGroupService.getTotalCount(searchTitle, searchContent);
		//String pageDivideForm = BoardUtil.dividePageForm(currentPage, countPerPage, totalNo , request, searchTitle, searchContent,"menuMngList.do");
		
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
		
		mav.addObject("rows", menuGroupService.findAllList(startRow, endRow, searchTitle, searchContent));
		logger.info("menuMngList:: ");
		logger.info("menuMngList.do end");
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}


	@RequestMapping(value = "/admin/menuGroup/getViewData.do", method = RequestMethod.POST)
	public ModelAndView view(
			@RequestParam(value="bi_menu_group_id", required = false, defaultValue = "") String bi_menu_group_id
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	
		ModelAndView mav = new ModelAndView();	
		
		mav.addObject("rows",  menuGroupService.findList(bi_menu_group_id));
		mav.addObject("prevTitle", "");
		mav.addObject("nextTitle", "");
		mav.setViewName("jsonView");	
	
					
		return mav;	
	}
	
	
	@RequestMapping(value = "/admin/menuGroup/getMenuGroupList.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView menuGroupList(
			  HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
	
		ModelAndView mav = new ModelAndView();
		
		
		logger.info("시작");
		List<MenuGroup> menuGroupList = menuGroupService.getMenuGroupList();
		logger.info("menuGroupList: " + menuGroupList);
		mav.addObject("rows", menuGroupList);		
		mav.setViewName("jsonView");	
	
					
		return mav;	
	}
	

	

	@RequestMapping(value = "/admin/menuGroup/save.do", method = RequestMethod.POST)
	public ModelAndView save(
			  @ModelAttribute("menuGroup") MenuGroup menuGroup 
			, BindingResult result			
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		

        String menuGroupId = "MG_"+UUID.randomUUID().toString();
		
		logger.info("bi_portal_menu_id" + menuGroupId);
		menuGroup.setBi_menu_group_id(menuGroupId);
		if(menuGroup.getBi_init_yn() == null || !menuGroup.getBi_init_yn().equals("Y")){
			menuGroup.setBi_init_yn("N"); // Y - 초기 설정 그룹
		}
		menuGroupService.save(menuGroup);		
		ModelAndView mav = new ModelAndView();		
		mav.addObject("msg", "저장되었습니다.");
		mav.setViewName("jsonView");	
				
		return mav;
		
	}
	


	@RequestMapping(value = "/menuGroup/modify.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView modify(
			@ModelAttribute("menuGroup") MenuGroup menuGroup
			, BindingResult result		
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
			
	
		menuGroupService.update(menuGroup);
		ModelAndView mav = new ModelAndView();		
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/menuGroup/gotoPage.do");			
		return mav;
	}

	
	@RequestMapping(value = "/admin/menuGroup/editForm.do", method = RequestMethod.POST)
	public ModelAndView editForm(
			@RequestParam(value="bi_menu_group_id", required = false, defaultValue = "") String bi_menu_group_id
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
		ModelAndView mav = new ModelAndView();	
		
		mav.addObject("menuGroup", menuGroupService.getById(bi_menu_group_id));
		mav.setViewName("redirect:menuMngList.do");	
		
		return mav;
	}

	@RequestMapping(value = "/admin/menuGroup/modify.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView adminModify(
			@ModelAttribute("menuGroup") MenuGroup menuGroup
			, BindingResult result	
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
	   
	
		menuGroupService.update(menuGroup);
		ModelAndView mav = new ModelAndView();		
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/admin/menuGroup/gotoPage.do");		
		
		return mav;
	}
	
	@RequestMapping(value = "/admin/menuGroup/deleteForm.do", method = RequestMethod.POST)
	public ModelAndView deleteForm(
			@RequestParam(value="bi_menu_group_id", required = false, defaultValue = "") String bi_menu_group_id
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		ModelAndView mav = new ModelAndView();			
		
		mav.addObject("menuCode", menuGroupService.getById(bi_menu_group_id));
		mav.setViewName("/admin/menuGroup/delete");	
		
		return mav;

	}

	@RequestMapping(value = "/admin/menuGroup/delete.do", method = RequestMethod.POST)
	public ModelAndView delete(
			@RequestParam(value="bi_menu_group_id", required = false, defaultValue = "") String bi_menu_group_id
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("entering 'remove' method...");
		}


		if (logger.isDebugEnabled()) {
			logger.debug("boardNo : " + bi_menu_group_id);
		}

	
		//if (password.equals(board.getPassword())) {
		menuGroupService.remove(bi_menu_group_id);
		
		int totalRowNum = menuGroupService.getMenuGroupMappCnt(bi_menu_group_id);
        
        if(totalRowNum > 0){
        	
        	menuGroupService.getMenuGroupMappDelete(bi_menu_group_id);
        	
        }
		
		ModelAndView mav = new ModelAndView();			
		
		mav.addObject("pageNm","list");
		mav.setViewName("redirect:/admin/menuGroup/gotoPage.do");			
		
		return mav;
	}
	
	

	@RequestMapping(value = "/admin/menuGroup/deleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteAll(
			 @RequestParam(value="checkId", required = false, defaultValue = "") String[] checkId
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		int totalRowNum = 0;
       
		for(int i=0; i<checkId.length; i++){
		
			menuGroupService.remove(checkId[i]);
			
			
			 totalRowNum = menuGroupService.getMenuGroupMappCnt(checkId[i]);
	        
	        if(totalRowNum > 0){
	        	
	        	menuGroupService.getMenuGroupMappDelete(checkId[i]);
	        	
	        }
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows",  "삭제 되었습니다.");
		mav.setViewName("jsonView");	
		return mav;
	}
	

	
	
	@RequestMapping(value = "/admin/menuGroup/menuGroupMappSave.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView menuGroupMappsave(
			  @ModelAttribute("menuGroupMapp") MenuGroupMapp menuGroupMapp
			, @RequestParam(value="bi_menu_group_id", required = false, defaultValue = "") String bi_menu_group_id
			, @RequestParam(value="menuCheckId", required = false, defaultValue = "") String[] menuCheckId
			, BindingResult result			
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
        int totalRowNum = menuGroupService.getMenuGroupMappCnt(bi_menu_group_id);
        
        if(totalRowNum > 0){
        	
        	menuGroupService.getMenuGroupMappDelete(bi_menu_group_id);
        	
        }
        
        ModelAndView mav = new ModelAndView();	
        
        if(menuCheckId.length > 0){
        	
	        for(int i=0; i<menuCheckId.length; i++){
	        	
	        	if(!menuCheckId[i].equals("#")){
	        	
		        	menuGroupMapp.setBi_menu_group_id(bi_menu_group_id);
		        	menuGroupMapp.setBi_portal_menu_id(menuCheckId[i]);
		        	menuGroupService.menuGroupMappSave(menuGroupMapp);	
		        	
	        	}
				
			}
	        
	        mav.addObject("msg", "저장되었습니다.");
			mav.setViewName("jsonView");	
			
	        
        }else{
			
        	mav.addObject("pageNm", "list");
    		mav.setViewName("redirect:/admin/menuGroup/gotoPage.do");	
		
		
        }		
		return mav;
		
	}
	
	
	@RequestMapping(value = "/admin/menuGroup/menuGroupMappList.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView menuGroupMappList(
			 @RequestParam(value="bi_menu_group_id", required = false, defaultValue = "") String bi_menu_group_id
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
   
        List list =  menuGroupService.getMenuGroupMappList(bi_menu_group_id);
        logger.info("list.size:" + list.size());
		ModelAndView mav = new ModelAndView();		
		mav.addObject("data",list);
		mav.setViewName("jsonView");	
				
		return mav;
		
	}
	
	
	
	@RequestMapping(value = "/admin/menuGroup/menuUserGroupMappSave.do", method = RequestMethod.POST)
	public ModelAndView menuUserGroupMappsave(
			  @ModelAttribute("menuUserGroupMapp") MenuUserGroupMapp menuUserGroupMapp
			, @RequestParam(value="bi_menu_group_id", required = false, defaultValue = "") String bi_menu_group_id
			, @RequestParam(value="bi_user_group_id", required = false, defaultValue = "") String[] bi_user_group_id
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
        int totalRowNum = menuGroupService.getMenuUserGroupMappCnt(bi_menu_group_id);
        
        if(totalRowNum > 0){
        	
        	menuGroupService.getMenuUserGroupMappDelete(bi_menu_group_id);
        	
        }
        
        for(int i=0; i<bi_user_group_id.length; i++){
        	
        	if(!bi_user_group_id[i].equals("#")){
        	
        		menuUserGroupMapp.setBi_menu_group_id(bi_menu_group_id);
        		menuUserGroupMapp.setBi_group_id(bi_user_group_id[i]);
	        	menuGroupService.menuUserGroupMappSave(menuUserGroupMapp);	
	        	
        	}
			
		}
		
        
        
		
		
		ModelAndView mav = new ModelAndView();		
		mav.addObject("msg", "수정되었습니다.");
		mav.setViewName("jsonView");	
				
		return mav;
		
	}
	
	
	@RequestMapping(value = "/admin/menuGroup/getMenuUserGroupMappList.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView menuUserGroupMappList(
			 @RequestParam(value="bi_menu_group_id", required = false, defaultValue = "") String bi_menu_group_id
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
   
        List list =  menuGroupService.getMenuUserGroupMappList(bi_menu_group_id);
        logger.info("list.size:" + list.size());
		ModelAndView mav = new ModelAndView();		
		mav.addObject("data",list);
		mav.setViewName("jsonView");	
				
		return mav;
		
	}
	
	
	@RequestMapping(value = "/admin/menuGroup/getMenuGroupMappStatus.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getMenuGroupMappStatus(
		     HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		logger.info("메뉴 맵핑 시작");		
        List<MenuGroup> list =  menuGroupService.getMenuGroupMappStatus();
        logger.info("list.size:" + list.size());
		ModelAndView mav = new ModelAndView();		
		mav.addObject("data",list);
		mav.setViewName("jsonView");	
				
		return mav;
		
	}
	
	
	
}
