!function() {
	var pieChart={};

	pieChart.draw=function(data, wid, hei, shape) {
		var width = wid,
		    height = hei,
		    radius = Math.min(width, height) / 2;
		
		var color = d3.scale.ordinal()
		    .range(["#98abc5", "#8a89a6", "#7b6888", "#6b486b", "#a05d56", "#d0743c", "#ff8c00"]);
		
		
		// innerRadius 변형 시키면 pie || donut 변경 가능 .innerRadius(0)은 파이 (redius-70)은 도넛
		if(shape == 1){
			var arc = d3.svg.arc()
			    .outerRadius(radius - 10)
			    .innerRadius(radius - 70);
		} else {
			var arc = d3.svg.arc()
		    .outerRadius(radius - 10)
		    .innerRadius(0);
		}
		
		
		var labelArc = d3.svg.arc()
		    .outerRadius(radius - 40)
		    .innerRadius(radius - 40);
		
		var pie = d3.layout.pie()
		    .sort(null)
		    .value(function(d) { return d.population; });
		
		var svg = d3.select("body").append("svg")
		    .attr("width", width)
		    .attr("height", height)
		  .append("g")
		    .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");
		
		var tip = d3.tip()
		.attr('class', 'd3-tip')
		.offset([10, 0])
		.html(function(d) {
		  return d.data.age + "<strong> = </strong> <span style='color:red'>" + d.data.population + "</span>";
		});
		
		
		svg.call(tip);
		
		  var g = svg.selectAll(".arc")
		      .data(pie(datalist))
		    .enter().append("g")
		      .attr("class", "arc")
		      .on('mouseover', tip.show)
			  .on('mouseout', tip.hide);
		
		  g.append("path")
		      .attr("d", arc)
		      .style("fill", function(d) { return color(d.data.age); });
		
		  g.append("text")
		      .attr("transform", function(d) { return "translate(" + labelArc.centroid(d) + ")"; })
		      .attr("dy", ".35em")
		      .text(function(d) { return d.data.age; });
		  
		  
	}
  this.pieChart = pieChart;
}();