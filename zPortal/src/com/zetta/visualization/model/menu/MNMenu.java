package com.zetta.visualization.model.menu;

import java.sql.Timestamp;

public class MNMenu {
	private String menuId;
	private String prtMenuId;
	private String menuTp; //폴더 페이지 구분 --f 폴더 , p 페이지 
	private String menuNm; //메뉴명
	private int sortSn;
	private String dc; //설명
	private String searchKeyword; //검색
	private Timestamp regDt;
	private Timestamp updtDt;
	public MNMenu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getPrtMenuId() {
		return prtMenuId;
	}
	public void setPrtMenuId(String prtMenuId) {
		this.prtMenuId = prtMenuId;
	}
	public String getmenuTp() {
		return menuTp;
	}
	public void setmenuTp(String menuTp) {
		this.menuTp = menuTp;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public int getSortSn() {
		return sortSn;
	}
	public void setSortSn(int sortSn) {
		this.sortSn = sortSn;
	}
	public String getDc() {
		return dc;
	}
	public void setDc(String dc) {
		this.dc = dc;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
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
	
}
