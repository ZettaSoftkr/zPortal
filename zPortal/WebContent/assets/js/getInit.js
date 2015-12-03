var pageUrl = $(location).attr("pathname").split("/"); // get
// page_url[ex)/ts/menu/get.do]

var serviceName = pageUrl[1]; // ts
var aliasName = pageUrl[2]; // alias
var secAliasName = pageUrl[3]; // alias

var currentId = getUrlVar("currentId"); // 현재 MENU 아이디
var lev1_parent_id = getUrlVar("lev1_parent_id"); // menu 부모 ID
var lev2_parent_id = getUrlVar("lev2_parent_id"); // menu 부모 ID
var lev3_parent_id = getUrlVar("lev3_parent_id"); // menu 부모 ID

var lev1_menuOpen = getUrlVar("lev1_menuOpen"); //메뉴 오픈 여부
if(lev1_menuOpen != "Y"){
	
	lev1_menuOpen = "N";	
}
var lev2_menuOpen = getUrlVar("lev2_menuOpen"); //메뉴 오픈 여부
if(lev2_menuOpen != "Y"){
	
	lev2_menuOpen = "N";	
}
var lev3_menuOpen = getUrlVar("lev3_menuOpen"); //메뉴 오픈 여부
if(lev3_menuOpen != "Y"){
	
	lev3_menuOpen = "N";	
}
var lev1_menuOpenId = getUrlVar("lev1_menuOpenId"); //오픈 상위메뉴ID
var lev2_menuOpenId = getUrlVar("lev2_menuOpenId"); //오픈 상위메뉴ID
var lev3_menuOpenId = getUrlVar("lev3_menuOpenId"); //오픈 상위메뉴ID
var keyVal = getUrlVar("keyVal"); //id값

var qlikViewUrl = "http://10.59.32.175:80";
var menuServCode = "2";

function getUrlVar(key) {
	var result = new RegExp(key + "=([^&]*)", "i").exec($(location).attr("search"));
	return result && unescape(result[1]) || "";
}


/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ****************** Defind TO LINK OF PAGE ****************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

var pageLink = {

	listPage   : "gotoPage.do?pageNm=list",
	writePage  : "gotoPage.do?pageNm=write",
	editPage   : "gotoPage.do?pageNm=edit",
	viewPage   : "gotoPage.do?pageNm=view",
	deletePage : "gotoPage.do?pageNm=delete",

};

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ****************** Defind OF LINK OF ACTION ************************ */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

var actionLink = {
		
	save      : "save.do",
	modify    : "modify.do",
	del       : "delete.do",
	deleteAll : "deleteAll.do",
	checkNum  : "getCntCheck.do"

};

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ****************** Defind OF LINK OF JSON DATA ************************ */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

var getDataLink = { // 데이터 로드 페이지
		
	top  : "topMenu.do",
	left : "leftMenu.do",
	list : "getListData.do",
	view : "getViewData.do",
	

};



/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ************************************* 상단 바로가가기(관리실..) 바로가기  ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */


function goMyMenu() {


		
	var url = "/" + serviceName + "/menu/getRoleMenuListData.do";
	$.ajax({

		url : url,
		type : "post",
		data : {
			bi_portal_menu_parent_id : "#"
		},
		datatype : 'json',
		beforeSend : function() {
			
			//strTopMenu += "<div id=\"box-1\"><img src=\"../assets/images/img/ajax-loader_blue.gif\"></div>";
			//strTopMenu += "<li style=\"margin-left:45%\"><img src=\"../assets/images/img/ajax-loader_blue.gif\"></li>";
			//$("#topMenu").html(strTopMenu);
			
		},
		success : function(data) {
			
			//alert(JSON.stringify(data));
		
		
			var strTopMenu = "";
		
			strTopMenu += "<li><a href=\"#\" onClick=\"goHome();\">HOME</a></li>";
			
		
			$.each(data, function(key, value) {
				
				if(data[key][0] != menuServCode){ //서비스 코드만 없어짐
					
					if(data.length == (key+1)){
						strTopMenu += "<li style=\"border-right:none;\"><a href=\"#\" onClick=\""+data[key][3]+"\">" + data[key][2] + "</a></li>";
						
					}else{
						
						strTopMenu += "<li><a href=\"#\" onClick=\""+data[key][3]+"\">" + data[key][2] + "</a></li>";
					}
				}
			});
		
			$("#topHome").html(strTopMenu);

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
/** ************************************* 사용자 정보  ****************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

function getUser() {
	
	var url = "/" + serviceName + "/userInfo/getUserInfo.do";
	$.ajax({

		url : url,
		type : "post",
		datatype : 'json',
		// / beforeSend: function () {
		// },
		success : function(data) {
			
			
			var userStr = "";
			if(data.rows['bi_unity_cust_id'] == 'admin'){
				//userStr += "<a href=\"#\" onClick=\"goInit();\" title=\"초기설정\"><span class=\"glyphicon glyphicon-cog\"></span></a>&nbsp;";
			}
			
			
			userStr +=data.rows['bi_deptnm'] + " (" + data.rows['bi_user_nm'] + ")";
			$("#userInfo").html(userStr);
			$("#pmLoginID").val(data.id);
		},
		error : function() {
			
		//	$("#progress_modal").modal('hide');
		//	$(".progress").hide();
		//	$('.progress-bar').attr('aria-valuetransitiongoal', 0).progressbar();
		    $("#session_modal").modal("show");	
			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");
			

		}
	});

}
//보고서 검색 
$("#searchReport_btn").click(function(){
	
	if($("#searchKeyWord").val() == "" ){
		
		$("#msg_modal").modal("show");	
		$("#msg").html("검색어를 입력해주세요");
		//e.preventDefault();  STOP default action
		//e.unbind();  unbind. to stop multiple form submit.
		 return;
	}else{
			
	    var gotoPage =  "/"+ serviceName +"/search/gotoPage.do?pageNm=searchList";
	    gotoPage +="&searchKeyWord="+ decodeURIComponent($("#searchKeyWord").val());
	    //gotoPage +="&searchKeyWord="+ $("#searchKeyWord").val();
	  	gotoPage +="&lev1_parent_id="+menuServCode;
	  	gotoPage +="&lev2_parent_id="+lev2_parent_id;
	  	gotoPage +="&lev3_parent_id="+lev3_parent_id;
	  	gotoPage +="&lev1_menuOpenId="+lev1_menuOpenId;
	  	gotoPage +="&lev2_menuOpenId="+lev2_menuOpenId;
	  	gotoPage +="&lev1_menuOpen="+lev1_menuOpen;
	  	gotoPage +="&lev2_menuOpen="+lev2_menuOpen;
	  	gotoPage +="&currentId=6";
	  	
	  	$(location).attr('href', gotoPage);
	}	
	
});

