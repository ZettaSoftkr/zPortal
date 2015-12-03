 package com.zetta.userInfo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.common.BoardUtil;
import com.zetta.sso.service.SsoService;
import com.zetta.userInfo.model.UserInfo;
import com.zetta.userInfo.service.UserInfoService;



@Controller
public class UserInfoController {
	
	public Logger logger = Logger.getLogger(getClass());
	
	//private static final String DEFAULT_CURRENT_PAGE = "1";
	//private static final String DEFAULT_COUNT_PER_PAGE = "10";

	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	SsoService ssoService;

	@RequestMapping(value = "/admin/userInfo/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			@RequestParam(value="pageNm", required = false, defaultValue = "") String pageNm
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		if(pageNm.equals("") || pageNm == null){
			
			pageNm = "redirect:pageError.do";
			
		}else{
			
			pageNm = "admin/userInfo/"+pageNm;
			
		}
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName(pageNm);	
		return mav;
		
	}

	
	

	@RequestMapping(value="/admin/userInfo/getListData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView list(
			 @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			,@RequestParam(value="countPerPage", required = false, defaultValue = "10") int countPerPage //페이지당 뿌릴 데이터 
			,@RequestParam(value="searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value="searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		

		logger.info("menuMngList.do Start");
						
		logger.info("searchTitle:" + searchTitle);
		logger.info("searchContent:" + searchContent);
	
		
		int totalNo = userInfoService.getTotalCount(searchTitle, searchContent);
	
		
		int endPage = 0;
		int startRow =	0;	
		int endRow = 0;
		if(totalNo > 0){
		
		 endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo);  // 마지막 페이지 		
		 startRow = BoardUtil.startRow(currentPage, countPerPage);  // 시작 row		
		 endRow = BoardUtil.endRow(totalNo, countPerPage, endPage);  //끝 row		
		} 
		
		logger.info("endPage" + endPage);
		logger.info("startRow" + startRow);
		logger.info("endRow" + endRow);
		
		
		ModelAndView mav = new ModelAndView();	
		
		//mav.addObject("pageDivideForm", pageDivideForm);
		 mav.addObject("currentPage", currentPage);
		 mav.addObject("totalPage", endPage);
		 mav.addObject("totalNo", totalNo);	
		
		mav.addObject("rows", userInfoService.findAllList(startRow, endRow, searchTitle, searchContent));
	
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}

	
	@RequestMapping(value = "/admin/userInfo/getViewData.do", method = RequestMethod.POST)
	public ModelAndView view(
			 @RequestParam(value="bi_unity_cust_id", required = false, defaultValue = "") String bi_unity_cust_id
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
		mav.addObject("rows", userInfoService.findList(bi_unity_cust_id));
		
		mav.setViewName("jsonView");	
	
					
		return mav;	
	}
	
	

	@RequestMapping(value = "/admin/userInfo/save.do", method = RequestMethod.POST)
	public ModelAndView save(
			  @ModelAttribute("userInfo") UserInfo userInfo 
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		logger.info("userInfo:: "+userInfo);
		
		
		if(userInfo.getBi_init_yn() == null || !userInfo.getBi_init_yn().equals("Y")){
			userInfo.setBi_init_yn("N"); // Y - 초기 설정 그룹
		}
	
		userInfoService.save(userInfo);		
		ModelAndView mav = new ModelAndView();		
//		mav.addObject("msg","등록 되었습니다.");
//		mav.setViewName("jsonView");	
		
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/admin/userInfo/gotoPage.do");
		return mav;
		
	}
	
	
	
	@RequestMapping(value = "/admin/userInfo/modify.do", method = RequestMethod.POST)
	public ModelAndView modify(@ModelAttribute("userInfo") UserInfo userInfo
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
			
	
		userInfoService.update(userInfo);
		ModelAndView mav = new ModelAndView();		
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/admin/userInfo/gotoPage.do");	
		return mav;
	}

	
	@RequestMapping(value = "/admin/userInfo/editForm.do", method = RequestMethod.POST)
	public ModelAndView editForm(
			@RequestParam(value="bi_unity_cust_id", required = false, defaultValue = "") String bi_unity_cust_id
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
		
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
		ModelAndView mav = new ModelAndView();	
		
		mav.addObject("userInfo", userInfoService.getById(bi_unity_cust_id));
		mav.addObject("pageNm","list");
		mav.setViewName("redirect:/userInfo/gotoPage.do");		
		
		return mav;
	}

	
	@RequestMapping(value = "/admin/userInfo/deleteForm.do", method = RequestMethod.POST)
	public ModelAndView deleteForm(
			@RequestParam(value="bi_unity_cust_id", required = false, defaultValue = "") String bi_unity_cust_id
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {

		
		ModelAndView mav = new ModelAndView();			
		
		mav.addObject("bi_unity_cust_id", userInfoService.getById(bi_unity_cust_id));
		mav.addObject("pageNm","list");
		mav.setViewName("redirect:/userInfo/gotoPage.do");		
		
		return mav;

	}
	
	@RequestMapping(value = "/admin/userInfo/delete.do", method = RequestMethod.POST)
	public ModelAndView delete(
			@RequestParam(value="bi_unity_cust_id", required = false, defaultValue = "") String bi_unity_cust_id
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
			logger.debug("boardNo : " + bi_unity_cust_id);
		}
	
		//if (password.equals(board.getPassword())) {
		userInfoService.remove(bi_unity_cust_id);
		
		ModelAndView mav = new ModelAndView();			
		
		mav.addObject("userInfo", userInfoService.getById(bi_unity_cust_id));
		mav.addObject("pageNm","list");
		mav.setViewName("redirect:/userInfo/gotoPage.do");		
		
		return mav;
			
		
	}
	
	
	@RequestMapping(value = "/admin/userInfo/deleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteAll(
			 @RequestParam(value="checkId", required = false, defaultValue = "") String[] checkId
			, HttpServletRequest request
			, HttpServletResponse response)
			
			throws Exception {
		
	logger.info("삭제하기");
		
		for(int i=0; i<checkId.length; i++){
			
			logger.info("선택된 값" + checkId[i]);
			userInfoService.remove(checkId[i]);
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows",  "삭제 되었습니다.");
		mav.setViewName("jsonView");	
		return mav;
	}
	
	
	@RequestMapping(value = "/userInfo/getUserInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getUserInfo(
			  HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("아이디:::: "+auth.getName().toString());
		UserInfo user = userInfoService.getById(auth.getName().toString());
		
		logger.info("user::"+  user.getBi_user_nm());
		String userId  = user.getBi_unity_cust_id();
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows", userInfoService.getById(auth.getName().toString()));	
		mav.addObject("id", ssoService.EncodeBySType(userId));	
	
		mav.setViewName("jsonView");		
		return mav;
		
		
	}
	

	
	@RequestMapping(value = "/userInfo/getCheckId.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getCheckId(
			@RequestParam(value="bi_unity_cust_id", required = false, defaultValue = "") String bi_unity_cust_id
			,HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
	
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows", userInfoService.getById(bi_unity_cust_id));		
		mav.setViewName("jsonView");		
		return mav;
	}
	
	
	
	
	

	
	
	@RequestMapping(value = "/admin/userInfo/getDeptUserInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getDeptUserInfo(
			@RequestParam(value="bi_dept_id", required = false, defaultValue = "") String bi_dept_id
			,HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows", userInfoService.findDeptUserList(bi_dept_id));		
		mav.setViewName("jsonView");		
		return mav;
		
		
	}
	
	
	@RequestMapping(value = "/admin/userInfo/getUserList.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getUserList(
			@RequestParam(value="bi_user_nm", required = false, defaultValue = "") String bi_user_nm
			,HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows", userInfoService.findUserList(bi_user_nm));		
		mav.setViewName("jsonView");		
		return mav;
		
		
	}
	
	
	
	@RequestMapping(value = "/batch/userInfo/addUserList.do", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView addUserList(
			  HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		String qvdName = "CUR_EMPLOYEE";
		String msg = userInfoService.addUserList(qvdName);
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);		
		mav.setViewName("jsonView");		
		return mav;
		
		
	}
	
	/*
	 * 관리실 > 사용자 목록 > 사용자관리  : 전체 조직 -사용자 보기
	 * @param :
	 * @return : list(부서ID,부서명, 사용자ID, 사용자명 )
	 * @return : json data 
	 * 조직 -사용자 트리 표현 
	 */
	
	@RequestMapping(value = "/admin/userInfo/getDeptUserList.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getDeptUserList(
			  HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
	
		List list = userInfoService.getDeptUserList();
		
		logger.info("list"+ list.size());
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", list);		
		mav.setViewName("jsonView");		
		return mav;
		
		
		
		
	}
	
	
	
	
	@RequestMapping(value = "/admin/userInfo/getUserNm.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getUserNm(
			  @RequestParam(value="bi_unity_cust_id", required = false, defaultValue = "") String bi_unity_cust_id
			,  HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		
		logger.info("bi_unity_cust_id::" + bi_unity_cust_id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows", userInfoService.getById(bi_unity_cust_id));	
	
	
		mav.setViewName("jsonView");		
		return mav;
		
		
	}
	
	
	

}
