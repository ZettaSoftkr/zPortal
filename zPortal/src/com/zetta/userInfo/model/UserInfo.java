package com.zetta.userInfo.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zetta.common.DateTimeUtil;
import com.zetta.dept.model.Dept;

@Entity
@Table(name = "PTL_TB_USER_D")
public class UserInfo {
	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private String bi_unity_cust_id;
	@Column
	private String bi_user_nm;
	@Column
	private String bi_dept_id;
	@Column
	private String bi_mpno;
	@Column
	private String bi_email_addr;
	@Column
	private String bi_perm_yn;
	@Column
	private String bi_init_yn;
	@Column
	private Timestamp bi_reg_dt;
	@Column
	private Timestamp bi_updt_dt;

	public String getBi_unity_cust_id() {
		return bi_unity_cust_id;
	}

	public void setBi_unity_cust_id(String bi_unity_cust_id) {
		this.bi_unity_cust_id = bi_unity_cust_id;
	}

	public String getBi_mpno() {
		return bi_mpno;
	}

	public void setBi_mpno(String bi_mpno) {
		this.bi_mpno = bi_mpno;
	}

	public String getBi_email_addr() {
		return bi_email_addr;
	}

	public void setBi_email_addr(String bi_email_addr) {
		this.bi_email_addr = bi_email_addr;
	}

	public String getBi_perm_yn() {
		return bi_perm_yn;
	}

	public void setBi_perm_yn(String bi_perm_yn) {
		this.bi_perm_yn = bi_perm_yn;
	}
	
	

	public String getBi_init_yn() {
		return bi_init_yn;
	}

	public void setBi_init_yn(String bi_init_yn) {
		this.bi_init_yn = bi_init_yn;
	}

	public String getBi_user_nm() {
		return bi_user_nm;
	}

	public void setBi_user_nm(String bi_user_nm) {
		this.bi_user_nm = bi_user_nm;
	}

	public String getBi_dept_id() {
		return bi_dept_id;
	}

	public void setBi_dept_id(String bi_dept_id) {
		this.bi_dept_id = bi_dept_id;
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

	@ManyToOne(cascade=CascadeType.ALL)
	// @JoinColumn(name = "bi_dept_id", insertable = false, updatable = false)
	// @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "bi_dept_id", referencedColumnName = "bi_dept_id", nullable = false, insertable = false, updatable = false)
	private Dept dept;

	public String getBi_deptnm() {

		return dept.getBi_deptnm();

	}

}
