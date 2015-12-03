package com.init.getData.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.dept.service.DeptService;
import com.zetta.group.service.GroupService;
import com.zetta.menu.model.Menu;
import com.zetta.menu.service.MenuService;
import com.zetta.security.service.SecurityService;
import com.zetta.userInfo.service.UserInfoService;




@Controller
public class GetDataController {
	
	public Logger logger = Logger.getLogger(getClass());
	
	//private static final String DEFAULT_CURRENT_PAGE = "1";
	//private static final String DEFAULT_COUNT_PER_PAGE = "10";
	@Autowired
	DeptService deptService;
	
	@Autowired
	UserInfoService userInfoService;

	@Autowired
	SecurityService securityService;
	
	
	@Autowired
	GroupService groupService;
	
	
	@Autowired
	MenuService menuService;


	@RequestMapping(value = "/getData/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			@RequestParam(value="pageNm", required = false, defaultValue = "") String pageNm
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		if(pageNm.equals("") || pageNm == null){
			
			pageNm = "redirect:pageError.do";
			
		}else{
			
			pageNm = "getData/"+pageNm;
			
		}
		

		
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName(pageNm);			
		
		return mav;
		
	}
	
	
	/// 부서 등록
	
	@RequestMapping(value = "/getData/getMenuExcel.do", method = RequestMethod.GET)
	public ModelAndView getDeptExcel(
			 HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		XSSFWorkbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet(URLEncoder.encode("메뉴목록","UTF-8"));
		
		
		List<Menu> menuList = menuService.findAllList();
		
		logger.info("전체 갯수 " + menuList.size());
		for (int i = 0; i < menuList.size(); i++) {
			
			
			logger.info("menuId: "+ menuList.get(i).getBi_portal_menu_id());
			logger.info("parentId: "+ menuList.get(i).getBi_portal_menu_parent_id());
			logger.info("menuNm: "+ menuList.get(i).getBi_menu_nm());
			logger.info("menuUrl: "+ menuList.get(i).getBi_menu_url_addr());
			logger.info("menuOrdr: "+ menuList.get(i).getBi_menu_sort_sn());
			logger.info("menuStle: "+ menuList.get(i).getBi_menu_fm_yn());
			
			
			  Row r = sheet.createRow(i);  
			  
			  Cell c1 = r.createCell(0);
			  c1.setCellValue( menuList.get(i).getBi_portal_menu_id());
			  
			  Cell c2 = r.createCell(1);
			  c2.setCellValue( menuList.get(i).getBi_portal_menu_parent_id());
			  
			  Cell c3 = r.createCell(2);
			  c3.setCellValue( menuList.get(i).getBi_menu_nm());
			  
			  Cell c4 = r.createCell(3);
			  c4.setCellValue( menuList.get(i).getBi_menu_url_addr());
			  
			  Cell c5 = r.createCell(4);
			  c5.setCellValue( menuList.get(i).getBi_menu_sort_sn());
			  
			  Cell c6 = r.createCell(5);
			  c6.setCellValue( menuList.get(i).getBi_menu_fm_yn());
			  

		}
		
		
    	//String excelRoot  = request.getRealPath("\\file\\temp\\excel.xlsx");    	
    	
    	 	
//    	Path newFile = FileSystems.getDefault().getPath(Excelfile);
//    	
//    	
//    	try{
//			
//			Files.createFile(newFile);
//			
//		}catch(IOException e){
//			
//			
//		}
    	
    	logger.info("wb:" + wb);

 		try {

 			FileOutputStream stream =  new FileOutputStream("");
 			logger.info("wb:" + stream);
 			wb.write(stream);
 			stream.close();

 		} catch (FileNotFoundException e) {

 			e.printStackTrace();

 		}catch (IOException e) {

 			e.printStackTrace();

 		}
    	
    	
		ModelAndView mav = new ModelAndView();		
		return mav;
		
	}
	
	
	

	

}
