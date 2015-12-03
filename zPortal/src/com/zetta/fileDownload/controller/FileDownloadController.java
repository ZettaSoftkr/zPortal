package com.zetta.fileDownload.controller;

import java.io.File;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.fileUpload.model.FileUpload;
import com.zetta.fileUpload.service.FileUploadService;
@Controller
public class FileDownloadController implements ApplicationContextAware{
 
	
	public Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	FileUploadService fileUploadService;
	
	
    private WebApplicationContext context = null;
    
    @RequestMapping(value = "/download.do", method = RequestMethod.GET , produces = "application/html; charset=utf8")
    public ModelAndView download(  @RequestParam(value="fileSn", required = false, defaultValue = "0") int fileSn //현재 페이지                         
                                  , HttpServletRequest request
                      			  , HttpServletResponse response){
    	
    	
    	logger.info("fileSn" + fileSn);
    
    	FileUpload fileUpload = fileUploadService.getById(fileSn);
    	
    	  String fullPath = "";
    	  
    	  logger.info("파일경로:" + fileUpload.getBi_atch_flpth_nm());
    	  logger.info("파일명:" + fileUpload.getBi_tmpr_atch_file_nm());
    	 
    	
 
    		
       fullPath =  request.getRealPath(fileUpload.getBi_atch_flpth_nm()+"\\" +fileUpload.getBi_tmpr_atch_file_nm());        
       String fileNm= fileUpload.getBi_atch_file_nm();      
         
        File file = new File(fullPath);
    	ModelAndView mav = new ModelAndView();
        
        mav.addObject("downloadFile", file);
        mav.addObject("fileNm", fileNm);
		mav.setViewName("DownloadView");
         
       // return new ModelAndView("DownloadView", "downloadFile", file);
		
		return mav;
    }
    
    @RequestMapping(value = "/manualDownload.do", method = RequestMethod.GET , produces = "application/html; charset=utf8")
    public ModelAndView manualDownload(  @RequestParam(value="fileNm", required = false, defaultValue = "0") String fileNm //현재 페이지                         
                                  , HttpServletRequest request
                      			  , HttpServletResponse response){
    	
    	String fullPath =  request.getRealPath("/file/manual/" +fileNm);    
        File file = new File(fullPath);
    	ModelAndView mav = new ModelAndView();
        
        mav.addObject("downloadFile", file);
        mav.addObject("fileNm", fileNm);
		mav.setViewName("DownloadView");
         
       // return new ModelAndView("DownloadView", "downloadFile", file);
		
		return mav;
    }
 
    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        // TODO Auto-generated method stub
        this.context = (WebApplicationContext)arg0;
         
    }
     
}
