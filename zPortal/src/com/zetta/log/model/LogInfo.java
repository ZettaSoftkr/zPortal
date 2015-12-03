package com.zetta.log.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zetta.common.DateTimeUtil;
import com.zetta.userInfo.model.UserInfo;

@Entity
@Table(name = "PTL_TB_LOG_INFO_D")
public class LogInfo {
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
	private String bi_connct_url_addr;
	@Column
	private String bi_portal_menu_id;
	@Column
	private Timestamp bi_reg_dt;
	@Column
	private Timestamp bi_updt_dt;

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

	public String getBi_unity_cust_id() {
		return bi_unity_cust_id;
	}

	public void setBi_unity_cust_id(String bi_unity_cust_id) {
		this.bi_unity_cust_id = bi_unity_cust_id;
	}
	
	

	public String getBi_userNm() {
		return bi_userNm;
	}

	public void setBi_userNm(String bi_userNm) {
		this.bi_userNm = bi_userNm;
	}

	public String getBi_connct_url_addr() {
		return bi_connct_url_addr;
	}

	public void setBi_connct_url_addr(String bi_connct_url_addr) {
		this.bi_connct_url_addr = bi_connct_url_addr;
	}

	public String getBi_portal_menu_id() {
		return bi_portal_menu_id;
	}

	public void setBi_portal_menu_id(String bi_portal_menu_id) {
		this.bi_portal_menu_id = bi_portal_menu_id;
	}

	public int getBi_sn() {
		return bi_sn;
	}

	public void setBi_sn(int bi_sn) {
		this.bi_sn = bi_sn;
	}

	public String getBi_session_id() {
		return bi_session_id;
	}

	public void setBi_session_id(String bi_session_id) {
		this.bi_session_id = bi_session_id;
	}

	public String getParseCreateDate() {
		return DateTimeUtil.getParseDateTimestemp(getBi_reg_dt(), "yyyy-MM-dd");
	}

	public String getParseModifyDate() {
		return DateTimeUtil.getParseDateTimestemp(getBi_updt_dt(), "yyyy-MM-dd");
	}



}
