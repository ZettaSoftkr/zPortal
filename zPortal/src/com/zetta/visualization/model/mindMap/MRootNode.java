package com.zetta.visualization.model.mindMap;

public class MRootNode {
	private String id;
    private String title;
    private MMap mindmap;
    private MDates dates;
    private MDimensions dimensions;
    private boolean autosave;
    
	public MRootNode(String id, String title, MMap mindmap, MDates dates, MDimensions dimensions, boolean autosave) {
		super();
		this.id = id;
		this.title = title;
		this.mindmap = mindmap;
		this.dates = dates;
		this.dimensions = dimensions;
		this.autosave = autosave;
	}
	
	public MRootNode(String id, String title, MNode root) {
		super();
		this.id = id;
		this.title = title;
		this.mindmap = new MMap(root);
		this.dates = new MDates("1448619553191", "1448619686124");
		this.dimensions = new MDimensions(4000,2000);
		this.autosave = false;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public MMap getMindmap() {
		return mindmap;
	}
	public void setMindmap(MMap mindmap) {
		this.mindmap = mindmap;
	}
	public MDates getDates() {
		return dates;
	}
	public void setDates(MDates dates) {
		this.dates = dates;
	}
	public MDimensions getDimensions() {
		return dimensions;
	}
	public void setDimensions(MDimensions dimensions) {
		this.dimensions = dimensions;
	}
	public boolean isAutosave() {
		return autosave;
	}
	public void setAutosave(boolean autosave) {
		this.autosave = autosave;
	}
       
	
}
