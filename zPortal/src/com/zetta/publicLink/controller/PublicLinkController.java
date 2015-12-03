package com.zetta.publicLink.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PublicLinkController {



	public Logger logger = Logger.getLogger(getClass());



	@RequestMapping(value = "/publicLink/gotoPage.do")
	public ModelAndView gotoPage(  
			 @RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

	
		if (pageNm.equals("") || pageNm == null) {

			pageNm = "redirect:pageError.do";

		} else {

			pageNm = "publicLink/" + pageNm;

		}

		ModelAndView mav = new ModelAndView();

		
		mav.setViewName(pageNm);
		return mav;

	}

	
}
