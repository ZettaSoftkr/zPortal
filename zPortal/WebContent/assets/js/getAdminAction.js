/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *************************************  관리자페이지 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */


function gotoSubPage(url, menuId){
	
	  var gotoPage =  "/"+ serviceName +"/"+url+"&lev1_parent_id="+menuId+"&currentId="+menuId;		 
	  $(location).attr('href', gotoPage);
	
}

/** *********************************************************************************************************** */
/** *************************************  관리자페이지  가기****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function goAdminPage(menuId) {
	
	    var gotoPage =  "/"+ serviceName +"/admin/menu/gotoPage.do?pageNm=list&lev1_parent_id="+menuId+"&currentId="+menuId;
	 
		$(location).attr('href', gotoPage);

}


/** *************************************  My Page  가기****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function goMyPage(parentId,currentId) {
	
    var gotoPage =  "/"+ serviceName +"/mypage/bookMark/gotoPage.do?pageNm=list&lev1_parent_id="+parentId+"&currentId="+currentId;
 
	$(location).attr('href', gotoPage);

}


/** *************************************  정보마당  가기****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function goCommunity(menuId,currentId) {
	
    var gotoPage =  "/"+ serviceName +"/notice/gotoPage.do?pageNm=list&lev1_parent_id="+menuId+"&currentId="+currentId;
	$(location).attr('href', gotoPage);

}

/** *************************************  사이트 맵가기  ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function goSiteMap(parentId,currentId) {
	
    var gotoPage =  "/"+ serviceName +"/siteMap/gotoPage.do?pageNm=list&lev1_parent_id="+currentId+"&currentId="+currentId;
	$(location).attr('href', gotoPage);

}

/** *************************************  보고서 검색 바로가기  ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function goSearch(currentId) {
	
    var gotoPage =  "/"+ serviceName +"/search/gotoPage.do?pageNm=searchList&lev1_parent_id="+currentId+"&currentId="+currentId;
	$(location).attr('href', gotoPage);

}
/** *************************************  색인 검색 바로가기  ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function goIndexSearch(currentId) {
	
    var gotoPage =  "/"+ serviceName +"/indexSearch/gotoPage.do?pageNm=list&lev1_parent_id="+currentId+"&currentId="+currentId;
	$(location).attr('href', gotoPage);

}

/** *************************************  로그아웃 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function logout() {
	
	
	// qliview 사용자 삭제 
	// var formURL = $(this).attr("action");
	/*
	$.ajax({
		url :  "/"+ serviceName +"/qlikview/qlikViewDeleteUser.do",
		type : "post",		
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {
			 
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	*/
	// 로그아웃 
	
  
		 
    var gotoPage =  "/"+ serviceName +"/j_spring_security_logout";
    $(location).attr('href', gotoPage);
	
   
    window.close();

}

/** *************************************  QlikView 가기 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function goQulickView() {


	window.open(qlikViewUrl+"/qlikview/index.htm", "_blank");
}


/** *****************************************  Home 가기 ******************************************************* */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function goHome() {

	var url = "/" + serviceName+"/jsp/main.jsp";
	$(location).attr('href', url);

}
/** *******************************************  관리콘솔  ******************************************************* */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function goQMC() {
	
	var gotoPage =  "http://10.59.32.175:4780/QMC";
	window.open(gotoPage, "_blank");
}

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 등록모달 페이지 클릭시 로그 기록*********************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** 
  -- 공지사항
  
 *********************************************************************************************************** */
// 등록
$("#writeForm_button").click(function() {
	
	
	if(aliasName2 == "userInfo"){
			
		    var deptUrl = "/" + serviceName + "/admin/dept/getDeptListData.do";
		 	
			$.ajax({
		
				url : deptUrl,
				type : "post",
				//	 		data : {
				//	 			bi_parent_id : "ajson1"
				//	 		},
				datatype : 'json',
				success : function(data) {
					
					$("#bi_dept_id").append("<option value=''>선택</option>");
					$.each(data, function(key, value) {
								$("#bi_dept_id").append("<option value='"+ this['bi_dept_id']+ "'>"	+ this['bi_deptnm']+ "</option>");
					});
					
				
		
				},
				error : function() {
		
					alert("false");
		
				}
			});
		
		}
	
});


/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *****************************************    저장처리         ************************************************ */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/* 
 *  공지사항
 */
$("#writeForm").submit(function(e) {	   	
	
//	$(this).validate();
//	$(this).attr('action', actionLink.save); 

	var postData = $(this).serializeArray();
  	//alert(JSON.stringify(postData));
	// var formURL = $(this).attr("action");
	$.ajax({
		url : actionLink.save,
		type : "POST",
		data : postData,
		beforeSend : function() { //			
		
			
		},
		success : function(data, textStatus, jqXHR) {
			
			
			
			$("#msg").html("저장 되었습니다.");
			$("#msg_modal").modal('show');
			
			$("#write_modal").modal('hide');
			
			$("form").each(function(){				
				
				  this.reset();
		
			});
			
			
			goAdminListPage();
			
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
			
			alert(jqXHR);
			alert(textStatus);
			alert(errorThrown);
		}
	});
	e.preventDefault(); // STOP default action
	//e.unbind(); // unbind. to stop multiple form submit.

});

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *********************************************** 수정처리 ***************************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/*
 * 공지사항
 */


$("#editForm").submit(function(e) {
	
	$(this).validate();
	var postData = $(this).serializeArray();
	$.ajax({
		url : actionLink.modify,
		type : "POST",
		data : postData,
		//dataType:"json",
		beforeSend : function() { //

		},
		success : function(data) {
			
			$("#msg").html("수정 되었습니다.");
			$("#msg_modal").modal('show');
		
			$("#edit_modal").modal('hide');
			$("form").each(function(){
				  this.reset();
			});
			
			
			 getAdminEditPage(keyVal);
					
		},
//		error : function(jqXHR, textStatus, errorThrown) {
//			// if fails
//		}
	});
	e.preventDefault(); // STOP default action
	e.unbind(); // unbind. to stop multiple form submit.
});







//*****************************************************************************************************************//
//*************************************** 관리실 선택 삭제 이벤트 **********************************************************//
//*****************************************************************************************************************//
//*****************************************************************************************************************//
/*****************************************************************************************************************
* 공지사항
* 
* 
*****************************************************************************************************************/
$("#allDel_button").click(function(){
	

if($("#checkId:checked").length > 0){
	 
	 var postData = $("#listForm").serializeArray();
	 
	 $.ajax({
			url : actionLink.deleteAll,
			type : "post",
			data : postData,
			beforeSend : function() { //

			},
			success : function(data, textStatus, jqXHR) { 				
				
				
				$("#msg").html("삭제되었습니다.");
				$("#msg_modal").modal("show");	
				
				
				var currentPage = 1;
				var searchtitle = 0;
				var searchContent = "";
				
				getListPageData(currentPage,searchtitle,searchContent);
				

			},
			error : function(jqXHR, textStatus, errorThrown) {
				// if fails
			}
		});
	 
	 
	 
}else{
	 
	$("#msg").html("선택된 항목이 없습니다");
	$("#msg_modal").modal("show");	
	return;
	 
}

	 
});

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 삭제 페이지 이동 ********************************************************* */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/*
 * 공지사항
 */

$("#goAdminDeleteForm_btn").click(function() {
	
	var id = $("#keyVal").val(); 
	var postData = attrKey+"="+id ;	
	
	
	if(confirm("삭제 하시겠습니까?")){
	
		$.ajax({
			url : actionLink.del
			,type : "POST"
			,data : postData
			,dataType: "json"
			,beforeSend : function() { //
	
			}
			,success : function(data, textStatus, jqXHR) {
				
				$("#msg").html("삭제되었습니다");
				$("#msg_modal").modal('show');
				
				goAdminListPage();

				
				 
			}
			,error : function(jqXHR, textStatus, errorThrown) {
				// if fails
			}
		});
	
	}else{
		
		return;
		
	}
	
});

  function getDelPage(colNm){
	
	
	var result = confirm("선택된 항목을 삭제 하시겠습니까?");
	if (result) {
		
		var id = $("#view_"+colNm).val();
		
		var postData = getId+"="+id ;	
		$.ajax({
			url : actionLink.del,
			type : "post",
			data : postData,
			beforeSend : function() { //

			},
			success : function(data, textStatus, jqXHR) {
				
				$("#msg").html("삭제되었습니다.");
				$("#msg_modal").modal('show');
				
				$("#listContent").show();
				$("#viewContent").hide();
				 getListPageData(1);
				 
			},
			error : function(jqXHR, textStatus, errorThrown) {
				// if fails
			}
		});
		

	} else {

		return;
	}
	
	
	
}
  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */
  /** *************************************** 페이지 이동************************************************* */
  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */
  $("#goAdminListForm_btn").click(function(e) {	
	  
	  
	    var gotoPage =  "/"+ serviceName +"/"+aliasName1+"/"+aliasName2+"/"+pageLink.listPage;
	  	gotoPage +="&lev1_parent_id="+lev1_parent_id;
	  	gotoPage +="&lev2_parent_id="+lev2_parent_id;
	  	gotoPage +="&lev3_parent_id="+lev3_parent_id;
	  	gotoPage +="&lev1_menuOpenId="+lev1_menuOpenId;
	  	gotoPage +="&lev2_menuOpenId="+lev2_menuOpenId;
	  	gotoPage +="&lev1_menuOpen="+lev1_menuOpen;
	  	gotoPage +="&lev2_menuOpen="+lev2_menuOpen;
	  	gotoPage +="&currentId="+currentId;
	  	
	  	$(location).attr('href', gotoPage);
	 
	  
	  
  });
  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */
  /** *************************************** 페이지 이동************************************************* */
  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */
  $("#goAdminWriteForm_btn").click(function() {	
	  
	  
	    var gotoPage =  "/"+ serviceName +"/"+aliasName1+"/"+aliasName2+"/"+pageLink.writePage;
	  	gotoPage +="&lev1_parent_id="+lev1_parent_id;
	  	gotoPage +="&lev2_parent_id="+lev2_parent_id;
	  	gotoPage +="&lev3_parent_id="+lev3_parent_id;
	  	gotoPage +="&lev1_menuOpenId="+lev1_menuOpenId;
	  	gotoPage +="&lev2_menuOpenId="+lev2_menuOpenId;
	  	gotoPage +="&lev1_menuOpen="+lev1_menuOpen;
	  	gotoPage +="&lev2_menuOpen="+lev2_menuOpen;
	  	gotoPage +="&currentId="+currentId;
	  	
	  	$(location).attr('href', gotoPage);
	 
	  
	  
  });
  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */
  /** *************************************** 페이지 이동************************************************* */
  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */
  $("#goAdminEditForm_btn").click(function(e) {	
	  
	  
	    var gotoPage =  "/"+ serviceName +"/"+aliasName1+"/"+aliasName2+"/"+pageLink.editPage;
	  	gotoPage +="&lev1_parent_id="+lev1_parent_id;
	  	gotoPage +="&lev2_parent_id="+lev2_parent_id;
	  	gotoPage +="&lev3_parent_id="+lev3_parent_id;
	  	gotoPage +="&lev1_menuOpenId="+lev1_menuOpenId;
	  	gotoPage +="&lev2_menuOpenId="+lev2_menuOpenId;
	  	gotoPage +="&lev1_menuOpen="+lev1_menuOpen;
	  	gotoPage +="&lev2_menuOpen="+lev2_menuOpen;
	  	gotoPage +="&currentId="+currentId;
	  	gotoPage +="&keyVal="+keyVal;
	  	
	  	
	  	$(location).attr('href', gotoPage);
	 
	  
	  
  });
  
  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */
  /** *************************************** 페이지 이동************************************************* */
  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */
  $("#goAdminViewForm_btn").click(function(e) {	
	  
	  
	    var gotoPage =  "/"+ serviceName +"/"+aliasName1+"/"+aliasName2+"/"+pageLink.viewPage;
	  	gotoPage +="&lev1_parent_id="+lev1_parent_id;
	  	gotoPage +="&lev2_parent_id="+lev2_parent_id;
	  	gotoPage +="&lev3_parent_id="+lev3_parent_id;
	  	gotoPage +="&lev1_menuOpenId="+lev1_menuOpenId;
	  	gotoPage +="&lev2_menuOpenId="+lev2_menuOpenId;
	  	gotoPage +="&lev1_menuOpen="+lev1_menuOpen;
	  	gotoPage +="&lev2_menuOpen="+lev2_menuOpen;
	  	gotoPage +="&currentId="+currentId;
	  	gotoPage +="&keyVal="+keyVal;
	  	
	  	$(location).attr('href', gotoPage);
	 
	  
	  
  });
  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */
  /** *************************************** 정보마당 > 자료실 > 등록(파일업로드) ************************************************* */
  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */

  $("#BoardWriteForm").submit(function(e) {	
  	
  	$(this).validate();
    $(this).attr('action', actionLink.save); 

  });

  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */
  /** *************************************** 정보마당 > 자료실 >수정(파일업로드) ************************************************* */
  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */

  $("#BoardEditForm").submit(function(e) {	
  	
  	$(this).validate();
    $(this).attr('action', actionLink.modify); 
      

  });


  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */
  /** *****************************************     목록페이지 이동하기           *********************************** */
  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */

  function goAdminListPage(){
  	  
  	
  	var gotoPage =  "/"+ serviceName +"/"+aliasName1+"/"+aliasName2+"/"+pageLink.listPage;	  	
  	gotoPage +="&lev1_parent_id="+lev1_parent_id;
  	gotoPage +="&lev2_parent_id="+lev2_parent_id;
  	gotoPage +="&lev3_parent_id="+lev3_parent_id;
  	gotoPage +="&lev1_menuOpenId="+lev1_menuOpenId;
  	gotoPage +="&lev2_menuOpenId="+lev2_menuOpenId;
  	gotoPage +="&lev1_menuOpen="+lev1_menuOpen;
  	gotoPage +="&lev2_menuOpen="+lev2_menuOpen;
  	gotoPage +="&currentId="+currentId;
  	$(location).attr('href', gotoPage);
  	
  }

  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */
  /** *****************************************     등록페이지 이동하기           *********************************** */
  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */

  function goAdminWritePage(id){
  	  
  	
  	var gotoPage =  "/"+ serviceName +"/"+aliasName1+"/"+aliasName2+"/"+pageLink.writePage;	
  	gotoPage +="&keyVal="+id;	
  	gotoPage +="&lev1_parent_id="+lev1_parent_id;
  	gotoPage +="&lev2_parent_id="+lev2_parent_id;
  	gotoPage +="&lev3_parent_id="+lev3_parent_id;
  	gotoPage +="&lev1_menuOpenId="+lev1_menuOpenId;
  	gotoPage +="&lev2_menuOpenId="+lev2_menuOpenId;
  	gotoPage +="&lev1_menuOpen="+lev1_menuOpen;
  	gotoPage +="&lev2_menuOpen="+lev2_menuOpen;
  	gotoPage +="&currentId="+currentId;
  	$(location).attr('href', gotoPage);
  	
  }

  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */
  /** *****************************************  수정페이지 이동하기    *********************************************** */
  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */
  function goAdminEditPage(id){
  	
  	
  	var gotoPage =  "/"+ serviceName +"/"+aliasName1+"/"+aliasName2+"/"+pageLink.editPage;	 
  	gotoPage +="&keyVal="+id;	
  	gotoPage +="&lev1_parent_id="+lev1_parent_id;
  	gotoPage +="&lev2_parent_id="+lev2_parent_id;
  	gotoPage +="&lev3_parent_id="+lev3_parent_id;
  	gotoPage +="&lev1_menuOpenId="+lev1_menuOpenId;
  	gotoPage +="&lev2_menuOpenId="+lev2_menuOpenId;
  	gotoPage +="&lev1_menuOpen="+lev1_menuOpen;
  	gotoPage +="&lev2_menuOpen="+lev2_menuOpen;
  	gotoPage +="&currentId="+currentId;
  	
  	
  	$(location).attr('href', gotoPage);
  	
  }



  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */
  /** *****************************************     상세보기페이지 이동하기           *********************************** */
  /** *********************************************************************************************************** */
  /** *********************************************************************************************************** */

  function goAdminViewPage(id){
  	  
  	
  	var gotoPage =  "/"+ serviceName +"/"+aliasName1+"/"+aliasName2+"/"+pageLink.viewPage;	
  	gotoPage +="&keyVal="+id;	
  	gotoPage +="&lev1_parent_id="+lev1_parent_id;
  	gotoPage +="&lev2_parent_id="+lev2_parent_id;
  	gotoPage +="&lev3_parent_id="+lev3_parent_id;
  	gotoPage +="&lev1_menuOpenId="+lev1_menuOpenId;
  	gotoPage +="&lev2_menuOpenId="+lev2_menuOpenId;
  	gotoPage +="&lev1_menuOpen="+lev1_menuOpen;
  	gotoPage +="&lev2_menuOpen="+lev2_menuOpen;
  	gotoPage +="&currentId="+currentId;
  	
  	$(location).attr('href', gotoPage);
  	
  }

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 저장처리 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
$("#writeAdminForm").submit(function(e) {	
   	
	
	$(this).validate();
	var postData = $(this).serializeArray();
//	alert(JSON.stringify(postData));
	// var formURL = $(this).attr("action");
	$.ajax({
		url : actionLink.save,
		type : "POST",
		data : postData,
		beforeSend : function() { //
			
			
//			$("#progress_modal").modal('show');		
//			$(".progress").show();
//			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();

		},
		success : function(data, textStatus, jqXHR) {
			
//			$("#progress_modal").modal('hide');		
//			$(".progress").hide();
//			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			
			$("#msg").html("저장 되었습니다.");
			$("#msg_modal").modal('show');
		
			$("#write_modal").modal('hide');
			$("form").each(function(){
				
				
				  this.reset();
		
			});
			
			var currentPage = 1;
			var searchtitle = 0;
			var searchContent = "";
			
			getAdminListPageData(currentPage, searchtitle, searchContent);
			
			
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	e.preventDefault(); // STOP default action
	e.unbind(); // unbind. to stop multiple form submit.
});




/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *************************************  수정 처리 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */


$("#editAdminForm").submit(function(e) {
	
	$(this).validate();
	var postData = $(this).serializeArray();
	

	$.ajax({
		url : actionLink.modify,
		type : "post",
		data : postData,
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {
			
			
			$("#msg").html("수정 되었습니다.");
			$("#msg_modal").modal('show');
			$("#edit_modal").modal('hide');
			$("form").each(function(){
				  this.reset();
			});
			
			
			var currentPage = 1;
			var searchtitle = 0;
			var searchContent = "";
			
			getAdminListPageData(currentPage, searchtitle, searchContent);
			
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	e.preventDefault(); // STOP default action
	e.unbind(); // unbind. to stop multiple form submit.
});



///*아이디 체크 *////

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 사용자 중복체크 ********************************************************* */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */


$("#idCheck").click(function() {
	
	$(this).validate();
	
	var bi_unity_cust_id = $("#bi_unity_cust_id").val();	
	if(bi_unity_cust_id == ""){
		
		$("#msg").html("아이디를 입력해주세요.");
		$("#msg_modal").modal('show');
		 return
	}
	$.ajax({
		url : "/"+serviceName+"/userInfo/getCheckId.do",
		type : "POST",
		data : {bi_unity_cust_id:bi_unity_cust_id},
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {	
					
				//alert(JSON.stringify(data));
				//alert(data.length);
				
				if(data != null){
					
					$("#msg").html("이미 등록된 아이디 입니다.");
					$("#msg_modal").modal('show');
					$(this).attr("data-keycolumn","false");
					$("#tf_id").val("false");
					
					$("#bi_unity_cust_id").val("");
					
					
					
				}else{
					
					$("#msg").html("사용 가능한 아이디 입니다.");
					$("#msg_modal").modal('show');
					$(this).attr("data-keycolumn","true");
					$("#tf_id").val("true");
					
					
				}
		
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	e.preventDefault(); // STOP default action
	e.unbind(); // unbind. to stop multiple form submit.
});

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 조직ID 중복체크 ********************************************************* */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */


$("#deptIdCheck").click(function() {
	
	$(this).validate();
	
	var bi_dept_id = $("#bi_dept_id").val();
	
	if(bi_dept_id == ""){
		
		$("#msg").html("아이디를 입력해주세요.");
		$("#msg_modal").modal('show');
		 return
	}

	$.ajax({
		url : "/"+serviceName+"/dept/getCheckId.do",
		type : "POST",
		data : {bi_dept_id:bi_dept_id},
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {	
					
				//alert(JSON.stringify(data));
				//alert(data.length);
				
				if(data != null){
					
					$("#msg").html("이미 등록된 아이디 입니다.");
					$("#msg_modal").modal('show');
					$(this).attr("data-keycolumn","false");
					$("#tf_id").val("false");
					
					$("#bi_unity_cust_id").val("");
					
					
					
				}else{
					
					$("#msg").html("사용 가능한 아이디 입니다.");
					$("#msg_modal").modal('show');
					$(this).attr("data-keycolumn","true");
					$("#tf_id").val("true");
					
					
				}
		
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	e.preventDefault(); // STOP default action
	e.unbind(); // unbind. to stop multiple form submit.
});
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *************************************   사용자 등록처리  ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

$("#writeUserForm").submit(function(e) {	
   	
	
	$(this).validate();
	
	
	if( $("#tf_id").val() == "false" ){
		
		$("#msg").html("아이디 중복체크를 해주세요.");
		$("#msg_modal").modal('show');
		
		e.preventDefault(); // STOP default action
		e.unbind(); // unbind. to stop multiple form submit.
		return
		 
	}else{
			
		var postData = $(this).serializeArray();
	//	alert(JSON.stringify(postData));
		// var formURL = $(this).attr("action");
		$.ajax({
			url : actionLink.save,
			type : "POST",
			data : postData,
			beforeSend : function() { //
				
				
	//			$("#progress_modal").modal('show');		
	//			$(".progress").show();
	//			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();
	
			},
			success : function(data, textStatus, jqXHR) {
				
	//			$("#progress_modal").modal('hide');		
	//			$(".progress").hide();
	//			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
				
				$("#msg").html("저장 되었습니다.");
				$("#msg_modal").modal('show');
			
				$("#write_modal").modal('hide');
				$("form").each(function(){
					
					  this.reset();
				});
				
				var currentPage = 1;
				var searchtitle = 0;
				var searchContent = "";
				
				getAdminListPageData(currentPage, searchtitle, searchContent);
				
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
				// if fails
			}
		});
		e.preventDefault(); // STOP default action
	//	e.unbind(); // unbind. to stop multiple form submit.
		
	
	}
});

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *************************************   조직 등록처리  ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

$("#writeAdminDeptForm").submit(function(e) {	
   	
	
	$(this).validate();
	
	
	if( $("#tf_id").val() == "false" ){
		
		$("#msg").html("아이디 중복체크를 해주세요.");
		$("#msg_modal").modal('show');
		
		e.preventDefault(); // STOP default action
		e.unbind(); // unbind. to stop multiple form submit.
		 return
		 
	}else{
			
		var postData = $(this).serializeArray();
	//	alert(JSON.stringify(postData));
		// var formURL = $(this).attr("action");
		$.ajax({
			url : actionLink.save,
			type : "POST",
			data : postData,
			beforeSend : function() { //
				
				
	//			$("#progress_modal").modal('show');		
	//			$(".progress").show();
	//			$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();
	
			},
			success : function(data, textStatus, jqXHR) {
				
	//			$("#progress_modal").modal('hide');		
	//			$(".progress").hide();
	//			$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
				
				$("#msg").html("저장 되었습니다.");
				$("#msg_modal").modal('show');
			
				$("#write_modal").modal('hide');
				$("form").each(function(){
					
					
					  this.reset();
			
				});
				
				var currentPage = 1;
				var searchtitle = 0;
				var searchContent = "";
				
				getAdminListPageData(currentPage, searchtitle, searchContent);
				deptUserInit(); //tree 보기 
				
				
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
				// if fails
			}
		});
		e.preventDefault(); // STOP default action
		e.unbind(); // unbind. to stop multiple form submit.
		
	
	}
});

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *************************************  조직 수정 하기  ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */


$("#editAdminDeptForm").submit(function(e) {
	
	$(this).validate();
	var postData = $(this).serializeArray();
	

	$.ajax({
		url : actionLink.modify,
		type : "post",
		data : postData,
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {
			
			
			$("#msg").html("수정 되었습니다.");
			$("#msg_modal").modal('show');
			$("#edit_modal").modal('hide');
			$("form").each(function(){
				  this.reset();
			});
			
			
			var currentPage = 1;
			var searchtitle = 0;
			var searchContent = "";
			
			getAdminListPageData(currentPage, searchtitle, searchContent);
			deptUserInit(); //tree 보기 
			
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	e.preventDefault(); // STOP default action
	e.unbind(); // unbind. to stop multiple form submit.
});

$("#comment_submit").click(function(e) {

   var postData = $("#commentForm").serializeArray();
	// var formURL = $(this).attr("action");
//alert(JSON.stringify(postData));
	var commentUrl = "/" + serviceName + "/comment/" + actionLink.save;
	//alert(commentUrl);
	$.ajax({
		url : commentUrl,
		type : "post",
		data : postData,
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {
			// $("#comment_modal").modal('hide');
			getCommentList(postData[0].value);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	e.preventDefault(); // STOP default action
	e.unbind(); // unbind. to stop multiple form submit.
});

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *************************************댓글 삭제 구성 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function getCommentDelete(bi_bbs_ansr_sn,bi_bbs_sn) {

	var listLink = "/" + serviceName + "/comment/" + actionLink.del;
	
	$.ajax({

				url : listLink,
				type : "POST",
				data : {
					bi_bbs_ansr_sn : bi_bbs_ansr_sn
				},
				dataType : 'json',
				beforeSend : function() {

				},

				success : function(data) {
					
					$("#msg").html(data);
					$("#msg_modal").modal('show');

					getCommentList(bi_bbs_sn);

					
				},
				error : function() {
					alert("false");

				}

			});

}



/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *********************************************** QNA 답변 하기 ***************************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

$("#answer_submit").click(function(e) {
	
	var postData = $("#viewForm").serializeArray();
	var listLink = "/" + serviceName + "/admin/qna/answerSave.do";
	

	$.ajax({
		url : listLink,
		type : "post",
		data : postData,
		//dataType: "json",
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {
			
			$("#msg").html("수정 되었습니다.");
			$("#msg_modal").modal('show');
			
			getAdminViewPage($("#keyVal").val());
			
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	e.preventDefault(); // STOP default action
	//e.unbind(); // unbind. to stop multiple form submit.
});


/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/**
 * ************************************* QNA 답변 처리
 * ******************************
 */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

$("#qnaForm").submit(function(e) {

	var postData = $(this).serializeArray();
	// alert(postData[2].value);
	// alert(JSON.stringify(postData));

	var commentUrl = "/" + serviceName + "/comment/" + actionLink.save;

	$.ajax({
		url : commentUrl,
		type : "post",
		data : postData,
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {

			getCommentList(postData[0].value, aliasName2);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	e.preventDefault(); // STOP default action
	e.unbind(); // unbind. to stop multiple form submit.
});






$("#all_delete").click(function(){
	
    
    if($("#checkId:checked").length > 0){
   	 
   	 var postData = $("#listForm").serializeArray();
   	 
   	 $.ajax({
   			url : actionLink.deleteAll,
   			type : "post",
   			data : postData,
   			beforeSend : function() { //

   			},
   			success : function(data, textStatus, jqXHR) {
   				
   			
   				
   				$("#msg").html(data);
   				$("#msg_modal").modal("show");	
   				
   				var searchtitle = 0;
   				var searchContent = "";	
   				getListPageData(currentPage,searchtitle,searchContent);
   				

   			},
   			error : function(jqXHR, textStatus, errorThrown) {
   				// if fails
   			}
   		});
   	 
   	 
   	 
    }else{
   	 
    	$("#msg").html("선택된 항목이 없습니다");
		$("#msg_modal").modal("show");	
    	
  
   	return;
   	 
    }
    
	 
});




/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 삭제 페이지 이동 ********************************************************* */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function deleteForm(getId) {


	var id = $("#view_"+getId).val();	
	var postData = getId+"="+id;
	
	
	
	

	// var formURL = $(this).attr("action");
	$.ajax({
		url : actionLink.del,
		type : "post",
		data : postData,
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {
			
			$("#msg").html("삭제되었습니다.");
			$("#msg_modal").modal('show');
			
			$("#viewContent").hide();
			$("#listContent").show();			
			var currentPage = 1;
			var searchtitle = 0;
			var searchContent = "";	
			getListPageData(currentPage,searchtitle,searchContent);
			 
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	
};




//*****************************************************************************************************************//
//*************************************** 관리실 선택 삭제 이벤트 **********************************************************//
//*****************************************************************************************************************//
//*****************************************************************************************************************//
/*****************************************************************************************************************
 * 공지사항
 * 
 * 
*****************************************************************************************************************/
$("#all_AdminDelete").click(function(){
	
	
    if($("#checkId:checked").length > 0){
   	 
   	 var postData = $("#listForm").serializeArray();
   	 
   	 $.ajax({
   			url : actionLink.deleteAll,
   			type : "post",
   			data : postData,
   			beforeSend : function() { //

   			},
   			success : function(data, textStatus, jqXHR) {
   				
   			
   			
   				$("#msg").html("삭제 되었습니다.");
				$("#msg_modal").modal('show');
				
				$("form").each(function(){
					  this.reset();
				});
				
				var currentPage = 1;
				var searchtitle = 0;
				var searchContent = "";
				
				getAdminListPageData(currentPage, searchtitle, searchContent);
				//getAdminListPageData(currentPage, searchTitle, searchContent);
				
   				

   			},
   			error : function(jqXHR, textStatus, errorThrown) {
   				// if fails
   			}
   		});
   	 
   	 
   	 
    }else{
   	 
    	$("#msg").html("선택된 항목이 없습니다");
		$("#msg_modal").modal("show");	
   	return;
   	 
    }
    
	 
});

//*****************************************************************************************************************//
//*************************************** 관리실 선택 삭제 이벤트 **********************************************************//
//*****************************************************************************************************************//
//*****************************************************************************************************************//
/*****************************************************************************************************************
* 공지사항
* 
* 
*****************************************************************************************************************/
$("#all_AdminDeptDelete").click(function(){
  
  if($("#checkId:checked").length > 0){
 	 
 	 var postData = $("#listForm").serializeArray();
 	 
 	 $.ajax({
 			url : actionLink.deleteAll,
 			type : "post",
 			data : postData,
 			beforeSend : function() { //

 			},
 			success : function(data, textStatus, jqXHR) {
 			
 				$("#msg").html("삭제 되었습니다.");
				$("#msg_modal").modal('show');
				
				$("form").each(function(){
					  this.reset();
				});
				
				
				var currentPage = 1;
				var searchtitle = 0;
				var searchContent = "";
				
				getAdminListPageData(currentPage, searchTitle, searchContent);
				deptUserInit(); //tree 보기 
 			},
 			error : function(jqXHR, textStatus, errorThrown) {
 				// if fails
 			}
 		});
 	 
 	 
 	 
  }else{
 	 
  	$("#msg").html("선택된 항목이 없습니다");
		$("#msg_modal").modal("show");	
 	return;
 	 
  }
  
	 
});



function addQlikView(id,nm){
	
	$("#qlikview_modal").modal('show');
	$("#qlikview_bi_unity_cust_id").val(id);
	
	 $.ajax({
			url : "/"+serviceName+"/userInfo/getQlikViewInfo.do",
			type : "POST",
			data : {bi_unity_cust_id:id},
			beforeSend : function() { //

			},
			success : function(data, textStatus, jqXHR) {
					
				
				
				if(data != null){
					$("#bi_qlikview_user_id").val(data.bi_qlikview_user_id);
					$("#bi_qlikview_user_pwd").val(data.bi_qlikview_user_pwd);
				}else{
					
					$("#bi_qlikview_user_id").val("");
					$("#bi_qlikview_user_pwd").val("");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				// if fails
			}
		});
	 
	
	
}


$("#writeQlikviewForm").submit(function(e) {

	var postData = $(this).serializeArray();
	// alert(postData[2].value);
	// alert(JSON.stringify(postData));

	$.ajax({
		url : "/"+serviceName+"/userInfo/getQlikViewSave.do",
		type : "POST",
		dataType:"json",
		data : postData,
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {
			
		   
			$("form").each(function(){
				  this.reset();
			});
			$("#qlikview_modal").modal('hide');

		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	e.preventDefault(); // STOP default action
	e.unbind(); // unbind. to stop multiple form submit.
});

//*********************************************************************************************************************//
//*****************************************   관리실 > 메뉴 관리   > 저장  ******************************************************//
//*********************************************************************************************************************//
$("#writeMenuForm").submit(function(e) {
	
	$(this).validate();
	
	var postData = $(this).serializeArray();
	//alert(JSON.stringify(postData));
	// var formURL = $(this).attr("action");
	$.ajax({
		url : actionLink.save,
		type : "post",
		data : postData,
		beforeSend : function() { //			
			
			//$("#progress_modal").modal('show');		
			//$(".progress").show();
			//$('.progress-bar').attr('aria-valuetransitiongoal', 100).progressbar();

		},
		success : function(data, textStatus, jqXHR) {
			
			//$("#progress_modal").modal('hide');		
			//$(".progress").hide();
			//$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
			
			$("#msg").html("저장 되었습니다.");
			$("#msg_modal").modal('show');		
			
			$("form").each(function(){					
				  this.reset();		
			});
			
			list();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	
	

	e.preventDefault(); // STOP default action
	e.unbind(); // unbind. to stop multiple form submit.
	
	
	
});


//*********************************************************************************************************************//
//*******************************************   관리실 > 메뉴 관리   > 수정  ****************************************************//
//*********************************************************************************************************************//
$("#editMenuForm").submit(function(e) {
	
	$(this).validate();
	
	var postData = $(this).serializeArray();
	
	
	$.ajax({
		url : actionLink.modify,
		type : "post",
		data : postData,
//		dataType:"json",
		beforeSend : function() { //

		},
		success : function(data) {
			
			$("#msg").html("수정 되었습니다.");
			$("#msg_modal").modal('show');		
			
			
			$("form").each(function(){					
				  this.reset();		
			});
			
			list();
			
		},
//		error : function(jqXHR, textStatus, errorThrown) {
//			// if fails
//		}
	});
	
	

	 
	 
	e.preventDefault(); // STOP default action
	e.unbind(); // unbind. to stop multiple form submit.
	
	
	
	 //배치 작업 
});

//*********************************************************************************************************************//
//*****************************************   관리실 > 메뉴 관리   > 선택삭제   ****************************************************//
//*********************************************************************************************************************//

$("#deleteMenu_button").click(function() {
	
	if($("#menuId:checked").length > 1){
	
	   var result = confirm("선택된 메뉴를 삭제 하시겠습니까?");
	

		if (result) {			
	
			$("#lev1_menuOpenId").val(lev1_menuOpenId);		
			$("#lev2_menuOpenId").val(lev2_menuOpenId);	
			$("#lev3_menuOpenId").val(lev3_menuOpenId);	
			$("#currentId").val(currentId);	
			
		
			
			
			$("#listForm").attr('action', actionLink.deleteAll).submit();
			
		
		} else {

			return;
		}
		
	}else if($("#menuId:checked").length == 1){
		
		  var result = confirm("선택된 메뉴를 삭제 하시겠습니까?");
			if (result) {			
		
				$("#lev1_menuOpenId").val(lev1_menuOpenId);		
				$("#lev2_menuOpenId").val(lev2_menuOpenId);	
				$("#lev3_menuOpenId").val(lev3_menuOpenId);	
				$("#currentId").val(currentId);	
				
				
				$("#listForm").attr('action', "/"+serviceName+"/admin/menu/deleteOne.do").submit();
				
			
			} else {

				return;
			}
		
	}else{
		
		$("#msg").html("선택된 메뉴항목이 없습니다.");
		$("#msg_modal").modal('show');
		
	}
	
	
});



$("#deleteUrl_button").click(function() {
	
	if($("#indexNum:checked").length > 0){
	
	var result = confirm("선택된 보고서를 재 수집 하시겠습니까?");
	
		if (result) {
			
			
			var postData = $("#responseForm").serializeArray();	
			
			$.ajax({
				url : "/"+serviceName+"/menu/getRequestFileRemake.do",
				type : "post",
				data : postData,
				beforeSend : function() { //

				},
				success : function(data) {
					
					$("#msg").html("수집목록에 추가 되었습니다.");
					$("#msg_modal").modal('show');	
					
				},
//				error : function(jqXHR, textStatus, errorThrown) {
//					// if fails
//				}
			});
			
			

		} else {

			return;
		}
		
	}else{
		
		$("#msg").html("선택된 메뉴항목이 없습니다.");
		$("#msg_modal").modal('show');
	}
	
	
});

/**********************************************************************
***********************MDM(활동역영 이동 )************************
***********************************************************************/
function goMdm(){
	
	 var myForm = document.mdmWin;
	 var url = "https://dim.ts2020.kr/servlet/ArtServlet";
	 window.open("" ,"mdmWin", "toolbar=no, width=800, height=600, directories=no, status=no,   scrollorbars=yes, resizable=yes"); 
	 myForm.action = url; 
	 myForm.method="post";
	 myForm.target="mdmWin";
	 myForm.submit();
}


/**********************************************************************
*********************** 세션 아웃일 창 ************************
***********************************************************************/

$("#sessionOut").click(function(){
	$(location).attr('href', "/"+serviceName+"/security/loginPage.do");
});

$("#reflash").click(function(e) {	
	
	$("form").each(function(){
		this.reset();
	});
	
	e.preventDefault(); // STOP default action
});