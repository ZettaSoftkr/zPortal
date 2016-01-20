!function(){
	var barChart={};
	
	barChart.draw=function(datalist, wid, hei, rot) {
		var margin = {top: 20, right: 20, bottom: 30, left: 40};
			width = wid - margin.left - margin.right, 
			height = hei - margin.top - margin.bottom;
					
		var formatPercent= d3.format("0");

		
			var data = d3.nest()
				.key(function(d) {return d.name;})
				.rollup(function(d){
				if(rot == 1){	
						return  d3.sum(d, function(g) {return g.value;});
						}
				else {
						return d.length;
					} 
				}).entries(datalist);
			
			data.forEach(function(d) {
				d.name = d.key;
				d.value = d.values;
		});
		
		var x = d3.scale.ordinal()
			.rangeRoundBands([0, width], 0.1)
			.domain(data.map(function(d) { return d.name; }));
		
		var y = d3.scale.linear()
			.range([height, 0])
			.domain([0, d3.max(data, function(d) { return d.value; })]);
		
		var xAxis = d3.svg.axis()
			.scale(x)
			.orient("bottom");

		var yAxis = d3.svg.axis()
			.scale(y)
			.orient("left")
			.tickSize(-width)
			.tickFormat(formatPercent);
		
		var color = d3.scale.ordinal()
			.domain([0,1,2,3,4])
			.range(["#8daacb", "#fc7362", "#bbd854", "#ffd92f", "#66c296"]);
		
		// 여기까지 bar chart init 셋팅
		
		var tip = d3.tip()
				.attr('class', 'd3-tip')
				.offset([20, 0])
				.html(function(d) {
		  return d.name + "<strong> = </strong> <span style='color:red'>" + d.value + "</span>";
		});
		
		// 막대에 마우스 오버 시키면 출력될 툴팁

		var svg = d3.select("body").append("svg")
			.attr("width", width + margin.left + margin.right)
			.attr("height", height + margin.top + margin.bottom)
			.attr("style","outline: thin solid black;")
			.attr("style","box-shadow: 5px 5px 5px #888888;")
			.append("g")
			.attr("transform", "translate(" + margin.left + "," + margin.top + ")");
		
		// 차트가 출력될 배경 컨테이너 작성

		svg.call(tip);
				
			svg.append("rect")
				.attr("width",width)
				.attr("height",height)
				.attr("fill","white");
			
			svg.append("g")
				.attr("class", "x axis")
				.attr("transform", "translate(0," + height + ")")
				.call(xAxis);
		
			svg.append("g")
				.attr("class", "y axis")
				.call(yAxis);
			
			svg.append("svg:line")
			   .attr("x1",0)
			   .attr("x2",width)
			   .attr("y1",height)
			   .attr("y2",height)
			   .attr("stroke", "#000");
		
			// 배경색, x축 y축 생성 및 x축 라인 생성 (컨테이너 배경작성)
			svg.append("g")
			.attr("class", "grid")
			.attr("transform", "translate(0," + height + ")")
			.call(make_x_axis()
				.tickSize(-height, 0, 0)
				.tickFormat("")
			);
	
			svg.append("g")
				.attr("class", "grid")
				.call(make_y_axis()
					.tickSize(-width, 0, 0)
					.tickFormat("")
			);
			
			d3.selectAll(".grid").attr("visibility","hidden");
			// 배경 그리드 생성
			
		svg.selectAll(".bar")
				.data(data)
			.enter().append("svg:rect")
				.attr("id","chart_bar")
				.attr("style","position: absolute;")
				.attr("class", "bar")
				.attr("x", function(d) { return x(d.name); })
				.attr("y", function(d) { return y(d.value); })
				.attr("width", x.rangeBand())
				.attr("height", function(d) { return height - y(d.value); });
		
		// 배경 위에 덧 씌워질 바 생성
		
		svg.selectAll(".text")
			.data(data)
			.enter().append("svg:text")
			.attr("id","pointValue")
			.text("")
			.attr("x", function(d){ return x(d.name)+5;})
			.attr("y", function(d) { return y(d.value) +15; })
			.attr("font-family", "malgun gothic")
			.attr("font-size", "11px")
			.attr("fill", "white");
		
		// 바 위에 덧씌워질 값 생성
		
		svg.append("text")
		   .attr("id","titlelabel")
		   .attr("x", (width/2))
		   .attr("y", 0 - (margin.top / 2 - 5))
		   .attr("text-anchor","middle")
		   .style("font-size","16px")
		   .style("text-decoration", "underline")
		   .text("");
		
		// 차트 캡션 생성
		
		// 아래로는 이벤트 처리
		
		d3.selectAll(".bar").on("mouseover",tip.show)
							.on("mouseout",tip.hide);
		
		d3.selectAll("#chart_title").on("focusout",function() {
			var titleText = $("#chart_title").val();

			d3.select("#titlelabel").transition().duration(300)
			  .text(titleText);
		});
		
		d3.selectAll("#value_on_point").on("change",function(){
			if($("#value_on_point").is(":checked")) {
				d3.selectAll("#pointValue").transition().duration(300)
					.text(function(d) {return formatPercent(d.value);});
			} else {
				d3.selectAll("#pointValue").transition().duration(300)
				.text("");
			}
		});
		
		d3.selectAll("#multi_color").on("change",function() {
			if($("#multi_color").is(":checked")) {
				d3.selectAll("#chart_bar").transition().duration(300)
					.style("fill",function(d) { return color(d.name); });
			} else {
				d3.selectAll("#chart_bar").transition().duration(300)
				.style("fill",null);
			}
		});
		
		d3.selectAll("#color1").on("focusout",function() {
			var newcolor = new Array();
			newcolor[0] = $(this).val();
			
			color = d3.scale.ordinal()
			.range([newcolor[0],color(1),color(2),color(3),color(4)]);
			d3.selectAll("#chart_bar").transition().duration(300)
				.style("fill",function(d) { return color(d.name); });
		});
		
		d3.selectAll("#show_grid").on("change",function(){
			if($("#show_grid").is(":checked")){
				d3.selectAll(".grid").attr("visibility","visible");
			} else {
				d3.selectAll(".grid").attr("visibility","hidden");
			}
		});
		
		function make_x_axis() {
			return d3.svg.axis()
					.scale(x)
					.orient("bottom")
					.ticks(30)
		}
		
		function make_y_axis() {
			return d3.svg.axis()
					.scale(y)
					.orient("left")
					.ticks(10)
		}

	}
	
	this.barChart = barChart;
}();