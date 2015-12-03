package com.zetta.siteMap.controller;

import java.util.List;

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

import com.zetta.menu.service.MenuService;
import com.zetta.menuGroup.service.MenuGroupService;

@Controller
public class SiteMapController {

	public Logger logger = Logger.getLogger(getClass());

	@Autowired
	MenuService menuService;
	
	@RequestMapping(value = "/siteMap/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			  @RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm
			, @RequestParam(value = "parentId", required = false, defaultValue = "") String parentId
			, @RequestParam(value = "currentId", required = false, defaultValue = "") String currentId
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		
		
		logger.info("siteMap/gotoPage.do");
		logger.info("siteMap::" + pageNm);

		if (pageNm.equals("") || pageNm == null) {

			pageNm = "redirect:pageError.do";

		} else {

			pageNm = "siteMap/"+ pageNm;

		}
		//
		// boolean isLogin = loginSessionCheck(request);
		// if (!isLogin)
		// return mapping.findForward("sessionError");
		logger.info("pageNm::" + pageNm);
		ModelAndView mav = new ModelAndView();
		mav.addObject("parentId", parentId);
		mav.addObject("currentId", currentId);
		mav.setViewName(pageNm);
		
		return mav;

	}
	
	@RequestMapping(value = "/siteMap/getSiteMap.do",method= RequestMethod.POST)	
	@ResponseBody
	public ModelAndView getSiteMap(  
			  @RequestParam(value = "bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception{
		
				List list  = menuService.getSiteMap(bi_portal_menu_id);
				ModelAndView mav = new ModelAndView();			
				mav.addObject("data",list);
				mav.setViewName("jsonView");

				return mav;
		
	}
	
	
	

}
