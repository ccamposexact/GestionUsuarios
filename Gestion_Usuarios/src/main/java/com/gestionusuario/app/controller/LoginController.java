package com.gestionusuario.app.controller;




import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.gestionusuario.app.entity.Usuario;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
public class LoginController {
	

	@PostMapping(path = "/logear", produces = "application/json")
    public String Logear(@RequestBody Usuario usuario) {
		return "nombre "+usuario.getUsername()+"password"+usuario.getPassword();
    }
	
}
