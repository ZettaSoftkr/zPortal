package com.zetta.visualization.model.menu;

import java.sql.Timestamp;
import java.util.List;

public class MNMenuGroup {
	private String mgId;
	private String mgNm; 
	private String dc;	
	private int sortSn; //순서
	private Timestamp regDt;
	private Timestamp updtDt;
	private List<MNMenu> menuList = null;
	
	public MNMenuGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMgId() {
		return mgId;
	}

	public void setMgId(String mgId) {
		this.mgId = mgId;
	}

	public String getMgNm() {
		return mgNm;
	}

	public void setMgNm(String mgNm) {
		this.mgNm = mgNm;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public int getSortSn() {
		return sortSn;
	}

	public void setSortSn(int sortSn) {
		this.sortSn = sortSn;
	}

	public Timestamp getRegDt() {
		return regDt;
	}

	public void setRegDt(Timestamp regDt) {
		this.regDt = regDt;
	}

	public Timestamp getUpdtDt() {
		return updtDt;
	}

	public void setUpdtDt(Timestamp updtDt) {
		this.updtDt = updtDt;
	}

	public List<MNMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MNMenu> menuList) {
		this.menuList = menuList;
	}
}
