package com.zetta.visualization.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.visualization.connector.MindMapConnector;
import com.zetta.visualization.model.mindMap.MFont;
import com.zetta.visualization.model.mindMap.MNode;
import com.zetta.visualization.model.mindMap.MOffset;
import com.zetta.visualization.model.mindMap.MText;

@Controller
public class MindMapController {

	public Logger logger = Logger.getLogger(getClass());

//	@Autowired
//	MenuService menuService;

	@RequestMapping(value = "/visualization/mindMap.do", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView mindMapShow(@RequestParam(value = "fileName", required = false, defaultValue = "1") String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {

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

		MindMapConnector conn = new MindMapConnector();
		// conn.setRootNode("{\"id\":\"31b99104-34c4-40fc-a6cb-a79b71bbb76d\",\"title\":\"1\",\"mindmap\":{\"root\":{\"id\":\"d6997563-9f47-446d-8e11-bdd391fcb8e1\",\"parentId\":null,\"text\":{\"caption\":\"1\",\"font\":{\"style\":\"normal\",\"weight\":\"bold\",\"decoration\":\"none\",\"size\":20,\"color\":\"#000000\"}},\"offset\":{\"x\":0,\"y\":0},\"foldChildren\":false,\"branchColor\":\"#000000\",\"children\":[]}},\"dates\":{\"created\":1448256254286,\"modified\":1448256264515},\"dimensions\":{\"x\":4000,\"y\":2000},\"autosave\":false}");
		conn.setRootNode(jsonStr);
		// conn.setRootNode("{\"id\":\"31b99104-34c4-40fc-a6cb-a79b71bbb76d\",\"title\":\"1\",\"autosave\":true}");
		ModelAndView mav = new ModelAndView();
		mav.addObject("json", conn.getJson());
		// mav.addObject("json", jsonStr);
		mav.setViewName("jsonView");
		return mav;

	}

	@RequestMapping(value = "/visualization/mindMap1.do", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView mindMapMenuShow(HttpServletRequest request, HttpServletResponse response) throws Exception {

		MindMapConnector conn = new MindMapConnector();
		//root
		//String id, String prentId, MText text, MOffset offset, String foldChidren, String branchColor, MNode[] children
		MNode  root = new MNode("root",null, new MText("교통안전공단", new MFont("normal", "bold", "none", "20", "#000000")), new MOffset("100", "100"), false, "#000000" , null);
		List<MNode> nullList = new ArrayList<MNode>();
		List<MNode> nodeList = new ArrayList<MNode>();
		int intoffset = 10;

		
//		List<Menu> menuList = menuService.findAllList();
//		List<Menu> menuList = null;
//
//		logger.info("전체 갯수 " + menuList.size());
//		for (int i = 0; i < menuList.size(); i++) {
//
//			HashMap<String, String> map = new HashMap<String, String>();
//
//			map.put("id", menuList.get(i).getBi_portal_menu_id());
//			map.put("parent", menuList.get(i).getBi_portal_menu_parent_id());
//			map.put("text", menuList.get(i).getBi_menu_nm());
//			map.put("bi_menu_url", menuList.get(i).getBi_menu_url_addr());
//			map.put("bi_menu_ordr", String.valueOf(menuList.get(i).getBi_menu_sort_sn()));
//			map.put("bi_menu_stle", menuList.get(i).getBi_menu_fm_yn());
//			//list.add(map);
//			if(menuList.get(i).getBi_portal_menu_parent_id().equals("#")){
//				intoffset = intoffset + 40;
//				MNode child = new MNode(menuList.get(i).getBi_portal_menu_id(), root.getId(), new MText(menuList.get(i).getBi_menu_nm() + ":" + menuList.get(i).getBi_portal_menu_parent_id(), 
//						new MFont("normal", "bold", "none", "20", "#000000")), 
//						new MOffset("100", String.valueOf(10 + intoffset)), false, "#000000",
//						nullList);
//				nodeList.add(child);
//			}
//		}
		
		conn.setRootNode("0", "교통안전공단", root);
		root.setChildren(nodeList);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("json", conn.getJson());
		mav.setViewName("jsonView");
		return mav;
	}
}
