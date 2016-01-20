package com.zetta.visualization.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zetta.dataSource.model.dataSource.zODBCList;
import com.zetta.dataSource.model.diagram.zDMain;
import com.zetta.dataSource.model.diagram.zDataSource;
import com.zetta.dataSource.model.diagram.zNodeDataArray;
import com.zetta.visualization.model.report.MReportInfo;
import com.zetta.visualization.model.report.ZObjectInfo;
import com.zetta.visualization.model.report.ZObjectTypeInfo;
import com.zetta.visualization.model.report.ZObjectTypeInfos;
import com.zetta.visualization.util.DataUtils;

@Controller
public class ModelManagerController {

	public Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value = "/modelManager/save.do", method = RequestMethod.POST)
	@ResponseBody
	public void save(
			@RequestParam(value = "fileName", required = true) String fileName, 
			@RequestParam(value = "objectKey", required = true) String objectKey, 
			@RequestParam(value = "content", required = true) String content, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		logger.info("/modelManager/save.do start");
		boolean result = true;
		
		String folderName = "C:\\zWorking\\" + fileName ;
		String path = "C:\\zWorking\\" + fileName + "\\" + objectKey +".json";
		result = new DataUtils().saveTextFile(folderName, path, content);

	    try {
	        response.getWriter().print(result);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }  
	}
	
	@RequestMapping(value = "/modelManager/load.do", method = RequestMethod.POST)
	@ResponseBody
	public void load(
			@RequestParam(value = "fileName", required = true) String fileName, 
			@RequestParam(value = "objectKey", required = true) String objectKey, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		logger.info("/modelManager/load.do start");
		
		String result = new DataUtils().getJsonFile(fileName,objectKey);

		try {
	        response.getWriter().print(result);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }  
	}
		
	@RequestMapping(value = "/modelManager/reportInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public void reportInfo(
			@RequestParam(value = "fileName", required = true) String fileName,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		logger.info("/modelManager/reportInfo.do start");
		
		String result = new DataUtils().getJsonFile(fileName,"main");

		zDMain dMain = new zDMain();
		try {
	
			Gson gson = new GsonBuilder().create();
			//보고서 main.json을 가져온다.
			logger.info("parsing zDMain.class");
			dMain = gson.fromJson(result, zDMain.class);
			List<zNodeDataArray> nodeDatas = new ArrayList<zNodeDataArray>();
			
			result = new DataUtils().getJsonFile("system","VObjectInfo");
			logger.info("parsing ZObjectTypeInfos.class");
			ZObjectTypeInfos oInfos = gson.fromJson(result, ZObjectTypeInfos.class);
			
			MReportInfo rInfo = new MReportInfo();
			rInfo.setReportNm(fileName);
			
			List<ZObjectInfo> rOInfos = new ArrayList<ZObjectInfo>();
			for (zNodeDataArray ary : dMain.getNodeDataArray()){
				if(ary.getCategory().equals("visualization")){
					for(ZObjectTypeInfo info :  oInfos.getObjectTypeInfos()){
						if(ary.getChartType().equals(info.getChartType())){
							ZObjectInfo objectInfo = new ZObjectInfo();
							objectInfo.setChartType(ary.getChartType());
							objectInfo.setGroup(ary.getGroup());
							objectInfo.setObjectKey(String.valueOf(ary.getKey()));
							objectInfo.setSize(ary.getSize());
							objectInfo.setUrl(info.getUrl());
							
							rOInfos.add(objectInfo);						
						}
					}
				}
			}
			
			rInfo.setObjectInfo(rOInfos);		
			result = gson.toJson(rInfo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
	        response.getWriter().print(result);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }  
	}
	
	@RequestMapping(value = "/modelManager/getTableData.do", method = RequestMethod.POST)
	@ResponseBody
	public void getTableData(
			@RequestParam(value = "fileName", required = true) String fileName, 
			@RequestParam(value = "objectKey", required = true) String objectKey, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		logger.info("/modelManager/getTableData.do start");
		
		String result = "";
		
		//main.json 열기
		result = new DataUtils().getJsonFile(fileName,"main");
		
		zDMain dMain = new zDMain();
		try {
			Gson gson = new GsonBuilder().create();
			logger.info("parsing zDMain.class");
			dMain = gson.fromJson(result, zDMain.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//데이터 오브젝트를 찾는다.
		String dataObjectKey = new DataUtils().getDataObject(dMain,objectKey);
		String sourceKey = null;
		zDMain dataObject = new zDMain();
		//dataObject
		if(dataObjectKey!=null){
			result = new DataUtils().getJsonFile(fileName, dataObjectKey);
			Gson gson = new GsonBuilder().create();
			logger.info("parsing zDMain.class");
			dataObject = gson.fromJson(result, zDMain.class);
			
			sourceKey = new DataUtils().getDataObject(dMain,dataObjectKey);
		}		
		
		//dataSource
		if(sourceKey!=null){
			zDataSource dataSource = new zDataSource();
			result = new DataUtils().getJsonFile(fileName, sourceKey);
			
			//dataObject
			Gson gson = new GsonBuilder().create();
			logger.info("parsing zDataSource.class");
			dataSource = gson.fromJson(result, zDataSource.class);
			
			if(dataSource.getSourceType().equals("FILE")){
				List list = new DataUtils().getFromExcel("C:\\zWorking\\upload\\" + dataSource.getConnName(), dataObject.getLinkDataArray().get(0).getFrom());
				gson = new GsonBuilder().create();
				result = gson.toJson(list);
			}
			else if(dataSource.getSourceType().equals("ODBC")){			
				//1. odbc정보 얻기
				zODBCList list = new DataUtils().getODBCInfo();
				String script = new DataUtils().makeScriptDBLoad(fileName, dataObjectKey, list.getOdbcs().get(0).getConnStr(), list.getOdbcs().get(0).getSchemaName(), dataObject);
				
				//2. qvx를 fileName폴더의 odbjectKey.qvx로 떨어뜨림.
				//qv.exe call 전달인자 넘겨야 함. 여러명이 하면 세션에 문제가 있을수 있다.
				if(new DataUtils().makeQvx(fileName, dataObjectKey, script)){
					result = new DataUtils().getFromQVX("C:\\zWorking\\" + fileName + "\\" + dataObjectKey + ".qvx");
				}
				else{
					result = "makeQvx error!!";
					logger.info(result); 
				}
			}
			else{
				result = "잘못된 소스타입입니다. 소스타입:" + dataSource.getSourceType();
				logger.info(result); 
			}
		}else
		{
			result = "데이터 소스를 찾을 수 없습니다.";
			logger.info(result); 
		}
		
		try {
	        response.getWriter().print(result);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }  
	}

	
	
	@RequestMapping(value = "/modelManager/getDbTableInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public void getDbTableInfo(
			@RequestParam(value = "fileName", required = true) String fileName, 
			@RequestParam(value = "objectKey", required = true) String objectKey, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		logger.info("/modelManager/getDbTableInfo.do start"); 
		
		//main.json 열기
		String result = "";
		result = new DataUtils().getJsonFile(fileName,"main");
		
		zDMain dMain = new zDMain();
		try {
			Gson gson = new GsonBuilder().create();
			logger.info("parsing zDMain.class");
			dMain = gson.fromJson(result, zDMain.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "main.json파일 없음.";

			try {
		        response.getWriter().print(result);
		    } catch (IOException e1) {
		        e1.printStackTrace();
		    }  
		}
		
		//데이터 오브젝트를 찾는다.
		String parentKey = new DataUtils().getDataObject(dMain,objectKey);
		logger.info("parentKey::" + parentKey); 
		//dataSource
		zDataSource dataSource = new zDataSource();
		if(parentKey!=null){
			result = new DataUtils().getJsonFile(fileName,parentKey);
			
			//dataObject
			Gson gson = new GsonBuilder().create();
			logger.info("parsing zDataSource.class");
			dataSource = gson.fromJson(result, zDataSource.class);
		}else{
			result = "데이터 소스를 찾을 수 없습니다.";
			try {
		        response.getWriter().print(result);
		    } catch (IOException e1) {
		        e1.printStackTrace();
		    }  
		}
		
		logger.info("dataSource.getSourceType()::" + dataSource.getSourceType());
		if(dataSource.getSourceType().equals("ODBC")){
			File file = new File("C:\\zWorking\\dbInfo\\" + dataSource.getConnName()  + "_" + dataSource.getSubName() + "_TableInfo.qvx");
			logger.info("File::" + "C:\\zWorking\\dbInfo\\" + dataSource.getConnName()  + "_" + dataSource.getSubName() + "_TableInfo.qvx");
			if (file.exists()) {
				result = new DataUtils().getFromQVXDataOnly(dataSource.getConnName()  + "_" + dataSource.getSubName() + "_TableInfo");
			}else
			{
				result = dataSource.getConnName()  + "_" + dataSource.getSubName() + " 파일을 찾을수 없습니다.";
				try {
			        response.getWriter().print(result);
			    } catch (IOException e1) {
			        e1.printStackTrace();
			    }  
			}
		}else if(dataSource.getSourceType().equals("FILE")){
			
			File myFile = new File("C:\\zWorking\\upload\\" + dataSource.getConnName());
			FileInputStream file = new FileInputStream(myFile);
			logger.info("File::" + "C:\\zWorking\\upload\\" + dataSource.getConnName());
			Workbook wb = WorkbookFactory.create(file);
			
			List resultSet = new ArrayList();
			
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				
				List row = new DataUtils().getColumnInfo(wb,i);
				for(int j = 0; j < row.size() ; j++){
					List table = new ArrayList();
	
					table.add(dataSource.getConnName());
					table.add("");				
					table.add(wb.getSheetName(i));
					table.add((String)row.get(j));
					table.add("String");
					table.add("");
					table.add("");
					table.add("");
					table.add("YES");
					
					resultSet.add(table);
				}
			}

			file.close();
			
			Gson gson = new GsonBuilder().create();
			result = gson.toJson(resultSet);
		}else{
			result = dataSource.getConnName() + "의 데이터 커넥션을 찾을 수 없습니다.";
			logger.info(result); 
		}	
		
		try {
	        response.getWriter().print(result);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }  
	}
	
	@RequestMapping(value = "/modelManager/getODBCList.do", method = RequestMethod.POST)
	@ResponseBody
	public void getODBCList(
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		logger.info("/modelManager/getODBCList.do start"); 
		
		String result = "";
		zODBCList list = new DataUtils().getODBCInfo();
		Gson gson = new GsonBuilder().create();
		result = gson.toJson(list);
		logger.info("result: " + result);	

		try {
	        response.getWriter().print(result);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }  
	}
	
	@RequestMapping(value = "/modelManager/getDbSources.do", method = RequestMethod.POST)
	@ResponseBody
	public void getDbSources(
			@RequestParam(value = "odbcName", required = true) String odbcName, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		logger.info("/modelManager/getDbSources.do start"); 
		
		String result = new DataUtils().getFromQVXDataOnly(odbcName + "_DBList");

		try {
	        response.getWriter().print(result);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }  
	}
}
