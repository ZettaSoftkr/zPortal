"use strict";

function getDbTableInfo(vType) {
    var sendInfo = {
        fileName: "process\\" + fileName,
        objectKey: objectKey
    };

    jQuery.ajax({
        type: "POST",
        url: "/zPortal/modelManager/getDbTableInfo.do",
        dataType: "json",
        success: function(msg) {
            dataSet = [];
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

            var tableData = new Array();

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
                tableData.push(table);
            }

            var data = null;
            if (vType == "record") {
                data = {
                    fileName: "system",
                    objectKey: "VRecordMapper"
                };
            } else {
                data = {
                    fileName: "system",
                    objectKey: "VVisualizationMapper"
                };
            }

            jQuery.ajax({
                type: "POST",
                url: "/zPortal/modelManager/load.do",
                dataType: "json",
                success: function(msg) {
                    if (msg[0] == "notFound") {
                        alert("파일정보가 없습니다.");
                        return;
                    } else {
                        loadRecordMapper(tableData, msg, vType);
                    }
                },
                error: function(request, status, error) {
                    alert("request:" + request.responseText + "\n error:" + error);
                },
                data: data
            });

        },
        error: function(request, status, error) {
            alert("request:" + request.responseText + "\n error:" + error);
        },
        data: sendInfo
    });
}

function getObjectInfo(vType) {

    jQuery.ajax({
        type: "POST",
        url: "/zPortal/modelManager/load.do",
        dataType: "json",
        success: function(msg) {
            if (msg[0] == "notFound") {
                alert("파일정보가 없습니다.");
                return;
            } else {
                dataSet = msg;
                //data형태가 2가지 일수 있다.
                
                var tableData = new Array();
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

                if(dataSet[0].tableName){
	                for (var i = 0; i < dataSet.length; i++) {
	                    var fields = new Array();
	                    var table = new zTable();
	                    table.key = dataSet[i].tableName;
	                    table.category = "record";
	                    table.text = dataSet[i].tableName;
	
	                    var header = dataSet[i].data[0];
	                    for (var j = 0; j < header.length; j++) {
	                        var field = new zField(header[j], header[j], "string", "#F7B84B", "Ellipse");
	                        fields.push(field);
	                    }
	                    table.fields = fields;
	                    tableData.push(table);
	                }
                }else{
                    var fields = new Array();
                    var table = new zTable();
                    table.key = "결과";
                    table.category = "record";
                    table.text = "결과";

                    var header = dataSet[0];
                    for (var j = 0; j < header.length; j++) {
                        var field = new zField(header[j], header[j], "string", "#F7B84B", "Ellipse");
                        fields.push(field);
                    }
                    table.fields = fields;
                    tableData.push(table);
                }

                var data = null;
                if (vType == "record") {
                    data = {
                        fileName: "system",
                        objectKey: "VRecordMapper"
                    };
                } else {
                    data = {
                        fileName: "system",
                        objectKey: "VVisualizationMapper"
                    };
                }

                jQuery.ajax({
                    type: "POST",
                    url: "/zPortal/modelManager/load.do",
                    dataType: "json",
                    success: function(msg) {
                        if (msg[0] == "notFound") {
                            alert("파일정보가 없습니다.");
                            return;
                        } else {
                        	console.info("msg" , JSON.stringify(msg));
                            loadRecordMapper(tableData, msg);
                        }
                    },
                    error: function(request, status, error) {
                        alert("request:" + request.responseText + "\n error:" + error);
                    },
                    data: data
                });
            }
        },
        error: function(request, status, error) {
            alert("request:" + request.responseText + "\n error:" + error);
        },
        data: {
            fileName: "process\\" + fileName,
            objectKey: "data_" + parentKey
        }
    });
}

function saveRecordMapper() {
//    if (!myDiagram.isModified)
//        return;
    saveDiagramProperties();

    var sendInfo = {
        fileName: "process\\" + fileName,
        objectKey: objectKey,
        content: window.myDiagram.model.toJson()
    };
    
    //데이터를 구성한다.
    if(dataSet==null){
    	//ODBC이니경우 데이러를 어떻게 만들까?
    }else{
    	dataProcessing(dataSet, window.myDiagram.model, fileName, objectKey);
    }

    jQuery.ajax({
        type: "POST",
        url: "/zPortal/modelManager/save.do",
        dataType: "json",
        success: function(msg) {
            if (msg) {
                myDiagram.model.undoManager.isEnabled = true;
                myDiagram.isModified = false;
                alert("저장되었습니다.");
            } else {
                alert("저장할수 없습니다.");
            }
        },
        error: function(request, status, error) {
            alert("request:" + request.responseText + "\n error:" + error);
        },
        data: sendInfo
    });
}

function loadRecordMapper(zTables, zObjects) {
    var sendInfo = {
        fileName: "process\\" + fileName,
        objectKey: objectKey
    };

    jQuery.ajax({
        type: "POST",
        url: "/zPortal/modelManager/load.do",
        dataType: "json",
        success: function(msg) {
            zRecordMapper(zTables, zObjects);

            if (msg[0] != "notFound") {
                myDiagram.model = go.Model.fromJson(msg);
            } else {
                myDiagram.model = $(go.GraphLinksModel, {
                    linkFromPortIdProperty: "fromPort",
                    linkToPortIdProperty: "toPort"
                });
                
                //model에 Default값을 넣는 방법을 고민해야한다.
            }
        },
        error: function(request, status, error) {
            alert("request:" + request.responseText + "\n error:" + error);
        },
        data: sendInfo
    });
}

function zRecordMapper(zTables, zObjects) {
    var $ = go.GraphObject.make;

    var fieldTemplate = $(go.Panel, "TableRow", new go.Binding("portId", "name"), {
        background: "transparent",
        fromSpot: go.Spot.Right,
        toSpot: go.Spot.Left,
        fromLinkable: true,
        toLinkable: true
    }, $(go.Shape, {
        width: 12,
        height: 12,
        column: 0,
        strokeWidth: 2,
        margin: 4,
        fromLinkable: false,
        toLinkable: false
    }, new go.Binding("figure", "figure"), new go.Binding("fill", "color")), $(go.TextBlock, {
        margin: new go.Margin(0, 2),
        column: 1,
        font: "bold 11px sans-serif",
        fromLinkable: false,
        toLinkable: false
    }, new go.Binding("text", "name")), $(go.TextBlock, {
        margin: new go.Margin(0, 2),
        column: 2,
        font: "11px sans-serif"
    }, new go.Binding("text", "info")));
    
    var dataTemplate = $(go.Panel, "TableRow", new go.Binding("portId", "name"), {
        background: "transparent",
        fromSpot: go.Spot.Right,
        toSpot: go.Spot.Left,
        fromLinkable: true,
        toLinkable: true
    }, $(go.Shape, {
        width: 12,
        height: 12,
        column: 0,
        strokeWidth: 2,
        margin: 4,
        fromLinkable: false,
        toLinkable: false
    }, new go.Binding("figure", "figure"), new go.Binding("fill", "color")), $(go.TextBlock, {
        margin: new go.Margin(0, 2),
        column: 1,
        font: "bold 11px sans-serif",
        fromLinkable: false,
        toLinkable: false
    }, new go.Binding("text", "name")), $(go.TextBlock, {
        margin: new go.Margin(0, 2),
        column: 2,
        font: "11px sans-serif"
    }, new go.Binding("text", "info")));


    var palscale = 2;
    
    var recordNodeTemplate = $(go.Node, "Spot", {
            locationObjectName: "SHAPE",
            locationSpot: go.Spot.Center,
            resizable: true,
            resizeObjectName: "PANEL",
            selectionAdorned: false,
        }, new go.Binding("position", "pos", go.Point.parse).makeTwoWay(go.Point.stringify), $(go.Panel, "Vertical",
            // this is the header for the whole node
            $(go.Panel, "Auto", {
                    stretch: go.GraphObject.Horizontal
                }, // as wide as the whole node
                $(go.Shape, {
                    fill: "#1570A6",
                    stroke: null
                }), $(go.TextBlock, {
                    alignment: go.Spot.Center,
                    margin: 3,
                    stroke: "white",
                    textAlign: "center",
                    font: "bold 10pt sans-serif"
                }, new go.Binding("text", "key"))), $(go.Panel, "Table", {
                padding: 2,
                minSize: new go.Size(100, 10),
                defaultStretch: go.GraphObject.Horizontal,
                itemTemplate: fieldTemplate
            }, new go.Binding("itemArray", "fields")) // end Table Panel of items
        ) // end Vertical Panel
    ); // end go.Node, which is a Spot Panel with bound itemArray

    var recordNodeTemplateForPalette = $(go.Node, "Vertical", {
        locationObjectName: "SHAPE",
        locationSpot: go.Spot.Left,
        selectionAdorned: false
    }, $(go.TextBlock, // the center text
        {
            alignment: go.Spot.Left,
            textAlign: "left",
            font: "bold 8pt sans-serif",
            margin: 2
        }, new go.Binding("text"))); // End Node

    var objectNodeTemplate = $(go.Node, "Spot", {
            locationObjectName: "SHAPE",
            locationSpot: go.Spot.Center,
            resizable: true,
            resizeObjectName: "PANEL",
            selectionAdorned: false,
        }, new go.Binding("position", "pos", go.Point.parse).makeTwoWay(go.Point.stringify), $(go.Panel, "Vertical",
            // this is the header for the whole node
            $(go.Panel, "Auto", {
                    stretch: go.GraphObject.Horizontal
                }, // as wide as the whole node
                $(go.Shape, {
                    fill: "#1570A6",
                    stroke: null
                }), $(go.TextBlock, {
                    alignment: go.Spot.Center,
                    margin: 3,
                    stroke: "white",
                    textAlign: "center",
                    font: "bold 10pt sans-serif"
                }, new go.Binding("text", "key"))), $(go.Panel, "Table", {
                padding: 2,
                minSize: new go.Size(100, 10),
                defaultStretch: go.GraphObject.Horizontal,
                itemTemplate: dataTemplate
            }, new go.Binding("itemArray", "data")) // end Table Panel of items
        ) // end Vertical Panel
    ); // end go.Node, which is a Spot Panel with bound itemArray

    var objectNodeTemplateForPalette = $(go.Node, "Vertical", {
        locationObjectName: "SHAPE",
        locationSpot: go.Spot.Left,
        selectionAdorned: false
    }, $(go.TextBlock, // the center text
        {
            alignment: go.Spot.Left,
            textAlign: "left",
            font: "bold 8pt sans-serif",
            margin: 2
        }, new go.Binding("text"))); // End Node

    // create the nodeTemplateMap, holding main view node templates:
    var nodeTemplateMap = new go.Map("string", go.Node);
    nodeTemplateMap.add("record", recordNodeTemplate);
    nodeTemplateMap.add("object", objectNodeTemplate);

    // create the nodeTemplateMap, holding special palette "mini" node
    // templates:
    var palNodeTemplateMap = new go.Map("string", go.Node);
    palNodeTemplateMap.add("record", recordNodeTemplateForPalette);
    palNodeTemplateMap.add("object", objectNodeTemplateForPalette);

    // ------------------------------------------the main
    // Diagram----------------------------------------------

    window.myDiagram = $(go.Diagram, "myDiagram", {
        nodeTemplateMap: nodeTemplateMap,
        allowDrop: true,
        commandHandler: new DrawCommandHandler(),
        "commandHandler.arrowKeyBehavior": "move",
        mouseDrop: function(e) {
            var ok = myDiagram.commandHandler.addTopLevelParts(myDiagram.selection, true);
            if (!ok)
                myDiagram.currentTool.doCancel();
        },
        linkingTool: new BPMNLinkingTool(),
        "InitialLayoutCompleted": loadDiagramProperties,
        "SelectionMoved": relayoutDiagram,
        "SelectionCopied": relayoutDiagram
    });

//    // Make sure the pipes are ordered by their key in the palette inventory
//    function keyCompare(a, b) {
//        var bt = b.data.key;
//        if (at < bt)
//            return -1;
//        if (at > bt)
//            return 1;
//        return 0;
//    }

    // initialize the first Palette, BPMN Spec Level 1
    var dataPaletteLevel1 = $(go.Palette, "dataPaletteLevel1", {
        nodeTemplateMap: palNodeTemplateMap,
        layout: $(go.GridLayout, {
            cellSize: new go.Size(1, 1),
            spacing: new go.Size(5, 5)
        })
    });

    // initialize the second Palette, BPMN Spec Level 2
    var dataPaletteLevel2 = $(go.Palette, "dataPaletteLevel2", {
        nodeTemplateMap: palNodeTemplateMap,
        layout: $(go.GridLayout, {
            cellSize: new go.Size(1, 1),
            spacing: new go.Size(5, 5)
        })
    });

    jQuery("#accordion").accordion({
        activate: function(event, ui) {
            dataPaletteLevel1.requestUpdate();
            dataPaletteLevel2.requestUpdate();
        }
    });

    dataPaletteLevel1.model = $(go.GraphLinksModel, {
        copiesArrays: true,
        copiesArrayObjects: true,
        nodeDataArray: zTables
    }); // end model

    dataPaletteLevel2.model = $(go.GraphLinksModel, {
        copiesArrays: true,
        copiesArrayObjects: true,
        nodeDataArray: zObjects
            // end nodeDataArray
    }); // end model

    // ------------------------------여기서부터 record Mapping
    // --------------------------------------
    // This template represents a whole "record".
    myDiagram.model = $(go.GraphLinksModel, {
        linkFromPortIdProperty: "fromPort",
        linkToPortIdProperty: "toPort"
    });

    myDiagram.nodeTemplate = $(go.Node, "Auto", {
            movable: false,
            copyable: false,
            deletable: false
        }, new go.Binding("location", "loc", go.Point.parse).makeTwoWay(go.Point.stringify),
        // this rectangular shape surrounds the content of the node
        $(go.Shape, {
            fill: "#EEEEEE"
        }),
        // the content consists of a header and a list of items
        $(go.Panel, "Vertical",
            // this is the header for the whole node
            $(go.Panel, "Auto", {
                    stretch: go.GraphObject.Horizontal
                }, // as wide as the whole node
                $(go.Shape, {
                    fill: "#1570A6",
                    stroke: null
                }), $(go.TextBlock, {
                    alignment: go.Spot.Center,
                    margin: 3,
                    stroke: "white",
                    textAlign: "center",
                    font: "bold 8pt sans-serif"
                }, new go.Binding("text", "key"))), $(go.Panel, "Table", {
                padding: 2,
                minSize: new go.Size(100, 10),
                defaultStretch: go.GraphObject.Horizontal,
                itemTemplate: fieldTemplate
            }, new go.Binding("itemArray", "fields")) // end Table Panel of items
        ) // end Vertical Panel
    ); // end Node

    myDiagram.linkTemplate = $(go.Link, {
            relinkableFrom: true,
            relinkableTo: true,
            toShortLength: 4
        }, // let user reconnect links
        $(go.Shape, {
            strokeWidth: 1.5
        }), $(go.Shape, {
            toArrow: "Standard",
            stroke: null
        }));

    function loadDiagramProperties(e) {
        var pos = myDiagram.model.modelData.position;
        if (pos)
            myDiagram.position = go.Point.parse(pos);
    }

    function relayoutDiagram() {
        myDiagram.layout.invalidateLayout();
        myDiagram.layoutDiagram();
    }

    var myOverview = $(go.Overview, "myOverview", {
        observed: myDiagram,
        maxScale: 0.5,
        contentAlignment: go.Spot.Center
    });
    // change color of viewport border in Overview
    myOverview.box.elt(0).stroke = "dodgerblue";

    window.onresize = function() {
        jQuery("#palette").height(window.innerHeight * 0.85);
        jQuery("#palette").height(window.innerHeight * 0.20);
        jQuery("#myDiagram").height(window.innerHeight * 0.85);
        jQuery("#myDiagram").width(window.innerWidth * 0.75);
        relayoutDiagram();
    };

    window.onresize();
} // end init

function layout() {
    myDiagram.layout.invalidateLayout();
    myDiagram.layoutDiagram();
}

function saveDiagramProperties() {
    myDiagram.model.modelData.position = go.Point.stringify(myDiagram.position);
}