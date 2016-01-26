<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<TITLE>ZettaSoft redirect</TITLE>
<script type="text/javascript">
	window.onload = function() {		
		var userId = "${userId}";
		var href = "${href}";
		if(userId != "") {
			document.location.href = decodeURIComponent(href) + "&userId=" + userId;
		}
		else{
			document.write("세션이 종료되었습니다. 다시 로그인하세요.");
		}
		
	}
</script>

</head>
<body oncontextmenu="return false;">
	
</body>
</html>