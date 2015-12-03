package com.zetta.gotoPage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GoToPageController {

	public Logger logger = Logger.getLogger(getClass());

	// private static final String DEFAULT_CURRENT_PAGE = "1";
	// private static final String DEFAULT_COUNT_PER_PAGE = "10";



	
	@RequestMapping(value = "/admin/intro/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoAdminPage(
			@RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

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
		mav.setViewName(pageNm);
		// mav.addObject("topApiDescList", apiDescService.topApiDescList()); //상단메뉴
		// 실행
		return mav;

	}
	
}
