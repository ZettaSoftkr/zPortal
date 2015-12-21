 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>교통안전공단-정보포탈</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/Default.css" />
<link href="${pageContext.request.contextPath}/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" />
<link rel="StyleSheet" href="${pageContext.request.contextPath}/dtree/dtree.css" type="text/css" />
<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/assets/js/docs.min.js"></script>
<!-- Custom styles for this template -->
<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/jquery.min.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/bootstrap-progressbar.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/moment-2.4.0.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/dtree/dtree_sitemap.js"></script>
<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script language="javascript" src="${pageContext.request.contextPath}/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script language="javascript" src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script language="javascript" src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<link href="${pageContext.request.contextPath}/bootstrap/dist/css/bootstrap-progressbar-3.1.0.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/bootstrap/dist/css/bootstrap-datetimepicker.css" rel="stylesheet" />
<t:insertAttribute name="header" />
<!-- tiles 설정 -->
</head>
<body>
<div id="code_origin" class="code_tmpl">
<!-- UI Object -->
<div id="wrap">
	<!-- header -->
    <div id="headerwrap">
        <div id="header">
		<!-- background -->
		<t:insertAttribute name="topmenu"/>
     </div>
    </div>
   <div id="container">
   	<div class="contents_main">
   		<t:insertAttribute name="content"/>
   	</div>
   </div> 
	<div id="hideTopMenu" class="up_close"><a href="#" title="메뉴 닫기"><img src="${pageContext.request.contextPath}/assets/images/top/up_btn.gif"/></a></div>
	<div id="openTopMenu" class="up_open"><a href="#" title="메뉴 열기"><img src="${pageContext.request.contextPath}/assets/images/top/down_btn.gif"/></a></div>
	<div id="Main_footerWrap">
	<t:insertAttribute name="footer" />
	</div>
</div>
</div>
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
<!-- /msg modal -->
<!-- /progressbar modal -->
<div  class="modal fade bs-example-modal-sm" id="progress_modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" >
	<div class="modal-dialog modal-sm" style="margin-top:20%;">
				 <div id="msgBox" class="alert alert-danger fade in">
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
<div  class="modal fade bs-example-modal-sm" id="error_modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm" style="margin-top:20%;">
				 <div data-alerts="alerts"  data-titles="{'warning': '<em>Warning!</em>'}" data-ids="myid" data-fade="3000"></div>
				
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
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

<form id="mdmWin" name="mdmWin" action="" method="post">
	<input type="hidden" name="artClass" value="com.ds.mdm.web.common.LoginCmd" />
	<input type="hidden" name="artActType" value="tsIndex" />
	<input type="hidden" name="command" value="login_sso_site" />
	<input type="hidden" name="pmLoginID" id="pmLoginID" value="" />
</form>
	<!-- Bootstrap core JavaScript
   ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/bootstrap.min.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/assets/js/docs.min.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/modal.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/button.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/tab.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/scrollspy.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/carousel.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/collapse.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/alert.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/jquery.bsAlerts.js"></script>	
	<script language="javascript" src="${pageContext.request.contextPath}/assets/js/getInit.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/assets/js/getMenu.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/assets/js/getAction.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/assets/js/getDataView.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/assets/js/getPageLogWrite.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/assets/js/jquery.validate.js"></script>
	<!-- validation checked js -->
	<iframe src="" id="qlikviewUrl" width="0" height="0"></iframe>
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