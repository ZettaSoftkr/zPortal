package com.zetta.visualization.model.mindMap;

public class MFont {
	private String style;
	private String weight;
	private String decoration;
	private String size;
	private String color;
	

	
	public MFont(String style, String weight, String decoration, String size, String color) {
		super();
		this.style = style;
		this.weight = weight;
		this.decoration = decoration;
		this.size = size;
		this.color = color;
	}
	
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getDecoration() {
		return decoration;
	}
	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}
