package com.zetta.dataSource.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GetDataController {

	public Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value = "/googlemap/getMap.do", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getGoogleMap(@RequestParam(value = "addr", required = true) String addr, HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("시작: googlemap.do ");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("zPortal/googlemap");
		mav.addObject("addr", addr);
		return mav;
	}
}
