package com.zetta.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ImageUtils {

	public static BufferedImage decodeToImage(String imageString) throws IOException {

        BufferedImage image = null;
        ByteArrayInputStream bis = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
             bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            
        }catch (IOException e) {
        	
        	System.out.print("IOEException");
        	
        } catch (Exception e) {
        	
        	System.out.print("Exception");
        	
        }finally {
        	
            
        	bis.close();
          
            
         }
        return image;
    }

    /**
     * Encode image to string
     * @param image The image to encode
     * @param type jpeg, bmp, ...
     * @return encoded string
     * @throws IOException 
     */
    public static String encodeToString(BufferedImage image, String type) throws IOException {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
        	
        	
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

           
        } catch (IOException e) {
           
        	System.out.print("IOException");
        	
        }finally {
        	
        
           bos.close();
         
           
        }
        
        
        

        
        return imageString;
    }



}
