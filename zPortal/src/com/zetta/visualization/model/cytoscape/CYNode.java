package com.zetta.visualization.model.cytoscape;

public class CYNode {
	private CYData data;
	private CYPosition position;
	private String group;
	private boolean selected;
	private boolean selectable;
	private boolean locked;
	private boolean grabbable;
	private String classes;
	
	
	public CYNode(CYData data, CYPosition position) {
		super();
		this.data = data;
		this.position = position;
	}
	
	
	public String getGroup() {
		return group;
	}


	public void setGroup(String group) {
		this.group = group;
	}


	public boolean isSelected() {
		return selected;
	}


	public void setSelected(boolean selected) {
		this.selected = selected;
	}


	public boolean isSelectable() {
		return selectable;
	}


	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}


	public boolean isLocked() {
		return locked;
	}


	public void setLocked(boolean locked) {
		this.locked = locked;
	}


	public boolean isGrabbable() {
		return grabbable;
	}


	public void setGrabbable(boolean grabbable) {
		this.grabbable = grabbable;
	}


	public String getClasses() {
		return classes;
	}


	public void setClasses(String classes) {
		this.classes = classes;
	}


	public CYData getData() {
		return data;
	}
	public void setData(CYData data) {
		this.data = data;
	}
	public CYPosition getPosition() {
		return position;
	}
	public void setPosition(CYPosition position) {
		this.position = position;
	}

}
