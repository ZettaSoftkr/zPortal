package com.zetta.intro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IntroController {

	public Logger logger = Logger.getLogger(getClass());




	@RequestMapping(value = "/admin/intro/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoAdminPage(
			@RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm
			,@RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
			,@RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
			,@RequestParam(value = "lev3_menuOpenId", required = false, defaultValue = "") String lev3_menuOpenId
			,@RequestParam(value = "currentId", required = false, defaultValue = "") String currentId
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		if (pageNm.equals("") || pageNm == null) {

			pageNm = "redirect:pageError.do";

		} else {

			pageNm = "admin/intro/" + pageNm;

		}

		//
		// boolean isLogin = loginSessionCheck(request);
		// if (!isLogin)
		// return mapping.findForward("sessionError");

		ModelAndView mav = new ModelAndView();
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev3_menuOpenId", lev3_menuOpenId);
		mav.addObject("currentId", currentId);
		mav.setViewName(pageNm);
		// mav.addObject("topMenuList", menuService.topMenuList()); //상단메뉴 실행
		return mav;

	}
	
	@RequestMapping(value = "/intro/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			@RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm
			,@RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
			,@RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
			,@RequestParam(value = "lev3_menuOpenId", required = false, defaultValue = "") String lev3_menuOpenId
			,@RequestParam(value = "currentId", required = false, defaultValue = "") String currentId
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		if (pageNm.equals("") || pageNm == null) {

			pageNm = "redirect:pageError.do";

		} else {

			pageNm = "intro/" + pageNm;

		}

		//
		// boolean isLogin = loginSessionCheck(request);
		// if (!isLogin)
		// return mapping.findForward("sessionError");

		ModelAndView mav = new ModelAndView();
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev3_menuOpenId", lev3_menuOpenId);
		mav.addObject("currentId", currentId);
		mav.setViewName(pageNm);
		// mav.addObject("topMenuList", menuService.topMenuList()); //상단메뉴 실행
		return mav;

	}


	

}
