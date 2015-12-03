package com.zetta.group.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity      
@Table(name="PTL_TB_GROUP_USER_MAPNG_R")
public class UserGroup
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bi_group_user_id;
	@Column	private String bi_group_id; 
	@Column	private String bi_unity_cust_id;
	@Column	private String bi_init_yn;
	
	
	public String getBi_group_id() {
		return bi_group_id;
	}

	public void setBi_group_id(String bi_group_id) {
		this.bi_group_id = bi_group_id;
	}

	public int getBi_group_user_id() {
		return bi_group_user_id;
	}

	public void setBi_group_user_id(int bi_group_user_id) {
		this.bi_group_user_id = bi_group_user_id;
	}

	public String getBi_unity_cust_id() {
		return bi_unity_cust_id;
	}

	public void setBi_unity_cust_id(String bi_unity_cust_id) {
		this.bi_unity_cust_id = bi_unity_cust_id;
	}

	public String getBi_init_yn() {
		return bi_init_yn;
	}

	public void setBi_init_yn(String bi_init_yn) {
		this.bi_init_yn = bi_init_yn;
	}

	 	
}
