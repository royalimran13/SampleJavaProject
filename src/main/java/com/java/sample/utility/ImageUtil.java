package com.java.sample.utility;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;

public class ImageUtil {
	
	/*
	 * Author : Mohtashim Ahmad
	 * 
	 */
	public static byte [] compressImage(byte []data) {
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(data);
		deflater.finish();
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte [] temp = new byte [4*1024];
		while (!deflater.finished()) {
			int size = deflater.deflate(temp);
			outputStream.write(temp, 0, size);
			
		}
		try {
			outputStream.close();
		} catch (Exception e) {
			System.out.println("Problem in ImageUtil");
			e.printStackTrace();
		}
		
		
		return outputStream.toByteArray();
	}

}
