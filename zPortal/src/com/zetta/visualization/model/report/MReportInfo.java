package com.zetta.visualization.model.report;

import java.util.List;

import com.zetta.dataSource.model.diagram.zNodeDataArray;

public class MReportInfo {
	private String reportNm;
	private List<ZObjectInfo> objectInfo;
	public String getReportNm() {
		return reportNm;
	}
	public void setReportNm(String reportNm) {
		this.reportNm = reportNm;
	}
	public List<ZObjectInfo> getObjectInfo() {
		return objectInfo;
	}
	public void setObjectInfo(List<ZObjectInfo> objectInfo) {
		this.objectInfo = objectInfo;
	}
	public MReportInfo() {
		super();
		// TODO Auto-generated constructor stub
	}


	
}
