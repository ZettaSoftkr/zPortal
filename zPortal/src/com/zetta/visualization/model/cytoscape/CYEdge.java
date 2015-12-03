package com.zetta.visualization.model.cytoscape;

public class CYEdge {
	private CYEdgeData data;
	private String classes;
	
	public CYEdge(CYEdgeData data, String classes) {
		super();
		this.data = data;
		this.classes = classes;
	}
	public CYEdgeData getData() {
		return data;
	}
	public void setData(CYEdgeData data) {
		this.data = data;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	
	
	
}
