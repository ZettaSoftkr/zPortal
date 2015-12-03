<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="header_global">
      <div class="global_member">
          <p class="qlikviewtxt">
<!--              <a href="http://10.59.32.175/QvPlugin/QvPluginSetup.exe" target="_new">QlikView Explorer Plug-In(WIN8이하)</a>&nbsp; -->
<!--              <a href="http://10.59.32.175/QvPlugin/QvPluginWin8andUp.exe" target="_new">QlikView Explorer Plug-In(WIN8이상)</a> -->
          </p>  
          <p class="btn"><a href="#"  onClick="logout();"><img src="${pageContext.request.contextPath}/assets/images/btn/btn_logout.gif" title="" alt="로그아웃" /></a></p>
          <p class="txt"><span id="userInfo"></span> 님 로그인 하셨습니다.</p>
          <!-- p class="btn"><a href="#"><img src="${pageContext.request.contextPath}/assets/images/btn/btn_pw_change.gif" title="" alt="비밀번호 변경" /></a></p -->
    </div>
</div>
<div id="navi">
    <h1><a href="#" onClick="goHome();"><img src="${pageContext.request.contextPath}/assets/images/top/logo.gif" title="" alt=""></a></h1>
    <ul id="topHome">
       
    </ul>
</div>
<div id="header_sub">
    <div class="header_search">
      <input type="text" id="searchKeyWord" name="searchKeyWord" class="search_input" placeholder="보고서검색"  value=""></input>
       <a href="#" id="searchReport_btn"><img src="${pageContext.request.contextPath}/assets/images/top/search_btn.png" /></a>
    </div>
    <ul class="sub_navi" id="topMenu">
      
    </ul>
</div>

<script type="text/javascript">
$(document).ready(function() {
	
	 $("#hideTopMenu").click(function(){
		 
		 $("#headerwrap").css("height","28px");
		 $("#navi").hide();	 
		 $("#header_sub").hide();
		 $("#hideTopMenu").hide();	
		 $("#openTopMenu").show();
		 $("#hideleftMenu").css("top","38px");
		 $("#openleftMenu").css("top","38px");		 
		 $("#reportUrl").height(($(window).height() -100));
		 $("#reportAurl").height(($(window).height()-100));
		 
		 
	 });
	 
	 $("#openTopMenu").click(function(){
		 
		 $("#headerwrap").css("height","114px");
		 $("#navi").show();	 
		 $("#header_sub").show();	
		 $("#openTopMenu").hide();	
		 $("#hideTopMenu").show();
		 $("#hideleftMenu").css("top","135px");
		 $("#openleftMenu").css("top","135px");
		 $("#reportUrl").height("780px");
		 $("#reportAurl").height("780px");
		 
		 
		 
		 
	 });
	  
});
</script>