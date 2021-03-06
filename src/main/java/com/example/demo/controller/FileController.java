package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
	
	public static String path = "d:\\";
	
	@PostMapping("/upload")    
	public String upload(MultipartFile file) throws Exception 
	{       
		System.out.println(file.getName());        
		System.out.println(file.getOriginalFilename());        
		System.out.println(file.getSize());        
		File localFile = new File(path, file.getOriginalFilename());
		file.transferTo(localFile);        
		return "上传成功";  
	}    
	
	@GetMapping("/{id}")    
	public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {      
		try {        
			InputStream inputStream = new FileInputStream(new File(path, id + ".jpg"));                
			OutputStream outputStream = response.getOutputStream();
			response.setContentType("application/x-download");            
			response.addHeader("Content-Disposition", "attachment;filename=" + id + ".jpg");            
			IOUtils.copy(inputStream, outputStream);        
		} catch (Exception e) {            
			e.printStackTrace();        
		}    
	}

}
