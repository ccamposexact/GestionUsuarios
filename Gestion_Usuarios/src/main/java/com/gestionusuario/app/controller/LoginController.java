package com.gestionusuario.app.controller;




import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.gestionusuario.app.entity.Usuario;

import com.gestionusuario.app.security.AuthToken;
import com.gestionusuario.app.security.Decoder;
import com.gestionusuario.app.security.JwtTokenUtil;
import com.gestionusuario.app.service.LoginUsuarioService;
import com.gestionusuario.app.service.SesionService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/login")
public class LoginController  {
	
		public int UsuarioActual;
	
		@Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;
	    
	    @Autowired
	    private LoginUsuarioService loginusuarioservice;
	    
	    @Autowired
	    private SesionService sesionservice;
	
	
	@RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(HttpServletRequest header ) throws AuthenticationException, Exception {
		
		String [] part ;
		String username = null;
        String password =null;
		Decoder decode = new Decoder();
		part=decode.decode(header);
		username=part[0];
    	password=part[1];
		
    	final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		username,
                		password
                )
        );
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
        	final Usuario usuario = loginusuarioservice.findOne(username);
			UsuarioActual=sesionservice.CrearSesion(usuario.getIdUsuario());
			final String token = jwtTokenUtil.generateToken(usuario,UsuarioActual);
			System.out.println(UsuarioActual);
		return ResponseEntity.ok(new AuthToken(token));
   }
	
	
	@RequestMapping(value = "/cerrarsession", method = RequestMethod.POST)
    public void cerrar() throws Exception {
			sesionservice.CerrarSesion(UsuarioActual);
	}
}
