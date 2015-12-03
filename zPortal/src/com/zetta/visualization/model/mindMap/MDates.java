package com.zetta.visualization.model.mindMap;

public class MDates {
	private String created;
	private String modified;
	
	
	
	public MDates(String created, String modified) {
		super();
		this.created = created;
		this.modified = modified;
	}
	
	
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	
	
}
