package com.zetta.visualization.model.mindMap;

public class MText {
	private String caption;
	private MFont font;
	
	
	
	public MText(String caption, MFont font) {
		super();
		this.caption = caption;
		this.font = font;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public MFont getFont() {
		return font;
	}
	public void setFont(MFont font) {
		this.font = font;
	}
	
	
}
