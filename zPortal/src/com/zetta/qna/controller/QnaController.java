package com.zetta.qna.controller;

import java.net.URLEncoder;

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
import com.zetta.common.DateTimeUtil;
import com.zetta.qna.model.Qna;
import com.zetta.qna.service.QnaService;



@Controller
public class QnaController {
	
	public Logger logger = Logger.getLogger(getClass());
	
	//private static final String DEFAULT_CURRENT_PAGE = "1";
	//private static final String DEFAULT_COUNT_PER_PAGE = "10";

	@Autowired
	QnaService qnaService;

	
	
	
	@RequestMapping(value = "/qna/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			@RequestParam(value="pageNm", required = false, defaultValue = "") String pageNm
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
			
			pageNm = "qna/"+pageNm;
			
		}
		
//	
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("bi_qna_sn", keyVal);
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
		//mav.addObject("topQnaList", qnaService.topQnaList()); //상단메뉴 실행 
		return mav;
		
	}
	
	@RequestMapping(value = "/mypage/qna/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoMyPage(
			 @RequestParam(value="pageNm", required = false, defaultValue = "") String pageNm
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
			
			pageNm = "mypage/qna/"+pageNm;
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("bi_qna_sn", keyVal);
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
	
	
	@RequestMapping(value="/mypage/qna/getListData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView mypageList(
			 @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			,@RequestParam(value="pageCount", required = false, defaultValue = "10") int countPerPage //페이지당 뿌릴 데이터 
			,@RequestParam(value="bi_qna_sn", required = false, defaultValue = "0") int bi_qna_sn
			,@RequestParam(value="sord", required = false, defaultValue = "") String sord
			,@RequestParam(value="searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value="searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		logger.info("qnaList.do Start");
						
		//String strToday = QnaUtil.getDate(0);
		

		int totalNo = qnaService.getTotalCount(searchTitle, searchContent);
		//String pageDivideForm = QnaUtil.dividePageForm(currentPage, countPerPage, totalNo , request, searchTitle, searchContent,"qnaList.do");
		logger.info("totalNo" + totalNo);
		int endPage = 0;
		int startRow =	0;	
		int endRow = 0;
		
		if(totalNo > 0){
		
		 endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo);  // 마지막 페이지 
		
		 startRow = BoardUtil.startRow(currentPage, countPerPage);  // 시작 row
		
		 endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage);  //끝 row
		
		} 
	
		logger.info("startRow" + startRow);
		logger.info("endRow" + endRow);
		logger.info("endPage" + endPage);
		
		
		
		ModelAndView mav = new ModelAndView();	
		
		//mav.addObject("pageDivideForm", pageDivideForm);
		 mav.addObject("currentPage", currentPage);
		 mav.addObject("totalPage", endPage);
		 mav.addObject("totalNo", totalNo);		 

		
		mav.addObject("rows", qnaService.findAllList(startRow, endRow, searchTitle, searchContent));
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}
	
	
	@RequestMapping(value="/qna/getListData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView list(
			 @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			,@RequestParam(value="pageCount", required = false, defaultValue = "10") int countPerPage //페이지당 뿌릴 데이터 
			,@RequestParam(value="bi_qna_sn", required = false, defaultValue = "0") int bi_qna_sn
			,@RequestParam(value="sord", required = false, defaultValue = "") String sord
			,@RequestParam(value="searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value="searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		logger.info("qnaList.do Start");
						
		//String strToday = QnaUtil.getDate(0);
		

		int totalNo = qnaService.getTotalCount(searchTitle, searchContent);
		//String pageDivideForm = QnaUtil.dividePageForm(currentPage, countPerPage, totalNo , request, searchTitle, searchContent,"qnaList.do");
		logger.info("totalNo" + totalNo);
		int endPage = 0;
		int startRow =	0;	
		int endRow = 0;
		
		if(totalNo > 0){
		
		 endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo);  // 마지막 페이지 
		
		 startRow = BoardUtil.startRow(currentPage, countPerPage);  // 시작 row
		
		 endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage);  //끝 row
		
		} 
	
		logger.info("startRow" + startRow);
		logger.info("endRow" + endRow);
		logger.info("endPage" + endPage);
		
		
		
		ModelAndView mav = new ModelAndView();	
		
		//mav.addObject("pageDivideForm", pageDivideForm);
		 mav.addObject("currentPage", currentPage);
		 mav.addObject("totalPage", endPage);
		 mav.addObject("totalNo", totalNo);	
		 

		
		mav.addObject("rows", qnaService.findAllList(startRow, endRow, searchTitle, searchContent));
	
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}

	
	

	@RequestMapping(value = "/qna/getViewData.do", method = RequestMethod.POST)
	public ModelAndView view(
			 @RequestParam(value="bi_qna_sn", required = false, defaultValue = "") int bi_qna_sn
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ModelAndView mav = new ModelAndView();	
		mav.addObject("nextTitle", "");
		mav.addObject("prevTitle", "");
		
		mav.addObject("rows",  qnaService.findList(bi_qna_sn));
		mav.setViewName("jsonView");
		return mav;	
	}
	
	
	@RequestMapping(value = "/mypage/qna/getViewData.do", method = RequestMethod.POST)
	public ModelAndView myPageView(
			 @RequestParam(value="bi_qna_sn", required = false, defaultValue = "") int bi_qna_sn
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//	
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
	
		ModelAndView mav = new ModelAndView();			
		
		
		mav.addObject("nextTitle", "");
		mav.addObject("prevTitle", "");
		
		mav.addObject("rows",  qnaService.findList(bi_qna_sn));
		mav.setViewName("jsonView");	
	
					
		return mav;	
	}
	

	@RequestMapping(value = "/qna/save.do", method = RequestMethod.POST)
	public ModelAndView save(
			  @ModelAttribute("qna") Qna qna 				
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		qna.setBi_unity_cust_id(auth.getName());
		qna.setBi_answer_yn("S"); //대기 (stand by) ,  A(답변, Answer) , C (확인중, check)
		qna.setBi_inqire_num(0);
		qnaService.save(qna);	
		
		ModelAndView mav = new ModelAndView();		
//		mav.addObject("msg", "등록되었습니다.");
//		mav.setViewName("jsonView");	
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/qna/gotoPage.do");
		return mav;
		
	}
	
	@RequestMapping(value = "/mypage/qna/save.do", method = RequestMethod.POST)
	public ModelAndView myPageSave(
			  @ModelAttribute("qna") Qna qna 				
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		qna.setBi_unity_cust_id(auth.getName());
		qna.setBi_answer_yn("S"); //대기 (stand by) ,  A(답변, Answer) , C (확인중, check)
		qna.setBi_inqire_num(0);
		qnaService.save(qna);	
		
		ModelAndView mav = new ModelAndView();		
//		mav.addObject("msg", "등록되었습니다.");
//		mav.setViewName("jsonView");	
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/qna/gotoPage.do");
		return mav;
		
	}
	
	
	
	@RequestMapping(value = "/qna/modify.do", method = RequestMethod.POST)
	public ModelAndView modify(
			@ModelAttribute("qna") Qna qna 
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		qna.setBi_unity_cust_id(auth.getName());
		qnaService.update(qna);
		ModelAndView mav = new ModelAndView();		
//		mav.addObject("msg", "수정 되었습니다.");
//		mav.setViewName("jsonView");	
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/qna/gotoPage.do");
		return mav;
	}
	
	

	@RequestMapping(value = "mypage/qna/modify.do", method = RequestMethod.POST)
	public ModelAndView myPageModify(
			@ModelAttribute("qna") Qna qna 
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		qna.setBi_unity_cust_id(auth.getName());
		qnaService.update(qna);
		ModelAndView mav = new ModelAndView();		
//		mav.addObject("msg", "수정 되었습니다.");
//		mav.setViewName("jsonView");	
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/qna/gotoPage.do");
		return mav;
	}
	
	
	

	
	


	
	@RequestMapping(value = "/qna/deleteForm.do", method = RequestMethod.POST)
	public ModelAndView deleteForm(
			@RequestParam(value="bi_qna_sn", required = false, defaultValue = "") int bi_qna_sn
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		ModelAndView mav = new ModelAndView();			
		
		mav.addObject("bi_qna_sn", qnaService.getById(bi_qna_sn));
		mav.setViewName("/qna/delete");	
		
		return mav;

	}
	
	
	

	@RequestMapping(value = "/qna/delete.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView delete(
			@RequestParam(value="bi_qna_sn", required = false, defaultValue = "") int bi_qna_sn
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {

	

		qnaService.remove(bi_qna_sn);
		ModelAndView mav = new ModelAndView();	
		mav.addObject("msg", "삭제 되었습니다.");
		mav.setViewName("jsonView");
		return mav;
			
	
	}
	
	@RequestMapping(value = "/mypage/qna/delete.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView myPageDelete(
			@RequestParam(value="bi_qna_sn", required = false, defaultValue = "") int bi_qna_sn
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {

	

		qnaService.remove(bi_qna_sn);
		ModelAndView mav = new ModelAndView();	
		mav.addObject("msg", "삭제 되었습니다.");
		mav.setViewName("jsonView");
		return mav;
			
	
	}
	
	@RequestMapping(value = "/qna/deleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteAll(
			 @RequestParam(value="checkId[]", required = false, defaultValue = "") String[] checkId
			,@ModelAttribute("qna") Qna qna 
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
       
		for(int i=0; i<checkId.length; i++){
			
			qna.setBi_qna_sn(Integer.valueOf(checkId[i]));
			qnaService.remove(qna.getBi_qna_sn());
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNm","list");
		mav.setViewName("redirect:/qna/gotoPage.do");	
		return mav;
		
		
	}
	
	
	

	@RequestMapping(value="/qna/getMainData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView mainList(
			 @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			,@RequestParam(value="pageCount",  required = false, defaultValue = "4") int countPerPage //페이지당 뿌릴 데이터 
			,@RequestParam(value="searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value="searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		

		logger.info("qnaList.do Start");

		int totalNo = qnaService.getTotalCount(searchTitle, searchContent);
		//String pageDivideForm = QnaUtil.dividePageForm(currentPage, countPerPage, totalNo , request, searchTitle, searchContent,"qnaList.do");
		logger.info("totalNo" + totalNo);
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
		mav.addObject("rows", qnaService.findAllList(startRow, endRow, searchTitle, searchContent));
	
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}
	
	
	
	@RequestMapping(value = "/admin/qna/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView adminGotoPage(
			  @RequestParam(value="pageNm", required = false, defaultValue = "") String pageNm
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
			
			pageNm = "admin/qna/"+pageNm;
			
		}
		
//	
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("bi_qna_sn", keyVal);
		mav.addObject("lev1_parent_id", lev1_parent_id);
		mav.addObject("lev2_parent_id", lev2_parent_id);
		mav.addObject("lev3_parent_id", lev3_parent_id);
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev1_menuOpen", lev1_menuOpen);
		mav.addObject("lev2_menuOpen", lev2_menuOpen);
		mav.addObject("currentId", currentId);
		mav.setViewName(pageNm);			
		//mav.addObject("topQnaList", qnaService.topQnaList()); //상단메뉴 실행 
		return mav;
		
	}
	
	

	@RequestMapping(value="/admin/qna/getListData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView adminList(
			 @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			,@RequestParam(value="pageCount", required = false, defaultValue = "10") int countPerPage //페이지당 뿌릴 데이터 
			,@RequestParam(value="bi_qna_sn", required = false, defaultValue = "0") int bi_qna_sn
			,@RequestParam(value="sord", required = false, defaultValue = "") String sord
			,@RequestParam(value="searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value="searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		logger.info("qnaList.do Start");
						
		//String strToday = QnaUtil.getDate(0);
		

		int totalNo = qnaService.getTotalCount(searchTitle, searchContent);
		//String pageDivideForm = QnaUtil.dividePageForm(currentPage, countPerPage, totalNo , request, searchTitle, searchContent,"qnaList.do");
		logger.info("totalNo" + totalNo);
		int endPage = 0;
		int startRow =	0;	
		int endRow = 0;
		
		if(totalNo > 0){
		
		 endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo);  // 마지막 페이지 
		
		 startRow = BoardUtil.startRow(currentPage, countPerPage);  // 시작 row
		
		 endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage);  //끝 row
		
		} 
	
		logger.info("startRow" + startRow);
		logger.info("endRow" + endRow);
		logger.info("endPage" + endPage);
		
		
		
		ModelAndView mav = new ModelAndView();	
		
		//mav.addObject("pageDivideForm", pageDivideForm);
		 mav.addObject("currentPage", currentPage);
		 mav.addObject("totalPage", endPage);
		 mav.addObject("totalNo", totalNo);	
		 

		
		mav.addObject("rows", qnaService.findAllList(startRow, endRow, searchTitle, searchContent));
	
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
	
	@RequestMapping(value = "/admin/qna/getViewData.do", method = RequestMethod.POST)
	public ModelAndView adminView(
			 @RequestParam(value="bi_qna_sn", required = false, defaultValue = "") int bi_qna_sn
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//	
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
	
		ModelAndView mav = new ModelAndView();			
		
		
		mav.addObject("nextTitle", "");
		mav.addObject("prevTitle", "");
		
		mav.addObject("rows",  qnaService.findList(bi_qna_sn));
		mav.setViewName("jsonView");	
	
					
		return mav;	
	}
	
	
	/**
	 * 자료실  (등록하기)
	 * @param request
	 * @param response
	 * @return view
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/qna/save.do", method = RequestMethod.POST)
	public ModelAndView adminSave(@ModelAttribute("qna") Qna qna 
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		qna.setBi_unity_cust_id(auth.getName());
		qna.setBi_answer_yn("S"); //대기 (stand by) ,  A(답변, Answer) , C (확인중, check)
		qna.setBi_inqire_num(0);
		qnaService.save(qna);		
		ModelAndView mav = new ModelAndView();		
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/admin/qna/gotoPage.do");		
		return mav;
		
	}
	
	
	
	@RequestMapping(value = "/admin/qna/modify.do", method = RequestMethod.POST)
	
	public ModelAndView adminModify(
			@ModelAttribute("qna") Qna qna 
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		qna.setBi_unity_cust_id(auth.getName());
		qnaService.update(qna);
		ModelAndView mav = new ModelAndView();		
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/admin/qna/gotoPage.do");	
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "/admin/qna/answerSave.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView answerSave(
			@ModelAttribute("qna") Qna qna 
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		qnaService.answerUpdate(qna);
		ModelAndView mav = new ModelAndView();		
//		mav.addObject("msg","저장되었습니다.");
//		mav.setViewName("jsonView");
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/admin/qna/gotoPage.do");	
		
//		mav.addObject("pageNm", "list");
//		mav.setViewName("redirect:/admin/qna/gotoPage.do");
		return mav;
	}
	
	
	


	
	@RequestMapping(value = "/admin/qna/deleteForm.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView adminDeleteForm(
			@RequestParam(value="bi_qna_sn", required = false, defaultValue = "") int bi_qna_sn
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		ModelAndView mav = new ModelAndView();			
		
		mav.addObject("bi_qna_sn", qnaService.getById(bi_qna_sn));
		mav.addObject("msg", "삭제 되었습니다.");
		mav.setViewName("jsonView");
		
		return mav;

	}
	
	
	

	/**
	 * 자료실  (삭제)
	 * @param request
	 * @param response
	 * @return list
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/qna/delete.do", method = RequestMethod.POST)
	public ModelAndView adminDelete(
			@RequestParam(value="bi_qna_sn", required = false, defaultValue = "") int bi_qna_sn
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {

	

		qnaService.remove(bi_qna_sn);
		ModelAndView mav = new ModelAndView();	
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/admin/qna/gotoPage.do");	
		
		return mav;
			
	
	}
	
	@RequestMapping(value = "/admin/qna/deleteAll.do", method = RequestMethod.POST)
	public ModelAndView adminDeleteAll(
			 @RequestParam(value="checkId", required = false, defaultValue = "") String[] checkId
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
       
		for(int i=0; i<checkId.length; i++){
			
			
			qnaService.remove(Integer.valueOf(checkId[i]));
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg",  "삭제되었습니다.");
		mav.setViewName("jsonView");	
		return mav;		
		
	}
	
	
	@RequestMapping(value = "/mypage/qna/deleteAll.do", method = RequestMethod.POST)
	public ModelAndView mypageDeleteAll(
			 @RequestParam(value="checkId", required = false, defaultValue = "") String[] checkId
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
       
		for(int i=0; i<checkId.length; i++){
			
			
			qnaService.remove(Integer.valueOf(checkId[i]));
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg",  "삭제되었습니다.");
		mav.setViewName("jsonView");	
		return mav;		
		
	}
	
	@RequestMapping(value="/qna/getCntCheck.do")  //,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView getCntCheck(
			 @RequestParam(value="bi_qna_sn", required = false, defaultValue = "0") int bi_qna_sn
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
    
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		
		
		ModelAndView mav = new ModelAndView();
		int num = qnaService.getCntCheck(bi_qna_sn,auth.getName());
		
		mav.addObject("btnNum",num);
		mav.setViewName("jsonView");
		return mav;
		
		
	}
	
	
	@RequestMapping(value="/mypage/qna/getCntCheck.do")  //,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView getMypageCntCheck(
			 @RequestParam(value="bi_qna_sn", required = false, defaultValue = "0") int bi_qna_sn
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
    
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		ModelAndView mav = new ModelAndView();
		int num = qnaService.getCntCheck(bi_qna_sn,auth.getName());
		
		mav.addObject("btnNum",num);
		mav.setViewName("jsonView");
		return mav;
		
		
	}

	
	
}
