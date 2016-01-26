//jspanel이 로드되어있어야 함.
var currentTree = null;
var currentPanel = null;
var currentSourceType = null;
var cssmenu_to = false; //menu search

function openElement(id) {
	//여러번하는경우
	currentSourceType = null;
	
	switch (id){
	case "openDocument":
		var html = '<div id="selectionObjects">';
		html += '		<div id="catalog" style="min-height: 400px;"">';
		html += '			<h2>';
		html += '				<a href="#">process개체</a>';
		html += '			</h2>';
		html += '			<div>';
		html += '				<ul>';
		html += '					<li><input type="text" id="cssmenu_q" value="" style="margin: 0em auto 1em auto; display: block; padding: 4px; border-radius: 4px; border: 1px solid silver; width:95%" /></li>';
		html += '					<li><div id="cssmenu" style="width: 100%; min-height: 200px;"></div></li>';
		html += '				</ul>';
		html += '			</div>';
		html += '			<h2>';
		html += '				<a href="#">메뉴에 없는 개체</a>';
		html += '			</h2>';
		html += '			<div>';
		html += '				<ul>';
		html += '					<li><a href="javascript:addItem(\'slider\');"><span  objectKey="slider">slider</span></a></li>';
		html += '					<li><a href="javascript:addItem(\'dropdownbox\');"><span  objectKey="dropdownbox">dropdownbox</span></a></li>';
		html += '					<li><a href="javascript:addItem(\'search\');"><span  objectKey="search">search</span></a></li>';
		html += '				</ul>';
		html += '			</div>';
		html += '		</div>';
		html += '	</div>';

		var arr = [
					{
					    item:     '<button id="btnMenu">메뉴등록</button>',
					    event:    "click",
					    btnclass: "btn-sm",
					    callback: function( event ){
					     	   var menu = $.jsPanel({
									paneltype : {
										type : 'modal',
										mode : 'default'
									},
									size : {
										width : window.innerWidth * 0.8,
										height : window.innerHeight * 0.8
									},
									theme : 'medium',
									title : "메뉴등록",
									iframe : {
										src : "/zPortal/zVM/zMenuManager.html?fileName=system&objectKey=menu",
										style : {
											"display" : "none",
											"border" : "10px solid transparent"
										},
										width : '100%',
										height : '100%'
									},
									callback : function(panel) {
										$("iframe", panel).load(function(e) {
											$(e.target).fadeIn(200);
										});
									}
								});
					    }
					},
		           {
		               item:     '<button id="btnMenu">닫기</button>',
		               event:    "click",
		               btnclass: "btn-sm",
		               callback: function( event ){event.data.close() }
		           }
		       ];
		
		currentPanel = jQuery.jsPanel({
			paneltype : {
				type : 'modal',
				mode : 'default'
			},
			title : "오브젝트선택",
			theme : "light",
			position : "center",
			toolbarFooter: arr,
			content: html,
			size : {
				width : 300,
				height : 300
			}
		});

		processShow("로딩중입니다.", 2000);
		jQuery.ajax({
			type : "POST",
			url : "/zPortal/modelManager/load.do",
			dataType : "json",
			success : function(msg) {
				var rootNode = new zMRootNode();
				rootNode.fromObject(msg);				
				rootNode.setTypeFiltering(['F','R']);
				
				var treeRoot = new zTree('#');
				treeRoot.setDataRootNode(rootNode);
				
				console.info("initFromLinkModel started rootNode", JSON.stringify(rootNode));
				var tree = treeRoot.initFromLinkModel(rootNode, treeRoot);
				currentTree = tree;
				
				var html = '';
				var result = tree.getJsTreeHtml({
							nodeTag:"li", 
							grouppingTag: "ul", 
							hasChildClass: '', 
							lastNodeClass:'', 
							hrefFunction:'addItem', 
							showIcon:'true',
							dragClass:''});
				
				if(result != ''){
					html += result;
				}
				else{
					html += '<ul><li><a href="#"><span>메뉴없음</span></a></li></ul>';
				}
				
				console.info("보고서 트리 결과 html",html);
				jQuery("#cssmenu").html(html);
				
				jQuery('#cssmenu').jstree(
				{
					"core" : {
							"check_callback" : true
					},

					"types" : {
					      "default" : {
					        "icon" : "fa fa-folder-o"
					      },
					      "R" : {
					        "icon" : "fa fa-cogs"
					      }
					 },
					"plugins" : [ "wholerow" , "types" , "search", "state", "dnd"]
				});
				
				$('#cssmenu_q').keyup(function() {
					if (cssmenu_to) {
						clearTimeout(cssmenu_to);
					}
					to = setTimeout(function() {
						var v = $('#cssmenu_q').val();
						$('#cssmenu').jstree(true).search(v);
					}, 250);
				});

				jQuery("#selectionObjects").draggable({
					handle : ".ui-widget-header"
				}).resizable({
					stop : function() {
						;
					}
				});

				jQuery("#catalog").accordion();
			},
			error : function(request, status, error) {
				alert("error:" + error);
			},
			data : {
				fileName : 'system',
				objectKey : 'menu'
			}
		});
		break;
	case "removeDocument":
		alert("삭제할수 없습니다.");
		break;
		
		default:
			alert("알수없는 호출입니다.");
			break;
	}	
}

//선택되면 오픈함.
function addItem(id) {
	var node = currentTree.findNode(currentTree, id);
	console.info("node", JSON.stringify(node));
	
//	var meneId = '';
	var menuTp = '';
	var text = '';
	
	if(node == null) {
		menuid = id;
		menuTp = 'O';
		text = id;
	}else
	{
//		menuId = node.data.data.menuId;
		menuTp = node.data.data.menuTp;
		text = node.data.data.menuNm;
	}
	
	if(menuTp!="R") return;
	
	processShow("로딩중입니다.", 2000);
	$.ajax({
		type : "POST",
		url : "/zPortal/modelManager/load.do",
		dataType : "json",
		success : function(msg) {
			console.info(msg);
			if (msg[0] == "notFound") {
				setCurrentFileName(text);
				saveServerDocument(text,"main","", "new");
				myDiagram.model.undoManager.isEnabled = true;
				myDiagram.isModified = false;
			}
			else
			{
				setCurrentFileName(text);
				myDiagram.model = go.Model.fromJson(msg);
				myDiagram.model.undoManager.isEnabled = true;
				myDiagram.isModified = false;
			}
			
			currentPanel.close();
		},
		error : function(request, status, error) {
			alert("request:" + request.responseText + "\n error:" + error);
		},
		data : {
			fileName : "process\\" + text,
			objectKey : "main"
		}
	});
	
}

//데이터소스 편집
function openDataSourceEdit(obj) {
	myDiagram.startTransaction("openDataSourceEdit");

	myDiagram.selection.each(function(node) {
		if (!(node instanceof go.Node))
			return;

		var fileName = getCurrentFileName();
		var objectKey = node.data.key;
		var html = '<div id="sourceTabs">';
			html += '	<ul>';
			html += '	<li><a href="#sourceTabs-1">FILE</a></li>';
			html += '	<li><a href="#sourceTabs-2">ODBC</a></li>';
			html += '	<li><a href="#sourceTabs-3">UserMemory</a></li>';
			html += '</ul>';
			html += '<div id="sourceTabs-1">';
			html += '	<fieldset>';
			html += '		<legend>FILE</legend>';
			html += '		<div>';
			html += '			<button id="uploadBtn" onclick="localFileLoad(\'' + fileName + '\',\'' + objectKey + '\')">로컬파일</button>';
			html += '		</div>';
			html += '		<div>';
			html += '			<button id="uploadBtn" onclick="serverFileLoad(\'' + fileName + '\',\'' + objectKey + '\')">서버파일</button>';
			html += '		</div>';
			html += '	</fieldset>';
			html += '</div>';
			html += '<div id="sourceTabs-2">';
			html += '	<fieldset>	';				
			html += '		<legend>ODBC</legend>';
			html += '		<div>';
			html += '			<label for="zODBCChoice">ODBC선택</label> <select id="zODBCChoice"></select>';
			html += '		</div>';
			html += '		<div>';
			html += '			<label for="url">DB선택</label> <select id="zDbChoice"></select>';
			html += '		</div>';
			html += '		<div>';
			html += '			<button id="daveODBCBtn" onclick="saveODBCInfo(\'' + fileName + '\',\'' + objectKey + '\')">정보저장</button>';
			html += '		</div>';
			html += '	</fieldset>';	
			html += '</div>';
			html += '<div id="sourceTabs-3">';
			html += '	<fieldset>	';				
			html += '		<legend>사용자저장파일</legend>';
			html += '		<div>';
			html += '			<button id="showUserDataBtn" onclick="showUserData(\'' + fileName + '\',\'' + objectKey + '\')">사용자저장파일보기</button>';
			html += '		</div>';
			html += '	</fieldset>';	
			html += '</div>';
		
		var arr = [		
		           {
		               item:     '<button id="btnSourceClose">Close</button>',
		               event:    "click",
		               btnclass: "btn-sm",
		               callback: function( event ){
		            	   console.info("dataStorageNodeRename");
		            	   dataStorageNodeRename();
		            	   event.data.close();
		               }
		           }
		       ];
					
		currentPanel = jQuery.jsPanel({
			paneltype : {
				type : 'modal',
				mode : 'default'
			},
			title : "데이터소스선택",
			theme : "light",
			position : "center",
			toolbarFooter: arr,
			content: html
		});
		
		jQuery("#sourceTabs").tabs();
		jQuery("#sourceTabs").height(window.innerHight * 0.95);
	});
	
	getDbSources();
	
	myDiagram.commitTransaction("openDataSourceEdit");
}

//데이터 오브젝트 편집
function openDataObjectEdit(obj) {
	myDiagram.startTransaction("openDataObjectEdit");

	if (myDiagram.isModified) {
		//정보를 얻기위해 저장하여야 함
		var r = confirm("정보추적을 위해 프로세스를 저장합니다.");
		if (r == true) {
			var fileName = getCurrentFileName();
			saveDiagramProperties()
			myDiagram.isModified = false;
			saveServerDocument(fileName, "main", myDiagram.model.toJson(), "");
		} else {
			return;
		}
	}
		
	myDiagram.selection.each(function(node) {
		if (!(node instanceof go.Node))
			return;

		var fileName = getCurrentFileName();
		var objectKey = node.data.key;
		var parentKey = null;
		var type = null;
		
		//odbc가 아닌경우에는 parentkey를 넘긴다.
		for(var index in myDiagram.model.linkDataArray){
			if(myDiagram.model.linkDataArray[index].to == node.data.key){
				for(var index_n in myDiagram.model.nodeDataArray){
					if(myDiagram.model.nodeDataArray[index_n].key==myDiagram.model.linkDataArray[index].from){
						type = myDiagram.model.nodeDataArray[index_n].text;
						parentKey = myDiagram.model.nodeDataArray[index_n].key;						
						break;
					}
				}
			}
		}
		
		var arr = [		
		           {
		               item:     '<button id="btnSourceClose">Close</button>',
		               event:    "click",
		               btnclass: "btn-sm",
		               callback: function( event ){
		            	   event.data.close();
		               }
		           }
		       ];
					
		currentPanel = $.jsPanel({
			paneltype : {
				type : 'modal',
				mode : 'default'
			},
			toolbarFooter: arr,
			size : {
				width : window.innerWidth * 0.8,
				height : window.innerHeight * 0.8
			},
			theme : 'medium',
			title : "데이터 매핑",
			iframe : {
				src : "/zPortal/zVM/zRecordMapper.html?fileName=" + fileName + "&objectKey=" + objectKey + "&type=" + type + "&parentKey=" + parentKey,
				style : {
					"display" : "none",
					"border" : "10px solid transparent"
				},
				width : '100%',
				height : '100%'
			},
			callback : function(panel) {
				$("iframe", panel).load(function(e) {
					$(e.target).fadeIn(200);
				});
			}
		});
	});
	
	myDiagram.commitTransaction("openDataObjectEdit");
}

function openVisualiztionEdit(obj) {
	myDiagram.startTransaction("openVisualiztionEdit");

	if (myDiagram.isModified) {
		//정보를 얻기위해 저장하여야 함
		var r = confirm("정보추적을 위해 프로세스를 저장합니다.");
		if (r == true) {
			var fileName = getCurrentFileName();
			saveDiagramProperties()
			myDiagram.isModified = false;
			saveServerDocument(fileName, "main", myDiagram.model.toJson(), "");
		} else {
			return;
		}
	}
		
	myDiagram.selection.each(function(node) {
		if (!(node instanceof go.Node))
			return;

		var fileName = getCurrentFileName();
		var objectKey = node.data.key;
		var parentKey = null;
		var type = null;
		
		//odbc가 아닌경우에는 parentkey를 넘긴다.
		for(var index in myDiagram.model.linkDataArray){
			if(myDiagram.model.linkDataArray[index].to == node.data.key){
				for(var index_n in myDiagram.model.nodeDataArray){
					if(myDiagram.model.nodeDataArray[index_n].key==myDiagram.model.linkDataArray[index].from){
						parentKey = myDiagram.model.nodeDataArray[index_n].key;
						type = myDiagram.model.nodeDataArray[index_n].text;
						break;
					}
				}
			}
		}
		var arr = [		
		           {
		               item:     '<button id="btnSourceClose">Close</button>',
		               event:    "click",
		               btnclass: "btn-sm",
		               callback: function( event ){
		            	   event.data.close();
		               }
		           }
		       ];
					
		currentPanel = $.jsPanel({
			paneltype : {
				type : 'modal',
				mode : 'default'
			},
			toolbarFooter: arr,
			size : {
				width : window.innerWidth * 0.8,
				height : window.innerHeight * 0.8
			},
			theme : 'medium',
			title : "데이터 매핑",
			iframe : {
				src : "/zPortal/zVM/zVisualizationMapper.html?fileName=" + fileName + "&objectKey=" + objectKey + "&type=" + type + "&parentKey=" + parentKey,
				style : {
					"display" : "none",
					"border" : "10px solid transparent"
				},
				width : '100%',
				height : '100%'
			},
			callback : function(panel) {
				$("iframe", panel).load(function(e) {
					$(e.target).fadeIn(200);
				});
			}
		});
	});
	
	myDiagram.commitTransaction("openVisualiztionEdit");
}


function dataStorageNodeRename(){	
	myDiagram.selection.each(function(node) {
		if(node instanceof go.Node){
			if(currentSourceType==null) currentSourceType = "FILE";
			node.data.text = currentSourceType;
			//다이어그램이 리플래쉬되는 방법은?
			myDiagram.layoutDiagram(true);
		}
	});
}

function dataStorageNodeShow(fileName, objectKey){
	processShow("로딩중입니다.", 2000);
	jQuery.ajax({
		type : "POST",
		url : "/zPortal/modelManager/load.do",
		dataType : "json",
		success : function(msg) {
			if(msg[0]=="notFound"){
				alert("저장된 소스가 없습니다.");
			}else{
				if(msg.sourceType =="ODBC") {
					alert("데이터소스입니다. DB는" + msg.connName);
				}else{
					showTabList(fileName, "data_" + objectKey);
				}
			}
		},
		error : function(request, status, error) {
			alert("error:" + error);
		},
		data : {fileName:"process//" + fileName, objectKey:objectKey}
	});
}

function showTabList(fileName, objectKey){
	console.info(fileName, objectKey);
	
	if(fileName=='_New'){
		alert("Process파일을 저장하셔야 합니다.");
		return;
	}
	
	var arr = [
	           {
	               item:     '<button id="btnSourceClose">Close</button>',
	               event:    "click",
	               btnclass: "btn-sm",
	               callback: function( event ){event.data.close()}
	           }
	       ];
	
	currentPanel = $.jsPanel({
		toolbarFooter: arr,
		paneltype : {
			type : 'modal',
			mode : 'default'
		},
		size : {
			width : window.innerWidth * 0.6,
			height : window.innerHeight * 0.6
		},
		theme : 'medium',
		title : "파일생성",
		iframe : {
			name: 'panelFrame',
			src : "/zPortal/zVisualization/zTabList.html?fileName="+ fileName + "&objectKey=" + objectKey,
			style : {
				"display" : "none",
				"border" : "10px solid transparent"
			},
			width : '100%',
			height : '100%'
		},
		callback : function(panel) {
			$("iframe", panel).load(function(e) {
				$(e.target).fadeIn(200);
			});
		}
	});	
}

function localFileLoad(fileName, objectKey){
	console.info(fileName, objectKey);
	
	if(fileName=='_New'){
		alert("Process파일을 저장하셔야 합니다.");
		return;
	}
	
	var arr = [
	           {
	               item:     '<button id="btnSourceClose">Close</button>',
	               event:    "click",
	               btnclass: "btn-sm",
	               callback: function( event ){event.data.close()}
	           }
	       ];
	
	currentPanel = $.jsPanel({
		toolbarFooter: arr,
		paneltype : {
			type : 'modal',
			mode : 'default'
		},
		size : {
			width : window.innerWidth * 0.6,
			height : window.innerHeight * 0.6
		},
		theme : 'medium',
		title : "파일생성",
		iframe : {
			name: 'panelFrame',
			src : "/zPortal/zVM/zFileRead.html?fileName="+ fileName + "&objectKey=" + objectKey,
			style : {
				"display" : "none",
				"border" : "10px solid transparent"
			},
			width : '100%',
			height : '100%'
		},
		callback : function(panel) {
			$("iframe", panel).load(function(e) {
				$(e.target).fadeIn(200);
			});
		}
	});	
}


function processShow(text, close){
	var cont = "<div style='font-size:14px;'>" +
	"<div style='float:left;width:auto;height:100%'>" +
        "<i class='fa fa-spinner fa-pulse' style='font-size:18px;padding:18px;'></i>" +
    "</div>" +
    "<p style='padding:14px 0;'>" + text + "</p>" +
    "</div>";
    
	jQuery.jsPanel({
        paneltype: 'hint',
        theme:     'info',
        position:  'center',
        autoclose: close, 
        size:      { width: 360, height: 'auto' },
        content:   cont
    });
}


function getDbSources() {
	console.info("getDbSources start");
	if(jQuery('#zODBCChoice').option == null){
		jQuery.ajax({
			type : "POST",
			url : "/zPortal/modelManager/getODBCList.do",
			dataType : "json",
			success : function(msg) {
				console.info("getODBCList:" + JSON.stringify(msg));
				if (msg) {
					var obj = msg;
					var db_option = '<option name="odbcName" value="">선택하세요</option>';
					for (var i = 0; i < obj.odbcs.length; i++) {
						db_option += '<option name="odbcName" value="' + obj.odbcs[i].odbcName + '">' + obj.odbcs[i].odbcName + '</option>';
					}
					jQuery('#zODBCChoice').append(db_option);
				} else {
					alert("불러올수 없습니다.");
				}
			},
			error : function(request, status, error) {
				alert("request:" + request.responseText + "\n error:" + error);
			}
		});
		
		jQuery("#zODBCChoice").change(function() {			
			var odbcName = document.getElementById("zODBCChoice").value;
			if(odbcName.length < 1) return;
			
			var sendInfo = {
				odbcName : odbcName
			};
			jQuery.ajax({
				type : "POST",
				url : "/zPortal/modelManager/getDbSources.do",
				dataType : "json",
				success : function(msg) {
					console.info("sendInfo" , JSON.stringify(sendInfo));
					console.info("getDbSources:" , JSON.stringify(msg));
					if (msg) {
						var obj = msg;
						var db_option = '';
						for (var i = 0; i < obj.length; i++) {
							db_option += '<option value="' + obj[i][0] + '">' + obj[i][0] + '</option>';
						}
						jQuery('#zDbChoice').append(db_option);
					} else {
						alert("불러올수 없습니다.");
					}
				},
				error : function(request, status, error) {
					alert("request:" + request.responseText + "\n error:" + error);
				},
				data : sendInfo
			});
			
		});
	}
}


function saveODBCInfo(fileName, objectKey){
	console.info(fileName, objectKey);
	
	if(fileName=='_New'){
		alert("Process파일을 저장하셔야 합니다.");
		return;
	}
	var odbcName = document.getElementById("zODBCChoice").value;
	var dbName = document.getElementById("zDbChoice").value;
	
	jQuery.ajax({
		type : "POST",
		url : "/zPortal/modelManager/save.do",
		dataType : "json",
		success : function(msg) {
			if (msg) {
				alert("저장되었습니다.");
			} else {
				alert("저장할수 없습니다.");
			}
		},
		error : function(request, status, error) {
			alert("request:" + request.responseText + "\n error:" + error);
		},
		data : {
			fileName:"process//" + fileName, 
			objectKey: objectKey, 
			content : "{ \"sourceType\": \"ODBC\" , \"connName\" : \"" + odbcName + "\", \"subName\" :  \"" + dbName + "\"}"}
	});
	currentSourceType = "ODBC";
}

function showUserData(fileName, objectKey){
	console.info(fileName, objectKey);
	
	if(fileName=='_New'){
		alert("Process파일을 저장하셔야 합니다.");
		return;
	}
	
	var arr = [
	           {
	               item:     '<button id="btnSourceClose">Close</button>',
	               event:    "click",
	               btnclass: "btn-sm",
	               callback: function( event ){event.data.close()}
	           }
	       ];
	
	currentPanel = jQuery.jsPanel({
		toolbarFooter: arr,
		paneltype : {
			type : 'modal',
			mode : 'default'
		},
		size : {
			width : window.innerWidth * 0.6,
			height : window.innerHeight * 0.6
		},
		theme : 'medium',
		title : "사용자파일",
		iframe : {
			name: 'panelFrame',
			src : "/zPortal/redirect.do?href=" + encodeURIComponent("/zPortal/zVM/zUserSavedData.html?fileName="+ fileName + "&objectKey=" + objectKey),
			style : {
				"display" : "none",
				"border" : "10px solid transparent"
			},
			width : '100%',
			height : '100%'
		},
		callback : function(panel) {
			$("iframe", panel).load(function(e) {
				$(e.target).fadeIn(200);
			});
		}
	});
	
	currentSourceType = "USERDATA";
}


function serverFileLoad(fileName, objectKey){
	alert("준비중입니다.");
}


function dataProcessing(dataSet, model, fileName, objectKey){
	//1. model을 해석하자
	var objectType = null;
	for(var i in model.nodeDataArray){
		
		if("object" == model.nodeDataArray[i].category){
			objectType = model.nodeDataArray[i].key;
		}
	}
	console.info("저장할데이터::", objectType);
	
	switch (objectType){
		case "subdata_json":
			
			console.info("subdata", fileName, objectKey);
			
			var dataDataSet = null;
			var subDataSet = null;
			
			var dataKeyIndex = null;
			var subKeyIndex = null;
			for(var i in model.linkDataArray){
				if( model.linkDataArray[i].toPort == "데이터Key"){
					for(var index =0; index < dataSet.length; index++){
						console.info(dataSet[index].tableName,model.linkDataArray[i].from);
						if(dataSet[index].tableName == model.linkDataArray[i].from){
							dataDataSet = dataSet[index].data;
							for(var idx in dataDataSet[0]){
								if(model.linkDataArray[i].fromPort == dataDataSet[0][idx]){
									dataKeyIndex = idx;
								}
							}
						}
					}
				}
				if( model.linkDataArray[i].toPort == "SUB데이터Key"){
					for(var index =0; index < dataSet.length; index++){
						console.info(dataSet[index].tableName,model.linkDataArray[i].from);
						if(dataSet[index].tableName == model.linkDataArray[i].from){
							subDataSet = dataSet[index].data;
							for(var idx in subDataSet[0]){
								if(model.linkDataArray[i].fromPort == subDataSet[0][idx]){
									subKeyIndex = idx;
								}
							}
						}
					}
				}
			}
			
			var subHeader = subDataSet[0];			
			console.info("subHeader", subHeader);
			
			subDataSet.splice(0, 1);
			var header = dataDataSet[0];
			header.push("data");
			dataDataSet.splice(0, 1);
			var newData = convertToSubDatJson(header, subHeader, dataDataSet, subDataSet, dataKeyIndex, subKeyIndex);
			saveProcessingData(fileName, objectKey, newData);
			
			break;
		case "json":
			console.info("json", fileName, objectKey);
			
			var dataDataSet = null;
			for(var i in model.linkDataArray){
				if( model.linkDataArray[i].toPort == "추출"){
					for(var index =0; index < dataSet.length; index++){
						console.info(dataSet[index].tableName,model.linkDataArray[i].from);
						if(dataSet[index].tableName == model.linkDataArray[i].from){
							dataDataSet = dataSet[index].data;
							break;
						}
					}
				}
			}
			
			console.info("json", fileName, objectKey);
			var header = [];
			for(var i in model.linkDataArray){
				header.push(model.linkDataArray[i].fromPort);
			}
			
			dataDataSet.splice(0, 1);
			var newData = convertToJson(header, dataDataSet);
			saveProcessingData(fileName, objectKey, newData);			
			break;
		
		case objectType.substring(0,2) == "am":		
			console.info("am_json", fileName, objectKey);
			var header = [];
			var swapHeader = [];
			for(var i in model.linkDataArray){
				header.push(model.linkDataArray[i].toPort);
				swapHeader.push(model.linkDataArray[i].fromPort);
			}
			
			dataSet.splice(0, 1);
			var newData = convertToSimpleJson(header, dataSet, swapHeader);
			saveProcessingData(fileName, objectKey, newData);
			
			break;
		case "distinctextract": //---------테이블 한개에서만 지원한다.
			console.info("distinctextract", fileName, objectKey);
			var dataDataSet = null;
			var columns = [];
			for(var i in model.linkDataArray){
				if( model.linkDataArray[i].toPort == "추출"){
					for(var index =0; index < dataSet.length; index++){
						console.info(dataSet[index].tableName,model.linkDataArray[i].from);
						if(dataSet[index].tableName == model.linkDataArray[i].from){
							dataDataSet = dataSet[index].data;
							for(var idx in dataDataSet[0]){
								if(model.linkDataArray[i].fromPort == dataDataSet[0][idx]){
									columns.push(idx);
								}
							}
							break;
						}						
					}
				}
			}
			var newDataSet = [];
			for(var index = 0 ; index < dataDataSet.length; index++){
				var row = [];
				for(var index_s in columns){
					row.push(dataDataSet[index][columns[index_s]]);
				}
				newDataSet.push(row);
			}
			
			//distinct하려면 sort, unique처리를 한다.
			newDataSet = jQuery.unique(newDataSet);
			
			saveProcessingData(fileName, objectKey, newDataSet);
			break;
		case "extract": //---------테이블 한개에서만 지원한다.
			console.info("extract", fileName, objectKey);
			var dataDataSet = null;
			var columns = [];
			for(var i in model.linkDataArray){
				if( model.linkDataArray[i].toPort == "추출"){
					for(var index =0; index < dataSet.length; index++){
						console.info(dataSet[index].tableName,model.linkDataArray[i].from);
						if(dataSet[index].tableName == model.linkDataArray[i].from){
							dataDataSet = dataSet[index].data;
							for(var idx in dataDataSet[0]){
								if(model.linkDataArray[i].fromPort == dataDataSet[0][idx]){
									columns.push(idx);
								}
							}
							break;
						}						
					}
				}
			}
			var newDataSet = [];
			for(var index = 0 ; index < dataDataSet.length; index++){
				var row = [];
				for(var index_s in columns){
					row.push(dataDataSet[index][columns[index_s]]);
				}
				newDataSet.push(row);
			}
			
			saveProcessingData(fileName, objectKey, newDataSet);
			break;
		default :
			saveProcessingData(fileName, objectKey, dataSet);
			break;
	}
}

function saveProcessingData(fileName, objectKey, newDataSet){
	console.info("JSON.stringify(newDataSet)", JSON.stringify(newDataSet));
	jQuery.ajax({
		type : "POST",
		url : "/zPortal/modelManager/save.do",
		dataType : "json",
		success : function(msg) {
			if (msg) {
				alert("저장되었습니다.");
			} else {
				alert("저장할수 없습니다.");
			}
		},
		error : function(request, status, error) {
			alert("request:" + request.responseText + "\n error:" + error);
		},
		data : {
			fileName:"process//" + fileName, 
			objectKey: "data_" + objectKey, 
			content : JSON.stringify(newDataSet)
		}
	});
}

function convertToJson(header, dataSet){
	console.info("convertToJson header", header);
	var result = [];
	var format = "{";
	for(var i in header){
		format += "\"" + header[i] + "\":\"\","; 
	}
	format = format.substring(0, format.length - 1) + "}";
	console.info(format);
	var newDataSet = [];
	for(var index in dataSet){
		var object = JSON.parse(format);
		for(var index_h in header){
			object[header[index_h]] = dataSet[index][getHeaderIndex(header,header[index_h])];
		}
		newDataSet.push(object);
	}
	
	return newDataSet;
}

function convertToSimpleJson(header, dataSet, swapHeader){
	console.info("convertToSimpleJson header, swapheader", header, swapHeader);
	var result = [];
	var format = "{";
	for(var i in header){
		format += "\"" + header[i] + "\":\"\","; 
	}
	format = format.substring(0, format.length - 1) + "}";
	console.info(format);
	var newDataSet = [];
	for(var index in dataSet){
		var object = JSON.parse(format);
		for(var index_h in swapHeader){
			object[header[index_h]] = dataSet[index][getHeaderIndex(swapHeader,swapHeader[index_h])];
		}
		newDataSet.push(object);
	}
	
	return newDataSet;
}

function convertToSubDatJson(header, subHeader, dataDataSet, subDataSet, dataKeyIndex, subKeyIndex){
	console.info("convertToJson", dataKeyIndex, subKeyIndex);
	var result = [];
	var format = "{";
	for(var i in header){
		format += "\"" + header[i] + "\":\"\","; 
	}
	format = format.substring(0, format.length - 1) + "}";
	
	var subFormat = "{";
	for(var i in subHeader){
		subFormat += "\"" + subHeader[i] + "\":\"\",";  
	}
	subFormat = subFormat.substring(0, subFormat.length - 1) + "}";
	console.info(format);
	console.info(subFormat);
	var newDataSet = [];
	for(var index in dataDataSet){
		var object = JSON.parse(format);
		for(var index_h in header){
			object[header[index_h]] = dataDataSet[index][getHeaderIndex(header,header[index_h])];
		}
		
		var subObjects = [];
		for(var index_s in subDataSet){
			if(dataDataSet[index][dataKeyIndex] == subDataSet[index_s][subKeyIndex]){
				var subObject = JSON.parse(subFormat);
				for(var index_sf in subHeader){
					subObject[subHeader[index_sf]] = subDataSet[index_s][getHeaderIndex(subHeader,subHeader[index_sf])]; 
				}
				subObjects.push(subObject);
			}
		}
		object.data = subObjects;
		newDataSet.push(object);
	}
	
	return newDataSet;
}

function getHeaderIndex(header, name){
	for(var i in header){
		if(header[i]==name) return i;
	}
}


function saveVObjectInfos(fileName, model){
	console.info("saveVObjectInfos", fileName);
	jQuery.ajax({
		type : "POST",
		url : "/zPortal/modelManager/load.do",
		dataType : "json",
		success : function(msg) {
			var content = msg;
			var object = {fileName:fileName, vObjects:[]};
			for (var i in model.nodeDataArray){
				if(model.nodeDataArray[i].category=="visualization"){
					object.vObjects.push(model.nodeDataArray[i]);
				}
			}			
			
			var foundIndex = -1;
			for(var i in content){
				if(content[i].fileName == fileName){
					foundIndex = i;
				}
			}
			
			if(foundIndex != -1){
				content.splice(foundIndex, 1);
			}
			content.push(object);
			
			jQuery.ajax({
				type : "POST",
				url : "/zPortal/modelManager/save.do",
				dataType : "json",
				success : function(msg) {
					console.info("saveVObjectInfos", "저장했습니다.");
				},
				error : function(request, status, error) {
					alert("request:" + request.responseText + "\n error:" + error);
				},
				data : {
					fileName:"process", 
					objectKey: "vObjectInfos",
					content: JSON.stringify(content)
				}
			});
		},
		error : function(request, status, error) {
			alert("request:" + request.responseText + "\n error:" + error);
		},
		data : {
			fileName:"process", 
			objectKey: "vObjectInfos"
		}
	});
}


