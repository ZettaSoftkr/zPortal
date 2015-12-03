package com.zetta.report.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.menu.model.Menu;
import com.zetta.menu.service.MenuService;
import com.zetta.qlikview.service.QlikViewService;
import com.zetta.report.service.ReportService;




@Controller
public class ReportController {
	
	public Logger logger = Logger.getLogger(getClass());
	
	//private static final String DEFAULT_CURRENT_PAGE = "1";
	//private static final String DEFAULT_COUNT_PER_PAGE = "10";


	@Autowired
	ReportService reportService;
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	QlikViewService qlikViewService;
	

	

	

	@RequestMapping(value = "/report/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			@RequestParam(value="pageNm", required = false, defaultValue = "") String pageNm
			,@RequestParam(value="fileNm", required = false, defaultValue = "") String fileNm
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		if(pageNm.equals("") || pageNm == null){
			
			pageNm = "redirect:pageError.do";
			
		}else{
			
			pageNm = "report/"+pageNm;
			
		}
		
//	
//		boolean isLogin = loginSessionCheck(request);
//		if (!isLogin)
//			return mapping.findForward("sessionError");
		
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName(pageNm);			
		//mav.addObject("topDemoList", demoService.topDemoList()); //상단메뉴 실행 
		return mav;
		
	}
	
	
	@RequestMapping(value = "/report/goReportPage.do")
	@ResponseBody
	public ModelAndView getRedirectPage(
		   @RequestParam(value = "bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id
		 , HttpServletRequest request
		 , HttpServletResponse response
		
			)throws Exception{
	         
		Menu menu = menuService.getById(bi_portal_menu_id);
		Menu tmenu = menuService.getById(menu.getBi_portal_menu_parent_id());
		Menu smenu = menuService.getById(tmenu.getBi_portal_menu_parent_id());
		Menu pmenu = menuService.getById(smenu.getBi_portal_menu_parent_id());
		
		String lev1_parent_id = "";
		String lev2_parent_id = "";
		String lev3_parent_id = "";
		String currentId = "";
		String lev1_menuOpenId = "";
		String lev2_menuOpenId = "";
		String lev1_menuOpen = "";
		String lev2_menuOpen = "";
		
		if(!pmenu.getBi_portal_menu_parent_id().equals("#")){
			
			 lev1_parent_id = pmenu.getBi_portal_menu_id();
			 lev2_parent_id = smenu.getBi_portal_menu_id();
			 lev3_parent_id = tmenu.getBi_portal_menu_id();
			 currentId = menu.getBi_portal_menu_id();
			 lev1_menuOpenId = pmenu.getBi_portal_menu_id();
			 lev2_menuOpenId = tmenu.getBi_portal_menu_id();
			 lev1_menuOpen = "Y";
			 lev2_menuOpen = "Y";
			
		}else{
			
			 lev1_parent_id = smenu.getBi_portal_menu_id();
			 lev2_parent_id = tmenu.getBi_portal_menu_id();
			 currentId = menu.getBi_portal_menu_id();
			 lev1_menuOpenId = smenu.getBi_portal_menu_id();
			 lev2_menuOpenId = tmenu.getBi_portal_menu_id();
			 lev1_menuOpen = "Y";
			
		}
				
			ModelAndView mav = new ModelAndView();
			
		
			
			mav.addObject("lev1_parent_id",lev1_parent_id);
			mav.addObject("lev2_parent_id",lev2_parent_id);
			mav.addObject("lev3_parent_id",lev3_parent_id);
			mav.addObject(" currentId",currentId);
			mav.addObject("lev1_menuOpenId",lev1_menuOpenId);
			mav.addObject("lev2_menuOpenId",lev2_menuOpenId);
			mav.addObject("lev1_menuOpen",lev1_menuOpen);
			mav.addObject("lev2_menuOpen",lev2_menuOpen);
			mav.addObject("pageNm","list");
			mav.setViewName("redirect:/report/gotoPage.do");
			return mav;

		}
	
	
		

	
	
	
	
	  //  http://10.59.32.175/QvAJAXZfc/AccessPoint.aspx?open=&id=QVS%4010.59.32.175%7CQVW%2F%EC%8B%9C%EC%8A%A4%ED%85%9C%EC%A0%95%EB%B3%B4%2FQVW%EC%8A%A4%EC%BA%94.QVW&client=Plugin

		@Value("#{qvconf['qlikview.http']}")
		private String http;
		
		@Value("#{qvconf['qlikview.ip']}")
		private String ip;
		
		@Value("#{qvconf['qlikview.port']}")
		private String port;
		
		@Value("#{qvconf['qlikview.plugInUrl']}")
		private String plugInUrl;
		
		
		@Value("#{qvconf['qlikview.plugInParam1']}")
		private String plugInParam1;
		
		@Value("#{qvconf['qlikview.plugInParam2']}")
		private String plugInParam2;
		
		
		@Value("#{qvconf['qlikview.plugInParam3']}")
		private String plugInParam3;
	
	
		
	@RequestMapping(value = "/report/getPromptUrl.do")
	@ResponseBody
	public ModelAndView getPromptUrl(
			  @RequestParam(value = "bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id
			 , HttpServletRequest request
			 , HttpServletResponse response)
			throws Exception {

	
		// 메뉴 구조 출력 
		
	
		
		
		String urlParam = http+ip+":"+port+"/"+plugInUrl+plugInParam1+ip+plugInParam2;	
		
		Menu menu = menuService.getById(bi_portal_menu_id);
	    
		
		String siteMapNm = "Home >";
		siteMapNm += menuService.getMenuTree(menu.getBi_portal_menu_id(), menu.getBi_menu_nm(), 0);
		
		//String urlParam1 = menuService.getPromptMenuUrl(bi_portal_menu_id, "", 0, objectNmSplit[0]);
		
		 String urlParam1 = "";
		 
		if(menu.getBi_qvw_nm() != null && !menu.getBi_qvw_nm().equals("")){
			
			urlParam1 = menu.getBi_qvw_nm();
			logger.info("urlParam1+:::"+urlParam1);
			
		}else{
		
			logger.info("부모아이디::" + menu.getBi_portal_menu_parent_id());
			logger.info("부모아이디::" + menu.getBi_menu_nm());
			
			urlParam1 = menuService.getPromptMenuUrl(menu.getBi_portal_menu_id(), menu.getBi_menu_nm(), 0);
			
			logger.info("urlParam1+:::"+urlParam1);
		}
		
		String aurl = "";
		if(menu.getBi_aqvw_nm() != null && !menu.getBi_aqvw_nm().equals("")){
			
			aurl = urlParam+URLEncoder.encode(menu.getBi_aqvw_nm(), "UTF-8")+".qvw"+plugInParam3;
		}
		String url = urlParam+ URLEncoder.encode(urlParam1, "UTF-8")+".qvw"+plugInParam3 ;
		
		logger.info("URL 출력::" + url);
		// html 가지고 오기 
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("rows", menu); // 보고서
		mav.addObject("url", url);
		mav.addObject("aurl", aurl);
		mav.addObject("siteMapNm", siteMapNm);
		
		mav.setViewName("jsonView");

		return mav;

	}
	
	
	@RequestMapping(value = "/report/getHistoryUrl.do")
	@ResponseBody
	public ModelAndView getHistoryUrl(
			  @RequestParam(value = "reportId", required = false, defaultValue = "") String reportId
			 , HttpServletRequest request
			 , HttpServletResponse response)
			throws Exception {

	
		// 메뉴 구조 출력 
		
	
		
		
		String urlParam = http+ip+":"+port+"/"+plugInUrl+plugInParam1+ip+plugInParam2;	
		
		 String historyUrl = "";
		
			historyUrl = urlParam+URLEncoder.encode("이력관리/"+reportId, "UTF-8")+".qvw"+plugInParam3;

		// html 가지고 오기 
		
		ModelAndView mav = new ModelAndView();
		

		mav.addObject("historyUrl", historyUrl);
		
		
		mav.setViewName("jsonView");

		return mav;

	}
	
	@RequestMapping(value = "/report/getAdvencedUrl.do")
	@ResponseBody
	public ModelAndView getAdvencedUrl(
			  @RequestParam(value = "reportNm", required = false, defaultValue = "") String reportNm
			 , HttpServletRequest request
			 , HttpServletResponse response)
			throws Exception {

	
		// 메뉴 구조 출력 
		
	
		
		
		String urlParam = http+ip+":"+port+"/"+plugInUrl+plugInParam1+ip+plugInParam2;	
		
		 String historyUrl = "";
		
			historyUrl = urlParam+URLEncoder.encode(reportNm, "UTF-8")+".qvw"+plugInParam3;

		// html 가지고 오기 
		
		ModelAndView mav = new ModelAndView();
		

		mav.addObject("historyUrl", historyUrl);
		
		
		mav.setViewName("jsonView");

		return mav;

	}
	@RequestMapping(value = "/report/getBackupUrl.do")
	@ResponseBody
	public ModelAndView getBackupUrl(
			  @RequestParam(value = "reportNm", required = false, defaultValue = "") String reportNm
			 , HttpServletRequest request
			 , HttpServletResponse response)
			throws Exception {

	
		// 메뉴 구조 출력 
		
	
		
		
		String urlParam = http+ip+":"+port+"/"+plugInUrl+plugInParam1+ip+plugInParam2;	
		
		 String historyUrl = "";
		
			historyUrl = urlParam+URLEncoder.encode(reportNm, "UTF-8")+".qvw"+plugInParam3;

		// html 가지고 오기 
		
		ModelAndView mav = new ModelAndView();
		

		mav.addObject("historyUrl", historyUrl);
		
		
		mav.setViewName("jsonView");

		return mav;

	}
	/*

	@RequestMapping(value = "/report/getPromptUrl.do")
	@ResponseBody
	public ModelAndView getPromptUrl(
			 @RequestParam(value = "bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id
			 , HttpServletRequest request
			 , HttpServletResponse response)
			throws Exception {

		
		
		 Menu menu = menuService.getById(bi_portal_menu_id);
		 
		 Menu parentMenu = menuService.getById(menu.getBi_portal_menu_parent_id()); //부모  경영현황	 
		 
		
		Calendar oCalendar = Calendar.getInstance();
		
	
		
		
		
		String indexNum =  null;   //html index 값
		String resultStr = null;   //html
		String searchParam = null; //검색조건

	   
	    
	  
		String objectNm =  menu.getBi_object_nm();
		String objectNmSplit[] = objectNm.split("=");

		//String urlParam1 = menuService.getPromptMenuUrl(bi_portal_menu_id, "", 0, objectNmSplit[0]);
		
		String urlParam1 = "";
		if(menu.getBi_qvw_nm() != null && !menu.getBi_qvw_nm().equals("")){
			urlParam1 = sheetParam +"\\"+menu.getBi_qvw_nm()+".qvw";
		}else{
		
			urlParam1 = menuService.getPromptMenuUrl(menu.getBi_portal_menu_parent_id(), "", 0, objectNmSplit[0]);
		}
		
	
		String urlParam2 = "&host=QVS%40di-bi&" + menu.getBi_object_nm() + menu.getBi_menu_nm();		
		
	    String bi_inqire_param =  menu.getBi_inqire_param();
		String[] paramSplit = bi_inqire_param.split(",");		
		
		//String urlParam3 = reportService.getDefaultParamVal(paramSplit, hmParam);	
		
		
		String url = qlikviewUrl + "/" + urlParam1 + urlParam2;
		logger.info("URL 출력::" + url);

		
		//index 가지고 오기 
		
		
		// html 가지고 오기 
		
		ModelAndView mav = new ModelAndView();
		
		
		
		mav.addObject("url", url); //selected
		mav.addObject("reportNm", menu.getBi_menu_nm()); // 보고서
		mav.addObject("htmlType", objectNmSplit[0]);
		mav.setViewName("jsonView");

		return mav;

	}
	
	*/
	

}
