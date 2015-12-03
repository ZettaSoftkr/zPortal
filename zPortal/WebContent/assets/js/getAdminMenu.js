/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 상단페이지 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */


function goTopMenu(lev1_parent_id) {


	var url = "/" + serviceName + "/menu/getRoleMenuListData.do";
	$.ajax({

		url : url,
		type : "POST",
		data : {
			bi_portal_menu_parent_id : menuServCode
		},
		dataType : 'json',
		beforeSend : function() {

			var strTopMenu = "";
			
			strTopMenu += "<div id=\"box-1\"><img src=\"/"+serviceName+"/assets/images/img/ajax-loader_blue.gif\"></div>";
			//strTopMenu += "<li style=\"margin-left:45%\"><img src=\"../assets/images/img/ajax-loader_blue.gif\"></li>";
			$("#topMenu").html(strTopMenu);

		},
		success : function(data) {
			
			var preTopId = ""; // 이전 클래스 삭제를 위한 변수명 지정
			var strTopMenu = "";
			$.each(data, function(key, value) {
			// alert(JSON.stringify(data));
			//getLeftMenu("+data[key][0]+")
			if(key == 0 ){
					
					strTopMenu += "<li id='top_" + data[key][0] + "' style=\"border-left:none;\"><a href=\"#\" onclick='gotoSubPage(\"" + data[key][3] + "\",\"" + data[key][0] + "\");'>" + data[key][2] + "</a></li>";
			}else{
				
				strTopMenu += "<li id='top_" + data[key][0] + "'><a href=\"#\" onclick='gotoSubPage(\"" + data[key][3] + "\",\"" + data[key][0] + "\");'>" + data[key][2] + "</a></li>";
			}
			

			});
			
			
		
			$("#topMenu").html(strTopMenu);
			
			if(lev1_parent_id){
				
				$("#top_" + lev1_parent_id).addClass("on");
			
			}		
			
			$("#topMenu li").click(function() {		
				
				$("#top_" + lev1_parent_id).removeClass();
				$("#" + preTopId).removeClass();
				$(this).addClass("on");
				preTopId = $(this).attr("id");
			});		

		},
		error : function() {
			
			$("#progress_modal").modal('hide');
			$(".progress").hide();
			//$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
			

		}
	});

}
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/**
 * ************************************* 왼쪽메뉴 나타나기
 * ******************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function getLeftMenu(lev1_parent_id) {

	getMenuNm(lev1_parent_id, "#menu_top_title");
	$("#leftMenu").slideDown('slow');

	// var leftUrl = "/" + serviceName + "/menu/" + getDataLink.left;
	var url = "/" + serviceName + "/menu/getRoleMenuListData.do";
	$.ajax({
		url : url,
		type : "POST",
		data : {
			bi_portal_menu_parent_id : lev1_parent_id
		},
		datatype : 'json',
		beforeSend : function() {

			var strTopMenu = "";
			strTopMenu += "<li style=\"margin-left:50px\"><img src=\"/"+serviceName+"/assets/images/img/spinner_squares_circle.gif\"></li>";
			$("#leftMenu").html(strTopMenu);

		},
		success : function(data) {

			$("#leftMenu").empty();
		
			$.each(data, function(key, value) {
						
				if(data[key][8] == 'F'){
					
					if(key == 0){
						
						$("#leftMenu").append("<li class=\"first\" id='lev1_leftMenu_"+data[key][0]+"' data-slide='true'><a href=\"#\"id='left_"+ data[key][0] + "' onClick='getLeftSubMenu(\"" + data[key][0] + "\",\"" + data[key][1] + "\");'>"+ data[key][2] + "</a>");
						
					}else if(data.length == (key+1)){
						
						$("#leftMenu").append("<li class=\"end\" id='lev1_leftMenu_"+data[key][0]+"' data-slide='true'><a href=\"#\" id='left_"
										+ data[key][0] + "' onClick='getLeftSubMenu(\"" + data[key][0] + "\",\"" + data[key][1] + "\");'>"+ data[key][2] + "</a></li>");
					        
					}else{
						
						$("#leftMenu").append("<li id='lev1_leftMenu_"+data[key][0]+"' data-slide='true'><a href=\"#\"id='left_"
										+ data[key][0] + "' onClick='getLeftSubMenu(\"" + data[key][0] + "\",\"" + data[key][1] + "\");'>"+ data[key][2] + "</a></li>");
					}
				
				}else if(data[key][8] == 'P'){
					
					if (data[key][5] == '1') { // 바로가기 Iframe
					
						if(key == 0){
							
							$("#leftMenu").append("<li id='lev1_leftMenu_"+data[key][0]+"' data-slide='true' class=\"first\"><a href=\"#\"id='left_"+ data[key][0] + "' onClick='goContent(\"/"+serviceName+"/"+  data[key][3] + "&lev1_parent_id=" + data[key][1] + "&currentId=" + data[key][0]+"&lev1_menuOpenId="+ data[key][1]+"&lev1_menuOpen="+lev1_menuOpen+"\")'>"+ data[key][2] + "</a>");
						}else if(data.length == (key+1)){
							
							$("#leftMenu").append("<li id='lev1_leftMenu_"+data[key][0]+"' data-slide='true'  class=\"end\"><a href=\"#\" id='left_"
											+ data[key][0] + "' onClick='goContent(\"/"+serviceName+"/"+  data[key][3] + "&lev1_parent_id=" + data[key][1] + "&currentId=" + data[key][0]+"&lev1_menuOpenId="+ data[key][1]+"&lev1_menuOpen="+lev1_menuOpen+"\")'>"+ data[key][2] + "</a></li>");
						        
						}else{
							
							$("#leftMenu").append("<li id='lev1_leftMenu_"+data[key][0]+"' data-slide='true' ><a href=\"#\"id='left_"
											+ data[key][0] + "' onClick='goContent(\"/"+serviceName+"/"+  data[key][3] + "&lev1_parent_id=" + data[key][1] + "&currentId=" + data[key][0]+"&lev1_menuOpenId="+ data[key][1]+"&lev1_menuOpen="+lev1_menuOpen+"\")'>"+ data[key][2] + "</a></li>");
						}
					}else if (data[key][5] == '2') { // 새창 링크
						
						if(key == 0){
							
							$("#leftMenu").append("<li id='lev1_leftMenu_"+data[key][0]+"' data-slide='true' class=\"first\"><a href=\"#\"id='left_"+ data[key][0] + "' onClick='getNewPopUpPage(\"/"+serviceName+"/"+  data[key][3] + "\");'>"+ data[key][2] + "</a>");
						}else if(data.length == (key+1)){
							
							$("#leftMenu").append("<li id='lev1_leftMenu_"+data[key][0]+"' data-slide='true' class=\"end\"><a href=\"#\" id='left_"
											+ data[key][0] + "' onClick='getNewPopUpPage(\"/"+serviceName+"/"+  data[key][3] + "\");'>"+ data[key][2] + "</a></li>");
						        
						}else{
							
							$("#leftMenu").append("<li id='lev1_leftMenu_"+data[key][0]+"' data-slide='true'><a href=\"#\"id='left_"
											+ data[key][0] + "' onClick='getNewPopUpPage(\"/"+serviceName+"/"+  data[key][3] + "\");'>"+ data[key][2] + "</a></li>");
						}
						
					}
				}
		        
				
				/*
				if(data[key][8] == 'F'){
					
					$("#leftMenu").append("<li id='lev1_leftMenu_"+data[key][0]+"' data-slide='true'><span class=\"glyphicon glyphicon-expand\"></span>&nbsp;<a href='#dropdowns' id='left_"
									+ data[key][0] + "' onClick='getLeftSubMenu(\"" + data[key][0] + "\",\"" + data[key][1] + "\");'>"+ data[key][2] + "</a> </li>");
					
				}else if(data[key][8] == 'P'){
					
					if (data[key][5] == '1') { // 바로가기 Iframe
						
						$("#leftMenu").append("<li id='lev1_leftMenu_"+data[key][0]+"' data-slide='true'><span class=\"glyphicon glyphicon-expand\"></span>&nbsp;<a href='#dropdowns' id='left_" + data[key][0] + "' onClick='goContent(\"/"+serviceName+"/"+  data[key][3] + "&lev1_parent_id=" + data[key][1] + "&currentId=" + data[key][0]+"&lev1_menuOpenId="+ data[key][1]+"&lev1_menuOpen="+lev1_menuOpen+"\")'>" + data[key][2] + "</a></li>");
											
					}else if (data[key][5] == '2') { // 새창
						
						$("#leftMenu").append("<li id='lev1_leftMenu_"+data[key][0]+"' data-slide='true'> <span class=\"glyphicon glyphicon-expand\"></span>&nbsp;<a href='#dropdowns' id='left_" + data[key][0]+ "' onclick='getNewPopUpPage(\"/"+serviceName+"/"+  data[key][3] + "\");'>" + data[key][2] +"</a></li>");
						
					} 	
				}
				*/
			});
			
			$("#left_" + currentId).addClass("on");
			
			
			if(lev1_menuOpen != null &&  lev1_menuOpen == "Y"){
				
		
				getLeftSubMenu(lev2_parent_id, lev1_parent_id);
			
				$('#lev1_leftMenu_'+lev2_parent_id).attr("data-slide","false");

			}
			

		},
		error : function() {
			
			$("#progress_modal").modal('hide');
			$(".progress").hide();
			//$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
			

		}
	});

}

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 상세 페이지 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function getLeftSubMenu(lev2_parent_id, lev1_parent_id) {
	    
	
	
	    
	    $("#leftMenu li a").removeClass("on");	 
	    $("#left_" + lev2_parent_id).addClass("on");
	   // $("#lev2_leftMenu_lk_" + lev2_parent_id).addClass("on");
	   
	   var dataSlide   = $('#lev1_leftMenu_'+lev2_parent_id).attr("data-slide"); // 메뉴 펠치기 닫기 // 클릭 시 data-slide 값을 비교해서  메뉴 펄치기 	  
		if (dataSlide == 'true')
        {
			 //$('#lev1_leftMenu_'+lev2_parent_id+' span').removeClass(); //아이콘 변경
			 //$('#lev1_leftMenu_'+lev2_parent_id+' span').addClass("glyphicon glyphicon-collapse-down");//아이콘 변경
			
			 var url = "/" + serviceName + "/menu/getRoleMenuListData.do";
				$.ajax({

					url : url,
					type : "POST",
					data : {
						bi_portal_menu_parent_id : lev2_parent_id
					},
					datatype : 'json',
					beforeSend: function () {
						 
							var strTopMenu = "";
							strTopMenu += "<ul id=\"left_secMenu_" + lev2_parent_id + "\">";
							strTopMenu += "  <li class='ajaxNone'><img src=\"/"+serviceName+"/assets/images/img/ajax-loader.gif\" widht=\"16\" height=\"16\"></li>";
							strTopMenu += "</ul>";
							$("#left_friMenu_" + lev2_parent_id).append(strTopMenu);
						 
					 },
					  success : function(data) {
						// alert(JSON.stringify(data));

						// data[key][0] -ID
						// data[key][1] -parent
						// data[key][2] -name
						// data[key][3] -url
						// data[key][4] -order
						// data[key][5] -menuType
						// data[key][8] -메뉴 형태
						  
						  //remove()
						
						$("#lev1_leftMenu_" + lev2_parent_id + " ul").remove();
						$("#lev2_leftPmenu_" + lev2_parent_id).remove();

						var dept2leftMenu = "";
					
						
						dept2leftMenu += "<div>";
						dept2leftMenu += "<ul id=\"lev2_leftPmenu_" + lev2_parent_id + "\">";

						
						$.each(data, function(key, value) {
							
							if(data[key][8] == 'F'){
								
								
								if ((data.length - 1) == key) {
									
									dept2leftMenu += "<li id='lev2_leftSmenu_" + data[key][0] + "' data-slide='true'><a href=\"#\" id='lev2_leftMenu_lk_" + data[key][0] + "'  onclick='getLeftThiMenu(\""+lev1_parent_id+"\",\""+lev2_parent_id+"\",\""+data[key][0]+"\");'>"+ data[key][2] + "</a></li>";
									

								} else {
									
									dept2leftMenu += "<li id='lev2_leftSmenu_" + data[key][0] + "' data-slide='true'><a href=\"#\" id='lev2_leftMenu_lk_" + data[key][0] + "' onclick='getLeftThiMenu(\""+lev1_parent_id+"\",\""+lev2_parent_id+"\",\""+data[key][0]+"\")'>"+ data[key][2] + "</a></li>";
									

								}	
								
								
							}else if(data[key][8] == 'P'){
								
								if (data[key][5] == '1') {
									
									if ((data.length - 1) == key) {
										
										dept2leftMenu += "<li id='lev2_leftSmenu_" + data[key][0] + "' data-slide='true'><a href=\"#\" id='lev2_leftMenu_lk_" + data[key][0] + "' onClick='goContent(\"/"+serviceName+"/"+  data[key][3]
												+ "&lev1_parent_id=" + lev1_parent_id +"&lev2_parent_id=" + data[key][1]+"&currentId=" + data[key][0]+"&lev1_menuOpenId="+ data[key][1]+"&lev1_menuOpen=Y\")'>"+ data[key][2] + "</a></li>";
										
	
									} else {
										dept2leftMenu += "<li id='lev2_leftSmenu_" + data[key][0] + "' data-slide='true'><a href=\"#\" id='lev2_leftMenu_lk_" + data[key][0] + "' onClick='goContent(\"/"+serviceName+"/"+  data[key][3] + "&lev1_parent_id=" + lev1_parent_id +"&lev2_parent_id=" + data[key][1]+"&currentId=" + data[key][0]+"&lev1_menuOpenId="+ data[key][1]+"&lev1_menuOpen=Y\")'>"+ data[key][2] + "</a></li>";
										
	
									}	
									
								} else if (data[key][5] == '2') { //메뉴 일경우 
									
								
									if ((data.length - 1) == key) {
										dept2leftMenu += "<li id='lev2_leftSmenu_" + data[key][0] + "' data-slide='true'><a href=\"#\" id='lev2_leftMenu_lk_" + data[key][0] + "' onclick='getPopUpPage(\"/"+serviceName+"/"+ data[key][3] + "\")'>"+ data[key][2] + "</a></li>";
										

									} else {
										dept2leftMenu += "<li id='lev2_leftSmenu_" + data[key][0] + "' data-slide='true'><a href=\"#\" id='lev2_leftMenu_lk_" + data[key][0] + "' onclick='getPopUpPage(\"/"+serviceName+"/"+  data[key][3] + "\")'>"+ data[key][2] + "</a></li>";
										

									}
								}
								
							}else if(data[key][8] == 'R'){
								
								if (data[key][5] == '1') {
									
									if ((data.length - 1) == key) {
										
										dept2leftMenu += "<li id='lev2_leftSmenu_" + data[key][0] + "' data-slide='true'><a href='#' id='lev2_leftMenu_lk_" + data[key][0] + "' onClick='goContent(\"/"+serviceName+"/"+  data[key][3]
												+ "&lev1_parent_id=" + lev1_parent_id +"&lev2_parent_id=" + data[key][1]+"&currentId=" + data[key][0]+"&lev1_menuOpenId="+ data[key][1]+"&lev1_menuOpen=Y\")'>"
												+ data[key][2] + "</a></li>";
	
									} else {
	
										dept2leftMenu += "<li id='lev2_leftSmenu_" + data[key][0] + "' data-slide='true'><a href='#' id='lev2_leftMenu_lk_" + data[key][0] + "' onClick='goContent(\"/"+serviceName+"/"+  data[key][3] + "&lev1_parent_id=" + lev1_parent_id +"&lev2_parent_id=" + data[key][1]+"&currentId=" + data[key][0]+"&lev1_menuOpenId="+ data[key][1]+"&lev1_menuOpen=Y\")'>" + data[key][2]
												+ "</a></li>";
	
									}	
									
								} else if (data[key][5] == '2') { //메뉴 일경우 
									
								
									if ((data.length - 1) == key) {

										dept2leftMenu += "<li  id='lev2_leftSmenu_" + data[key][0] + "' data-slide='true'><a href='#' id='lev2_leftMenu_lk_" + data[key][0] + "' onclick='getPopUpPage(\"/"+serviceName+"/"+ data[key][3] + "\")'>"+ data[key][2] + "</a></li>";

									} else {

										dept2leftMenu += "<li id='lev2_leftSmenu_" + data[key][0] + "' data-slide='true'><a href='#' id='lev2_leftMenu_lk_" + data[key][0] + "' onclick='getPopUpPage(\"/"+serviceName+"/"+  data[key][3] + "\")'>" + data[key][2]	+ "</a></li>";

									}
								}
								
							}


						});

						 dept2leftMenu += "</ul>";
						 dept2leftMenu += "</div>";
						 
						 
						 $("#lev1_leftMenu_" + lev2_parent_id).append(dept2leftMenu);
						


					},
					error : function() {
						
						$("#progress_modal").modal('hide');
						$(".progress").hide();
						//$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
						$("#session_modal").modal("show");	
						$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
						

					}
				});
				
		    
			 $('#lev1_leftMenu_'+lev2_parent_id).attr("data-slide","false");			 
			// $("#lev2_leftMenu_lk_" + lev2_parent_id).addClass("on");
			
				if(lev2_menuOpen != null &&  lev2_menuOpen == "Y"){
					
					setTimeout(function(){ // 
						
						getLeftThiMenu(lev1_parent_id,lev2_parent_id,lev3_parent_id);
						
					}, 1000);
					
					
					
					$('#lev2_leftSmenu_'+lev2_menuOpenId).attr("data-slide","false");

				}
             
        } else {
        	
        	$('#lev1_leftMenu_'+lev2_parent_id+' div').removeClass();
        	//$('#lev1_leftMenu_'+lev2_parent_id+' span').addClass("glyphicon glyphicon-expand");
        	$("#lev1_leftMenu_"+lev2_parent_id+" div").remove();
     	    $('#lev1_leftMenu_'+lev2_parent_id).attr("data-slide","true");
          
     	 
        }
}


/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 상세 페이지 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function getLeftThiMenu(lev1_parent_id,lev2_parent_id,lev3_parent_id) {
	
	    
	    
	    $("#leftMenu li a").removeClass("on");	  
	    //$("#lev2_leftMenu_lk_" + lev3_parent_id).addClass("on");
	   // $("#lev3_leftSmenu_" + lev3_parent_id).addClass("on");
	   
	   var dataSlide   = $('#lev2_leftSmenu_'+lev3_parent_id).attr("data-slide"); 
		if (dataSlide == 'true')
        {
			 //$('#left_secMenu_'+sec_parent_Id+' span').removeClass(); //아이콘 변경
			 //$('#left_secMenu_'+sec_parent_Id+' span').addClass("glyphicon glyphicon-collapse-down");//아이콘 변경
			
			 var url = "/" + serviceName + "/menu/getRoleMenuListData.do";
				$.ajax({

					url : url,
					type : "POST",
					data : {
						bi_portal_menu_parent_id : lev3_parent_id
					},
					datatype : 'json',
					beforeSend: function () {
						 
							var strTopMenu = "";
							strTopMenu += "<ul id=\"lev3_leftPmenu_" + lev3_parent_id + "\">";
							strTopMenu += "  <li class='ajaxNone'><img src=\"/"+serviceName+"/assets/images/img/ajax-loader.gif\" widht=\"16\" height=\"16\"></li>";
							strTopMenu += "</ul>";
							$("#left_secMenu_sub_" + lev3_parent_id).append(strTopMenu);
						 
					 },
					  success : function(data) {
						// alert(JSON.stringify(data));

						// data[key][0] -ID
						// data[key][1] -parent
						// data[key][2] -name
						// data[key][3] -url
						// data[key][4] -order
						// data[key][5] -menuType
						// data[key][8] -메뉴 형태
						  
						  //remove()
						$("#lev3_leftPmenu_" + lev3_parent_id).remove();
						//$("#left_friMenu_" + lev2_parent_id + " ul").remove();
						

						var dept2leftMenu = "";					
						dept2leftMenu += "<ul id=\"levl3_leftPmenu" + lev3_parent_id + "\" style=\"background:#ccc; width:170px; margin-left:10px;\">";
						
						$.each(data, function(key, value) {
							
							if(data[key][8] == 'F'){
								
								
								
								
							}else if(data[key][8] == 'P'){
								
								if (data[key][5] == '1') {
									
									
									if ((data.length - 1) == key) {
										
										dept2leftMenu += "<li style=\"color:#333; padding:0 5px 5px 10px\" id='lev3_leftSmenu_" + data[key][0] + "'><a href='#' id='lev3_leftSmenu_lk_" + data[key][0] + "' onClick='goContent(\"/"+serviceName+"/"+ data[key][3]
												+ "&lev1_parent_id=" + lev1_parent_id +"&lev2_parent_id=" + lev2_parent_id +"&lev3_parent_id=" + lev3_parent_id+"&currentId=" + data[key][0]+"&lev1_menuOpenId="+ lev1_menuOpenId +"&lev1_menuOpen=Y&lev2_menuOpenId="+ data[key][1] +"&lev2_menuOpen=Y\")'>"
												+ data[key][2] + "</a></li>";
	
									} else {
	
										dept2leftMenu += "<li style=\"color:#333; padding:0 5px 5px 10px\" id='lev3_leftSmenu_" + data[key][0] + "'><a href='#' id='lev3_leftSmenu_lk_" + data[key][0] + "' onClick='goContent(\"/"+serviceName+"/"+ data[key][3] + "&lev1_parent_id=" + lev1_parent_id +"&lev2_parent_id=" + lev2_parent_id +"&lev3_parent_id=" + data[key][1] +"&currentId=" + data[key][0]+"&lev1_menuOpenId="+ lev1_menuOpenId+"&lev1_menuOpen=Y&lev2_menuOpenId="+ data[key][1]+"&lev2_menuOpen=Y\")'>" + data[key][2]
												+ "</a></li>";	
									}	
									
									

								} else if (data[key][5] == '2') {
									
									if ((data.length - 1) == key) {

										dept2leftMenu += "<li style=\"color:#333; padding:0 5px 5px 10px\" id='lev3_leftSmenu_" + data[key][0] + "'><a href='#' id='lev3_leftSmenu_lk_" + data[key][0] + "' onclick='getPopUpPage(\"/"+serviceName+"/"+ data[key][3] + "\")'>"+ data[key][2] + "</a></li>";

									} else {

										dept2leftMenu += "<li style=\"color:#333; padding:0 5px 5px 10px\" id='lev3_leftSmenu_" + data[key][0] + "'><a href='#' id='lev3_leftSmenu_lk_" + data[key][0] + "' onclick='getPopUpPage(\"/"+serviceName+"/"+ data[key][3] + "\")'>" + data[key][2]	+ "</a></li>";

									}
								} 
							}else if(data[key][8] == 'R'){	
								
								if (data[key][5] == '1') {
									
									
									if ((data.length - 1) == key) {
										
										dept2leftMenu += "<li style=\"color:#333; padding:0 5px 5px 10px\" id='lev3_leftSmenu_" + data[key][0] + "'><a href='#' id='lev3_leftSmenu_lk_" + data[key][0] + "' onClick='goContent(\"/"+serviceName+"/"+ data[key][3]
												+ "&lev1_parent_id=" + lev1_parent_id +"&lev2_parent_id=" + lev2_parent_id +"&lev3_parent_id=" + lev3_parent_id+"&currentId=" + data[key][0]+"&lev1_menuOpenId="+ lev1_menuOpenId +"&lev1_menuOpen=Y&lev2_menuOpenId="+ data[key][1] +"&lev2_menuOpen=Y\")'>"
												+ data[key][2] + "</a></li>";
	
									} else {
	
										dept2leftMenu += "<li style=\"color:#333; padding:0 5px 5px 10px\" id='lev3_leftSmenu_" + data[key][0] + "'><a href='#' id='lev3_leftSmenu_lk_" + data[key][0] + "' onClick='goContent(\"/"+serviceName+"/"+ data[key][3] + "&lev1_parent_id=" + lev1_parent_id +"&lev2_parent_id=" + lev2_parent_id +"&lev3_parent_id=" + data[key][1] +"&currentId=" + data[key][0]+"&lev1_menuOpenId="+ lev1_menuOpenId+"&lev1_menuOpen=Y&lev2_menuOpenId="+ data[key][1]+"&lev2_menuOpen=Y\")'>" + data[key][2]
												+ "</a></li>";	
									}	
									
									

								} else if (data[key][5] == '2') {
									
									if ((data.length - 1) == key) {

										dept2leftMenu += "<li style=\"color:#333; padding:0 5px 5px 10px\" id='lev3_leftSmenu_" + data[key][0] + "'><a href='#' id='lev3_leftSmenu_lk_" + data[key][0] + "' onclick='getPopUpPage(\"/"+serviceName+"/"+ data[key][3] + "\")'>"+ data[key][2] + "</a></li>";

									} else {

										dept2leftMenu += "<li style=\"color:#333; padding:0 5px 5px 10px\" id='lev3_leftSmenu_" + data[key][0] + "'><a href='#' id='lev3_leftSmenu_lk_" + data[key][0] + "' onclick='getPopUpPage(\"/"+serviceName+"/"+ data[key][3] + "\")'>" + data[key][2]	+ "</a></li>";

									}
								} 
							}	
							
						});

						 dept2leftMenu += "</ul>";
						
						 $("#lev2_leftSmenu_" + lev3_parent_id).append(dept2leftMenu);
						

					},
					error : function() {
						
						$("#progress_modal").modal('hide');
						$(".progress").hide();
						//$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
						$("#session_modal").modal("show");	
						$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
						

					}
				});
				
			 
			 $('#lev2_leftSmenu_'+lev3_parent_id).attr("data-slide","false");
             
          
        } else {
        	
        	$("#lev2_leftSmenu_"+lev3_parent_id+" ul").remove();
     	    $('#lev2_leftSmenu_'+lev3_parent_id).attr("data-slide","true");
        
     	 
        }
}
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 서비스 이동 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function goContent(gotoPage) {

	$(location).attr('href', gotoPage);

}



function goMainContent(gotoPage) {

	$(location).attr('href', gotoPage);
	


}

function getPopUpPage(gotoPage){
	
	window.open(gotoPage, "newWin", "width=800, height=600, toolbar=no, scrollbars=yes, resizable = yes").focus();
	
	//$(this).attr("target", "_blank"); 
	//window.open(gotoPage);
	//$(location).attr('href', gotoPage);

	
}

function getPortlet(getId){
	
	var gotoPage = "/"+serviceName+"/portlet/view.do?reportId="+getId; 
	
	window.open(gotoPage, "newWin", "width=1050, height=780, toolbar=no, scrollbars=yes, resizable = yes");
	
	//$(this).attr("target", "_blank"); 
	//window.open(gotoPage);
	//$(location).attr('href', gotoPage);

	
}



/******************************* 큐릭뷰 이동 *******************************/


function getPopUpQlikviewPage(gotoPage){
	
	
	var url = "/" + serviceName + "/qlikview/getQlikViewUser.do";



	$.ajax({

		url : url,
		type : "post",
		data : {},
		datatype : 'json',
		beforeSend : function() {

		},

		success : function(data) {
			
		
			
			if(data['bi_qlikview_user_id'] != "" && data['bi_qlikview_user_id'] != null){
				
				
				qlikviewLoginInfo(data['bi_qlikview_user_id'],data['bi_qlikview_user_pwd'], gotoPage);
				
			}

			//

		},
		error : function() {
			
			$("#progress_modal").modal('hide');
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
			

		}

	});
	
	
	
	
	
	//$(this).attr("target", "_blank"); 
	//window.open(gotoPage);
	//$(location).attr('href', gotoPage);

	
}


function qlikviewLoginInfo(qlikview_id,qlikview_pwd,gotoPage){
	
	
	
	
	var url = "/" + serviceName + "/qlikview/qlikviewLoginInfo.do";



	$.ajax({

		url : url,
		type : "post",
		data : {bi_qlikview_user_id:qlikview_id},
		datatype : 'json',
		beforeSend : function() {

		},

		success : function(data) {
			
			
			
			if(data.size > 0 && data.loginStatus == 'N'){ // 다른 사용자가 있을 경우 
				
				
				var yn = confirm("현재  다른 사용자가 이용하고 있습니다.확인을 누르시면   다른 사용자 접속 권한을 잃을수 있습니다. 해당 보고서를 확인 하시겠습니까?");
				
				if(yn == true){
					
					// 해당 사용자 삭제
					$("#qlikviewUrl").attr("src",qlikViewUrl+"/qlikview/formLogin1.htm?userNm=" + qlikview_id + "&userPw="+ qlikview_pwd);
					
					// 해당 사용자 삭제
					qlikviewInfoDel(qlikview_id);
					// 해당 유저 입력 
					qlikviewInfoSave(qlikview_id);
					// 클릭뷰 오픈 
					
                    // 2초뒤에 팝업 개시 
					setTimeout(function(){
						
						window.open(gotoPage, "newWin", "width=1050, height=780, toolbar=no, scrollbars=yes, resizable = yes").focus();
						
					}, 2000);
					
				}
				
				
				
				
			}else if(data.size > 0 && data.loginStatus == 'Y'){ // 내가 접속햇을경우 
				
				
				window.open(gotoPage, "newWin", "width=1050, height=780, toolbar=no, scrollbars=yes, resizable = yes").focus();
				
				
				
			}else{ // 처음 접속 했을경우 
				
				// 해당 사용자 삭제
				qlikviewInfoDel(qlikview_id);
				// 해당 유저 입력 
				qlikviewInfoSave(qlikview_id);
				// 클릭뷰 오픈
				
				
				$("#qlikviewUrl").attr("src",qlikViewUrl+"/qlikview/formLogin1.htm?userNm=" + qlikview_id + "&userPw="+ qlikview_pwd);
								
				
				 // 2초뒤에 팝업 개시 
				setTimeout(function(){
					
					window.open(gotoPage, "newWin", "width=1050, height=780, toolbar=no, scrollbars=yes, resizable = yes").focus();
					
				}, 2000);
				
				
			}

			//$("#qlikviewUrl").attr("src","http://10.59.32.254:9090/qlikview/formLogin1.htm?userNm=" + data['bi_qlikview_user_id'] + "&userPw="+ data['bi_qlikview_user_pwd']);

		},
		error : function() {
			
			$("#progress_modal").modal('hide');
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
			

		}
	  });
	
	
	
}

function qlikviewInfoSave(qlikviewId){
		
	var url = "/" + serviceName + "/qlikview/qlikviewLoginInfoSave.do";
	$.ajax({

		url : url,
		type : "post",
		data : {bi_qlikview_user_id:qlikviewId},
		dataType : 'json',
		beforeSend : function() {

		},

		success : function(data) {
			
			
			

			//$("#qlikviewUrl").attr("src","http://10.59.32.254:9090/qlikview/formLogin1.htm?userNm=" + data['bi_qlikview_user_id'] + "&userPw="+ data['bi_qlikview_user_pwd']);

		},
		error : function() {
			
			$("#progress_modal").modal('hide');
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
			

		}
	  });
		
}

function qlikviewInfoDel(qlikviewId){
	
	
	var url = "/" + serviceName + "/qlikview/qlikviewLoginInfoDelete.do";
	
		
		$.ajax({

			url : url,
			type : "post",
			data : {bi_qlikview_user_id:qlikviewId},
			dataType : 'json',
			beforeSend : function() {

			},

			success : function(data) {

				

			},
			error : function() {
				
				$("#progress_modal").modal('hide');
				$(".progress").hide();
				$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
				$("#session_modal").modal("show");	
				$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
				

			}
		  });
	
}




function getMenuList(id,selectId,clearSelect) {

	
	
	for(var i=0;i < clearSelect.length;i++){
		
		$("#"+clearSelect[i]+" option").remove();
		
	}
	
    var url =  "/"+ serviceName +"/menu/getRoleReportListData.do";
	$.ajax({

		url : url
		,type : "post"
		,data : {bi_portal_menu_parent_id : id, bi_menu_type_yn : 'R'}
		,datatype : 'json',
		success : function(data) {
			
			//data[key][0] -ID
    		//data[key][1] -parent
    		//data[key][2] -name
    		//data[key][3] -url
			//data[key][4] -order
			//data[key][5] -menuType
			
			//$("#"+selectId+" option").remove();
			$("#"+selectId).append("<option value=''>:::::: 선택하세요 :::::::: </option>");
			$.each(data, function(key, value) {
				
				$("#"+selectId).append("<option value='"+data[key][0]+"'>"+data[key][2]+"</option>");
				
			});
		},
		error : function() {
			
			
			$("#progress_modal").modal('hide');
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
			

		}
	});

}


/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *************************************  권한별 메뉴 명 리턴  ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function getMenuNm(id,viewId) {

	 var url =  "/"+ serviceName +"/menu/getViewData.do";
	$.ajax({

		url : url
		,type : "post"
		,data : {bi_portal_menu_id : id}
		,datatype : 'json',
		success : function(data) {
				
		   $(viewId).html(data['bi_menu_nm']);
		},
		error : function() {
			
			
			$("#progress_modal").modal('hide');
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
			

		}
	});

}




/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 메뉴 리스트 select ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function getMenuSelected(id,selectId,val) {
	
	 var url =  "/"+ serviceName +"/menu/getRoleReportListData.do";
	 
	$.ajax({

		url : url
		,type : "post"
		,data : {bi_portal_menu_parent_id : id ,bi_menu_type_yn : 'R'}
		,datatype : 'json',
		success : function(data) {
			
			//data[key][0] -ID
    		//data[key][1] -parent
    		//data[key][2] -name
    		//data[key][3] -url
			//data[key][4] -order
			//data[key][5] -menuType
			
			//$("#"+selectId+" option").remove();
			$("#edit_"+selectId).empty();
			$("#edit_"+selectId).append("<option value=''>:::::: 선택하세요 :::::::: </option>");
			$.each(data, function(key, value) {
				$("#edit_"+selectId).append("<option value='"+data[key][0]+"'>"+data[key][2]+"</option>");
			});
			
			$("#edit_"+selectId).val(val);
			
			
		},
		error : function() {
			
			
			$("#progress_modal").modal('hide');
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
			

		}
	});

}


   
function getQulickViewLink() {

	var url = "/" + serviceName + "/qlikview/getQlikViewUser.do";



	$.ajax({

		url : url,
		type : "post",
		data : {},
		datatype : 'json',
		beforeSend : function() {

		},

		success : function(data) {

			$("#qlikviewUrl").attr("src",qlikViewUrl+"/qlikview/formLogin1.htm?userNm=" + data['bi_qlikview_user_id'] + "&userPw="+ data['bi_qlikview_user_pwd']);

		},
		error : function() {
			
			
			$("#progress_modal").modal('hide');
			$(".progress").hide();
			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			$("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
			

		}

	});

}


	




