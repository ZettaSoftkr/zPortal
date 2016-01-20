!function(){
	var pnBarChart={};

	
	pnBarChart.draw=function(data, wid, hei) {
		var margin = {top: 30, right: 10, bottom: 10, left: 10},
	    width = wid - margin.left - margin.right,
	    height = hei - margin.top - margin.bottom;

		var x = d3.scale.linear()
		    .range([0, width]);
	
		var y = d3.scale.ordinal()
		    .rangeRoundBands([0, height], .2);
	
		var xAxis = d3.svg.axis()
		    .scale(x)
		    .orient("top");
		    
		var tip = d3.tip()
		.attr('class', 'd3-tip')
		.offset([20, 0])
		.html(function(d) {
		  return d.name + "<strong> = </strong> <span style='color:red'>" + d.value + "</span>";
		});
	
	
	
		var svg = d3.select("body").append("svg")
		    .attr("width", width + margin.left + margin.right)
		    .attr("height", height + margin.top + margin.bottom)
		    .append("g")
		    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
	
		svg.call(tip);
			
		data.forEach(function(d) {
		    d.value = +d.value;
		  });
		  
		  x.domain(d3.extent(data, function(d) { return d.value; })).nice();
		  y.domain(data.map(function(d) { return d.name; }));
	
		  svg.selectAll(".bar")
		      .data(data)
		    .enter().append("rect")
		      .attr("class", function(d) { return d.value < 0 ? "bar negative" : "bar positive"; })
		      .attr("x", function(d) { return x(Math.min(0, d.value)); })
		      .attr("y", function(d) { return y(d.name); })
		      .attr("width", function(d) { return Math.abs(x(d.value) - x(0)); })
		      .attr("height", y.rangeBand())
		      .on('mouseover', tip.show)
			  .on('mouseout', tip.hide);
	
		  svg.append("g")
		      .attr("class", "x axis")
		      .call(xAxis);
		  
		  var bars = svg.selectAll(".bar")
				.data(data)
				.enter().append("g")
				.attr("class", "bar");
	
		  svg.append("g")
		      .attr("class", "y axis")
		    .append("line")
		      .attr("x1", x(0))
		      .attr("x2", x(0))
		      .attr("y2", height);
	}
	
	this.pnBarChart = pnBarChart;
}();