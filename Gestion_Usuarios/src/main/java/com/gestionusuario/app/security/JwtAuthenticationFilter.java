package com.gestionusuario.app.security;

import java.io.IOException;
import java.util.Arrays;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gestionusuario.app.entity.Usuario;
import com.gestionusuario.app.service.LoginUsuarioService;
import com.gestionusuario.app.service.SesionService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

import static com.gestionusuario.app.enumerator.Identificadores.HEADER_STRING;
import static com.gestionusuario.app.enumerator.Identificadores.TOKEN_PREFIX;
import static com.gestionusuario.app.enumerator.Identificadores.AUTHENTICATION_PREFIX;
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	
	
	
	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    

	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private LoginUsuarioService loginusuarioservice;
	
	@Autowired
    private SesionService sesionservice;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {

        String header = req.getHeader(HEADER_STRING);
        String username = null;
        String password =null;
        String Basictoken64 = null;
        String decoderString = null;
        String [] part ;
        Base64 decoder = new Base64();
        byte[] decodedBytes;
        int UsuarioActual;
        String token=null;
        
        
        if (header != null && header.startsWith(AUTHENTICATION_PREFIX)) {
        	Basictoken64 = header.replace(AUTHENTICATION_PREFIX,"");
        	decodedBytes = decoder.decode(Basictoken64);
        	decoderString = new String (decodedBytes);
        	part=decoderString.split(":");
        	username=part[0];
        	password=part[1];
        	
        	final Usuario usuario = loginusuarioservice.findOne(username);
            
			try {
				token = jwtTokenUtil.generateToken(usuario);
				UsuarioActual=sesionservice.CrearSesion(usuario.getIdUsuario());
				System.out.println(token);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
        	
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, password, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
                
                
           
        }

        chain.doFilter(req, res);
    
		
	}
	
	
	

}
