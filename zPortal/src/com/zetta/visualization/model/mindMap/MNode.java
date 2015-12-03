package com.zetta.visualization.model.mindMap;

import java.util.List;

public class MNode {
	private String id;
	private String prentId;
	private MText text;
	private MOffset offset;
	private boolean foldChidren;
	private String branchColor;
	private List<MNode> children;
	public MNode(String id, String prentId, MText text, MOffset offset, boolean foldChidren, String branchColor, List<MNode> children) {
		super();
		this.id = id;
		this.prentId = prentId;
		this.text = text;
		this.offset = offset;
		this.foldChidren = foldChidren;
		this.branchColor = branchColor;
		this.children = children;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrentId() {
		return prentId;
	}
	public void setPrentId(String prentId) {
		this.prentId = prentId;
	}
	public MText getText() {
		return text;
	}
	public void setText(MText text) {
		this.text = text;
	}
	public MOffset getOffset() {
		return offset;
	}
	public void setOffset(MOffset offset) {
		this.offset = offset;
	}
	public boolean isFoldChidren() {
		return foldChidren;
	}
	public void setFoldChidren(boolean foldChidren) {
		this.foldChidren = foldChidren;
	}
	public String getBranchColor() {
		return branchColor;
	}
	public void setBranchColor(String branchColor) {
		this.branchColor = branchColor;
	}
	public List<MNode> getChildren() {
		return children;
	}
	public void setChildren(List<MNode> children) {
		this.children = children;
	}
	
	
	
	
}
