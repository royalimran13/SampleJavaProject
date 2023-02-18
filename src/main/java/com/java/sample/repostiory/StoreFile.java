package com.java.sample.repostiory;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;



public class StoreFile {
	
	public byte[] fileStorage(byte[] data) {
		
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream(data.length);
		byte[] tmp= new byte[4*1024];
		while (!deflater.finished()) {
			int size = deflater.deflate(tmp);
			outputStream.write(tmp,0,size);
			
			
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				 return outputStream.toByteArray();	
			
	
		}
		return tmp;
		}
	
}
