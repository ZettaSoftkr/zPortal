package com.zetta.bookMark.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.zetta.common.DateTimeUtil;
import com.zetta.common.StringUtil;
import com.zetta.menu.model.Menu;
import com.zetta.userInfo.model.UserInfo;

@Entity
@Table(name="PTL_TB_BKMK_M")
public class BookMark
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bi_bkmk_sn; 
	@Column(name="bi_portal_menu_id", nullable=false, length=30)
	private String bi_portal_menu_id;  
	@Column(name="bi_unity_cust_id", nullable=false, length=30)
	private String bi_unity_cust_id;
	@Column	private Timestamp bi_reg_dt;
	@Column	private Timestamp bi_updt_dt;

	
	
	public int getBi_bkmk_sn() {
		return bi_bkmk_sn;
	}

	public void setBi_bkmk_sn(int bi_bkmk_sn) {
		this.bi_bkmk_sn = bi_bkmk_sn;
	}

	

	public String getBi_portal_menu_id() {
		return bi_portal_menu_id;
	}

	public void setBi_portal_menu_id(String bi_portal_menu_id) {
		this.bi_portal_menu_id = bi_portal_menu_id;
	}

	public String getBi_unity_cust_id() {
		return bi_unity_cust_id;
	}

	public void setBi_unity_cust_id(String bi_unity_cust_id) {
		this.bi_unity_cust_id = bi_unity_cust_id;
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

	

	@ManyToOne
	@JoinColumn(name = "bi_unity_cust_id", insertable = false, updatable = false)
	private UserInfo userInfo;

	public String getBi_user_nm() {

		return userInfo.getBi_user_nm();

	}
	
	
	@OneToOne
	@JoinColumn(name = "bi_portal_menu_id", insertable = false, updatable = false)
	private Menu menu;

	public String getBi_menu_nm() {

		return menu.getBi_menu_nm();

	}


}
