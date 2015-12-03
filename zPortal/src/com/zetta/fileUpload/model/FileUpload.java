package com.zetta.fileUpload.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zetta.common.DateTimeUtil;
import com.zetta.common.StringUtil;

@Entity
@Table(name="API_TB_ATCHMNFL")
public class FileUpload
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bi_atch_file_sn; 
	@Column	private int bi_sn;
	@Column	private int bi_file_num;
	@Column	private String bi_svc_nm;
	@Column	private String bi_atch_file_nm;
	@Column	private String bi_atch_flpth_nm;
	@Column	int  bi_atch_file_mg_byte;
	@Column	private String  bi_tmpr_atch_file_nm;
	@Column	private String  bi_dc;
	@Column	private Timestamp bi_reg_dt;
	@Column	private Timestamp bi_updt_dt;


	public int getBi_atch_file_sn() {
		return bi_atch_file_sn;
	}


	public void setBi_atch_file_sn(int bi_atch_file_sn) {
		this.bi_atch_file_sn = bi_atch_file_sn;
	}
	
	

	public int getBi_sn() {
		return bi_sn;
	}


	public void setBi_sn(int bi_sn) {
		this.bi_sn = bi_sn;
	}


	public int getBi_file_num() {
		return bi_file_num;
	}


	public void setBi_file_num(int bi_file_num) {
		this.bi_file_num = bi_file_num;
	}


	public String getBi_svc_nm() {
		return bi_svc_nm;
	}

	public void setBi_svc_nm(String bi_svc_nm) {
		this.bi_svc_nm = bi_svc_nm;
	}

	public String getBi_atch_file_nm() {
		return bi_atch_file_nm;
	}


	public void setBi_atch_file_nm(String bi_atch_file_nm) {
		this.bi_atch_file_nm = bi_atch_file_nm;
	}

	public String getBi_atch_flpth_nm() {
		return bi_atch_flpth_nm;
	}

	public void setBi_atch_flpth_nm(String bi_atch_flpth_nm) {
		this.bi_atch_flpth_nm = bi_atch_flpth_nm;
	}

	public int getBi_atch_file_mg_byte() {
		return bi_atch_file_mg_byte;
	}

	public void setBi_atch_file_mg_byte(int bi_atch_file_mg_byte) {
		this.bi_atch_file_mg_byte = bi_atch_file_mg_byte;
	}


	public String getBi_tmpr_atch_file_nm() {
		return bi_tmpr_atch_file_nm;
	}



	public void setBi_tmpr_atch_file_nm(String bi_tmpr_atch_file_nm) {
		this.bi_tmpr_atch_file_nm = bi_tmpr_atch_file_nm;
	}



	public String getBi_dc() {
		return bi_dc;
	}


	public void setBi_dc(String bi_dc) {
		this.bi_dc = bi_dc;
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
	
	
