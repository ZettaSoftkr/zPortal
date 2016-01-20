package com.zetta.qlikview.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.common.BoardUtil;
import com.zetta.common.DateTimeUtil;
import com.zetta.qlikview.model.QlikView;
import com.zetta.qlikview.model.QlikViewLoginInfo;
import com.zetta.qlikview.service.QlikViewService;

import egovframework.com.utl.fcc.service.EgovStringUtil;


@Controller
public class QlikViewController {

	// Logger선언
	public Logger logger = Logger.getLogger(getClass());

	// 사용객체 선언(Spring DI)
	@Autowired
	QlikViewService qlikViewService;

	@RequestMapping(value = "/qlikview/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			  @RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		if (pageNm.equals("") || pageNm == null) {

			pageNm = "redirect:pageError.do";

		} else {

			pageNm = "qlikview/" + pageNm;

		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName(pageNm);
		return mav;

	}
	
	
	@RequestMapping(value = "/admin/qlikview/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoAdminPage(
			  @RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		if (pageNm.equals("") || pageNm == null) {

			pageNm = "redirect:pageError.do";

		} else {

			pageNm = "admin/qlikview/" + pageNm;

		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName(pageNm);
		return mav;

	}


	@RequestMapping(value = "/admin/qlikview/getListData.do")
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView list(
			  @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage 
			, @RequestParam(value = "countPerPage", required = false, defaultValue = "20") int countPerPage 
			, @RequestParam(value = "searchTitle", required = false, defaultValue = "0") int searchTitle
			, @RequestParam(value = "searchContent", required = false, defaultValue = "") String searchContent
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		logger.info("qlikView.do Start");

		int totalNo = qlikViewService.getTotalCount(searchTitle, searchContent);

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

		mav.addObject("rows", qlikViewService.findAllList(startRow, endRow, searchTitle, searchContent));
		logger.info("qlikView:: ");
		logger.info("qlikView.do end");
		mav.setViewName("jsonView");

		return mav;

	}

	
	@RequestMapping(value = "/admin/qlikview/getViewData.do", method = RequestMethod.POST)
	public ModelAndView view(
			 @RequestParam(value="bi_sn", required = false, defaultValue = "") int bi_sn
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
//	
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
		logger.info("adsfasdf");
	
		ModelAndView mav = new ModelAndView();	
		mav.addObject("prevTitle", "");
		mav.addObject("nextTitle", "");
		mav.addObject("rows", qlikViewService.findList(bi_sn));
		
		mav.setViewName("jsonView");	
	
					
		return mav;	
	}
	
	

	@RequestMapping(value = "/admin/qlikview/save.do", method = RequestMethod.POST)
	public ModelAndView save(
			  @ModelAttribute("qlikView") QlikView qlikView 
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
	
		qlikViewService.save(qlikView);		
		ModelAndView mav = new ModelAndView();		

		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/admin/qlikview/gotoPage.do");
		return mav;
		
	}
	
	
	
	@RequestMapping(value = "/admin/qlikview/modify.do", method = RequestMethod.POST)
	public ModelAndView modify(@ModelAttribute("qlikView") QlikView qlikView
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
			
	
		qlikViewService.modify(qlikView);
		ModelAndView mav = new ModelAndView();		
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/admin/qlikview/gotoPage.do");	
		return mav;
	}

	
	@RequestMapping(value = "/admin/qlikview/editForm.do", method = RequestMethod.POST)
	public ModelAndView editForm(
			@RequestParam(value="bi_sn", required = false, defaultValue = "") int bi_sn
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
		ModelAndView mav = new ModelAndView();	
		
		mav.addObject("userInfo", qlikViewService.getById(bi_sn));
		mav.addObject("pageNm","list");
		mav.setViewName("redirect:/qlikview/gotoPage.do");		
		
		return mav;
	}

	
	
	
	@RequestMapping(value = "/admin/qlikview/delete.do", method = RequestMethod.POST)
	public ModelAndView delete(
			@RequestParam(value="bi_sn", required = false, defaultValue = "") int bi_sn
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("entering 'remove' method...");
		}

//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
		if (logger.isDebugEnabled()) {
			logger.debug("boardNo : " + bi_sn);
		}
	
		//if (password.equals(board.getPassword())) {
		qlikViewService.remove(bi_sn);
		
		ModelAndView mav = new ModelAndView();			
		
		mav.addObject("userInfo", qlikViewService.getById(bi_sn));
		mav.addObject("pageNm","list");
		mav.setViewName("redirect:/qlikview/gotoPage.do");		
		
		return mav;
			
		
	}
	
	
	@RequestMapping(value = "/admin/qlikview/deleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteAll(
			 @RequestParam(value="checkId", required = false, defaultValue = "") String[] checkId
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
	
       
		for(int i=0; i<checkId.length; i++){
			logger.info("선택된 값" + checkId[i]);
			qlikViewService.remove(Integer.valueOf(checkId[i]));
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows",  "삭제 되었습니다.");
		mav.setViewName("jsonView");	
		return mav;
	}
	
	@RequestMapping(value = "/qlikview/getQlikViewUser.do", method = RequestMethod.POST)
	public ModelAndView qlikviewUser(			
			 HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
//	
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
		logger.info("adsfasdf");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView mav = new ModelAndView();	
		mav.addObject("rows", qlikViewService.qlikViewUser(auth.getName()));
		
		mav.setViewName("jsonView");	
	
					
		return mav;	
	}
	
	
	
	

	@Value("#{qvconf['qlikview.url']}")
	private String qlikviewUrl;

	@Value("#{qvconf['qlikview.batchRequest']}")
	private String batchRequest;

	
	 
	//qlikview 사용자 체크
		@RequestMapping(value = "/qlikview/qlikviewLoginInfo.do")
		@ResponseBody
		public ModelAndView qlikviewLoginInfo(
				  HttpServletRequest request
				, HttpServletResponse response)
				throws Exception {
			
			
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        //auth.getName()
			List<QlikView>  list = qlikViewService.qlikViewLoginCheck(auth.getName().toString());
			
		

			ModelAndView mav = new ModelAndView();
			mav.addObject("rows", list);
			mav.addObject("size", list.size());
			mav.setViewName("jsonView");
			return mav;


		}
	
	//qlikview 사용자 저장
	@RequestMapping(value = "/qlikview/qlikviewLoginInfoSave.do")
	@ResponseBody
	public ModelAndView qlikviewLoginInfoSave(
			@RequestParam(value = "bi_qlikview_user_id", required = false, defaultValue = "") String bi_qlikview_user_id
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		WebAuthenticationDetails authDetails = (WebAuthenticationDetails) auth.getDetails();
		logger.info("authDetails.getSessionId(): "+authDetails.getSessionId() ); 
		QlikViewLoginInfo qlInfo = new QlikViewLoginInfo();
		
		qlInfo.setBi_qlikview_user_id(bi_qlikview_user_id);
		qlInfo.setBi_unity_cust_id(auth.getName());
		qlInfo.setBi_session_id(authDetails.getSessionId());
		
		QlikViewLoginInfo list = qlikViewService.qlikViewLoginSave(qlInfo);
		
		

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "저장되었습니다.");		
		mav.setViewName("jsonView");


		return mav;

	}
	
	
	//qlikview 사용자 저장
		@RequestMapping(value = "/qlikview/qlikViewLoginEdit.do")
		@ResponseBody
		public ModelAndView qlikViewLoginEdit(
				   HttpServletRequest request
				, HttpServletResponse response)
				throws Exception {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			WebAuthenticationDetails authDetails = (WebAuthenticationDetails) auth.getDetails();
			logger.info("authDetails.getSessionId(): "+authDetails.getSessionId() ); 
			
			QlikViewLoginInfo getData = qlikViewService.qvLoginInfo(authDetails.getSessionId());
			qlikViewService.qlikViewLoginEdit(getData);
			
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("msg", "저장되었습니다.");		
			mav.setViewName("jsonView");


			return mav;

		}
		
	
	
	//qlikview 사용자 삭제
	@RequestMapping(value = "/qlikview/qlikviewLoginInfoDelete.do")
	@ResponseBody
	public ModelAndView qlikviewLoginInfoDelete(
			@RequestParam(value = "bi_qlikview_user_id", required = false, defaultValue = "") String bi_qlikview_user_id
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {	
		
	
		int  cnt = qlikViewService.qlikViewLoginDelete(bi_qlikview_user_id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "삭제완료");
		mav.setViewName("jsonView");
		return mav;

	}

	//qlikview 사용자 삭제
		@RequestMapping(value = "/qlikview/qlikviewMyLoginInfoDelete.do")
		@ResponseBody
		public ModelAndView qlikviewMyLoginInfoDelete(
				@RequestParam(value = "bi_qlikview_user_id", required = false, defaultValue = "") String bi_qlikview_user_id
				, HttpServletRequest request
				, HttpServletResponse response)
				throws Exception {	
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			int  cnt = qlikViewService.qlikviewMyLoginInfoDelete(auth.getName(), bi_qlikview_user_id);
			ModelAndView mav = new ModelAndView();
			mav.addObject("msg", "삭제완료");
			mav.setViewName("jsonView");
			return mav;

	}
		
		
	@RequestMapping(value = "/qlikview/qlikViewMaxUser.do")
	@ResponseBody
	public ModelAndView qlikViewMaxUser(
		 HttpServletRequest request
		, HttpServletResponse response)
		throws Exception {	
		
		List<QlikView> list = qlikViewService.qlikViewMaxUser();
		ModelAndView mav = new ModelAndView();
		
		
		mav.addObject("size", list.size());
		if(list.size() > 0){
			mav.addObject("rows", list.get(0));
		}
		mav.addObject("msg", "삭제완료");
		mav.setViewName("jsonView");
	return mav;

	}
	
	
	@RequestMapping(value = "/qlikview/qlikViewAddUser.do")
	@ResponseBody
	public ModelAndView qlikViewAddUser(
		@RequestParam(value = "bi_sn", required = false, defaultValue = "") int bi_sn
		, HttpServletRequest request
		, HttpServletResponse response)
		throws Exception {	
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		WebAuthenticationDetails authDetails = (WebAuthenticationDetails) auth.getDetails();
		logger.info("qlikViewAddUser: ");
		logger.info("authDetails.getSessionId(): "+authDetails.getSessionId());
		logger.info("authDetails.getSessionId(): "+authDetails.getSessionId());	
		QlikView qv =qlikViewService.getById(bi_sn);
		
		qv.setBi_qlikview_user_id(qv.getBi_qlikview_user_id());
		qv.setBi_qlikview_user_pwd(qv.getBi_qlikview_user_pwd());
		qv.setBi_session_id(authDetails.getSessionId());
		qv.setBi_sn(bi_sn);
		qv.setBi_unity_cust_id(auth.getName().toString());
		qv.setBi_reg_dt(qv.getBi_reg_dt());
		qv.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		
		QlikView getQv =   qlikViewService.qlikViewAddUser(qv);
		ModelAndView mav = new ModelAndView();	
		mav.addObject("rows", getQv);
		mav.addObject("msg", "삭제완료");
		mav.setViewName("jsonView");
	return mav;

	}
	
	
	@RequestMapping(value = "/qlikview/qlikViewDeleteUser.do")
	@ResponseBody
	public ModelAndView qlikViewDeleteUser(
		  HttpServletRequest request
		, HttpServletResponse response)
		throws Exception {	
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		WebAuthenticationDetails authDetails = (WebAuthenticationDetails) auth.getDetails();
		logger.info("authDetails.getSessionId(): "+authDetails.getSessionId() ); 
		
		
		String user = EgovStringUtil.isNullToString(auth.getName().toString());		
		if(!auth.getName().toString().equals("admin")){
			
			
			QlikView qv = qlikViewService.qlikViewUser(auth.getName().toString());
			qv.setBi_sn(qv.getBi_sn());			
			
			qv.setBi_session_id(null);
			qv.setBi_qlikview_user_id(qv.getBi_qlikview_user_id());
			qv.setBi_qlikview_user_pwd(qv.getBi_qlikview_user_pwd());
			qv.setBi_unity_cust_id(user);
			qv.setBi_reg_dt(qv.getBi_reg_dt());
			qv.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
			QlikView getQv =   qlikViewService.qlikViewAddUser(qv);
			
		
		}
		ModelAndView mav = new ModelAndView();	
		mav.addObject("msg", "삭제완료");
		mav.setViewName("jsonView");
	return mav;

	}
	
	
	@RequestMapping(value = "/qlikview/qvLogtout.do")
	@ResponseBody
	public ModelAndView qvLogtout(
		@RequestParam(value = "bi_unity_cust_id", required = false, defaultValue = "") String bi_unity_cust_id
		,  HttpServletRequest request
		, HttpServletResponse response)
		throws Exception {	
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		WebAuthenticationDetails authDetails = (WebAuthenticationDetails) auth.getDetails();
		logger.info("authDetails.getSessionId(): "+authDetails.getSessionId() ); 
		
		if(!bi_unity_cust_id.equals("admin")){
			
			QlikView qv = qlikViewService.qlikViewUser(bi_unity_cust_id);
			qv.setBi_sn(qv.getBi_sn());
			qv.setBi_session_id(null);
			qv.setBi_qlikview_user_id(qv.getBi_qlikview_user_id());
			qv.setBi_qlikview_user_pwd(qv.getBi_qlikview_user_pwd());
			qv.setBi_unity_cust_id(null);
			qv.setBi_reg_dt(qv.getBi_reg_dt());
			qv.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
			QlikView getQv =   qlikViewService.qlikViewAddUser(qv);
		
		}
		ModelAndView mav = new ModelAndView();	
		mav.addObject("msg", "로그아웃 완료");
		mav.setViewName("jsonView");
	return mav;

	}
	
}
