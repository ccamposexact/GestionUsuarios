package com.gestionusuario.app;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.datasource.DataSourceUtils;

@SpringBootApplication
public class GestionUsuariosApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(GestionUsuariosApplication.class, args);
		System.out.println("Test OK|");
		
		
	}
}
