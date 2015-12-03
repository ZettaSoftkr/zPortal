package com.zetta.fileUpload.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zetta.fileUpload.model.FileUpload;

public interface FileUploadService {

	public int save(FileUpload fileUpload);
	
	public int remove(int sn);	

	public FileUpload uploadFileHandler(FileUpload fileUpload, String name, MultipartFile file) throws IOException;

	public FileUpload uploadMultipleFileHandler(FileUpload fileUpload, String realPath, MultipartFile files) throws IOException;
	
	public FileUpload uploadMultipleFileRealNameHandler(FileUpload fileUpload, String realPath, MultipartFile files) throws IOException;
	
	public FileUpload uploadMultipleFileIndicatorHandler(FileUpload fileUpload, String realPath, MultipartFile files) throws IOException;
	
	public List findAllList(int currentPage, int countPerPage,int searchTitle, String searchContent, String serviceNm);

	public int getTotalCount(int searchTitle, String searchContent, String serviceNm);
	
	public FileUpload getFileNm(String servNm,String fileNm);
	
	public int update(FileUpload fileUpload);
	
	public FileUpload getById(int bi_atch_file_sn);
	
	public  List<FileUpload> fileBoardList(int bi_sn, String serviceNm);
	
	public FileUpload fileBoard(int bi_sn, String serviceNm, int bi_file_num);
	
	public FileUpload findName(String serviceNm, String fileNm);
}
