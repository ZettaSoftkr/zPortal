package com.zetta.qlikview.utils;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class QlikViewUtils {
	private Logger logger = Logger.getLogger(getClass());

	//qvw에서 뽑아낸 DocumentSummary String
	private String sReportSpec;
	//DocumentSummary 을 xml화 한 xml Dom
	private Document oDocument;
	private Document oSheets;
	
	//생성자
	public QlikViewUtils() {}
	
	//생성자
	//qvw 파일중 <DocumentSummary> Xml부분을 뽑아내서 셋팅
	public QlikViewUtils(String reportSpec) {
		
		
		try {
			sReportSpec = reportSpec;
			this.loadDocument();
		}catch (IOException e) {
			 System.out.print("Exception");
		}catch (Exception e) {
			 System.out.print("Exception");
		}finally {
        	
            
          
            
         }
	}
	
	//qvw 파일 path로 <DocumentSummary> Xml부분을 뽑아내서 셋팅
	public QlikViewUtils(Path p) {
		try {
			sReportSpec = this.loadStringWithFile(p);
			this.loadDocument();
		} catch (IOException e) {
			System.out.println("IOException");
		}catch (Exception e) {
			System.out.println("Excption");
		}
	}
	
	public void init(Path p){
		try {
			sReportSpec = this.loadStringWithFile(p);
			sReportSpec = sReportSpec.substring(sReportSpec.indexOf("<DocumentSummary>"),sReportSpec.indexOf("</DocumentSummary>")+ "</DocumentSummary>".length());
			this.loadDocument();
		} catch (IOException e) {
			
			System.out.println("IOException");
			
		}catch (Exception e) {
			
			System.out.println("Excption");
			
		}
	}
	
	private void loadDocument() throws IOException{
		
		
		ByteArrayInputStream bais =  null;
		SAXReader xmlReader = null;
		try {
			
			
			String sheets = "<Sheets>";
			sheets += sReportSpec.substring(sReportSpec.indexOf("<Sheet>"),sReportSpec.lastIndexOf("</Sheet>")+ "</Sheet>".length());
			sheets += "</Sheets>";
			sheets = sheets.replace("Document\\", "");
			 xmlReader = new SAXReader();
			logger.info(sheets);
			 bais = new ByteArrayInputStream(sheets.getBytes("UTF-8"));
			oSheets = xmlReader.read(bais);
			
	
		}catch (IOException e) {
			
			System.out.print("IOException");
		}catch (Exception e) {
			
			System.out.print("Exception");
			
		}finally{
			
			if(bais != null){
				bais.close();
			}
			
         }
	}
	
	//path의 파일을 스트링으로 읽어들임.
	public String loadStringWithFile(Path p) {
		String str = null;
		
		try {
			
			byte[] array = Files.readAllBytes(p);
			//String str = new String(array,"ISO-8859-1");
			 str = new String(array,"UTF-8");
			 
		}catch (IOException e) {
			
			System.out.print("IOException");
			
		}catch (Exception e) {
			
			System.out.print("Exception");
			
		}
		
		return str;
	}
	
	//sheet list얻기
	public String[] getSheets(){
		String[] resultAry = null;		
		String sheets = "";
		
		List list = oSheets.selectNodes("/Sheets/Sheet");
		for(int i=0; list.size() > i; i++){
			Element el = (Element) list.get(i);
			sheets += el.elementText("SheetId") + ",";
		}
		
		if(sheets.length()> 1){
			sheets = sheets.substring(0, sheets.length()-1);
			return sheets.split(",");
		} 
		else
			return null;
	}
	
	
	//sheet 에 딸린 object 얻기
	//public String[] getSheetObjects(String sheetId){
	public String[] getSheetObjects(String prefix){
		String[] resultAry = null;		
		String objects = "";
		
		List list = oSheets.selectNodes("/Sheets/Sheet");
		for(int i=0; list.size() > i; i++){
			Element el = (Element) list.get(i);
			//if(el.elementText("SheetId").equals(prefix)){
				Element childObjects = el.element("ChildObjects");
				for(int j=0; childObjects.elements().size() > j; j++){
					Element objEl = (Element) childObjects.elements().get(j);
					if(objEl==null) break;
					if(objEl.getText().contains(prefix)){
						objects += objEl.getText() + "" + ",";
					}
				}
			//}
		}
		
		if(objects.length()> 1){
			objects = objects.substring(0, objects.length()-1);
			return objects.split(",");
		}
		else
			return null;
	}
}
