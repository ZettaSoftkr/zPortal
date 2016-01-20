zMRootNode = function() {
	this.class  = 'go.GraphLinksModel';
	this.nodeDataArray = new Array();
	this.linkDataArray = new Array();
};

zMRootNode = function(rootId, rootName, depth) {
	this.class = 'go.GraphLinksModel';
	this.nodeDataArray = new Array();
	this.linkDataArray = new Array();
	//menuId, menuNm, menuTp, sortSn, dc, searchKeyword, regDt, updtDt
	var data = new zMenu(rootId, rootName, 'F', 1, rootName, rootName, "", "");
	//key, category, text, data
	var root = new zMenuNode(rootId, 'Folder', rootName, data);
	this.nodeDataArray.push(root);
};

zMRootNode = function(rootId, rootName, depth, type) {
	this.nodeDataArray = new Array();
	this.linkDataArray = new Array();

	var data = new zOrg(rootId, rootName, type);
	var root = new zMenuNode(rootId, type, rootName, data);
	this.nodeDataArray.push(root);	
};

zMRootNode.prototype.getNodesWithIdAndDepth = function(menuList, header, rootId, depth){
	var depthMap = new Array();
	depthMap.push("0_" + rootId);
	
	for(var depthIndex = 0; depthIndex <= depth; depthIndex++){
		depthMap = this.getnodeDataArrayWithId(depthMap, header, menuList, depthIndex);
	}
}

zMRootNode.prototype.makeTree= function(list, header, tree, targetDepth, currentDepth)
{
	if (targetDepth == currentDepth){
		return tree;
	}
	
	currentDepth++;
	for (var i = 0; i < list.length; i++) {
		var prtOrgId = list[i][header['상위조직코드']];
		var orgId = list[i][header['조직코드']];
		var orgNm = list[i][header['조직명']];
		
		if (prtOrgId == tree.value) {
			var node = new zTree(orgId);
			var data = new zOrg(orgId, orgNm, 'Org');
			var menuNode = new zMenuNode(orgId, 'Org', orgNm, data);
			this.nodeDataArray.push(menuNode);

			var link = new zMenuLink(prtOrgId, orgId);
			this.linkDataArray.push(link);
			
			tree.addChild(this.makeTree(list, header, node, targetDepth, currentDepth), tree.value);
		}
	}
	return tree;
}

zMRootNode.prototype.findDataNode = function(filteredDataArray, key){
	if(key == null) {
		console.info("key:::" , key);
	}
	for(var index in filteredDataArray){
		if(filteredDataArray[index].key){
			if(key.toString() == filteredDataArray[index].key.toString())
				return true;
		}
	}
	return false;
}


zMRootNode.prototype.getnodeDataArrayWithId = function(depthMap, header, menuList, currentDepth){
	for (var keyIndex=0 ; keyIndex < depthMap.length ; keyIndex++) {
		var keys = depthMap[keyIndex].split("_");
		var value = keys[1];
		var key = keys[0];
		
		var sortIndex = 1;
        
		if(key == currentDepth){
        	//menulist 첫번째 행은 해더임.
        	for(var i=1 ; i <menuList.length ; i++) {
        		///menuId	prtMenuId	menuTp	menuNm	dc	sortSn	searchKeyword	regDt	updtDt
				var menuId = menuList[i][header['menuId']];
				var prtMenuId = menuList[i][header['prtMenuId']];
			    var menuTp = menuList[i][header['menuTp']];
			    var menuNm = menuList[i][header['menuNm']];
			    var dc = menuList[i][header['dc']];
			    var sortSn = menuList[i][header['sortSn']];
			    var searchKeyword = menuList[i][header['searchKeyword']];
			    var regDt = menuList[i][header['regDt']];
			    var updtDt = menuList[i][header['updtDt']];
			    
			    //menuId, menuNm, menuTp, sortSn, dc, searchKeyword, regDt, updtDt
			    var data = new zMenu(menuId, menuNm, menuTp, sortSn, dc, searchKeyword, regDt, updtDt);
				//key, category, text, data
				switch (menuTp) {
					case ('F'):
						menuTp = 'Folder';
						break;
					case ('R'):
						menuTp = 'Report';
						break;
					case ('P'):
						menuTp = 'Program';
						break;
					case ('L'):
						menuTp = 'Link';
						break;
					default:
						break;
				}
				var node = new zMenuNode(menuId, menuTp, menuNm, data);
				this.nodeDataArray.push(node);
				
				var link = new zMenuLink(prtMenuId, menuId);
				this.linkDataArray.push(link);
			}
        }
    }
				
	return depthMap;
}

zMRootNode.prototype.setTypeFiltering = function(types){
		
	var filteredDataArray = null;
	var filteredLinkArray = null;
	
	//filters [[Folder,"true"], [] ...]
	for(var index = 0; index < types.length; index++){
		var checkName = this.getFullType(types[index]);
		var temp = jQuery.grep(this.nodeDataArray, function(entry) {
			return ( entry.category == checkName);
		});
    	
		console.info("temp:" + JSON.stringify(temp));
    	if(filteredDataArray == null) filteredDataArray = temp;
    	else jQuery.merge(filteredDataArray, temp);
	}
	
	filteredDataArray = jQuery.unique(filteredDataArray);
	
	console.info("filteredDataArray:" + JSON.stringify(filteredDataArray));
  	if(filteredDataArray != null){
  		
  		//to 링크를 넣는다.
  		var tempLink = new Array();
  		for(var i = 0 ; i < this.linkDataArray.length ; i++){
  			var link  = this.linkDataArray[i];
  			for(var j = 0 ; j < filteredDataArray.length ; j++){
  				//from이 노드 인지 확인해야한다.
  				if(this.findDataNode(filteredDataArray, link.from)){
  					if(link.to == filteredDataArray[j].key) tempLink.push(link);
  				}
  			}
  		}
  		
  		if(filteredLinkArray == null) filteredLinkArray = tempLink;
  		else jQuery.merge(filteredLinkArray, tempLink);
  		
  		//중복이 일어날수 있다..따라서 Unique처리해줌.
  		filteredLinkArray = jQuery.unique(filteredLinkArray);
  		
    	this.nodeDataArray = filteredDataArray;
    	this.linkDataArray = filteredLinkArray;
	}
}

zMRootNode.prototype.fromJson = function(json){
	var obj = null;
	if (window.JSON && window.JSON.parse) {
		try {
			obj = window.JSON.parse(json);
			this.nodeDataArray = obj.nodeDataArray;
			this.linkDataArray = obj.linkDataArray;
	    } catch (d) {
	    	alert("json형식 오류 error:" + d);
	    }
	}
	return result;
}

zMRootNode.prototype.fromObject = function(obj){
	try {
		this.nodeDataArray = obj.nodeDataArray;
		this.linkDataArray = obj.linkDataArray;
    } catch (d) {
    	alert("json형식 오류 error:" + d);
    }
}

zMRootNode.prototype.getFullType = function(menuTp){
	switch (menuTp) {
		case ('F'):
			 return 'Folder';
		case ('R'):
			return 'Report';
		case ('P'):
			return 'Program';
		case ('L'):
			return 'Link';
		case ('D'):
			return 'Dashboard';
		case ('V'):
			return 'VisaulizationObject';
		default:
			return null;
	}
}

zMRootNode.prototype.getTypeColor = function(menuTp){
	switch (menuTp) {
		case ('F'):
			 return '#00BFFF';
		case ('R'):
			return '#FF5675';
		case ('P'):
			return '#FF7F50';
		case ('L'):
			return '#800000';
		case ('D'):
			return '#D2691E';
		case ('V'):
			return '#0064CD';
		default:
			return "#828282";
	}
}


zMRootNode.prototype.getCyModel = function(menuTp){
	var model = {nodes:new Array(), edges: new Array()};
	
	for (var i = 0; i < this.nodeDataArray.length; i++){
		var faveShape = "ellipse";
		if(this.nodeDataArray[i].text=='root') faveShape = "octagon";
		
		var faveColor = this.getTypeColor(this.nodeDataArray[i].data.menuTp);
		
		var data = {
			data:{
				id:	this.nodeDataArray[i].key, 
				name:	this.nodeDataArray[i].text, 
				type:	this.nodeDataArray[i].category,
				faveShape:	faveShape,
				faveColor:	faveColor
			}
		};
		model.nodes.push(data);
		
	}
	
	for (var i = 0; i < this.linkDataArray.length; i++){
		var data = {data:{source:this.linkDataArray[i].from, target:this.linkDataArray[i].to}};
		model.edges.push(data);
		
	}
	return model;
}
