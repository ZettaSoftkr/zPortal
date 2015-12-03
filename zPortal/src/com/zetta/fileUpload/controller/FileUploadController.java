package com.zetta.fileUpload.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.common.BoardUtil;
import com.zetta.common.DateTimeUtil;
import com.zetta.fileUpload.model.FileUpload;
import com.zetta.fileUpload.service.FileUploadService;



@Controller
public class FileUploadController {
	
	public Logger logger = Logger.getLogger(getClass());
	
	//private static final String DEFAULT_CURRENT_PAGE = "1";
	//private static final String DEFAULT_COUNT_PER_PAGE = "10";

	@Autowired
	FileUploadService fileUploadService;

	@RequestMapping(value = "/fileUpload/gotoPage.do", method = RequestMethod.GET)
	public ModelAndView gotoPage(
			@RequestParam(value="pageNm", required = false, defaultValue = "") String pageNm
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
		if(pageNm.equals("") || pageNm == null){
			
			pageNm = "redirect:pageError.do";
			
		}else{
			
			pageNm = "fileUpload/"+pageNm;
			
		}
		
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName(pageNm);			
		
		return mav;
		
	}
	


	@RequestMapping(value = "/fileUpload/save.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView save(
			  @ModelAttribute("fileUpload") FileUpload fileUpload 			
            , @RequestParam("files[]") MultipartFile files[]
            , @RequestParam("fileType") String fileType
            , @RequestParam("filePath") String filePath
            , BindingResult result			
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
	    logger.info("경로:" +request.getServletPath());
	    logger.info("경로:" +files.length);
	    ModelAndView mav = new ModelAndView();
	    for(int i=0;i< files.length ;i++){
	    	
	    	

	    	

	    	String fileRoot = request.getRealPath("/file/temp/");
	    	fileUpload  =  fileUploadService.uploadMultipleFileHandler(fileUpload, fileRoot, files[i]); //파일 업로드 
	    	
	  	
	    	
	    	fileUpload.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
	    	fileUpload.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
	    	fileUpload.setBi_svc_nm("indicator");
	        fileUpload.setBi_atch_flpth_nm("/file/temp/"); //파일경로
	    	
	    	fileUploadService.save(fileUpload);		    	
	    	
	    	mav.addObject("file_name" , fileUpload.getBi_atch_file_nm());
	   	    mav.addObject("file_size" , fileUpload.getBi_atch_file_mg_byte());
	   	   
	    
	    }
	 
		mav.setViewName("jsonView");	
	
					
		return mav;	
		
	}
	
	
	

	
	
	
	
	
	@RequestMapping(value = "/fileUpload/modify.do", method = RequestMethod.POST)
	public ModelAndView modify(
			@ModelAttribute("fileUpload") FileUpload fileUpload 
			,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ModelAndView mav = new ModelAndView();		
		mav.addObject("pageNm","list");
		mav.setViewName("redirect:/fileUpload/gotoPage.do");		
		return mav;
		
		
		
	}
	

	@RequestMapping(value = "/fileUpload/excelUpload.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView excelUpload(
			  @ModelAttribute("fileUpload") FileUpload fileUpload 			
            , @RequestParam("files[]") MultipartFile files[]
            , @RequestParam("fileType") String fileType
            , @RequestParam("filePath") String filePath
            , BindingResult result			
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
		
	    
	    for(int i=0;i< files.length ;i++){

	    	String fileRoot = request.getRealPath("/file/temp/");
	    	fileUpload  =  fileUploadService.uploadMultipleFileHandler(fileUpload, fileRoot, files[i]); //파일 업로드 
	    	
	    }
	      	
	    	
		fileUpload.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		fileUpload.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		fileUpload.setBi_svc_nm("indicator");
	    fileUpload.setBi_atch_flpth_nm("/file/temp/"); //파일경로
		
		fileUploadService.save(fileUpload);	
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("file_name" , fileUpload.getBi_atch_file_nm());
   	    mav.addObject("file_size" , fileUpload.getBi_atch_file_mg_byte());
		mav.setViewName("jsonView");
					
		return mav;	
		
	}
	
	
	
	@RequestMapping(value="/fileUpload/getListData.do")  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView list(
			 @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage //현재 페이지
			,@RequestParam(value="pageCount", required = false, defaultValue = "10") int countPerPage //페이지당 뿌릴 데이터 
			,@RequestParam(value="serviceNm", required = false, defaultValue = "") String serviceNm
			,@RequestParam(value="searchTitle", required = false, defaultValue = "0") int searchTitle
			,@RequestParam(value="searchContent", required = false, defaultValue = "") String searchContent
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		

		logger.info("getListData.do Start");
		logger.info("getListData.do Start"+ serviceNm);
		logger.info("getListData.do Start"+ searchTitle);
		logger.info("getListData.do Start"+ searchContent);
	
		

		int totalNo = fileUploadService.getTotalCount(searchTitle, searchContent, serviceNm);

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
		
		mav.addObject("rows", fileUploadService.findAllList(startRow, endRow, searchTitle, searchContent, serviceNm));
		logger.info("boardList:: ");
		logger.info("boardList.do end");
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}

	
	
	@RequestMapping(value = "/fileUpload/deleteAll.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView adminDeleteAll(
			 @RequestParam(value="checkId", required = false, defaultValue = "") String[] checkId	
			, HttpServletRequest request
			, HttpServletResponse response)
			throws Exception {
       
		 FileUpload fileUpload  = new FileUpload();
		
		for(int i=0; i<checkId.length; i++){
			
			
			FileUpload getFu = fileUploadService.getById(Integer.valueOf(checkId[i]));
					
					
				if(getFu.getBi_atch_flpth_nm().equals("/file/indicator") || getFu.getBi_atch_flpth_nm().equals("/file/board")){
	    		
	    		Path pathfile = Paths.get(request.getRealPath(getFu.getBi_atch_flpth_nm()), getFu.getBi_tmpr_atch_file_nm());
	    		try {
	    			
					Files.delete(pathfile);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    	}else{
	    		
	    		
	    		Path pathfile = Paths.get(getFu.getBi_atch_flpth_nm(), getFu.getBi_tmpr_atch_file_nm());
	    		try {
	    			
					Files.delete(pathfile);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		
	    	}
			  	 
			  
					
			
			
			logger.info("아이디: "+ checkId[i]);
			fileUpload.setBi_atch_file_sn(Integer.valueOf(checkId[i]));
			fileUploadService.remove(fileUpload.getBi_atch_file_sn());
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg","삭제 되었습니다.");
		mav.setViewName("jsonView");
		return mav;
		
		
	}
	
	
	
	@RequestMapping(value="/fileUpload/getBoardListData.do", method = RequestMethod.POST)  //,method= RequestMethod.POST
	@ResponseBody
	public  ModelAndView fileBoardList(
			 @RequestParam(value="bi_sn", required = false, defaultValue = "0") int bi_sn //현재 페이지
			,@RequestParam(value="serviceNm", required = false, defaultValue = "") String serviceNm			
			,HttpServletRequest request
			,HttpServletResponse response)
			throws Exception {
		
		ModelAndView mav = new ModelAndView();	
		
		logger.info("bi_sn:" + bi_sn);
		logger.info("serviceNm:" + serviceNm);

		
		mav.addObject("rows", fileUploadService.fileBoardList(bi_sn, serviceNm));
		mav.setViewName("jsonView");	
		
		return mav;
		
		
	}
	
	
	 @RequestMapping(value = "/fileUpload/fileDelete.do", method = RequestMethod.POST)
	 @ResponseBody
	 public ModelAndView fileDelete(  @RequestParam(value="bi_atch_file_sn", required = false, defaultValue = "0") int bi_atch_file_sn //현재 페이지                         
	                                  , HttpServletRequest request
	                      			  , HttpServletResponse response){
	    	
	    	
	    	logger.info("bi_atch_file_sn" + bi_atch_file_sn);
	    
	    	FileUpload fileUpload = fileUploadService.getById(bi_atch_file_sn);
	    	
	    	  String fullPath = "";
	    	  
	    	  logger.info("파일경로:" + fileUpload.getBi_atch_flpth_nm());
	    	  logger.info("파일명:" + fileUpload.getBi_tmpr_atch_file_nm());
	    	  
	    	
	    	if(fileUpload.getBi_atch_flpth_nm().equals("/file/indicator") || fileUpload.getBi_atch_flpth_nm().equals("/file/board")){
	    		
	    		Path pathfile = Paths.get(request.getRealPath(fileUpload.getBi_atch_flpth_nm()), fileUpload.getBi_tmpr_atch_file_nm());
	    		try {
	    			
					Files.delete(pathfile);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    	}else{
	    		
	    		
	    		Path pathfile = Paths.get(fileUpload.getBi_atch_flpth_nm(), fileUpload.getBi_tmpr_atch_file_nm());
	    		try {
	    			
					Files.delete(pathfile);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		
	    	}
	    	 
	    	int delNum = fileUploadService.remove(bi_atch_file_sn);
	      
	      
	         
	      
	    	ModelAndView mav = new ModelAndView();
	        mav.addObject("msg", "삭제되었습니다.");
			mav.setViewName("jsonView");
	         
	       // return new ModelAndView("DownloadView", "downloadFile", file);
			
			return mav;
	    }
	
	
}
