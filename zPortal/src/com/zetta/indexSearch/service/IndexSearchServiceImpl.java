package com.zetta.indexSearch.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class IndexSearchServiceImpl implements IndexSearchService {

	public Logger logger = Logger.getLogger(getClass());
	
	
	
	@Override
	public boolean matchString(String keyword, String value){ 
		
		
		logger.info(value +" 초성은 :"+ this.returnDirect(value));
		
		if(keyword.equals(this.returnDirect(value))){
			
			return true;
			
		}else{
			
			
			return false;
		}
		
       
    }

	 public String returnDirect(String name){
	        char b =name.charAt(0);
	        String chosung = null;
	        int first = (b - 44032 ) / ( 21 * 28 );
	         switch(first){
	             case 0:
	             case 1:
	                 chosung="ㄱ";
	                 break;
	             case 2:
	                 chosung="ㄴ";
	                 break;
	             case 3:
	             case 4:
	                 chosung="ㄷ";
	                 break;
	             case 5:
	                 chosung="ㄹ";
	                 break;
	             case 6:
	                 chosung="ㅁ";
	                 break;
	             case 7:
	             case 8:
	                 chosung="ㅂ";
	                 break;
	             case 9:
	             case 10:
	                 chosung="ㅅ";
	                 break;
	             case 11:
	                 chosung="ㅇ";
	                 break;
	             case 12:
	             case 13:
	                 chosung="ㅈ";
	                 break;
	             case 14:
	                 chosung="ㅊ";
	                 break;
	             case 15:
	                 chosung="ㅋ";
	                 break;
	             case 16:
	                 chosung="ㅌ";
	                 break;
	             case 17:
	                 chosung="ㅍ";
	                 break;
	             case 18:
	                 chosung="ㅎ";
	                 break;
	           
	         }     
	     
	      return chosung;
	 }
}
