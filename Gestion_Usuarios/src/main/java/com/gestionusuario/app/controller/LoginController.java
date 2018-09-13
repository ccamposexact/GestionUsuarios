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
import com.gestionusuario.app.security.JwtTokenUtil;
import com.gestionusuario.app.service.LoginUsuarioService;
import com.gestionusuario.app.service.PermisoService;
import com.gestionusuario.app.service.UsuarioService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/login")
public class LoginController  {
	
	
		@Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;
	    
	    @Autowired
	    private LoginUsuarioService loginusuarioservice;
	
	
	@RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUsuario loginusuario) throws AuthenticationException, Exception {
		
	
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		loginusuario.getUsername(),
                		loginusuario.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final Usuario usuario = loginusuarioservice.findOne(loginusuario.getUsername());
        final String token = jwtTokenUtil.generateToken(usuario);
        System.out.println(token);
        return ResponseEntity.ok(new AuthToken(token));
    }
	
}
