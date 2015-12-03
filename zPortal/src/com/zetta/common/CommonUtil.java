
package com.zetta.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;




public class CommonUtil {
	
	
	public static HashMap<String, String> getReturnNameMappData(List getData, ArrayList<String> getParamNm){
		 
		 HashMap<String,String> hm = new HashMap<String,String>();
		 
		 for (int i = 0; i < getData.size(); i++) {
		     
		        Object[] obj = (Object[]) getData.get(i);
		       
		        for (int j=0;j<obj.length;j++)
		        {
		        	
		        		hm.put(String.valueOf(getParamNm.get(j)), String.valueOf(obj[j]));
		        
		        }

		    }
		 
		 return hm;
	 }

	 public static HashMap<Integer, String> getReturnHashMapData(List getData){
			 
			 HashMap<Integer,String> hm = new HashMap<Integer,String>();
			 
			 
			 for (int i = 0; i < getData.size(); i++) {
			     
			        Object[] obj = (Object[]) getData.get(i);
			        for (int j=0;j<obj.length;j++)
			        {
			        	
			        		hm.put(i, String.valueOf(obj[j]));
			        
			        }
	
			    }
			 
			 return hm;
		 }
		
	 	public static ArrayList<HashMap<String,String>> getReturnListHashMapData(List getData, ArrayList<String> getParamNm){
		 
		 
		 ArrayList <HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		 
		 for (int i = 0; i < getData.size(); i++) {
			 
			   HashMap<String,String> hm = new HashMap<String,String>();
		        Object[] obj = (Object[]) getData.get(i);
		        for (int j=0;j<obj.length;j++)
		        {
		        	hm.put(String.valueOf(getParamNm.get(j)), String.valueOf(obj[j]));
		        }
		        
		      
		        list.add(hm);
		       
		       

		    }
		 
		 return list;
	 }
	 	
	 	/***
	 	 * 
	 	 *  요청변수 필수 항목 HashMap 리턴
	 	 * @param getData
	 	 * @return HashMap<Integer, String>
	 	 */
		 public static HashMap<Integer, String> getReturnHashMapParam(List getData){
			 
			 HashMap<Integer,String> hm = new HashMap<Integer,String>();
			 
			 
			 for (int i = 0; i < getData.size(); i++) {
			     
			        Object[] obj = (Object[]) getData.get(i);
			        for (int j=0;j<obj.length;j++)
			        {
			        	
			        	if(j == 1){
			        		hm.put(i, (String) obj[j]);
			        	}
			        }

			    }
			 
			 return hm;
		 }
			
		 
			/***
		 	 * 
		 	 *  검색 필수 유효성체크 필수항목체크 
		 	 * @param HashMap<String,String> urlParam, HashMap<Integer,String> searchIndisItemHm
		 	 * @return HashMap<String,String>
			 * 
			 */
		 
		 public static HashMap<String,String> getValidataIndisItem(HashMap<String,String> urlParam, HashMap<Integer,String> searchIndisItemHm, HashMap<String,String> errHm){
				
				
			
		
				
				// 필수 항목 체크 (url에 필수적으로 들어가야 할 항목 체크)		 
				 int allchkNum =0;
				 
				 
				for(int i=0;i<searchIndisItemHm.size();i++){
					 Set key = urlParam.keySet();
					 
					 int chkNum = searchIndisItemHm.size(); //필수항목 체크 숫자
					 
					  for (Iterator iterator = key.iterator(); iterator.hasNext();) {
			                   String keyName = (String) iterator.next();
			                   String valueName = (String) urlParam.get(keyName);	 
			                   
			               if(searchIndisItemHm.get(i).toUpperCase().equals(keyName.toUpperCase())){
			                	
			                	allchkNum= allchkNum + 1;
			                	chkNum = chkNum -searchIndisItemHm.size();
			                }
					  }
					  
					  if(chkNum > 0){
						  
						  errHm.put(searchIndisItemHm.get(i), "필수 항목 변수를 존재하지 않습니다.");
						  
					  }
					  
					
				}
				
				int num =searchIndisItemHm.size() -allchkNum;				
				errHm.put("errCnt",String.valueOf(num)); 				
				return errHm;
			}
		 
		 
			/*
			 * 
			 * 검색 항목 변수명 List 담기;
			 * 
			 * 필수항목 제거
			 */
		
	public static ArrayList<String> getRetunItemToList(HashMap<String,String> urlParam, HashMap<Integer,String> searchIndisItemHm){
		
		ArrayList<String> li = new ArrayList<String>();
		
		Set key = urlParam.keySet();
		// 필수 항목 체크 (url에 필수적으로 들어가야 할 항목 체크)		 
		for (Iterator iterator = key.iterator(); iterator.hasNext();) {
            String keyName = (String) iterator.next();
            String valueName = (String) urlParam.get(keyName);
			 int chkNum = urlParam.size(); //필수항목 체크 숫자			 
			 for(int i=0;i<searchIndisItemHm.size();i++){
	               if(keyName.toUpperCase().equals(searchIndisItemHm.get(i).toUpperCase())){
	                	chkNum = chkNum -urlParam.size();
	                }
			  }
			  
			  if(chkNum > 0){
				  
				  li.add(keyName);
				 
			  }
			  
			
		}
		
		
		return li;
	}
	
	
	/*
	 * 
	 * 변수명 유효성체크
	 * 
	 * @param :param -URL 변수값 , searchIndisItemHm -> 검색변수 필수 값
	 */
	
	public static HashMap<String,String> getValidataParam(ArrayList<String> urlParam, HashMap<Integer,String> searchIndisItemHm, HashMap<String,String> errHm){
		//urlParam.get("pParam" + urlParam.get("pPram"));
		// 필수 항목 체크 (url에 필수적으로 들어가야 할 항목 체크)		 
		 int allChkNum =0; //전체건수
		 
		 for (int i=0; i<urlParam.size();i++) {
		
			 int chkNum = urlParam.size(); //필수항목 체크 숫자
			 
			 for(int j=0;j<searchIndisItemHm.size();j++){
				 
	               if(searchIndisItemHm.get(j).toUpperCase().equals(urlParam.get(i).toUpperCase())){
	               
	                	allChkNum= allChkNum + 1;
	                	chkNum = chkNum -urlParam.size();
	                }
			  }
			  
			  if(chkNum > 0){
				  
				  errHm.put(urlParam.get(i), "해당 변수명이 없거나 변수명이 일치하지 않습니다.");
				  
			  }
		}
		
		int num =urlParam.size() -allChkNum;		
		errHm.put("errCnt",String.valueOf(num)); 		
		
		return errHm;
	}
	
	
	 /*
	  *  xml 변환
	  * (non-Javadoc)
	  * @see com.zetta.apiMnt.service.ApiMntService#convertToXml(java.util.ArrayList)
	  */
	
	public static String convertToXml(ArrayList<HashMap<String,String>> getData) throws IOException{
		
		String strXml = null;
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		
		Element root = document.addElement("Data");
		Element header = root.addElement("Header");
		header.addElement("MsgCode").addText("100");
		header.addElement("Title").addText("고객정보");
		header.addElement("dataCount").addText("200");
		
		for(int i=0;i<getData.size();i++){
			
			Element row = root.addElement("row");
			HashMap<String,String> hm = getData.get(i);
			  Set key = hm.keySet();
			  for (Iterator iterator = key.iterator(); iterator.hasNext();) {
	                   String keyName = (String) iterator.next();
	                   String valueName = (String) hm.get(keyName);
	                  // System.out.println(keyName +" = " +valueName);
	                   row.addElement(keyName).addText(valueName);
			  }
		}
		
		strXml = document.asXML();
		
      
		
		return strXml;
	}
	
	
	
	 public static ArrayList<HashMap<String,String>> getReturnHashMapData(List getData, ArrayList<String> getParamNm){
	
		 ArrayList <HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		 
		 if(getParamNm.size() == 1){
			 
			   for (int i = 0; i < getData.size(); i++) {
				 
				    HashMap<String,String> hm = new HashMap<String,String>();
			        hm.put(String.valueOf(getParamNm.get(0)), String.valueOf(getData.get(i)));
			        list.add(hm);
			    }
			 
		 }else{
	
		
		 
		   for (int i = 0; i < getData.size(); i++) {
			 
			   HashMap<String,String> hm = new HashMap<String,String>();
		        Object[] obj = (Object[]) getData.get(i);
		        for (int j=0;j<obj.length;j++)
		        {
		        	hm.put(String.valueOf(getParamNm.get(j)), String.valueOf(obj[j]));
		        }
		        
		      
		        list.add(hm);
		    }
		 
		 
		 
		 }
		 return list;
	 }
			
	 
	 
	 public static ArrayList<HashMap<String,String>>  getReturnIndexHmData(List getData, ArrayList<String> getParamNm,String  searchKeyword){

				
			 ArrayList <HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
			 
			 if(getParamNm.size() == 1){
				  HashMap<String,String> hm = new HashMap<String,String>();
				  
				
				  if(searchKeyword.equals(returnDirect(String.valueOf(getData.get(2))))){
					  
				   for (int i = 0; i < getData.size(); i++) {
					  
				        hm.put(String.valueOf(getParamNm.get(0)), String.valueOf(getData.get(i)));
					   
				    }
				   
				   list.add(hm);
				 }
				 
			 }else{
		
			
			 
			   for (int i = 0; i < getData.size(); i++) {
				 
				   HashMap<String,String> hm = new HashMap<String,String>();
			        Object[] obj = (Object[]) getData.get(i);
			        
			        if(searchKeyword.equals(returnDirect(String.valueOf(obj[2])))){
			        for (int j=0;j<obj.length;j++)
			        {
			        	hm.put(String.valueOf(getParamNm.get(j)), String.valueOf(obj[j]));
			        }
			        
			      
			        list.add(hm);
			        }
			    }
			 
			 
			 
			 }
			 return list;
		 }
	 
	 
	 public static String returnDirect(String name){
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
