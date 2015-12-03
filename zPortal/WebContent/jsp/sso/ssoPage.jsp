<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>


<html>
<body>
 <form id="logonForm" name="logonForm" method="post" action="${pageContext.request.contextPath}/j_spring_security_check">  
        <input type="hidden" name="spring-security-redirect" value="/jsp/index.jsp" />
		<input type="hidden" name="j_username" placeholder="아이디" value="${id}">
		<input type="hidden" name="j_password" placeholder="비밀번호" value="${loginKey}">
  </form>
<script>


$(document).ready(function(){
	
	$("#logonForm").submit();
	
	
});
			
		

	
</script>
</body>
</html>