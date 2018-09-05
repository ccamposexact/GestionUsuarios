package com.gestionusuario.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@CrossOrigin(origins = { "http://localhost:4200" })
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@PostMapping("/auth")
	@ResponseStatus(HttpStatus.FOUND)
	public @ResponseBody String logear(@RequestBody String request) throws Exception{
		
		return "logueado";
	}
	

}
