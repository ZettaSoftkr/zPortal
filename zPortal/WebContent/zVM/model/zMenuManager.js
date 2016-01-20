zMenuManager = function(){
	this.fullDiagram = null; 
	this.modifyModel = { node: new Array(), types : new Array()};
	this.currentNode = null; 
}

zMenuManager = function(fullDiagram, currentNode) {
	this.fullDiagram = fullDiagram; 
	this.modifyModel = { node: new Array(), types : new Array()};
	this.currentNode = currentNode; 
}

zMenuManager.prototype.addModifyModel = function(editNode, modify){
	this.modifyModel.node.push(editNode);
	this.modifyModel.types.push(modify);
}
