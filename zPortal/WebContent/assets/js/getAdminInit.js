var pageUrl = $(location).attr("pathname").split("/"); // get
// page_url[ex)/ts/menu/get.do]

var serviceName = pageUrl[1]; // ts
var aliasName1 = pageUrl[2]; // alias
var aliasName2 = pageUrl[3]; // alias

var currentId = getUrlVar("currentId"); // 현재 MENU 아이디

var lev1_parent_id = getUrlVar("lev1_parent_id"); // menu 부모 ID1
var lev2_parent_id = getUrlVar("lev2_parent_id"); // menu 부모 ID2
var lev3_parent_id = getUrlVar("lev3_parent_id"); // menu 부모 ID3

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
var lev1_menuOpenId = getUrlVar("lev1_menuOpenId"); //메뉴열기 상위메뉴ID1
var lev2_menuOpenId = getUrlVar("lev2_menuOpenId"); //메뉴열기 상위메뉴ID2
var lev3_menuOpenId = getUrlVar("lev3_menuOpenId"); //메뉴열기 상위메뉴ID3
var keyVal = getUrlVar("keyVal"); //id값

var qlikViewUrl = "http://10.59.32.175:80";

var menuServCode = "2";




function getUrlVar(key) {
	var result = new RegExp(key + "=([^&]*)", "i").exec($(location).attr("search"));
	return result && unescape(result[1]) || "";
}

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ******************  페이지 링크 정의(페이지 이동) ****************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

var pageLink = {

	listPage : "gotoPage.do?pageNm=list",
	writePage : "gotoPage.do?pageNm=write",
	editPage : "gotoPage.do?pageNm=edit",
	viewPage : "gotoPage.do?pageNm=view",
	deletePage : "gotoPage.do?pageNm=delete",

};

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ****************** 페이지 링크 정의 (처리 기능)************************ */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

var actionLink = {
	save : "save.do",
	modify : "modify.do",
	del : "delete.do",
	deleteAll : "deleteAll.do",
	checkNum  : "getCntCheck.do"
};

/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** ******************  메뉴 데이터 로드 페이지 정의 ************************ */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

var getDataLink = { // 데이터 로드 페이지
	top : "topMenu.do",
	left : "leftMenu.do",
	list : "getListData.do",
	view : "getViewData.do"
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
				userStr += "<a href=\"#\" onClick=\"goInit();\" title=\"초기설정\"><span class=\"glyphicon glyphicon-cog\"></span></a>&nbsp;";
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