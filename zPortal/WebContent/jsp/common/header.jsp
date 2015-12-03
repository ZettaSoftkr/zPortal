<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<script type="text/javascript" charset="utf-8">





$(document).ready(function(){
	
	$("#openTopMenu").hide();
	$("#openleftMenu").hide();



    goTopMenu(lev1_parent_id); // 탑메뉴

    getLeftMenu(lev1_parent_id);//왼쪽 메뉴

    goMyMenu();//상단바로가기

    getUser();   //사용자 정보

    getLogList(); //로그

});

</script>
	<!-- jqGride End -->
