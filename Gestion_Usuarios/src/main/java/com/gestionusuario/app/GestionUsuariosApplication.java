package com.gestionusuario.app;







import java.sql.*;

import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GestionUsuariosApplication {

	private static final Logger logger = LoggerFactory.getLogger(GestionUsuariosApplication.class);
	
	 //private static Logger logger = LogManager.getLogger(GestionUsuariosApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(GestionUsuariosApplication.class, args);
		System.out.println("Test OK|");
		logger.debug("--Application Started--");
		
	}
}
