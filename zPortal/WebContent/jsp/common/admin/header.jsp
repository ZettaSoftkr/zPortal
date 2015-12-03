<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jqGrid start-->
	
<script type="text/javascript">

$(document).ready(function(){
	
	$("#openTopMenu").hide();
	$("#openleftMenu").hide();
	
	
	
	goTopMenu(lev1_parent_id); // 탑메뉴
	getLeftMenu(lev1_parent_id);//왼쪽 메뉴
	getUser(); // 사용자 정보
	goMyMenu(); // home, 활동현황, 관리실, myPage, 지표관리, 엑셀데이터, 사이트맵 


 });



</script>
	<!-- jqGride End -->
