package com.zetta.history.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.common.BoardUtil;
import com.zetta.history.service.HistoryService;




@Controller
public class HistoryController {
	
	public Logger logger = Logger.getLogger(getClass());
	

	@Autowired
	HistoryService historyService;
	

	@RequestMapping(value = "/history/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			  @RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm
			, @RequestParam(value = "keyVal", required = false, defaultValue = "") String keyVal
			, @RequestParam(value = "lev1_parent_id", required = false, defaultValue = "") String lev1_parent_id
			, @RequestParam(value = "lev2_parent_id", required = false, defaultValue = "") String lev2_parent_id
			, @RequestParam(value = "lev3_parent_id", required = false, defaultValue = "") String lev3_parent_id
			, @RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
			, @RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
			, @RequestParam(value = "lev1_menuOpen", required = false, defaultValue = "") String lev1_menuOpen
			, @RequestParam(value = "lev2_menuOpen", required = false, defaultValue = "") String lev2_menuOpen
			, @RequestParam(value = "currentId", required = false, defaultValue = "") String currentId
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		if(pageNm.equals("") || pageNm == null){
			
			pageNm = "redirect:pageError.do";
			
		}else{
			
			pageNm = "history/"+pageNm;
			
		}
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("bi_history_sn",keyVal);
		mav.addObject("keyVal", keyVal);
		mav.addObject("lev1_parent_id", lev1_parent_id);
		mav.addObject("lev2_parent_id", lev2_parent_id);
		mav.addObject("lev3_parent_id", lev3_parent_id);
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev1_menuOpen", lev1_menuOpen);
		mav.addObject("lev2_menuOpen", lev2_menuOpen);
		mav.addObject("currentId", currentId);
		mav.setViewName(pageNm);			
	
		return mav;
	}
	
	
	@RequestMapping(value="/history/getListData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView list(
			  @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			, @RequestParam(value="pageCount", required = false, defaultValue = "10") int countPerPage //페이지당 뿌릴 데이터 
			, @RequestParam(value="bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		logger.info("bi_portal_menu_id:::" + bi_portal_menu_id);
		
		
		int totalNo = historyService.getDataCnt(bi_portal_menu_id);
		logger.info("totalNo:::" +  totalNo);
	
		int endPage = 0;
		int startRow =	0;	
		int endRow = 0;
		
		if(totalNo > 0){
		
			endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo);  // 마지막 페이지 
			startRow = BoardUtil.startRow(currentPage, countPerPage);  // 시작 row
			endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage);  //끝 row
		} 
		
		ModelAndView mav = new ModelAndView();	
		
		 mav.addObject("currentPage", currentPage);
		 mav.addObject("totalPage", endPage);
		 mav.addObject("totalNo", totalNo);	
		 mav.addObject("rows", historyService.getDataList(startRow, endRow, bi_portal_menu_id));
		 mav.setViewName("jsonView");	
		
		return mav;
		
	}
	
	@RequestMapping(value="/backup/getListData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView backupList(
			  @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			, @RequestParam(value="pageCount", required = false, defaultValue = "10") int countPerPage //페이지당 뿌릴 데이터 
			, @RequestParam(value="bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		logger.info("bi_portal_menu_id:::" + bi_portal_menu_id);
		
		
		int totalNo = historyService.getBackupDataCnt(bi_portal_menu_id);
		logger.info("totalNo:::" +  totalNo);
	
		int endPage = 0;
		int startRow =	0;	
		int endRow = 0;
		
		if(totalNo > 0){
		
			endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo);  // 마지막 페이지 
			startRow = BoardUtil.startRow(currentPage, countPerPage);  // 시작 row
			endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage);  //끝 row
		} 
		
		ModelAndView mav = new ModelAndView();	
		
		 mav.addObject("currentPage", currentPage);
		 mav.addObject("totalPage", endPage);
		 mav.addObject("totalNo", totalNo);	
		 mav.addObject("rows", historyService.getBackupDataList(startRow, endRow, bi_portal_menu_id));
		 mav.setViewName("jsonView");	
		
		return mav;
		
	}
	
	@RequestMapping(value="/advenced/getListData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView advencerList(
			  @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			, @RequestParam(value="pageCount", required = false, defaultValue = "10") int countPerPage //페이지당 뿌릴 데이터 
			, @RequestParam(value="bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		logger.info("bi_portal_menu_id:::" + bi_portal_menu_id);
		
		int totalNo = historyService.getAdvencedDataCnt(bi_portal_menu_id);
		logger.info("totalNo:::" +  totalNo);
	
		int endPage = 0;
		int startRow =	0;	
		int endRow = 0;
		
		if(totalNo > 0){
		
			endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo);  // 마지막 페이지 
			startRow = BoardUtil.startRow(currentPage, countPerPage);  // 시작 row
			endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage);  //끝 row
		} 
		
		ModelAndView mav = new ModelAndView();	
		
		 mav.addObject("currentPage", currentPage);
		 mav.addObject("totalPage", endPage);
		 mav.addObject("totalNo", totalNo);	
		 mav.addObject("rows", historyService.getAdvencedDataList(startRow, endRow, bi_portal_menu_id));
		 mav.setViewName("jsonView");	
		
		return mav;
		
	}

}
