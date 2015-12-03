package com.zetta.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class StringUtil {

	public static String stackTraceToString(Throwable ex) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		PrintStream p = new PrintStream(b);
		ex.printStackTrace(p);
		p.close();
		String stackTrace = b.toString();
		try {
			b.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return convertHtmlBr(stackTrace);
	}

	public static String convertHtmlBr(String comment) {
		if (comment == null) return "";
		return comment.replaceAll(" ","&nbsp;").replaceAll("\n", "<BR>");
	}
	

	public static int byte2Int(byte[] src) {
        int s1 = src[0] & 0xFF;
        int s2 = src[1] & 0xFF;
        int s3 = src[2] & 0xFF;
        int s4 = src[3] & 0xFF;
    
        return ((s1 << 24) + (s2 << 16) + (s3 << 8) + (s4 << 0));
}
	
	
	 static String convert(String str, String encoding) throws IOException {
		  ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
		  requestOutputStream.write(str.getBytes(encoding));
		  return requestOutputStream.toString(encoding);
		 }
		 
		 static String Encoding(String str, String encoding) throws IOException {
		  String result = convert(str, encoding);
		  
		  return result;
		 }
		 


}
