package com.zetta.menuGroup.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zetta.common.DateTimeUtil;
import com.zetta.common.StringUtil;

@Entity
@Table(name="PTL_TB_MENUGROUP_M")
public class MenuGroup
{
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private String bi_menu_group_id;
	@Column	private String bi_menu_group_nm; 
	@Column	private String bi_sort_sn;
	@Column	private String bi_dc;	
	@Column private String bi_init_yn; // 초기 설정 값
	@Column	private Timestamp bi_reg_dt;
	@Column	private Timestamp bi_updt_dt;
	
	public String getBi_menu_group_id() {
		return bi_menu_group_id;
	}

	public void setBi_menu_group_id(String bi_menu_group_id) {
		this.bi_menu_group_id = bi_menu_group_id;
	}

	public String getBi_menu_group_nm() {
		return bi_menu_group_nm;
	}

	public void setBi_menu_group_nm(String bi_menu_group_nm) {
		this.bi_menu_group_nm = bi_menu_group_nm;
	}

	public String getBi_sort_sn() {
		return bi_sort_sn;
	}

	public void setBi_sort_sn(String bi_sort_sn) {
		this.bi_sort_sn = bi_sort_sn;
	}
	
	public String getBi_init_yn() {
		return bi_init_yn;
	}

	public void setBi_init_yn(String bi_init_yn) {
		this.bi_init_yn = bi_init_yn;
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

	public String getHtmlFormtWithCn() {
		if (getBi_dc() != null) {
			return StringUtil.convertHtmlBr(getBi_dc());
		} else {
			return "";
		}
	}
	

}
