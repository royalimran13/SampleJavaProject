package com.java.sample.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name="customer")
public class Customer {
	/*
	 * 
	 * Author : Mohtashim Ahmad
	 * 
	 */
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customer_id")
	@Id
	private Long customer_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="dob")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String dob;
	
	private String fileName;
	
	
	private String Address;
	/*
	 * private String uploadFileName; private long size;
	 * 
	 * public String getUploadFileName() { return uploadFileName; }
	 * 
	 * public void setUploadFileName(String uploadFileName) { this.uploadFileName =
	 * uploadFileName; }
	 * 
	 * public long getSize() { return size; }
	 * 
	 * public void setSize(long size) { this.size = size; }
	 */


	
//	@Transient
//	private MultipartFile uploadfile;
	

	/*
	 * public MultipartFile getUploadfile() { return uploadfile; }
	 * 
	 * public void setUploadfile(MultipartFile uploadfile) { this.uploadfile =
	 * uploadfile; }
	 */

	//private String uploadKey;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Lob
	@Column(name="image", columnDefinition = "BLOB")
	private byte [] image;
	
	/*
	 * public String getUploadKey() { return uploadKey; }
	 * 
	 * public void setUploadKey(String uploadKey) { this.uploadKey = uploadKey; }
	 */

	

	public String getName() {
		return name;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public byte [] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
