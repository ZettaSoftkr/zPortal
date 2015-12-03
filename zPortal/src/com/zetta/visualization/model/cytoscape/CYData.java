package com.zetta.visualization.model.cytoscape;

public class CYData {
	String id;
	String name;
	String parent;
	public CYData(String id, String name, String parent) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	
	
}
