package com.zetta.qlikview.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class ImageEncodeToString {
	
	//Logger선언
	public Logger logger = Logger.getLogger(getClass());
		
	 /**
	     * Encode image to Base64 string
	     * @param image The image to Base64 encode
	     * @param type jpeg, bmp, ...
	     * @return Base64 encoded string
	 * @throws IOException 
	     */
	    public static String encodeToString(BufferedImage image, String type) throws IOException {
	        String imageString = null;
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	
	        try {
	            ImageIO.write(image, type, bos);
	            byte[] imageBytes = bos.toByteArray();
	
	            Base64 encoder = new Base64();
	            imageString = encoder.encode(imageBytes);
	
	            bos.close();
	        } catch (IOException e) {
	        	
	           System.out.print("IOException");
	           
	        }catch (Exception e) {
	        	
		           System.out.print("Exception");
		           
		    }finally {
	        	
	            
	            bos.close();
	          
	            
	         }
	        return imageString;
	    }

	    
	    public static BufferedImage decodeToImage(String imageString) throws IOException {

	        BufferedImage image = null;
	        ByteArrayInputStream bis =  null;
	        byte[] imageByte;
	        try {
	        	Base64 decoder = new Base64();
	            imageByte = decoder.decode(imageString);
	            bis = new ByteArrayInputStream(imageByte);
	            image = ImageIO.read(bis);
	           
	        }catch (IOException e) {
	        	
		           System.out.print("IOException");
		           
		    } catch (Exception e) {
	        	
	        	 System.out.print("Exception");
	        	 
	        }finally {
	        	
	            
	        	bis.close();
	          
	            
	         }
	        return image;
	    }

	    
	    
}
