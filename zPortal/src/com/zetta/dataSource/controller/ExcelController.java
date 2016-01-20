package com.zetta.dataSource.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zetta.dataSource.model.excel.zSheet;

@Controller
public class ExcelController {

	public Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value = "/dataSource/getExcelSheetNames.do", method = RequestMethod.GET)
	@ResponseBody
	public void getExcelSheetNames(@RequestParam(value = "path", required = true) String path, HttpServletRequest request, HttpServletResponse response) throws Exception {

		File myFile = new File(path);
		Workbook wb = WorkbookFactory.create(myFile);

		List<zSheet> resultSet = new ArrayList();
		
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			zSheet sheet = new zSheet();
			sheet.setSheetId(i);
			sheet.setSheetName(wb.getSheetName(i));
			sheet.setRows(sampleFromExcel(path, i));
			resultSet.add(sheet);
		}
		
		// showExelData(sheetData);

		String jsonStr = "";
		Gson gson = new GsonBuilder().create();
		jsonStr = gson.toJson(resultSet);

		try {
	        response.getWriter().print(jsonStr);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }  
	}

	@SuppressWarnings("unchecked")
	public List sampleFromExcel(String xlsPath, int sheetId) throws IOException  {

        FileInputStream inputStream = new FileInputStream(new File(xlsPath));
         
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(sheetId);
        Iterator<Row> iterator = firstSheet.iterator();
        
        List list = new ArrayList();
        int count = 0;
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            
            List row = new ArrayList();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                 
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                    	row.add(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                    	row.add(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                    	row.add(cell.getNumericCellValue());
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                    	row.add(cell.getCachedFormulaResultType());
                        break;
                }
            }
            count++;
            if(count > 21) break;
            list.add(row);
        }
         
        inputStream.close();
        return list;
	}

}
