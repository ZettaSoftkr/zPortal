package com.zetta.faq.controller;

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
import com.zetta.faq.model.Faq;
import com.zetta.faq.service.FaqService;


@Controller
public class FaqController {
	
	public Logger logger = Logger.getLogger(getClass());
	
	//private static final String DEFAULT_CURRENT_PAGE = "1";
	//private static final String DEFAULT_COUNT_PER_PAGE = "10";

	@Autowired
	FaqService faqService;
	
	

	@RequestMapping(value = "/faq/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			  @RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm
			, @RequestParam(value = "keyVal", required = false, defaultValue = "") String keyVal
			, @RequestParam(value = "lev1_parent_id", required = false, defaultValue = "") String lev1_parent_id
			, @RequestParam(value = "lev2_parent_id", required = false, defaultValue = "") String lev2_parent_id
			, @RequestParam(value = "lev3_parent_id", required = false, defaultValue = "") String lev3_parent_id
			, @RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
			, @RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
			, @RequestParam(value = "lev1_menuOpen", required = false, defaultValue = "") String lev1_menuOpen
			, @RequestParam(value = "lev2_menuOpen", required = false, defaultValue = "") String lev2_menuOpen
			, @RequestParam(value = "currentId", required = false, defaultValue = "") String currentId
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		if(pageNm.equals("") || pageNm == null){
			
			pageNm = "redirect:pageError.do";
			
		}else{
			
			pageNm = "faq/"+pageNm;
			
		}
		
//	
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("bi_faq_sn",keyVal);
		mav.addObject("keyVal", keyVal);
		mav.addObject("lev1_parent_id", lev1_parent_id);
		mav.addObject("lev2_parent_id", lev2_parent_id);
		mav.addObject("lev3_parent_id", lev3_parent_id);
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev1_menuOpen", lev1_menuOpen);
		mav.addObject("lev2_menuOpen", lev2_menuOpen);
		mav.addObject("currentId", currentId);
		mav.setViewName(pageNm);			
		//mav.addObject("topFaqList", faqService.topFaqList()); //상단메뉴 실행 
		return mav;
		
	}
	
	@RequestMapping(value = "/admin/faq/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView adminGotoPage(
			  @RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm
			, @RequestParam(value = "keyVal", required = false, defaultValue = "") String keyVal
			, @RequestParam(value = "lev1_parent_id", required = false, defaultValue = "") String lev1_parent_id
			, @RequestParam(value = "lev2_parent_id", required = false, defaultValue = "") String lev2_parent_id
			, @RequestParam(value = "lev3_parent_id", required = false, defaultValue = "") String lev3_parent_id
			, @RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
			, @RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
			, @RequestParam(value = "lev1_menuOpen", required = false, defaultValue = "") String lev1_menuOpen
			, @RequestParam(value = "lev2_menuOpen", required = false, defaultValue = "") String lev2_menuOpen
			, @RequestParam(value = "currentId", required = false, defaultValue = "") String currentId
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		if(pageNm.equals("") || pageNm == null){
			
			pageNm = "redirect:pageError.do";
			
		}else{
			
			pageNm = "admin/faq/"+pageNm;
			
		}
		
		logger.info("keyVal::" + keyVal);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("bi_faq_sn",keyVal);
		mav.addObject("keyVal", keyVal);
		mav.addObject("lev1_parent_id", lev1_parent_id);
		mav.addObject("lev2_parent_id", lev2_parent_id);
		mav.addObject("lev3_parent_id", lev3_parent_id);
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev1_menuOpen", lev1_menuOpen);
		mav.addObject("lev2_menuOpen", lev2_menuOpen);
		mav.addObject("currentId", currentId);
		mav.setViewName(pageNm);			
	
		return mav;
		
	}
	
	@RequestMapping(value="/faq/getListData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView faqlist(
			 @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage 
			 ,@RequestParam(value ="countPerPage", required = false, defaultValue = "10") int countPerPage //페이지당 뿌릴 데이터 
			, @RequestParam(value = "searchTitle", required = false, defaultValue = "0") int searchTitle 
			, @RequestParam(value = "searchContent", required = false, defaultValue = "") String searchContent 
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
		int totalNo = faqService.getTotalCount(searchTitle, searchContent);
		
	
		int endPage = 0;
		int startRow =	0;	
		int endRow = 0;
		
		if(totalNo > 0){
		
		 endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo);  // 마지막 페이지 
		
		 startRow = BoardUtil.startRow(currentPage, countPerPage);  // 시작 row
		
		 endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage);  //끝 row
		
		} 
		
		ModelAndView mav = new ModelAndView();	
		
		//mav.addObject("pageDivideForm", pageDivideForm);
		 mav.addObject("currentPage", currentPage);
		 mav.addObject("totalPage", endPage);
		 mav.addObject("totalNo", totalNo);	
		
		mav.addObject("rows", faqService.findAllList(startRow, endRow, searchTitle, searchContent));
		mav.setViewName("jsonView");	
		
		return mav;
		
	
		
	}



	@RequestMapping(value="/admin/faq/getListData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView list(
			 @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			,@RequestParam(value="pageCount", required = false, defaultValue = "10") int countPerPage //페이지당 뿌릴 데이터 
			,@RequestParam(value="bi_faq_sn", required = false, defaultValue = "0") int bi_faq_sn
			,@RequestParam(value="sord", required = false, defaultValue = "") String sord
			,@RequestParam(value="searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value="searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
		int totalNo = faqService.getTotalCount(searchTitle, searchContent);
		//String pageDivideForm = BoardUtil.dividePageForm(currentPage, countPerPage, totalNo , request, searchTitle, searchContent,"faqList.do");
	
		int endPage = 0;
		int startRow =	0;	
		int endRow = 0;
		
		if(totalNo > 0){
		
		 endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo);  // 마지막 페이지 
		
		 startRow = BoardUtil.startRow(currentPage, countPerPage);  // 시작 row
		
		 endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage);  //끝 row
		
		} 
		
		ModelAndView mav = new ModelAndView();	
		
		//mav.addObject("pageDivideForm", pageDivideForm);
		 mav.addObject("currentPage", currentPage);
		 mav.addObject("totalPage", endPage);
		 mav.addObject("totalNo", totalNo);	
		
		mav.addObject("rows", faqService.findAllList(startRow, endRow, searchTitle, searchContent));
		mav.setViewName("jsonView");	
		
		return mav;
		
	}

	
	
	/**
	 * 자료실  (상세보기)
	 * @param request
	 * @param response
	 * @return view
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/admin/faq/getViewData.do", method = RequestMethod.POST)
	public ModelAndView view(
		@RequestParam(value="bi_faq_sn", required = false, defaultValue = "") int bi_faq_sn
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//	
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
		
	
	
		
		
		Faq faqPrive = faqService.getPriView(bi_faq_sn);
		Faq faqNext  = faqService.getNextView(bi_faq_sn);

		ModelAndView mav = new ModelAndView();

		if (faqPrive == null) {

			mav.addObject("prevTitle", "이전글");
			mav.addObject("prevDate", "");

		} else {
			
			
			
			String preTitle = "<a href=\"#\" onclick=\"getViewPage('bi_faq_sn','"+faqPrive.getBi_faq_sn()+"');\">"+faqPrive.getBi_titl()+"</a>";
			
			mav.addObject("prevTitle", preTitle);
			mav.addObject("prevDate", faqPrive.getBi_updt_dt());

		}

		if (faqNext == null) {

			mav.addObject("nextTitle", "다음글");
			mav.addObject("nextDate", "");

		} else {
			
			String nextTitle = "<a href=\"#\" onclick=\"getViewPage('bi_faq_sn','"+faqNext.getBi_faq_sn()+"');\">"+faqNext.getBi_titl()+"</a>";
			mav.addObject("nextTitle", nextTitle);
			mav.addObject("nextDate", faqNext.getBi_updt_dt());

		}

		
		mav.addObject("rows",  faqService.findList(bi_faq_sn));
		mav.setViewName("jsonView");	
	
					
		return mav;	
	}
	
	

	@RequestMapping(value = "/admin/faq/save.do", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("faq") Faq faq 
			, BindingResult result			
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
	
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		logger.info("아이디:::: "+auth.getName());
		
		faq.setBi_unity_cust_id(auth.getName()); //아이디 등록
		faq.setBi_inqire_num(0);
		faqService.save(faq);		
		ModelAndView mav = new ModelAndView();		
		mav.addObject("pageNm","list");
		mav.setViewName("redirect:/admin/faq/gotoPage.do");			
		return mav;
		
	}
	
	
	/**
	 * 자료실  (수정하기)
	 * @param request
	 * @param response
	 * @return view
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/faq/modify.do", method = RequestMethod.POST)
	public ModelAndView modify(
			  @ModelAttribute("faq") Faq faq 
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("아이디:::: "+auth.getName());
		faq.setBi_unity_cust_id(auth.getName()); //아이디 등록
		
		faqService.update(faq);
		ModelAndView mav = new ModelAndView();		
		mav.addObject("pageNm","list");
		mav.setViewName("redirect:/admin/faq/gotoPage.do");		
		return mav;
	}
	
	/**
	 * 자료실  (수정페이지 전환)
	 * @param request
	 * @param response
	 * @return edit
	 * @throws Exception
	 */
	


	
	@RequestMapping(value = "/admin/faq/deleteForm.do", method = RequestMethod.POST)
	public ModelAndView deleteForm(
			@RequestParam(value="bi_faq_sn", required = false, defaultValue = "") int bi_faq_sn
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		ModelAndView mav = new ModelAndView();			
		
		mav.addObject("bi_faq_sn", faqService.getById(bi_faq_sn));
		mav.setViewName("/faq/delete");	
		
		return mav;

	}
	
	
	

	/**
	 * 자료실  (삭제)
	 * @param request
	 * @param response
	 * @return list
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/faq/delete.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView delete(
			  @RequestParam(value="bi_faq_sn", required = false, defaultValue = "") int bi_faq_sn
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {

		faqService.remove(bi_faq_sn);
		ModelAndView mav = new ModelAndView();	
		mav.addObject("msg", "삭제 되었습니다.");
		mav.setViewName("jsonView");
		
		return mav;
			
	
	}
	
	@RequestMapping(value = "/admin/faq/deleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteAll(
			 @RequestParam(value="checkId", required = false, defaultValue = "") String[] checkId
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
       
		for(int i=0; i<checkId.length; i++){
			
			faqService.remove(Integer.valueOf(checkId[i]));
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg",  "삭제되었습니다.");
		mav.setViewName("jsonView");
		return mav;		
		
	}
	
	
	@RequestMapping(value="/faq/getMainData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView mainList(
			 @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			,@RequestParam(value="pageCount", required = false, defaultValue = "4") int countPerPage //페이지당 뿌릴 데이터 
			,@RequestParam(value="searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value="searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
		

		int totalNo = faqService.getTotalCount(searchTitle, searchContent);
		//String pageDivideForm = BoardUtil.dividePageForm(currentPage, countPerPage, totalNo , request, searchTitle, searchContent,"faqList.do");
	
		int endPage = 0;
		int startRow =	0;	
		int endRow = 0;
		
		if(totalNo > 0){
		
		 endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo);  // 마지막 페이지 
		
		 startRow = BoardUtil.startRow(currentPage, countPerPage);  // 시작 row
		
		 endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage);  //끝 row
		
		} 
		
		
		ModelAndView mav = new ModelAndView();	
		
		//mav.addObject("pageDivideForm", pageDivideForm);
		 mav.addObject("currentPage", currentPage);
		 mav.addObject("totalPage", endPage);
		 mav.addObject("totalNo", totalNo);	
		
		mav.addObject("rows", faqService.findAllList(startRow, endRow, searchTitle, searchContent));
		mav.setViewName("jsonView");	
		
		return mav;
		
	}

	
}
