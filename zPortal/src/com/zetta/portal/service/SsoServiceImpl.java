package com.zetta.portal.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sutil.Encrypt;


@Service
@Transactional
public class SsoServiceImpl implements SsoService  {
	
	public Logger logger = Logger.getLogger(getClass());

	
	 public String EncodeBySType(String strData){
	    	String strRet = null;
	    	strRet = Encrypt.com_Encode(":" + strData + ":sisenc");
	    	return strRet;	
    }
    
    
    public String DecodeBySType(String strData){
    	String strRet = null;
    	int e, d, s, i=0;

    	strRet = Encrypt.com_Decode(strData);
    	e = strRet.indexOf(":");
    	d = strRet.indexOf(":sisenc");
    	strRet = strRet.substring(e+1, d);
    	return strRet;
    }
	

}
