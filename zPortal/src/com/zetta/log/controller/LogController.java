package com.zetta.log.controller;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.common.DateTimeUtil;
import com.zetta.log.model.LogFail;
import com.zetta.log.model.LogInfo;
import com.zetta.log.service.LogService;



@Controller
public class LogController {
	
	public Logger logger = Logger.getLogger(getClass());
	
	//private static final String DEFAULT_CURRENT_PAGE = "1";
	//private static final String DEFAULT_COUNT_PER_PAGE = "10";

	@Autowired
	LogService logService;

	@RequestMapping(value = "/log/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			@RequestParam(value="pageNm", required = false, defaultValue = "") String pageNm
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		if(pageNm.equals("") || pageNm == null){
			
			pageNm = "redirect:pageError.do";
			
		}else{
			
			pageNm = "log/"+pageNm;
			
		}
		

		
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName(pageNm);	
		return mav;
		
	}
	
	

	
	@RequestMapping(value = "/log/readPageLog.do", method = RequestMethod.POST)
	public ModelAndView readPageLog(
			@RequestParam(value="bi_connct_url_addr", required = false, defaultValue = "") String bi_connct_url_addr
			,@RequestParam(value="bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		logger.info("로그 기록 패이지 ");
		logger.info("bi_conect_pge:: "+ bi_connct_url_addr);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		logger.info("auth.getAuthorities(): "+ auth.getAuthorities());
		logger.info("auth.getPrincipal(): " + auth.getPrincipal());
		logger.info("auth.getName(): " + auth.getName());
		logger.info("auth.getDetails(): "+ auth.getDetails());
		logger.info("bi_portal_menu_id: "+ bi_portal_menu_id);
		
		
		WebAuthenticationDetails authDetails = (WebAuthenticationDetails) auth.getDetails();
		logger.info("authDetails.getSessionId(): "+authDetails.getSessionId() ); 
		
		
		//authDetails.getSessionId().toString()
		//auth.getName().toString()
		//DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss")
		
		
		InetAddress address = InetAddress.getLocalHost();
		String ipAddress =  address.getHostAddress(); //클라이언트 IP	
		
		LogInfo logInfo = new LogInfo();
		
		logInfo.setBi_connct_url_addr(bi_connct_url_addr);
		logInfo.setBi_portal_menu_id(bi_portal_menu_id);
		logInfo.setBi_unity_cust_id(auth.getName());
		logInfo.setBi_session_id(authDetails.getSessionId());
		
		logInfo.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		logInfo.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		
		logService.pageSave(logInfo);
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg","logWriteOK");
		mav.setViewName("jsonView");
		return mav;
		
	}
	

	
	@RequestMapping(value = "/log/getLogList.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getLogList(
			@RequestParam(value="bi_menu_type_yn", required = false, defaultValue = "") String bi_menu_type_yn
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		logger.info("로그목록");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView mav = new ModelAndView();
		mav.addObject("data",logService.getHistoryView(auth.getName(), bi_menu_type_yn));
		mav.setViewName("jsonView");	
		return mav;
		
	}

	
	@RequestMapping(value = "/log/qlikviewLoginFail.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getQikviewLoginFail(
			  HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		logger.info("qlikview 사용자 로그인 제한 20명 ");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		LogFail log = new LogFail();
		
		log.setBi_log_desc("이미 다른 사용자가 로긍인하여 사용중입니다.(20명사용제한)");
		log.setBi_log_type("Q");
		log.setBi_unity_cust_id(auth.getName().toString());
		logService.logFailSave(log);
		ModelAndView mav = new ModelAndView();
		
		
		
		mav.setViewName("jsonView");	
		return mav;
		
	}

	
}
