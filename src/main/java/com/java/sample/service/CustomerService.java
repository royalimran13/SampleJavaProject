package com.java.sample.service;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.java.sample.model.Customer;


public interface CustomerService  {
	
	/*
	 * Author : Mohtashim Ahmad
	 * 
	 */
	
	public List<Customer> getCustomerList();
	
	public Customer getCustomerById(Long customer_id) throws Exception;
	
	public Customer addCustomer(Customer customer, MultipartFile file, String name, String dob);
	
	public Customer updateCustomer(Customer customer);
	
	public void deleteCustomerById(Long customer_id);
	
	public String addCustomerDetails(Customer customer);
	
	public Customer saveCustomer(MultipartFile file);
	

}
