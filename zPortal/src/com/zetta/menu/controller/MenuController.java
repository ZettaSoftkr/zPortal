package com.zetta.menu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.zetta.menu.model.Menu;
import com.zetta.menu.service.MenuService;
import com.zetta.menuGroup.model.MenuGroupMapp;
import com.zetta.menuGroup.service.MenuGroupService;

@Controller
public class MenuController {

	public Logger logger = Logger.getLogger(getClass());


	@Autowired
	MenuService menuService;

	@Autowired
	MenuGroupService menuGroupService;
	

	@RequestMapping(value = "/admin/menu/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			 @RequestParam(value = "pageNm", required = false, defaultValue = "") String pageNm
			,@RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
			,@RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
			,@RequestParam(value = "lev3_menuOpenId", required = false, defaultValue = "") String lev3_menuOpenId
			,@RequestParam(value = "currentId", required = false, defaultValue = "") String currentId
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		if (pageNm.equals("") || pageNm == null) {

			pageNm = "redirect:pageError.do";

		} else {

			pageNm = "admin/menu/" + pageNm;

		}

		//
		// boolean isLogin = loginSessionCheck(request);
		// if (!isLogin)
		// return mapping.findForward("sessionError");

		ModelAndView mav = new ModelAndView();
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev3_menuOpenId", lev3_menuOpenId);
		mav.addObject("currentId", currentId);
		mav.setViewName(pageNm);
		// mav.addObject("topMenuList", menuService.topMenuList()); //상단메뉴 실행
		return mav;

	}

	@RequestMapping(value = "/admin/menu/getListData.do")
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("menuList.do Start");

		ModelAndView mav = new ModelAndView();

		List list = new ArrayList();
		

		List<Menu> menuList = menuService.findAllList();
	
		logger.info("전체 갯수 " + menuList.size());
		for (int i = 0; i < menuList.size(); i++) {
			
			HashMap<String, String> map = new HashMap<String, String>();
			
			map.put("id", menuList.get(i).getBi_portal_menu_id());
			map.put("parent", menuList.get(i).getBi_portal_menu_parent_id());
			map.put("text", menuList.get(i).getBi_menu_nm());
			map.put("bi_menu_url", menuList.get(i).getBi_menu_url_addr());
			map.put("bi_menu_ordr", String.valueOf(menuList.get(i).getBi_menu_sort_sn()));
			map.put("bi_menu_stle", menuList.get(i).getBi_menu_fm_yn());

			list.add(map);

		}

	
		mav.addObject("data", list);
		mav.setViewName("jsonView");
		return mav;

	}
	
	
	
	
	@RequestMapping(value = "/menu/getSearchListData.do")
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView searchList(
			@RequestParam(value = "bi_search_keyword", required = false, defaultValue = "") String bi_search_keyword
			,HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		logger.info("menuList.do Start");

		ModelAndView mav = new ModelAndView();
		
		

		List<Menu> menuList = menuService.findSearchList(bi_search_keyword);	
		mav.addObject("data", menuList);
		mav.setViewName("jsonView");

		return mav;

	}
	

	@RequestMapping(value = "/menu/getChildListData.do" ,method= RequestMethod.POST)
	@ResponseBody
	public ModelAndView childList(
			  @RequestParam(value = "bi_parent_id", required = false, defaultValue = "") String bi_parent_id
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		
		

		logger.info("menuList.do Start");
		ModelAndView mav = new ModelAndView();
		List<Menu> list = menuService.findChildList(bi_parent_id);
		
		mav.addObject("data", list);
		mav.setViewName("jsonView");

		return mav;

	}

	@RequestMapping(value = "/admin/menu/getViewData.do", method = RequestMethod.POST)
	public ModelAndView adminView(@RequestParam(value = "bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//
		// boolean isLogin = loginSessionCheck(request);
		// if (!isLogin)
		// return mapping.findForward("sessionError");

		ModelAndView mav = new ModelAndView();
		logger.info(bi_portal_menu_id);

		mav.addObject("data", menuService.findList(bi_portal_menu_id));
		mav.setViewName("jsonView");

		return mav;
	}
	
	
	
	@RequestMapping(value = "/menu/getViewData.do", method = RequestMethod.POST)
	public ModelAndView view(@RequestParam(value = "bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//
		// boolean isLogin = loginSessionCheck(request);
		// if (!isLogin)
		// return mapping.findForward("sessionError");

		ModelAndView mav = new ModelAndView();
		logger.info(bi_portal_menu_id);

		mav.addObject("data", menuService.findList(bi_portal_menu_id));
		mav.setViewName("jsonView");

		return mav;
	}

	
	@Value("#{qvconf['menuGroupAdminCd']}") private String menuGroupAdminCd; // 메뉴 그룹 코드 
	@RequestMapping(value = "/admin/menu/save.do", method = RequestMethod.POST)
	public ModelAndView save(
			@ModelAttribute("menu") Menu menu
			, BindingResult result
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		// boolean isLogin = loginSessionCheck(request);
		// if (!isLogin)
		// return mapping.find
		
		Menu lastMenu =  menuService.getLastMenuId();
		logger.info("lastMenu.getBi_portal_menu_id()" + lastMenu.getBi_portal_menu_id());
		
		Integer menuId = Integer.valueOf(lastMenu.getBi_portal_menu_id()) + 1;
		
		String bi_portal_menu_id = String.valueOf(menuId);
		// 메뉴 등록 
//		String bi_portal_menu_id = menu.getBi_portal_menu_parent_id()+"_"+UUID.randomUUID().toString();
		
		logger.info("bi_portal_menu_id" + bi_portal_menu_id);
		
		menu.setBi_portal_menu_id(bi_portal_menu_id);
		if(menu.getBi_init_yn() == null || !menu.getBi_init_yn().equals("Y")){
			menu.setBi_init_yn("N"); // Y - 초기 설정 값 , N - 보고서 설정 값  보고소 일괄 등록시 N 값만 삭제하여 사용 할수 있도록 함
		}
		
		
		menu = menuService.save(menu);
		
		
		// 관리 메뉴 등록 
		int mappCnt = menuGroupService.getMenuGroupMappCnt(menuGroupAdminCd, bi_portal_menu_id);
		if(mappCnt > 0){
			
			menuGroupService.getMenuGroupMappDelete(menuGroupAdminCd,bi_portal_menu_id);
			
		}
		
		MenuGroupMapp  menuGroupMapp = new MenuGroupMapp();		
		menuGroupMapp.setBi_menu_group_id(menuGroupAdminCd);
    	menuGroupMapp.setBi_portal_menu_id(bi_portal_menu_id);
    	menuGroupService.menuGroupMappSave(menuGroupMapp);	
    	
    	// 관리 메뉴 등록 
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/admin/menu/gotoPage.do");
		return mav;

	}

	@RequestMapping(value = "/admin/menu/modify.do", method = RequestMethod.POST)
	public ModelAndView modify(@ModelAttribute("menu") Menu menu, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		menuService.update(menu);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/admin/menu/gotoPage.do");
		return mav;
	}

	@RequestMapping(value = "/admin/menu/deleteForm.do", method = RequestMethod.POST)
	public ModelAndView deleteForm(@RequestParam(value = "id", required = false, defaultValue = "") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//
		// boolean isLogin = loginSessionCheck(request);
		// if (!isLogin)
		// return mapping.findForward("sessionError");

		ModelAndView mav = new ModelAndView();

		mav.addObject("menuId", menuService.getById(id));
		mav.setViewName("/admin/menu/delete");

		return mav;

	}

	/**
	 * 자료실 (삭제)
	 * 
	 * @param request
	 * @param response
	 * @return list
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/menu/delete.do", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam(value = "bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id, HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("id::: " + bi_portal_menu_id);

		menuService.remove(bi_portal_menu_id);
		menuService.childRemove(bi_portal_menu_id);

		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNm", "list");
		mav.setViewName("redirect:/admin/menu/gotoPage.do");
		return mav;

		// } else {

		// request.setAttribute("error", "비밀번호가 일치 하지 않습니다.");
		// return mapping.findForward("delete");
		// }
	}
	
	@RequestMapping(value = "/admin/menu/deleteOne.do", method = RequestMethod.POST)
	public ModelAndView deleteOne(
			 @RequestParam(value = "menuId", required = false, defaultValue = "") String menuId
			,@RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
			,@RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
			,@RequestParam(value = "lev3_menuOpenId", required = false, defaultValue = "") String lev3_menuOpenId
			,@RequestParam(value = "currentId", required = false, defaultValue = "") String currentId
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		
		logger.info("menuId::" + menuId);
		
		menuService.remove(menuId);
		
		int mappCnt = menuGroupService.getMenuGroupMappMenuCnt(menuId);
		logger.info("mappCnt::" + mappCnt);
		if(mappCnt > 0){				
			
			menuGroupService.getMenuGroupMappMenuDelete(menuId);
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNm", "list");
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev3_menuOpenId", lev3_menuOpenId);
		mav.addObject("currentId", currentId);
		mav.setViewName("redirect:/admin/menu/gotoPage.do");
		return mav;

		// } else {

		// request.setAttribute("error", "비밀번호가 일치 하지 않습니다.");
		// return mapping.findForward("delete");
		// }
	}

	@RequestMapping(value = "/admin/menu/deleteAll.do", method = RequestMethod.POST)
	public ModelAndView deleteAll(
			@RequestParam(value = "menuId", required = false, defaultValue = "") String[] menuId
			,@RequestParam(value = "lev1_menuOpenId", required = false, defaultValue = "") String lev1_menuOpenId
			,@RequestParam(value = "lev2_menuOpenId", required = false, defaultValue = "") String lev2_menuOpenId
			,@RequestParam(value = "lev3_menuOpenId", required = false, defaultValue = "") String lev3_menuOpenId
			,@RequestParam(value = "currentId", required = false, defaultValue = "") String currentId
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		
	
	
		logger.info("삭제:: "  + menuId);
		logger.info("갯수 :: "  + menuId.length);
		
			for (int i = 0; i < menuId.length; i++) {
				
				
				logger.info("삭제:: "  + menuId[i]);
	
				
				menuService.remove(menuId[i]);
				
				int mappCnt = menuGroupService.getMenuGroupMappMenuCnt(menuId[i]);
				
				if(mappCnt > 0){				
					
					menuGroupService.getMenuGroupMappMenuDelete(menuId[i]);
				}
	
			}
		

		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNm", "list");
		mav.addObject("lev1_menuOpenId", lev1_menuOpenId);
		mav.addObject("lev2_menuOpenId", lev2_menuOpenId);
		mav.addObject("lev3_menuOpenId", lev3_menuOpenId);
		mav.addObject("currentId", currentId);
		mav.setViewName("redirect:/admin/menu/gotoPage.do");
		return mav;
	}
	
	
	@RequestMapping(value = "/menu/topMenu.do" ,method= RequestMethod.POST)
	@ResponseBody
	public ModelAndView topMenu(@RequestParam(value = "bi_parent_id", required = false, defaultValue = "") String bi_parent_id, HttpServletRequest request, HttpServletResponse response) throws Exception {

	

	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		

		ModelAndView mav = new ModelAndView();
		mav.addObject("menuList", menuService.getMenuList(bi_parent_id));
		mav.setViewName("jsonView");

		return mav;
	}

	@RequestMapping(value = "/menu/leftMenu.do" ,method= RequestMethod.POST)
	@ResponseBody
	public ModelAndView leftMenu(@RequestParam(value = "bi_parent_id", required = false, defaultValue = "") String bi_parent_id, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// boolean isLogin = loginSessionCheck(request);
		// if (!isLogin)
		// return mapping.findForward("sessionError");

		logger.info("topMenu.do Start");

		ModelAndView mav = new ModelAndView();
		mav.addObject("leftMenu", menuService.leftMenuList(bi_parent_id));
		mav.setViewName("jsonView");

		return mav;

	}
	
	
	@RequestMapping(value = "/menu/getRoleMenuListData.do")
	@ResponseBody
	public ModelAndView RoleMenulist(
			 @RequestParam(value = "bi_portal_menu_parent_id", required = false, defaultValue = "") String bi_portal_menu_parent_id
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		logger.info("menuList.do Start");

		ModelAndView mav = new ModelAndView();

		
		
	     Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		logger.info("아이디:::: "+auth.getName());
		
		List<Menu> menuList = menuService.getRoleMenuList(auth.getName(),  bi_portal_menu_parent_id);
		
		logger.info("전체 갯수 " + menuList.size());		
		logger.info("전체 갯수 " + menuList.toString());
		
	

		mav.addObject("menuList", menuList);
		mav.setViewName("jsonView");

		return mav;

	}
	
	
	
	
	
	@RequestMapping(value = "/menu/getRoleReportListData.do")
	@ResponseBody
	public ModelAndView RoleReportlist(
			 @RequestParam(value = "bi_portal_menu_parent_id", required = false, defaultValue = "") String bi_portal_menu_parent_id
			, @RequestParam(value = "bi_menu_type_yn", required = false, defaultValue = "") String bi_menu_type_yn
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		logger.info("menuList.do Start");

		ModelAndView mav = new ModelAndView();

		
		
	     Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		logger.info("아이디:::: "+auth.getName());
		
		List<Menu> menuList = menuService.getRoleReportList(auth.getName(), bi_portal_menu_parent_id, bi_menu_type_yn);
		
		logger.info("전체 갯수 " + menuList.size());		
		logger.info("전체 갯수 " + menuList.toString());
		
	

		mav.addObject("menuList", menuList);
		mav.setViewName("jsonView");

		return mav;

	}
	
	
	@RequestMapping(value = "/menu/getReportView.do")
	@ResponseBody
	public ModelAndView getReportView(
			 @RequestParam(value = "bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		logger.info("menuList.do Start");
		
		Menu menu = menuService.getById(bi_portal_menu_id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("menu", menu);
		mav.setViewName("jsonView");

		return mav;

	}
	
	
	@RequestMapping(value = "/admin/menu/getChildMaxId.do")
	@ResponseBody
	public ModelAndView getChildMaxId(
			 @RequestParam(value = "bi_portal_menu_parent_id", required = false, defaultValue = "") String bi_portal_menu_parent_id
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		logger.info("menuList.do Start");
		
		Menu menu = menuService.getChildMaxId(bi_portal_menu_parent_id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("menu", menu);
		mav.setViewName("jsonView");

		return mav;

	}
	
	
	@RequestMapping(value = "/admin/menu/getChildCount.do")
	@ResponseBody
	public ModelAndView getChildCount(
			 @RequestParam(value = "bi_portal_menu_parent_id", required = false, defaultValue = "") String bi_portal_menu_parent_id
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		logger.info("menuList.do Start");
		
		Menu menu = menuService.getChildMaxId(bi_portal_menu_parent_id);
		int cnt = menuService.getChildCount(bi_portal_menu_parent_id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("menu", menu);
		mav.addObject("cnt", cnt);
		mav.setViewName("jsonView");

		return mav;

	}
	
	@RequestMapping(value = "/admin/menu/getListGroup.do",method= RequestMethod.POST)
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView listAdminGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("listGroup.do Start");

		ModelAndView mav = new ModelAndView();
		
		
		List<List<String>> list = new ArrayList<List<String>>();
		List<Menu> menuList = menuService.findAllList();	
		
		logger.info("전체 갯수 " + menuList.size());
		
		for (int i = 0; i < menuList.size(); i++) {
			
			List<String> rowlist = new ArrayList<String>();			
			rowlist.add(menuList.get(i).getBi_portal_menu_id());
			rowlist.add(menuList.get(i).getBi_portal_menu_parent_id());
			rowlist.add(menuList.get(i).getBi_menu_nm());
			list.add(rowlist);

		}

		mav.addObject("data", list);
		mav.setViewName("jsonView");

		return mav;

	}
	
	@RequestMapping(value = "/menu/getListGroup.do",method= RequestMethod.POST)
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView listGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("listGroup.do Start");

		ModelAndView mav = new ModelAndView();
		
		
		List<List<String>> list = new ArrayList<List<String>>();
		
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		
	   List<Menu> menuList = menuService.getRoleMenuList(auth.getName(),  "2");	
		
	
		
		logger.info("전체 갯수 " + menuList.size());
		
		for (int i = 0; i < menuList.size(); i++) {
			
			List<String> rowlist = new ArrayList<String>();			
			rowlist.add(menuList.get(i).getBi_portal_menu_id());
			rowlist.add(menuList.get(i).getBi_portal_menu_parent_id());
			rowlist.add(menuList.get(i).getBi_menu_nm());
			list.add(rowlist);

		}

		mav.addObject("data", list);
		mav.setViewName("jsonView");

		return mav;

	}
	
	
	@RequestMapping(value = "/admin/menu/getListCnt.do",method= RequestMethod.POST)
	// ,method= RequestMethod.POST
	@ResponseBody
	public ModelAndView listCntGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("listGroup.do Start");

		ModelAndView mav = new ModelAndView();

		List<Menu> menuList = menuService.findAllList();
		
		mav.addObject("totalCnt", menuList.size());
		mav.setViewName("jsonView");

		return mav;

	}
	
	@RequestMapping(value = "/menu/getMenuNm.do",method= RequestMethod.POST)	
	@ResponseBody
	public ModelAndView getMenuNm(
			  @RequestParam(value = "bi_menu_nm", required = false, defaultValue = "") String bi_menu_nm
			, @RequestParam(value = "bi_portal_menu_parent_id", required = false, defaultValue = "") String bi_portal_menu_parent_id
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		logger.info("######################################################");
		logger.info("bi_menu_nm: " + bi_menu_nm);
		logger.info("bi_portal_menu_parent_id: "+bi_portal_menu_parent_id);
		
		Menu menuList = menuService.getParentMenuNm(bi_menu_nm,bi_portal_menu_parent_id);	
		
		logger.info("menuList: " + menuList);

		ModelAndView mav = new ModelAndView();			
		mav.addObject("data",menuList);
		mav.setViewName("jsonView");

		return mav;

	}
	

	
	@RequestMapping(value = "/menu/getMenuIntro.do",method= RequestMethod.POST)	
	@ResponseBody
	public ModelAndView getMenuIntro(  
			  @RequestParam(value = "bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception{
		
		Menu menu = menuService.getById(bi_portal_menu_id);
		String siteMapNm = "Home > ";
		siteMapNm += menuService.getMenuTree(menu.getBi_portal_menu_id(), menu.getBi_menu_nm(), 0);
		ModelAndView mav = new ModelAndView();
		mav.addObject("rows", menu); // 보고서
		mav.addObject("siteMapNm", siteMapNm);
		mav.setViewName("jsonView");
		return mav;
		
	}
	
	
	@RequestMapping(value = "/menu/getParentMenuNm.do",method= RequestMethod.POST)	
	@ResponseBody
	public ModelAndView getParentMenuNm(
			 @RequestParam(value = "bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {

		logger.info("######################################################");
		
		
		Menu menu       = menuService.getById(bi_portal_menu_id);
		logger.info("####################### menu.getBi_portal_menu_parent_id() + " +menu.getBi_portal_menu_parent_id()+ " ######################");
		Menu parentMenu = menuService.getById(menu.getBi_portal_menu_parent_id());
		logger.info("#################### 끝  ###############################");
		ModelAndView mav = new ModelAndView();			
		mav.addObject("data",parentMenu);
		mav.setViewName("jsonView");

		return mav;

	}
	
	
	
	
	@RequestMapping(value = "/menu/getSiteMap.do",method= RequestMethod.POST)	
	@ResponseBody
	public ModelAndView getSiteMap(  
			  @RequestParam(value = "bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception{
		
				List list  = menuService.getSiteMap(bi_portal_menu_id);
				ModelAndView mav = new ModelAndView();			
				mav.addObject("data",list);
				mav.setViewName("jsonView");

				return mav;
		
	}
	
	
	@RequestMapping(value = "/menu/getRoleQlikViewList.do",method= RequestMethod.POST)	
	@ResponseBody
	public ModelAndView getRoleQlikViewList(  
			  @RequestParam(value = "bi_portal_menu_id", required = false, defaultValue = "") String bi_portal_menu_id
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception{
		
		       logger.info("bi_portal_menu_id::: "+bi_portal_menu_id);
		       Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
			   logger.info("아이디:::: "+auth.getName());
		
				int roleCnt  = menuService.getRoleQlikViewList(auth.getName(), bi_portal_menu_id);
				logger.info("roleCnt" + roleCnt);
				ModelAndView mav = new ModelAndView();			
				mav.addObject("data",roleCnt);
				mav.setViewName("jsonView");

				return mav;
		
	}
	
	

}
