"use strict";
function init() {
	var $ = go.GraphObject.make;

	var GradientYellow = $(go.Brush, "Linear", {
		0 : "LightGoldenRodYellow",
		1 : "#FFFF66"
	});
	var GradientLightGreen = $(go.Brush, "Linear", {
		0 : "#E0FEE0",
		1 : "PaleGreen"
	});
	var GradientLightGray = $(go.Brush, "Linear", {
		0 : "White",
		1 : "#DADADA"
	});

	var ActivityNodeFill = $(go.Brush, "Linear", {
		0 : "OldLace",
		1 : "PapayaWhip"
	});
	
	var ActivityNodeStroke = "#CDAA7D";
	var ActivityMarkerStrokeWidth = 1.5;
	var ActivityNodeWidth = 120;
	var ActivityNodeHeight = 40;
	var ActivityNodeStrokeWidth = 1;
	var ActivityNodeStrokeWidthIsCall = 4;

	var DataFill = GradientLightGray;

	// custom figures for Shapes

	go.Shape.defineFigureGenerator("Empty", function(shape, w, h) {
		return new go.Geometry();
	});

	var annotationStr = "M 150,0L 0,0L 0,600L 150,600 M 800,0";
	var annotationGeo = go.Geometry.parse(annotationStr);
	annotationGeo.normalize();
	go.Shape.defineFigureGenerator("Annotation", function(shape, w, h) {
		var geo = annotationGeo.copy();
		// calculate how much to scale the Geometry so that it fits in w x h
		var bounds = geo.bounds;
		var scale = Math.min(w / bounds.width, h / bounds.height);
		geo.scale(scale, scale);
		return geo;
	});

	var gearStr = "F M 391,5L 419,14L 444.5,30.5L 451,120.5L 485.5,126L 522,141L 595,83L 618.5,92L 644,106.5"
			+ "L 660.5,132L 670,158L 616,220L 640.5,265.5L 658.122,317.809L 753.122,322.809L 770.122,348.309L 774.622,374.309"
			+ "L 769.5,402L 756.622,420.309L 659.122,428.809L 640.5,475L 616.5,519.5L 670,573.5L 663,600L 646,626.5"
			+ "L 622,639L 595,645.5L 531.5,597.5L 493.192,613.462L 450,627.5L 444.5,718.5L 421.5,733L 393,740.5L 361.5,733.5"
			+ "L 336.5,719L 330,627.5L 277.5,611.5L 227.5,584.167L 156.5,646L 124.5,641L 102,626.5L 82,602.5L 78.5,572.5"
			+ "L 148.167,500.833L 133.5,466.833L 122,432.5L 26.5,421L 11,400.5L 5,373.5L 12,347.5L 26.5,324L 123.5,317.5"
			+ "L 136.833,274.167L 154,241L 75.5,152.5L 85.5,128.5L 103,105.5L 128.5,88.5001L 154.872,82.4758L 237,155"
			+ "L 280.5,132L 330,121L 336,30L 361,15L 391,5 Z M 398.201,232L 510.201,275L 556.201,385L 505.201,491L 399.201,537"
			+ "L 284.201,489L 242.201,385L 282.201,273L 398.201,232 Z";
	var gearGeo = go.Geometry.parse(gearStr);
	gearGeo.normalize();

	go.Shape.defineFigureGenerator("BpmnTaskService", function(shape, w, h) {
		var geo = gearGeo.copy();
		// calculate how much to scale the Geometry so that it fits in w x h
		var bounds = geo.bounds;
		var scale = Math.min(w / bounds.width, h / bounds.height);
		geo.scale(scale, scale);
		// text should go in the hand
		geo.spot1 = new go.Spot(0, 0.6, 10, 0);
		geo.spot2 = new go.Spot(1, 1);
		return geo;
	});

	var handGeo = go.Geometry.parse("F1M18.13,10.06 C18.18,10.07 18.22,10.07 18.26,10.08 18.91,"
			+ "10.20 21.20,10.12 21.28,12.93 21.36,15.75 21.42,32.40 21.42,32.40 21.42,"
			+ "32.40 21.12,34.10 23.08,33.06 23.08,33.06 22.89,24.76 23.80,24.17 24.72,"
			+ "23.59 26.69,23.81 27.19,24.40 27.69,24.98 28.03,24.97 28.03,33.34 28.03,"
			+ "33.34 29.32,34.54 29.93,33.12 30.47,31.84 29.71,27.11 30.86,26.56 31.80,"
			+ "26.12 34.53,26.12 34.72,28.29 34.94,30.82 34.22,36.12 35.64,35.79 35.64,"
			+ "35.79 36.64,36.08 36.72,34.54 36.80,33.00 37.17,30.15 38.42,29.90 39.67,"
			+ "29.65 41.22,30.20 41.30,32.29 41.39,34.37 42.30,46.69 38.86,55.40 35.75,"
			+ "63.29 36.42,62.62 33.47,63.12 30.76,63.58 26.69,63.12 26.69,63.12 26.69,"
			+ "63.12 17.72,64.45 15.64,57.62 13.55,50.79 10.80,40.95 7.30,38.95 3.80,"
			+ "36.95 4.24,36.37 4.28,35.35 4.32,34.33 7.60,31.25 12.97,35.75 12.97,"
			+ "35.75 16.10,39.79 16.10,42.00 16.10,42.00 15.69,14.30 15.80,12.79 15.96,"
			+ "10.75 17.42,10.04 18.13,10.06z ");
	handGeo.rotate(90, 0, 0);
	handGeo.normalize();
	go.Shape.defineFigureGenerator("BpmnTaskManual", function(shape, w, h) {
		var geo = handGeo.copy();
		// calculate how much to scale the Geometry so that it fits in w x h
		var bounds = geo.bounds;
		var scale = Math.min(w / bounds.width, h / bounds.height);
		geo.scale(scale, scale);
		// guess where text should go (in the hand)
		geo.spot1 = new go.Spot(0, 0.6, 10, 0);
		geo.spot2 = new go.Spot(1, 1);
		return geo;
	});

	// define the appearance of tooltips, shared by various templates
	var tooltiptemplate = $(go.Adornment, go.Panel.Auto, $(go.Shape, "RoundedRectangle", {
		fill : "whitesmoke",
		stroke : "gray"
	}), $(go.TextBlock, {
		margin : 3,
		editable : true
	}, new go.Binding("text", "", function(data) {
		if (data.item !== undefined)
			return data.item;
		return "(unnamed item)";
	})));

	// conversion functions used by data Bindings

	function nodeActivityTaskTypeConverter(s) {
		var tasks = [ "Empty", "BpmnTaskMessage", "BpmnTaskUser", "BpmnTaskManual", // Custom hand symbol
		"BpmnTaskScript", "BpmnTaskMessage", // should be black on white
		"BpmnTaskService", // Custom gear symbol
		"InternalStorage" ];
		if (s < tasks.length)
			return tasks[s];
		return "NotAllowed"; // error
	}

	// location of event on boundary of Activity is based on the index of the event in the boundaryEventArray
	function nodeActivityBESpotConverter(s) {
		var x = 10 + (EventNodeSize / 2);
		if (s === 0)
			return new go.Spot(0, 1, x, 0); // bottom left
		if (s === 1)
			return new go.Spot(1, 1, -x, 0); // bottom right
		if (s === 2)
			return new go.Spot(1, 0, -x, 0); // top right
		return new go.Spot(1, 0, -x - (s - 2) * EventNodeSize, 0); // top ... right-to-left-ish spread
	}

	function nodeActivityTaskTypeColorConverter(s) {
		return (s == 5) ? "dimgray" : "white";
	}

	function nodeEventTypeConverter(s) { // order here from BPMN 2.0 poster
		var tasks = [ "NotAllowed", "Empty", "BpmnTaskMessage", "BpmnEventTimer", "BpmnEventEscalation",
				"BpmnEventConditional", "Arrow", "BpmnEventError", "ThinX", "BpmnActivityCompensation", "Triangle",
				"Pentagon", "ThickCross", "Circle" ];
		if (s < tasks.length)
			return tasks[s];
		return "NotAllowed"; // error
	}

	function nodeEventDimensionStrokeColorConverter(s) {
		if (s === 8)
			return EventDimensionStrokeEndColor;
		return EventDimensionStrokeColor;
	}

	function nodeEventDimensionSymbolFillConverter(s) {
		if (s <= 6)
			return EventSymbolLightFill;
		return EventSymbolDarkFill;
	}

	//------------------------------------------  Activity Node Boundary Events   ----------------------------------------------

	var boundaryEventMenu = // context menu for each boundaryEvent on Activity node
	$(go.Adornment, "Vertical", $("ContextMenuButton", $(go.TextBlock, "Remove event"),
	// in the click event handler, the obj.part is the Adornment; its adornedObject is the port
	{
		click : function(e, obj) {
			removeActivityNodeBoundaryEvent(obj.part.adornedObject);
		}
	}));

	// removing a boundary event doesn't not reposition other BE circles on the node
	// just reassigning alignmentIndex in remaining BE would do that.
	function removeActivityNodeBoundaryEvent(obj) {
		myDiagram.startTransaction("removeBoundaryEvent");
		var pid = obj.portId;
		var arr = obj.panel.itemArray;
		for (var i = 0; i < arr.length; i++) {
			if (arr[i].portId === pid) {
				myDiagram.model.removeArrayItem(arr, i);
				break;
			}
		}
		myDiagram.commitTransaction("removeBoundaryEvent");
	}

	function makeSubButton(sub) {
		if (sub)
			return [ $("SubGraphExpanderButton"), {
				margin : 2,
				visible : false
			}, new go.Binding("visible", "isSubProcess") ];
		return [];
	}

	// sub-process,  loop, parallel, sequential, ad doc and compensation markers in horizontal array
	function makeMarkerPanel(sub, scale) {
		return $(go.Panel, "Horizontal", {
			alignment : go.Spot.MiddleBottom,
			alignmentFocus : go.Spot.MiddleBottom
		}, $(go.Shape, "BpmnActivityLoop", {
			width : 12 / scale,
			height : 12 / scale,
			margin : 2,
			visible : false,
			strokeWidth : ActivityMarkerStrokeWidth
		}, new go.Binding("visible", "isLoop")), $(go.Shape, "BpmnActivityParallel", {
			width : 12 / scale,
			height : 12 / scale,
			margin : 2,
			visible : false,
			strokeWidth : ActivityMarkerStrokeWidth
		}, new go.Binding("visible", "isParallel")), $(go.Shape, "BpmnActivitySequential", {
			width : 12 / scale,
			height : 12 / scale,
			margin : 2,
			visible : false,
			strokeWidth : ActivityMarkerStrokeWidth
		}, new go.Binding("visible", "isSequential")), $(go.Shape, "BpmnActivityAdHoc", {
			width : 12 / scale,
			height : 12 / scale,
			margin : 2,
			visible : false,
			strokeWidth : ActivityMarkerStrokeWidth
		}, new go.Binding("visible", "isAdHoc")), $(go.Shape, "BpmnActivityCompensation", {
			width : 12 / scale,
			height : 12 / scale,
			margin : 2,
			visible : false,
			strokeWidth : ActivityMarkerStrokeWidth,
			fill : null
		}, new go.Binding("visible", "isCompensation")), makeSubButton(sub)); // end activity markers horizontal panel
	}
	
	var activityNodeTemplate = $(go.Node, "Spot", {
			locationObjectName : "SHAPE",
			locationSpot : go.Spot.Center,
			resizable : true,
			resizeObjectName : "PANEL",
			toolTip : tooltiptemplate,
			selectionAdorned : false, 
		}, new go.Binding("itemArray", "boundaryEventArray"), new go.Binding("location", "loc", go.Point.parse)
				.makeTwoWay(go.Point.stringify),
		// move a selected part into the Foreground layer, so it isn"t obscured by any non-selected parts
		new go.Binding("layerName", "isSelected", function(s) {
			return s ? "Foreground" : "";
		}).ofObject(), $(go.Panel, "Auto", {
			name : "PANEL",
			minSize : new go.Size(ActivityNodeWidth, ActivityNodeHeight),
			desiredSize : new go.Size(ActivityNodeWidth, ActivityNodeHeight)
		}, new go.Binding("desiredSize", "size", go.Size.parse).makeTwoWay(go.Size.stringify), 
		$(go.Panel, "Spot", 
			$(go.Shape, "RoundedRectangle", // the outside rounded rectangle
				{
					name : "SHAPE",
					fill : ActivityNodeFill,
					stroke : ActivityNodeStroke,
					parameter1 : 10, // corner size
					portId : "",
					fromLinkable : true,
					toLinkable : true,
					cursor : "pointer",
					fromSpot : go.Spot.RightSide,
					toSpot : go.Spot.LeftSide
				}, new go.Binding("fill", "color"), new go.Binding("strokeWidth", "isCall", function(s) {
					return s ? ActivityNodeStrokeWidthIsCall : ActivityNodeStrokeWidth;
				})),
			makeMarkerPanel(false, 1) // sub-process,  loop, parallel, sequential, ad doc and compensation markers
		), // end main body rectangles spot panel
		$(go.TextBlock, // the center text
		{
			alignment : go.Spot.Center,
			textAlign : "center",
			margin : 12,
			editable : true
		}, new go.Binding("text").makeTwoWay())) // end Auto Panel
	); // end go.Node, which is a Spot Panel with bound itemArray

	function theChartConverter(chartType) {
		return "images/" + chartType.toLowerCase().replace(/\s/g, "-") + ".png";
	}

	var palscale = 2;
	var activityNodeTemplateForPalette = $(go.Node, "Vertical", 
		{
		locationObjectName : "SHAPE",
		locationSpot : go.Spot.Center,
		selectionAdorned : false
		},
		$(go.TextBlock, // the center text
		{
			alignment : go.Spot.Center,
			textAlign : "center",
			margin : 2
		}, new go.Binding("text"))
	); // End Node

	function groupStyle() { // common settings for both Lane and Pool Groups
		return [ {
			layerName : "Background", // all pools and lanes are always behind all nodes and links
			background : "transparent", // can grab anywhere in bounds
			movable : true, // allows users to re-order by dragging
			copyable : false, // can't copy lanes or pools
			avoidable : false
		// don't impede AvoidsNodes routed Links
		}, new go.Binding("location", "loc", go.Point.parse).makeTwoWay(go.Point.stringify) ];
	}

	// hide links between lanes when either lane is collapsed
	function updateCrossLaneLinks(group) {
		group.findExternalLinksConnected().each(function(l) {
			l.visible = (l.fromNode.isVisible() && l.toNode.isVisible());
		});
	}

	// create the nodeTemplateMap, holding main view node templates:
	var nodeTemplateMap = new go.Map("string", go.Node);
	nodeTemplateMap.add("activity", activityNodeTemplate);

	var groupTemplateMap = new go.Map("string", go.Group);

	// create the nodeTemplateMap, holding special palette "mini" node templates:
	var palNodeTemplateMap = new go.Map("string", go.Node);
	palNodeTemplateMap.add("activity", activityNodeTemplateForPalette);

	var palGroupTemplateMap = new go.Map("string", go.Group);

	//------------------------------------------  Link Templates   ----------------------------------------------

	var messageFlowLinkTemplate = $(PoolLink, // defined in BPMNClasses.js
	{
		routing : go.Link.Orthogonal,
		curve : go.Link.JumpGap,
		corner : 10,
		fromSpot : go.Spot.TopBottomSides,
		toSpot : go.Spot.TopBottomSides,
		reshapable : true,
		relinkableTo : true,
		toEndSegmentLength : 20
	}, new go.Binding("points").makeTwoWay(), $(go.Shape, {
		stroke : "black",
		strokeWidth : 1,
		strokeDashArray : [ 6, 2 ]
	}), $(go.Shape, {
		toArrow : "Triangle",
		scale : 1,
		fill : "white",
		stroke : "black"
	}), $(go.Shape, {
		fromArrow : "Circle",
		scale : 1,
		visible : true,
		stroke : "black",
		fill : "white"
	}), $(go.TextBlock, {
		editable : true,
		text : "label"
	}, // Link label
	new go.Binding("text", "text").makeTwoWay()));

	var dataAssociationLinkTemplate = $(go.Link, {
		routing : go.Link.AvoidsNodes,
		curve : go.Link.JumpGap,
		corner : 10,
		fromSpot : go.Spot.AllSides,
		toSpot : go.Spot.AllSides,
		reshapable : true,
		relinkableFrom : true,
		relinkableTo : true
	}, new go.Binding("points").makeTwoWay(), $(go.Shape, {
		stroke : "black",
		strokeWidth : 1,
		strokeDashArray : [ 1, 3 ]
	}), $(go.Shape, {
		toArrow : "OpenTriangle",
		scale : 1,
		fill : null,
		stroke : "blue"
	}));

	var annotationAssociationLinkTemplate = $(go.Link, {
		reshapable : true,
		relinkableFrom : true,
		relinkableTo : true,
		toSpot : go.Spot.AllSides,
		toEndSegmentLength : 20,
		fromEndSegmentLength : 40
	}, new go.Binding("points").makeTwoWay(), $(go.Shape, {
		stroke : "black",
		strokeWidth : 1,
		strokeDashArray : [ 1, 3 ]
	}), $(go.Shape, {
		toArrow : "OpenTriangle",
		scale : 1,
		stroke : "black"
	}));

	var linkTemplateMap = new go.Map("string", go.Link);
	linkTemplateMap.add("msg", messageFlowLinkTemplate);
	linkTemplateMap.add("annotation", annotationAssociationLinkTemplate);
	linkTemplateMap.add("data", dataAssociationLinkTemplate);

	//------------------------------------------the main Diagram----------------------------------------------

	window.myDiagram = $(go.Diagram, "myDiagram", {
		nodeTemplateMap : nodeTemplateMap,
		linkTemplateMap : linkTemplateMap,
		groupTemplateMap : groupTemplateMap,

		allowDrop : true, // accept drops from palette

		commandHandler : new DrawCommandHandler(), // defined in DrawCommandHandler.js
		// default to having arrow keys move selected nodes
		"commandHandler.arrowKeyBehavior" : "move",

		mouseDrop : function(e) {
			// when the selection is dropped in the diagram's background,
			// make sure the selected Parts no longer belong to any Group
			var ok = myDiagram.commandHandler.addTopLevelParts(myDiagram.selection, true);
			if (!ok)
				myDiagram.currentTool.doCancel();
		},
		linkingTool : new BPMNLinkingTool(), // defined in BPMNClasses.js
		// set these kinds of Diagram properties after initialization, not now
		"InitialLayoutCompleted" : loadDiagramProperties, // defined below
		"SelectionMoved" : relayoutDiagram, // defined below
		"SelectionCopied" : relayoutDiagram
	});

	myDiagram.toolManager.mouseDownTools.insertAt(0, new LaneResizingTool());

	myDiagram.addDiagramListener("LinkDrawn", function(e) {
		if (e.subject.fromNode.category === "annotation") {
			e.subject.category = "annotation"; // annotation association
		} else if (e.subject.fromNode.category === "dataobject" || e.subject.toNode.category === "dataobject") {
			e.subject.category = "data"; // data association
		} else if (e.subject.fromNode.category === "datastore" || e.subject.toNode.category === "datastore") {
			e.subject.category = "data"; // data association
		}
	});

	// change the title to indicate that the diagram has been modified
	myDiagram.addDiagramListener("Modified", function(e) {
		var currentFile = document.getElementById("currentFile");
		var idx = currentFile.textContent.indexOf("*");
		if (myDiagram.isModified) {
			if (idx < 0)
				currentFile.textContent = currentFile.textContent + "*";
		} else {
			if (idx >= 0)
				currentFile.textContent = currentFile.textContent.substr(0, idx);
		}
	});

	//------------------------------------------  Palette   ----------------------------------------------

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
	var myPaletteLevel1 = $(go.Palette, "myPaletteLevel1", { // share the templates with the main Diagram
		nodeTemplateMap : palNodeTemplateMap,
		groupTemplateMap : palGroupTemplateMap,
		layout : $(go.GridLayout, {
			cellSize : new go.Size(1, 1),
			spacing : new go.Size(5, 5),
			comparer : keyCompare
		})
	});

	// initialize the second Palette, BPMN Spec Level 2
	var myPaletteLevel2 = $(go.Palette, "myPaletteLevel2", { // share the templates with the main Diagram
		nodeTemplateMap : palNodeTemplateMap,
		groupTemplateMap : palGroupTemplateMap,
		layout : $(go.GridLayout, {
			cellSize : new go.Size(1, 1),
			spacing : new go.Size(5, 5),
			comparer : keyCompare
		})
	});

	jQuery("#accordion").accordion({
		activate : function(event, ui) {
			myPaletteLevel1.requestUpdate();
			myPaletteLevel2.requestUpdate();
		}
	});

	myPaletteLevel1.model = $(go.GraphLinksModel, {
		copiesArrays : true,
		copiesArrayObjects : true,
		nodeDataArray : [
		// -------------------------- Event Nodes
		{
			key : 1,
			category : "activity",
			taskType : 1,
			text : "Receive Task",
			item : "Receive Task"
		}, {
			key : 2,
			category : "activity",
			taskType : 1,
			text : "Receive Task",
			item : "Receive Task"
		}, // BpmnTaskMessage
		{
			key : 3,
			category : "activity",
			taskType : 1,
			text : "Receive Task",
			item : "Receive Task"
		} ]
	// end nodeDataArray
	}); // end model

	myPaletteLevel2.model = $(go.GraphLinksModel, {
		copiesArrays : true,
		copiesArrayObjects : true,
		nodeDataArray : [ {
			key : 4,
			category : "activity",
			taskType : 1,
			text : "Receive Task",
			item : "Receive Task"
		} ]
	// end nodeDataArray
	}); // end model

	//------------------------------여기서부터 record Mapping --------------------------------------
	var fieldTemplate = $(go.Panel, "TableRow", // this Panel is a row in the containing Table
	new go.Binding("portId", "name"), // this Panel is a "port"
	{
		background : "transparent", // so this port's background can be picked by the mouse
		fromSpot : go.Spot.Right, // links only go from the right side to the left side
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
		font : "bold 13px sans-serif",
		// and disallow drawing links from or to this text:
		fromLinkable : false,
		toLinkable : false
	}, new go.Binding("text", "name")), $(go.TextBlock, {
		margin : new go.Margin(0, 2),
		column : 2,
		font : "13px sans-serif"
	}, new go.Binding("text", "info")));

	// This template represents a whole "record".
	myDiagram.nodeTemplate = $(go.Node, "Auto", 
		{
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
			$(go.Panel, "Auto", 
				{
					stretch : go.GraphObject.Horizontal
				}, // as wide as the whole node
				$(go.Shape, {
						fill : "#1570A6",
						stroke : null
					}), 
				$(go.TextBlock, {
						alignment : go.Spot.Center,
						margin : 3,
						stroke : "white",
						textAlign : "center",
						font : "bold 12pt sans-serif"
					}, new go.Binding("text", "key")
				)
			),
			$(go.Panel, "Table", {
					padding : 2,
					minSize : new go.Size(100, 10),
					defaultStretch : go.GraphObject.Horizontal,
					itemTemplate : fieldTemplate
				}, new go.Binding("itemArray", "fields")
			) // end Table Panel of items
		) // end Vertical Panel
	); // end Node

	myDiagram.linkTemplate = $(go.Link, {
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

	myDiagram.model = $(go.GraphLinksModel, {
		linkFromPortIdProperty : "fromPort",
		linkToPortIdProperty : "toPort",
		nodeDataArray : [ {
			key : "Record1",
			fields : [ {
				name : "field1",
				info : "",
				color : "#F7B84B",
				figure : "Ellipse"
			}, {
				name : "field2",
				info : "the second one",
				color : "#F25022",
				figure : "Ellipse"
			}, {
				name : "fieldThree",
				info : "3rd",
				color : "#00BCF2"
			} ],
			loc : "0 0"
		}, {
			key : "Record2",
			fields : [ {
				name : "fieldA",
				info : "",
				color : "#FFB900",
				figure : "Diamond"
			}, {
				name : "fieldB",
				info : "",
				color : "#F25022",
				figure : "Rectangle"
			}, {
				name : "fieldC",
				info : "",
				color : "#7FBA00",
				figure : "Diamond"
			}, {
				name : "fieldD",
				info : "fourth",
				color : "#00BCF2",
				figure : "Rectangle"
			} ],
			loc : "250 0"
		} ],
		linkDataArray : [ {
			from : "Record1",
			fromPort : "field1",
			to : "Record2",
			toPort : "fieldA"
		}, {
			from : "Record1",
			fromPort : "field2",
			to : "Record2",
			toPort : "fieldD"
		}, {
			from : "Record1",
			fromPort : "fieldThree",
			to : "Record2",
			toPort : "fieldB"
		} ]
	});

	// automatically update the model that is shown on this page
	myDiagram.model.addChangedListener(function(e) {
		if (e.isTransactionFinished)
			showModel();
	});

	showModel(); // show the diagram's initial model

	function showModel() {
		document.getElementById("mySavedModel").textContent = myDiagram.model.toJson();
	}

	//------------------------------여기서부터 record Mapping --------------------------------------	

} // end init

function loadDiagramProperties(e) {
	var pos = myDiagram.model.modelData.position;
	if (pos)
		myDiagram.position = go.Point.parse(pos);
}

function LaneResizingTool() {
	go.ResizingTool.call(this);
}
go.Diagram.inherit(LaneResizingTool, go.ResizingTool);

LaneResizingTool.prototype.isLengthening = function() {
	return (this.handle.alignment === go.Spot.Right);
};

function relayoutDiagram() {
	myDiagram.layout.invalidateLayout();
	myDiagram.findTopLevelGroups().each(function(g) {
		if (g.category === "Pool")
			g.layout.invalidateLayout();
	});
	myDiagram.layoutDiagram();
}
