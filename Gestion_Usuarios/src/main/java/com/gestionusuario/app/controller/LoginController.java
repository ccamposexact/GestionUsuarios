package com.gestionusuario.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gestionusuario.app.entity.LoginUsuario;
import com.gestionusuario.app.entity.Usuario;

import com.gestionusuario.app.security.AuthToken;
import com.gestionusuario.app.security.JwtAuthenticationFilter;
import com.gestionusuario.app.security.JwtTokenUtil;
import com.gestionusuario.app.service.LoginUsuarioService;
import com.gestionusuario.app.service.PermisoService;
import com.gestionusuario.app.service.SesionService;
import com.gestionusuario.app.service.UsuarioService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/login")
public class LoginController  {
	
		public int UsuarioActual;
	
	

	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;
	    
	    @Autowired
	    private LoginUsuarioService loginusuarioservice;
	    
	    @Autowired
	    private SesionService sesionservice;
	
	//ResponseEntity<?> 
	@RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public String register() throws AuthenticationException, Exception {
		
		
		
		
       // final Usuario usuario = loginusuarioservice.findOne();
       // final String token = jwtTokenUtil.generateToken(usuario);
       // if(token!=null) {
       // 	UsuarioActual=sesionservice.CrearSesion(usuario.getIdUsuario());
       // }
       
        //return ResponseEntity.ok(new AuthToken(token));
		
		return "logueado";
    }
	
	
	@RequestMapping(value = "/cerrarsession", method = RequestMethod.GET)
    public String cerrar() throws Exception {
			
		
			sesionservice.CerrarSesion(UsuarioActual);
		
	
       return "Cierre de sesion exitoso";
	
	}
}
