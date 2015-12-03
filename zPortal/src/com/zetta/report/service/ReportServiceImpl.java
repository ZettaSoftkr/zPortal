package com.zetta.report.service;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ReportServiceImpl implements ReportService {

	public Logger logger = Logger.getLogger(getClass());

	// 전달 받은 requestID값에 해당하는 HTML을 String으로 전달함.
	@Override
	public String getDefaultParamVal(String[] paramSplit, HashMap<String, String> hmParam) {

		

		String paramVal = "";

		for (int pNum = 0; pNum < paramSplit.length; pNum++){

			paramVal += "&select=Document\\" + paramSplit[pNum] + "," + hmParam.get(paramSplit[pNum]);

		}

		return paramVal;
	}

	
	
	/*
	public String getDefaultMainParamVal(String[] paramSplit, HashMap<String, String> hmParam) {

		

		String paramVal = "";

		for (int pNum = 0; pNum < paramSplit.length; pNum++) {

			paramVal += paramSplit[pNum] + " :  " + hmParam.get(paramSplit[pNum]);

		}

		return paramVal;
	}
	
	*/
	
	
	


}
