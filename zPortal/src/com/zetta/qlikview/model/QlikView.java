package com.zetta.qlikview.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zetta.common.DateTimeUtil;

@Entity
@Table(name = "PTL_TB_QLIKVIEW_USER_M")

public class QlikView {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bi_sn;
	@Column private String bi_session_id;
	@Column private String bi_unity_cust_id;
	@Column	private String bi_qlikview_user_id;
	@Column	private String bi_qlikview_user_pwd;
	
	@Column	private Timestamp bi_reg_dt;
	@Column	private Timestamp bi_updt_dt;



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

	public String getBi_unity_cust_id() {
		return bi_unity_cust_id;
	}

	public void setBi_unity_cust_id(String bi_unity_cust_id) {
		this.bi_unity_cust_id = bi_unity_cust_id;
	}
	

	public String getBi_qlikview_user_id() {
		return bi_qlikview_user_id;
	}

	public void setBi_qlikview_user_id(String bi_qlikview_user_id) {
		this.bi_qlikview_user_id = bi_qlikview_user_id;
	}

	public String getBi_qlikview_user_pwd() {
		return bi_qlikview_user_pwd;
	}

	public void setBi_qlikview_user_pwd(String bi_qlikview_user_pwd) {
		this.bi_qlikview_user_pwd = bi_qlikview_user_pwd;
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
	

	public String getParseCreateDate() {
		return DateTimeUtil.getParseDateTimestemp(getBi_reg_dt(), "yyyy-MM-dd");
	}

	public String getParseModifyDate() {
		return DateTimeUtil.getParseDateTimestemp(getBi_updt_dt(), "yyyy-MM-dd");
	}



}
