package com.zetta.history.model;

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
@Table(name="PTL_TB_REPORT_H")
public class History
{
	@Id
	private int bi_h_mng_sn;
	@Column(name="bi_portal_menu_id", nullable=false, length=20)
	private String bi_portal_menu_id;
	@Column(name="bi_update_disc", nullable=false, length=4000)
	private String bi_update_disc;
	@Column
	private Timestamp bi_reg_dt;
	
 
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

	public String getBi_update_disc() {
		return bi_update_disc;
	}

	public void setBi_update_disc(String bi_update_disc) {
		this.bi_update_disc = bi_update_disc;
	}





	public Timestamp getBi_reg_dt() {
		return bi_reg_dt;
	}

	public void setBi_reg_dt(Timestamp bi_reg_dt) {
		this.bi_reg_dt = bi_reg_dt;
	}

	public String getParseCreateDate() {
		return DateTimeUtil.getParseDateTimestemp(getBi_reg_dt(), "yyyy-MM-dd");
	}

	
	public String getHtmlFormtWithCn() {
		if (getBi_update_disc() != null) {
			return StringUtil.convertHtmlBr(getBi_update_disc());
		} else {
			return "";
		}
	}
	
}
