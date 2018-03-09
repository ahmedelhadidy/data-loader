package eg.com.dataload.controllers;


import java.io.File;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import eg.com.dataload.aop.annotaion.Loggable;
import eg.com.dataload.model.Entity;
import eg.com.dataload.service.EntityService;
import eg.com.dataload.service.ExcelService;

@Controller
public class DataLoaderController {
 
	static Logger logger = Logger.getLogger(DataLoaderController.class);
	
	@Autowired
	ExcelService excelService;
	
	@Autowired
	EntityService entityService; 
	
	@Loggable
	@PostMapping("/post")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file){
	    System.out.println(file.getOriginalFilename());
	    
	    try {
	    	File f = new File("upload.xlsx");
	        byte [] buffer = new byte[file.getInputStream().available()];	        
	        file.getInputStream().read(buffer);
	        
	        FileOutputStream fileOutputStream = new FileOutputStream(f);
	        fileOutputStream.write(buffer);
	        
	        
			Entity e =  excelService.parseXlS(f);
			return ResponseEntity.status(HttpStatus.OK).body(entityService.getEntityLoaderXml(e));
		}  catch (Exception e) {
			logger.error(e,e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	
	}
}
