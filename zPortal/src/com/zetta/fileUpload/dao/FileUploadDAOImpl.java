package com.zetta.fileUpload.dao;

// Generated 2010. 5. 24 ���� 7:01:23 by Hibernate Tools 3.2.4.GA

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.fileUpload.model.FileUpload;

@Repository
@Transactional
public class FileUploadDAOImpl implements FileUploadDAO {

	private static Logger logger = Logger.getLogger(FileUploadDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public FileUpload getById(int getNo) throws DataAccessException {
		return (FileUpload) sessionFactory.getCurrentSession().get(FileUpload.class, getNo);
	}

	@SuppressWarnings("unchecked")
	public int insert(FileUpload fileUpload) throws DataAccessException {
		
		sessionFactory.getCurrentSession().save(fileUpload);		
		return 1;
	}

	@SuppressWarnings("unchecked")
	public int update(FileUpload fileUpload) throws DataAccessException {

		
		sessionFactory.getCurrentSession().merge(fileUpload);
		return 1;
	}

	@SuppressWarnings("unchecked")
	public int delete(int getNo) throws DataAccessException {

		sessionFactory.getCurrentSession().delete(getById(getNo));

		return 1;
	}

	@SuppressWarnings("unchecked")
	public FileUpload findList(int no) throws DataAccessException {

		FileUpload fileUpload = (FileUpload) sessionFactory.getCurrentSession().get(FileUpload.class, no);

		if (fileUpload == null) {

			throw new ObjectRetrievalFailureException(FileUpload.class, no);

		}

		return fileUpload;

	}

	@SuppressWarnings("unchecked")
	public List<FileUpload> findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent, String serviceNm) throws DataAccessException {
		logger.debug("findFileUploadList ");
		Query query = null;

		if (searchTitle == 1) {

			query = sessionFactory.getCurrentSession().createQuery(" From FileUpload fileUpload where fileUpload.bi_svc_nm = ? and fileUpload.bi_atch_file_nm = ? ");
			query.setParameter(0, serviceNm);
			query.setParameter(1, searchContent);

		}  else {

			query = sessionFactory.getCurrentSession().createQuery(" From FileUpload fileUpload where fileUpload.bi_svc_nm = ?   order by fileUpload.bi_atch_file_sn desc");
			query.setParameter(0, serviceNm);
		}

		// int start = (currentPage) * countPerPage;
		query.setFirstResult(currentPage);
		query.setMaxResults(countPerPage);

		logger.info("query.list:" + query.list().toString());

		return query.list();

	}

	@SuppressWarnings("unchecked")
	
	public int getTotalNo(int searchTitle, String searchContent, String serviceNm) throws DataAccessException {

		Query query = null;

		if (searchTitle == 1) {

			query = sessionFactory.getCurrentSession().createQuery(" From FileUpload fileUpload where fileUpload.bi_svc_nm = ? and fileUpload.bi_atch_file_nm = ?  ");
			query.setParameter(0, searchContent);			
			query.setParameter(1, serviceNm);

		} else {

			query = sessionFactory.getCurrentSession().createQuery(" From FileUpload fileUpload where fileUpload.bi_svc_nm = ?  order by fileUpload.bi_atch_file_sn desc");
			query.setParameter(0, serviceNm);	

		}

		return query.list().size();

	}
	
	
	 public FileUpload getFileNm(String servNm,String fileNm) {
		 Query query = null;
		 query = sessionFactory.getCurrentSession().createQuery(" From FileUpload fileUpload where fileUpload.bi_svc_nm = ?  and fileUpload.bi_atch_file_nm = ?");
		 query.setParameter(0, servNm);	
		 query.setParameter(1, fileNm);	
		 
		 FileUpload fileUpload = (FileUpload) query.list().get(0);
		 
		return fileUpload;
			
		
		 
		 
	 }
	 
	 
	 public List<FileUpload> fileBoardList(int bi_sn, String serviceNm){
		 Query query = null;
		 query = sessionFactory.getCurrentSession().createQuery(" From FileUpload fileUpload where fileUpload.bi_sn = ?  and fileUpload.bi_svc_nm = ?");
		 query.setParameter(0, bi_sn);	
		 query.setParameter(1, serviceNm);	
		 
		 List<FileUpload> fileList = (List<FileUpload>) query.list();
		 
		return fileList;
			
		
		 
		 
	 }
	 
	 
	 public FileUpload fileBoard(int bi_sn, String serviceNm,int bi_file_num){
		 Query query = null;
		 query = sessionFactory.getCurrentSession().createQuery(" From FileUpload fileUpload where fileUpload.bi_sn = ?  and fileUpload.bi_svc_nm = ? and fileUpload.bi_file_num = ?");
		 query.setParameter(0, bi_sn);	
		 query.setParameter(1, serviceNm);	
		 query.setParameter(2, bi_file_num);	
		 
		 FileUpload file = (FileUpload)query.list().get(0);
		 
		return file;
			
		
		 
		 
	 }
	 
	 public FileUpload findName(String serviceNm, String fileNm){
		 
		 Query query = null;
		 FileUpload fu = null;
		 query = sessionFactory.getCurrentSession().createQuery(" From FileUpload fileUpload where fileUpload.bi_svc_nm = ? and fileUpload.bi_atch_file_nm = ?");
		 query.setParameter(0, serviceNm);	
		 query.setParameter(1, fileNm);	
		 
		 if(query.list().size() > 0){
			 
			 fu = (FileUpload)query.list().get(0);
		 }
		 
		return fu;
			
		
		 
		 
	 }

}
