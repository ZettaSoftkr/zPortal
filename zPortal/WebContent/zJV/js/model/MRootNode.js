MRootNode = function() {
	this.id = "0";
	this.title = "시작";
	this.mindmap = new Array();
	this.dates = new Array();
	this.dimensions = new Array();
	this.autosave = "false";
	
	var font = new MFont("normal", "bold", "none", 20, "#000000")
	var text = new MText("시작123", font);
	var offset = new MOffset(0,0);
	var foldChidren = false;
	var branchColor = "#000000";
	var root = new MNode("1", null, text, offset, foldChidren, branchColor, new Array());
	
	this.mindmap.push(root);
	
	var date = new MDate(new Date(),new Date());
	var dimension = new MDimension(4000,2000);
	
	this.dates.push(date);
	this.dimensions.push(dimension);
};

MRootNode.prototype.getNodesWithIdAndDepth = function(menuList,rootId, depth){
	var depthMap = new Array();
	depthMap.push("0_" + rootId);
	
	for(var depthIndex = 0; depthIndex <= depth; depthIndex++){
		depthMap = this.getNodesWithId(depthMap, menuList, depthIndex);
	}
}


MRootNode.prototype.getNodesWithId = function(depthMap, menuList, currentDepth){
	for (var keyIndex=0 ; keyIndex < depthMap.length ; keyIndex++) {
		var keys = depthMap[keyIndex].split("_");
		var value = keys[1];
		var key = keys[0];
		var colorIndex = 0;
        if(key == currentDepth){
        	for(var i=0 ; i <menuList.length ; i++) {
				var id = menuList[i][0];
				var prentId = menuList[i][1];
			    var name = menuList[i][2];
			    
				if(prentId == value) {
					
					var font = new MFont("normal", "bold", "none", 20, "#000000")
					var text = new MText(name, font);
					var offset = new MOffset(0,colorIndex++ + 20);
					var foldChidren = false;
					var branchColor = "#000000";
					
					var node = new MNode(id, prentId, text, offset, foldChidren, branchColor, new Array());
					this.mindmap.elements[0].children.push(node);
					
					depthMap.push((currentDepth + 1) + "_" + id);
				}
			}
        }
    }
				
	return depthMap;
}
