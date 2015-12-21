<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="boardarea">
	<section class="boardlist">
		<h3>
			<span class="textt">사이트 맵</span>
		</h3>
		<div class="bs-docs-section">
			<div class="bs-callout bs-callout-danger">
					
					   체크박스를 클릭하면 해당 보고서 및 메뉴의 설명을 볼 수 있습니다.
					 <h4 id="title"></h4>
					  <div id="keyword"></div>
					 <div id="desc"></div>
<!-- 					 <code></code> -->
			
			</div>
		</div>
		<div class="row" id="topSiteMap">
		
		</div>

		<div class="row" id="topSiteMap1">
			
		</div>
	</section>
</div>
<!-- 끝 -->


<!--search Modal -->
<script type="text/javascript">
	

	$(document).ready(function() {
		
		var url = "/" + serviceName + "/menu/getRoleMenuListData.do";
		$.ajax({

			url : url,
			type : "post",
			data : {
				bi_portal_menu_parent_id : menuServCode
			},
			dataType : 'json',
			beforeSend : function() {

				$("#progress_modal").modal('show');		
				$(".progress").show();
				$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();

			},
			success : function(data) {

				$("#progress_modal").modal('hide');		
				$(".progress").hide();
				$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
				
				
				
				var strLayout = "";
				$.each(data,function(key, value) {
					
				 
					strLayout += "<div class=\"col-md-3\">";
					strLayout += "<div class=\"panel panel-info\">";
					strLayout += "<div class=\"panel-heading\">";
					strLayout += "<span class=\"glyphicon glyphicon-th-list\"></span> " + data[key][2];
					strLayout += "</div>";
					strLayout += "<div class=\"panel-body\" id=\"menu_list"+key+"\">";
					strLayout += "</div>";
					strLayout += "</div>";
					strLayout += "</div>";
				
				});
				
				$('#topSiteMap').html(strLayout);
				
				$.each(data,function(key, value) {					
					list("menu_list"+key, data[key][0], "d"+key);					
				});
			

			},
			error : function() {
				
				$("#progress_modal").modal('hide');		
				$("#error_modal").modal("show");			
				$(document).trigger("add-alerts", [
				    {
				      'message': "This is a warning.",
				      'priority': 'warning'
				    }
				]);		
				
			}
		});

	
		
		var url = "/" + serviceName + "/menu/getRoleMenuListData.do";
		$.ajax({

			url : url,
			type : "post",
			data : {
				bi_portal_menu_parent_id : "#"
			},
			datatype : 'json',
			beforeSend : function() {

				
			},
			success : function(data) {
			
			
				var strLayout = "";
				$.each(data,function(key, value) {
					
					if(data[key][2] != '관리실' && data[key][2] != '사이트맵' && data[key][2] != '서비스메뉴'){
						
						strLayout += "<div class=\"col-md-3\">";
						strLayout += "<div class=\"panel panel-info\">";
						strLayout += "<div class=\"panel-heading\">";
						strLayout += "<span class=\"glyphicon glyphicon-th-list\"></span> " + data[key][2];
						strLayout += "</div>";
						strLayout += "<div class=\"panel-body\" id=\"menu_list"+(key+4)+"\">";
						strLayout += "</div>";
						strLayout += "</div>";
						strLayout += "</div>";
					}
				
				});
				
				$('#topSiteMap1').html(strLayout);
				
				$.each(data,function(key, value) {	
					
					
					if(data[key][2] != '관리실' && data[key][2] != '사이트맵' && data[key][2] != '서비스메뉴'){
						
						
						list("menu_list"+(key+4), data[key][0], "d"+(key+4));
						
						
					}
					
				});
				
			
				
				
			

			},
			error : function() {

				$("#progress_modal").modal('hide');		
				$("#error_modal").modal("show");			
				$(document).trigger("add-alerts", [
				    {
				      'message': "This is a warning.",
				      'priority': 'warning'
				    }
				]);	

			}
		});

	
		 
		
		//list("menu_list2","M_76","d1");
		//list("menu_list3","M_118","d2");

	});
	
	
	
	
	
	
	 function list(viewId,menuId,treeId){

			$.ajax({

				url : "/" + serviceName + "/sitemap/getSiteMap.do",
				type : "post",
				data : {bi_portal_menu_id:menuId},
				dataType : 'json',
				beforeSend : function() {
					
// 					$("#progress_modal").modal('show');		
// 					$(".progress").show();
// 					$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();
					

				},
				success : function(data) {					
				
					var menuArray = [ [ menuId, -1, '목록' ] ];
					
					for(var i = menuArray.length-1; i--;){
							 if(i != 0){
								 menuArray.splice(i);
							 }
					}

					
// 					$("#progress_modal").modal('hide');
// 					$(".progress").hide();
// 					$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();

					for (var i = 0; i < data.length; i++) {
						
					
						var addArray = [ data[i][0], data[i][1], data[i][2] ];
						 menuArray.push(addArray);
					}
					
					menuList(menuArray,viewId,treeId);
					
				
				
				},
				error : function() {
					alert("false");

				}
			});
			

			}
	
	   function menuList(menuArray,viewId,treeId) {
		   
		if(treeId == new String("d0")){

			  d0 = new dTree("d0");
	
			for (var i = 0; i < menuArray.length; i++) {
	
				d0.add(menuArray[i][0], menuArray[i][1], menuArray[i][2]);
			}	
			$('#'+viewId).html(d0.toString());
			
		}else if(treeId == new String("d1")){

			  d1 = new dTree("d1");
	
			for (var i = 0; i < menuArray.length; i++) {
	
				d1.add(menuArray[i][0], menuArray[i][1], menuArray[i][2]);
			}
	
			$('#'+viewId).html(d1.toString());
		}else if(treeId == new String("d2")){

			  d2 = new dTree("d2");
	
			for (var i = 0; i < menuArray.length; i++) {
	
				d2.add(menuArray[i][0], menuArray[i][1], menuArray[i][2]);
			}
	
			$('#'+viewId).html(d2.toString());
		}else if(treeId == new String("d3")){

			  d3 = new dTree("d3");
	
			for (var i = 0; i < menuArray.length; i++) {
	
				d3.add(menuArray[i][0], menuArray[i][1], menuArray[i][2]);
			}
	
			$('#'+viewId).html(d3.toString());
		}else if(treeId == new String("d4")){

			  d4 = new dTree(treeId);
	
			for (var i = 0; i < menuArray.length; i++) {
	
				d4.add(menuArray[i][0], menuArray[i][1], menuArray[i][2]);
			}
	
			$('#'+viewId).html(d4.toString());
		}else if(treeId == new String("d5")){

			  d5 = new dTree(treeId);
	
			for (var i = 0; i < menuArray.length; i++) {
	
				d5.add(menuArray[i][0], menuArray[i][1], menuArray[i][2]);
			}
	
			$('#'+viewId).html(d5.toString());
		}else if(treeId == new String("d6")){

			 d6 = new dTree(treeId);
	
			for (var i = 0; i < menuArray.length; i++) {
	
				d6.add(menuArray[i][0], menuArray[i][1], menuArray[i][2]);
			}
	
			$('#'+viewId).html(d6.toString());
		}else if(treeId == new String("d7")){

			  d7 = new dTree(treeId);
	
			for (var i = 0; i < menuArray.length; i++) {
	
				d7.add(menuArray[i][0], menuArray[i][1], menuArray[i][2]);
			}
	
			$('#'+viewId).html(d7.toString());
		}
		else if(treeId == new String("d8")){

			  d8 = new dTree(treeId);
	
			for (var i = 0; i < menuArray.length; i++) {
	
				d8.add(menuArray[i][0], menuArray[i][1], menuArray[i][2]);
			}
	
			$('#'+viewId).html(d8.toString());
		}
	}

	   
	   function checkDesc(objId) {
			

			if (objId.checked == true) {
				$.ajax({

					url : "/"+serviceName+"/menu/getViewData.do",
					type : "POST",
					data : {
						bi_portal_menu_id : objId.value
					},
					dataType : 'json',
					beforeSend : function() {
						
						$("#title").html("");
						$("#keyword").html("");
						$("#desc").html("");
						

					},
					success : function(rowData) {

						// alert(JSON.stringify(rowData));

				
						
						$("#title").html(rowData['bi_menu_nm']);
				        $("#keyword").html(rowData['bi_search_keyword']);
						$("#desc").html(rowData['bi_dc']);
					

						

					}
				});
				
				
				
				$("input:checkbox").click(function(){
				    var group = "input:checkbox[name='"+$(this).prop("name")+"']";
				    $(group).prop("checked",false);
				    $(this).prop("checked",true);
				});
				

			} else {

				

				$("#title").html("");
				$("#desc").html("");
				$("#keyword").html("");

			}

		}


	</script>



