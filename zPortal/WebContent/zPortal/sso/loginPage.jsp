<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <form id="logonForm" role="form" method="post" action="${pageContext.request.contextPath}/sso/loginPass.do">  
		<input type="text" name="D1" placeholder="아이디" value="${bi_user_id}">
		<input type="button" name="sb" value="로그인 테스트" onclick="loginProcess();">
  </form>



<script>
function loginProcess(){
	
	$("#logonForm").submit();

}

	
</script>