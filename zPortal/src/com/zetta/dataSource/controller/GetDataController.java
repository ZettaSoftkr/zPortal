package com.zetta.dataSource.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.visualization.util.DataUtils;

@Controller
public class GetDataController {

	public Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value = "/googlemap/getMap.do", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getGoogleMap(@RequestParam(value = "addr", required = true) String addr, HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("시작: googlemap.do ");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("zPortal/googlemap");
		mav.addObject("addr", addr);
		return mav;
	}

	@RequestMapping(value = "/loginSuccess.do")
	@ResponseBody
	public ModelAndView loginSuccess(@RequestParam(value = "userId", required = true) String userId, HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("/loginSuccess.do start");

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		session = request.getSession(true);
		session.setAttribute("userId", userId);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("zPortal/setConfig");
		mav.addObject("userId", userId);
		return mav;
	}

	@RequestMapping(value = "/getUserInfo.do")
	@ResponseBody
	public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("/getUserInfo.do start");

		HttpSession session = request.getSession(false);
		String userId = (String)session.getAttribute("userId");

		logger.info("/modelManager/load.do start");

		String result = new DataUtils().getJsonFile("users", userId);

		try {
			response.getWriter().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/exportFile.do")
	@ResponseBody
	public void exportFile(@RequestParam(value = "fileType", required = true) String fileType, @RequestParam(value = "fileName", required = true) String fileName, @RequestParam(value = "content", required = true) String content,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("/exportFile.do start");

		response.setContentType(fileType);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "utf-8") + "\";");
		response.setHeader("Content-Transfer-Encoding", "BASE64");

		OutputStream out = response.getOutputStream();
		try {

			out.write(content.getBytes());

		} catch (IOException e) {

			System.out.print("IOException");

		} catch (Exception e) {

			System.out.print("Exceptoin");

		} finally {

			out.flush();

		} // try end;
	}

}
