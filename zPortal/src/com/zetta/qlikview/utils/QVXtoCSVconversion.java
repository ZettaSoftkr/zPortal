package com.zetta.qlikview.utils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class QVXtoCSVconversion {
    
	public Logger logger = Logger.getLogger(getClass());	

	
	
    public QVXtoCSVconversion() {
    }
    
    public List getValues(String qvxFileName,String outputPath) throws IOException{
    	
    	
    	
    	//String resultStr = "";  
        QVXReader qvxReader = null;
        List<String> mArrayList = new ArrayList<String>();	
    	//List list = new ArrayList();
    	logger.info("qvxFileName: " + qvxFileName);
        try {
        	
        	qvxReader = new QVXReader(outputPath + "/QVX/" + qvxFileName +".qvx"); 
        
        	String[] header = qvxReader.getHeaderFieldNames();
        	logger.info("header: " + header);
        
        	int cnt = 0;
        	int num = 0;
            while (qvxReader.hasRecord()) {
            	
            	String recodeString = qvxReader.getRecodeString();    
            	
            	
            	if(!recodeString.equals("")){

            		 mArrayList.add(recodeString);
            		
            	}
            	
               cnt = cnt +1;	
            }
            
            logger.info("mArrayList:::: " + mArrayList.toString());
            
        }catch (IOException ex) {
      			System.out.print("IOException");
        }catch (Exception ex) {
        			System.out.print("Exception");
        }finally {
        	
        	 qvxReader.close();    
         }
        
    	return mArrayList;
    }
    
}
