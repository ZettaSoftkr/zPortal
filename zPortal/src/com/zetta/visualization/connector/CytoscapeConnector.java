package com.zetta.visualization.connector;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zetta.visualization.model.cytoscape.CYEdge;
import com.zetta.visualization.model.cytoscape.CYNode;
import com.zetta.visualization.model.cytoscape.CYRootNode;

public class CytoscapeConnector {
	public Logger logger = Logger.getLogger(getClass());
	
	private CYRootNode rootNode;
	private List<CYNode> nodes = new ArrayList<CYNode>();
	private List<CYEdge> edges = new ArrayList<CYEdge>();

	public CytoscapeConnector() {
	};

	public String getJson() {
		String jsonStr = "";

		// rootNode = new RootNode();

		Gson gson = new GsonBuilder().create();
		jsonStr = gson.toJson(rootNode);

		return jsonStr;
	}

	public CYRootNode getRootNode() {
		return rootNode;
	}

	public CYRootNode setRootNode(String jsonStr) {

		try {
			Gson gson = new GsonBuilder().create();
			rootNode = gson.fromJson(jsonStr, CYRootNode.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rootNode;
	}

	public CYRootNode setRootNode() {
		rootNode = new CYRootNode(nodes,edges);
		return rootNode;
	}
	
//	//MenuList에서 Depth 까지의 데이터를 구성한다.
//	public CYRootNode setDepthNodes(List<Menu> menuList, String rootId, String rootName, int depth){
//		// root노드
//		CYNode root = new CYNode(new CYData(rootId, rootName, null), null);
//		nodes.add(root);
//
//		rootNode = getNodesWithIdAndDepth(menuList,rootId, depth);
//		return rootNode;
//	}
	
//	//rootId에 해당하는 노드와 엣지를 만들어 낸다.
//	private CYRootNode getNodesWithIdAndDepth(List<Menu> menuList, String rootId, int depth) {
//		HashMap<String, String> depthMap = new HashMap<String, String>();
//		
//		depthMap.put(String.valueOf(0) + "_" + rootId, rootId);
//		
//		for(int depthIndex = 0; depthIndex <= depth; depthIndex++){
//			depthMap = getNodesWithId(depthMap, menuList, depthIndex);
//		}
//		
//		return this.setRootNode();
//	}
//
//	//rootId에 해당하는 노드와 엣지를 만들어 낸다.
//	private HashMap getNodesWithId(HashMap<String, String> depthMap, List<Menu> menuList, int currentDepth) {
//		String keyStr = depthMap.keySet().toString().replace("[", "").replace("]", "");
//		String[] keys = keyStr.split(",");
//		
//		for (String key : keys) {
//			key = key.trim();
//            if(key.split("_")[0].equals(String.valueOf(currentDepth))){
//            
//            	for (int i = 0; i < menuList.size(); i++) {
//    				HashMap<String, String> map = new HashMap<String, String>();
//    	
//    				String id = menuList.get(i).getBi_portal_menu_id();
//    				String parent = menuList.get(i).getBi_portal_menu_parent_id();
//    				String name = menuList.get(i).getBi_menu_nm();
//    				// map.put("bi_menu_url", menuList.get(i).getBi_menu_url_addr());
//    				// map.put("bi_menu_ordr",
//    				// String.valueOf(menuList.get(i).getBi_menu_sort_sn()));
//    				// map.put("bi_menu_stle", menuList.get(i).getBi_menu_fm_yn());
//    				// list.add(map);
//    				//logger.info("parent:" + parent + " -->>>>  depthMap.get(key) " + depthMap.get(key));
//    				if(parent.equals(depthMap.get(key))) {
//    					
//    					CYNode node = new CYNode(new CYData(id, name, null), null);
//    					node.setClasses("rootNode");
//    					nodes.add(node);
//    					
//    					CYEdge edge = new CYEdge(new CYEdgeData(null, parent, id), null);
//    					edges.add(edge);
//    					
//    					depthMap.put(String.valueOf(currentDepth + 1) + "_" + id, id);
//    					
//    					//logger.info("currentdepth:" + currentDepth + " -->>>> " + depthMap.keySet().toString());
//    				}
//    			}
//            }
//        }
//					
//		return depthMap;
//	}
}
