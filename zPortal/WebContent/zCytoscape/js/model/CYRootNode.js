CYRootNode = function() {
	this.nodes = new Array();
	this.edges = new Array();
};

CYRootNode = function(rootId, rootName, depth) {
	this.nodes = new Array();
	this.edges = new Array();	
	var data = new CYData(rootId, rootName, null, 40, '#000000', 'octagon');
	var root = new CYNode(data,  null);
	this.nodes.push(root);
};

CYRootNode.prototype.getNodesWithIdAndDepth = function(menuList,rootId, depth, colorSet){
	var depthMap = new Array();
	depthMap.push("0_" + rootId);
	
	for(var depthIndex = 0; depthIndex <= depth; depthIndex++){
		depthMap = this.getNodesWithId(depthMap, menuList, depthIndex, colorSet);
	}
}


CYRootNode.prototype.getNodesWithId = function(depthMap, menuList, currentDepth, colorSet){
	for (var keyIndex=0 ; keyIndex < depthMap.length ; keyIndex++) {
		var keys = depthMap[keyIndex].split("_");
		var value = keys[1];
		var key = keys[0];
		var colorIndex = 0;
        if(key == currentDepth){
        	for(var i=0 ; i <menuList.length ; i++) {
				var id = menuList[i][0];
				var parent = menuList[i][1];
			    var name = menuList[i][2];
			    
				if(parent == value) {
					if(currentDepth == 0){
						var data = new CYData(id, name, parent,  40, colorSet[colorIndex++], 'ellipse');
						var node = new CYNode(data,  null);
						//node.classes = "rootNode";
						this.nodes.push(node);
					}else{
						var data = new CYData(id, name, parent,  40, this.getFaveColor(this.nodes, parent), 'ellipse');
						var node = new CYNode(data,  null);
						//node.classes = "rootNode";
						this.nodes.push(node);
					}
					
					if(currentDepth >= 1){
						
					}else{
//						var edgeData = new CYEdgeData(parent, id, 2, this.getFaveColor(this.nodes, parent));
//						var edge = new CYEdge(edgeData,  null);
//						this.edges.push(edge);
					}
					
					depthMap.push((currentDepth + 1) + "_" + id);
				}
			}
        }
    }
				
	return depthMap;
}



CYRootNode.prototype.getFaveColor = function(nodes, nodeId) {
	for (var i = 0; i < nodes.length; i++) {
		if (nodes[i].data.id == nodeId) {
			return nodes[i].data.faveColor;
		}
	}
	return "#000123";
};