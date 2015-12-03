package com.zetta.notice.controller;

import java.util.List;

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

import com.zetta.board.model.Board;
import com.zetta.common.BoardUtil;
import com.zetta.faq.model.Faq;
import com.zetta.notice.model.Notice;
import com.zetta.notice.service.NoticeService;

@Controller
public class NoticeController {

	public Logger logger = Logger.getLogger(getClass());

	// private static final String DEFAULT_CURRENT_PAGE = "1";
	// private static final String DEFAULT_COUNT_PER_PAGE = "10";

	@Autowired
	NoticeService noticeService;

	@RequestMapping(value = "/notice/gotoPage.do", method = RequestMethod.GET)
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
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (pageNm.equals("") || pageNm == null) {

			pageNm = "redirect:pageError.do";

		} else {

			pageNm = "notice/" + pageNm;

		}

		//
		// boolean isLogin = loginSessionCheck(request);
		// if (!isLogin)
		// return mapping.findForward("sessionError");

		ModelAndView mav = new ModelAndView();
		mav.addObject("bi_nct_sn",keyVal);
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
		// mav.addObject("topNoticeList", noticeService.topNoticeList()); //상단메뉴
		// 실행
		return mav;

	}
	
	
	@RequestMapping(value = "/admin/notice/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoAdminPage(
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
			, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (pageNm.equals("") || pageNm == null) {

			pageNm = "redirect:pageError.do";

		} else {

			pageNm = "admin/notice/" + pageNm;

		}

		//
		// boolean isLogin = loginSessionCheck(request);
		// if (!isLogin)
		// return mapping.findForward("sessionError");

		ModelAndView mav = new ModelAndView();
		mav.addObject("bi_nct_sn",keyVal);
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
		// mav.addObject("topNoticeList", noticeService.topNoticeList()); //상단메뉴
		// 실행
		return mav;

	}

	@RequestMapping(value = "/notice/getListData.do" ,method= RequestMethod.POST)
	@ResponseBody
	public ModelAndView list(
			 @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage // 현재
			,@RequestParam(value = "pageCount", required = false, defaultValue = "10") int countPerPage // 페이지당
			,@RequestParam(value = "bi_nct_sn", required = false, defaultValue = "0") int bi_nct_sn
			,@RequestParam(value = "sord", required = false, defaultValue = "") String sord
			,@RequestParam(value = "searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value = "searchContent", required = false, defaultValue = "") String searchContent
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {

		
		
	
		
		logger.info("noticeList.do Start");

		// String strToday = BoardUtil.getDate(0);

		int totalNo = noticeService.getTotalCount(searchTitle, searchContent);
		// String pageDivideForm = BoardUtil.dividePageForm(currentPage,
		// countPerPage, totalNo , request, searchTitle,
		// searchContent,"noticeList.do");
		logger.info("totalNo" + totalNo);
		int endPage = 0;
		int startRow = 0;
		int endRow = 0;

		if (totalNo > 0) {

			endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo); // 마지막
																				// 페이지

			startRow = BoardUtil.startRow(currentPage, countPerPage); // 시작 row

			endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage); // 끝
																			// row

		}

		ModelAndView mav = new ModelAndView();

		// mav.addObject("pageDivideForm", pageDivideForm);
		mav.addObject("currentPage", currentPage);
		mav.addObject("totalPage", endPage);
		mav.addObject("totalNo", totalNo);

		List getList = noticeService.findAllList(startRow, endRow, searchTitle,	searchContent);

		mav.addObject("rows", getList);
		mav.setViewName("jsonView");

		return mav;

	}

	
	

	@RequestMapping(value = "/notice/getViewData.do")
	// , method = RequestMethod.POST
	@ResponseBody
	public ModelAndView view(
			@RequestParam(value = "bi_nct_sn", required = false, defaultValue = "") int bi_nct_sn,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {



		Notice noticePrive = noticeService.getPriView(bi_nct_sn);
		Notice noticeNext = noticeService.getNextView(bi_nct_sn);

		ModelAndView mav = new ModelAndView();

		if (noticePrive == null) {

			mav.addObject("prevTitle", "이전글");
			mav.addObject("prevDate", "");

		} else {
			
			
			
			String preTitle = "<a href=\"#\" onclick=\"getViewPage('"+noticePrive.getBi_nct_sn()+"');\">"+noticePrive.getBi_titl()+"</a>";
			
			mav.addObject("prevTitle", preTitle);
			mav.addObject("prevDate", noticePrive.getParseModifyDate());

		}

		if (noticeNext == null) {

			mav.addObject("nextTitle", "다음글");
			mav.addObject("nextDate", "");

		} else {
			
			String nextTitle = "<a href=\"#\" onclick=\"getViewPage('"+noticeNext.getBi_nct_sn()+"');\">"+noticeNext.getBi_titl()+"</a>";
			mav.addObject("nextTitle", nextTitle);
			mav.addObject("nextDate", noticeNext.getParseModifyDate());

		}

		mav.addObject("rows", noticeService.findList(bi_nct_sn));
		mav.setViewName("jsonView");

		return mav;

	}



	@RequestMapping(value = "/notice/getMainData.do")
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView mainList(
			 @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage // 현재페이지
			,@RequestParam(value = "pageCount", required = false, defaultValue = "5") int countPerPage // 페이지당
			,@RequestParam(value = "searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value = "searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {

		logger.info("noticeList.do Start");

		int totalNo = noticeService.getTotalCount(searchTitle, searchContent);
		// String pageDivideForm = BoardUtil.dividePageForm(currentPage,
		// countPerPage, totalNo , request, searchTitle,
		// searchContent,"noticeList.do");
		logger.info("totalNo" + totalNo);
		int endPage = 0;
		int startRow = 0;
		int endRow = 0;

		if (totalNo > 0) {

			endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo); // 마지막
																				// 페이지
			startRow = BoardUtil.startRow(currentPage, countPerPage); // 시작 row
			endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage); // 끝
																			// row

		}

		ModelAndView mav = new ModelAndView();

		// mav.addObject("pageDivideForm", pageDivideForm);
		mav.addObject("currentPage", currentPage);
		mav.addObject("totalPage", endPage);
		mav.addObject("totalNo", totalNo);

		mav.addObject("rows", noticeService.findAllList(startRow, endRow,
				searchTitle, searchContent));
		logger.info("noticeList:: ");
		logger.info("noticeList.do end");
		mav.setViewName("jsonView");

		return mav;

	}
	
	
	@RequestMapping(value = "/admin/notice/getListData.do" ,method= RequestMethod.POST)
	@ResponseBody
	public ModelAndView adminList(@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage // 현재
			,@RequestParam(value = "pageCount", required = false, defaultValue = "10") int countPerPage // 페이지당
			,@RequestParam(value = "bi_nct_sn", required = false, defaultValue = "0") int bi_nct_sn
			,@RequestParam(value = "sord", required = false, defaultValue = "") String sord
			,@RequestParam(value = "searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value = "searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		int totalNo = noticeService.getTotalCount(searchTitle, searchContent);

		logger.info("totalNo" + totalNo);
		int endPage = 0;
		int startRow = 0;
		int endRow = 0;

		if (totalNo > 0) {

			endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo); // 마지막
																				// 페이지

			startRow = BoardUtil.startRow(currentPage, countPerPage); // 시작 row

			endRow = BoardUtil.endRow(totalNo, countPerPage, currentPage); // 끝
																			// row

		}

		ModelAndView mav = new ModelAndView();

		// mav.addObject("pageDivideForm", pageDivideForm);
		mav.addObject("currentPage", currentPage);
		mav.addObject("totalPage", endPage);
		mav.addObject("totalNo", totalNo);

		List getList = noticeService.findAllList(startRow, endRow, searchTitle,	searchContent);

		mav.addObject("rows", getList);
		mav.setViewName("jsonView");

		return mav;

	}
	
	
	@RequestMapping(value = "/admin/notice/getViewData.do")
	// , method = RequestMethod.POST
	@ResponseBody
	public ModelAndView adminView(
			@RequestParam(value = "bi_nct_sn", required = false, defaultValue = "") int bi_nct_sn,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {



		Notice noticePrive = noticeService.getPriView(bi_nct_sn);
		Notice noticeNext = noticeService.getNextView(bi_nct_sn);

		ModelAndView mav = new ModelAndView();

		if (noticePrive == null) {

			mav.addObject("prevTitle", "이전글");
			mav.addObject("prevDate", "");

		} else {
			
			
			
			String preTitle = "<a href=\"#\" onclick=\"getViewPage('"+noticePrive.getBi_nct_sn()+"');\">"+noticePrive.getBi_titl()+"</a>";
			
			mav.addObject("prevTitle", preTitle);
			mav.addObject("prevDate", noticePrive.getBi_updt_dt());

		}

		if (noticeNext == null) {

			mav.addObject("nextTitle", "다음글");
			mav.addObject("nextDate", "");

		} else {
			
			String nextTitle = "<a href=\"#\" onclick=\"getViewPage('"+noticeNext.getBi_nct_sn()+"');\">"+noticeNext.getBi_titl()+"</a>";
			mav.addObject("nextTitle", nextTitle);
			mav.addObject("nextDate", noticeNext.getBi_updt_dt());

		}

		mav.addObject("rows", noticeService.findList(bi_nct_sn));
		mav.setViewName("jsonView");

		return mav;

	}
	

	@RequestMapping(value = "/admin/notice/save.do", method = RequestMethod.POST)
	public ModelAndView save(
			  @ModelAttribute("notice") Notice notice 
			, @RequestParam(value = "bi_popup_bgn_dt", required = false, defaultValue = "") String bi_popup_bgn_dt
			, @RequestParam(value = "bi_popup_end_dt", required = false, defaultValue = "") String bi_popup_end_dt
			, BindingResult result			
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		logger.info("아이디:::: "+auth.getName());
		logger.info("bi_popup_bgn_dt:::: " + bi_popup_bgn_dt);
		logger.info("bi_popup_end_dt:::: " + bi_popup_end_dt);
		
		notice.setBi_unity_cust_id(auth.getName()); //아이디 등록
		notice.setBi_inqire_num(0);
		noticeService.save(notice);		
		ModelAndView mav = new ModelAndView();		
		mav.addObject("pageNm","list");
		mav.setViewName("redirect:/admin/notice/gotoPage.do");			
		return mav;
		
	}


	@RequestMapping(value = "/admin/notice/modify.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView modify(
			  @ModelAttribute("notice") Notice notice
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("아이디:::: " + auth.getName());

		
		notice.setBi_unity_cust_id(auth.getName()); // 아이디 등록
		noticeService.update(notice);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/admin/notice/gotoPage.do");
		return mav;
	}



	@RequestMapping(value = "/admin/notice/deleteForm.do", method = RequestMethod.POST)
	public ModelAndView deleteForm(
			@RequestParam(value = "bi_nct_sn", required = false, defaultValue = "") int bi_nct_sn,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ModelAndView mav = new ModelAndView();

		mav.addObject("no", noticeService.getById(bi_nct_sn));
		mav.setViewName("/notice/delete");

		return mav;

	}


	@RequestMapping(value = "/admin/notice/delete.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView delete(
			 @RequestParam(value = "bi_nct_sn", required = false, defaultValue = "") int bi_nct_sn		
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		
		logger.info("삭제");
		
		noticeService.remove(bi_nct_sn);
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "삭제 되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}

	@RequestMapping(value = "/admin/notice/deleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteAll(
			@RequestParam(value = "checkId", required = false, defaultValue = "") String[] checkId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		for (int i = 0; i < checkId.length; i++) {

			
			noticeService.remove(Integer.valueOf(checkId[i]));

		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg",  "삭제되었습니다.");
		mav.setViewName("jsonView");
		return mav;

	}
	
	
	
	@RequestMapping(value = "/notice/mainPopUp.do", method = RequestMethod.POST)
	public ModelAndView getMainPopUpView(
			@RequestParam(value = "bi_popup_yn", required = false, defaultValue = "") String bi_popup_yn,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		Notice notice = noticeService.getMainPopUpView(bi_popup_yn);

		

		ModelAndView mav = new ModelAndView();
		mav.addObject("data",  notice);
		mav.setViewName("jsonView");
		return mav;

	}
	
	
	



}
