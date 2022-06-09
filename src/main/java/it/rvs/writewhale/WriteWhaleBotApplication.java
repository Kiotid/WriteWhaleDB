package it.rvs.writewhale;

import it.rvs.writewhale.controller.UsersController;
import it.rvs.writewhale.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"it.rvs.writewhale"})
public class WriteWhaleBotApplication{

	public static void main(String[] args){
		System.setProperty("org.apache.commons.logging.Log","org.apache.commons.logging.impl.Jdk14Logger");

		SpringApplication.run(WriteWhaleBotApplication.class,args);

	}

}
