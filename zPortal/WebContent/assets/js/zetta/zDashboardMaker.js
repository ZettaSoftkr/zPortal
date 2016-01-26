var fileName = "users\\admin_dashboard";
var objectKey = "Book1"
function makeDashboard(tableData, dropFileName){
	objectKey = dropFileName.split(".")[0];
	var model = { "class": "go.GraphLinksModel", "nodeDataArray": [],"linkDataArray": []};
	var layoutInfo = getLayoutInfo(tableData.length);
	
	model = setModel(model, tableData);
	console.info(objectKey);
	//테이블 데이터를 분석한다.
}

//완성된 대시보드를 보여준다.
function showDashboard(){	
	return;
	jQuery.jsPanel({
		paneltype : {
			type : 'modal',
			mode : 'default'
		},
		size : {
			width : window.innerWidth * 0.90,
			height : window.innerHeight * 0.90
		},
		theme : 'medium',
		title : objectKey,
		iframe : {
			src : "/zPortal/zVM/zDViewerForUser.html?fileName=" + fileName + "&objectKey=" + objectKey,
			style : {
				"display" : "none",
				"border" : "10px solid transparent"
			},
			width : '100%',
			height : '100%'
		},
		callback : function(panel) {
			jQuery("iframe", panel).load(function(e) {
				jQuery(e.target).fadeIn(200);
			});
		}
	});
	
}


function setPanels(reports){
	console.info('reports',reports);
	
	for (var i = 0; i < reports.length; i++) {
		var report = reports[i];
		var reportId = report.id;
		//대시보드에서 표현하는것은 V, L둘중에 하나이다.
		//만약 V인경우에는 id_objectkey 형태로 들어오게됨.
		var subObjectKeys = report.id.toString().split("_");
		var subObjectKey = null;
		if(subObjectKeys.length == 2){
			subObjectKey = subObjectKeys[1];
			reportId = subObjectKeys[0];
		}else{
			subObjectKey = null;
		}    			
		showPanel(subObjectKey, report);			
	}
}

function showPanel(subObjectKey, report){
	var key = report.id;
	var text = report.text;

	$.ajax({
		type : "POST",
		url : "/zPortal/modelManager/load.do",
		dataType : "json",
		success : function(msg) {
				var reportInfo = msg;
				
				console.info("reportInfo", reportInfo);
				for(var i in reportInfo.nodeDataArray){
					if(reportInfo.nodeDataArray[i].key==subObjectKey){
						url = reportInfo.nodeDataArray[i].url + "?fileName=" + fileName + "\\" + objectKey + "&objectKey=" + subObjectKey;
						var $newPanel = $.jsPanel({
		            	    title:    report.text,
		            	    theme:    "light",
		            	    position: 	report.position,
		            	    size: { width: report.width, height: report.height },
		            	    iframe: {
		            	        src:    url,
		            	        style:  {"display": "none", "border": "10px solid transparent"},
		            	        width:  '100%',
		            	        height: '100%'
		            	    },
		            	    callback: function (panel) {
		            	        $("iframe", panel).load(function (e) {
		            	          $(e.target).fadeIn(100);
		            	        });
		            	    }
		            	});
					}
				}
		},
		error : function(request, status, error) {
			alert("error:" + error);
		},
		data : {fileName:fileName + "\\" + objectKey, objectKey:"main"}
	});
}


function getLayoutInfo(index){
	
	$.ajax({
		type : "POST",
		url : "/zPortal/modelManager/load.do",
		dataType : "json",
		success : function(msg) {
			var layout = msg;
			return layout[index];
		},
		error : function(request, status, error) {
			alert("error:" + error);
		},
		data : {fileName:"system", objectKey:"VLayoutInfo"}
	});
}


function setModel(model, tableData){
	var start = {"key":"1", "category":"activity", "leftGroup":"dataProcess", "text":"start", "chartType":"start", "url":""};
	var datastore = {"key":"2", "category":"datastore", "leftGroup":"dataProcess", "text":"FILE", "chartType":"datastore", "url":""};	
	var link = {"from":"", "to":""};
	model.nodeDataArray.push(start);
	model.nodeDataArray.push(datastore);
	model.linkDataArray.push({"from":"1", "to":"2"});
	for(var i in tableData.length){
		var dataobject = {"key":tableData.tableName, "category":"dataobject", "leftGroup":"dataProcess", "text":"dataobject", "chartType":"", "url":""};
		var visualization = {"key":"v" + tableData.tableName , "category":"visualization", "leftGroup":"visualization", "text":"amcolumnsimple", "chartType":"amcolumnsimple", "url":"/zPortal/zVisualization/amchart/amColumnSimple.html"};
		model.nodeDataArray.push(dataobject);
		model.nodeDataArray.push(visualization);
		model.linkDataArray.push({"from":"2", "to":dataobject.key});
		model.linkDataArray.push({"from":dataobject.key, "to":visualization.key});
	}
	return model;
}



