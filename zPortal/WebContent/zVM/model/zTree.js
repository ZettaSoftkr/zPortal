zTree = function (id) {
    this.id = id ? id.toString() : '_root' ;
    //this.type = null;
    this.data = null;
    this.children = [];
    this.parent = null;

    this.setParent = function(id) {
        this.parent = id;
    }

    this.getParent = function() {
        return this.parent;
    }

    this.addChild = function(node, parent, depth) {
        node.setParent(parent);
        this.children[this.children.length] = node;
    }

    this.getChildren = function() {
        return this.children;
    }

    this.removeChildren = function() {
        this.children = [];
    }
    
    this.hasChild = function(){
    	if(this.children.length == 0) return false;
    	else return true;
    }
}

zTree.prototype.findNode = 	function(current, id) {
	if (current.id == id.toString()) {
		return current;
	}
	var children = current.children;
	for (var i = 0; i < children.length; i++) {
		var result = this.findNode(children[i], id);
		if(result != null) 
			return result;
	}
	return null;
}

//depth계산을 위핸 만듬
zTree.prototype.getKeys = function(currChild, keys) {
	if (currChild.children.length == 0) {
		return keys[keys.length] = currChild.id;
	}else{
		keys[keys.length] = currChild.id;
		for (var i = 0; i < currChild.children.length; i++) {
			this.getKeys(currChild.children[i], keys);
		}
	}
	return keys;
}

//graph link 모델을 가져온다.
zTree.prototype.initFromLinkModel = function(rootNode, tree) {
	for (var i = 0; i < rootNode.linkDataArray.length; i++){
		var link = rootNode.linkDataArray[i];
		if(link.from.toString() == tree.id.toString()) {
			var node = new zTree(link.to);
			node.data = this.getDataNode(rootNode, link.to);
//			console.info("getDataNode::", link.to.toString(), JSON.stringify(node.data));
			tree.addChild(this.initFromLinkModel(rootNode, node), tree.id);
		}
	}		
	return tree;
} 

zTree.prototype.getDataNode = function(rootNode, key) {
	for (var i = 0; i < rootNode.nodeDataArray.length; i++) {
		if (rootNode.nodeDataArray[i].key.toString() == key.toString()) {
			return rootNode.nodeDataArray[i];
		}
	}
	return null;
}

zTree.prototype.setDataRootNode = function(rootNode){
	for (var i = 0; i < rootNode.nodeDataArray.length; i++) {
		if (rootNode.nodeDataArray[i].key.toString() == this.id.toString()) {
			this.data = rootNode.nodeDataArray[i];
//			console.info("setDataRootNode::", JSON.stringify(this.data));
			return;
		}
	}
	this.data = null;
}

//leftMenu html 을 만든다.
zTree.prototype.getJsTreeHtml = function(htmlFormat) {
	if(htmlFormat == null)
		htmlFormat = {nodeTag:"li", grouppingTag: "ul", hasChildClass: '', lastNodeClass:'', hrefFunction:'', showIcon:'false', isDraggable:'false'};
	console.info("getJsTreeHtml started!!", JSON.stringify(this));
	var html = '<' + htmlFormat.grouppingTag + '>';
	html =  this.makeHtml(this, htmlFormat, html, '');
	html += '</' + htmlFormat.grouppingTag + '>';	
	return html;
} 

//leftMenu html 을 만든다.
zTree.prototype.getleftMenu = function(htmlFormat) {
	if(htmlFormat == null)
		htmlFormat = {nodeTag:"li", grouppingTag: "ul", hasChildClass: '', lastNodeClass:'', hrefFunction:'',  showIcon:'false', isDraggable:'false'};
	console.info("makeHtml started!!");
	var html = '<' + htmlFormat.grouppingTag + '>';
	html =  this.makeHtml(this, htmlFormat, html, '');
	html += '</' + htmlFormat.grouppingTag + '>';	
	return html;
} 

zTree.prototype.makeHtml = function(tree, htmlFormat, html, type){
	if (tree.children.length == 0) {
		return html;
	} else {
		for (var i = 0; i < tree.children.length; i++) {
			if (i == 0) {
				if(tree.children[i].children){
					if (tree.children[i].children.length == 0) {
						if (tree.children.length == 1) {
							html = this.getNodeHtml(tree.children[i], htmlFormat, html, '');
							html += '</' + htmlFormat.grouppingTag + '>';
						} else {
							html = this.getNodeHtml(tree.children[i], htmlFormat, html, '');
						}
					} else {
						if (tree.children.length == 1) {
							html = this.getNodeHtml(tree.children[i], htmlFormat, html, 'hasSub');
						} else {
							html = this.getNodeHtml(tree.children[i], htmlFormat, html, 'hasSub');
							html += '<' + htmlFormat.grouppingTag + '>';
						}
					}
				}

			} else {
				if(tree.children[i].children){
					if (i == tree.children.length - 1) {
						if (tree.children[i].children.length == 0) {
							html = this.getNodeHtml(tree.children[i], htmlFormat, html, 'last');
							html += '</' + htmlFormat.grouppingTag + '>';
							html += '</' + htmlFormat.nodeTag + '>';
						} else {
							html = this.getNodeHtml(tree.children[i], htmlFormat, html, 'hasSub');
							html += '<' + htmlFormat.grouppingTag + '>';
						}
					} else {
						if (tree.children[i].children.length == 0) {
							html = this.getNodeHtml(tree.children[i], htmlFormat, html, 'last');
						} else {
							html = this.getNodeHtml(tree.children[i], htmlFormat, html, 'hasSub');
							html += '<' + htmlFormat.grouppingTag + '>';
						}
					}
				}
			}
			
			html = this.makeHtml(tree.children[i], htmlFormat, html, type);
		}
	}
	return html;
}


//향후 타입은 기본값으로 가져가야 할것같다.
zTree.prototype.getNodeHtml = function(tree, htmlFormat, html, type){
	console.info("treeIcon tree info ::: ", JSON.stringify(tree));
	switch (type){
		case 'last':
			if(htmlFormat.showIcon =='true'){
				html += '<' + htmlFormat.nodeTag + ' ' + htmlFormat.lastNodeClass + ' data-jstree=\'{"opened":false , "type":"' + tree.data.data.menuTp + '"}\'>';
			}
			else {
				html += '<' + htmlFormat.nodeTag + ' ' + htmlFormat.lastNodeClass + ' data-jstree=\'{"opened":false}\'>';
			}	
			
			if(htmlFormat.hrefFunction == '') {
				html += '<span ' + htmlFormat.dragClass + '>' + tree.data.text + '</span>';	
			}else{
				html += '<a href="#">' + this.getIconClass(htmlFormat.showIcon, tree.data.data.menuTp) + '<span  onclick=\'javascript:' +htmlFormat.hrefFunction + '(' + tree.id + ');\' style="margin-left:3px;" >';
				html += tree.data.text;		
				html += '</span></a>';
			}
			break;
			
		case 'first': // nodetag를 열어준다.
			if(htmlFormat.showIcon =='true'){
				html += '<' + htmlFormat.nodeTag + ' ' + htmlFormat.hasChildClass + ' data-jstree=\'{"opened":false , "type":"' + tree.data.data.menuTp + '"}\'>';
			}
			else {
				html += '<' + htmlFormat.nodeTag + ' ' + htmlFormat.hasChildClass + ' data-jstree=\'{"opened":false}\'>';
			}
			
			if(htmlFormat.hrefFunction == '') {
				html += '<span ' + htmlFormat.dragClass + '>' + tree.data.text + '</span>';	
			}else{
				html += '<a href="#">' + this.getIconClass(htmlFormat.showIcon, tree.data.data.menuTp) + '<span onclick=\'javascript:' +htmlFormat.hrefFunction + '(' + tree.id + ');\' style="margin-left:3px;" >';
				html += tree.data.text;		
				html += '</span></a>';
			}
			break;
		default:
			if(htmlFormat.showIcon =='true'){
				html += '<' + htmlFormat.nodeTag + ' data-jstree=\'{"opened":false , "type":"' + tree.data.data.menuTp + '"}\'>';
			}
			else {
				html += '<' + htmlFormat.nodeTag + ' data-jstree=\'{"opened":false}\'>';
			}
		
			if(htmlFormat.hrefFunction == '') {
				html += '<span ' + htmlFormat.dragClass + '>' + tree.data.text + '</span>';	
			}else{
				html += '<a href="#">' + this.getIconClass(htmlFormat.showIcon, tree.data.data.menuTp) + '<span onclick=\'javascript:' +htmlFormat.hrefFunction + '(' + tree.id + ');\' style="margin-left:3px;" >';
				html += tree.data.text;		
				html += '</span></a>';
			}
			break;
	}	
	return html;
}


zTree.prototype.getIconClass = function(showIcon, type) {	
	if(showIcon=='false') return '';
	switch (type){
		case 'F':
			return '<i class="fa fa-folder"></i>';
		case 'D':
			return '<i class="fa fa-laptop"></i>';
		case 'R':
			return '<i class="fa fa-cog"></i>';
		case 'P':
			return '<i class="fa fa-cogs"></i>';
		case 'L':
			return '<i class="fa fa-link"></i>';
		case 'V':
			return '<i class="fa fa-bar-chart"></i>';
		default:
			return '<i class="fa fa-question"></i>';
	}
}











