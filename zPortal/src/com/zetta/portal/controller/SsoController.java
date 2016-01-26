package com.zetta.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zetta.log.model.LogFail;
import com.zetta.log.service.LogService;
import com.zetta.portal.service.SsoService;
import com.zetta.security.model.Security;
import com.zetta.security.service.SecurityService;

@Controller
public class SsoController {

	@Autowired
	SsoService ssoService;
	@Autowired
	LogService logService;
	@Autowired
	SecurityService securityService;

	public Logger logger = Logger.getLogger(getClass());

	@Value("#{qvconf['accUrl']}")
	private String accUrl;

	@RequestMapping(value = "/sso/loginPage.do")
	public ModelAndView gotoPage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String usernm = ssoService.EncodeBySType("");
		logger.info("usernm" + usernm);
		ModelAndView mav = new ModelAndView();
		mav.addObject("bi_user_id", usernm);
		mav.setViewName("sso/loginPage");
		return mav;

	}

	@RequestMapping(value = "/sso/loginPass.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "D1", required = false, defaultValue = "") String D1) {

		ModelAndView mv = new ModelAndView();

		String msg = null;
		LogFail log = new LogFail();

		if (D1 != null && !D1.equals("")) {

			logger.info("D1:: " + D1);
			String decodeString[] = ssoService.DecodeBySType(D1).split(";");
			String emplString[] = decodeString[0].split("=");
			logger.info("D1:: " + D1);

			if (emplString[1] != null) {

				logger.info("emplString[0]:: " + emplString[0]);
				logger.info("emplString[1]:: " + emplString[1]);

				Security security = securityService.getUser(emplString[1]);

				if (security == null) {

					msg = "해당 사용자는 스인된 사용자가 아니거나  정보포탈에 등록된 사용자가 아닙니다";

					log.setBi_unity_cust_id(emplString[1]);
					log.setBi_log_type("S");
					log.setBi_log_desc(msg);
					logService.logFailSave(log);

					mv.addObject("msg", msg);
					mv.setViewName("sso/ssoError");

				} else {

					mv.addObject("id", security.getBi_unity_cust_id());
					mv.addObject("loginKey", security.getBi_portal_user_esntl_id());
					mv.setViewName("sso/ssoPage");
				}
			} else {

				msg = "SSO 정보가 전달되지 않았습니다.";

				log.setBi_unity_cust_id("");
				log.setBi_log_type("S");
				log.setBi_log_desc(msg);
				logService.logFailSave(log);

				mv.addObject("msg", msg);
				mv.setViewName("sso/ssoError");
			}

			// 로그인 실패시 로그 남기기

			logService.logFailSave(log);

		} else {

			msg = "SSO 정보가 전달되지 않았습니다.";
			log.setBi_unity_cust_id("");
			log.setBi_log_type("S");
			log.setBi_log_desc(msg);
			logService.logFailSave(log);

			mv.addObject("msg", msg);
			mv.setViewName("sso/ssoError");

		}

		return mv;

	}

}
