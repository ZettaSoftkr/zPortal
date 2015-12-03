$(function() { // on dom ready
	
	//initial
	init();
});

function init(){
	getCytoscapeRootNode("#" , "교통안전공단", 0);
}


function getCytoscapeRootNode(rootId, rootName, depth) {

	var fullUrl = "/portal/visualization/cytoscape.do";

	$.ajax({
		url : fullUrl,
		type : "GET",
		data: {rootId:rootId,rootName:rootName,depth:depth},
        contentType: "application/json; charset=utf-8",
		dataType : 'json',
		beforeSend : function() {
		},
		success : function(data) {
			cy = cytoscape({
				container : document.getElementById('cy'),

				style : [ {
					selector : 'node',
					style : {
						'content' : 'data(name)',
						'text-valign' : 'center',
						'color' : 'white',
						'text-outline-width' : 5,
						'text-outline-color' : '#888',
						'width' : 80,
						'height' : 80
					}
				},

				{
					selector : 'edge',
					style : {
						'content' : 'data(name)',
						'width' : 8,
						'line-color' : '#888',
						'target-arrow-color' : '#888',
						'source-arrow-color' : '#888',
						'target-arrow-shape' : 'triangle'
					}
				}, {
					selector : ':selected',
					style : {
						'background-color' : 'black',
						'line-color' : 'black',
						'target-arrow-color' : 'black',
						'source-arrow-color' : 'black',
						'text-outline-color' : 'black'
					}
				},

				{
					selector : '.NodeClass1',
					style : {
						'shape' : 'roundrectangle',
						'text-valign' : 'top',
						'background-color' : '#ccc',
						'background-opacity' : 0.333,
						'color' : '#888',
						'text-outline-width' : 0,
						'font-size' : 25
					}
				},

				{
					selector : '.NodeClass2',
					style : {
						'shape' : 'roundrectangle',
						'text-valign' : 'top',
						'background-color' : '#ccc',
						'background-opacity' : 0.333,
						'color' : '#888',
						'text-outline-width' : 0,
						'font-size' : 25
					}
				},

				{
					selector : '.NodeClass3',
					style : {
						'shape' : 'roundrectangle',
						'text-valign' : 'top',
						'background-color' : '#ccc',
						'background-opacity' : 0.333,
						'color' : '#888',
						'text-outline-width' : 0,
						'font-size' : 25
					}
				},

				{
					selector : '#rootNode',
					style : {
						'background-opacity' : 0,
						'border-width' : 1,
						'border-color' : '#aaa',
						'border-opacity' : 0.5,
						'font-size' : 50,
						'padding-top' : 40,
						'padding-left' : 40,
						'padding-bottom' : 40,
						'padding-right' : 40
					}
				} ],

				elements : jQuery.parseJSON(data),

				layout : {
					name : 'preset'
				}
			});

			cy.cxtmenu({
				selector : 'node',

				commands : [ {
					content : '루트로 열기',
					select : function() {
						getCytoscapeRootNode(this.id() , this.data('name'), 0);
					}
				},{
					content : '처음으로',
					select : function() {
						init();
					}
				},
				{
					content : '열기',
					select : function() {
						
					}
				},
				{
					content : '닫기',
					select : function() {
						
					}
				}
				,
				{
					content : '닫기12',
					select : function() {
						
					}
				}
				,
				{
					content : '보고서로이동',
					select : function() {
						document.location.href("http://www.naver.com");
					}
				}]
			});

			var options = {
				name : 'cose',
				// Called on `layoutready`
				ready : function() {
				},
				// Called on `layoutstop`
				stop : function() {
				},
				// Whether to animate while running the layout
				animate : true,
				// The layout animates only after this many milliseconds
				// (prevents flashing on fast runs)
				animationThreshold : 250,
				// Number of iterations between consecutive screen positions update
				// (0 -> only updated on the end)
				refresh : 20,
				// Whether to fit the network view after when done
				fit : true,
				// Padding on fit
				padding : 30,
				// Constrain layout bounds; { x1, y1, x2, y2 } or { x1, y1, w, h }
				boundingBox : undefined,
				// Extra spacing between components in non-compound graphs
				componentSpacing : 100,
				// Node repulsion (non overlapping) multiplier
				nodeRepulsion : function(node) {
					return 400000;
				},
				// Node repulsion (overlapping) multiplier
				nodeOverlap : 10,
				// Ideal edge (non nested) length
				idealEdgeLength : function(edge) {
					return 10;
				},
				// Divisor to compute edge forces
				edgeElasticity : function(edge) {
					return 100;
				},
				// Nesting factor (multiplier) to compute ideal edge length for nested edges
				nestingFactor : 5,
				// Gravity force (constant)
				gravity : 80,
				// Maximum number of iterations to perform
				numIter : 1000,
				// Initial temperature (maximum node displacement)
				initialTemp : 200,
				// Cooling factor (how the temperature is reduced between consecutive iterations
				coolingFactor : 0.95,
				// Lower temperature threshold (below this point the layout will end)
				minTemp : 1.0,
				// Whether to use threading to speed up the layout
				useMultitasking : true
			};

			//	 				var options = {
			//	 				  name: 'breadthfirst',

			//	 				  fit: true, // whether to fit the viewport to the graph
			//	 				  directed: false, // whether the tree is directed downwards (or edges can point in any direction if false)
			//	 				  padding: 30, // padding on fit
			//	 				  circle: false, // put depths in concentric circles if true, put depths top down if false
			//	 				  spacingFactor: 1.75, // positive spacing factor, larger => more space between nodes (N.B. n/a if causes overlap)
			//	 				  boundingBox: undefined, // constrain layout bounds; { x1, y1, x2, y2 } or { x1, y1, w, h }
			//	 				  avoidOverlap: true, // prevents node overlap, may overflow boundingBox if not enough space
			//	 				  roots: undefined, // the roots of the trees
			//	 				  maximalAdjustments: 0, // how many times to try to position the nodes in a maximal way (i.e. no backtracking)
			//	 				  animate: false, // whether to transition the node positions
			//	 				  animationDuration: 500, // duration of animation in ms if enabled
			//	 				  animationEasing: undefined, // easing of animation if enabled
			//	 				  ready: undefined, // callback on layoutready
			//	 				  stop: undefined // callback on layoutstop
			//	 				};

			cy.layout(options);
		},
		error : function(request, status, error) {
			alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
		}

	});

}