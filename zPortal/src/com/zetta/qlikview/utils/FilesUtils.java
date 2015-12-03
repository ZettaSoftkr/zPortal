package com.zetta.qlikview.utils;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclEntryType;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

public class FilesUtils {
	
	//Logger선언
	public Logger logger = Logger.getLogger(getClass());
		
	//파일의 권한을 부여한다.
	//WRITE_DATA, READ_DATA, READ_ATTRIBUTES, EXECUTE
	public boolean setPermissions(String userID, Path p){
		 try {
			UserPrincipal user = p.getFileSystem()
					.getUserPrincipalLookupService()
					.lookupPrincipalByName(userID);
			// get view
			AclFileAttributeView view = Files.getFileAttributeView(p,
					AclFileAttributeView.class);
			// create ACE to give "joe" read access
			AclEntry entry = AclEntry
					.newBuilder()
					.setType(AclEntryType.ALLOW)
					.setPrincipal(user)
					.setPermissions(AclEntryPermission.WRITE_DATA,
							AclEntryPermission.READ_DATA,
							AclEntryPermission.READ_ATTRIBUTES,
							AclEntryPermission.EXECUTE)

					.build();
			// read ACL, insert ACE, re-write ACL
			List<AclEntry> acl = view.getAcl();
			acl.add(0, entry); // insert before any DENY entries
			view.setAcl(acl);
		}  catch(IOException e){
            
       	 System.out.print("IOException");
            
         }catch (Exception e) {
			
			System.out.print("Excepption");


		}
		return true;
	}
	
	
	//파일의 형식 및 사용자의 권한에 따른 파일 찾기
	public boolean isFileUserPermission(String userID, String resultStr, Path p, String fileType, String permission)
			throws IOException {
		
		if (p.getFileName().toString().toLowerCase().endsWith("." + fileType)) {
			List<AclEntry> aclList = null;
			try {
				aclList = (List<AclEntry>) Files.getAttribute(p, "acl:acl",java.nio.file.LinkOption.NOFOLLOW_LINKS);

				for (AclEntry aclEntry : aclList) {
					
					if(aclEntry.principal().getName().toLowerCase().endsWith(userID) && aclEntry.permissions().toString().contains(permission)){
						return true;		
					}
				}
			}  catch (IOException e) {
				
				
				System.out.print("IOException");
				return false;
				
			}catch (Exception e) {
				
				
				System.out.print("Excepption");
				return false;
				
			}finally {
	        	
				
	         }
		}
		return false;
	}


	//url을 가지고 있는지 확인함.
	//전달인자: url - http://qlikviewserver/~~, path: response.txt경로
	public boolean hasUrl(String url, String filePath) throws IOException {
		
		
		FileInputStream f_in = null;
		FileChannel in=  null;
		
		
		try {
			 f_in=new FileInputStream(filePath); 
			 in=f_in.getChannel();
			 ByteBuffer  buf2=ByteBuffer.allocate((int)in.size());
			in.read(buf2);
			byte buffer[] = buf2.array();
			String str=new String(buffer);
			
			return str.contains(url);
		}catch (IOException e) {
			
			System.out.print("IOException");
			return false;
			
		} catch (Exception e) {
			
			System.out.print("Excepption");
			return false;
			
		}finally {
        	
			f_in.close();
			in.close();
		
          
            
         }
	}

	public String getRequestID(String url, String filePath) throws IOException {
		
		FileInputStream f_in=null; 
		FileChannel in=null;
		
		try {
			
			 f_in=new FileInputStream(filePath); 
			 in=f_in.getChannel();
			ByteBuffer buf2=ByteBuffer.allocate((int)in.size());
			in.read(buf2);
			byte buffer[] = buf2.array();
			String str=new String(buffer);
			
			str= str.substring(0, str.indexOf("," + url));
			if(str.lastIndexOf("\r\n") < 0){
				str = str.substring(0, str.length());
			}
			else{
				str = str.substring(str.lastIndexOf("\r\n"), str.length());
			}
			
			
			return str.replace("\r\n", "");
			
			
			
		} catch (FileNotFoundException e) {
			
			System.out.print("FileNotFoundException");
			return "0";
		}catch (IOException e) {
			return "0";
		} catch (Exception e) {
			return "0";
		}finally{
			
			f_in.close();
			in.close();
		}
		
		 
	}

	
	@Value("#{qvconf['qlikview.OUTPUTPATH']}")
	private String outputPath;
	public String setRequestID(String url, String responseFilePath, String requestFilePath,String objectType) {
		
		try {
			
	
			//response.txt에 내용을 넣는다.
			String requestID = this.getLastRequestID(responseFilePath);
			
			logger.info("requestID:::" + requestID);
			//마지막 ID에 1을 더함.
			requestID = String.valueOf(Integer.valueOf(requestID) + 1);			
			//String responseStr= this.readFile(responseFilePath);
			//logger.info("responseStr: " + responseStr);

			Charset charset = Charset.forName("UTF-8");			
			ArrayList<String> lines = new ArrayList<String>();	
			Path path = FileSystems.getDefault().getPath(responseFilePath); //repsonseFile
			//responseStr += currentResponse;	
			if(requestID.equals("200001")){
				
				lines.add("\r");
				
			}
			
			lines.add(requestID+";"+url);
			//Files.write(path, responseStr.getBytes());
			Files.write(path, lines,charset,StandardOpenOption.APPEND);	 //response 파일에 추가 
			
			
			String newFile = outputPath + "\\" + Integer.valueOf(requestID) / 1000 + "\\" + requestID + ".html";
			Path htmlFile = FileSystems.getDefault().getPath(newFile);
			if(Files.exists(htmlFile)){ // 파일이 존재 하지 않으면		
				
				 Files.delete(htmlFile); //해당파일을 삭제 한다
				
			}
			
			
			ArrayList<String> reqyestlines = new ArrayList<String>();
			reqyestlines.add(requestID+";"+url+";"+objectType);		
			
			Path requestPath = FileSystems.getDefault().getPath(requestFilePath);
			
			logger.info("파일 유우 :"+ Files.exists(requestPath));
			
			if(!Files.exists(requestPath)){ // 파일이 존재 하지 않으면 
				
				Files.createFile(requestPath);
				Files.write(requestPath, reqyestlines, charset,StandardOpenOption.APPEND);
				
			}else{
				
//			
				Files.write(requestPath, reqyestlines, charset,StandardOpenOption.APPEND);
				
			}
			
		
			
			return requestID;
			
		}catch (FileNotFoundException e) {
			
			System.out.print("FileNotFoundException");
			return "";
		}catch (IOException e) {
			
			System.out.print("IOException");
			return "";
		}catch (Exception e) {
			
			System.out.print("Exception");
			return "";
		}finally{
			
	
		}
		
	}


	//지정된 경로의 파일을 String으로 읽기
	public String readFile(String filePath) throws FileNotFoundException,
			IOException {
		
		Path path = FileSystems.getDefault().getPath(filePath);
		if(!Files.exists(path)){
			return "NoFile";
		}
		else{
			FileInputStream f_in = null;			
			FileChannel in=null;
			
			try{
			 f_in = new FileInputStream(filePath);			
			 in=f_in.getChannel();
			ByteBuffer buf2=ByteBuffer.allocate((int)in.size());
			in.read(buf2);
			byte buffer[] = buf2.array();
			return new String(buffer);
			
			
			}catch (FileNotFoundException e) {
				
				System.out.print("FileNotFoundException");
				return "";
			}catch (IOException e) {
				
				System.out.print("IOException");
				return "";
			}catch (Exception e) {
				
				System.out.print("Exception");
				return "";
			}finally{
				
				in.close();
				f_in.close();
				
				
			}
		}
		
		
	}
	
	
	//마지막 RequestID를 찾는다.
	public String getLastRequestID(String filePath) throws IOException{
		
		FileInputStream f_in = null; 
		FileChannel in = null;
		try {
			
			 f_in = new FileInputStream(filePath); 
			 in=f_in.getChannel();
			ByteBuffer buf2=ByteBuffer.allocate((int)in.size());
			in.read(buf2);
			byte buffer[] = buf2.array();
			String str=new String(buffer);
			
			str = str.substring(0, str.lastIndexOf(";http://"));
			if(str.lastIndexOf("\r\n") < 0){
				
				str = str.substring(0, str.length());
			}else{
				
				str = str.substring(str.lastIndexOf("\r\n"), str.length());
			}
			if(Integer.valueOf(str.replace("\r\n", "")) < 200000){
				
				return "200000";
				
			}else{
				
				return str.replace("\r\n", "");
				
			}
		}  catch (FileNotFoundException e) {
			
			System.out.print("FileNotFoundException");
			return "200000";
		}catch (IOException e) {
			
			logger.debug("getLastRequestID Exception:" + e.getMessage());
			return "200000";
			
		}catch (Exception e) {
			
			logger.debug("getLastRequestID Exception:" + e.getMessage());
			return "200000";
			
		}finally{
			
			f_in.close();
			in.close();
		}
		
	}

	//path의 Html을 스트링으로 전달한다.
	public String getHtml(Path path, Path error) {
		
		String str = null;
		try {
			
			byte[] array = Files.readAllBytes(path);
			str = new String(array, "UTF-8");
			
			
		} catch(IOException e){
            
       	 System.out.print("IOException");
            
        } catch (Exception e) {
			
    		 System.out.print("IOException");
    		 
		}finally{
			
			str = null;
		}
		
		return str;		
	}
	
	//전달된 파일패스에 스트링을 저장함. 만약 내용이 있다면 overwrite한다.
	public boolean overWriteFile(String filePath, String contents) throws IOException{
		
		Writer out = null;
		try {
			//reqeust.txt에 내용을 넣는다.
			Path path = FileSystems.getDefault().getPath(filePath);
			if(!Files.exists(path)){
//				Path createPath = FileSystems.getDefault().getPath(filePath);
//				Path newFile = Files.createFile(createPath);
//				Files.write(newFile, contents.getBytes());
				 out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
				 out.write(contents);
				
			} else {
				
				 out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
				 out.write(contents);
			
//				Path requestPath = FileSystems.getDefault().getPath(filePath);
//				Files.write(requestPath, contents.getBytes());
			}
			
			return true;
			
		}  catch(IOException e){
            
       	 	System.out.print("IOException");
            
        }catch (Exception e) {
        	
        	System.out.print("Exception");
            
		}finally {
			
			if(out != null){
				
				out.close();
				
			}
		}
		
		return false;
	}
}
