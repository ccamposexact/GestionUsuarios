package com.gestionusuario.app;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GestionUsuariosApplication {
	
	
	private static final Logger logger = LoggerFactory.getLogger(GestionUsuariosApplication.class);
	
	
	public static void main(String[] args) {
		SpringApplication.run(GestionUsuariosApplication.class, args);
		
		
		String databaseURL = "jdbc:sqlserver://localhost;databaseName=GestionUsuarios;user=sa;password=123456";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(databaseURL);
            if (conn != null) {
            	logger.debug("--Aplicacion Iniciada--");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
	}
}
