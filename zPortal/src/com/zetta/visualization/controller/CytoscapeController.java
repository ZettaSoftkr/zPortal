package com.zetta.visualization.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.visualization.connector.CytoscapeConnector;

@Controller
public class CytoscapeController {

	public Logger logger = Logger.getLogger(getClass());

//	@Autowired
//	MenuService menuService;

//	@RequestMapping(value = "/visualization/cytoscape.do", method = RequestMethod.GET)
//	@ResponseBody
//	public ModelAndView mindMapMenuShow(
//			@RequestParam(value = "rootId", required = true) String rootId,
//			@RequestParam(value = "rootName", required = true) String rootName,
//			@RequestParam(value = "depth", required = true) String depth,
//			HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//		CytoscapeConnector conn = new CytoscapeConnector();
//
////		List<Menu> menuList = menuService.findAllList();
//		List<Menu> menuList = null;
//
//		conn.setDepthNodes(menuList, rootId, rootName, Integer.valueOf(depth));
//
//		ModelAndView mav = new ModelAndView();
//		logger.info(conn.getJson());
//		mav.addObject("json", conn.getJson());
//		mav.setViewName("jsonView");
//		return mav;
//	}
	
	@RequestMapping(value = "/visualization/cytoscape1.do", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView mindMapShow(@RequestParam(value = "fileName", required = false, defaultValue = "cytoscape1") String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// json파일 로딩
		File fileDirs = new File("C:/Qlikview_Working/mapJsonFile/" + fileName + ".json");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDirs), "UTF-8"));
		String jsonStr = "";
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			jsonStr = sb.toString();
		} finally {
			br.close();
		}

		CytoscapeConnector conn = new CytoscapeConnector();
		conn.setRootNode(jsonStr);
		ModelAndView mav = new ModelAndView();
		mav.addObject("json", conn.getJson());
		mav.setViewName("jsonView");
		return mav;

	}
	
	
//	@RequestMapping(value = "/visualization/getStringList.do", method = RequestMethod.GET)
//	@ResponseBody
//	public ModelAndView getStringList(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
////		List<Menu> menuList = menuService.findAllList();
//		List<Menu> menuList = null;
//		String resultStr = "{";
//		for (int i = 0; i < menuList.size(); i++) {
//			HashMap<String, String> map = new HashMap<String, String>();
//
//			String id = menuList.get(i).getBi_portal_menu_id();
//			String parent = menuList.get(i).getBi_portal_menu_parent_id();
//			String name = menuList.get(i).getBi_menu_nm();
//			
//			resultStr = resultStr + "{\"" + id + "\","  + "\"" + parent + "\"," + "\"" + name + "\"},";
//		}
//
//	
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("json", resultStr);
//		mav.setViewName("jsonView");
//		return mav;
//	}
	
	
}
