package com.zetta.comment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.common.BoardUtil;
import com.zetta.comment.model.Comment;
import com.zetta.comment.service.CommentService;



@Controller
public class CommentController {
	
	public Logger logger = Logger.getLogger(getClass());
	
	//private static final String DEFAULT_CURRENT_PAGE = "1";
	//private static final String DEFAULT_COUNT_PER_PAGE = "10";

	@Autowired
	CommentService commentService;
	
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	@RequestMapping(value = "/comment/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			@RequestParam(value="pageNm", required = false, defaultValue = "") String pageNm
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		if(pageNm.equals("") || pageNm == null){
			
			pageNm = "redirect:pageError.do";
			
		}else{
			
			pageNm = "comment/"+pageNm;
			
		}
		
//	
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName(pageNm);			
		//mav.addObject("topCommentList", commentService.topCommentList()); //상단메뉴 실행 
		return mav;
		
	}
	
	

	@RequestMapping(value="/comment/getListData.do",method= RequestMethod.POST)  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView list(
			 @RequestParam(value="bi_bbs_sn", required = false, defaultValue = "0") int bi_bbs_sn
			,@RequestParam(value="searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value="searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		

		logger.info("commentList.do Start");
						
	
		

		int totalNo = commentService.getTotalCount(bi_bbs_sn, searchTitle, searchContent);
	
		
		
		ModelAndView mav = new ModelAndView();	
		
		

		mav.addObject("totalNo", totalNo);	
		
		mav.addObject("rows", commentService.findAllList(bi_bbs_sn ,searchTitle, searchContent));
		logger.info("commentList:: ");
		logger.info("commentList.do end");
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}

	
	

	
	@RequestMapping(value = "/comment/getViewData.do", method = RequestMethod.POST)
	public ModelAndView view(
			@RequestParam(value="no", required = false, defaultValue = "") int no
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//	
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
	
		ModelAndView mav = new ModelAndView();			
		
		mav.addObject("rows",  commentService.findList(no));
		mav.setViewName("jsonView");	
	
					
		return mav;	
	}
	

	@RequestMapping(value = "/comment/save.do", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("comment") Comment comment 
			, BindingResult result			
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
	
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.find
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		comment.setBi_unity_cust_id(auth.getName());
		comment.setBi_bbs_ansr_sn(0);
	
		
		commentService.save(comment);		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/board/gotoPage.do");
		return mav;	
		
		
	}
	
	

	@RequestMapping(value = "/comment/modify.do", method = RequestMethod.POST)
	public ModelAndView modify(
			@ModelAttribute("comment") Comment comment 
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		
		
		commentService.update(comment);
		ModelAndView mav = new ModelAndView();		
		mav.addObject("pageNm","list");
		mav.setViewName("redirect:/comment/gotoPage.do");		
		return mav;
	}
	


	

	@RequestMapping(value = "/comment/delete.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView delete(
			@RequestParam(value="bi_bbs_ansr_sn", required = false, defaultValue = "") int bi_bbs_ansr_sn
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
	
		logger.info("bi_ansr_sn:: " + bi_bbs_ansr_sn);

		commentService.remove(bi_bbs_ansr_sn);
		ModelAndView mav = new ModelAndView();	
		mav.addObject("msg", "삭제되었습니다.");
		mav.setViewName("jsonView");	
		
		return mav;
			
	
	}
	
	@RequestMapping(value = "/comment/deleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteAll(
			 @RequestParam(value="checkId[]", required = false, defaultValue = "") String[] checkId
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
       
		for(int i=0; i<checkId.length; i++){
			
		
			commentService.remove(Integer.valueOf(checkId[i]));
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNm","list");
		mav.setViewName("redirect:/comment/gotoPage.do");	
		return mav;
		
		
	}
	
	
}
