

function gotoSubPage(url, menuId){
	
	  var gotoPage =  "/"+ serviceName +"/"+url+"&lev1_parent_id="+menuId+"&currentId="+menuId;		 
	  $(location).attr('href', gotoPage);
	
}

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *************************************  관리자페이지 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function goAdminPage(menuId) {
	
	  var gotoPage =  "/"+ serviceName +"/admin/menu/gotoPage.do?pageNm=list&lev1_parent_id="+menuId+"&currentId="+menuId;
	 
		$(location).attr('href', gotoPage);

}


/**********************************************************************
*********************** MY Page 이동************************
***********************************************************************/
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


/**********************************************************************
*********************** 사이트맵 이동 ************************
***********************************************************************/

function goSiteMap(parentId,currentId) {
	
    var gotoPage =  "/"+ serviceName +"/siteMap/gotoPage.do?pageNm=list&lev1_parent_id="+currentId+"&currentId="+currentId;
	$(location).attr('href', gotoPage);

}
/**********************************************************************
*********************** 보고서 검색 이동 ************************
***********************************************************************/

function goSearch(currentId) {
	
    var gotoPage =  "/"+ serviceName +"/search/gotoPage.do?pageNm=searchList&lev1_parent_id="+currentId+"&currentId="+currentId;
	$(location).attr('href', gotoPage);

}


function goIndexSearch(currentId) {
	
    var gotoPage =  "/"+ serviceName +"/indexSearch/gotoPage.do?pageNm=list&lev1_parent_id="+currentId+"&currentId="+currentId;
	$(location).attr('href', gotoPage);

}
/**********************************************************************
*********************** MY Page 이동************************
***********************************************************************/
function goQMC() {
	
	var gotoPage =  "http://10.59.32.175:4780/QMC";
	window.open(gotoPage, "_blank");
}

/**********************************************************************
*********************** 로그아웃 페이지 이동***********************
***********************************************************************/

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

/**********************************************************************
*********************** 큐릭뷰 이동************************
***********************************************************************/
function goQulickView() {


	window.open(qlikViewUrl+"/qlikview/index.htm", "_blank");
}


/**********************************************************************
*********************** 메인화면 이동************************
***********************************************************************/
function goHome() {

	var url = "/" + serviceName+"/jsp/main.jsp";
	$(location).attr('href', url);

}
/**********************************************************************
*********************** 보고서 바로가기 ************************
***********************************************************************/
function goReport(id){
	
	
	
	
    var gotoPage =  "/"+ serviceName +"/report/goReportPage.do?";
  	gotoPage +="bi_portal_menu_id="+id;
  
  	$(location).attr('href', gotoPage);
	
	
}


/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ***************************************등록 페이지 이동************************************************* */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
$("#goMyPageWriteForm_btn").click(function(e) {	
	  
	  
	    var gotoPage =  "/"+ serviceName+"/"+aliasName+"/"+secAliasName+"/"+pageLink.writePage;
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

/**********************************************************************
*********************** 목록 페이지 이동 ************************
***********************************************************************/

//리스트 페이지로 
$("#goMyPageListForm_btn").click(function() {	
	
	 var gotoPage =  "/"+ serviceName +"/"+aliasName+"/"+secAliasName+"/"+pageLink.listPage;
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

/**********************************************************************
*********************** 수정 페이지 이동 ************************
***********************************************************************/

//리스트 페이지로 
$("#goMyPageEditForm_btn").click(function() {	
	
	var gotoPage =  "/"+ serviceName +"/"+aliasName+"/"+secAliasName+"/"+pageLink.editPage;
	  	gotoPage +="&lev1_parent_id="+lev1_parent_id;
	  	gotoPage +="&lev2_parent_id="+lev2_parent_id;
	  	gotoPage +="&lev3_parent_id="+lev3_parent_id;
	  	gotoPage +="&lev1_menuOpenId="+lev1_menuOpenId;
	  	gotoPage +="&lev2_menuOpenId="+lev2_menuOpenId;
	  	gotoPage +="&lev1_menuOpen="+lev1_menuOpen;
	  	gotoPage +="&lev2_menuOpen="+lev2_menuOpen;
	  	gotoPage +="&currentId="+currentId;
	  	gotoPage +="&keyVal="+$("#keyVal").val();	  	
	  	$(location).attr('href', gotoPage);
	
});
/**********************************************************************
*********************** 상세 페이지 이동 ************************
***********************************************************************/

//리스트 페이지로 
$("#goMyPageViewForm_btn").click(function() {	
	
	var gotoPage =  "/"+ serviceName +"/"+aliasName+"/"+secAliasName+"/"+pageLink.viewPage;
	  	gotoPage +="&lev1_parent_id="+lev1_parent_id;
	  	gotoPage +="&lev2_parent_id="+lev2_parent_id;
	  	gotoPage +="&lev3_parent_id="+lev3_parent_id;
	  	gotoPage +="&lev1_menuOpenId="+lev1_menuOpenId;
	  	gotoPage +="&lev2_menuOpenId="+lev2_menuOpenId;
	  	gotoPage +="&lev1_menuOpen="+lev1_menuOpen;
	  	gotoPage +="&lev2_menuOpen="+lev2_menuOpen;
	  	gotoPage +="&currentId="+currentId;
		gotoPage +="&keyVal="+$("#keyVal").val();	  	
	  	$(location).attr('href', gotoPage);
	
});

/**********************************************************************
***********************삭제 페이지 이동 ************************
***********************************************************************/
$("#goMyPageDeleteForm_btn").click(function() {


	var id = $("#keyVal").val();	
	var postData = attrKey+"="+id;
	

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
			
			goMyPageListPage();			
			 
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	
});

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *****************************************     MyPage> 목록페이지 이동하기          *************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function goMyPageListPage(){
	  
	
	var gotoPage =  "/"+ serviceName +"/"+aliasName+"/"+secAliasName+"/"+pageLink.listPage;	
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
/** *****************************************     MyPage >등록페이지 이동하기           ***************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function goMyPageWritePage(id){
	  
	
	var gotoPage =  "/"+ serviceName +"/"+aliasName+"/"+secAliasName+"/"+pageLink.writePage;	
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
/** ***************************************** MyPage> 수정페이지 이동하기    *********************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
function goMyPageEditPage(id){
	
	
	var gotoPage =  "/"+ serviceName+"/"+aliasName+"/"+secAliasName+"/"+pageLink.editPage;	 
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
/** *****************************************    MyPage> 상세보기페이지 이동하기           *********************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function goMyPageViewPage(id){
	
	
	var gotoPage =  "/"+ serviceName+"/"+aliasName+"/"+secAliasName+"/"+pageLink.viewPage;	
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
/** *****************************************     MyPage >전체삭제             *********************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

$("#myPage_allDelete").click(function(){				
			
	     
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
						
						
						var currentPage = 1;
	    				var searchTitle = 0;
	    				var searchContent = "";
	    				getMyPageListPageData(currentPage, searchTitle, searchContent);
	    				

	    			},
	    			error : function(jqXHR, textStatus, errorThrown) {
	    				// if fails
	    			}
	    		});
	    	 
	    	 
	    	 
	     }else{
	    	 
	    	$("#msg").html("선택된 항목이 없습니다.");
			$("#msg_modal").modal('show');
	    	
	    	return;
	    	 
	     }
	     
		 
});


/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ***************************************등록 페이지 이동************************************************* */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
$("#goWriteForm_btn").click(function(e) {	
	  
	  
	    var gotoPage =  "/"+ serviceName +"/"+aliasName+"/"+pageLink.writePage;
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

/**********************************************************************
*********************** 목록 페이지 이동 ************************
***********************************************************************/

//리스트 페이지로 
$("#goListForm_btn").click(function() {	
	
	 var gotoPage =  "/"+ serviceName +"/"+aliasName+"/"+pageLink.listPage;
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

/**********************************************************************
*********************** 수정 페이지 이동 ************************
***********************************************************************/

//리스트 페이지로 
$("#goEditForm_btn").click(function() {	
	
	var gotoPage =  "/"+ serviceName +"/"+aliasName+"/"+pageLink.editPage;
	  	gotoPage +="&lev1_parent_id="+lev1_parent_id;
	  	gotoPage +="&lev2_parent_id="+lev2_parent_id;
	  	gotoPage +="&lev3_parent_id="+lev3_parent_id;
	  	gotoPage +="&lev1_menuOpenId="+lev1_menuOpenId;
	  	gotoPage +="&lev2_menuOpenId="+lev2_menuOpenId;
	  	gotoPage +="&lev1_menuOpen="+lev1_menuOpen;
	  	gotoPage +="&lev2_menuOpen="+lev2_menuOpen;
	  	gotoPage +="&currentId="+currentId;
	  	gotoPage +="&keyVal="+$("#keyVal").val();	  	
	  	$(location).attr('href', gotoPage);
	
});
/**********************************************************************
*********************** 상세 페이지 이동 ************************
***********************************************************************/

//리스트 페이지로 
$("#goViewForm_btn").click(function() {	
	
	var gotoPage =  "/"+ serviceName +"/"+aliasName+"/"+pageLink.viewPage;
	  	gotoPage +="&lev1_parent_id="+lev1_parent_id;
	  	gotoPage +="&lev2_parent_id="+lev2_parent_id;
	  	gotoPage +="&lev3_parent_id="+lev3_parent_id;
	  	gotoPage +="&lev1_menuOpenId="+lev1_menuOpenId;
	  	gotoPage +="&lev2_menuOpenId="+lev2_menuOpenId;
	  	gotoPage +="&lev1_menuOpen="+lev1_menuOpen;
	  	gotoPage +="&lev2_menuOpen="+lev2_menuOpen;
	  	gotoPage +="&currentId="+currentId;
		gotoPage +="&keyVal="+$("#keyVal").val();	  	
	  	$(location).attr('href', gotoPage);
	
});


/**********************************************************************
***********************삭제 페이지 이동 ************************
***********************************************************************/
$("#goDeleteForm_btn").click(function() {


	var id = $("#keyVal").val();	
	var postData = attrKey+"="+id;
	

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
			
			goListPage();		
			
			 
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	
});


/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *****************************************     목록페이지 이동하기           *********************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function goListPage(){
	  
	
	var gotoPage =  "/"+ serviceName +"/"+aliasName+"/"+pageLink.listPage;	
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

function goWritePage(id){
	  
	
	var gotoPage =  "/"+ serviceName +"/"+aliasName+"/"+pageLink.writePage;	
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
function goEditPage(id){
	
	
	var gotoPage =  "/"+ serviceName +"/"+aliasName+"/"+pageLink.editPage;	 
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

function goViewPage(id){
	
	
	var gotoPage =  "/"+ serviceName +"/"+aliasName+"/"+pageLink.viewPage;	
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
/** ************************************* 삭제 페이지 이동 ********************************************************* */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function goDeletePage(id) {


	var id = $("#keyVal").val();  
	var postData = attrKey+"="+id;

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
			
				
			goListPage();
			 
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	
};



/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *************************************** 정보마당 > 자료실 > 등록(파일업로드) ************************************************* */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

$("#BoardWriteForm").submit(function(e) {	
	
	$("#lev1_parent_id").val(lev1_parent_id);
	$("#lev2_parent_id").val(lev2_parent_id);
	$("#lev3_parent_id").val(lev3_parent_id);
	$("#lev1_menuOpenId").val(lev1_menuOpenId);
	$("#lev2_menuOpenId").val(lev2_menuOpenId);
	$("#lev1_menuOpen").val(lev1_menuOpen);
	$("#lev2_menuOpen").val(lev2_menuOpen);
	$("#currentId").val(currentId);
	
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
	//var postData = $(this).serializeArray();
	//postData.push({name:"lev1_parent_id", value:lev1_parent_id});
	$("#lev1_parent_id").val(lev1_parent_id);
	$("#lev1_parent_id").val(lev1_parent_id);
	$("#lev2_parent_id").val(lev2_parent_id);
	$("#lev3_parent_id").val(lev3_parent_id);
	$("#lev1_menuOpenId").val(lev1_menuOpenId);
	$("#lev2_menuOpenId").val(lev2_menuOpenId);
	$("#lev1_menuOpen").val(lev1_menuOpen);
	$("#lev2_menuOpen").val(lev2_menuOpen);
	$("#currentId").val(currentId);
    $(this).attr('action', actionLink.modify); 

});

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 저장처리 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
$("#writeForm").submit(function(e) {
	
	$(this).validate();
	var postData = $(this).serializeArray();	
	postData.push("bi_user_nm","");
	postData.push("lev1_parent_id", lev1_parent_id);
	postData.push("lev1_parent_id", lev1_parent_id);
	postData.push("lev2_parent_id", lev2_parent_id);
	postData.push("lev3_parent_id", lev3_parent_id);
	postData.push("lev1_menuOpenId",lev1_menuOpenId);
	postData.push("lev2_menuOpenId",lev2_menuOpenId);
	postData.push("lev1_menuOpen",lev1_menuOpen);
	postData.push("lev2_menuOpen",lev2_menuOpen);
	postData.push("currentId",currentId);
	
	$.ajax({
		url : actionLink.save,
		type : "POST",
		data : postData,
		
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {
			
			
			$("#msg").html("저장 되었습니다.");
			$("#msg_modal").modal('show');
			$("form").each(function(){
				this.reset();
			});
			
			
		
			goListPage();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	e.preventDefault(); // STOP default action
//	e.unbind(); // unbind. to stop multiple form submit.
});




/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *********************************************** 수정처리 ***************************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

$("#editForm").submit(function(e) {
	$(this).validate();
	var postData = $(this).serializeArray();
	postData.push({name:"lev1_parent_id", value:lev1_parent_id});
	postData.push({name:"lev2_parent_id", value:lev2_parent_id});
	postData.push({name:"lev3_parent_id", value:lev3_parent_id});
	postData.push({name:"lev1_menuOpenId",value:lev1_menuOpenId});
	postData.push({name:"lev2_menuOpenId", value:lev2_menuOpenId});
	postData.push({name:"lev1_menuOpen", value:lev1_menuOpen});
	postData.push({name:"lev2_menuOpen", value:lev2_menuOpen});
	postData.push({name:"currentId", value:currentId});
	
	$.ajax({
		url : actionLink.modify,
		type : "post",
		data : postData,
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {
			
			$("#msg").html("수정 되었습니다.");
			$("#msg_modal").modal('show');
			
			$("form").each(function(){			
				
					  this.reset();
			
			});
			
			
			
			$("#edit_modal").modal('hide');
		
			goEditPage(keyVal);
			
			
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
/** ************************************* 저장처리 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
$("#myPageWriteForm").submit(function(e) {
	
	$(this).validate();
	var postData = $(this).serializeArray();	
	postData.push("lev1_parent_id", lev1_parent_id);
	postData.push("lev1_parent_id", lev1_parent_id);
	postData.push("lev2_parent_id", lev2_parent_id);
	postData.push("lev3_parent_id", lev3_parent_id);
	postData.push("lev1_menuOpenId",lev1_menuOpenId);
	postData.push("lev2_menuOpenId",lev2_menuOpenId);
	postData.push("lev1_menuOpen",lev1_menuOpen);
	postData.push("lev2_menuOpen",lev2_menuOpen);
	postData.push("currentId",currentId);
	
	$.ajax({
		url : actionLink.save,
		type : "POST",
		data : postData,
		
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {
			
			
			$("#msg").html("저장 되었습니다.");
			$("#msg_modal").modal('show');
			$("form").each(function(){
				this.reset();
			});
			
			goMyPageListPage();
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	e.preventDefault(); // STOP default action
//	e.unbind(); // unbind. to stop multiple form submit.
});


/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 저장처리 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
$("#myPageEditForm").submit(function(e) {
	
	$(this).validate();
	var postData = $(this).serializeArray();	
	postData.push("lev1_parent_id", lev1_parent_id);
	postData.push("lev1_parent_id", lev1_parent_id);
	postData.push("lev2_parent_id", lev2_parent_id);
	postData.push("lev3_parent_id", lev3_parent_id);
	postData.push("lev1_menuOpenId",lev1_menuOpenId);
	postData.push("lev2_menuOpenId",lev2_menuOpenId);
	postData.push("lev1_menuOpen",lev1_menuOpen);
	postData.push("lev2_menuOpen",lev2_menuOpen);
	postData.push("currentId",currentId);
	
	$.ajax({
		url : actionLink.modify,
		type : "POST",
		data : postData,
		
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {
			
			
			$("#msg").html("수정 되었습니다.");
			$("#msg_modal").modal('show');
			$("form").each(function(){
				this.reset();
			});
			
			goMyPageListPage();
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	e.preventDefault(); // STOP default action
//	e.unbind(); // unbind. to stop multiple form submit.
});


/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 댓글 저장 ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

$("#comment_submit").click(function(e) {
	
	$("#commentForm").validate();

	var postData = $("#commentForm").serializeArray();
	// var formURL = $(this).attr("action");
//alert(JSON.stringify(postData));
	var commentUrl = "/" + serviceName + "/comment/" + actionLink.save;
//	alert(commentUrl);
	$.ajax({
		url : commentUrl,
		type : "post",
		data : postData,
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {
			
			
			$("#msg").html("등록 되었습니다.");
			$("#msg_modal").modal('show');
			
			$("form").each(function(){			
				
					  this.reset();
			
			});
			
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
				type : "post",
				data : {
					bi_bbs_ansr_sn : bi_bbs_ansr_sn
				},
				datatype : 'json',
				beforeSend : function() {

				},

				success : function(data) {
					
					$("#msg").html("삭제 되었습니다.");
					$("#msg_modal").modal('show');
					
					$("form").each(function(){			
						
							  this.reset();
					
					});

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
	var listLink = "/" + serviceName + "/qna/answerSave.do";
	
	//alert(JSON.stringify(postData));
	// var formURL = $(this).attr("action");
	$.ajax({
		url : listLink,
		type : "post",
		data : postData,
		beforeSend : function() { //

		},
		success : function(data, textStatus, jqXHR) {
			
		
			var currentPage = 1;
			var searchtitle = 0;
			var searchContent = "";	
			getListPageData(currentPage,searchtitle,searchContent);
			
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
/** ************************************** QNA 답변 처리********************************************************** */
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

			getCommentList(postData[0].value, aliasName);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			// if fails
		}
	});
	e.preventDefault(); // STOP default action
	e.unbind(); // unbind. to stop multiple form submit.
});


/**********************************************************************
*********************** 정보마당 & 전체 게시판 전체 삭제  ************************
***********************************************************************/

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

	    				$("#msg").html("삭제 되었습니다.");
						$("#msg_modal").modal('show');
						
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
	    	 
	    	$("#msg").html("선택된 항목이 없습니다.");
			$("#msg_modal").modal('show');
	    	
	    	return;
	    	 
	     }
	     
		 
});






/**********************************************************************
***********************세션 로그 아웃 일 경우 페이지 이동************************
***********************************************************************/
$("#sessionOut").click(function(){
	$(location).attr('href', "/"+serviceName+"/security/loginPage.do");
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

$("#reflash").click(function(e) {	
	
	$("form").each(function(){
		this.reset();
	});
	
	e.preventDefault(); // STOP default action
});
