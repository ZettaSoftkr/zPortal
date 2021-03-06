if( typeof jQuery == 'undefined') {
	Qva.LoadScript('/QvAjaxZfc/QvsViewClient.aspx?public=only&name=Extensions/wordCloud/jquery-2.1.4.min.js', function() {
		Qva.LoadScript('/QvAjaxZfc/QvsViewClient.aspx?public=only&name=Extensions/wordCloud/d3.js', function() {
			Qva.LoadScript('/QvAjaxZfc/QvsViewClient.aspx?public=only&name=Extensions/wordCloud/d3.layout.cloud.js', loadThem);
		});
	});
} else {
	Qva.LoadScript('/QvAjaxZfc/QvsViewClient.aspx?public=only&name=Extensions/wordCloud/d3.js', function() {
		Qva.LoadScript('/QvAjaxZfc/QvsViewClient.aspx?public=only&name=Extensions/wordCloud/d3.layout.cloud.js', loadThem);
	});
}
function AtoR(s){
	var news = "";
	news = "RGB(" + s.split(",")[1] + "," +  + s.split(",")[2] + "," + s.split(",")[3];
	return news;
}
function loadThem() {
	Qva.AddExtension('wordCloud', function() {
		var thisPath = Qva.Remote + "?public=only&name=Extensions/wordCloud/";
		var _this = this;
		var divName = _this.Layout.ObjectId.replace("\\", "_");
		if(_this.Element.children.length == 0) {//if this div doesn't already exist, create a unique div with the divName
			var ui = document.createElement("div");
			ui.setAttribute("id", divName);
			_this.Element.appendChild(ui);
		} else {
			$("#" + divName).empty();
			//if it does exist, empty it
		}
		var maxVal = 0;

		var minT = 10;
		var maxT = 60;
		if(_this.Layout.Text0.text.toString()){
			minT = parseFloat(_this.Layout.Text0.text.toString());
		}
		if(_this.Layout.Text1.text.toString()){
			maxT = parseFloat(_this.Layout.Text1.text.toString());
		}
		var td = _this.Data;
		var lists = [];
		
		//console.info("len:" + td.Rows.length);
		
		for(var rowIx = 0; rowIx < td.Rows.length; rowIx++) {
			var row = td.Rows[rowIx];
			var list = [];
			list.push(row[0].text);
			list.push(parseFloat(row[1].text));
			list.push(row[2].text);
			lists.push(list);
		}
		
		console.info("2");
		lists.sort(function(a, b){
		    var a1= a[1], b1= b[1];
		    if(a1 == b1) return 0;
		    return b1> a1? 1: -1;
		});
		
		//console.info("sorted!!");
		
		var wordSet = [];
		var colorSet = {};
		
		//console.info("length::" + lists.length);
		var lastLength = 30;
		if(lists.length < lastLength) {
			lastLength = lists.length;
		}
		console.info("lastLength::" + lastLength);
		for(var rowIx = 0; rowIx < lastLength; rowIx++) {
			
			var row = lists[rowIx];
			var cVal = parseFloat(row[1]);
			
			//console.info("rowIx::" + rowIx + "    cVal::" + cVal);
			
			wordSet.push(row[0]);
			if(cVal > maxVal) {
				maxVal = cVal;
			}
			
			var thisCol = "";
			if(row[2].indexOf("ARGB") > -1){
				thisCol = AtoR(row[2]);
			}else{
				thisCol = row[2];
			}
			colorSet[row[0]] = {"color" : thisCol,"size" : cVal}
			colorSet[row[0]].size = cVal;
		}
		console.info("4");
		$("#" + divName).css("width", _this.GetWidth() + "px").css("height", _this.GetHeight() + "px");
		
		d3.layout.cloud().size([_this.GetWidth(), _this.GetHeight()]).words(
				wordSet.map(function(d) {
					var tNum = parseFloat(colorSet[d].size);
					return {
						text : d,
						size : minT + (tNum / maxVal) * maxT
					};
				})).rotate(function(){return 0;}).fontSize(function(d) 
				{
					return d.size;
				}
			).on("end", draw).start();
		
		function draw(words) {
			d3.select("#" + divName).append("svg").attr("width", _this.GetWidth()).attr("height", _this.GetHeight()).append("g").attr("transform", "translate(" + (_this.GetWidth() / 2) + "," + (_this.GetHeight() / 2) + ")").selectAll("text").data(words).enter().append("text").style("font-size", function(d) {
				return d.size + "px";
			}).style("font-family", "Trebuchet MS, Trebuchet MS, sans-serif").style("font-weight","bold").attr("text-anchor", "middle").attr("transform", function(d) {
				return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
			}).text(function(d) {
				return d.text;
			}).style("cursor","pointer").on("click", function(d){
				_this.Data.SearchColumn(0, d.text);
					
			}).attr("fill",function(d){
				return colorSet[d.text].color;
			});
		}
	});
}