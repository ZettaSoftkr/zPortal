package com.zetta.fileUpload.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zetta.common.StringUtil;
import com.zetta.fileUpload.dao.FileUploadDAO;
import com.zetta.fileUpload.model.FileUpload;

@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {

	public Logger logger = Logger.getLogger(getClass());

	@Autowired
	private FileUploadDAO fileUploadDao;


	public FileUpload getById(int bi_atch_file_sn){
		
		return fileUploadDao.getById(bi_atch_file_sn);
		
	}
	
	public int save(FileUpload fileUpload) {

		return fileUploadDao.insert(fileUpload);
		

	}

	public int remove(int fileUploadNo) {

		return fileUploadDao.delete(fileUploadNo);
		
	}

	



	public FileUpload uploadFileHandler(FileUpload fileUpload, String name, MultipartFile file) throws IOException {
		
		
		BufferedOutputStream stream = null;
		File dir = null;
		if (!file.isEmpty()) {
			
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				 dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);

				logger.info("Server File Location=" + serverFile.getAbsolutePath());

			}catch(IOException e){
	            
		          System.out.print("IOException");
		             
		    } catch (Exception e) {
				
				System.out.print("Exception ");
				
			}finally{
				
				
				stream.close();
				
			}
			
			
		} else {
			
		}
		return null;
	}

	/**
	 * Upload multiple file using Spring Controller
	 * @throws IOException 
	 */

	public FileUpload uploadMultipleFileHandler(FileUpload fileUpload, String realPath, MultipartFile files) throws IOException {
		     InputStream stream = null;
		    UUID uuid = UUID.randomUUID();
	        String tempFileName = uuid.toString();
	        OutputStream bos = null;
	        try {
	        	
	            stream = files.getInputStream();
	            // write the file to the file specified
	            bos = new FileOutputStream(realPath +"/"+tempFileName);
	            int bytesRead = 0;
	            byte[] buffer = new byte[8192];
	            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
	                bos.write(buffer, 0, bytesRead);
	            }
	         

	            if (logger.isDebugEnabled()) {
	                logger.debug("The file has been written to \"" + realPath + tempFileName);
	            }
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	           logger.info("FileNotFoundException");
	           
	        } catch (IOException e) {
	        	
	            // TODO Auto-generated catch block
	            logger.info("IOException");
	            
	        }finally{
	        	
	        	 bos.close();
		         stream.close();
	        	
	        }
	        
	        //newFile.getFileName();
	        logger.info("파일크기"+ StringUtil.byte2Int(files.getBytes()));
	        logger.info("실재크기"+ realPath + tempFileName);
	        logger.info("파일이름"+  files.getOriginalFilename());
	        fileUpload.setBi_atch_file_nm(files.getOriginalFilename()); //파일명	        
	        fileUpload.setBi_atch_file_mg_byte(StringUtil.byte2Int(files.getBytes()));//파일사이즈
	        fileUpload.setBi_tmpr_atch_file_nm(tempFileName);//임시파일
	       
	        return fileUpload;
	        
	}
	
	
	
	public FileUpload uploadMultipleFileIndicatorHandler(FileUpload fileUpload, String realPath, MultipartFile files) throws IOException {
		   InputStream stream;

		    UUID uuid = UUID.randomUUID();
	        String tempFileName = uuid.toString();
	        try {
	            stream = files.getInputStream();
	            // write the file to the file specified
	            OutputStream bos = new FileOutputStream(realPath +"/"+files.getOriginalFilename());
	            int bytesRead = 0;
	            byte[] buffer = new byte[8192];
	            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
	                bos.write(buffer, 0, bytesRead);
	            }
	            bos.close();
	            stream.close();

	            if (logger.isDebugEnabled()) {
	                logger.debug("The file has been written to \"" + realPath + files.getOriginalFilename());
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        fileUpload.setBi_atch_file_nm(files.getOriginalFilename()); //파일명	        
	        fileUpload.setBi_atch_file_mg_byte(StringUtil.byte2Int(files.getBytes()));//파일사이즈
	        fileUpload.setBi_atch_file_nm(tempFileName);//임시파일
	    
	       
	        return fileUpload;
	}
	
	
	public FileUpload uploadMultipleFileRealNameHandler(FileUpload fileUpload, String realPath, MultipartFile files) throws IOException {
		    InputStream stream;

		    UUID uuid = UUID.randomUUID();
	        String tempFileName = uuid.toString();
	     
	        try {
	        	
	            stream = files.getInputStream();
	            // write the file to the file specified
	            OutputStream bos = new FileOutputStream(realPath +"/"+files.getOriginalFilename());
	            int bytesRead = 0;
	            byte[] buffer = new byte[8192];
	            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
	                bos.write(buffer, 0, bytesRead);
	            }
	            bos.close();
	            stream.close();

	            if (logger.isDebugEnabled()) {
	                logger.debug("The file has been written to \"" + realPath + tempFileName);
	            }
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        //newFile.getFileName();
	        logger.info("파일크기"+ StringUtil.byte2Int(files.getBytes()));
	        logger.info("실재크기"+ realPath + tempFileName);
	        logger.info("파일이름"+  files.getOriginalFilename());
	        fileUpload.setBi_atch_file_nm(files.getOriginalFilename()); //파일명	        
	        fileUpload.setBi_atch_file_mg_byte(StringUtil.byte2Int(files.getBytes()));//파일사이즈
	        fileUpload.setBi_tmpr_atch_file_nm(files.getOriginalFilename());//임시파일
	       
	        return fileUpload;
	}
	

	@Override
	public List findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent, String serviceNm) {
		
		return fileUploadDao.findAllList(currentPage, countPerPage, searchTitle, searchContent, serviceNm);
	}
	


	@Override
	public int getTotalCount(int searchTitle, String searchContent, String serviceNm) {
		return fileUploadDao.getTotalNo(searchTitle, searchContent, serviceNm);
	}

	
	public FileUpload getFileNm(String servNm,String fileNm){
		
		return fileUploadDao.getFileNm(servNm,fileNm);
	}
	
	
	public int update(FileUpload fileUpload){
		
		return fileUploadDao.update(fileUpload);
		
	}
	
	
	public  List<FileUpload> fileBoardList(int bi_sn, String serviceNm){
		
		return fileUploadDao.fileBoardList(bi_sn, serviceNm);
	}
	
	 public FileUpload fileBoard(int bi_sn, String serviceNm, int bi_file_num){
		 
		 return fileUploadDao.fileBoard(bi_sn, serviceNm, bi_file_num);
	 }
	 
	 
	 public FileUpload findName(String serviceNm, String fileNm){
		 
		 return fileUploadDao.findName(serviceNm, fileNm);
	 }
}
