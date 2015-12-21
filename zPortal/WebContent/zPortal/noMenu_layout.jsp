 <%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
 
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>교통안전공단-정보포탈</title>
    <!-- Bootstrap core CSS -->
   
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/Default.css" media="screen" />
<%--   <link href="${pageContext.request.contextPath}/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" .> --%>
    <!-- Custom styles for this template -->
    <script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/jquery.min.js"></script>
	<!-- <link href="jquery-ui/css/custom-theme/jquery-ui-1.8.22.custom.css" rel="stylesheet">  -->		
	<!-- <link href="bootstrap/css/bootstrap.css" rel="stylesheet"> -->	
	<!-- standard jqGrid CSS -->
    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script language="javascript" src="${pageContext.request.contextPath}/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script language="javascript" src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script language="javascript" src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->	
	<!-- jqGrid -->	
  </head>
 <body>
 <div class="contents_main">
	<t:insertAttribute name="content" />
</div>
<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/bootstrap.min.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/bootstrap/dist/js/alert.js"></script>
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


