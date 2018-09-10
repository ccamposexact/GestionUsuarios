package com.gestionusuario.app;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class GestionUsuariosApplication {
	
	
	//private static final Logger logger = LoggerFactory.getLogger(GestionUsuariosApplication.class);
	
	
	public static void main(String[] args) {
		SpringApplication.run(GestionUsuariosApplication.class, args);
		
	}
}
