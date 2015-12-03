package com.zetta.etlJob.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.omg.CORBA.portable.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EtlJobController {

	public Logger logger = Logger.getLogger(getClass());
	
	
	
	@Value("#{qvconf['etlIp']}")
	private String etlIp;
	
	
	@RequestMapping(value = "/etlJob/callJob.do", method = RequestMethod.GET)
	public ModelAndView startDemons(
			  @RequestParam(value = "jobNm", required = false, defaultValue = "") String jobNm
			, @RequestParam(value = "methodNm", required = false, defaultValue = "") String methodNm
			, @RequestParam(value = "strDate", required = false, defaultValue = "") String strDate
			, @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
                //OS 종류 확인

		String osName = System.getProperty("os.name");
		StringBuilder sb = new StringBuilder();
		
		if(methodNm.equals("start")){
			
		sb.append("java -jar C:\\etllib\\ShellExecClient-1.0.jar "+etlIp+" 60011 \"/data/DWDB/shell/dsshell.sh -p TS -j "+jobNm+"  -m "+methodNm+" \"$ETL_STR_DT="+strDate+",$ETL_END_DT="+endDate+"\"\"");
		}else{
			
			sb.append("java -jar C:\\etllib\\ShellExecClient-1.0.jar "+etlIp+" 60011 \"/data/DWDB/shell/dsshell.sh -p TS -j "+jobNm+"  -m "+methodNm+"\"");	
			
		}
		System.out.println(" - StringBuilder length: " + sb.length());
		logger.info(" - Command: " + sb.toString());

		

		Process process = null;
		BufferedReader br = null;

		try {

			String[] cmd = null;
			if (osName.toLowerCase().startsWith("window")) {
                                // OS 가  Windows 일때 명령어 라인 생성

				cmd = new String[]{"cmd.exe", "/y", "/c", sb.toString()};

			} else {

				cmd = new String[]{"/bin/sh", "-c", sb.toString()};

			}

                        // 콘솔 명령 실행

			process = Runtime.getRuntime().exec(cmd);
                        // 실행 결과 확인 (에러) 

			br = new BufferedReader(new InputStreamReader(process.getErrorStream(),"EUC-KR"));
			

			logger.info("\n ## ERROR : ");

			String line = null;

			while ((line = br.readLine())!= null) {

				logger.info(line);

			}

			

			br = null;  // 실행 결과 확인
			br = new BufferedReader(new InputStreamReader(process.getInputStream()));

			logger.info("\n ## RESULT: ");

			line = null;

			while ((line = br.readLine())!= null) {
				logger.info(line);
			}    // 프로세스의 수행이 끝날때까지 대기

             process.waitFor();

		} catch (IOException e) {
			logger.info("IOException");
			
		}finally{
			
			br  = null;
			
		

			
		}

		sb.delete(0, sb.length());
		sb.setLength(0);
		sb = null;

		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "실행완료.");
		mav.setViewName("jsonView");
		return mav;

	}
	
	
	
	

}
