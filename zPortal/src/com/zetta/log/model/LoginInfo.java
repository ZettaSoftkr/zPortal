package com.zetta.log.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zetta.common.DateTimeUtil;

@Entity
@Table(name = "PTL_TB_LOGIN_INFO_D")
public class LoginInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bi_sn;
	@Column
	private String bi_unity_cust_id;
	@Column
	private String bi_userNm;	
	@Column
	private String bi_session_id;
	@Column
	private String bi_user_ip;
	@Column
	private String bi_refrn_url_addr_id;
	@Column
	private Timestamp bi_login_dt;
	@Column
	private Timestamp bi_logout_dt;
	@Column
	private Timestamp bi_reg_dt;
	@Column
	private Timestamp bi_updt_dt;

	public int getBi_sn() {
		return bi_sn;
	}

	public void setBi_sn(int bi_sn) {
		this.bi_sn = bi_sn;
	}

	public Timestamp getBi_reg_dt() {
		return bi_reg_dt;
	}

	public void setBi_reg_dt(Timestamp bi_reg_dt) {
		this.bi_reg_dt = bi_reg_dt;
	}

	public Timestamp getBi_updt_dt() {
		return bi_updt_dt;
	}

	public void setBi_updt_dt(Timestamp bi_updt_dt) {
		this.bi_updt_dt = bi_updt_dt;
	}

	public String getBi_session_id() {
		return bi_session_id;
	}

	public void setBi_session_id(String bi_session_id) {
		this.bi_session_id = bi_session_id;
	}

	
	
	public String getBi_user_ip() {
		return bi_user_ip;
	}

	public void setBi_user_ip(String bi_user_ip) {
		this.bi_user_ip = bi_user_ip;
	}

	public String getBi_refrn_url_addr_id() {
		return bi_refrn_url_addr_id;
	}

	public void setBi_refrn_url_addr_id(String bi_refrn_url_addr_id) {
		this.bi_refrn_url_addr_id = bi_refrn_url_addr_id;
	}

	public String getBi_unity_cust_id() {
		return bi_unity_cust_id;
	}
	
	public String getBi_userNm() {
		return bi_userNm;
	}

	public void setBi_userNm(String bi_userNm) {
		this.bi_userNm = bi_userNm;
	}

	public void setBi_unity_cust_id(String bi_unity_cust_id) {
		this.bi_unity_cust_id = bi_unity_cust_id;
	}

	public Timestamp getBi_login_dt() {
		return bi_login_dt;
	}

	public void setBi_login_dt(Timestamp bi_login_dt) {
		this.bi_login_dt = bi_login_dt;
	}

	public Timestamp getBi_logout_dt() {
		return bi_logout_dt;
	}

	public void setBi_logout_dt(Timestamp bi_logout_dt) {
		this.bi_logout_dt = bi_logout_dt;
	}

	public String getParseCreateDate() {
		return DateTimeUtil.getParseDateTimestemp(getBi_reg_dt(), "yyyy-MM-dd");
	}

	public String getParseModifyDate() {
		return DateTimeUtil.getParseDateTimestemp(getBi_updt_dt(), "yyyy-MM-dd");
	}

	public String getParseLoginDate() {
		return DateTimeUtil.getParseDateTimestemp(getBi_login_dt(), "yyyy-MM-dd");
	}

	public String getParseLogoutDate() {
		return DateTimeUtil.getParseDateTimestemp(getBi_logout_dt(), "yyyy-MM-dd");
	}



}
