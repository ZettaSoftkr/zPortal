package com.zetta.menuGroup.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zetta.common.DateTimeUtil;

@Entity
@Table(name="PTL_TB_GROUP_MAPNG_R")
public class MenuUserGroupMapp
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bi_menu_user_group_sn;
	@Column private String bi_menu_group_id;
	@Column	private String bi_group_id; 
	@Column	private String bi_sort_sn;
	@Column private String bi_init_yn; // 초기 설정 값
	@Column	private Timestamp bi_reg_dt;
	@Column	private Timestamp bi_updt_dt;
	
	
	public int getBi_menu_user_group_sn() {
		return bi_menu_user_group_sn;
	}

	public void setBi_menu_user_group_sn(int bi_menu_user_group_sn) {
		this.bi_menu_user_group_sn = bi_menu_user_group_sn;
	}
	
	public String getBi_menu_group_id() {
		return bi_menu_group_id;
	}

	public void setBi_menu_group_id(String bi_menu_group_id) {
		this.bi_menu_group_id = bi_menu_group_id;
	}

	public String getBi_group_id() {
		return bi_group_id;
	}
	
	public void setBi_group_id(String bi_group_id) {
		this.bi_group_id = bi_group_id;
	}
	
	
	public String getBi_sort_sn() {
		return bi_sort_sn;
	}

	public void setBi_sort_sn(String bi_sort_sn) {
		this.bi_sort_sn = bi_sort_sn;
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

	

	public String getBi_init_yn() {
		return bi_init_yn;
	}

	public void setBi_init_yn(String bi_init_yn) {
		this.bi_init_yn = bi_init_yn;
	}
	
	
}
