﻿!function(){
	var lineChart={};
		
	lineChart.draw=function(data, wid, hei, shape){
	
		var margin = {top: 20, right: 20, bottom: 30, left: 50},
	    width = wid - margin.left - margin.right,
	    height = hei - margin.top - margin.bottom;
	
		var x = d3.time.scale()
		.range([0, width]);
		
		var y = d3.scale.linear()
		.range([height, 0]);
		
		var xAxis = d3.svg.axis()
		.scale(x)
		.orient("bottom");
		
		var yAxis = d3.svg.axis()
		.scale(y)
		.orient("left");
		
		
		if(shape == 1){
		var line = d3.svg.line()
		.x(function(d) { return x(d[0]); })
		.y(function(d) { return y(d[1]); });
		} else {
		var area = d3.svg.area()
		.x(function(d) { return x(d[0]); })
		.y0(height)
		.y1(function(d) { return y(d[1]); });
		}
		
		var svg = d3.select("body").append("svg")
		.attr("width", width + margin.left + margin.right)
		.attr("height", height + margin.top + margin.bottom)
		.append("g")
		.attr("transform", "translate(" + margin.left + "," + margin.top + ")");
		
		
		x.domain(d3.extent(data, function(d) { return d[0]; }));
		if(shape == 1){
			y.domain(d3.extent(data, function(d) { return d[1]; }));
		} else {
			y.domain([0, d3.max(data, function(d) { return d[1]; })]);
		}
		
		if(shape == 1){
			svg.append("path")
				.datum(data)
				.attr("class", "line")
				.attr("d", line)
		} else {
			svg.append("path")
				.datum(data)
				.attr("class", "area")
				.attr("d", area)
		}
		
		
		svg.append("g")
		    .attr("class", "x axis")
		    .attr("transform", "translate(0," + height + ")")
		    .call(xAxis);
		
		svg.append("g")
		    .attr("class", "y axis")
		    .call(yAxis)
		  .append("text")
		    .attr("transform", "rotate(-90)")
		    .attr("y", 6)
		    .attr("dy", ".71em")
		    .style("text-anchor", "end")
		    .text("Price ($)");
	}
	
	
	this.lineChart = lineChart;
}();