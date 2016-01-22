"use strict";
var currentFileName = "";
var currentObjectKey = "";

function zRecordMapper_init(selectedfileName, objectKey) {
	console.info("zRecordMapper.js zRecordMapper_init start");
	currentFileName = selectedfileName;
	currentObjectKey = objectKey;
	getDbTableInfo();
}

function zDataSource_init(selectedfileName, objectKey) {
	console.info("zRecordMapper.js zDataSource_init start");
	currentFileName = selectedfileName;
	currentObjectKey = objectKey;
	getDbSources();
}

function getDbSources() {
	console.info("getDbSources start");
	if(jQuery('#zODBCChoice').option == null){
		$.ajax({
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
		
		$("#zODBCChoice").change(function() {			
			var odbcName = document.getElementById("zODBCChoice").value;
			if(odbcName.length < 1) return;
			
			var sendInfo = {
				odbcName : odbcName
			};
			$.ajax({
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

function getDbTableInfo() {
	var sendInfo = {
		fileName : currentFileName,
		objectKey : currentObjectKey
	};

	$.ajax({
		type : "POST",
		url : "/zPortal/modelManager/getDbTableInfo.do",
		dataType : "json",
		success : function(msg) {
			var dataSet = [];
			try {
				dataSet = msg;
			} catch (e) {
				alert(e);
				return;
			}
			var db_tables = [];
			for (var i = 0, l = dataSet.length; i < l; i++) {
				if (db_tables.indexOf(dataSet[i][2]) === -1) {
					db_tables.push(dataSet[i][2]);
				}
			}

			var zTables = new Array();

			var zField = function(name, text, info, color, figure) {
				this.name = name;
				this.text = text;
				this.info = info;
				this.color = color;
				this.figure = figure;
			};

			var zTable = function(key, category, text, fields) {
				this.key = key;
				this.category = category;
				this.text = text;
				this.fields = fields;
			};

			var db_option = '';
			for (var i = 0; i < db_tables.length; i++) {
				var fields = new Array();
				var table = new zTable();
				table.key = db_tables[i];
				table.category = "record";
				table.text = db_tables[i];

				for (var j = 0; j < dataSet.length; j++) {
					if (db_tables[i] == dataSet[j][2]) {
						var field = new zField(dataSet[j][3], dataSet[j][3], dataSet[j][4], "#F7B84B", "Ellipse");
						fields.push(field);
					}
				}
				table.fields = fields;
				zTables.push(table);
			}

			if (window.dataDiagram.model == undefined) {
				zRecordMapper(zTables);
				loadRecordMapper();
			}
		},
		error : function(request, status, error) {
			alert("request:" + request.responseText + "\n error:" + error);
		},
		data : sendInfo
	});
}

function saveDataSource() {
	var sendInfo;
	var radioObj = document.getElementsByName("sourceType");
	 
	var sourceType;
	for (var i = 0; i < radioObj.length; i++) {
		if(radioObj[i].checked){
			sourceType = radioObj[i].value;
			break;
		}
	}
	
	if(sourceType == 'FILE'){
		var file = document.getElementById("datafile").value.replace(/^.*[\\\/]/, '').split("kepath");
		var fileName = file[file.length-1];
		
		sendInfo = {
			fileName : currentFileName,
			objectKey : currentObjectKey,
			content : "{ \"sourceType\": \"" + sourceType + "\" , \"connName\" : \"" + fileName + "\", \"subName\" :  \"" + fileName + "\"}"
		};
	}else{
		var connName = document.getElementById("zODBCChoice").value;
		var subName = document.getElementById("zDbChoice").value;
		sendInfo = {
				fileName : currentFileName,
				objectKey : currentObjectKey,
				content : "{ \"sourceType\": \"" + sourceType + "\" , \"connName\" : \"" + connName + "\", \"subName\" :  \"" + subName + "\"}"
			};
	}

	$.ajax({
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
		data : sendInfo
	});
}

function saveRecordMapper() {
	var sendInfo = {
		fileName : currentFileName,
		objectKey : currentObjectKey,
		content : window.dataDiagram.model.toJson()
	};

	$.ajax({
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
		data : sendInfo
	});
}

function loadRecordMapper() {
	var sendInfo = {
			fileName : currentFileName,
			objectKey : currentObjectKey
	};

	$.ajax({
		type : "POST",
		url : "/zPortal/modelManager/load.do",
		dataType : "json",
		success : function(msg) {
			if (msg) {
				var obj;
				try {
					obj = jQuery.parseJSON(msg);
					dataDiagram.model = go.Model.fromJson(obj);
				} catch (e) {
					// alert(msg);
				}
			} else {
				alert("정보를 로드하지 못했습니다.");
			}
		},
		error : function(request, status, error) {
			alert("request:" + request.responseText + "\n error:" + error);
		},
		data : sendInfo
	});
}

function zRecordMapper(zTables) {
	var $ = go.GraphObject.make;

	var fieldTemplate = $(go.Panel, "TableRow", // this Panel is a row in the
												// containing Table
	new go.Binding("portId", "name"), // this Panel is a "port"
	{
		background : "transparent", // so this port's background can be picked
									// by the mouse
		fromSpot : go.Spot.Right, // links only go from the right side to the
									// left side
		toSpot : go.Spot.Left,
		// allow drawing links from or to this port:
		fromLinkable : true,
		toLinkable : true
	}, $(go.Shape, {
		width : 12,
		height : 12,
		column : 0,
		strokeWidth : 2,
		margin : 4,
		// but disallow drawing links from or to this shape:
		fromLinkable : false,
		toLinkable : false
	}, new go.Binding("figure", "figure"), new go.Binding("fill", "color")), $(go.TextBlock, {
		margin : new go.Margin(0, 2),
		column : 1,
		font : "bold 11px sans-serif",
		// and disallow drawing links from or to this text:
		fromLinkable : false,
		toLinkable : false
	}, new go.Binding("text", "name")), $(go.TextBlock, {
		margin : new go.Margin(0, 2),
		column : 2,
		font : "11px sans-serif"
	}, new go.Binding("text", "info")));

	var palscale = 2;
	var recordNodeTemplate = $(go.Node, "Spot", {
		locationObjectName : "SHAPE",
		locationSpot : go.Spot.Center,
		resizable : true,
		resizeObjectName : "PANEL",
		selectionAdorned : false,
	}, $(go.Panel, "Vertical",
	// this is the header for the whole node
	$(go.Panel, "Auto", {
		stretch : go.GraphObject.Horizontal
	}, // as wide as the whole node
	$(go.Shape, {
		fill : "#1570A6",
		stroke : null
	}), $(go.TextBlock, {
		alignment : go.Spot.Center,
		margin : 3,
		stroke : "white",
		textAlign : "center",
		font : "bold 10pt sans-serif"
	}, new go.Binding("text", "key"))), $(go.Panel, "Table", {
		padding : 2,
		minSize : new go.Size(100, 10),
		defaultStretch : go.GraphObject.Horizontal,
		itemTemplate : fieldTemplate
	}, new go.Binding("itemArray", "fields")) // end Table Panel of items
	) // end Vertical Panel
	); // end go.Node, which is a Spot Panel with bound itemArray

	var recordNodeTemplateForPalette = $(go.Node, "Vertical", {
		locationObjectName : "SHAPE",
		locationSpot : go.Spot.Left,
		selectionAdorned : false
	}, $(go.TextBlock, // the center text
	{
		alignment : go.Spot.Left,
		textAlign : "left",
		font : "bold 8pt sans-serif",
		margin : 2
	}, new go.Binding("text"))); // End Node

	// create the nodeTemplateMap, holding main view node templates:
	var nodeTemplateMap = new go.Map("string", go.Node);
	nodeTemplateMap.add("record", recordNodeTemplate);

	// create the nodeTemplateMap, holding special palette "mini" node
	// templates:
	var palNodeTemplateMap = new go.Map("string", go.Node);
	palNodeTemplateMap.add("record", recordNodeTemplateForPalette);

	// ------------------------------------------the main
	// Diagram----------------------------------------------

	window.dataDiagram = $(go.Diagram, "dataDiagram", {
		nodeTemplateMap : nodeTemplateMap,
		allowDrop : true,
		commandHandler : new DrawCommandHandler(), // defined in
													// DrawCommandHandler.js
		// default to having arrow keys move selected nodes
		"commandHandler.arrowKeyBehavior" : "move",

		mouseDrop : function(e) {
			// when the selection is dropped in the diagram's background,
			// make sure the selected Parts no longer belong to any Group
			var ok = dataDiagram.commandHandler.addTopLevelParts(dataDiagram.selection, true);
			if (!ok)
				dataDiagram.currentTool.doCancel();
		},
		linkingTool : new BPMNLinkingTool(), // defined in BPMNClasses.js
		// set these kinds of Diagram properties after initialization, not now
		"InitialLayoutCompleted" : loadDiagramProperties, // defined below
		"SelectionMoved" : relayoutDiagram, // defined below
		"SelectionCopied" : relayoutDiagram
	});

	// dataDiagram.toolManager.mouseDownTools.insertAt(0, new
	// LaneResizingTool());

	// Make sure the pipes are ordered by their key in the palette inventory
	function keyCompare(a, b) {
		var at = a.data.key;
		var bt = b.data.key;
		if (at < bt)
			return -1;
		if (at > bt)
			return 1;
		return 0;
	}

	// initialize the first Palette, BPMN Spec Level 1
	var dataPaletteLevel1 = $(go.Palette, "dataPaletteLevel1", { // share the
																	// templates
																	// with the
																	// main
																	// Diagram
		nodeTemplateMap : palNodeTemplateMap,
		layout : $(go.GridLayout, {
			cellSize : new go.Size(1, 1),
			spacing : new go.Size(5, 5),
			comparer : keyCompare
		})
	});

	// initialize the second Palette, BPMN Spec Level 2
	var dataPaletteLevel2 = $(go.Palette, "dataPaletteLevel2", {
		nodeTemplateMap : palNodeTemplateMap,
		layout : $(go.GridLayout, {
			cellSize : new go.Size(1, 1),
			spacing : new go.Size(5, 5),
			comparer : keyCompare
		})
	});

	jQuery("#dataAccordion").accordion({
		activate : function(event, ui) {
			dataPaletteLevel1.requestUpdate();
			dataPaletteLevel2.requestUpdate();
		}
	});

	dataPaletteLevel1.model = $(go.GraphLinksModel, {
		copiesArrays : true,
		copiesArrayObjects : true,
		nodeDataArray : zTables
	}); // end model

	dataPaletteLevel2.model = $(go.GraphLinksModel, {
		copiesArrays : true,
		copiesArrayObjects : true,
		nodeDataArray : [ {
			key : "LineChart123",
			category : "record",
			text : "라인차트",
			fields : [ {
				name : "행",
				info : ""
			}, {
				name : "열",
				info : ""
			}, {
				name : "표현값",
				info : ""
			} ],
		}, {
			key : "ColumnChart",
			category : "record",
			text : "컬럼차트",
			fields : [ {
				name : "행",
				info : ""
			}, {
				name : "열",
				info : ""
			}, {
				name : "표현값",
				info : ""
			} ],
		}, {
			key : "PivotTable",
			category : "record",
			text : "피벗테이블",
			fields : [ {
				name : "행",
				info : ""
			}, {
				name : "열",
				info : ""
			}, {
				name : "표현값",
				info : ""
			} ],
		}, {
			key : "CircosChart",
			category : "record",
			text : "다중원형차트",
			fields : [ {
				name : "parent",
				info : ""
			}, {
				name : "parent_Label",
				info : ""
			}, {
				name : "parent_Value",
				info : ""
			}, {
				name : "id",
				info : ""
			}, {
				name : "id_Label",
				info : ""
			}, {
				name : "id_Value1",
				info : ""
			}, {
				name : "id_Value2",
				info : ""
			}],
		},]
	// end nodeDataArray
	}); // end model
	
	// ------------------------------여기서부터 record Mapping
	// --------------------------------------
	// This template represents a whole "record".
	dataDiagram.model = $(go.GraphLinksModel, {
		linkFromPortIdProperty : "fromPort",
		linkToPortIdProperty : "toPort"
	});

	dataDiagram.nodeTemplate = $(go.Node, "Auto", {
		movable : false,
		copyable : false,
		deletable : false
	}, new go.Binding("location", "loc", go.Point.parse).makeTwoWay(go.Point.stringify),
	// this rectangular shape surrounds the content of the node
	$(go.Shape, {
		fill : "#EEEEEE"
	}),
	// the content consists of a header and a list of items
	$(go.Panel, "Vertical",
	// this is the header for the whole node
	$(go.Panel, "Auto", {
		stretch : go.GraphObject.Horizontal
	}, // as wide as the whole node
	$(go.Shape, {
		fill : "#1570A6",
		stroke : null
	}), $(go.TextBlock, {
		alignment : go.Spot.Center,
		margin : 3,
		stroke : "white",
		textAlign : "center",
		font : "bold 8pt sans-serif"
	}, new go.Binding("text", "key"))), $(go.Panel, "Table", {
		padding : 2,
		minSize : new go.Size(100, 10),
		defaultStretch : go.GraphObject.Horizontal,
		itemTemplate : fieldTemplate
	}, new go.Binding("itemArray", "fields")) // end Table Panel of items
	) // end Vertical Panel
	); // end Node

	dataDiagram.linkTemplate = $(go.Link, {
		relinkableFrom : true,
		relinkableTo : true,
		toShortLength : 4
	}, // let user reconnect links
	$(go.Shape, {
		strokeWidth : 1.5
	}), $(go.Shape, {
		toArrow : "Standard",
		stroke : null
	}));

	function loadDiagramProperties(e) {
		var pos = dataDiagram.model.modelData.position;
		if (pos)
			dataDiagram.position = go.Point.parse(pos);
	}

	function LaneResizingTool() {
		go.ResizingTool.call(this);
	}

	go.Diagram.inherit(LaneResizingTool, go.ResizingTool);

	LaneResizingTool.prototype.isLengthening = function() {
		return (this.handle.alignment === go.Spot.Right);
	};

	function relayoutDiagram() {
		dataDiagram.layout.invalidateLayout();
		dataDiagram.findTopLevelGroups().each(function(g) {
			if (g.category === "Pool")
				g.layout.invalidateLayout();
		});
		dataDiagram.layoutDiagram();
	}

} // end init

