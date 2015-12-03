<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
  <div id="loginbg">
	 <form class="form-horizontal" id="logonForm" role="form" method="post" action="${pageContext.request.contextPath}/j_spring_security_check" onsubmit="return loginProcess();">  
	  <div class="login">
		<ul class="idpass">
			<li><span><img src="${pageContext.request.contextPath}/assets/images/common/id.gif" alt="아이디"></span></li>
			<li><input type="text" id="username" name="j_username" placeholder="아이디" /></li>
			<li><span><img src="${pageContext.request.contextPath}/assets/images/common/password.gif" alt="비밀번호"></span></li>
			<li><input type="password" id="password" name="j_password" placeholder="비밀번호" /></li>
		</ul>
		<p class="btn"><a href="#" ><input type="image" src="${pageContext.request.contextPath}/assets/images/btn/btn_login.gif" alt="login" style="border:0px"></a></p>
	  </div>
	  </form>
</div>
</body>
</html>
<script>

	function loginProcess(){
		
		if($("#username").val() == new String("")){
			
			alert("아이디를 입력해주세요");
			$('#username').focus();
			return false;
			
		}
		
		if($("#password").val() == new String("")){
			
			alert("비민번호룰 입력해주세요");
			$('#password').focus();
			return false;
			
		}
		
		 return true;
		//$("#logonForm").submit();
		
	}
</script>