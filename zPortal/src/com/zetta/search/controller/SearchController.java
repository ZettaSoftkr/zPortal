package com.zetta.search.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.common.BoardUtil;
import com.zetta.common.CommonUtil;
import com.zetta.menu.service.MenuService;




@Controller
public class SearchController {
	
	public Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	MenuService menuService;
	



	@RequestMapping(value = "/search/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			  @RequestParam(value="pageNm", required = false, defaultValue = "") String pageNm
			, @RequestParam(value="searchKeyWord", required = false, defaultValue = "") String searchKeyWord
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
			
			pageNm = "search/"+pageNm;
			
		}
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("searchKeyWord",searchKeyWord);
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
	
	
	@RequestMapping(value = "/search/getSearchReportList.do", method= RequestMethod.POST)	
	@ResponseBody
	public ModelAndView getSearchReportList(
			  @RequestParam(value = "bi_menu_type_yn", required = false, defaultValue = "R") String bi_menu_type_yn
			, @RequestParam(value = "searchKeyword", required = false, defaultValue = "") String searchKeyword
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		logger.info("######################################################");
		logger.info("bi_menu_type_yn: "+bi_menu_type_yn);
		logger.info("searchKeyword: "+searchKeyword);
		
	     Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
			logger.info("아이디:::: "+auth.getName());
			

		int totalNo = menuService.getSearchReportCount(auth.getName(), bi_menu_type_yn, searchKeyword);

		ArrayList<HashMap<String, String>> getHmData = new ArrayList<HashMap<String,String>>(); //데이터 해쉬	
		 List menuList = menuService.getSearchReportList(auth.getName(), bi_menu_type_yn, searchKeyword);	
		 ArrayList<String> nameParam = new ArrayList<String>();
		 nameParam.add("bi_portal_menu_id");
		 nameParam.add("bi_portal_menu_parent_id");
		 nameParam.add("bi_menu_nm");
		 nameParam.add("bi_menu_url_addr");
		 nameParam.add("bi_sort_sn");
		 nameParam.add("bi_menu_type_yn");
		 nameParam.add("bi_menu_fm_yn");
		 nameParam.add("bi_dc");
		 nameParam.add("bi_updt_dt");
		
		getHmData = CommonUtil.getReturnHashMapData(menuList, nameParam);
		 
		logger.info("menuList: " + menuList);
		logger.info("menuList: " + getHmData);
		
		ModelAndView mav = new ModelAndView();	
		mav.addObject("totalNo", totalNo);				
		mav.addObject("rows",getHmData);
		mav.setViewName("jsonView");

		return mav;

	}
	
	
	
	

}
