package com.zetta.visualization.connector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zetta.visualization.model.mindMap.MNode;
import com.zetta.visualization.model.mindMap.MRootNode;

public class MindMapConnector {

	private MRootNode rootNode;

	public MindMapConnector() {
	};

	public String getJson() {
		String jsonStr = "";

		// rootNode = new RootNode();

		Gson gson = new GsonBuilder().create();
		jsonStr = gson.toJson(rootNode);

		return jsonStr;
	}

	public MRootNode getRootNode() {
		return rootNode;
	}

	public MRootNode setRootNode(String jsonStr) {

		try {
			Gson gson = new GsonBuilder().create();
			rootNode = gson.fromJson(jsonStr, MRootNode.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rootNode;
	}

	public MRootNode setRootNode(String id, String title, MNode root) {
		rootNode = new MRootNode(id, title, root);
		return rootNode;
	}
}
