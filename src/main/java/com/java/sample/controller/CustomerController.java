package com.java.sample.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.sample.model.Customer;
import com.java.sample.repostiory.CustomerRepository;
import com.java.sample.repostiory.FIleUploadUtil;
import com.java.sample.service.CustomerService;

import javassist.expr.NewArray;


public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	
	  @Autowired private CustomerRepository customerRepository;
	  
		@Value("${file.photo.Image}")
		private String uploadBulkUserDocs;
	 
	 @GetMapping("/")
		public String viewHomePage(Model model) {
		 List<Customer> customerList=customerService.getCustomerList();
		 model.addAttribute("customerList", customerList);
		 model.addAttribute("customer", customerList);
		 
		 return "index";		
		}
	
	 @GetMapping("/CustomerForm")
		public String showNewCustomerForm(Model model) {
		 	
			model.addAttribute("customer", new Customer());
			return "newcustomer";
		}
	 
	 @PostMapping("/saveCustomer")
		public String saveCustomer(@ModelAttribute("customer") Customer Customer, 
				 Model model, @RequestParam("image") MultipartFile multipartFile, RedirectAttributes redirectAttributes)throws IOException {
		 
		 	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		 	System.out.println("File name"+fileName);
		 	FIleUploadUtil fIleUploadUtil = new FIleUploadUtil();
		 	//customer.setImage(fileName);
		 	//Customer savedCustomer = customerRepository.save(customer);
		 	//System.out.println("saved customer" + savedCustomer);
		 	//String uploadDir = "Photos/" +savedCustomer.getId();
		 	//fIleUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		 //	customerService.addCustomer(customer);
		 	System.out.println("customer saved successfully");
		 	
			return "newcustomer";
		}
	 
	 @RequestMapping(value = "/addUser", method = RequestMethod.POST )
	 public String addUser(@ModelAttribute("customer") Customer customer, 
			 Model model, @RequestParam("image1") MultipartFile uploadfile, RedirectAttributes redirectAttributes) throws IOException {
		 
		 
			
			 File destPath= null;
			 String result = "";
			 String uploadFileName="";
			 String extension = "";
			 
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss_"); 
			 Date now = new Date();
			
			 model.addAttribute("customer", new Customer());
			 
			 destPath = new File(uploadBulkUserDocs);
			 if(!destPath.exists()) {
				 destPath.mkdirs();
			 }
			 
			 if(uploadfile == null) {
				 model.addAttribute("err msg", "Please select file");
				 return "newcustomer";
			 }
			 String extn = "";
			// if(customer.getUploadKey() != null) {
				 
				 //extn = customer.getUploadKey().substring(customer.getUploadKey().lastIndexOf("."),
						// customer.getUploadKey().length());
				 if(!extn.equalsIgnoreCase(".png") || extn.equalsIgnoreCase("jpeg")) {
					 model.addAttribute("errMsg", "Inavalid Format");
					 return "newcustomer";
				 }
				 if(!uploadfile.isEmpty()) {
					 extension = FilenameUtils.getExtension(uploadfile.getOriginalFilename());
					 uploadFileName =  sdf.format(now) + "image.png";
					 //customer.setUploadKey(uploadFileName);
					 FileCopyUtils.copy(uploadfile.getBytes(), new File(uploadBulkUserDocs + File.separator + uploadFileName));
					 
				 }
				 
				 result = customerService.addCustomerDetails(customer);
				 
				 if("success".equals(result)) {
					 model.addAttribute("customer", new Customer());
					 model.addAttribute("msg", "File Uploaded Successfully");
					 
				 }else {
					model.addAttribute("arrmsg", "File not added.");
				 }
				
			
				// return "newcustomer";

		 
			 //}
			return "newcustomer";
			}
	 
	 private final String UPLOAD_DIRECTORY = "E:\\SimpleSolution\\upload";
	 
	 @PostMapping("/uploadFile")
	 public String uploadDetails(@RequestParam("image") MultipartFile file, Model model,
			 RedirectAttributes redirectAttributes) throws IOException {
		 Customer customer = new Customer();
		
		 if(file.isEmpty()) {
			 redirectAttributes.addFlashAttribute("errmsg", "please select a file");
			 return "newcustomer";
		 }
		 
		 Path path = Paths.get(UPLOAD_DIRECTORY,file.getOriginalFilename());
		 Files.write(path, file.getBytes());
		 //redirectAttributes.addFlashAttribute("success msg", "File upload success"+ file.getOriginalFilename());
		 String fileName = file.getOriginalFilename();
		// customer.setUploadFileName(fileName);
		 customer.setImage(file.getContentType().getBytes());
		 //customer.setSize(file.getSize());
		// customerService.addCustomer(customer);
		 customerRepository.saveAndFlush(customer);
		 model.addAttribute("Success", "Details uploaded");
		 model.addAttribute("customer", new Customer());

		 return "newcustomer";
	 }
	 
	 
}
