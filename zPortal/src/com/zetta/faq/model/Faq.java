package com.zetta.faq.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zetta.common.DateTimeUtil;
import com.zetta.common.StringUtil;
import com.zetta.userInfo.model.UserInfo;

@Entity
@Table(name="PTL_TB_FAQ_M")
public class Faq
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bi_faq_sn;
	@Column(name="bi_titl", nullable=false, length=200)
	private String bi_titl;
	@Column(name="bi_cn", nullable=false, length=4000)
	private String bi_cn;
	@Column
	private int bi_inqire_num;
	@Column
	private Timestamp bi_reg_dt;
	@Column
	private Timestamp bi_updt_dt;
	@Column
	private String bi_unity_cust_id;

 
	
	public int getBi_faq_sn() {
		return bi_faq_sn;
	}

	public void setBi_faq_sn(int bi_faq_sn) {
		this.bi_faq_sn = bi_faq_sn;
	}


	public String getBi_titl() {
		return bi_titl;
	}

	public void setBi_titl(String bi_titl) {
		this.bi_titl = bi_titl;
	}

	public int getBi_inqire_num() {
		return bi_inqire_num;
	}

	public void setBi_inqire_num(int bi_inqire_num) {
		this.bi_inqire_num = bi_inqire_num;
	}

	public String getBi_unity_cust_id() {
		return bi_unity_cust_id;
	}

	public void setBi_unity_cust_id(String bi_unity_cust_id) {
		this.bi_unity_cust_id = bi_unity_cust_id;
	}

	public String getBi_cn() {
		return bi_cn;
	}

	public void setBi_cn(String bi_cn) {
		this.bi_cn = bi_cn;
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
		if (getBi_cn() != null) {
			return StringUtil.convertHtmlBr(getBi_cn());
		} else {
			return "";
		}
	}

	@ManyToOne
	@JoinColumn(name = "bi_unity_cust_id", insertable = false, updatable = false)
	private UserInfo userInfo;

	public String getBi_user_nm() {

		return userInfo.getBi_user_nm();

	}
	


}
