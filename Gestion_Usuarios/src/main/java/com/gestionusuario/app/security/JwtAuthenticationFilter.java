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

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

import static com.gestionusuario.app.enumerator.Identificadores.HEADER_STRING;
import static com.gestionusuario.app.enumerator.Identificadores.SIGNING_KEY;
import static com.gestionusuario.app.enumerator.Identificadores.AUTHENTICATION_PREFIX;
import static com.gestionusuario.app.enumerator.Identificadores.TOKEN_PREFIX;

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
		String password = null;
		String[] part;

		if (header != null && header.startsWith(AUTHENTICATION_PREFIX)) {

			Decoder decoder = new Decoder();
			part = decoder.decode(req, AUTHENTICATION_PREFIX);
			username = part[0];
			password = part[1];
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, password, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				logger.info("authenticated user " + username + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);

			}

			chain.doFilter(req, res);
		} else if (header != null && header.startsWith(TOKEN_PREFIX)) {
			String token = header.replace(TOKEN_PREFIX, "");
			try {
				Jwts.parser()
	            .setSigningKey(SIGNING_KEY)
	            .parseClaimsJws(token)
	            .getBody();
				chain.doFilter(req, res);
			}catch (ExpiredJwtException eje) {
				res.setStatus(894);				
			}catch (MalformedJwtException mje) {
				res.setStatus(498);
			}catch (SignatureException se) {
				res.setStatus(498);
			}
			chain.doFilter(req, res);
		}

	}

}
