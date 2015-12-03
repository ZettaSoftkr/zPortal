package com.zetta.popup.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class PopUpController {
	
	public Logger logger = Logger.getLogger(getClass());
	
	//private static final String DEFAULT_CURRENT_PAGE = "1";
	//private static final String DEFAULT_COUNT_PER_PAGE = "10";


	@RequestMapping(value = "/popup/list.do")
	public ModelAndView newReport(
			   @RequestParam(value="reportId", required = false, defaultValue = "") String reportId
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {

		ModelAndView mav = new ModelAndView();
		
		
		mav.setViewName("/popup/list");	
		return mav;
		
	}
	
	

	
}
