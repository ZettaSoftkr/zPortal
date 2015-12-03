package com.zetta.visualization.model.cytoscape;

import java.util.List;

public class CYRootNode {
	private List<CYNode> nodes;
    private List<CYEdge> edges;
	public CYRootNode(List<CYNode> nodes, List<CYEdge> edges) {
		super();
		this.nodes = nodes;
		this.edges = edges;
	}
	public List<CYNode> getNodes() {
		return nodes;
	}
	public void setNodes(List<CYNode> nodes) {
		this.nodes = nodes;
	}
	public List<CYEdge> getEdges() {
		return edges;
	}
	public void setEdges(List<CYEdge> edges) {
		this.edges = edges;
	}    	
    
    
    
}
