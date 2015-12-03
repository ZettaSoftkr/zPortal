package com.zetta.qlikview.service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zetta.common.DateTimeUtil;
import com.zetta.qlikview.dao.QlikViewDAO;
import com.zetta.qlikview.model.QlikView;
import com.zetta.qlikview.model.QlikViewLoginInfo;
import com.zetta.qlikview.utils.FilesUtils;
import com.zetta.qlikview.utils.QVXReader;
import com.zetta.qlikview.utils.QlikViewUtils;

/**
 * @시스템 통합정보구축
 * @파일명 QlikViewFileService
 * @설명 QlikView가 저장하고 있는 QVW파일의 정보를 읽거나 저장하는 역할 수행 Implementation
 * @작성자 김창영
 * @작성일 2014/02/11
 * @기타사항 없음.
 */

@Service
@Transactional
public class QlikViewServiceImpl implements QlikViewService {

	public Logger logger = Logger.getLogger(getClass());

	@Autowired
	private QlikViewDAO qlikViewDao;
	
	
	
	
	

	@SuppressWarnings("unchecked")
	public QlikView getById(int bi_sn)  
	{
		return qlikViewDao.getById(bi_sn);
	}

	
	@SuppressWarnings("unchecked")
	public QlikView save(QlikView qlikview) {
		
		qlikview.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		qlikview.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		
		return qlikViewDao.insert(qlikview);
	}
	
	

	@SuppressWarnings("unchecked")
	public QlikView modify(QlikView qlikview) {
		
		return qlikViewDao.update(qlikview);
	}
	@SuppressWarnings("unchecked")
	public int remove(int bi_sn) {

		return qlikViewDao.delete(bi_sn);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public QlikView findList(int bi_sn){
		
	
		return qlikViewDao.findList(bi_sn);
		
	}
	
	public int updateHitCount(int bi_sn) {

		return qlikViewDao.updateHitCount(bi_sn);
	}
	

	@SuppressWarnings("unchecked")
	public List<QlikView> findAllList(int currentPage, int countPerPage, int searchTitle, String searchContent) {		
		
		return qlikViewDao.findAllList( currentPage,  countPerPage,  searchTitle, searchContent) ;
		
	}

	@Override
	public int getTotalCount(int searchTitle, String searchContent) {
		return qlikViewDao.getTotalNo(searchTitle, searchContent);
	}


	 public QlikView qlikViewUser(String bi_unity_cust_id){
		 
		 return qlikViewDao.qlikViewUser(bi_unity_cust_id);
	 }
	 
	 
	
	
	
	

	@Value("#{qvconf['qlikview.SERVERIP']}")
	private String serverIp;
	@Value("#{qvconf['qlikview.QVWPATH']}")
	private String qlikviewPath;
	@Value("#{qvconf['qlikview.RESPONSEFILE']}")
	private String responseFilePath;
	@Value("#{qvconf['qlikview.REQUESTFILE']}")
	private String requestFilePath;
	@Value("#{qvconf['qlikview.SERVERPORT']}")
	private String serverPort;
	@Value("#{qvconf['qlikview.INTEGRATORPORT']}")
	private String integratorPort;
	@Value("#{qvconf['qlikview.OUTPUTPATH']}")
	private String outputPath;

	@Value("#{qvconf['qlikview.QVDOUT']}")
	private String qvdOutputPath;

	@Override
	public List getFileList(String userID) {

		int level1 = 1;
		int level2 = 1;

		//String resultStr = "[";
		//resultStr += "{id:0,label:\"레포트리스트\"},";

		logger.info("경로:" + qlikviewPath);

		Path path = FileSystems.getDefault().getPath(qlikviewPath);
		logger.info("getFileList::path::" + path);
		FilesUtils fu = new FilesUtils();
		QlikViewUtils qvu = new QlikViewUtils();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path)) {
			for (Path p : ds) {
				if (p.getFileName().toString().contains("Shared")) {
					break;
				}

				logger.info("getFileList::fileName::" + p.getFileName());
				HashMap<String, String> hm = new HashMap<String, String>();
				qvu.init(p);
				String[] objects = qvu.getSheetObjects("RPT");
				if (objects != null) {
					for (String objEntry : objects) {

						hm.put("parentId", "100" + level1);
						hm.put("parentNm", p.getFileName().toString());
						hm.put("id", "200" + level2);
						hm.put("nm", objEntry);

						level2++;
					}
				}

				level1++;

				
				list.add(hm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//resultStr += "]";

		return list;
	}

	@Override
	public String getRequestUrl(String url, String objectType) {
		// requestID list를 보자..response.txt

		//String resultStr = "[{url:";
		String requestID = "";
		//String url = "http://" + serverIp + ":" + serverPort + "/QvAJAXZfc/singleobject.htm?document=qvw%2Freport%2F" + object.split(",")[0] + "&object=" + object.split(",")[1];
		FilesUtils fu = new FilesUtils();
//		if (fu.hasUrl(url, responseFilePath)) {
//			
//			requestID = fu.getRequestID(url, responseFilePath);
//			
//		} else {
			
			//url  = url + ";"+ objectType;	
			
			requestID = fu.setRequestID(url, responseFilePath, requestFilePath,objectType);
		//}

		//resultStr += "/ts/QlikView/requestHTML.do?requestID=" + requestID + "\"";

		return requestID;
	}

	// 전달 받은 requestID값에 해당하는 HTML을 String으로 전달함.
	@Override
	public String getRequestHTML(String requestID) {

		logger.info("값은: " + requestID);
		int folderInx = Integer.valueOf(requestID);
		logger.info("값은: " + folderInx / 1000);

		Path path = FileSystems.getDefault().getPath(outputPath + "\\" + folderInx / 1000 + "\\" + requestID + ".html");
		logger.info("path: " + path);
		Path errorPath = FileSystems.getDefault().getPath(outputPath + "\\noinfo.html");
		logger.info("errorPath: " + errorPath);
		FilesUtils fu = new FilesUtils();
		return fu.getHtml(path, errorPath);
	}

	

	public String getValues(String qvxFileName) throws IOException {

		logger.info("qvdOutputPath: " + qvdOutputPath);
		String resultStr = "";
		QVXReader qvxReader = null;
		
		try {
			
			qvxReader = new QVXReader(qvdOutputPath + "/QVX/" + qvxFileName + ".qvx");
			String[] header = qvxReader.getHeaderFieldNames();

			// header
			for (int i = 0; i < header.length; i++) {
				resultStr += header[i] + ",";
			}
			
			if (header.length > 0) {
				resultStr = resultStr.substring(0, resultStr.length() - 1);
			}

			// output records
			while (qvxReader.hasRecord()) {
				String recodeString = qvxReader.getRecodeString();
				if (!recodeString.equals("")) {
					resultStr += "," + recodeString;
				}
			}

		
		} catch (IOException ex) {
			
			System.out.print("IOException");
			return ex.getMessage();
			
		}catch (Exception ex) {
			System.out.print("Exception");
			
			return ex.getMessage();
			
		}finally{
			
			qvxReader.close();
			
		}
		return resultStr;
	}
	
	
	public List<QlikView> qlikViewLoginCheck(String userId){
		
		return qlikViewDao.qlikViewLoginCheck(userId);
		
	}
	
	public int  qlikViewLoginDelete(String bi_qlikview_user_id){
		
		return qlikViewDao.qlikViewLoginDelete(bi_qlikview_user_id);
	}
	
	
 
	
     public QlikViewLoginInfo  qlikViewLoginSave(QlikViewLoginInfo qlikViewLoginInfo){
   
    	 qlikViewLoginInfo.setBi_reg_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
    	 qlikViewLoginInfo.setBi_updt_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
    	 qlikViewLoginInfo.setBi_useStart_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
    	 qlikViewLoginInfo.setBi_useEnd_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));
		return qlikViewDao.qlikViewLoginSave(qlikViewLoginInfo);
    }
     
     public QlikViewLoginInfo  qlikViewLoginEdit(QlikViewLoginInfo qlikViewLoginInfo){
    	 
    	 qlikViewLoginInfo.setBi_useEnd_dt(DateTimeUtil.getTimestampByPattern("yyyy-MM-dd hh:mm:ss"));    	 
    	 return qlikViewDao.qlikViewLoginEdit(qlikViewLoginInfo);
     }
      
      public int  qlikviewMyLoginInfoDelete(String bi_unity_cust_id, String bi_qlikview_user_id){
    	  
    	  return qlikViewDao.qlikviewMyLoginInfoDelete(bi_unity_cust_id, bi_qlikview_user_id);
      }
      
      public List<QlikView> qlikViewMaxUser() {
    	  
    	  return qlikViewDao.qlikViewMaxUser();
      }
      
      public QlikView qlikViewAddUser(QlikView qlikview){
    	  
    	  return qlikViewDao.qlikViewAddUser(qlikview);
      }
      
      public QlikViewLoginInfo qvLoginInfo(String bi_session_id){
    	  
    	  return qlikViewDao.qvLoginInfo(bi_session_id);
      }
      
      public QlikView qvSessionLogin(String bi_unity_cust_id){
    	  
    	  return qlikViewDao.qvSessionLogin(bi_unity_cust_id);
      }
}
