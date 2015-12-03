package com.zetta.indexSearch.service;


/**
 * @시스템 통합정보구축
 * @파일명 QlikViewFileService
 * @설명 QlikView가 저장하고 있는 QVW파일의 정보를 읽거나 저장하는 역할 수행 interface
 * @작성자 김창영
 * @작성일 2014/02/11
 * @기타사항 없음.
 */

public interface IndexSearchService {

	public boolean  matchString(String keyword, String value);


	
}
