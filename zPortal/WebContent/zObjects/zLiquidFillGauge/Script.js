var template_path = Qva.Remote + "?public=only&name=Extensions/zLiquidFillGauge/"; /// path of the extension
 
function extension_Init() {
	console.info("extension_Init start..");
	var js_Load = function () {
		Qva.LoadScript(template_path + 'd3.min.js', function() {
			Qva.LoadScript(template_path + 'liquidFillGauge.js', extension_Done);
		});
	};
 
    if (typeof jQuery == 'undefined') Qva.LoadScript(template_path + 'jquery-2.1.4.min.js', js_Load);
    else js_Load();
}
 
function extension_Done() {
	console.info("extension_Done start..");
	
    Qva.AddExtension('zLiquidFillGauge', function () {
    	
    	console.info("zLiquidFillGauge AddExtension start..");
    	
        var _this = this;
        var widthCount = 0;
        var gaugeValue1 = this.Layout.Text0.text.toString();
        if (!isNaN(gaugeValue1))  widthCount++;
        var gaugeValue2 = this.Layout.Text1.text.toString();
        if (!isNaN(gaugeValue2))  widthCount++;
        var gaugeValue3 = this.Layout.Text2.text.toString();
        if (!isNaN(gaugeValue3))  widthCount++;
        var gaugeValue4 = this.Layout.Text3.text.toString();
        if (!isNaN(gaugeValue4))  widthCount++;
        var gaugeValue5 = this.Layout.Text4.text.toString();
        if (!isNaN(gaugeValue5))  widthCount++;
        
        console.info("gaugeValue4:" + gaugeValue4 + "gaugeValue2:" + gaugeValue2);
        
        var height = _this.GetHeight();
        var width = 100/widthCount;
        
        console.info("height:" + height + "width:" + width);
        
        var html = ""; 
 
        if (!isNaN(gaugeValue1)) html += "<svg id='fillgauge1' width='"+width+"%' height='"+height+"'></svg>";
        if (!isNaN(gaugeValue2)) html += "<svg id='fillgauge2' width='"+width+"%' height='"+height+"'></svg>";
        if (!isNaN(gaugeValue3)) html += "<svg id='fillgauge3' width='"+width+"%' height='"+height+"'></svg>";
        if (!isNaN(gaugeValue4)) html += "<svg id='fillgauge4' width='"+width+"%' height='"+height+"'></svg>";
        if (!isNaN(gaugeValue5)) html += "<svg id='fillgauge5' width='"+width+"%' height='"+height+"'></svg>";
    	
        _this.Element.innerHTML = html;
        
        if (!isNaN(gaugeValue1)) {
        	var gauge1 = loadLiquidFillGauge("fillgauge1", gaugeValue1);
        }
        
        if (!isNaN(gaugeValue2)) {
        	var config1 = liquidFillGaugeDefaultSettings();
    	    config1.circleColor = "#FF7777";
    	    config1.textColor = "#FF4444";
    	    config1.waveTextColor = "#FFAAAA";
    	    config1.waveColor = "#FFDDDD";
    	    config1.circleThickness = 0.2;
    	    config1.textVertPosition = 0.2;
    	    config1.waveAnimateTime = 1000;
    	    var gauge2= loadLiquidFillGauge("fillgauge2", gaugeValue2, config1);
        }
        
        if (!isNaN(gaugeValue3)) {
        	 var config2 = liquidFillGaugeDefaultSettings();
     	    config2.circleColor = "#D4AB6A";
     	    config2.textColor = "#553300";
     	    config2.waveTextColor = "#805615";
     	    config2.waveColor = "#AA7D39";
     	    config2.circleThickness = 0.1;
     	    config2.circleFillGap = 0.2;
     	    config2.textVertPosition = 0.8;
     	    config2.waveAnimateTime = 2000;
     	    config2.waveHeight = 0.3;
     	    config2.waveCount = 1;
     	    var gauge3 = loadLiquidFillGauge("fillgauge3", gaugeValue3, config2);
        }
        
        if (!isNaN(gaugeValue4)) {
        	var config4 = liquidFillGaugeDefaultSettings();
    	    config4.circleThickness = 0.15;
    	    config4.circleColor = "#808015";
    	    config4.textColor = "#555500";
    	    config4.waveTextColor = "#FFFFAA";
    	    config4.waveColor = "#AAAA39";
    	    config4.textVertPosition = 0.8;
    	    config4.waveAnimateTime = 1000;
    	    config4.waveHeight = 0.05;
    	    config4.waveAnimate = true;
    	    config4.waveRise = false;
    	    config4.waveHeightScaling = false;
    	    config4.waveOffset = 0.25;
    	    config4.textSize = 0.75;
    	    config4.waveCount = 3;
    	    var gauge5 = loadLiquidFillGauge("fillgauge4", gaugeValue4, config4);
        }
        
        if (!isNaN(gaugeValue5)) {
        	var config5 = liquidFillGaugeDefaultSettings();
    	    config5.circleThickness = 0.4;
    	    config5.circleColor = "#6DA398";
    	    config5.textColor = "#0E5144";
    	    config5.waveTextColor = "#6DA398";
    	    config5.waveColor = "#246D5F";
    	    config5.textVertPosition = 0.52;
    	    config5.waveAnimateTime = 5000;
    	    config5.waveHeight = 0;
    	    config5.waveAnimate = false;
    	    config5.waveCount = 2;
    	    config5.waveOffset = 0.25;
    	    config5.textSize = 1.2;
    	    config5.minValue = 30;
    	    config5.maxValue = 150
    	    config5.displayPercent = true;
    	    var gauge6 = loadLiquidFillGauge("fillgauge5", gaugeValue5, config5);
        }	    
	    
    });
    
    
}
 
extension_Init(); //Initiate extension
