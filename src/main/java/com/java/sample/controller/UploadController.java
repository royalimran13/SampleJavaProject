package com.java.sample.controller;

import java.io.File;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.java.sample.model.Customer;
import com.java.sample.repostiory.CustomerRepository;
import com.java.sample.service.CustomerService;

import javassist.expr.NewArray;


@Controller
public class UploadController {
	
	/*
	 * 
	 * Author : Mohtashim Ahmad
	 * 
	 * 
	 */
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/newForm")
	public String startPage(Model model) {
		model.addAttribute("customer",new Customer());
		return "newcustomer";
	}
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listCustomers", customerService.getCustomerList());
		return "index";
	}

	@PostMapping("/uploadData")
	public String uploadCustomer(@RequestPart MultipartFile image,Model model) {
		try {
			Customer customer = new Customer();
			if(image.isEmpty()) {
				System.out.println("no image uploaded");
			}else {
				image.transferTo(new File("E:\\SimpleSolution\\upload" + image.getOriginalFilename()));
				model.addAttribute("customer",new Customer());
			}
			customerRepository.saveAndFlush(customer);
			
		} catch (Exception e) {
		
			e.printStackTrace();
			System.out.println("There is some problem");
		}
		return "newcustomer";
	}
	
	
	@PostMapping("/uploadDetails")
	public String uploadCustomerDetails( @RequestParam ("image")
			MultipartFile file, Model model, @RequestParam ("name") String name, @RequestParam ("dob") String dob) throws IOException {
		
			    Customer customer = new Customer();
				
				System.out.println("no image uploaded");
				//file.transferTo(new File("E:\\SimpleSolution\\upload" + file.getOriginalFilename()));
				customerService.addCustomer(customer, file, name, dob);
				customerRepository.save(customer);
				model.addAttribute("customer",new Customer());
				model.addAttribute("msg", "Successfullu Save Data");
				
				//customerRepository.saveAndFlush(customer);
				
				return "redirect:/";
	}
	
	@GetMapping("/showUpdateForm/{customer_id}")
	public String showFormForUpdate(@PathVariable (value = "customer_id") long customer_id, 
			Model model,Customer customer1 ) {
		
		try {
			//Customer customer
			 customer1 = customerService.getCustomerById(customer_id);
			//customer1.getDob();
			model.addAttribute("customer1",customer1);
			//model.addAttribute("customer", customer);
		}catch (Exception e) {
			e.printStackTrace();
		}
				
		return "updatecustomer";
	}
	
	
	@RequestMapping("/deleteCustomer/{customer_id}")
	public String deleteCustomerRecord(@PathVariable (value = "customer_id") Long customer_id) {
		this.customerService.deleteCustomerById(customer_id);
		return "redirect:/";
	}
	
}
