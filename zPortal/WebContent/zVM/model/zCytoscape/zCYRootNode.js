zCYRootNode = function() {
	this.nodes = new Array();
	this.edges = new Array();
};

zCYRootNode = function(rootId, rootName, depth) {
	this.nodes = new Array();
	this.edges = new Array();
	var data = new zCYData(rootId, rootName, null, 40, '#000000', 'octagon', null);
	var root = new zCYNode(data,  null);
	this.nodes.push(root);
};

zCYRootNode.prototype.getNodesWithIdAndDepth = function(menuList,rootId, depth, colorSet){
	var depthMap = new Array();
	depthMap.push("0_" + rootId);
	
	for(var depthIndex = 0; depthIndex <= depth; depthIndex++){
		depthMap = this.getNodesWithId(depthMap, menuList, depthIndex, colorSet);
	}
}


zCYRootNode.prototype.getNodesWithId = function(depthMap, menuList, currentDepth, colorSet){
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
			    var nodeTypes = menuList[i][3];
			    var nodeType = nodeTypes.split(',')[0];
			    var desc = menuList[i][4];
			    
				if(parent == value) {
					if(nodeType!='R'){
						if(currentDepth == 0){
							var data = new zCYData(id, name, null,  40, colorSet[colorIndex++], 'ellipse', nodeTypes);
							var node = new zCYNode(data,  null);
							//node.classes = "rootNode";
							this.nodes.push(node);
						}else{
							var data = new zCYData(id, name, null,  20, this.getFaveColor(this.nodes, parent), 'ellipse', nodeTypes);
							var node = new zCYNode(data,  null);
							//node.classes = "rootNode";
							this.nodes.push(node);
						}
					}
					
					if(nodeType!='N'){
						var edgeData = new zCYEdgeData(parent, id, desc, 10,  'black', "from");
						var edge = new zCYEdge(edgeData, 'questionable');
						this.edges.push(edge);
					}else{
						var edgeData = new zCYEdgeData(parent, id, desc, 1,  this.getFaveColor(this.nodes, parent), null);
						var edge = new zCYEdge(edgeData, null);
						this.edges.push(edge);
					}
					
					
					depthMap.push((currentDepth + 1) + "_" + id);
				}
			}
        }
    }
				
	return depthMap;
}



zCYRootNode.prototype.getFaveColor = function(nodes, nodeId) {
	for (var i = 0; i < nodes.length; i++) {
		if (nodes[i].data.id == nodeId) {
			return nodes[i].data.faveColor;
		}
	}
	return "#000123";
};