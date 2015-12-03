package com.zetta.group.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PTL_TB_GROUP_DEPT_MAPNG_R")
public class DeptGroup
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bi_group_dept_id;
	@Column	private String bi_group_id; 
	@Column	private String bi_dept_id;
	@Column	private String bi_init_yn;
	
	public int getBi_group_dept_id() {
		return bi_group_dept_id;
	}

	public void setBi_group_dept_id(int bi_group_dept_id) {
		this.bi_group_dept_id = bi_group_dept_id;
	}

	public String getBi_group_id() {
		return bi_group_id;
	}

	public void setBi_group_id(String bi_group_id) {
		this.bi_group_id = bi_group_id;
	}

	
	public String getBi_dept_id() {
		return bi_dept_id;
	}

	public void setBi_dept_id(String bi_dept_id) {
		this.bi_dept_id = bi_dept_id;
	}

	public String getBi_init_yn() {
		return bi_init_yn;
	}

	public void setBi_init_yn(String bi_init_yn) {
		this.bi_init_yn = bi_init_yn;
	}
	
	


}
