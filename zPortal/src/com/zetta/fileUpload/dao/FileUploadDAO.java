package com.zetta.fileUpload.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zetta.fileUpload.model.FileUpload;


public interface FileUploadDAO {

	
	public FileUpload getById(int getNo) throws DataAccessException;
  	
 	public int insert(FileUpload fileUpload) throws DataAccessException;

    public int update(FileUpload fileUpload) throws DataAccessException;

    public int delete(int getNo) throws DataAccessException;

    public FileUpload findList(int getNo) throws DataAccessException;
    
    public List findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent, String serviceNm)  throws DataAccessException;
    
    public int getTotalNo(int searchTitle, String searchContent, String serviceNm) throws DataAccessException;    
    
    public FileUpload getFileNm(String servNm,String fileNm) throws DataAccessException;
    
    public  List<FileUpload> fileBoardList(int bi_sn, String serviceNm) throws DataAccessException;
    
    public FileUpload fileBoard(int bi_sn, String serviceNm, int bi_file_num) throws DataAccessException;
    
    public FileUpload findName(String serviceNm, String fileNm) throws DataAccessException;
	    
  
	    
}