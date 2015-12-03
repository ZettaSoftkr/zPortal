<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <div class="Main_Title_Area">
       <div class="title" id="reportTitle"><!-- 대시보드 <span class="title_name">[담당자 : 한범종 ]</span--></div>                
           <div class="sitemap"><!-- 경로 출력<span class="sel_txt">대시보드</span> --></div>
   </div>
    <div class="report_area">
        <div class="report_info">교통안전공단 마스터 데이터 [고객, 운수종사자, 운수업체 , 인사/조직, 주소] 를 공통API를 통해 시스템 담당자가 해당 DB를 직업 연결 하지 않아도  필요한 자료를  RESET 방식으로  XML 혹은JSON 으로   데이터를 받을수 있습니다.
        </div>
    </div>
    <div class="meta_btn_area">
        <a href="http://10.59.32.176/api" target="_new"><img src="${pageContext.request.contextPath}/assets/images/main/api_btn.png"></a>
    </div> 
 <script type="text/javascript">

$(document).ready(function() { 
	

	
	getMenuNmdept(currentId);
	

});
       
</script>