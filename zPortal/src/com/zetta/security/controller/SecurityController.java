package com.zetta.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.common.DateTimeUtil;
import com.zetta.qlikview.model.QlikView;
import com.zetta.security.model.Security;
import com.zetta.security.service.SecurityService;



@Controller
public class SecurityController {
	
	public Logger logger = Logger.getLogger(getClass());
//
	
	
	@Autowired
	SecurityService securityService;

	   @RequestMapping(value="/security/loginPage.do") 
	   public ModelAndView login(
				  HttpServletRequest request
				, HttpServletResponse response)
				throws Exception {

		    logger.info("/security/loginPage.do");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("security/loginPage");			
			return mav;
			
		}
	   
	   @RequestMapping(value="/security/loginProcess.do") 
	   public ModelAndView loginProcess( HttpServletRequest request
				, HttpServletResponse response				
			   )throws Exception {
				
		
		   	logger.info("성공");
		     //customUserservice.loadUserByUsername(j_username);
		   
			ModelAndView mav = new ModelAndView();
			mav.setViewName("security/index");			
			//mav.addObject("topQnaList", qnaService.topQnaList()); //상단메뉴 실행 
			return mav;
			
		}
	   @RequestMapping(value="/security/index.do") 
	   public ModelAndView indexPage(
			      HttpServletRequest request
				, HttpServletResponse response				
			   )throws Exception {
				
		    logger.info("로그인정보");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//logger.info("session:::: "+auth.getCredentials().toString());			
			//logger.info("아이디:::: "+auth.getName());
		

			ModelAndView mav = new ModelAndView();
			//mav.setViewName("/security/index");			
			//mav.addObject("topQnaList", qnaService.topQnaList()); //상단메뉴 실행 
			return mav;
			
		}
	   
	   
	   
	   @RequestMapping(value="/security/loginFailProcess.do") 
	   public ModelAndView loginFailsProcess(
				  HttpServletRequest request
				, HttpServletResponse response)
				throws Exception {
		   
		    logger.info("/security/loginFailProcess.do");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("security/loginPage");			
			//mav.addObject("topQnaList", qnaService.topQnaList()); //상단메뉴 실행 
			return mav;
			
		}
	   
	   
	   @RequestMapping(value="/security/logout.do") 
	   public ModelAndView logout(
				  HttpServletRequest request
				, HttpServletResponse response)
				throws Exception {
		   
		   
		   logger.info("security/logout.do");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("security/close");			
			//mav.addObject("topQnaList", qnaService.topQnaList()); //상단메뉴 실행 
			return mav;
			
		}
	   
	   
	   @RequestMapping(value="/userInfo/getUserData.do")  //,method= RequestMethod.POST
	   @ResponseBody
	   public ModelAndView getUser(
				  HttpServletRequest request
				, HttpServletResponse response)
				throws Exception {
		   
		   
		   Authentication auth = SecurityContextHolder.getContext().getAuthentication();		   
			ModelAndView mav = new ModelAndView();	
		    mav.addObject("rows", securityService.getUser(auth.getName()));
			mav.setViewName("jsonView");
			//mav.addObject("topQnaList", qnaService.topQnaList()); //상단메뉴 실행 
			return mav;
			
		}
	   
	   
	   @RequestMapping(value="/secutiry/setInitQlikviewUser.do")  //,method= RequestMethod.POST
	   @ResponseBody
	   public ModelAndView setInitQlikviewUser(
				  HttpServletRequest request
				, HttpServletResponse response)
				throws Exception {
		   
		   securityService.setInitQlikviewUser();
			ModelAndView mav = new ModelAndView();	
		    mav.addObject("rows", "완료");
			mav.setViewName("jsonView");
			//mav.addObject("topQnaList", qnaService.topQnaList()); //상단메뉴 실행 
			return mav;
			
		}
}
