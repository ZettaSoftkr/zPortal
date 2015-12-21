<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <div class="Main_Title_Area">
       <div class="title" id="reportTitle"><!-- 대시보드 <span class="title_name">[담당자 : 한범종 ]</span--></div>                
           <div class="sitemap"><!-- 경로 출력<span class="sel_txt">대시보드</span> --></div>
   </div>
 <div class="report_area">
                <div class="report_info">
                     
                    <li><img src="${pageContext.request.contextPath}/assets/images/main/ico_01.png"></li>
                    <li>일정한 형태를 갖고 있는 보고서를 의미하며 통상적으로 알고 있는 보고서로 간단한 필터의 조작으로 데이터를 정형적으로 분석할 수 있는 보고서를 의미합니다.
                    통합정보 분석 시스템에서는 아래와 같은 정형 보고서를 서비스합니다.<br>
<!--                     현재 <span class="bold">총 51개의 정형보고서</span>를 서비스 하고 있습니다. -->
                    </li>
                </div>
            </div>
            <div class="report_type_area">
                <table class="report_board_Type">
                        <colgroup>
                            <col width="120"/>  
                            <col />  
                        </colgroup>
                            <tr>
                                <th><img src="${pageContext.request.contextPath}/assets/images/main/report_ico_01.png"><br>검사운영</th>
                                <td>자동차검사 의  목표대비 실적 및 결과를 대시보드 형태로 서비스합니다.
목표대비 실적현황(월별), 검사실적현황(월별), 부적합결과 현황(월별) 월마감(말일)
기준의 데이터를 3년이전 부터 전월 까지의 데이터를 서비스합니다.
</td>
                            </tr> 
                            <tr>
                                <td class="blank"></td>
                                <td class="blank"></td>
                            </tr>
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
									월단위로 서비스합니다.
									</td>
                            </tr> 
                            <tr>
                                <td class="blank"></td>
                                <td class="blank"></td>
                            </tr>
                            <tr>
                                <th><img src="${pageContext.request.contextPath}/assets/images/main/report_ico_04.png"><br>운수업체</th>
                                <td>운수업체의 안전진단, 안전관리규정심사등 관리정보와 사고와의 관계등 타 시스템에서 볼수 
									없었던 분석영역의 Cross를 통해 다양한 시각으로 데이터를 분석할 수 있도록 서비스합니다.
									안전진단업체/안전관리규정심사 대상업체 사고 현황 및 사망자 분석, 
									운수업체 안전진단/규정심사 결과 사고 상관 분석 (업종별/지역별/연도별),
									등.
									</td>
                            </tr> 
                            <tr>
                                <td class="blank"></td>
                                <td class="blank"></td>
                            </tr>
                            <tr>
                                <th><img src="${pageContext.request.contextPath}/assets/images/main/report_ico_05.png"><br>운수종사자</th>
                                <td>운수종사자의 적성검사결과 및 성향을 사고와의 관련지어  타 시스템에서 볼수 없었던 분석영역의 Cross를 통해 다양한 시각으로 데이터를 분석할 수 있도록 서비스합니다.고령 운전자 정밀검사/체험교육/운행기록 상관 분석 ,중대사고 발생 종사자 현황 및 특성 분석 등.</td>
								
                            </tr> 
                            <tr>
                                <td class="blank"></td>
                                <td class="blank"></td>
                            </tr>
                            <tr>
                                <th><img src="${pageContext.request.contextPath}/assets/images/main/report_ico_06.png"><br>운전행태</th>
                                <td>체험교육과 사고와의 관계 및 위험운전유형 분석등을 서비스합니다.
								</td>
                            </tr> 
                            <tr>
                                <td class="blank"></td>
                                <td class="blank"></td>
                            </tr>
                            <tr>
                                <th><img src="${pageContext.request.contextPath}/assets/images/main/report_ico_07.png"><br>시스템보고서</th>
                                <td>보고서 활용현황, 마스터데이터 현황 및 통합DB정보 보기 등 현재 통합정보 분석 시스템을 활용하기 위한 기초정보 및 활동 현황등의 시스템 정보를 제공합니다.</td>
                            </tr> 
                    </table>  
</div>
            
 <script type="text/javascript">

$(document).ready(function() { 
	

	
	getMenuNmdept(currentId);
	

});
       
</script>