package com.gestionusuario.app.controller;


import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

import static com.gestionusuario.app.enumerator.Identificadores.AUTHENTICATION_PREFIX;
import static com.gestionusuario.app.enumerator.Identificadores.HEADER_STRING;



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
		
		String decoderString = null;
        String [] part ;
        Base64 decoder = new Base64();
        byte[] decodedBytes;
        String Basictoken64= null;
        String username = null;
        String password =null;
        
        
		String auth = header.getHeader(HEADER_STRING);
		
		Basictoken64 = auth.replace(AUTHENTICATION_PREFIX,"");
    	decodedBytes = decoder.decode(Basictoken64);
    	decoderString = new String (decodedBytes);
    	part=decoderString.split(":");
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

		return ResponseEntity.ok(new AuthToken(token));
   }
	
	
	@RequestMapping(value = "/cerrarsession", method = RequestMethod.POST)
    public ResponseEntity<?> cerrar(HttpServletRequest header ) throws AuthenticationException, Exception {
		
		String decoderString = null;
        String [] part ;
        Base64 decoder = new Base64();
        byte[] decodedBytes;
        String Basictoken64= null;
        String username = null;
        String password =null;
        
        
		String auth = header.getHeader(HEADER_STRING);
		
		Basictoken64 = auth.replace(AUTHENTICATION_PREFIX,"");
    	decodedBytes = decoder.decode(Basictoken64);
    	decoderString = new String (decodedBytes);
    	part=decoderString.split(":");
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
			
/**/			
			int x = usuario.getIdUsuario().intValue();
			
			System.out.println(x);

			sesionservice.CerrarSesion(x);
			
		//return ResponseEntity.ok(new AuthToken(token));
			return ResponseEntity.ok(null);
/**/			
   }
	
	@RequestMapping(value = "/cerrarsessions", method = RequestMethod.POST)
    public void cerrar() throws Exception {
		
			sesionservice.CerrarSesion(UsuarioActual);
	}
}
