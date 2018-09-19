package com.gestionusuario.app.controller;




import java.util.Base64;

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
		Decoder decoder = new Decoder();
		part=decoder.decode(header);
		
		final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		part[0],
                		part[1]
                )
        );
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
        	final Usuario usuario = loginusuarioservice.findOne(part[0]);
			UsuarioActual=sesionservice.CrearSesion(usuario.getIdUsuario());
			final String token = jwtTokenUtil.generateToken(usuario,UsuarioActual);

		return ResponseEntity.ok(new AuthToken(token));
   }
	
	
@RequestMapping(value = "/cerrarsession", method = RequestMethod.POST)
public void cerrar(String idsesion ) throws AuthenticationException, Exception {
		sesionservice.CerrarSesion(Integer.parseInt(idsesion));
	}
	

}
