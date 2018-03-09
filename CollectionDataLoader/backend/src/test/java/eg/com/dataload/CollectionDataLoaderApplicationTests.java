package eg.com.dataload;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import eg.com.dataload.model.Entity;
import eg.com.dataload.model.env.Env;
import eg.com.dataload.service.DataBaseEnvService;
import eg.com.dataload.service.EntityService;
import eg.com.dataload.service.ExcelService;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class CollectionDataLoaderApplicationTests {

	@Autowired
	ExcelService excelService;
	
	@Autowired
	EntityService entityService;
	
	@Autowired
	DataBaseEnvService dataBaseEnvService;
	
    private File defaultFile;
	
    @Before
    public void setup() {
    	defaultFile = new File("d:\\temp\\collection\\Loaders\\DWH_CUSTOMER_INVOICES_LOADER.xlsx")	;
    }
	
	//@Test
	public void excelParserProperFileTest() {
		try{
			Entity entity = excelService.parseXlS(defaultFile);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//@Test
	public void getXmlRepresentationTest() {
		try{
			Entity entity = excelService.parseXlS(defaultFile);
			entityService.validateEntity(entity);
			try(FileWriter fileWriter = new FileWriter("d:\\temp\\collection\\Loaders\\DWH_CUSTOMER_LOADER.xml")){
				fileWriter.write(entityService.getEntityLoaderXml(entity));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//@Test
	public void getConfiguaredEnvironmentsTest(){
		try{
			List<Env> envs = dataBaseEnvService.getConfiguaredEnvironments();	
			System.out.println(" ["+envs.size()+"] env retrived");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	//@Test
	public void processEntity(){
		try{
			Map<String, DataSource> databases = new HashMap<String, DataSource>();
			databases.put("Collection", dataBaseEnvService.getDataSource("DEV", "Collection"));
			databases.put("Billing", dataBaseEnvService.getDataSource("DEV", "Billing"));
			Entity entity = excelService.parseXlS(defaultFile);
			entityService.validateEntity(entity);
			entityService.processEntity(entity, databases);
			System.out.println("end");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
