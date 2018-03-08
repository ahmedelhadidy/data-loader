package eg.com.dataload;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CollectionDataLoaderApplication {

	public static void main(String[] args) {
		ApplicationContext ctx= SpringApplication.run(CollectionDataLoaderApplication.class, args);
		String [] beanNames = ctx.getBeanDefinitionNames();
		System.out.println("------------------Beans------------------------");
		for(String bean:beanNames){
			System.out.println(bean);
		}
	}
}
