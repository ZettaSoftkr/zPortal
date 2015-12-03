package com.zetta.fileDownload.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

@Controller
public class DownloadView extends AbstractView {
 
	public Logger logger = Logger.getLogger(getClass());
	
	
    public void Download(){
        setContentType("application/download; utf-8");
    }
    
    @Override     
    protected void renderMergedOutputModel(
    		  Map<String, Object> model
    		, HttpServletRequest request
    		, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
         
        File file = (File)model.get("downloadFile");
        logger.info("DownloadView --> file.getPath() : " + file.getPath());
        logger.info("DownloadView --> file.getName() : " + file.getName());   
        
        
        //String  fileName = (String) model.get("fileNm");
        
         
        response.setContentType(getContentType());
        response.setContentLength((int)file.length());
         
        String userAgent = request.getHeader("User-Agent");
         
        boolean ie = userAgent.indexOf("MSIE") > -1;
         
        String fileName = null;
         
        if(ie){
             
            fileName = URLEncoder.encode((String) model.get("fileNm"), "utf-8");
                         
        } else {
             
            fileName = new String(((String) model.get("fileNm")).getBytes("utf-8"));
             
        }// end if; 
         
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName,"utf-8") + "\";");         
        response.setHeader("Content-Transfer-Encoding", "binary");
         
        OutputStream out = response.getOutputStream();
         
        FileInputStream fis = null;
         
        try {
             
            fis = new FileInputStream(file);
             
            FileCopyUtils.copy(fis, out);
             
             
        }catch(IOException e){
            
          System.out.print("IOException");
             
       }catch(Exception e){
             
           System.out.print("Exceptoin");
              
       }finally{
             
   
            fis.close();
            out.flush();
             
        }// try end;
         
      
         
    }// render() end;

	
	

}