<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <div class="Main_Title_Area">
       <div class="title" id="reportTitle"><!-- 대시보드 <span class="title_name">[담당자 : 한범종 ]</span--></div>                
           <div class="sitemap"><!-- 경로 출력<span class="sel_txt">대시보드</span> --></div>
   </div>
   <div class="report_area">
                <div class="report_info">
                <li><img src="${pageContext.request.contextPath}/assets/images/main/ico_02.png"></li>
                <li>고급분석은 사용자의 Self-Service 분석을 목표로 하기때문에 다양한 사용자 친화적인 기능들을 가지고 있습니다. 각 고급분석 메뉴마다 도움말을 확인하시고 Self_Service 분석에 도전하세요.</li>

                </div>
            </div>
            <div class="report_type_area">
                <table class="report_board_Type">
                        <colgroup>
                            <col width="120"/>  
                            <col />  
                        </colgroup>
                            <tr>
                                <th><img src="${pageContext.request.contextPath}/assets/images/main/report_ico_02.png"><br>자동차검사</th>
                                <td>이륜차 및 내압용기를 포함하는 모든 자동차 검사에 대한 상세한 데이터를 서비스합니다.
자동차검사대상, 검사실적,검사결과, 이륜차, 내압용기, 자동차수검지수, 주행거리 등이 
포함되어 있으며, 월별마감(말일) 기준으로 3년이전 부터 전월 까지의 데이터를 서비스합니다.
</td>
                            </tr> 
                            <tr>
                                <td class="blank"></td>
                                <td class="blank"></td>
                            </tr>
                            <tr>
                                <th><img src="${pageContext.request.contextPath}/assets/images/main/report_ico_03.png"><br>경영정보</th>
                                <td>공단의 조직정보(정규직)의 변동사항을 월별마감(말일)기준으로  과거 5년전 부터 전월까지 
월단위로 서비스합니다.</td>
                            </tr> 
                            <tr>
                                <td class="blank"></td>
                                <td class="blank"></td>
                            </tr>
                            <tr>
                                <th><img src="${pageContext.request.contextPath}/assets/images/main/report_ico_08.png"><br>통합분석</th>
                                <td>운수업체, 운수종사자, 체험교육등의  업무와 사고와의 관계를 추론하고 개연성을 성립할 수
있도록 분석이 가능하며, 타 시스템에서 볼수 없었던 분석영역의 Cross를 통해 다양한 
시각으로 데이터를 분석할 수 있도록 서비스합니다.</td>
                            </tr> 
                            
                    </table>  
            </div>            
		 <script type="text/javascript">
		$(document).ready(function() { 
			
			getMenuNmdept(currentId);	
		
		});
		       
		</script>