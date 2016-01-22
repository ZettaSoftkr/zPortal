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
@Table(name = "PTL_TB_FAIL_LOG")
public class LogFail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bi_sn;
	@Column(name="bi_unity_cust_id", nullable=true, length=20)
	private String bi_unity_cust_id;
	@Column(name="bi_qlikview_user_id", nullable=true, length=20)
	private String bi_qlikview_user_id;
	@Column(name="bi_log_type", nullable=true, length=1)
	private String bi_log_type;
	@Column(name="bi_log_desc", nullable=true, length=4000)
	private String bi_log_desc;
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

	public String getBi_log_type() {
		return bi_log_type;
	}

	public void setBi_log_type(String bi_log_type) {
		this.bi_log_type = bi_log_type;
	}

	public String getBi_log_desc() {
		return bi_log_desc;
	}

	public void setBi_log_desc(String bi_log_desc) {
		this.bi_log_desc = bi_log_desc;
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
