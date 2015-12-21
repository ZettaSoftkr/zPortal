<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h2 id="menu_top_title"><!-- 첫번째 제목 --></h2>
<div class="left_sub_nav_area">
   	<ul class="left_sub_nav" id="leftMenu">
   	
       	
    </ul>
</div>

<script type="text/javascript">

$(document).ready(function() {
	
	 $("#hideleftMenu").click(function(){
		 
		 $("#bodyLeft").hide();	 		
		 $("#hideleftMenu").hide();	
		 $("#openleftMenu").show();	
		 $("#reportUrl").width(($(window).width() -20));		 	
		 $(".Main_Title_Area").width(($(window).width() -20));
		 $(".box_type").width(($(window).width() -20));
		
	 });
	 
	 $("#openleftMenu").click(function(){
		 
		 $("#bodyLeft").show();			
		 $("#openleftMenu").hide();	
		 $("#hideleftMenu").show();	
		 $("#reportUrl").width("1040px");
		 $(".Main_Title_Area").width("1040px");
		 $(".box_type").width("1040px");
		 
	 });
	
	
	 

	
	  
	});
</script>
