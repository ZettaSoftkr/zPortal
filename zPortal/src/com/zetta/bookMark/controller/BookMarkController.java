package com.zetta.bookMark.controller;

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
import com.zetta.bookMark.model.BookMark;
import com.zetta.bookMark.service.BookMarkService;



@Controller
public class BookMarkController {
	
	public Logger logger = Logger.getLogger(getClass());
	
	//private static final String DEFAULT_CURRENT_PAGE = "1";
	//private static final String DEFAULT_COUNT_PER_PAGE = "10";

	@Autowired
	BookMarkService bookMarkService;

	@RequestMapping(value = "/bookMark/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			@RequestParam(value="pageNm", required = false, defaultValue = "") String pageNm
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		
		logger.info("pageNm" + pageNm);
		if(pageNm.equals("") || pageNm == null){
			
			pageNm = "redirect:pageError.do";
			
		}else{
			
			pageNm = "bookMark/"+pageNm;
			
		}
		

	
		ModelAndView mav = new ModelAndView();
		mav.setViewName(pageNm);			
		//mav.addObject("topBookMarkList", bookMarkService.topBookMarkList()); //상단메뉴 실행 
		return mav;
		
	}
	
	@RequestMapping(value = "/mypage/bookMark/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoMyPagePage(
			@RequestParam(value="pageNm", required = false, defaultValue = "") String pageNm
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		
		logger.info("pageNm" + pageNm);
		if(pageNm.equals("") || pageNm == null){
			
			pageNm = "redirect:pageError.do";
			
		}else{
			
			pageNm = "mypage/bookMark/"+pageNm;
			
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName(pageNm);			
		//mav.addObject("topBookMarkList", bookMarkService.topBookMarkList()); //상단메뉴 실행 
		return mav;
	}	

	@RequestMapping(value="/bookMark/getListData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView list(
			 @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			,@RequestParam(value="pageCount", required = false, defaultValue = "10") int countPerPage //페이지당 뿌릴 데이터 
			,@RequestParam(value="bi_bkmk_sn", required = false, defaultValue = "0") int bi_bkmk_sn
			,@RequestParam(value="searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value="searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		logger.info("bookMarkList.do Start");
						
		//String strToday = BookMarkUtil.getDate(0);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("아이디:::: "+auth.getName());
		
		int totalNo = bookMarkService.getTotalCount(searchTitle, searchContent, auth.getName());
		//String pageDivideForm = BookMarkUtil.dividePageForm(currentPage, countPerPage, totalNo , request, searchTitle, searchContent,"bookMarkList.do");
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
		
		mav.addObject("rows", bookMarkService.findAllList(startRow, endRow, searchTitle, searchContent, auth.getName()));
		logger.info("bookMarkList:: ");
		logger.info("bookMarkList.do end");
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}

	
	@RequestMapping(value = "/bookMark/getViewData.do", method = RequestMethod.POST)
	public ModelAndView view(
			@RequestParam(value="bi_bkmk_sn", required = false, defaultValue = "") int bi_bkmk_sn
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {

	
		ModelAndView mav = new ModelAndView();			
		mav.addObject("prevTitle", "");
		mav.addObject("nextTitle", "");
		mav.addObject("rows",  bookMarkService.findList(bi_bkmk_sn));
		mav.setViewName("jsonView");	
	
					
		return mav;	
	}
	

	@RequestMapping(value = "/bookMark/save.do", method = RequestMethod.POST)
	public ModelAndView save(
			  @ModelAttribute("bookMark") BookMark bookMark
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("아이디:::: "+auth.getName());
		bookMark.setBi_unity_cust_id(auth.getName()); //아이디 등록
		bookMarkService.save(bookMark);		
		
		ModelAndView mav = new ModelAndView();		
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/bookMark/gotoPage.do");	
		return mav;
		
	}
	

	@RequestMapping(value = "/bookMark/modify.do", method = RequestMethod.POST)
	public ModelAndView modify(
			@ModelAttribute("bookMark") BookMark bookMark 
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		
		
		bookMarkService.update(bookMark);
		ModelAndView mav = new ModelAndView();	
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/bookMark/gotoPage.do");		
		return mav;
	}

	@RequestMapping(value = "/bookMark/deleteForm.do", method = RequestMethod.POST)
	public ModelAndView deleteForm(
			@RequestParam(value="bi_bkmk_sn", required = false, defaultValue = "") int bi_bkmk_sn
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		ModelAndView mav = new ModelAndView();		
		
		int num = bookMarkService.remove(bi_bkmk_sn);
		
		
		mav.addObject("msg",  "삭제 되었습니다.");
		mav.setViewName("jsonView");	
		
		return mav;

	}
	
	
	


	@RequestMapping(value = "/bookMark/delete.do", method = RequestMethod.POST)
	public ModelAndView delete(
			@RequestParam(value="bi_bkmk_sn", required = false, defaultValue = "") int bi_bkmk_sn
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {

	

		bookMarkService.remove(bi_bkmk_sn);
		ModelAndView mav = new ModelAndView();	
		mav.addObject("msg",  "삭제 되었습니다.");
		mav.setViewName("jsonView");	
		
		return mav;
			
	
	}
	
	@RequestMapping(value = "/bookMark/deleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteAll(
			 @RequestParam(value="checkId", required = false, defaultValue = "") String[] checkId
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
       
		for(int i=0; i<checkId.length; i++){
		
			bookMarkService.remove(Integer.valueOf(checkId[i]));
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg",  "삭제 되었습니다.");
		mav.setViewName("jsonView");	
		return mav;
		
		
	}
	
	
	@RequestMapping(value="/bookMark/getMainData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView mainList(
			 @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			,@RequestParam(value="pageCount", required = false, defaultValue = "5") int countPerPage //페이지당 뿌릴 데이터 
			,@RequestParam(value="searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value="searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("아이디:::: "+auth.getName());
		
		logger.info("bookMarkList.do Start");

		int totalNo = bookMarkService.getTotalCount(searchTitle, searchContent, auth.getName());
		//String pageDivideForm = BookMarkUtil.dividePageForm(currentPage, countPerPage, totalNo , request, searchTitle, searchContent,"bookMarkList.do");
		
		int endPage = 0;
		int startRow =	0;	
		int endRow = 0;
		
		if(totalNo > 0){
		
		 endPage =  BoardUtil.endPage(currentPage, countPerPage, totalNo);  // 마지막 페이지 
		
		 startRow = BoardUtil.startRow(currentPage, countPerPage);  // 시작 row
		
		 endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage);  //끝 row
		
		} 
		
		
		ModelAndView mav = new ModelAndView();	
		
		//mav.addObject("pageDivideForm", pageDivideForm);
		 mav.addObject("currentPage", currentPage);
		 mav.addObject("totalPage", endPage);
		 mav.addObject("totalNo", totalNo);	
		
		mav.addObject("rows", bookMarkService.findAllList(startRow, endRow, searchTitle, searchContent, auth.getName()));
		logger.info("bookMarkList:: ");
		logger.info("bookMarkList.do end");
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}
	
	
	@RequestMapping(value="/bookMark/getMainListData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView mainlist(
			 @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			,@RequestParam(value="pageCount", required = false, defaultValue = "5") int countPerPage //페이지당 뿌릴 데이터 
			,@RequestParam(value="bi_bkmk_sn", required = false, defaultValue = "0") int bi_bkmk_sn
			,@RequestParam(value="searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value="searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		logger.info("bookMarkList.do Start");
						
		//String strToday = BookMarkUtil.getDate(0);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("아이디:::: "+auth.getName());
		
		int totalNo = bookMarkService.getTotalCount(searchTitle, searchContent, auth.getName());
		//String pageDivideForm = BookMarkUtil.dividePageForm(currentPage, countPerPage, totalNo , request, searchTitle, searchContent,"bookMarkList.do");
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
		
		mav.addObject("rows", bookMarkService.findAllList(startRow, endRow, searchTitle, searchContent, auth.getName()));
		logger.info("bookMarkList:: ");
		logger.info("bookMarkList.do end");
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}
	
	
	@RequestMapping(value="/bookMark/getCntCheck.do")  //,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView getCntCheck(
			 @RequestParam(value="bi_bkmk_sn", required = false, defaultValue = "0") int bi_bkmk_sn
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
    
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		ModelAndView mav = new ModelAndView();
		int num = bookMarkService.getCntCheck(bi_bkmk_sn,auth.getName());
		
		mav.addObject("btnNum",num);
		mav.setViewName("jsonView");
		return mav;
		
		
	}
	
	
	
	@RequestMapping(value="/mypage/bookMark/getListData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView myPagelist(
			 @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			,@RequestParam(value="pageCount", required = false, defaultValue = "10") int countPerPage //페이지당 뿌릴 데이터 
			,@RequestParam(value="bi_bkmk_sn", required = false, defaultValue = "0") int bi_bkmk_sn
			,@RequestParam(value="searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value="searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		logger.info("bookMarkList.do Start");
						
		//String strToday = BookMarkUtil.getDate(0);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("아이디:::: "+auth.getName());
		
		int totalNo = bookMarkService.getTotalCount(searchTitle, searchContent, auth.getName());
		//String pageDivideForm = BookMarkUtil.dividePageForm(currentPage, countPerPage, totalNo , request, searchTitle, searchContent,"bookMarkList.do");
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
		
		mav.addObject("rows", bookMarkService.findAllList(startRow, endRow, searchTitle, searchContent, auth.getName()));
		logger.info("bookMarkList:: ");
		logger.info("bookMarkList.do end");
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}

	
	@RequestMapping(value = "/mypage/bookMark/getViewData.do", method = RequestMethod.POST)
	public ModelAndView myPageview(
			@RequestParam(value="bi_bkmk_sn", required = false, defaultValue = "") int bi_bkmk_sn
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {

	
		ModelAndView mav = new ModelAndView();			
		mav.addObject("prevTitle", "");
		mav.addObject("nextTitle", "");
		mav.addObject("rows",  bookMarkService.findList(bi_bkmk_sn));
		mav.setViewName("jsonView");	
	
					
		return mav;	
	}
	

	@RequestMapping(value = "/mypage/bookMark/save.do", method = RequestMethod.POST)
	public ModelAndView myPageSave(
			  @ModelAttribute("bookMark") BookMark bookMark
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("아이디:::: "+auth.getName());
		bookMark.setBi_unity_cust_id(auth.getName()); //아이디 등록
		bookMarkService.save(bookMark);		
		
		ModelAndView mav = new ModelAndView();		
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/mypage/bookMark/gotoPage.do");	
		return mav;
		
	}
	

	@RequestMapping(value = "/mypage/bookMark/modify.do", method = RequestMethod.POST)
	public ModelAndView myPageModify(
			@ModelAttribute("bookMark") BookMark bookMark 
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		
		
		bookMarkService.update(bookMark);
		ModelAndView mav = new ModelAndView();	
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/mypage/bookMark/gotoPage.do");		
		return mav;
	}

	@RequestMapping(value = "/mypage/bookMark/deleteForm.do", method = RequestMethod.POST)
	public ModelAndView myPageDeleteForm(
			@RequestParam(value="bi_bkmk_sn", required = false, defaultValue = "") int bi_bkmk_sn
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		ModelAndView mav = new ModelAndView();		
		
		int num = bookMarkService.remove(bi_bkmk_sn);
		
		
		mav.addObject("msg",  "삭제 되었습니다.");
		mav.setViewName("jsonView");	
		
		return mav;

	}
	
	@RequestMapping(value = "/mypage/bookMark/deleteAll.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView myPageDeleteAll(
			 @RequestParam(value="checkId", required = false, defaultValue = "") String[] checkId
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
       
		for(int i=0; i<checkId.length; i++){
		
			bookMarkService.remove(Integer.valueOf(checkId[i]));
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg",  "삭제 되었습니다.");
		mav.setViewName("jsonView");	
		return mav;
		
		
	}
	
}
