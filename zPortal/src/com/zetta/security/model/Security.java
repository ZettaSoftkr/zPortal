package com.zetta.security.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity  
@Table(name="PTL_VW_USER_LOGIN_M")  
public class Security {  
      
    @Id 
    private String bi_unity_cust_id;  
    @Column private String bi_portal_user_esntl_id;
    
    public String getBi_unity_cust_id() {
		return bi_unity_cust_id;
	}
	public void setBi_unity_cust_id(String bi_unity_cust_id) {
		this.bi_unity_cust_id = bi_unity_cust_id;
	}
	public String getBi_portal_user_esntl_id() {
		return bi_portal_user_esntl_id;
	}
	public void setBi_portal_user_esntl_id(String bi_portal_user_esntl_id) {
		this.bi_portal_user_esntl_id = bi_portal_user_esntl_id;
	}
  
}  

