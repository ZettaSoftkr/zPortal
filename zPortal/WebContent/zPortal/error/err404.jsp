 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/bootstrap/assets/ico/favicon.ico">
<title>교통안전공단-정보포털</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/Default.css" media="screen">


<!-- Custom styles for this template -->
<script src="${pageContext.request.contextPath}/bootstrap/dist/js/jquery.min.js"></script>


<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<div class="error_wrap">
    <h1 class="logo"><a href="#"><img src="${pageContext.request.contextPath}/assets/images/top/logo.gif"></a></h1>
	<div class="error_cont"> <img src="${pageContext.request.contextPath}/assets/images/error/error_03.gif">
         <div class="error_code">이용에 불편을 드려 죄송합니다.</div>   
        <div class="error_txt">404 해당 페이지가 존재 하지 않습니다. 관리자에게 문의하세요</div>   
    </div>
    <div class="error_button_Area">
      
        <a href="#"><span class="error_btn" id="goHome">홈으로</span></a>
    </div> 
</div>

<!-- /msg modal -->
	<!-- Bootstrap core JavaScript
   ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${pageContext.request.contextPath}/bootstrap/dist/js/bootstrap.min.js">
	<script src="${pageContext.request.contextPath}/bootstrap/assets/js/docs.min.js">
	<script type="text/javascript">
	var pageUrl = $(location).attr("pathname").split("/"); // get
	// page_url[ex)/ts/menu/get.do]

	var serviceName = pageUrl[1]; // ts
	var aliasName = pageUrl[2]; // alias
	
	
		function goHome() {		
		
	
			var url = "/" + serviceName;			
			$(location).attr('href', url);
	
		}

	
		function click() {

			if ((event.button == 2) || (event.button == 3)) {
				alert("오른쪽 마우스 사용 불가능 합니다. ");

				return false;

			}

		}

		function keypressed() {

			var key = event.keyCode;

			if (key == 16) {
				alert('Shift키는 사용 불가능합니다.');
				return false;
			}

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

		}

		document.onmousedown = click;

		document.onkeydown = keypressed;
	</script>
</body>
</html>