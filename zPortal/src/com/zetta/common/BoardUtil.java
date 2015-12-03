
package com.zetta.common;


import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


public class BoardUtil {

	public static int endPage(int currentPage, int countPerPage, int totalNo) { //마지막 페이지 
    	
        
        int extra = totalNo % countPerPage;
        
     
        if ( extra > 0 ){         
      
            return (totalNo - extra )/countPerPage + 1;
            
        } else {
        	
            return totalNo/countPerPage;
            
        }
    }
	
	
	public static int startRow(int currentPage, int countPerPage) { // 시작할 row 카운트
     
     
        if ( currentPage > 1 ){         
      
            return ((currentPage-1) * countPerPage);
            
        } else {
        	
        	
        	return currentPage-1;
           
            
        }
    }
    
	
	public static int endRow(int totalNo, int countPerPage, int currentPage) { // 시작할 row 카운트
		
		int endRow = 0;
		
		if(totalNo > countPerPage*(currentPage-1)){
		
			endRow  = countPerPage;
			 
		}else{
			
			endRow  =  totalNo % (countPerPage*(currentPage-1));
			
			
		}
		
		  return  endRow;
    }
    

    
    public static String getDate(int addDate)
    {

       
    	 Date yymmdd = new Date();
         yymmdd.getTime();
    	
    	DecimalFormat df = new DecimalFormat("00");

        Calendar currentCalendar = Calendar.getInstance();
       
        currentCalendar.add(currentCalendar.DATE, addDate);
        
        String strYear   = Integer.toString(currentCalendar.get(Calendar.YEAR));
        String strMonth  = df.format(currentCalendar.get(Calendar.MONTH) + 1);
        String strDay   = df.format(currentCalendar.get(Calendar.DATE));
        String strDate = strYear + strMonth + strDay;
           
        return strDate;
       }

    public static String convertHtmlBr(String comment) {
        // **********************************************************************
        if (comment == null)
            return "";
        int length = comment.length();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            String tmp = comment.substring(i, i + 1);
            if ("\r".compareTo(tmp) == 0) {
                tmp = comment.substring(++i, i + 1);
                if ("\n".compareTo(tmp) == 0)
                    buffer.append("<br>\r");
                else
                    buffer.append("\r");
            }
            buffer.append(tmp);
        }
        return buffer.toString();
    }


    
    public static String dividePageForm(int currentPage, int countPerPage,int totalNo,
			HttpServletRequest request, int searchTitle, String searchContent, String pageVal) {
		
		
		int endPage = BoardUtil.endPage(currentPage, countPerPage, totalNo);
		
		   StringBuffer sb = new StringBuffer();
		   sb.append("<ul>");
		   if(currentPage != 1){
			   
			   sb.append("<li><a href='"+request.getContextPath()+"/"+pageVal+"?currentPage="+(currentPage-1)+"&countPerPage="+countPerPage+"&searchTitle="+searchTitle+"&searchContent="+searchContent+"'>Prev</a></li>");
		   
		   }else{
			   
			   sb.append("<li class=\"disabled\"><a href=\"#\">Prev</a></li>");
		   }
		   for (int i = 0; i < endPage; i++) {
			   sb.append("<li");
			   if (i == (currentPage-1) ) {
				   sb.append(" class=\"active\"");
			   }
					sb.append(">");
					sb.append("<a href=\"");
					sb.append(request.getContextPath());
					sb.append("/"+pageVal+"?currentPage=");
					sb.append(i + 1);
					sb.append("&countPerPage=");
					sb.append(countPerPage);
					sb.append("&searchTitle="+searchTitle+"&searchContent="+searchContent);
					sb.append("\">");
					sb.append(i + 1);
					sb.append("</a>");
					sb.append("</li>");
		   }
		  
		   if(currentPage != endPage){
			sb.append("<li>");
			sb.append("	<a href='"+request.getContextPath()+"/"+pageVal+"?currentPage="+(currentPage+1)+"&countPerPage="+countPerPage+"&searchTitle="+searchTitle+"&searchContent="+searchContent+"'>");
			sb.append("Next");
			sb.append("</a>");
			sb.append("</li>");
		   }else{
			   
			   sb.append("<li class=\"disabled\"><a href=\"#\">Next</a></li>");
		   }
		    sb.append("</ul>");

		return sb.toString();
	}
	

}
