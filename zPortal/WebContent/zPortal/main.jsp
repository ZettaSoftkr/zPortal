<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>MAIN</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/Default.css" />
<link href="${pageContext.request.contextPath}/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>

<body>
<div id="code_origin" class="code_tmpl">
<!-- UI Object -->
<div id="wrap">
	<!-- header -->
    <div id="headerwrap">
        <div id="header">
            <div id="header_global">
                <div class="global_member">
                    <p class="qlikviewtxt">
<!--                     <a href="http://10.59.32.175/QvPlugin/QvPluginSetup.exe" target="_new">QlikView Explorer Plug-In(WIN8이하)</a>&nbsp; -->
<!--                     <a href="http://10.59.32.175/QvPlugin/QvPluginWin8andUp.exe" target="_new">QlikView Explorer Plug-In(WIN8이상)</a> -->
                    </p>  
                    <p class="btn"><a href="#"  onClick="logout();"><img src="${pageContext.request.contextPath}/assets/images/btn/btn_logout.gif" title="" alt="로그아웃" /></a></p>
                    <p class="txt"><span id="userInfo"></span> 님 로그인 하셨습니다.</p>
                    <!-- p class="btn"><a href="#"><img src="${pageContext.request.contextPath}/assets/images/btn/btn_pw_change.gif" title="" alt="비밀번호 변경" /></a></p -->
                   
                </div>
            </div>
            <div id="navi">
                <h1><a href="#" onclick="goHome();"><img src="${pageContext.request.contextPath}/assets/images/top/logo.gif" title="" alt="교통안전공단" /></a></h1>
                <ul id="topHome"><!--  상단 바로가기 메뉴(관리실, MyPage) --></ul>
                   
                
            </div>
            <div id="header_sub">
                <div class="header_search">
                <input type="text" id="searchKeyWord" name="searchKeyWord" class="search_input" placeholder="보고서검색"  value=""></input>
                <a href="#" id="searchReport_btn"><img src="${pageContext.request.contextPath}/assets/images/top/search_btn.png" /></a>
                </div>
                <ul class="sub_navi" id="topMenu"><!--  상단  1단계 메뉴(정형보고서, 고급분석, Meta시스템, 공통API) --></ul>
            </div>
        </div>
    </div>
    
	<!-- //header -->
	<!-- container -->
	<div id="container">    	
		<!-- content -->
        	<div class="main_visual">
            	<img src="${pageContext.request.contextPath}/assets/images/main/main_visual.png" />
            </div>
             <div class="report_area">
                <div class="report">
                    <p class="report_title"><img src="${pageContext.request.contextPath}/assets/images/main/report_title.png"></p>
                    <ul>
                        <li style="background:none;"><a href="../manualDownload.do?fileNm=DW-DP-02-사용자메뉴얼(통합정보분석시스템)_v1.0.pptx"><img src="${pageContext.request.contextPath}/assets/images/main/main_report_01.png"><p class="report_txt">사용자 메뉴얼</p></a></li>
                        <li><a href="../manualDownload.do?fileNm=교육메뉴얼_기본1.zip"><img src="${pageContext.request.contextPath}/assets/images/main/main_report_02.png"><p class="report_txt">교육자료1</p></a></li>
                        <li><a href="../manualDownload.do?fileNm=교육메뉴얼_기본2.zip"><img src="${pageContext.request.contextPath}/assets/images/main/main_report_03.png"><p class="report_txt">교육자료2</p></a></li>
                        <li><img src="${pageContext.request.contextPath}/assets/images/main/main_report_04.png">                        
                        <p class="report_txt">필수유틸리티(<a href="http://10.59.32.175/QvPlugin/QvPluginSetup.exe" target="_new">WIN8이하</a> , <a href="http://10.59.32.175/QvPlugin/QvPluginWin8andUp.exe" target="_new">WIN8이상</a>)</p></li>                        
                    </ul>
                </div>
            </div>
            <div class="notice_zone">
                <div class="main_notice">
                    <h2>NOTICE</h2>
                    <p class="more"><a href="#"><img src="${pageContext.request.contextPath}/assets/images/main/more.gif" title="" alt="more" /></a></p>
                    <ul id="notice">
                       
                        <li><a href="#"></a></li>                        
                        <li><a href="#"></a></li>
                        <li><a href="#"></a></li>                        
                        <li><a href="#"></a></li>
                        <li><a href="#"></a></li>
                    </ul>
                </div>
                <div class="main_notice">
                    <h2>FAQ</h2>
                    <p class="more"><a href="#"><img src="${pageContext.request.contextPath}/assets/images/main/more.gif" title="" alt="more" /></a></p>
                    <ul id="faq">
                    	<li><a href="#"></a></li>                        
                        <li><a href="#"></a></li>
                        <li><a href="#"></a></li>                        
                        <li><a href="#"></a></li>
                        <li><a href="#"></a></li>                      
                    </ul>
                </div>
                <div class="main_notice">
                    <h2>Q&A</h2>
                    <p class="more"><a href="#"><img src="${pageContext.request.contextPath}/assets/images/main/more.gif" title="" alt="more" /></a></p>
                    <ul id="qna">
                       	<li><a href="#"></a></li>                        
                        <li><a href="#"></a></li>
                        <li><a href="#"></a></li>                        
                        <li><a href="#"></a></li>
                        <li><a href="#"></a></li>
                    </ul>
                </div>
            </div>
		<!-- //content -->
	</div>
	<!-- //container -->
        	<!-- footer -->
	<div id="Main_footerWrap">
		<div id="Main_footer">
            <p><img src="${pageContext.request.contextPath}/assets/images/main/img_address.gif" title="" alt="" /></p>
        </div>
        
	</div>
	<!-- //footer -->
	
</div>
<!-- //UI Object -->
</div>


<!-- Notice Modal -->

<div class="modal fade bs-example-modal-lg" id="notice_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h6 id=""></h6>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h5 class="modal-title" id="myModalLabel">
				<button class="btn btn-warning btn-xs" type="button"><span class="glyphicon glyphicon-hand-right"></span></button>
				<span id="notice_title"></span>
				</h5>
			</div>
			<div class="modal-body">
				<div class="form-group" id="notice_content">
					
				</div>
				<div class="form-group">
					<div class="checkbox">
				    <label >
				     <input type="checkbox" name="close" value="OK" onclick="javascript:closeWin('ts2020_popup', 1);"/> 하루동안 이 창을 열지 않음     
				     <button class="btn btn-warning btn-xs" type="button" data-dismiss="modal">닫기</button>
				    </label>
				  </div>
				  
				</div>
			</div>
			
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- /msg modal -->
<div  class="modal fade bs-example-modal-sm" id="session_modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" >
	<div class="modal-dialog modal-sm" style="margin-top:20%;">
				 <div id="msgBox" class="alert alert-danger fade in">
<!-- 			   <button type="button" class="close" id="closeMsgBox">&times;</button> -->
			      <p id="session_msg"></p>
			      <p>
			        <button type="button" class="btn btn-danger btn-xs" data-dismiss="modal" id="sessionOut">확인</button>	       
			      </p>
				</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /progressbar modal -->
<div  class="modal fade bs-example-modal-sm" id="progress_modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" >
	<div class="modal-dialog modal-sm" style="margin-top:20%;">
				 <div id="msgBox" class="alert alert-danger fade in">
			     
			    		<p> Loading! ...</p>	
			    		<div id="progress" class="progress progress-striped">
						    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuetransitiongoal="100"></div>
						</div> 		      
<!-- 			            <ul> -->
<%-- 							<li style="margin-left:50px"><img src="${pageContext.request.contextPath}/assets/images/img/spinner_squares_circle.gif"></li> --%>
<!-- 						</ul> -->
			      
				</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /progressbar modal -->

<!-- /msg modal -->
<div  class="modal fade bs-example-modal-sm" id="msg_modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" >
	<div class="modal-dialog modal-sm" style="margin-top:20%;">
				 <div id="msgBox" class="alert alert-danger fade in">
<!-- 			   <button type="button" class="close" id="closeMsgBox">&times;</button> -->
			     
			      <p id="msg"></p>
			      <p>
			        <button type="button" class="btn btn-danger btn-xs" data-dismiss="modal">확인</button>	       
			      </p>
				</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<form id="mdmWin" name="mdmWin" action="" method="post">
	<input type="hidden" name="artClass" value="com.ds.mdm.web.common.LoginCmd" />
	<input type="hidden" name="artActType" value="tsIndex" />
	<input type="hidden" name="command" value="login_sso_site" />
	<input type="hidden" name="pmLoginID" id="pmLoginID" value="" />
																	
</form>


<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/jquery.min.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/bootstrap.min.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/assets/js/docs.min.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/button.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/dropdown.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/modal.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/alert.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/jquery.bsAlerts.js"></script>	
<script language="javascript" src="${pageContext.request.contextPath}/assets/js/getInit.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/assets/js/getMenu.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/assets/js/getAction.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/assets/js/getMainSc.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/assets/js/getPageLogWrite.js"></script>
	<script language="javascript">
	
	$(document).ready(function(){
		 

	    $.ajaxSetup({

	           beforeSend: function(xhr) {

	            xhr.setRequestHeader("AJAX", true);

	        },

	        error: function(xhr, status, err) {

	            if (xhr.status == 401) {

	            	$("#session_modal").modal("show");	
	     			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");


	            } else if (xhr.status == 403) {

	            	$("#session_modal").modal("show");	
	     			$("#session_msg").html("페이지 접속시간이 만료되었습니다.");


	            } else {

	                alert("시스템 오류가 발생하였습니다. 관리자에게 문의하세요.");

	            }

	        }

	    });
	    
	    
	   });

	
	
		function click() {

			if ((event.button == 2) || (event.button == 3)) {
				alert("오른쪽 마우스 사용 불가능 합니다. ");

				return false;

			}

		}

		function keypressed() {

			var key = event.keyCode;
			

// 			if (key == 16) {
// 				alert('Shift키는 사용 불가능합니다.');
// 				return false;
// 			}
			
			if (key == 17) {
				alert('Ctrl키는 사용 불가능합니다.');
				return false;
			}

			if (key == 18) {
				alert('Alt키는 사용 불가능합니다.');
				return false;
			}

			if (key == 93) {
				alert('메뉴키는 사용 불가능합니다.');
				return false;
			}

			if (key == 41) {
				alert('메뉴키는 사용 불가능합니다.');
				return false;
			}
			
			if (key == 505) { 
			    alert(document.body.onBeforeUnload);
			}

		}

		document.onmousedown = click;
		document.onkeydown = keypressed;
		
		
	</script>
</body>
</html>
