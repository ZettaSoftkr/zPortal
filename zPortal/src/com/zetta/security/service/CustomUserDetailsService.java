package com.zetta.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.security.dao.SecurityDAO;
import com.zetta.security.model.Security;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	public Logger logger = Logger.getLogger(getClass());
	@Autowired
	private SecurityDAO securityDAO;

//	 @Autowired
//	 private GroupDAO groupDao;
	
	
	@Value("#{qvconf['portalAdminId']}") private String adminId;
	@Value("#{qvconf['portalAdminPwd']}") private String adminPwd;
	@Value("#{qvconf['portalAdminRole']}") private String adminRole;
	
	
	@Value("#{sqlquery['group.getMenuGroupDelete']}") private String query3;
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		Security domainUser =  null;
		String userId = null;
		String userPwd = null;
		
		
		
		if(login.equals(adminId)){
			
			userId = adminId;
			userPwd = adminPwd;
			logger.info("관리자 설정");
			
			
		}else{
			logger.info("일반사용자 처리");
			domainUser = securityDAO.getUser(login);
			userId =domainUser.getBi_unity_cust_id();
			userPwd=domainUser.getBi_portal_user_esntl_id();
		}
		 
		

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		//logger.info("아이디:" + domainUser.getBi_unity_cust_id());
		//logger.info("비밀번호:"+ domainUser.getBi_portal_user_esntl_id());
		// getAuthorities(domainUser.getBi_dept_id()
		
		return new User(userId, userPwd, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(userId));

	}



	public Collection getAuthorities(String userId) { // extends
														// GrantedAuthority
       
		List RoleList = getRoles(userId);
		List<GrantedAuthority> authList = getGrantedAuthorities(RoleList);
		// List<GrantedAuthority> authList =
		// getGrantedAuthorities(getRoles(role));

		return authList;
	}

	public List<String> getRoles(String userId) {

		
		List<String> roles = new ArrayList<String>();
		
	    if(userId.equals(adminId)){
			
	    	roles.add(String.valueOf(adminRole));
	    	logger.info("관리자 설정2");
		  
		}else{
			
			logger.info("일반사용자 처리");
			List gm = securityDAO.getGroupRoleList(userId);
			logger.info(gm.size());
			
			for (int i = 0; i < gm.size(); i++) {
				//logger.info("group.getBi_group_author_id()" + group.getBi_group_author_id());
					
				logger.info("group.getBi_group_author_id()" + String.valueOf(gm.get(i)));
				roles.add(String.valueOf(gm.get(i)));
				

			}
			
		}
		

		return roles;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}

		return authorities;
	}

}
