package com.gestionusuario.app.security;

import java.io.IOException;
import java.util.Arrays;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


import static com.gestionusuario.app.enumerator.Identificadores.HEADER_STRING;
import static com.gestionusuario.app.enumerator.Identificadores.AUTHENTICATION_PREFIX;

public class JwtAuthenticationFilter extends OncePerRequestFilter {


	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
    private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {

        String header = req.getHeader(HEADER_STRING);
        String username = null;
        String password =null;
        String [] part ;
        
        if (header != null && header.startsWith(AUTHENTICATION_PREFIX)) {
        	
        	Decoder decoder = new Decoder();
    		part=decoder.decode(req);
    		username=part[0];
        	password=part[1];
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
