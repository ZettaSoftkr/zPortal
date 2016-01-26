package com.zetta.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PortalController {

	public Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value = "/loginSuccess.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView save(
			@RequestParam(value = "userId", required = true) String userId,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		logger.info("/loginSuccess.do start");
		boolean result = true;
		
		String userInfo = "C:\\zWorking\\users\\" + userId +".json";

		ModelAndView mav = new ModelAndView();
		mav.setViewName("zVM/zViewer");
		mav.addObject("userInfo", userInfo);
		return mav;
	}

}
