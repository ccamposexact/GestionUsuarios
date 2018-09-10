package com.gestionusuario.app.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gestionusuario.app.entity.Usuario;
import com.gestionusuario.app.service.UsuarioService;

@CrossOrigin(origins = { "http://localhost:4200/usuario" })
@RestController
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioservice;
	
/*
	@PostMapping(path = "/logear", produces = "application/json")
    public String Logear(@RequestBody Usuario usuario) throws Exception {
		
		usuarioservice.LoguearUsuario(usuario.getUsername(), usuario.getPassword());
		
		return  "{\"RPTA\":\"SE REGISTRO PEaaaaaaaaaaaaaaaaaaaaaaaRFIL\"}";
    }
*/
	
	@RequestMapping(path = "/logear", produces = "application/json", method = RequestMethod.GET)
    public String Logear(@RequestBody Usuario usuario) throws Exception {
		
		usuarioservice.LoguearUsuario(usuario.getUsername(), usuario.getPassword());
		
		return  "{\"RPTA\":\"SE REGISTRO PEaaaaaaaaaaaaaaaaaaaaaaaRFIL\"}";
    }
	
}
