package com.zetta.dept.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zetta.common.DateTimeUtil;
import com.zetta.common.StringUtil;

@Entity
@Table(name="PTL_TB_DEPT_M")
public class Dept
{
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private String bi_dept_id;
	@Column(name="bi_deptnm", nullable=false, length=30)	
	private String bi_deptnm; 
	@Column(name="bi_sort_sn", nullable=false, length=11)
	private String bi_sort_sn;
	@Column(name="bi_perm_yn", nullable=false, length=1) 
	private String bi_perm_yn;	           
	@Column	private Timestamp bi_reg_dt;
	@Column	private Timestamp bi_updt_dt;

	
	
	
	public String getBi_dept_id() {
		return bi_dept_id;
	}

	public void setBi_dept_id(String bi_dept_id) {
		this.bi_dept_id = bi_dept_id;
	}


	public String getBi_deptnm() {
		return bi_deptnm;
	}

	public void setBi_deptnm(String bi_deptnm) {
		this.bi_deptnm = bi_deptnm;
	}

	public String getBi_sort_sn() {
		return bi_sort_sn;
	}

	public void setBi_sort_sn(String bi_sort_sn) {
		this.bi_sort_sn = bi_sort_sn;
	}


	

	public String getBi_perm_yn() {
		return bi_perm_yn;
	}

	public void setBi_perm_yn(String bi_perm_yn) {
		this.bi_perm_yn = bi_perm_yn;
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
