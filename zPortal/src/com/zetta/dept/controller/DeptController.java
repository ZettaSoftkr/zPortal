package com.zetta.dept.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.common.BoardUtil;
import com.zetta.dept.model.Dept;
import com.zetta.dept.service.DeptService;

@Controller
public class DeptController {

	public Logger logger = Logger.getLogger(getClass());

	// private static final String DEFAULT_CURRENT_PAGE = "1";
	// private static final String DEFAULT_COUNT_PER_PAGE = "10";

	@Autowired
	DeptService deptService;

	@RequestMapping(value = "/admin/dept/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			@RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {

		if (pageNm.equals("") || pageNm == null) {

			pageNm = "redirect:pageError.do";

		} else {

			pageNm = "admin/dept/" + pageNm;

		}

		//
		// boolean isLogin = loginSessionCheck(request);
		// if (!isLogin)
		// return mapping.findForward("sessionError");

		ModelAndView mav = new ModelAndView();
		mav.setViewName(pageNm);
		// mav.addObject("topMenuList", menuMngService.topMenuList()); //상단메뉴 실행
		return mav;

	}

	@RequestMapping(value = "/admin/dept/getListData.do")
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView list(
			  @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage 
			, @RequestParam(value = "countPerPage", required = false, defaultValue = "10") int countPerPage 
			, @RequestParam(value = "bi_dept_id", required = false, defaultValue = "0") String bi_dept_id
			, @RequestParam(value = "sord", required = false, defaultValue = "") String sord
			, @RequestParam(value = "searchTitle", required = false, defaultValue = "0") int searchTitle
			, @RequestParam(value = "searchContent", required = false, defaultValue = "") String searchContent
			, HttpServletRequest request
			, HttpServletResponse response
			) throws Exception {

		// boolean isLogin = loginSessionCheck(request);
		// if (!isLogin)
		// return mapping.findForward("sessionError");
		logger.info("menuMngList.do Start");

		// String strToday = BoardUtil.getDate(0);

		int totalNo = deptService.getTotalCount(searchTitle, searchContent);
		// String pageDivideForm = BoardUtil.dividePageForm(currentPage,
		// countPerPage, totalNo , request, searchTitle,
		// searchContent,"menuMngList.do");

		int endPage = 0;
		int startRow = 0;
		int endRow = 0;
		if (totalNo > 0) {

			endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo); // 마지막
																				// 페이지

			startRow = BoardUtil.startRow(currentPage, countPerPage); // 시작 row

			endRow = BoardUtil.endRow(totalNo, countPerPage, endPage); // 끝 row

		}

		logger.info("startRow" + startRow);
		logger.info("endRow" + endRow);
		logger.info("endPage" + endPage);

		ModelAndView mav = new ModelAndView();

		// mav.addObject("pageDivideForm", pageDivideForm);
		mav.addObject("currentPage", currentPage);
		mav.addObject("totalPage", endPage);
		mav.addObject("totalNo", totalNo);

		mav.addObject("rows", deptService.findAllList(startRow, endRow,
				searchTitle, searchContent));
		logger.info("menuMngList:: ");
		logger.info("menuMngList.do end");
		mav.setViewName("jsonView");

		return mav;

	}

	/**
	 * 자료실 (상세보기)
	 * 
	 * @param request
	 * @param response
	 * @return view
	 * @throws Exception
	 */

	@RequestMapping(value = "/admin/dept/getViewData.do", method = RequestMethod.POST)
	public ModelAndView view(
			@RequestParam(value = "bi_dept_id", required = false, defaultValue = "") String bi_dept_id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ModelAndView mav = new ModelAndView();

		mav.addObject("rows", deptService.findList(bi_dept_id));
		mav.addObject("prevTitle", "");
		mav.addObject("nextTitle", "");
		mav.setViewName("jsonView");

		return mav;
	}

	

	@RequestMapping(value = "/admin/dept/save.do", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("dept") Dept dept,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		dept.setBi_perm_yn("Y");
		dept.setBi_sort_sn("0");
		deptService.save(dept);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/admin/dept/gotoPage.do");
		return mav;

	}


	@RequestMapping(value = "/admin/dept/modify.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView modify(
			 @ModelAttribute("dept") Dept dept
			, BindingResult result
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {

		deptService.update(dept);
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg","수정완료");
		mav.setViewName("jsonView");	
		return mav;
	}

	/**
	 * 자료실 (수정페이지 전환)
	 * 
	 * @param request
	 * @param response
	 * @return edit
	 * @throws Exception
	 */

	@RequestMapping(value = "/admin/dept/editForm.do", method = RequestMethod.POST)
	public ModelAndView editForm(
			@RequestParam(value = "bi_dept_id", required = false, defaultValue = "") String bi_dept_id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ModelAndView mav = new ModelAndView();

		mav.addObject("dept", deptService.getById(bi_dept_id));
		mav.setViewName("redirect:menuMngList.do");

		return mav;
	}

	@RequestMapping(value = "/admin/dept/deleteForm.do", method = RequestMethod.POST)
	public ModelAndView deleteForm(
			@RequestParam(value = "bi_dept_id", required = false, defaultValue = "") String bi_dept_id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ModelAndView mav = new ModelAndView();

		mav.addObject("menuCode", deptService.getById(bi_dept_id));
		mav.setViewName("/dept/delete");

		return mav;

	}

	@RequestMapping(value = "/admin/dept/delete.do", method = RequestMethod.POST)
	public ModelAndView delete(
			@RequestParam(value = "bi_dept_id", required = false, defaultValue = "") String bi_dept_id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		deptService.remove(bi_dept_id);

		ModelAndView mav = new ModelAndView();

		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/dept/gotoPage.do");

		return mav;

	}
	
	

	@RequestMapping(value = "/admin/dept/getDeptListData.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getDeptlist(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.addObject("rows", deptService.getDeptList());
		mav.setViewName("jsonView");

		return mav;

	}

	
	
	@RequestMapping(value = "/admin/dept/deleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteAll(
			 @RequestParam(value="checkId", required = false, defaultValue = "") String[] checkId
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
	
       
		for(int i=0; i<checkId.length; i++){
			logger.info("선택된 값" + checkId[i]);
			deptService.remove(checkId[i]);
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows",  "삭제 되었습니다.");
		mav.setViewName("jsonView");	
		return mav;
	}
	
	
	@RequestMapping(value = "/dept/getCheckId.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getCheckId(
			@RequestParam(value="bi_dept_id", required = false, defaultValue = "") String bi_dept_id
			,HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
	
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows", deptService.getById(bi_dept_id));		
		mav.setViewName("jsonView");		
		return mav;
	}
	
	
	@RequestMapping(value = "/batch/dept/addDeptList.do", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView addUserList(
			  HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		String qvdName = "CUR_ORG";
		String msg = deptService.addDeptList(qvdName);
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);		
		mav.setViewName("jsonView");		
		return mav;
		
		
	}
	

}
