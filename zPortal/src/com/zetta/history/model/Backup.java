package com.zetta.history.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PTL_TB_BACKUP_REPORT_H")
public class Backup
{
	@Id
	private int bi_h_mng_sn;
	@Column(name="bi_portal_menu_id", nullable=false, length=20)
	private String bi_portal_menu_id;
	@Column(name="bi_qvw_nm", nullable=false, length=100)
	private String bi_qvw_nm;
	@Column
	private String bi_reg_dt;
	
 
	public int getBi_h_mng_sn() {
		return bi_h_mng_sn;
	}

	public void setBi_h_mng_sn(int bi_h_mng_sn) {
		this.bi_h_mng_sn = bi_h_mng_sn;
	}

	public String getBi_portal_menu_id() {
		return bi_portal_menu_id;
	}

	public void setBi_portal_menu_id(String bi_portal_menu_id) {
		this.bi_portal_menu_id = bi_portal_menu_id;
	}

	public String getBi_qvw_nm() {
		return bi_qvw_nm;
	}

	public void setBi_qvw_nm(String bi_qvw_nm) {
		this.bi_qvw_nm = bi_qvw_nm;
	}

	public String getBi_reg_dt() {
		return bi_reg_dt;
	}

	public void setBi_reg_dt(String bi_reg_dt) {
		this.bi_reg_dt = bi_reg_dt;
	}


//	public String getParseCreateDate() {
//		return DateTimeUtil.getParseDateTimestemp(getBi_reg_dt(), "yyyy-MM-dd");
//	}
//
//	

	
}
