package com.java.sample.serviceimpl;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.java.sample.model.Customer;
import com.java.sample.repostiory.CustomerRepository;
import com.java.sample.service.CustomerService;
import com.java.sample.utility.ImageUtil;



@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	/*
	 * Author : Mohtashim Ahmad
	 * 
	 */
	
	
	@Autowired
	private CustomerRepository customerrepository;

	@Override
	public List<Customer> getCustomerList() {
		//Log.debug("Inside getCustomerList Method");
		return this.customerrepository.findAll();
	}

	@Override
	public Customer getCustomerById(Long customer_id) {
		Optional<Customer> optional = customerrepository.findById(customer_id);
		Customer customer = null;
		if(optional.isPresent()) {
			optional.get();
		}
		else {
			throw new RuntimeException("Customer not found for id :"+ customer_id );
		}
		return customer;
	}

	@Override
	public Customer addCustomer(Customer customer,MultipartFile file, String name, String dob) {
		//Customer customer = new Customer();
		customer.setCustomer_id(customer.getCustomer_id());
		customer.setFileName(file.getOriginalFilename());
		customer.setName(name);
		customer.setDob(dob);		
		customer.setImage(ImageUtil.compressImage(file.getContentType().getBytes()));
		return customerrepository.save(customer);
	}
	
	
	@Override
	public String addCustomerDetails(Customer customer) {
		String ret="success";
		//Customer customer = null;
		Date now = new Date();
		try {
			
			customer=new Customer();
			//customer.setSlNo(bulkUserdTo.getSlNo());
			customer.setCustomer_id(customer.getCustomer_id());
			customer.setDob(customer.getDob());
			customer.setName(customer.getName());
			//bulkUservo.setSubauaID(bulkUserdTo.getDepartementid());
			//bulkUservo.setUploadedBy(bulkUserdTo.getUserId());
			//bulkUservo.setUploadKey(bulkUserdTo.getUploadKey());
			customer.setImage(customer.getImage());
			//bulkUservo.setUploadStatus(bulkUserdTo.getUploadStatus());
			//bulkUservo.setUploadedOn(now);
			
			
			
			//bulkUserRepository.saveAndFlush(bulkUservo);
			customerrepository.saveAndFlush(customer);
			//issueListRepository.saveAndFlush(issueLogvo);
			//return ret;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ret;
		
	}
	
	

	@Override
	public Customer updateCustomer(Customer customer) {
		Optional<Customer> customerDb = this.customerrepository.findById(customer.getCustomer_id());
		
		if(customerDb.isPresent()) {
			Customer updateCustomer=customerDb.get();
			updateCustomer.setName(updateCustomer.getName());
			updateCustomer.setDob(customer.getDob());
			updateCustomer.setImage(customer.getImage());
			customerrepository.save(updateCustomer);
			return updateCustomer;
		}else {
			System.out.println("Record is not updated");
		}
		return customer;
		
	}

	@Override
	public void deleteCustomerById(Long customer_id) {
		this.customerrepository.deleteById(customer_id);
		
	}

	@Override
	public Customer saveCustomer(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

}
