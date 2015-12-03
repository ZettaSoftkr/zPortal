package com.zetta.security.controller;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;

import com.zetta.common.DateTimeUtil;
import com.zetta.log.model.LoginInfo;
import com.zetta.log.service.LogService;



/**
 * 로그인 성공 후 접속 이력을 남기기 위한 핸들러
 * @author Antop
 *
 */
@Controller
public class AuthenticationSuccessContorller extends SavedRequestAwareAuthenticationSuccessHandler {


	@Autowired
	LogService logService;

	

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		super.onAuthenticationSuccess(request, response, authentication);
		
	
		logger.info("로그인: ");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		logger.info("auth.getAuthorities(): "+ auth.getAuthorities());
		logger.info("auth.getPrincipal(): " + auth.getPrincipal());
		logger.info("auth.getName(): " + auth.getName());
		logger.info("auth.getDetails(): "+ auth.getDetails());
		
		
		WebAuthenticationDetails authDetails = (WebAuthenticationDetails) auth.getDetails();
		
		
		logger.info("authDetails.getSessionId(): "+authDetails.getSessionId() ); //sessionID
		
		
		
		InetAddress address = InetAddress.getLocalHost();
		String ipAddress =  address.getHostAddress(); //클라이언트 IP		
		
//		// create login histroy instance
		if(authDetails.getSessionId() != null){
		LoginInfo log = new LoginInfo();
		log.setBi_session_id(authDetails.getSessionId().toString()); //sessionID
		log.setBi_unity_cust_id(auth.getName().toString());
		log.setBi_user_ip(ipAddress);
		log.setBi_login_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		log.setBi_logout_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		
		logger.info("log.getBi_session_id(): "+log.getBi_session_id());
		logger.info("log.getBi_user_id(): "+log.getBi_unity_cust_id());
		logger.info("log.getBi_login_time():"+log.getBi_login_dt());
		logger.info("log.GetBi_logout_time(): "+log.getBi_logout_dt());
		
		

		logService.save(log);
		
		}
//		
//		// set
//		loginToken.setHistory(lh);
	}
	
}
