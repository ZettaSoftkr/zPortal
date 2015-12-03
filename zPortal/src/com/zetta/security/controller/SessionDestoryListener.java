package com.zetta.security.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;

import com.zetta.common.DateTimeUtil;
import com.zetta.log.service.LogService;
import com.zetta.qlikview.model.QlikView;
import com.zetta.qlikview.service.QlikViewService;

@Controller
public class SessionDestoryListener implements ApplicationListener<SessionDestroyedEvent> {

	public Logger logger = Logger.getLogger(getClass());

	@Autowired
	LogService logService;
	
	@Autowired
	QlikViewService qlikViewService;

	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {

		logger.info(" 로그아웃 ");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {

	
			WebAuthenticationDetails authDetails = (WebAuthenticationDetails) auth.getDetails();

			

			logService.logOutUpdate(authDetails.getSessionId().toString(), DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
			
			
			if(!auth.getName().toString().equals("admin")){
				
				QlikView qv = qlikViewService.qvSessionLogin(auth.getName());
				
				if(qv != null){
					
					logger.info("qv"+ qv.getBi_sn());
				
					qv.setBi_sn(qv.getBi_sn());
					qv.setBi_qlikview_user_id(qv.getBi_qlikview_user_id());
					qv.setBi_qlikview_user_pwd(qv.getBi_qlikview_user_pwd());
					qv.setBi_unity_cust_id("");
					qv.setBi_session_id("");
					qv.setBi_reg_dt(qv.getBi_reg_dt());
					qv.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
					
					qlikViewService.qlikViewAddUser(qv);
				}
			}

		}

		logger.info(" 로그아웃 끝 ");

	}

}