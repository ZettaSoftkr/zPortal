package com.zetta.menu.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zetta.common.DateTimeUtil;

@Entity
@Table(name = "PTL_TB_MENU_M")
public class Menu {
	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private String bi_portal_menu_id;
	@Column
	private String bi_portal_menu_parent_id;
	@Column
	private String bi_menu_type_yn;
	@Column
	private String bi_menu_nm;
	@Column
	private String bi_menu_url_addr;
	@Column
	private int bi_menu_sort_sn;
	@Column
	private String bi_menu_fm_yn;
	@Column
	private String bi_inqire_param;
	@Column
	private String bi_qvw_nm;
	@Column
	private String bi_object_nm;
	@Column
	private String bi_inqire_yn;
	@Column
	private String bi_top_view_yn;
	@Column
	private String bi_search_keyword;
	@Column
	private String bi_dc;
	@Column
	private String bi_init_yn;	
	@Column
	private String bi_aqvw_nm;	
	@Column
	private String bi_help_yn;	
	@Column
	private Timestamp bi_reg_dt;
	@Column
	private Timestamp bi_updt_dt;
	

	public String getBi_portal_menu_id() {
		return bi_portal_menu_id;
	}

	public void setBi_portal_menu_id(String bi_portal_menu_id) {
		this.bi_portal_menu_id = bi_portal_menu_id;
	}

	public String getBi_portal_menu_parent_id() {
		return bi_portal_menu_parent_id;
	}

	public void setBi_portal_menu_parent_id(String bi_portal_menu_parent_id) {
		this.bi_portal_menu_parent_id = bi_portal_menu_parent_id;
	}

	public String getBi_menu_type_yn() {
		return bi_menu_type_yn;
	}

	public void setBi_menu_type_yn(String bi_menu_type_yn) {
		this.bi_menu_type_yn = bi_menu_type_yn;
	}

	public String getBi_menu_nm() {
		return bi_menu_nm;
	}

	public void setBi_menu_nm(String bi_menu_nm) {
		this.bi_menu_nm = bi_menu_nm;
	}

	public String getBi_menu_url_addr() {
		return bi_menu_url_addr;
	}

	public void setBi_menu_url_addr(String bi_menu_url_addr) {
		this.bi_menu_url_addr = bi_menu_url_addr;
	}

	public String getBi_menu_fm_yn() {
		return bi_menu_fm_yn;
	}

	public void setBi_menu_fm_yn(String bi_menu_fm_yn) {
		this.bi_menu_fm_yn = bi_menu_fm_yn;
	}

	public int getBi_menu_sort_sn() {
		return bi_menu_sort_sn;
	}

	public void setBi_menu_sort_sn(int bi_menu_sort_sn) {
		this.bi_menu_sort_sn = bi_menu_sort_sn;
	}

	public String getBi_inqire_param() {
		return bi_inqire_param;
	}

	public void setBi_inqire_param(String bi_inqire_param) {
		this.bi_inqire_param = bi_inqire_param;
	}

	public String getBi_qvw_nm() {
		return bi_qvw_nm;
	}

	public void setBi_qvw_nm(String bi_qvw_nm) {
		this.bi_qvw_nm = bi_qvw_nm;
	}

	public String getBi_object_nm() {
		return bi_object_nm;
	}

	public void setBi_object_nm(String bi_object_nm) {
		this.bi_object_nm = bi_object_nm;
	}

	public String getBi_inqire_yn() {
		return bi_inqire_yn;
	}

	public void setBi_inqire_yn(String bi_inqire_yn) {
		this.bi_inqire_yn = bi_inqire_yn;
	}

	public String getBi_top_view_yn() {
		return bi_top_view_yn;
	}

	public void setBi_top_view_yn(String bi_top_view_yn) {
		this.bi_top_view_yn = bi_top_view_yn;
	}

	public String getBi_search_keyword() {
		return bi_search_keyword;
	}

	public void setBi_search_keyword(String bi_search_keyword) {
		this.bi_search_keyword = bi_search_keyword;
	}

	public String getBi_dc() {
		return bi_dc;
	}

	public void setBi_dc(String bi_dc) {
		this.bi_dc = bi_dc;
	}

	
	public String getBi_init_yn() {
		return bi_init_yn;
	}

	public void setBi_init_yn(String bi_init_yn) {
		this.bi_init_yn = bi_init_yn;
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

	
	public String getBi_aqvw_nm() {
		return bi_aqvw_nm;
	}

	public void setBi_aqvw_nm(String bi_aqvw_nm) {
		this.bi_aqvw_nm = bi_aqvw_nm;
	}

	
	public String getBi_help_yn() {
		return bi_help_yn;
	}

	public void setBi_help_yn(String bi_help_yn) {
		this.bi_help_yn = bi_help_yn;
	}

	public String getParseCreateDate() {
		return DateTimeUtil.getParseDateTimestemp(getBi_reg_dt(), "yyyy-MM-dd hh:mm:ss");
	}

	public String getParseModifyDate() {
		return DateTimeUtil.getParseDateTimestemp(getBi_updt_dt(), "yyyy-MM-dd hh:mm:ss");
	}

}
