<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <div class="Main_Title_Area">
                <div class="title" id="reportTitle">도움말</div>                
                    <button type="button" class="button_Area" title="창닫기" id="close_btn">
                        <span class="button_small whitishBtn" id="insertAction"><span class="fontawesome_Btn fa-book"></span>창닫기</span>
                    </button>
                    <div class="sitemap"><!-- 경로 출력<span class="sel_txt">대시보드</span> --></div>
            </div>
            <div class="box_type">
                <div class="box_first">
                    <!--div class="box_second">                                  
                        <div class="lm_select_left">
                            <ul>
                                <li class="box_title">조회년도</li>
                                <li class="box_form"><select id="" name="ay" class="combobox_free_class">
                                        <option>2015년</option>
                                    </select>
                                </li>  
                                <li class="box_title">조회월</li>  
                                <li class="box_form"><select id="" name="ay" class="combobox_free_class">
                                        <option>4월</option>
                                    </select>
                                </li>                                  
                            </ul>                 
                        </div>
                        <button type="submit" class="search_button" title="조회">
                            <span class="whitishBtn_search button_small_search"><span class="fontawesome_Btn fa-search"></span>조회</span>
                        </button>
                    </div-->
                </div>   
            </div> 
<p><embed src="${pageContext.request.contextPath}/file/pdf/${currentId}.pdf" type="application/pdf" width="100%" height="700px"></p>


  <script type="text/javascript">

$(document).ready(function() {
	


$("#close_btn").click(function(){
	
	window.close();
	
});

});
	
</script> 