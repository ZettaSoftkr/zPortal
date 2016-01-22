package com.zetta.visualization.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zetta.dataSource.model.dataSource.zODBCList;
import com.zetta.dataSource.model.diagram.zDMain;
import com.zetta.dataSource.model.diagram.zLinkDataArray;

import de.tiq.solutions.data.conversion.QVXReader;

public class DataUtils {

	public Logger logger = Logger.getLogger(getClass());

	public String getFromQVXDataOnly(String qvxPath) throws IOException {

		logger.info("qvxPath: " + qvxPath);
		String result = "";
		List record = new ArrayList();
		QVXReader qvxReader = null;
		try {

			qvxReader = new QVXReader("C:\\zWorking\\dbInfo\\" + qvxPath + ".qvx");
			while (qvxReader.hasRecord()) {
				record.add(qvxReader.readRecord());
			}

		} catch (Exception ex) {
			System.out.print("Exception");
		} finally {
			qvxReader.close();
		}

		Gson gson = new GsonBuilder().create();
		result = gson.toJson(record);
		logger.info("result: " + result);
		return result;
	}

	public String getFromQVX(String qvxPath) throws IOException {

		logger.info("qvxPath: " + qvxPath);
		String result = "";
		List record = new ArrayList();
		QVXReader qvxReader = null;
		try {

			qvxReader = new QVXReader(qvxPath);
			record.add(qvxReader.getHeaderFieldNames());
			while (qvxReader.hasRecord()) {
				record.add(qvxReader.readRecord());
			}

		} catch (Exception ex) {
			System.out.print("Exception");
		} finally {
			qvxReader.close();
		}

		Gson gson = new GsonBuilder().create();
		result = gson.toJson(record);
		logger.info("result: " + result);
		return result;
	}

	public zODBCList getODBCInfo() throws IOException {
		zODBCList list = new zODBCList();

		String result = "";
		File file = new File("C:\\zWorking\\odbc.json");
		if (file.exists()) {
			BufferedReader br = null;
			InputStreamReader isr = null;
			FileInputStream fis = null;

			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, "UTF-8");
			br = new BufferedReader(isr);
			String temp = "";
			while ((temp = br.readLine()) != null) {
				result += temp;
			}
			fis.close();

		} else {
			result = "odbc.json 파일을 찾을수 없습니다.";
			logger.info(result);
		}

		Gson gson = new GsonBuilder().create();
		list = gson.fromJson(result, zODBCList.class);

		return list;
	}

	// QlikView스크립트를 만든다.
	public String makeScriptDBLoad(String fileName, String objectKey, String connStr, String subName, zDMain dataObject) {
		String result = "";
		result = connStr;
		dataObject.getLinkDataArray().get(0).getFrom();

		String loadTable = "\n" + "\n" + "tableInfo:\n" + "load *;\n" + "sql select * from " + subName + "." + dataObject.getLinkDataArray().get(0).getFrom() + ";\n";

		result += loadTable;

		return result;
	}

	public boolean makeQvx(String fileName, String objectKey, String script) {
		// 1. 스크립트 파일 저장
		String folderName = "C:\\zWorking\\" + fileName;
		String path = "C:\\zWorking\\" + fileName + "\\" + objectKey + ".script";
		if (saveTextFile(folderName, path, script)) {
			// 2. qv.exe를 실행해야한다.
			// "C:\Program Files\QlikView\Qv.exe" /r
			// "D:\QVD\QVDLOAD\초기적재\초기적재7_QVD.QVW" /vvPath="간단한 보고서\-13"
			try {
				String cmd = "\"C:\\Program Files\\QlikView\\Qv.exe\" /r \"C:\\zWorking\\RuleEngine.qvw\" /vvPath=\"" + fileName.replace(" ", "%20") + "_" + objectKey + "\"";
				logger.info("cmd:" + cmd);

				Process p = Runtime.getRuntime().exec(cmd);
				int exitVal = p.waitFor();

			} catch (InterruptedException ex) {
				logger.info("error: " + ex);
			} catch (IOException ex) {
				logger.info("error: " + ex);
			}

		} else {
			logger.info("스크립트 파일을 저장하지 못했습니다. 경로:" + path);
			return false;
		}

		return true;
	}

	public boolean saveTextFile(String folderName, String path, String content) {
		
		File file = new File(folderName);
		if (!file.exists()) {
			if (file.mkdir()) {
				logger.info("Directory is created!");
			} else {
				logger.info("Failed to create directory!");
			}
		}

		try {
		
			File targetFile = new File(path);
			targetFile.createNewFile();

			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile.getPath()), "UTF8"));

			output.write(content);
			output.close();
		} catch(UnsupportedEncodingException uee) {
			uee.printStackTrace();
			return false;
		} catch(IOException ioe) {
			ioe.printStackTrace();
			return false;
		}

		return true;
	}

	// 첫번째 행을 읽어서 보낸다.
	public List getColumnInfo(Workbook workbook, int sheetId) throws IOException {

		logger.info("function getColumnInfo start");

		Sheet firstSheet = workbook.getSheetAt(sheetId);
		Iterator<Row> iterator = firstSheet.iterator();

		// List list = new ArrayList();
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
			if (count > 0)
				return row;
			// list.add(row);
		}
		return null;
		// return list;
	}

	@SuppressWarnings("unchecked")
	public List sampleFromExcel(String xlsPath, String sheetName) throws IOException, InvalidFormatException {
		logger.info("function sampleFromExcel start");

		File myFile = new File(xlsPath);
		Workbook workbook = null;

		FileInputStream inputStream = null;

		if (xlsPath.substring(xlsPath.lastIndexOf(".") + 1).toLowerCase().equals("xls")) {
			workbook = WorkbookFactory.create(myFile);
		} else if (xlsPath.substring(xlsPath.lastIndexOf(".") + 1).toLowerCase().equals("xlsx")) {
			inputStream = new FileInputStream(myFile);
			workbook = new XSSFWorkbook(inputStream);
		} else {
			return null;
		}

		Sheet firstSheet = workbook.getSheet(sheetName);
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
			if (count > 21)
				break;
			list.add(row);
		}

		if (inputStream != null)
			inputStream.close();
		// inputStream.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List getFromExcel(String xlsPath, String sheetName) throws IOException, InvalidFormatException {
		logger.info("function getFromExcel start");

		File myFile = new File(xlsPath);
		Workbook workbook = null;

		FileInputStream inputStream = null;

		if (xlsPath.substring(xlsPath.lastIndexOf(".") + 1).toLowerCase().equals("xls")) {
			workbook = WorkbookFactory.create(myFile);
		} else if (xlsPath.substring(xlsPath.lastIndexOf(".") + 1).toLowerCase().equals("xlsx")) {
			inputStream = new FileInputStream(myFile);
			workbook = new XSSFWorkbook(inputStream);
		} else {
			return null;
		}

		Sheet firstSheet = workbook.getSheet(sheetName);
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
			if (count > 10000)
				break;
			list.add(row);
		}
		if (inputStream != null)
			inputStream.close();
		// inputStream.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List sampleFromExcel(String xlsPath, int sheetId) throws IOException {
		logger.info("function sampleFromExcel start");

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
			if (count > 21)
				break;
			list.add(row);
		}

		inputStream.close();
		return list;
	}

	public String getJsonFile(String folder, String fileName) throws FileNotFoundException, UnsupportedEncodingException, IOException {

		logger.info("function getJsonFile start");
		logger.info("C:\\zWorking\\" + folder + "\\" + fileName + ".json");
		String result = "";
		File file = new File("C:\\zWorking\\" + folder + "\\" + fileName + ".json");
		if (file.exists()) {
			BufferedReader br = null;
			InputStreamReader isr = null;
			FileInputStream fis = null;
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, "UTF-8");
			br = new BufferedReader(isr);
			String temp = "";
			while ((temp = br.readLine()) != null) {
				result += temp;
			}
			//result = result.replace("\"class\"", "\"classes\"");

			isr.close();
		} else {
			result = "[\"notFound\"]";
			logger.info(result);
		}
		return result;
	}

	public String getDataObject(zDMain dMain, String objectKey) {
		logger.info("function getDataObject start");

		List<zLinkDataArray> List = dMain.getLinkDataArray();
		for (zLinkDataArray row : List) {
			if (row.getTo().equals(objectKey))
				return row.getFrom();
		}
		return null;
	}
	
    public static List sortByValue(final Map map){
        List<String> list = new ArrayList();
        list.addAll(map.keySet());
         
        Collections.sort(list,new Comparator(){
             
            public int compare(Object o1,Object o2){
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                 
                return ((Comparable) v1).compareTo(v2);
            }
             
        });
        Collections.reverse(list); // 주석시 오름차순
        return list;
    }
}
