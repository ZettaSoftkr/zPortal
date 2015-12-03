package com.zetta.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class ExcelUtil {
	

	public static XSSFWorkbook getWorkBook(String fileName) throws IOException {
		
		XSSFWorkbook workBook = null;
		FileInputStream in = null;
		try {

			in = new FileInputStream(fileName);			
			workBook = new XSSFWorkbook(in);
		
		} catch (FileNotFoundException e) {
			
			System.out.print("FileNotFoundException");
			
		}catch (IOException e) {
			
			System.out.print("IOException");
		}catch (Exception e) {
			
			System.out.print("Exception");
		}finally{
			
			in.close();
			
		}
		
		
		return workBook;
	}
	

public List readExcel(XSSFWorkbook workbook, Integer sheetNum,  List<String> metaName) throws IOException {
		
		XSSFSheet sheet = workbook.getSheetAt(sheetNum);
		Iterator<Row> rowIterator = sheet.iterator();
		int i = 0;
		int j = 0;

		List<HashMap<Object, Comparable>> list = new ArrayList();
		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			// 읽어들인 데이터를 저장
			HashMap<Object, Comparable> hm = new HashMap();
			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();			
				
			
				
				if(cell.equals("") || cell == null){
					
					  hm.put(metaName.get(j),String.valueOf("null"));
					 
					  break;
					  
				}else{

					switch (cell.getCellType()) {
	
					case Cell.CELL_TYPE_BOOLEAN:
	
						hm.put(metaName.get(j), String.valueOf(cell.getBooleanCellValue()));
						break;
					case Cell.CELL_TYPE_NUMERIC:
	
						hm.put(metaName.get(j), Integer.valueOf((int) cell.getNumericCellValue()));
						break;
					case Cell.CELL_TYPE_STRING:
	
						hm.put(metaName.get(j), String.valueOf(cell.getStringCellValue()));
						break;
					}
					
				}
				
				j++;

			}
			j = 0;
			list.add(i, hm);
			i++;

		}

		return list;
	}

	
	public String getCellTypeVal(XSSFCell cell){
		
		String cellVal = "";
		
		switch (cell.getCellType()) {

		case Cell.CELL_TYPE_BOOLEAN:

			cellVal = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC:

			cellVal = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:

			cellVal = String.valueOf(cell.getStringCellValue());
			break;
		}
		 
          return cellVal;
		
	}
	
//	public String fileCreate(String fileName) throws IOException{
//		
//		Path newFile = FileSystems.getDefault().getPath(fileName);
//		
//		try{
//			
//			Files.createFile(newFile);
//			
//		}catch(IOException e){
//			
//			
//		}
//		 
//		
//		return "";
//	}
//	
	
	public String fileCopy(String inFileName, String outFileName) throws IOException {

		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			
				
		 fis = new FileInputStream(inFileName);
		 fos = new FileOutputStream(outFileName);
			int data = 0;

			while ((data = fis.read()) != -1) {

				fos.write(data);

			}

		
		} catch (FileNotFoundException e) {
			
			System.out.print("FileNotFoundException");
			
		}catch (IOException e) {
			
			System.out.print("IOException");
		}catch (Exception e) {
			
			System.out.print("Exception");
		}finally{
			
			fis.close();
			fos.close();
			
		}
		

		return "success";
	}


	public void setExcelCellvalue(XSSFWorkbook wb, String searchIndex,  String setValue) {

		String rowNum = searchIndex.split(":")[0];
		String cellNum = searchIndex.split(":")[1];
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow r = sheet.getRow(Integer.valueOf(rowNum));
		XSSFCell c = r.getCell(Integer.valueOf(cellNum));
		c.setCellValue(setValue);

	}
	
	
	
	public static void setCell(XSSFRow r,int cellNum,int i){
		
		
		  XSSFCell c = r.createCell(cellNum);
		  c.setCellValue(i);
		
		
	
	}
	
//	public void setReportDifine(XSSFWorkbook wb,HashMap reportList){
//		
//		
//		String searchIndex1 = this.returnExcelCellNum(wb, 0, "#001"); //파일 이름 , 시트번호, 비교할 문자열		
//		String searchIndex2 = this.returnExcelCellNum(wb, 0, "#002"); //파일 이름 , 시트번호, 비교할 문자열		
//		String searchIndex3 = this.returnExcelCellNum(wb, 0, "#003"); //파일 이름 , 시트번호, 비교할 문자열		
//		String searchIndex4 = this.returnExcelCellNum(wb, 0, "#004"); //파일 이름 , 시트번호, 비교할 문자열		
//		String searchIndex5 = this.returnExcelCellNum(wb, 0, "#005"); //파일 이름 , 시트번호, 비교할 문자열		
//		String searchIndex6 = this.returnExcelCellNum(wb, 0, "#006"); //파일 이름 , 시트번호, 비교할 문자열		
//		String searchIndex7 = this.returnExcelCellNum(wb, 0, "#007"); //파일 이름 , 시트번호, 비교할 문자열		
//		String searchIndex8 = this.returnExcelCellNum(wb, 0, "#008"); //파일 이름 , 시트번호, 비교할 문자열		
//		String searchIndex9 = this.returnExcelCellNum(wb, 0, "#009"); //파일 이름 , 시트번호, 비교할 문자열		
//		String searchIndex10 = this.returnExcelCellNum(wb, 0, "#010"); //파일 이름 , 시트번호, 비교할 문자열	
//		String searchIndex11 = this.returnExcelCellNum(wb, 0, "#011"); //파일 이름 , 시트번호, 비교할 문자열
//		String rootPath = this.getSearchPath(reportList.get("lcode")+">"+reportList.get("mcode")+">"+reportList.get("scode"));
//		this.setExcelCellvalue(wb,searchIndex1,CommonUtils.getConfig().getProperties("SystemName"));
//		this.setExcelCellvalue(wb,searchIndex2,CommonUtils.getConfig().getProperties("SubjectName")); //업무영역 
//		this.setExcelCellvalue(wb,searchIndex3,String.valueOf(reportList.get("key"))); //문서식별자
//		this.setExcelCellvalue(wb,searchIndex4,(String)  new SimpleDateFormat("yyyy-MM-dd").format(new Date())); //작성일자
//		this.setExcelCellvalue(wb,searchIndex5, CommonUtils.getConfig().getProperties("developer")); //작성자
//		this.setExcelCellvalue(wb,searchIndex6,(String) reportList.get("packageNm")); //패키지명
//		this.setExcelCellvalue(wb,searchIndex7, rootPath); //경로
//		this.setExcelCellvalue(wb,searchIndex8,(String) reportList.get("reportNm")); //레포트명
//		this.setExcelCellvalue(wb,searchIndex9,"DatabaseView"); //모델구분
//		this.setExcelCellvalue(wb,searchIndex10,"Report"); //레포트유형
//		this.setExcelCellvalue(wb,searchIndex11,"1"); //프롬프트유형
//		
//		
//	}
	
	
	
	
	
	 public static String getSTRFilter(String str){ 

		  String str_imsi   = "";  
		
		  List<String> filter_word = Arrays.asList("","\\.","\\?","scode","\\/",">\\~","\\!","\\@","\\#","\\$","\\%","\\^","\\&","\\*","\\(","\\)","\\_","\\+","\\=","\\|","\\\\","\\}","\\]","\\{","\\[","\\\"","\\'","\\:","\\;","\\<","\\,","\\>","\\.","\\?","\\/");
		
		  for(int i=0;i<filter_word.size();i++){ 
		   //while(str.indexOf(filter_word[i]) >= 0){ 
			  str_imsi = str.replaceAll(filter_word.get(i),""); 
		      str = str_imsi; 
		   //} 
		  } 
		  return str; 


		 } 
	 
	 
	 public String getPathFilter(String str){
		// file:/ path가 되게 변경
		
		String result= "";
		result = str.replaceAll("/", "[_]");
		return result;
		}


}
