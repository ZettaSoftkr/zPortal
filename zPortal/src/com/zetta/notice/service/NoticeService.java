package com.zetta.notice.service;

import java.util.List;

import com.zetta.notice.model.Notice;

public interface NoticeService {

	public Notice getById(int bi_nct_sn);

	public Notice save(Notice notice);

	public Notice update(Notice notice);

	public int remove(int bi_nct_sn);

	public Notice findList(int bi_bbs_sn);

	public List findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent);

	public int getTotalCount(int searchTitle, String searchContent);

	public Notice getPriView(int bi_nct_sn);

	public Notice getNextView(int bi_nct_sn);

	public Notice getMainPopUpView(String bi_popup_yn);

}
