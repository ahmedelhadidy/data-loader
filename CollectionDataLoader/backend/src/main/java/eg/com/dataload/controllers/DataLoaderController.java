package eg.com.dataload.controllers;


import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import eg.com.dataload.aop.annotaion.Loggable;
import eg.com.dataload.controllers.vo.model.ProcessEntityRequest;
import eg.com.dataload.model.Entity;
import eg.com.dataload.model.env.Database;
import eg.com.dataload.model.env.Env;
import eg.com.dataload.service.DataBaseEnvService;
import eg.com.dataload.service.EntityService;
import eg.com.dataload.service.ExcelService;

@RestController
public class DataLoaderController {
 
	static Logger logger = Logger.getLogger(DataLoaderController.class);
	
	@Autowired
	ExcelService excelService;
	
	@Autowired
	EntityService entityService; 
	
	@Autowired
	DataBaseEnvService dataBaseEnvServ;
	
	@Loggable
	@RequestMapping(value="/post",method= RequestMethod.POST)
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file){
	    System.out.println(file.getOriginalFilename());
	    
	    try {
	    	File f = new File("upload.xlsx");
	        byte [] buffer = new byte[file.getInputStream().available()];	        
	        file.getInputStream().read(buffer);
	        
	        FileOutputStream fileOutputStream = new FileOutputStream(f);
	        fileOutputStream.write(buffer);
	        
	        
			Entity e =  excelService.parseXlS(f);
			return ResponseEntity.status(HttpStatus.OK).body(e);
		}  catch (Exception e) {
			logger.error(e,e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR). body(e.getMessage());
		}
	
	}
	
	@Loggable
	@RequestMapping(value="/process",method=RequestMethod.POST)
	public ResponseEntity<?> processEntity(@RequestBody ProcessEntityRequest req){
		try{			
			entityService.processEntity(req.getEntity(), dataBaseEnvServ.getEnvDataSources(req.getEnv().getName()));
			return ResponseEntity.status(HttpStatus.OK).build();			
		}catch(Exception e){
			logger.error(e,e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR). body(e.getMessage()); 
		}
	}
	
	@Loggable
	@RequestMapping(value="/getenvs",method=RequestMethod.GET)
	public ResponseEntity<List<Env>> getAllEnvironments() {
		
		try{
			List<Env> allenvs = dataBaseEnvServ.getAllEnvs();
			return ResponseEntity.status(HttpStatus.OK).body(allenvs);
		}catch(Exception e){
			logger.error(e,e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Loggable()
	@RequestMapping(value="/createenv",method=RequestMethod.POST,consumes={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> createEnv(@RequestBody Env env){
		try{
			Env savedEnv = dataBaseEnvServ.createEnv(env);
			return ResponseEntity.status(HttpStatus.OK).body(savedEnv);
		}catch(Exception e){
			logger.error(e,e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR). body(e.getMessage()); 
		}
	}
	
	@Loggable()
	@RequestMapping(value="/creatdb",method=RequestMethod.POST,consumes={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> createDb(@RequestBody Env env){
		try{
			Database createdDatabase = dataBaseEnvServ.createDatabase(env, env.getDatabase().get(0));
			return ResponseEntity.status(HttpStatus.OK).body(createdDatabase);
		}catch(Exception e){
			logger.error(e,e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR). body(e.getMessage()); 
		}
	}
	
	
}
